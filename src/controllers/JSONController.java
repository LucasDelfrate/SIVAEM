package controllers;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import org.json.simple.JSONArray;


//import com.google.gson.Gson;
//import com.google.gson.JsonObject;


import models.Candidato;
import models.Empresa;
import models.Resposta;

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
			
			return resposta1;
			
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
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
	
}
