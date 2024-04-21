package controllers;

import com.google.gson.Gson;

import models.Candidato;

public class LoginCandidatoController {
	
	public void changeToJSON(Candidato candidato){
		
		Gson gson = new Gson();
		String candidatoJson = gson.toJson(candidato);
		
		System.out.println(candidatoJson);
	}

}
