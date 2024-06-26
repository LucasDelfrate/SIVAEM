package controllers;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;


//import com.google.gson.Gson;
//import com.google.gson.JsonObject;


import models.Candidato;
import models.CandidatosResposta;
import models.Competencia;
import models.CompetenciaExperiencia;
import models.Empresa;
import models.Filtro;
import models.FiltroCandidato;
import models.FiltroDentro;
import models.Mensagem;
import models.ModeloFiltrarCandidato;
import models.Resposta;
import models.RespostaFiltro;
import models.Vaga;
import models.Vagas;

public class JSONController {
	
	
	public JSONObject changeReponseToJson(Resposta resposta){
			
			JSONObject res = new JSONObject();
			res.put("operacao", resposta.getOperacao());
			res.put("status", resposta.getStatus());
			if(resposta.getMsg() != null) {
				res.put("mensagem", resposta.getMsg());				
			}
			if(resposta.getToken() != null) {
				res.put("token", resposta.getToken());				
			}
			if(resposta.getUser() != null) {
				res.put("nome", resposta.getUser());
			}if(resposta.getEmail() != null) {
				res.put("email", resposta.getEmail());								
			}
			if(resposta.getSenha() != null) {
				res.put("senha", resposta.getSenha());				
			}
			if(resposta.getDescricao() != null) {
				res.put("descricao", resposta.getDescricao());
			}
			if(resposta.getCnpj() != null) {
				res.put("cnpj", resposta.getCnpj());				
			}
			if(resposta.getRamo() != null) {
				res.put("ramo", resposta.getRamo());				
			}
			if(resposta.getRazaoSocial() != null) {
				res.put("razaoSocial", resposta.getRazaoSocial());				
			}
			return res;
			
	}
	
	public JSONObject changeVagasToJson(Vagas vagas) {
		JSONObject res = new JSONObject();
		res.put("operacao", vagas.getOperacao());
		res.put("status", vagas.getStatus());
		
		if(vagas.getVagas() != null) {
			vagas.getVagas().forEach(vaga -> {
				JSONArray vagasArray = new JSONArray();
				for (Vaga c : vagas.getVagas()) {
	                JSONObject competenciaJson = new JSONObject();
	                competenciaJson.put("nome", c.getNome());
	                competenciaJson.put("idVaga", c.getId());
	                vagasArray.add(competenciaJson);
	            }
	            
	            res.put("vagas", vagasArray);
			});
		
        }
		return res;
	}
	
