package models;
import java.net.*;
import java.sql.SQLException;

import org.json.simple.JSONObject;

import controllers.JSONController;
import dao.UsuarioDAO;

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

         String inputLine; 
         JSONController jsonController = new JSONController();
         ConexaoBanco.iniciarBD();
         
         while ((inputLine = in.readLine()) != null) 
             { 
              System.out.println ("Server: " + inputLine); 
              String res = jsonController.getOperacao(inputLine);
              
              System.out.println(res);
	              switch(res) {
		              case "loginCandidato":{
		            	  jsonController.changeCandidatoLoginToJSON(res);
		              }
		              case "cadastrarCandidato":{
		            	  Candidato candidato = jsonController.changeCandidatoCompletoJSON(res);
		            	  UsuarioDAO userDao = new UsuarioDAO();
		            	  try {
							userDao.adicionarUsuarioCandidato(candidato);
						} catch (SQLException e) {
							e.printStackTrace();
						}
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