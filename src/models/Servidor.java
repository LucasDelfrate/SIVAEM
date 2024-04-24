package models;
import java.net.*;
import java.sql.Connection;
import java.sql.SQLException;

import org.json.simple.JSONObject;

import controllers.CadastroController;
import controllers.JSONController;
import controllers.LoginController;
import dao.BancoDados;
import dao.candidatoDAO;
import enums.CadastroEnum;
import enums.LoginEnum;

import java.io.*; 

public class Servidor extends Thread
{ 
 protected static boolean serverContinue = true;
 protected Socket clientSocket;
 protected int porta;


public void ligarServidor() {
	 ServerSocket serverSocket = null; 

	    try { 
	         serverSocket = new ServerSocket(this.porta); 
	         System.out.println ("Connection Socket Created");
	         try { 
	              while (serverContinue)
	                 {
	                  serverSocket.setSoTimeout(10000);
	                  System.out.println ("Waiting for Connection");
	                  try {
	                       new Servidor (serverSocket.accept()); 
	                      }
	                  catch (SocketTimeoutException ste)
	                      {
	                       System.out.println ("Timeout Occurred");
	                      }
	                 }
	             } 
	         catch (IOException e) 
	             { 
	              System.err.println("Accept failed."); 
	              System.exit(1); 
	             } 
	        } 
	    catch (IOException e) 
	        { 
	         System.err.println("Could not listen on port: 10008."); 
	         System.exit(1); 
	        } 
	    finally
	        {
	         try {
	              System.out.println ("Closing Server Connection Socket");
	              serverSocket.close(); 
	             }
	         catch (IOException e)
	             { 
	              System.err.println("Could not close port: 10008."); 
	              System.exit(1); 
	             } 
	        }
	   }
 

 private Servidor(Socket clientSoc)
   {
    clientSocket = clientSoc;
    start();
   }

 public Servidor(int porta) {
	 this.porta = porta;
}


public void run()
   {
    System.out.println ("New Communication Thread Started");

    try { 
         PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), 
                                      true); 
         BufferedReader in = new BufferedReader( 
                 new InputStreamReader( clientSocket.getInputStream())); 

         String res; 
         JSONController jsonController = new JSONController();
         CadastroController cadastroController = new CadastroController();
         LoginController loginController = new LoginController();
         
         
         while ((res = in.readLine()) != null) 
             { 
              System.out.println ("Server: " + res); 
              String op = jsonController.getOperacao(res);
	              switch(op) {
		              case "loginCandidato":{
		            	  Candidato candidato = jsonController.changeCandidatoLoginToJSON(res);
		            	  LoginEnum response = loginController.validarLogin(candidato);
		            	  Resposta resposta = new Resposta();
		            	  resposta.setOperacao("loginCandidato");
		            	  switch(response) {
			            	 case SUCESSO: {
				            		 resposta.setMsg("Login realizado com sucesso!");
				            		 resposta.setStatus(200);
				            		 String uuid;
									try {
										uuid = loginController.getUUID(candidato.getEmail());
										resposta.setToken(uuid);
									} catch (SQLException e) {
										e.printStackTrace();
									}
				            		 System.out.println("LOGIN SUCESSO");
				            		 JSONObject respostaJSON = jsonController.changeReponseToJson(resposta);
			            		 out.println(respostaJSON);
			            		 break;
			            	 	}
			            	 case ERRO_USUARIO_E_SENHA:{
			            		 resposta.setMsg("Login ou senha incorreto");
			            		 resposta.setStatus(401);
			            		 resposta.setToken(candidato.getUUID());
			            		 System.out.println("ERRO NO LOGIN");
			            		 JSONObject respostaJSON = jsonController.changeReponseToJson(resposta);
			            		 out.println(respostaJSON);
			            		 break;
			            	 }
		            	  }
		            	  break;
		              }
		              case "cadastrarCandidato":{
		            	  
		            	 Candidato candidato = jsonController.changeCandidatoCompletoJSON(res);
		            	 CadastroEnum response = cadastroController.validarCadastro(candidato);
		            	 switch(response) {
			            	 case ERRO_USUARIO: {
			            		 out.println("Usuario informado não é válido ou já está cadastrado");
			            		 break;
			            	 }case ERRO_EMAIL: {
			            		 out.println("Email informado não é válido ou já está cadastrado");
			            		 break;
			            	 }case ERRO_SENHA: {
			            		 out.println("Senha informada não é válida");
			            		 break;
			            	 }case SUCESSO:{
			            		 try {
				            			Connection conn = BancoDados.conectar();
				            			new candidatoDAO(conn).cadastrarUsuario(candidato);
				            			out.println("Cadastro realizado com sucesso!");
				            			BancoDados.desconectar();
									} catch (SQLException e) {
										e.printStackTrace();
									}	 
			            		 break;
			            	 }
		            		
		            	}
		            	break;			
		              }
	              }
             } 
         
         out.close(); 
         in.close(); 
         clientSocket.close(); 
        } 
    catch (IOException e) 
        { 
         System.err.println("Problem with Communication Server");
         System.exit(1); 
        } 
    }
} 