package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import dao.BancoDados;
import dao.candidatoDAO;
import models.Candidato;
import models.Empresa;

public class AplicationController {
	
	public Candidato getCandidatoByToken(String token) throws IOException{
		try {
			Connection conn = BancoDados.conectar();
			Candidato response = new candidatoDAO(conn).getCandidato(token);
			System.out.println("Response: "+response);
			BancoDados.desconectar();
			return response;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public Empresa getEmpresaByToken(String token){
		return new Empresa();
		
	}
}
