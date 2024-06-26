package models;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import controllers.JSONController;
import enums.StatusEnum;
import view.AplicationHomeFrame;
import view.CandidatosFiltrados;
import view.EditarCompetencias;
import view.HomeFrame;
import view.LoginCandidatoFrame;
import view.LoginEmpresaFrame;
import view.PerfilFrame;
import view.RegistroCandidatoFrame;
import view.RegistroEmpresaFrame;
import view.UmaVagaFrame;
import view.VagasFiltradas;
import view.VisualizarVagas;

public class Cliente {
	
	private String ip;
	private int porta;
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	private Thread threadEscuta;
	private JSONController json;
	private LoginCandidatoFrame loginFrame;
	private RegistroEmpresaFrame registroEmpFrame;
	private LoginEmpresaFrame loginEmpresaFrame;
	private RegistroCandidatoFrame cadastroFrame;
	private HomeFrame homeFrame;
	private HomeFrame home;
	private AplicationHomeFrame app;
	private PerfilFrame perfil;
	private String email;
	private int idVaga;
	private String token;
	
	
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
		this.perfil = new PerfilFrame(this.app);
		
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
                        String message = resposta.getMsg();
                        String token = resposta.getToken();
                        String email = resposta.getEmail();
                        String senha = resposta.getSenha();
                        String user = resposta.getUser();
                        String cnpj = resposta.getCnpj();
                        String descricao = resposta.getDescricao();
                        String ramo = resposta.getRamo();
                        String razaoSocial = resposta.getRazaoSocial();
                        int status = resposta.getStatus();
                        if(status == 0) {
                        	System.out.println("Não foi informado nenhum status");
                        }
                        String operacao = resposta.getOperacao();
                        switch(operacao){                           
                        	case "loginCandidato":{
                        		String msg;
	                        		if(status == 201 || status == 200) {
	                        			System.out.println("! TOKENNNNN !!: "+ token);
	                        			abrirApp(token);
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
	                        case "cadastrarEmpresa":{
	                        	String msg;
	                        	if(status == 201) {
	                        		
                        			msg = "Empresa cadastrada com sucesso!";
                        			respostaTelaRegistroEmpresa(msg);
                        		}else if(status == 422) {
                        			msg = message;
                        			respostaTelaRegistroEmpresa(msg);
                        		}else {
                        			System.out.println("Status informado não está cadastrado");
                        		}
	                        	break;
	                        }
	                        case "loginEmpresa":{
                        		String msg;
	                        		if(status == 201 || status == 200) {
	                        			abrirAppEmpresa(token);
	                        			fecharTelaLoginEmpresa();
	                        			setarToken(token);
	                        		}else if(status == 401) {
	                        			msg = "Login inválido";
	                        			respostaTelaLoginEmpresa(msg);
	                        		}else {
	                        			System.out.println("Status informado não está cadastrado");
	                        		}
	                        	break;
	                        }
	                        case "visualizarEmpresa":{
	                        	if(status == 201) {	         
                        			retornoGetEmpresa(razaoSocial, descricao, ramo, senha , cnpj );
	                        	}
	                        	else if(status == 404){
	                        		System.out.println("Email não encontrado");
	                        	}
	                        	break;
	                        }
	                        case "apagarEmpresa":{
	                        	String msg;
	                        	if(status == 201) {
	                        		
                        			msg = "Empresa deletado com sucesso!";
                        			respostaTelaHome(msg);
                        		}else if(status == 404) {
                        			msg = "Email não encontrado";
                        		}else {
                        			System.out.println("Status informado não está cadastrado");
                        		}
	                        	break;
	                        }
	                        case "atualizarEmpresa":{
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
	                        case "cadastrarCompetenciaExperiencia":{
	                        	String msg;
	                        	if(status == 201) {	                        		
                        			msg = "Cadastro de competências realizado com sucesso!";
                        			respostaTelaEdit(msg);
	                        	}
	                        	else if(status == 404){
	                        		msg = "Erro ao cadastrar";
	                        		respostaTelaEdit(msg);
	                        	}
	                        	break;
	                        }
	                        case "visualizarCompetenciaExperiencia":{
	                        	String msg;
	                        	if(status == 201) {	         
	                        		
                        			abrirEditComp(resposta.getCompetencias(), token);
	                        	}
	                        	else if(status == 404){
	                        		msg = "Erro ao buscar";
	                        		respostaTelaEdit(msg);
	                        	}
	                        	break;
	                        }
	                        case "atualizarCompetenciaExperiencia":{
	                        	String msg;
	                        	if(status == 201) {	                        		
                        			msg = "Atualização realizada com sucesso!";
                        			respostaTelaEdit(msg);
	                        	}
	                        	else if(status == 404){
	                        		msg = "Erro ao cadastrar";
	                        		respostaTelaEdit(msg);
	                        	}
	                        	break;
	                        }
	                        case "apagarCompetenciaExperiencia":{
	                        	String msg;
	                        	if(status == 201) {	                        		
                        			msg = "Remoção realizada com sucesso!";
                        			respostaTelaEdit(msg);
                        			attLista();
	                        	}
	                        	else if(status == 404){
	                        		msg = "Erro ao remover competencia";
	                        		respostaTelaEdit(msg);
	                        	}
	                        	break;
	                        }
	                        case "cadastrarVaga":{
	                        	String msg;
	                        	if(status == 201) {	                        		
                        			msg = "Cadastro de vaga realizado com sucesso!";
                        			respostaTelaEdit(msg);
	                        	}
	                        	else if(status == 404){
	                        		msg = "Erro ao cadastrar";
	                        		respostaTelaEdit(msg);
	                        	}
	                        	break;
	                        }
	                        case "listarVagas":{
	                        	List<Vaga> vagas = new ArrayList();
	                        	try {
									vagas = json.getVagas(mensagem);
									String msg;
		                        	if(status == 201) {	                        		
	                        			abrirVisuVagas(vagas);
		                        	}
		                        	else if(status == 404){
		                        		msg = "Erro ao listar";
		                        		respostaTelaEdit(msg);
		                        	}
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
	                        	
	                        	break;
	                        }
	                        case "visualizarVaga":{
	                        	Vaga vaga = new Vaga();
	                        	try {
									vaga = json.visualizarVagaJson(mensagem);
									String msg;
		                        	if(status == 201) {	                        		
	                        			abrirUmaVaga(vaga);
		                        	}
		                        	else if(status == 404){
		                        		msg = "Vaga não encontrada";
		                        		respostaTelaEdit(msg);
		                        	}
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
	                        	break;
	                        }
	                        case "filtrarCandidatos":{
	                        	RespostaFiltro res = new RespostaFiltro();
	                        	try {
									res = json.candidatosJson(mensagem);
									String msg;
									System.out.println("STATUS: "+ res.getStatus());
		                        	if(res.getStatus() == 201) {	                        		
	                        			abrirCandidatosFiltrados(res);
		                        	}
		                        	else if(status == 404){
		                        		msg = "Vaga não encontrada";
		                        		respostaTelaEdit(msg);
		                        	}
								} catch (ParseException e) {
									e.printStackTrace();
								}
	                        	
	                        	break;
	                        }
	                        case "filtrarVagas":{
	                        	List<Vaga> vagas = new ArrayList();
	                        	try {
	                        		vagas = json.vagasChangeObject(mensagem);
									String msg;
		                        	if(status == 201) {	  
	                        			abrirVagasFiltrados(vagas);
		                        	}
		                        	else if(status == 404){
		                        		msg = "Vaga não encontrada";
		                        		respostaTelaEdit(msg);
		                        	}
								} catch (ParseException e) {
									e.printStackTrace();
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
    public void setarToken(String token) {
    	this.token = token;
    }
    public void retornoGetEmpresa(String razaoSocial, String descricao, String ramo, String senha, String cnpj) {
    	this.app.receiveEmpresaByEmail(razaoSocial, descricao, ramo, senha , cnpj );
    	System.out.println("caiu no receive");
    }
    public void enviarMensagem(JSONObject msg) {
    	this.out.println(msg);
    	System.out.println("MENSAGEM ENVIADA AO SERVIDOR: " + msg);
    }
    
    public void fecharTerminal() throws IOException {
    	this.in.close();
    	this.out.close();
    	this.clientSocket.close();
    }

	public void setLoginFrame(LoginCandidatoFrame loginFrame) {
		this.loginFrame = loginFrame;
	}
	public void setLoginEmpresaFrame(LoginEmpresaFrame loginEmpresaFrame) {
		this.loginEmpresaFrame = loginEmpresaFrame;
	}
	public void setEmpresaFrame(RegistroEmpresaFrame registro) {
		this.registroEmpFrame = registro;
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
	public void fecharTelaLoginEmpresa() {
		this.loginEmpresaFrame.dispose();
	}
	public void abrirApp(String token) {
		this.token = token;
		AplicationHomeFrame app = new AplicationHomeFrame(this, this.token, this.email);
		this.app = app;
		this.app.getByEmail(true, this.email);
		this.app.setarIsCandidato(true);
		this.app.setVisible(true);
		Mensagem msg = new Mensagem();
		msg.setOperacao("receberMensagem");
		msg.setEmail(this.email);
		msg.setToken(this.token);
		JSONController json = new JSONController();
		JSONObject response = json.changeMsgToJson(msg);
		this.enviarMensagem(response);
	}
	public void abrirAppEmpresa(String token) {
		AplicationHomeFrame app = new AplicationHomeFrame(this, token, this.email);
		this.app = app;
		this.app.getByEmail(false, this.email);
		this.app.setarIsCandidato(false);
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
	public void respostaTelaRegistroEmpresa(String msg) {
		this.registroEmpFrame.mostrarRespota(msg);
	}
	public void respostaTelaLoginEmpresa(String msg) {
		this.loginEmpresaFrame.mostrarRespota(msg);
	}
	public void abrirEditComp(List<Competencia> competencias, String token) {
		EditarCompetencias edit = new EditarCompetencias(competencias, this , token, email);
		edit.setVisible(true);
	}
	public void attLista() {
		this.app.visualizarCompetencia();
	}
	public void abrirVisuVagas(List<Vaga> vagas) {
		VisualizarVagas visuVaga = new VisualizarVagas(this, this.token);
		visuVaga.setarCompetencias(vagas);
		visuVaga.setVisible(true);
	}
	public void abrirUmaVaga(Vaga res) {
		System.out.println("COMPETENCIAS: " + res.getCompetencias());
		UmaVagaFrame uma = new UmaVagaFrame();
		uma.setVariables(this, res.getFaixaSalarial(), res.getDescricao(), res.getEstado(), res.getCompetencias(), this.idVaga, res.getNome(), this.email, this.token);
		uma.setVisible(true);
	}
	public void setIdVaga(int id) {
		this.idVaga = id;
	}
	public void abrirCandidatosFiltrados(RespostaFiltro res){
		CandidatosFiltrados candFilt = new CandidatosFiltrados(this.email, this.token, this, res);
		candFilt.setVisible(true);
	}
	public void abrirVagasFiltrados(List<Vaga> vagas) {
		VagasFiltradas vagasFilt = new VagasFiltradas(vagas);
		vagasFilt.setVisible(true);
	}
}
