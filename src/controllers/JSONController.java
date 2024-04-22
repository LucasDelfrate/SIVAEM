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
		JSONParser parser = new JSONParser();
		try {
			
			JSONObject jsonObject = (JSONObject) parser.parse(candidato);
			
			String user = (String) jsonObject.get("user");
			candidato1.setUser(user);
			String password = (String) jsonObject.get("password");
			candidato1.setPassword(password);
			
			String email = (String) jsonObject.get("password");
			candidato1.setEmail(email);
			
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
