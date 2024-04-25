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
				            		JSONObject respostaJSON = jsonController.changeReponseToJson(resposta);
			            		    out.println(respostaJSON);
			            		 break;
			            	 	}
			            	 case ERRO_USUARIO_E_SENHA:{
			            		 resposta.setMsg("Login ou senha incorreto");
			            		 resposta.setStatus(401);
			            		 resposta.setToken(candidato.getUUID());
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
		            	 Resposta resposta = new Resposta();
		            	 resposta.setOperacao("cadastrarCandidato");
		            	 switch(response) {
			            	 case SUCESSO:{
			            		 try {
				            			Connection conn = BancoDados.conectar();
				            			new candidatoDAO(conn).cadastrarUsuario(candidato);
				            			resposta.setMsg("Cadastro realizado com sucesso!");
					            		resposta.setStatus(200);
					            		String uuid;
										try {
											uuid = loginController.getUUID(candidato.getEmail());
											resposta.setToken(uuid);
										} catch (SQLException e) {
											e.printStackTrace();
										}
										JSONObject respostaJSON = jsonController.changeReponseToJson(resposta);
					            		out.println(respostaJSON);
				            			BancoDados.desconectar();
									} catch (SQLException e) {
										e.printStackTrace();
									}	 
			            		 break;
			            	 }case ERRO: {
			            		 resposta.setMsg("Dados inválidos");
			            		 resposta.setStatus(404);
			            		 resposta.setToken(candidato.getUUID());
			            		 JSONObject respostaJSON = jsonController.changeReponseToJson(resposta);
			            		 out.println(respostaJSON);
			            		 break;
			            		 
			            	 }case EMAIL_CADASTRADO: {
			            		 resposta.setMsg("Email já cadastrado");
			            		 resposta.setStatus(422);
			            		 resposta.setToken(candidato.getUUID());
			            		 JSONObject respostaJSON = jsonController.changeReponseToJson(resposta);
			            		 out.println(respostaJSON);
			            		 break;
		            	}
		            			
		              }
	              }
		              break;
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