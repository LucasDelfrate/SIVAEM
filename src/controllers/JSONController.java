package controllers;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import org.json.simple.JSONArray;


//import com.google.gson.Gson;
//import com.google.gson.JsonObject;


import models.Candidato;
import models.Resposta;

public class JSONController {
	
	
	public JSONObject changeReponseToJson(Resposta resposta){
			
			JSONObject res = new JSONObject();
			res.put("operacao", resposta.getOperacao());
			res.put("status", resposta.getStatus());
			res.put("mensagem", resposta.getMsg());
			res.put("token", resposta.getToken());
			return res;
			
	}
	
	public Resposta changeResponseToObjectJSON(String resposta) {
		Resposta resposta1 = new Resposta();
		JSONParser parser = new JSONParser();
		try {
			
			JSONObject jsonObject = (JSONObject) parser.parse(resposta);
			System.out.println(jsonObject);
			
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
			
			return resposta1;
			
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
	}

	public JSONObject changeToJSON(Candidato candidato){
		
		System.out.println("candidato no JSON CONTROLLER: "+ candidato);
		JSONObject cand = new JSONObject();
		cand.put("operacao", candidato.getOperacao());
		cand.put("user", candidato.getUser());
		cand.put("password", candidato.getPassword());
		cand.put("email", candidato.getEmail());
		cand.put("token", candidato.getUUID());
		return cand;
		
	}
	
	public Candidato changeCandidatoLoginToJSON(String candidato) {
		Candidato candidato1 = new Candidato();
		JSONParser parser = new JSONParser();
		try {
			
			JSONObject jsonObject = (JSONObject) parser.parse(candidato);
			System.out.println(jsonObject);
			
			String email = (String) jsonObject.get("email");
			candidato1.setEmail(email);
			
			String password = (String) jsonObject.get("password");
			candidato1.setPassword(password);
			
			return candidato1;
			
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
		
	}
	public Candidato changeCandidatoCompletoJSON(String candidato) {
		System.out.println("=================================================================");
		System.out.println("Res: "+ candidato);
		Candidato candidato1 = new Candidato();
		UUIDController uuidController = new UUIDController();
		JSONParser parser = new JSONParser();
		try {
			
			JSONObject jsonObject = (JSONObject) parser.parse(candidato);
			String stringUUID = uuidController.generateUUID();
			
			
			candidato1.setUser((String) jsonObject.get("user") );
			candidato1.setPassword((String) jsonObject.get("password"));
			candidato1.setEmail((String) jsonObject.get("email"));
			String oldUUID = (String) jsonObject.get("token");
			System.out.println("oldUUID: "+ oldUUID);
			if(oldUUID == null) {
				System.out.println("entrou no olduuid ou seja, length é 0");
				candidato1.setUUID(stringUUID);			
			}else {
				System.out.println("entrou no currentUUID ou seja, length nao é 0");
				candidato1.setUUID(oldUUID);	
			}		
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
		return candidato1;
		
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
