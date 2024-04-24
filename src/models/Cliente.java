package models;
import java.io.*;
import java.net.*;

import org.json.simple.JSONObject;

import controllers.JSONController;
import enums.StatusEnum;
import view.AplicationHomeFrame;
import view.LoginCandidatoFrame;

public class Cliente {
	
	private String ip;
	private int porta;
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	private Thread threadEscuta;
	private JSONController json;
	private LoginCandidatoFrame loginFrame;
	
	
    public Cliente(String ip, int porta) throws UnknownHostException, IOException {
		super();
		this.ip = ip;
		this.porta = porta;
		this.clientSocket = new Socket(this.ip, porta);
		this.out = new PrintWriter(clientSocket.getOutputStream(), 
                true); 
		this.in = new BufferedReader( 
				new InputStreamReader( clientSocket.getInputStream()));
		this.json = new JSONController();
		this.loginFrame = new LoginCandidatoFrame(this);
		
		// Iniciar uma thread para escutar mensagens do servidor
        threadEscuta = new Thread(new Runnable() {
            @Override
            public void run() {
            	System.out.println("THREAD ESTÁ ESCUTANDO MENSAGENS SERVIDOR...");
                try {
                    String mensagem;
                    while ((mensagem = in.readLine()) != null) {
                        System.out.println("Mensagem do servidor: " + mensagem);
                        
                        Resposta resposta = new Resposta();
                        resposta = json.changeResponseToObjectJSON(mensagem);
                        int status = resposta.getStatus();
                        String operacao = resposta.getOperacao();
                        switch(operacao){                           
                        	case "loginCandidato":{
                        		if(status == 200) {
                        			AplicationHomeFrame app = new AplicationHomeFrame();
                        			fecharTelaLogin();
                        			app.setVisible(true);
                        		}else if(status == 401) {
                        			System.out.println("Credenciais incorretas");
                        			retornarRespostaPraTela();
                        		}else {
                        			System.out.println("Status informado não está cadastrado");
                        		}
                        	}
                        }
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

	public void setLoginFrame(LoginCandidatoFrame loginFrame) {
		this.loginFrame = loginFrame;
	}
	
	public void retornarRespostaPraTela() {
		this.loginFrame.respostaAposLogin();
	}
	public void fecharTelaLogin() {
		this.loginFrame.dispose();
	}
    
}