	public JSONObject changeReponseToJsonCompetencia(CompetenciaExperiencia compExp){
		
		JSONObject comp = new JSONObject();
		comp.put("operacao", compExp.getOperacao());
		
		compExp.getCompetencias().forEach(competencia -> {
		});
		
		if (compExp.getCompetencias().size()!= 0) {
            JSONArray competenciasArray = new JSONArray();
            
            for (Competencia c : compExp.getCompetencias()) {
                JSONObject competenciaJson = new JSONObject();
                competenciaJson.put("competencia", c.getDescricao());
                competenciaJson.put("experiencia", c.getExperiencia());
                competenciasArray.add(competenciaJson);
            }
            
            comp.put("competenciaExperiencia", competenciasArray);
        }
		if(compExp.getEmail() != null) {
			comp.put("email", compExp.getEmail());			
		}
		if(compExp.getToken() != null) {
			comp.put("token", compExp.getToken());			
		}
		return comp;
		
}
	
public JSONObject changeReponseToJsonFiltroCandidato(ModeloFiltrarCandidato model){
		
	JSONObject responseJson = new JSONObject();
    responseJson.put("operacao", model.getOperacao());

    // Construção do objeto filtros
    JSONObject filtrosJson = new JSONObject();
    List<Competencia> competencias = model.getFiltros().getCompetenciasExperiencias();
    if (competencias.size() != 0) {
        JSONArray competenciasArray = new JSONArray();

        for (Competencia c : competencias) {
            JSONObject competenciaJson = new JSONObject();
            competenciaJson.put("competencia", c.getDescricao());
            competenciaJson.put("experiencia", c.getExperiencia());
            competenciasArray.add(competenciaJson);
        }

        filtrosJson.put("competenciasExperiencias", competenciasArray);
    }

    // Definindo o tipo nos filtros
    filtrosJson.put("tipo", "OR");

    // Adicionando o objeto filtros ao JSON principal
    responseJson.put("filtros", filtrosJson);

    // Adicionando token se existir
    if (model.getToken() != null) {
        responseJson.put("token", model.getToken());
    }

    return responseJson;
		
}
	
public JSONObject changeReponseToJsonCompetenciaWithStatus(CompetenciaExperiencia compExp){
		
		JSONObject comp = new JSONObject();
		comp.put("operacao", compExp.getOperacao());
		
		compExp.getCompetencias().forEach(competencia -> {
		});
		
		if (compExp.getCompetencias() != null) {
            JSONArray competenciasArray = new JSONArray();
            
            for (Competencia c : compExp.getCompetencias()) {
                JSONObject competenciaJson = new JSONObject();
                competenciaJson.put("competencia", c.getDescricao());
                competenciaJson.put("experiencia", c.getExperiencia());
                competenciasArray.add(competenciaJson);
            }
            
            comp.put("competenciaExperiencia", competenciasArray);
        }
		if(compExp.getEmail() != null) {
			comp.put("email", compExp.getEmail());			
		}
		if(compExp.getToken() != null) {
			comp.put("token", compExp.getToken());			
		}
		comp.put("status", 201);
		return comp;
		
}

public JSONObject changeReponseToJsonVaga(Vaga vaga){
	
	JSONObject vaga1 = new JSONObject();
	vaga1.put("operacao", vaga.getOperacao());
	
	vaga.getCompetencias().forEach(competencia -> {
	});
	
	if (vaga.getCompetencias().size()!= 0) {
        JSONArray competenciasArray = new JSONArray();
        
        for (String c : vaga.getCompetencias()) {
            competenciasArray.add(c.toString());
        }
        
        vaga1.put("competencias", competenciasArray);
    }
	if(vaga.getEmail() != null) {
		vaga1.put("email", vaga.getEmail());			
	}
	if(vaga.getToken() != null) {
		vaga1.put("token", vaga.getToken());			
	}
	if(vaga.getEstado() != null) {
		vaga1.put("estado", vaga.getEstado());			
	}
	if(vaga.getFaixaSalarial() != 0) {
		vaga1.put("faixaSalarial", vaga.getFaixaSalarial());			
	}
	if(vaga.getNome() != null) {
		vaga1.put("nome", vaga.getNome());			
	}
	if(vaga.getDescricao() != null) {
		vaga1.put("descricao", vaga.getDescricao());			
	}
	return vaga1;
	
}

public JSONObject changeReponseToJsonVaga(Resposta vaga){
	
	JSONObject vaga1 = new JSONObject();
	vaga1.put("operacao", vaga.getOperacao());
	
	if (vaga.getCompetenciasString() != null) {
        JSONArray competenciasArray = new JSONArray();
        
        for (String c : vaga.getCompetenciasString()) {
            competenciasArray.add(c.toString());
        }
        
        vaga1.put("competencias", competenciasArray);
    }
	if(vaga.getEmail() != null) {
		vaga1.put("email", vaga.getEmail());			
	}
	if(vaga.getToken() != null) {
		vaga1.put("token", vaga.getToken());			
	}
	if(vaga.getEstado() != null) {
		vaga1.put("estado", vaga.getEstado());			
	}
	if(vaga.getFaixaSalarial() != 0) {
		vaga1.put("faixaSalarial", vaga.getFaixaSalarial());			
	}
	if(vaga.getNome() != null) {
		vaga1.put("nome", vaga.getNome());			
	}
	if(vaga.getDescricao() != null) {
		vaga1.put("descricao", vaga.getDescricao());			
	}
	vaga1.put("status", vaga.getStatus());	

	return vaga1;
	
}

public JSONObject changeReponseToJsonVagaListar(Vaga vaga){
	
	JSONObject vaga1 = new JSONObject();
	vaga1.put("operacao", vaga.getOperacao());
	
	
	if(vaga.getEmail() != null) {
		vaga1.put("email", vaga.getEmail());			
	}
	if(vaga.getToken() != null) {
		vaga1.put("token", vaga.getToken());			
	}
	if(vaga.getFaixaSalarial() != 0) {
		vaga1.put("faixaSalarial", vaga.getFaixaSalarial());			
	}
	if(vaga.getDescricao() != null) {
		vaga1.put("descricao", vaga.getDescricao());			
	}
	if(vaga.getEstado() != null) {
		vaga1.put("estado", vaga.getEstado());			
	}
	if(vaga.getCompetencias() != null) {
		vaga1.put("competencias", vaga.getCompetencias());			
	}
	if(vaga.getNome() != null) {
		vaga1.put("nome", vaga.getNome());			
	}
	int idAux = vaga.getId();
	vaga1.put("idVaga", idAux);
	return vaga1;
	
}

public JSONObject changeReponseToJsonVagaVisualizar(Vaga vaga){
	
	JSONObject vaga1 = new JSONObject();
	vaga1.put("operacao", vaga.getOperacao());
	
	
	if(vaga.getEmail() != null) {
		vaga1.put("email", vaga.getEmail());			
	}
	if(vaga.getToken() != null) {
		vaga1.put("token", vaga.getToken());			
	}
	
	Long idLong = Long.valueOf(vaga.getId());
	if(idLong != null) {
		vaga1.put("idVaga", idLong);			
	}			
	return vaga1;
	
}

public JSONObject changeReponseToJsonVagaListar2(Vaga vaga){
	
	JSONObject vaga1 = new JSONObject();
	vaga1.put("operacao", vaga.getOperacao());
	
	
	if(vaga.getEmail() != null) {
		vaga1.put("email", vaga.getEmail());			
	}
	if(vaga.getToken() != null) {
		vaga1.put("token", vaga.getToken());			
	}
		
	return vaga1;
	
}
	
	
	public Resposta changeResponseToObjectJSON(String resposta) {
		Resposta resposta1 = new Resposta();
		JSONParser parser = new JSONParser();
		try {
			
			JSONObject jsonObject = (JSONObject) parser.parse(resposta);
			
			String operacao = (String) jsonObject.get("operacao");
			resposta1.setOperacao(operacao);

			Object valorStatus = jsonObject.get("status");
			int statusInt = 0; 

			if (valorStatus != null) {
			    if (valorStatus instanceof String) {
			        try {
			            statusInt = Integer.parseInt((String) valorStatus);
			        } catch (NumberFormatException e) {
			            System.err.println("O valor de status não pôde ser convertido para int: " + valorStatus);
			        }
			    } else if (valorStatus instanceof Long) {
			        statusInt = ((Long) valorStatus).intValue();
			    } else {
			        System.err.println("O valor de status não é do tipo String ou Long: " + valorStatus);
			    }
			}

			resposta1.setStatus(statusInt);
			
			String msg = (String) jsonObject.get("mensagem");
			resposta1.setMsg(msg);
			
			String token = (String) jsonObject.get("token");
			resposta1.setToken(token);
			
			String email = (String) jsonObject.get("email");
			resposta1.setEmail(email);
			
			String user = (String) jsonObject.get("nome");
			resposta1.setUser(user);
			
			String password = (String) jsonObject.get("senha");
			resposta1.setSenha(password);
			
			String ramo = (String) jsonObject.get("ramo");
			resposta1.setRamo(ramo);
			
			String razaoSocial = (String) jsonObject.get("razaoSocial");
			resposta1.setRazaoSocial(razaoSocial);
			
			String descricao = (String) jsonObject.get("descricao");
			resposta1.setDescricao(descricao);
			
			String cnpj  = (String) jsonObject.get("cnpj");
			resposta1.setCnpj(cnpj);
				
			
			 JSONArray candidatosArray = (JSONArray) jsonObject.get("candidatos");
	         if (candidatosArray != null && !candidatosArray.isEmpty()) {
	                JSONObject candidatoObj = (JSONObject) candidatosArray.get(0); 
	                long idCandidato = (long) candidatoObj.get("idCandidato");
	                String nomeCandidato = (String) candidatoObj.get("nome");
	                String emailCandidato = (String) candidatoObj.get("email");
	                
	                JSONArray competenciasExperienciaArray = new JSONArray();
	                competenciasExperienciaArray = (JSONArray) candidatoObj.get("competenciaExperiencia");
	                List<CompetenciaExperiencia> competenciasExp = new ArrayList<>();

	                for (Object obj : competenciasExperienciaArray) {
	                    JSONObject competenciaObj = (JSONObject) obj;
	                    String competenciaCandidato = (String) competenciaObj.get("competencia");
	                    int experienciaCandidato = ((Number) competenciaObj.get("experiencia")).intValue();

	                    CompetenciaExperiencia compExp = new CompetenciaExperiencia();
	                    resposta1.setCompetencia(competenciaCandidato.toString());
	                    resposta1.setExperiencia(experienciaCandidato);

	                    competenciasExp.add(compExp);
	                }
			
			
			JSONArray competenciasArray = (JSONArray) jsonObject.get("competenciaExperiencia");
			if(competenciasArray != null) {
				List<Competencia> competencias = new ArrayList<>();
				
				for (Object obj : competenciasArray) {
					JSONObject competenciaJson = (JSONObject) obj;
					
					int experiencia = ((Number) competenciaJson.get("experiencia")).intValue();
					String competencia = (String) competenciaJson.get("competencia");
					
					Competencia comp = new Competencia();
					comp.setDescricao(competencia);;
					comp.setExperiencia(experiencia);
					
					competencias.add(comp);
					resposta1.setCompetencias(competencias);
				}
				
			}
			
//			JSONObject jsonObjectComp = (JSONObject) parser.parse(resposta);
//
//            // Get the competencias array
//            JSONArray competenciasArrayComp = (JSONArray) jsonObjectComp.get("competencias");
//
//            // Convert the competencias array to a list of Strings
//            List<String> competenciasString = new ArrayList<>();
//
//            for (Object competencia : competenciasArrayComp) {
//            	competenciasString.add((String) competencia);
//            }
//            resposta1.setCompetenciasString(competenciasString);
			
			Object faixaSalarialObj = jsonObject.get("faixaSalarial");
			if (faixaSalarialObj != null) {
			    resposta1.setFaixaSalarial(((Number) faixaSalarialObj).intValue());
			}
			
			String estado = (String) jsonObject.get("estado");
			if(estado != null) {
				resposta1.setEstado(estado);				
			}
			
	
			
			JSONArray vagasArray = (JSONArray) jsonObject.get("vagas");
			if(vagasArray != null) {
				List<Vaga> vagas = new ArrayList<>();
				
				for (Object obj : vagasArray) {
					JSONObject vagaObj = (JSONObject) obj;
					
					Long idVaga = (Long) vagaObj.get("idVaga");
					String nome = (String) vagaObj.get("nome");
					
					Vaga vaga = new Vaga();
					vaga.setId(idVaga.intValue());;
					vaga.setNome(nome);
					
					vagas.add(vaga);
					resposta1.setVagas(vagas);;
				}
				
			}
				
			return resposta1;
	         }
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
		return resposta1;
	}

	public JSONObject changeToJSON(Candidato candidato){
		
		JSONObject cand = new JSONObject();
		cand.put("operacao", candidato.getOperacao());
		if(candidato.getUser() != null) {
			cand.put("nome", candidato.getUser());			
		}
		if(candidato.getPassword() != null) {
			cand.put("senha", candidato.getPassword());			
		}
		if(candidato.getEmail() != null) {
			cand.put("email", candidato.getEmail());			
		}
		if(candidato.getUUID() != null) {
			cand.put("token", candidato.getUUID());			
		}
		return cand;
	}
	
public JSONObject changeToJSONEmpresa(Empresa empresa){
		
		JSONObject emp = new JSONObject();
		emp.put("operacao", empresa.getOperacao());

		if(empresa.getEmail() != null) {
			emp.put("email", empresa.getEmail());			
		}
		return emp;
	}
	
	public JSONObject changeEmpresaToJSON(Empresa empresa) {
		JSONObject emp = new JSONObject();
		emp.put("operacao", empresa.getOperacao());
		if(empresa.getEmail() != null) {
			emp.put("email", empresa.getEmail());			
		}
		if(empresa.getUUID() != null) {
			emp.put("token", empresa.getUUID());			
		}
		if(empresa.getRazaoSocial() != null) {
			emp.put("razaoSocial", empresa.getRazaoSocial());			
		}
		if(empresa.getCnpj() != null) {
			emp.put("cnpj", empresa.getCnpj());			
		}
		if(empresa.getRamo() != null) {
			emp.put("ramo", empresa.getRamo());			
		}
		if(empresa.getDescricao() != null) {
			emp.put("descricao", empresa.getDescricao());			
		}
		if(empresa.getSenha() != null) {
			emp.put("senha", empresa.getSenha());			
		}
		return emp;
	}
	
	public Candidato changeCandidatoLoginToJSON(String candidato) {
		Candidato candidato1 = new Candidato();
		JSONParser parser = new JSONParser();
		try {
			
			JSONObject jsonObject = (JSONObject) parser.parse(candidato);
			
			String email = (String) jsonObject.get("email");
			candidato1.setEmail(email);
			
			String password = (String) jsonObject.get("senha");
			candidato1.setPassword(password);
			
			return candidato1;
			
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
		
	}
	public Candidato changeCandidatoCompletoJSON(String candidato) {
		Candidato candidato1 = new Candidato();
		UUIDController uuidController = new UUIDController();
		JSONParser parser = new JSONParser();
		try {
			
			JSONObject jsonObject = (JSONObject) parser.parse(candidato);
			String stringUUID = uuidController.generateUUID();
			
			
			candidato1.setUser((String) jsonObject.get("nome") );
			candidato1.setPassword((String) jsonObject.get("senha"));
			candidato1.setEmail((String) jsonObject.get("email"));
			String oldUUID = (String) jsonObject.get("token");
			if(oldUUID == null) {
				candidato1.setUUID(stringUUID);			
			}else {
				candidato1.setUUID(oldUUID);	
			}		
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
		return candidato1;
		
	}
	
	public Candidato changeCandidatoCompletoJSONWithoutChangeUUID(String candidato) {
		Candidato candidato1 = new Candidato();
		JSONParser parser = new JSONParser();
		try {
			
			JSONObject jsonObject = (JSONObject) parser.parse(candidato);
			
			
			candidato1.setUser((String) jsonObject.get("nome") );
			candidato1.setPassword((String) jsonObject.get("senha"));
			candidato1.setEmail((String) jsonObject.get("email"));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
		return candidato1;
		
	}
	public Empresa changeEmpresaCompletoJSONWithoutChangeUUID(String empresa) {
		Empresa empresa1 = new Empresa();
		JSONParser parser = new JSONParser();
		try {
			
			JSONObject jsonObject = (JSONObject) parser.parse(empresa);
			
			empresa1.setEmail((String) jsonObject.get("email") );
			empresa1.setCnpj((String) jsonObject.get("cnpj") );
			empresa1.setRamo((String) jsonObject.get("ramo"));
			empresa1.setRazaoSocial((String) jsonObject.get("razaoSocial"));
			empresa1.setDescricao((String) jsonObject.get("descricao"));
			empresa1.setSenha((String) jsonObject.get("senha"));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
		return empresa1;
		
	}
	
	public Empresa changeEmpresaCompletoJSON(String empresa) {
		Empresa empresa1 = new Empresa();
		UUIDController uuidController = new UUIDController();
		JSONParser parser = new JSONParser();
		try {
			
			JSONObject jsonObject = (JSONObject) parser.parse(empresa);
			String stringUUID = uuidController.generateUUID();
				
			empresa1.setEmail((String) jsonObject.get("email") );
			empresa1.setSenha((String) jsonObject.get("senha"));
			empresa1.setRamo((String) jsonObject.get("ramo"));
			empresa1.setRazaoSocial((String) jsonObject.get("razaoSocial"));
			empresa1.setCnpj((String) jsonObject.get("cnpj"));
			empresa1.setDescricao((String) jsonObject.get("descricao"));
			String oldUUID = (String) jsonObject.get("token");
			
			if(oldUUID == null) {
				empresa1.setUUID(stringUUID);			
			}else {
				empresa1.setUUID(oldUUID);	
			}		
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
		return empresa1;
		
	}
	
	public CompetenciaExperiencia changeCompetenciaCompletoJSON(String compExp) {
		System.out.println("entrou no change competencia ");
		CompetenciaExperiencia compExp1 = new CompetenciaExperiencia();
		JSONParser parser = new JSONParser();
		try {
			
			JSONObject jsonObject = (JSONObject) parser.parse(compExp);
				
			compExp1.setEmail((String) jsonObject.get("email") );
			String oldUUID = (String) jsonObject.get("token");
			 JSONArray competenciasArray = (JSONArray) jsonObject.get("competenciaExperiencia");
			 if(competenciasArray != null) {
				 List<Competencia> competencias = new ArrayList<>();
				 for (Object obj : competenciasArray) {
					 JSONObject competenciaJson = (JSONObject) obj;
					 int experiencia = ((Number) competenciaJson.get("experiencia")).intValue();
					 String descricao = (String) competenciaJson.get("competencia");
					 Competencia compAux = new Competencia();
					 compAux.setDescricao(descricao);
					 compAux.setExperiencia(experiencia);
					 competencias.add(compAux);
				 }
				 compExp1.setCompetencias(competencias);
				 
			 }
				
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("erro ao transformar pra objeto");
            return null;
        }
		return compExp1;
		
	}
	
	public Vaga changeVagaCompletoJSON(String compExp) {
		Vaga vaga = new Vaga();
		JSONParser parser = new JSONParser();
		try {
			
			JSONObject jsonObject = (JSONObject) parser.parse(compExp);
				
			vaga.setEmail((String) jsonObject.get("email") );
			vaga.setEstado((String) jsonObject.get("estado") );
			Object faixaSalarialObj = jsonObject.get("faixaSalarial");
			if (faixaSalarialObj != null) {
			    vaga.setFaixaSalarial(((Number) faixaSalarialObj).intValue());
			}
			vaga.setNome((String) jsonObject.get("nome") );
			vaga.setDescricao((String) jsonObject.get("descricao") );
			if((jsonObject.get("idVaga")) != null) {
				int idVaga = ((Number) jsonObject.get("idVaga")).intValue();				
				vaga.setId(idVaga);
			}
			String oldUUID = (String) jsonObject.get("token");
			 JSONArray competenciasArray = (JSONArray) jsonObject.get("competencias");
			 if(competenciasArray != null) {
				 List<String> competencias = new ArrayList<>();
				 for (Object obj : competenciasArray) {
					 competencias.add(obj.toString());
				 }
				 vaga.setCompetencias(competencias);
			 }
				
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("erro ao transformar pra objeto");
            return null;
        }
		return vaga;
		
	}
	
	public CompetenciaExperiencia changeCompetenciaCompletoJSONGetEmail(String compExp) throws ParseException {
		System.out.println("entrou no change competencia ");
		CompetenciaExperiencia compExp1 = new CompetenciaExperiencia();
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(compExp);
		compExp1.setEmail((String) jsonObject.get("email") );
		return compExp1;
		
	}
	
	
	public Empresa changeEmpresaLoginToJSON(String emp) {
		Empresa empresa = new Empresa();
		JSONParser parser = new JSONParser();
		try {
			
			JSONObject jsonObject = (JSONObject) parser.parse(emp);
			
			String email = (String) jsonObject.get("email");
			empresa.setEmail(email);
			
			String password = (String) jsonObject.get("senha");
			empresa.setSenha(password);
			
			return empresa;
			
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
		
	}
	
	public String getOperacao(String obj) {
		JSONParser parser = new JSONParser();
		try {
			
			JSONObject jsonObject = (JSONObject) parser.parse(obj);

			String operacao = (String) jsonObject.get("operacao");
			return operacao;
        } catch (ParseException e) {
            e.printStackTrace();
        }
		return obj;
	}
	
	public JSONObject changeReponseToJsonFiltroVaga(Filtro filtro) {
		JSONObject filt = new JSONObject();
		filt.put("operacao", filtro.getOperacao());
		
		
		
		if(filtro.getToken() != null) {
			filt.put("token", filtro.getToken());			
		}
		if(filtro.getFiltros() != null) {
			filt.put("filtros", filtro.getFiltros());			
		}
		JSONObject filtros = new JSONObject();
		filtros.put("competencias", filtro.getFiltros().getCompetencias());
		filtros.put("tipo", filtro.getFiltros().getTipo());
		if(filtro.getFiltros() != null) {
			filt.put("filtros", filtros);			
		}
		return filt;
	}
	
	public Filtro changeFiltroCompletoJSON(String res) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(res);
		Filtro filtro = new Filtro();
		FiltroDentro dentro = new FiltroDentro();
		filtro.setToken((String) jsonObject.get("token") );
		filtro.setOperacao((String) jsonObject.get("operacao") );
		if (jsonObject.get("filtros") != null) {
            JSONObject jsonFiltros = (JSONObject) jsonObject.get("filtros");
            if(jsonFiltros.get("competencias") != null) {
            	List<String> comps = (List<String>) jsonFiltros.get("competencias");
            	dentro.setCompetencias(comps);
            }
            if(jsonFiltros.get("tipo") != null) {
            	dentro.setTipo(jsonFiltros.get("tipo").toString());
            }
            
        }
		filtro.setFiltros(dentro);
		
		return filtro;
	}
	
	public ModeloFiltrarCandidato changeFiltroCompletoJSONtoObjetct(String res) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(res);
		ModeloFiltrarCandidato resposta = new ModeloFiltrarCandidato();
        resposta.setOperacao((String) jsonObject.get("operacao"));
        resposta.setToken((String) jsonObject.get("token"));

        JSONObject jsonFiltros = (JSONObject) jsonObject.get("filtros");
        if (jsonFiltros != null) {
            FiltroCandidato filtros = new FiltroCandidato();

            JSONArray competenciasArray = (JSONArray) jsonFiltros.get("competenciasExperiencias");
            if (competenciasArray != null) {
                List<Competencia> competencias = new ArrayList<>();

                for (Object obj : competenciasArray) {
                    JSONObject competenciaJson = (JSONObject) obj;

                    int experiencia = ((Number) competenciaJson.get("experiencia")).intValue();
                    String competencia = (String) competenciaJson.get("competencia");

                    Competencia comp = new Competencia();
                    comp.setDescricao(competencia);
                    comp.setExperiencia(experiencia);

                    competencias.add(comp);
                }

                filtros.setCompetenciasExperiencias(competencias);
            }

            if (jsonFiltros.get("tipo") != null) {
                filtros.setTipo((String) jsonFiltros.get("tipo"));
            }

            resposta.setFiltros(filtros);
        }

        return resposta;
	}
	
	public JSONObject changeMsgToJson(Mensagem msg) {
		JSONObject obj = new JSONObject();
		obj.put("operacao", msg.getOperacao());
		if(msg.getEmail() != null) {
			obj.put("email", msg.getEmail());			
		}
		if(msg.getToken() != null) {
			obj.put("token", msg.getToken());			
		}
		return obj;
	}
	
	public RespostaFiltro candidatosJson(String msg) throws ParseException {
		RespostaFiltro resposta = new RespostaFiltro();
		JSONParser parser = new JSONParser();
		CandidatosResposta candidatoResposta = new CandidatosResposta();
		JSONObject jsonObject = (JSONObject) parser.parse(msg);
		String operacao = (String) jsonObject.get("operacao");
		int status = ((Number) jsonObject.get("status")).intValue();
		CompetenciaExperiencia compExp = new CompetenciaExperiencia();
		JSONArray candidatosArray = (JSONArray) jsonObject.get("candidatos");
		List<CandidatosResposta> candidatosFinal = new ArrayList();
		 for (Object obj : candidatosArray) {
			 JSONObject candidato = (JSONObject) obj;
             int id = ((Number) candidato.get("idCandidato")).intValue();
             String nome = (String) candidato.get("nome");
             String email = (String) candidato.get("email");
             List<Competencia> listCompetencias = new ArrayList();
             JSONArray competenciasExperiencias = (JSONArray) candidato.get("competenciaExperiencia");
             for (Object obj2 : competenciasExperiencias) {
    			 JSONObject compExpAux = (JSONObject) obj2;
                 String comp = (String) compExpAux.get("competencia");
                 int exp = (((Number) compExpAux.get("experiencia")).intValue());
                 Competencia competenciaAuxiliar = new Competencia();
                 competenciaAuxiliar.setDescricao(comp);
                 competenciaAuxiliar.setExperiencia(exp);
                 listCompetencias.add(competenciaAuxiliar);
    		 }
             compExp.setCompetencias(listCompetencias);
             candidatoResposta.setCompExp(compExp);
             candidatoResposta.setEmail(email);
             candidatoResposta.setId(id);
             candidatoResposta.setNome(nome);
             candidatosFinal.add(candidatoResposta);
		 }
		 resposta.setCandidatos(candidatosFinal);
		 resposta.setStatus(status);
		 return resposta;
	}
	public Vaga visualizarVagaJson(String msg) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(msg);
		Vaga vaga = new Vaga();
		if(jsonObject.get("descricao") != null) {
			String operacao = (String) jsonObject.get("operacao");
			int faixa = ((Number) jsonObject.get("faixaSalarial")).intValue();
			String descricao = (String) jsonObject.get("descricao");
			String estado = (String) jsonObject.get("estado");
			int status = ((Number) jsonObject.get("status")).intValue();
			JSONArray compsArray = (JSONArray) jsonObject.get("competencias");
			vaga.setCompetencias(compsArray);
			vaga.setOperacao(operacao);
			vaga.setDescricao(descricao);
			vaga.setEstado(estado);
			vaga.setFaixaSalarial(faixa);
			vaga.setStatus(status);
			return vaga;
			
		}
		return vaga;
	}
	public List<Vaga> getVagas(String msg) throws ParseException{
		List<Vaga> vagas = new ArrayList();
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(msg);
		String operacao = (String) jsonObject.get("operacao");
		int status = ((Number) jsonObject.get("status")).intValue();
		JSONArray vagasArray = (JSONArray) jsonObject.get("vagas");
		 for (Object vaga : vagasArray) {
			 JSONObject vagaObject = (JSONObject) vaga;
			 Vaga vagaAux = new Vaga();
			 String nome = (String) vagaObject.get("nome");
			 int id = (((Number) vagaObject.get("idVaga")).intValue());
			 vagaAux.setNome(nome);
			 vagaAux.setId(id);
			 vagas.add(vagaAux);
		 }
		return vagas;
	}
}
