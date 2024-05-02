package models;
import java.io.*;
import java.net.*;

import org.json.simple.JSONObject;

import controllers.JSONController;
import enums.StatusEnum;
import view.AplicationHomeFrame;
import view.HomeFrame;
import view.LoginCandidatoFrame;
import view.PerfilFrame;
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
	private HomeFrame homeFrame;
	private HomeFrame home;
	private AplicationHomeFrame app;
	private PerfilFrame perfil;
	private String email;
	
	
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
		this.loginFrame = new LoginCandidatoFrame(this.home, this);
		this.cadastroFrame = new RegistroCandidatoFrame(this.home, this);
		this.app = new AplicationHomeFrame(this,  null, null);
		this.perfil = new PerfilFrame(this.app, null, null);
		
		// Iniciar uma thread para escutar mensagens do servidor
        threadEscuta = new Thread(new Runnable() {
            private Cliente cliente;

			@Override
            public void run() {
            	System.out.println("THREAD ESTÁ ESCUTANDO MENSAGENS SERVIDOR...");
                try {
                    String mensagem;
                    while ((mensagem = in.readLine()) != null) {
                    	System.out.println("---------------------------------");
                        System.out.println("Cliente: " + mensagem);
                        System.out.println("---------------------------------");
                        
                        Resposta resposta = new Resposta();
                        resposta = json.changeResponseToObjectJSON(mensagem);
                        String token = resposta.getToken();
                        String email = resposta.getEmail();
                        String senha = resposta.getPassword();
                        String user = resposta.getUser();
                        int status = resposta.getStatus();
                        if(status == 0) {
                        	System.out.println("Não foi informado nenhum status");
                        }
                        String operacao = resposta.getOperacao();
                        switch(operacao){                           
                        	case "loginCandidato":{
                        		String msg;
	                        		if(status == 201 || status == 200) {
	                        			abrirApp(token, email);
	                        			fecharTelaLogin();
	                        		}else if(status == 401) {
	                        			msg = "Login inválido";
	                        			respostaTelaLogin(msg);
	                        		}else {
	                        			System.out.println("Status informado não está cadastrado");
	                        		}
	                        	break;
                        	}case "cadastrarCandidato":{
                        		String msg;
	                        		if(status == 201 || status == 200) {
	                        			AplicationHomeFrame app = new AplicationHomeFrame(this.cliente, token, email);
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
	                        case "apagarCandidato":{
	                        	String msg;
	                        	if(status == 201) {
	                        		
                        			msg = "Candidato deletado com sucesso!";
                        			respostaTelaHome(msg);
                        		}else if(status == 404) {
                        			msg = "Email não encontrado";
                        		}else {
                        			System.out.println("Status informado não está cadastrado");
                        		}
	                        	break;
	                        }
	                        case "logout":{
	                        	String msg;
	                        	if(status == 201) {	                        		
                        			msg = "Você foi deslogado com sucesso!";
                        			respostaTelaApp(msg);
	                        	}
	                        	break;
	                        }
	                        case "atualizarCandidato":{
	                        	String msg;
	                        	if(status == 201) {	                        		
                        			msg = "Edição realizado com sucesso!";
                        			respostaTelaEdit(msg);
	                        	}
	                        	else if(status == 404){
	                        		msg = "Erro ao editar";
	                        		respostaTelaEdit(msg);
	                        	}
	                        	break;
	                        }
	                        case "visualizarCandidato":{
	                        	if(status == 201) {	         
                        			retornoGet(user, senha);
	                        	}
	                        	else if(status == 404){
	                        		System.out.println("Email não encontrado");
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

    public void retornoGet(String user, String senha) {
    	this.app.receiveCandidatoByEmail(user ,senha);
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
	public void setHomeFrame(HomeFrame home) {
		this.home = home;
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
	public void abrirApp(String token, String email) {
		AplicationHomeFrame app = new AplicationHomeFrame(this, token, this.email);
		this.app = app;
		this.app.getByEmail();
		this.app.setVisible(true);
	}
	public void respostaTelaHome(String msg) {
		HomeFrame home = new HomeFrame(this);
		home.respostaTela(msg);
	}
	public void respostaTelaApp(String msg){
		this.app.respostaTela(msg);
	}
	public void respostaTelaEdit(String msg){
		this.app.sendoToPerfil(msg);
	}
	public void saveEmail(String email) {
		this.email = email;
	}
}
