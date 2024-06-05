package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import dao.BancoDados;
import dao.CompetenciaDao;
import dao.candidatoDAO;
import models.Candidato;
import models.Competencia;
import models.CompetenciaExperiencia;
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
	
	public Boolean cadastrarCompetencia(CompetenciaExperiencia compExp) throws IOException{
		for (Competencia comp : compExp.competencias) {
            try {
                Connection conn = BancoDados.conectar();
                CompetenciaDao compDao = new CompetenciaDao(conn);
                compDao.cadastrarCompetencia(comp);
                BancoDados.desconectar();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
		return true;
    }
	
	public Empresa getEmpresaByToken(String token){
		return null;
		
	}
}
