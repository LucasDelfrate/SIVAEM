package models;
import java.net.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import controllers.AplicationController;
import controllers.CadastroController;
import controllers.DeleteController;
import controllers.EdicaoController;
import controllers.JSONController;
import controllers.LoginController;
import controllers.LoginEmpresaController;
import dao.BancoDados;
import dao.CompetenciaDao;
import dao.EmpresaDao;
import dao.VagaDao;
import dao.candidatoDAO;
import enums.CadastroEnum;
import enums.EdicaoEnum;
import enums.EmailEnum;
import enums.LoginEnum;
import view.ClientesConectadosFrame;

import java.io.*; 

public class Servidor extends Thread
{ 
 protected static boolean serverContinue = true;
 protected Socket clientSocket;
 protected int porta;
 private ClientesConectadosFrame conectadosFrame;


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
	         System.err.println("Could not listen on port:"); 
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
	              System.err.println("Could not close port:"); 
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
    String clientIp = clientSocket.getInetAddress().toString();
    atualizarConectados(clientIp);
    
    try { 
         PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), 
                                      true); 
         BufferedReader in = new BufferedReader( 
                 new InputStreamReader( clientSocket.getInputStream())); 

         String res; 
         JSONController jsonController = new JSONController();
         CadastroController cadastroController = new CadastroController();
         EdicaoController editController = new EdicaoController();
         LoginController loginController = new LoginController();
         LoginEmpresaController loginEmpresaController = new LoginEmpresaController();
         
         
         while ((res = in.readLine()) != null) 
             { 
        	  System.out.println("---------------------------------");
              System.out.println ("Server: " + res); 
              System.out.println("---------------------------------");
              String op = jsonController.getOperacao(res);
	              switch(op) {
		              case "loginCandidato":{
		            	  Candidato candidato = jsonController.changeCandidatoLoginToJSON(res);
		            	  LoginEnum response = loginController.validarLogin(candidato);
		            	  Resposta resposta = new Resposta();
		            	  resposta.setOperacao("loginCandidato");
		            	  switch(response) {
			            	 case SUCESSO: {
				            		resposta.setStatus(200);
				            		String uuid;
									try {
										uuid = loginController.getUUID(candidato.getEmail());
										resposta.setToken(uuid);
										JSONObject respostaJSON = jsonController.changeReponseToJson(resposta);
										out.println(respostaJSON);
									} catch (SQLException e) {
										e.printStackTrace();
									}
			            		 break;
			            	 	}
			            	 case ERRO_USUARIO_E_SENHA:{
			            		 resposta.setMsg("Login ou senha incorreto");
			            		 resposta.setStatus(401);
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
					            		resposta.setStatus(201);
					            		String uuid;
										try {
											uuid = loginController.getUUID(candidato.getEmail());
											resposta.setToken(uuid);
										} catch (SQLException e) {
											e.printStackTrace();
										}
										JSONObject respostaJSON = jsonController.changeReponseToJson(resposta);
					            		out.println(respostaJSON);
					            		System.out.println("REPOSTA AO CLIENTE: " + respostaJSON);
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
			            		 System.out.println("REPOSTA AO CLIENTE: " + respostaJSON);
			            		 break;
			            		 
			            	 }case EMAIL_CADASTRADO: {
			            		 resposta.setMsg("Email já cadastrado");
			            		 resposta.setStatus(422);
			            		 resposta.setToken(candidato.getUUID());
			            		 JSONObject respostaJSON = jsonController.changeReponseToJson(resposta);
			            		 out.println(respostaJSON);
			            		 System.out.println("REPOSTA AO CLIENTE: " + respostaJSON);
			            		 break;
		            	}
		            			
		              }
		            	 break;
	              }
		              case "apagarCandidato":{
		            	  Resposta response = new Resposta();
		            	  response.setOperacao("apagarCandidato");
		            	  Candidato candidato = jsonController.changeCandidatoLoginToJSON(res);
		            	  String email = candidato.getEmail();
		            	  DeleteController delControll = new DeleteController();
		            	  EmailEnum resposta = delControll.validarEmailToDelete(email);
		            	  if(resposta == EmailEnum.SUCESSO) {
		            		  response.setMsg("Candidato deletado com sucesso!");
		            		  response.setStatus(201);
		            	  }else if(resposta == EmailEnum.NAO_ENCONTRADO) {
		            		  response.setStatus(404);
		            		  response.setMsg("Email não encontrado");
		            	  }
		            	  JSONObject respostaJSON = jsonController.changeReponseToJson(response);
		            	  out.println(respostaJSON);
		            	  System.out.println("REPOSTA AO CLIENTE: " + respostaJSON);
		            	  break;
		              }
		              case "logout":{
		            	  Resposta response = new Resposta();
		            	  response.setOperacao("logout");
		            	  response.setStatus(204);
		            	  JSONObject respostaJSON = jsonController.changeReponseToJson(response);
		            	  out.println(respostaJSON);
		            	  System.out.println("REPOSTA AO CLIENTE: " + respostaJSON);
		            	  break;
		              }
		              case "atualizarCandidato":{
		            	  	 Candidato candidato = jsonController.changeCandidatoCompletoJSONWithoutChangeUUID(res);
		            	  	 System.out.println("CANDIDATO ANTES DE EDITAR: "+ candidato);
		            	  	 Resposta resposta = new Resposta();
		            	  	 EdicaoEnum response = editController.validarEdicao(candidato);
			            	 resposta.setOperacao("atualizarCandidato");
			            	 switch(response) {
				            	 case SUCESSO:{
				            		 System.out.println("Sucesso ao buscar candidato");
				            		 try {
					            			Connection conn = BancoDados.conectar();
					            			new candidatoDAO(conn).atualizarCandidato(candidato);	
					            			resposta.setMsg("Edição realizada com sucesso!");
						            		resposta.setStatus(201);
											JSONObject respostaJSON = jsonController.changeReponseToJson(resposta);
						            		out.println(respostaJSON);
						            		System.out.println("REPOSTA AO CLIENTE: " + respostaJSON);
					            			BancoDados.desconectar();
										} catch (SQLException e) {
											e.printStackTrace();
										}	 
				            		 break;
				            	 }case ERRO: {
				            		 resposta.setMsg("Dados inválidos");
				            		 resposta.setStatus(404);
				            		 JSONObject respostaJSON = jsonController.changeReponseToJson(resposta);
				            		 out.println(respostaJSON);
				            		 System.out.println("REPOSTA AO CLIENTE: " + respostaJSON);
				            		 break;
				            	 }
			              }
		              }
		              case "visualizarCandidato":{
		            	  	 Candidato candidato = jsonController.changeCandidatoCompletoJSON(res);
		            	  	 Resposta resposta = new Resposta();
		            	  	 String email = candidato.getEmail();
			            	 resposta.setOperacao("visualizarCandidato");
			            	
			            		 try {
				            			Connection conn = BancoDados.conectar();
				            			Candidato resGetCand = new candidatoDAO(conn).getCandidatoByEmail(email);
				            			if(resGetCand != null) {
				            				resposta.setOperacao("visualizarCandidato");
				            				resposta.setUser(resGetCand.getUser());
				            				resposta.setSenha(resGetCand.getPassword());
				            				resposta.setStatus(201);
				            				JSONObject respostaJSON = jsonController.changeReponseToJson(resposta);
				            				out.println(respostaJSON);
				            				System.out.println("REPOSTA AO CLIENTE: " + respostaJSON);
				            			}else{				            				
				            				resposta.setMsg("Email nao encontrado");
				            				resposta.setStatus(404);
				            				JSONObject respostaJSON = jsonController.changeReponseToJson(resposta);
				            				out.println(respostaJSON);
				            				System.out.println("REPOSTA AO CLIENTE: " + respostaJSON);
				            			}
				            			BancoDados.desconectar();
									} catch (SQLException e) {
										e.printStackTrace();
									}	 
			            		 break;	 
			              }
		              case "cadastrarEmpresa":{
		            	  	 Empresa empresa = jsonController.changeEmpresaCompletoJSON(res);
		            	  	 CadastroEnum response = cadastroController.validarCadastroEmpresa(empresa);
		            	  	 Resposta resposta = new Resposta();
		            	  	 String email = empresa.getEmail();
			            	 resposta.setOperacao("cadastrarEmpresa");
			            	 switch(response) {
			            	 case SUCESSO:{
			            		 try {
				            			Connection conn = BancoDados.conectar();
				            			new EmpresaDao(conn).cadastrarEmpresa(empresa);
				            			resposta.setMsg("Cadastro realizado com sucesso!");
					            		resposta.setStatus(201);
					            		String uuid;
										uuid = empresa.getUUID();
										resposta.setToken(uuid);
										JSONObject respostaJSON = jsonController.changeReponseToJson(resposta);
					            		out.println(respostaJSON);
					            		System.out.println("REPOSTA AO CLIENTE: " + respostaJSON);
				            			BancoDados.desconectar();
									} catch (SQLException e) {
										e.printStackTrace();
									}	 
			            		 break;
			            	 }case CNPJ_CADASTRADO: {
			            		 resposta.setMsg("Cnpj já cadastrado");
			            		 resposta.setStatus(422);
			            		 JSONObject respostaJSON = jsonController.changeReponseToJson(resposta);
			            		 out.println(respostaJSON);
			            		 System.out.println("REPOSTA AO CLIENTE: " + respostaJSON);
			            		 break;
			            		 
			            	 }case EMAIL_CADASTRADO: {
			            		 resposta.setMsg("Email já cadastrado");
			            		 resposta.setStatus(422);
			            		 JSONObject respostaJSON = jsonController.changeReponseToJson(resposta);
			            		 out.println(respostaJSON);
			            		 System.out.println("REPOSTA AO CLIENTE: " + respostaJSON);
			            		 break;
		            	}case ERRO: {
		            		 resposta.setMsg("Caracteres inválidos...");
		            		 resposta.setStatus(404);
		            		 JSONObject respostaJSON = jsonController.changeReponseToJson(resposta);
		            		 out.println(respostaJSON);
		            		 System.out.println("REPOSTA AO CLIENTE: " + respostaJSON);
		            		break;
		            	}
		            			
		              }
			            			 break;	 
			              }
		              case "loginEmpresa":{
		            	  Empresa empresa = jsonController.changeEmpresaLoginToJSON(res);
		            	  LoginEnum response = loginEmpresaController.validarLoginEmpresa(empresa);
		            	  Resposta resposta = new Resposta();
		            	  resposta.setOperacao("loginEmpresa");
		            	  switch(response) {
			            	 case SUCESSO: {
				            		resposta.setStatus(200);
				            		String uuid;
									try {
										uuid = loginController.getUUIDEmpresa(empresa.getEmail());
										resposta.setToken(uuid);
										System.out.println(resposta.getToken());
										JSONObject respostaJSON = jsonController.changeReponseToJson(resposta);
										out.println(respostaJSON);
										System.out.println("REPOSTA AO CLIENTE: " + respostaJSON);
									} catch (SQLException e) {
										e.printStackTrace();
									}
			            		 break;
			            	 	}
			            	 case ERRO_USUARIO_E_SENHA:{
			            		 resposta.setMsg("Login ou senha incorreto");
			            		 resposta.setStatus(401);
			            		 JSONObject respostaJSON = jsonController.changeReponseToJson(resposta);
			            		 out.println(respostaJSON);
			            		 System.out.println("REPOSTA AO CLIENTE: " + respostaJSON);
			            		 break;
			            	 }
		            	  }
		            	  break;
		              }
		              case "visualizarEmpresa":{
		            	  Empresa empresa = jsonController.changeEmpresaCompletoJSON(res);
		            	  	 Resposta resposta = new Resposta();
		            	  	 String email = empresa.getEmail();
			            	 resposta.setOperacao("visualizarEmpresa");
			            	
			            		 try {
				            			Connection conn = BancoDados.conectar();
				            			Empresa resGetEmp = new EmpresaDao(conn).getEmpresaByEmail(email);
				            			if(resGetEmp != null) {
				            				resposta.setOperacao("visualizarEmpresa");
				            				resposta.setCnpj(resGetEmp.getCnpj());
				            				resposta.setSenha(resGetEmp.getSenha());
				            				resposta.setDescricao(resGetEmp.getDescricao());
				            				resposta.setRamo(resGetEmp.getRamo());
				            				resposta.setRazaoSocial(resGetEmp.getRazaoSocial());
				            				resposta.setStatus(201);
				            				JSONObject respostaJSON = jsonController.changeReponseToJson(resposta);
				            				out.println(respostaJSON);
				            				System.out.println("REPOSTA AO CLIENTE: " + respostaJSON);
				            			}else{				            				
				            				resposta.setMsg("Email nao encontrado");
				            				resposta.setStatus(404);
				            				JSONObject respostaJSON = jsonController.changeReponseToJson(resposta);
				            				out.println(respostaJSON);
				            				System.out.println("REPOSTA AO CLIENTE: " + respostaJSON);
				            			}
				            			BancoDados.desconectar();
									} catch (SQLException e) {
										e.printStackTrace();
									}	 
			            		 break;	 
			              }
		              case "apagarEmpresa":{
		            	  Resposta response = new Resposta();
		            	  response.setOperacao("apagarEmpresa");
		            	  Empresa empresa = jsonController.changeEmpresaCompletoJSON(res);
		            	  String email = empresa.getEmail();
		            	  DeleteController delControll = new DeleteController();
		            	  EmailEnum resposta = delControll.validarEmailToDeleteEmpresa(email);
		            	  System.out.println(resposta);
		            	  if(resposta == EmailEnum.SUCESSO) {
		            		  response.setMsg("Empresa deletada com sucesso!");
		            		  response.setStatus(201);
		            	  }else if(resposta == EmailEnum.NAO_ENCONTRADO) {
		            		  response.setStatus(404);
		            		  response.setMsg("Email não encontrado");
		            	  }
		            	  JSONObject respostaJSON = jsonController.changeReponseToJson(response);
		            	  out.println(respostaJSON);
		            	  System.out.println("REPOSTA AO CLIENTE: " + respostaJSON);
		            	  break;
		              }
		              case "atualizarEmpresa":{
		            	  	 Empresa emp = jsonController.changeEmpresaCompletoJSONWithoutChangeUUID(res);
		            	  	 System.out.println("EMPRESA ANTES DE EDITAR: "+ emp.getEmail());
		            	  	 Resposta resposta = new Resposta();
		            	  	 CadastroEnum response = cadastroController.validarEdicao(emp);
			            	 resposta.setOperacao("atualizarEmpresa");
			            	 switch(response) {
				            	 case SUCESSO:{
				            		 System.out.println("Sucesso ao buscar empresa");
				            		 try {
					            			Connection conn = BancoDados.conectar();
					            			new EmpresaDao(conn).atualizarEmpresa(emp);	
					            			resposta.setMsg("Edição realizada com sucesso!");
						            		resposta.setStatus(201);
											JSONObject respostaJSON = jsonController.changeReponseToJson(resposta);
						            		out.println(respostaJSON);
						            		System.out.println("REPOSTA AO CLIENTE: " + respostaJSON);
					            			BancoDados.desconectar();
										} catch (SQLException e) {
											e.printStackTrace();
										}	 
				            		 break;
				            	 }case ERRO: {
				            		 System.out.println("erro");
				            		 resposta.setMsg("Dados inválidos");
				            		 resposta.setStatus(404);
				            		 JSONObject respostaJSON = jsonController.changeReponseToJson(resposta);
				            		 out.println(respostaJSON);
				            		 System.out.println("REPOSTA AO CLIENTE: " + respostaJSON);
				            		 break;
				            	 }
				            	 case CNPJ_CADASTRADO: {
				            		 resposta.setMsg("CNPJ já cadastrado");
				            		 resposta.setStatus(422);
				            		 JSONObject respostaJSON = jsonController.changeReponseToJson(resposta);
				            		 out.println(respostaJSON);
				            		 System.out.println("REPOSTA AO CLIENTE: " + respostaJSON);
				            		 break;
				            	 }
			              }
		              }
		              case "cadastrarCompetenciaExperiencia": {
		            	     AplicationController app = new AplicationController();
		            	     CompetenciaExperiencia compExp = jsonController.changeCompetenciaCompletoJSON(res);
		            	  	 Resposta resposta = new Resposta();
			            	 resposta.setOperacao("cadastrarCompetenciaExperiencia");
			            	 Boolean response = app.loopPraCadastrarCompetencia(compExp);
			            	 if(response == true) {
			            		 resposta.setStatus(201);
			            		 resposta.setMsg("Competencia cadastrada com sucesso!");
			            		 JSONObject respostaJSON = jsonController.changeReponseToJson(resposta);
			            		 out.println(respostaJSON);
			            	 }else {
			            		 resposta.setStatus(422);
			            		 resposta.setMsg("Erro");
			            		 JSONObject respostaJSON = jsonController.changeReponseToJson(resposta);
			            		 out.println(respostaJSON);
			            	 }
		            		 break;
		            	 }
		              case "cadastrarVaga": {
		            	     AplicationController app = new AplicationController();
		            	     Vaga vaga = jsonController.changeVagaCompletoJSON(res);
		            	  	 Resposta resposta = new Resposta();
			            	 resposta.setOperacao("cadastrarVaga");
			            	 Boolean response = app.loopPraCadastrarVaga(vaga);
			            	 if(response == true) {
			            		 resposta.setStatus(201);
			            		 resposta.setMsg("Vaga cadastrada com sucesso!");
			            		 JSONObject respostaJSON = jsonController.changeReponseToJson(resposta);
			            		 out.println(respostaJSON);
			            	 }else {
			            		 resposta.setStatus(422);
			            		 resposta.setMsg("Erro");
			            		 JSONObject respostaJSON = jsonController.changeReponseToJson(resposta);
			            		 out.println(respostaJSON);
			            	 }
		            		 break;
		            	 }
		              case "atualizarCompetenciaExperiencia": {
		            	     AplicationController app = new AplicationController();
		            	     CompetenciaExperiencia compExp = jsonController.changeCompetenciaCompletoJSON(res);
		            	  	 Resposta resposta = new Resposta();
			            	 resposta.setOperacao("atualizarCompetenciaExperiencia");
			            	 app.loopPraAtualizarCompetencia(compExp);
			            	 resposta.setStatus(201);
			            	 resposta.setMsg("Competencia atualizada com sucesso!");
			            	 JSONObject respostaJSON = jsonController.changeReponseToJson(resposta);
		            		 out.println(respostaJSON);
		            		 break;
		            	 }
		              case "atualizarVaga": {
		            	  	 AplicationController app = new AplicationController();
		            	     Vaga vaga = jsonController.changeVagaCompletoJSON(res);
		            	  	 Resposta resposta = new Resposta();
			            	 resposta.setOperacao("atualizarVaga");
			            	 
		            		 break;
		            	 }
		              case "apagarCompetenciaExperiencia": {
		            	  	 Resposta resposta = new Resposta();
			            	 resposta.setOperacao("apagarCompetenciaExperiencia");
			            	 CompetenciaExperiencia compExp = jsonController.changeCompetenciaCompletoJSON(res);
			            	 AplicationController app = new AplicationController();
			            	 app.loopPraApagarCompetencia(compExp);
		            		 resposta.setStatus(201);
		            		 resposta.setMsg("Competencia apagada com sucesso!");
		            		 JSONObject respostaJSON = jsonController.changeReponseToJson(resposta);
		            		 out.println(respostaJSON);
		            		 break;
		            	 }
		              case "apagarVaga": {
		            	     AplicationController app = new AplicationController();
		            	     Vaga vaga = jsonController.changeVagaCompletoJSON(res);
		            	  	 Resposta resposta = new Resposta();
			            	 resposta.setOperacao("apagarVaga");
			            	 try {
								Connection conn = BancoDados.conectar();
								VagaDao vagaDao = new VagaDao(conn);
								vagaDao.apagarVagaCompetencias(vaga.getId());
								vagaDao.apagarVagaCompleta(vaga.getId());
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			            	 
		            		 break;
		            	 }
		              case "visualizarCompetenciaExperiencia": {
		            	  System.out.println("entrei no visualizar");
		            	     AplicationController app = new AplicationController();
		            	  	 Resposta resposta = new Resposta();
			            	 try {
			            		 CompetenciaExperiencia compExp = jsonController.changeCompetenciaCompletoJSONGetEmail(res);
			            		 String email = compExp.getEmail();
			            		 System.out.println("entrei no try");
			         			Connection conn = BancoDados.conectar();
			         			List<Competencia> response = new CompetenciaDao(conn).getCompetencias(email);
			         			compExp.setCompetencias(response);
			         			compExp.setOperacao("visualizarCompetenciaExperiencia");
			         			JSONObject respostaJSON = jsonController.changeReponseToJsonCompetenciaWithStatus(compExp);
				            	out.println(respostaJSON);
				            	System.out.println("ENVIANDO PRO CLIENTE: "+ respostaJSON);
			         			BancoDados.desconectar();
			         		} catch (SQLException | ParseException e) {
			         			e.printStackTrace();
			         		}
			            	 
		            		 break;
		            	 }
		              case "visualizarVaga": {
		            	     AplicationController app = new AplicationController();
		            	     Vaga vaga;
							try {
								vaga = jsonController.visualizarVagaJson(res);
							} catch (ParseException e) {
								e.printStackTrace();
							}
		            	  	 Resposta resposta = new Resposta();
			            	 resposta.setOperacao("visualizarVaga");
			            	 Connection conn;
							try {
								conn = BancoDados.conectar();
								VagaDao vagaDao = new VagaDao(conn);
								Vaga vagaResposta = new Vaga();

								if(vagaResposta.getNome() == null) {
									resposta.setStatus(404);
									resposta.setMsg("Vaga não encontrada");
									JSONObject respostaJSON = jsonController.changeReponseToJsonVaga(resposta);
					            	out.println(respostaJSON);
								}else {
									resposta.setDescricao(vagaResposta.getDescricao());
									resposta.setFaixaSalarial(vagaResposta.getFaixaSalarial());
									resposta.setEstado(vagaResposta.getEstado());
									resposta.setCompetenciasString(vagaResposta.getCompetencias());
									resposta.setStatus(201);
									JSONObject respostaJSON = jsonController.changeReponseToJsonVaga(resposta);
					            	out.println(respostaJSON);
								}
							} catch (SQLException e) {
								e.printStackTrace();
							}
		            		 break;
		            	 }
		              case "listarVagas": {
			            	 AplicationController app = new AplicationController();
			            	 Vaga vaga = jsonController.changeVagaCompletoJSON(res);
			            	 Connection conn;
							try {
								conn = BancoDados.conectar();
								VagaDao vagaDao = new VagaDao(conn);
								List<Vaga> vagas;
								vagas = vagaDao.listarVagas(vaga);
								Vagas vagas2 = new Vagas();
								vagas2.setVagas(vagas);
								vagas2.setOperacao("listarVagas");
								vagas2.setStatus(201);
								JSONObject respostaJSON = jsonController.changeVagasToJson(vagas2);
								out.println(respostaJSON);
								
							} catch (SQLException e) {
								e.printStackTrace();
							}
		            	 }
		              case "filtrarVagas": {
		            	     AplicationController app = new AplicationController();
			            	 Filtro filtro;
							try {
								Connection conn;
								filtro = jsonController.changeFiltroCompletoJSON(res);
//								conn = BancoDados.conectar();
//								VagaDao vagaDao = new VagaDao(conn);
//								List<Vaga> vagas;
//								vagas = vagaDao.filtrarVagas(filtro);
								
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		            		 break;
		            	 }
		              case "filtrarCandidato": {
//		            	  	 AplicationController app = new AplicationController();
//		            	  	 Connection conn;
//		            	     try {
//								ModeloFiltrarCandidato model = jsonController.changeFiltroCompletoJSONtoObjetct(res);
//								try {
//									conn = BancoDados.conectar();
//									candidatoDAO candDao = new candidatoDAO(conn);
//									CompetenciaDao compDao = new CompetenciaDao(conn);
//								} catch (SQLException e) {
//									// TODO Auto-generated catch block
//									e.printStackTrace();
//								}
//				
//							} catch (ParseException e) {
//								e.printStackTrace();
//							}
		            	  	
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
    	 this.conectadosFrame.removerConectado(clientIp);
         System.err.println("Problem with Communication Server");
         System.exit(1); 
        } 
    }
	public void atualizarConectados(String ip) {
		if(this.conectadosFrame == null) {
			this.conectadosFrame = new ClientesConectadosFrame();
			this.conectadosFrame.setVisible(true);			
		}
		 this.conectadosFrame.atualizarConectados(ip);
	}
	
} 