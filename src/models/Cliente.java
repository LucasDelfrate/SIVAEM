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
	 private Thread threadEscuta;
	
	
    public Cliente(String ip, int porta) throws UnknownHostException, IOException {
		super();
		this.ip = ip;
		this.porta = porta;
		this.clientSocket = new Socket(this.ip, porta);
		this.out = new PrintWriter(clientSocket.getOutputStream(), 
                true); 
		this.in = new BufferedReader( 
				new InputStreamReader( clientSocket.getInputStream()));
		
		// Iniciar uma thread para escutar mensagens do servidor
        threadEscuta = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String mensagem;
                    while ((mensagem = in.readLine()) != null) {
                        System.out.println("Mensagem do servidor: " + mensagem);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        threadEscuta.start();
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
