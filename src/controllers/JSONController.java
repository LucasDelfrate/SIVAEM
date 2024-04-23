package controllers;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import org.json.simple.JSONArray;


//import com.google.gson.Gson;
//import com.google.gson.JsonObject;


import models.Candidato;

public class JSONController {
	
	public JSONObject changeToJSON(Candidato candidato){
		
		JSONObject cand = new JSONObject();
		cand.put("user", candidato.getUser());
		cand.put("password", candidato.getPassword());
		cand.put("operacao", candidato.getOperacao());
		cand.put("email", candidato.getEmail());
		return cand;
		
	}
	
	public void changeCandidatoLoginToJSON(String candidato) {
		Candidato candidato1 = new Candidato();
		JSONParser parser = new JSONParser();
		try {
			
			JSONObject jsonObject = (JSONObject) parser.parse(candidato);
			System.out.println(jsonObject);
			
			String user = (String) jsonObject.get("user");
			System.out.println(user);
			
			String password = (String) jsonObject.get("password");
			System.out.println(password);
			
			
        } catch (ParseException e) {
            e.printStackTrace();
        }
		
	}
	public Candidato changeCandidatoCompletoJSON(String candidato) {
		Candidato candidato1 = new Candidato();
		UUIDController uuidController = new UUIDController();
		JSONParser parser = new JSONParser();
		try {
			
			JSONObject jsonObject = (JSONObject) parser.parse(candidato);
			
			
			candidato1.setUser((String) jsonObject.get("user") );
			candidato1.setPassword((String) jsonObject.get("password"));
			candidato1.setEmail((String) jsonObject.get("email"));
			String stringUUID = uuidController.generateUUID();
			candidato1.setUUID(stringUUID);
			
			return candidato1;
			
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
