package models;
import java.io.*;
import java.net.*;

import org.json.simple.JSONObject;

import controllers.JSONController;
import enums.StatusEnum;
import view.AplicationHomeFrame;
import view.LoginCandidatoFrame;
import view.RegistroCandidatoFrame;

public class Cliente {
	
	private String ip;
	private int porta;
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	private Thread threadEscuta;
	private JSONController json;
	private LoginCandidatoFrame loginFrame;
	private RegistroCandidatoFrame cadastroFrame;
	
	
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
		this.cadastroFrame = new RegistroCandidatoFrame(this);
		
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
                        if(status == 0) {
                        	System.out.println("Não foi informado nenhum status");
                        }
                        String operacao = resposta.getOperacao();
                        switch(operacao){                           
                        	case "loginCandidato":{
                        		String msg;
	                        		if(status == 200) {
	                        			AplicationHomeFrame app = new AplicationHomeFrame();
	                        			fecharTelaLogin();
	                        			app.setVisible(true);
	                        		}else if(status == 401) {
	                        			msg = "Login inválido";
	                        			respostaTelaLogin(msg);
	                        		}else {
	                        			System.out.println("Status informado não está cadastrado");
	                        		}
	                        	break;
                        	}case "cadastrarCandidato":{
                        		String msg;
	                        		if(status == 200) {
	                        			AplicationHomeFrame app = new AplicationHomeFrame();
	                        			msg = "Cadastro realizado com sucesso!";
	                        			respostaTelaCadastro(msg);
	                        		}else if(status == 404) {
	                        			msg = "Credenciais inválidas";
	                        			respostaTelaCadastro(msg);
	                        		}else if(status == 422){
	                        			msg = "Email já cadastrado";
	                        			respostaTelaCadastro(msg);
	                        		}else {
	                        			System.out.println("Status informado não está cadastrado");
	                        		}
	                        	break;
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
	
	public void respostaTelaLogin(String msg) {
		this.loginFrame.respostaTela(msg);
	}
	public void respostaTelaCadastro(String msg) {
		this.cadastroFrame.respostaTela(msg);
	}
	public void fecharTelaLogin() {
		this.loginFrame.dispose();
	}
    
}
