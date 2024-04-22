package models;
import java.io.*;
import java.net.*;

import org.json.simple.JSONObject;

public class Cliente {
	
	private String ip;
	private int porta;
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	
	
    public Cliente(String ip, int porta) throws UnknownHostException, IOException {
		super();
		this.ip = ip;
		this.porta = porta;
		this.clientSocket = new Socket(this.ip, porta);
		this.out = new PrintWriter(clientSocket.getOutputStream(), 
                true); 
		this.in = new BufferedReader( 
				new InputStreamReader( clientSocket.getInputStream()));
    }

    public void enviarMensagem(JSONObject msg) {
    	this.out.println(msg);
    }
    
    public void fecharTerminal() throws IOException {
    	this.in.close();
    	this.out.close();
    	this.clientSocket.close();
    }

}
