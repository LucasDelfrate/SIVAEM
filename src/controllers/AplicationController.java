package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import dao.BancoDados;
import dao.CompetenciaDao;
import dao.VagaDao;
import dao.candidatoDAO;
import models.Candidato;
import models.Competencia;
import models.CompetenciaExperiencia;
import models.Empresa;
import models.Filtro;
import models.Resposta;
import models.Vaga;

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
	
	public Boolean loopPraCadastrarCompetencia(CompetenciaExperiencia compExp) {
		compExp.competencias.forEach(competencia -> {
			try {
				System.out.println("CADASTRANDO "+ competencia.getDescricao());
				cadastrarCompetencia(competencia, compExp.getEmail());
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		return true;
	}
	
	public Boolean loopPraCadastrarVaga(Vaga vaga) throws IOException {
		int idVaga = cadastrarVaga(vaga);
		vaga.getCompetencias().forEach(competencia -> {
			try {
				cadastrarVagaCompetencia(competencia, idVaga);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		return true;
	}
	
	public Boolean loopPraEditarVaga(Vaga vaga) throws IOException {
		editarVaga();
		vaga.getCompetencias().forEach(competencia -> {
			//editarVagaCompetencia(competencia, idVaga);
		});
		return true;
	}
	
	public void editarVaga() {
		//Connection conn = BancoDados.conectar();
        // VagaDao vagaDao = new VagaDao(conn);
        //vagaDao.editarVagaCompetencia(comp, idVaga);
	}
	
	public void editarVagaCompetencia(String competencia, int idVaga){
		
	}
	
	
	public Boolean loopPraApagarCompetencia(CompetenciaExperiencia compExp) {
		compExp.competencias.forEach(competencia -> {
			try {
				apagarCompetencia(competencia, compExp.getEmail());
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		return true;
	}
	
	public void atualizarCompetencia(Competencia comp, String email) throws IOException {
		try {
            Connection conn = BancoDados.conectar();
            CompetenciaDao compDao = new CompetenciaDao(conn);
            compDao.atualizarCompetencia(comp, email);
            BancoDados.desconectar();

        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void apagarCompetencia(Competencia comp, String email) throws IOException {
		try {
            Connection conn = BancoDados.conectar();
            CompetenciaDao compDao = new CompetenciaDao(conn);
            compDao.apagarCompetencia(email, comp.getDescricao());
            BancoDados.desconectar();

        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void loopPraAtualizarCompetencia(CompetenciaExperiencia compExp) {
		compExp.competencias.forEach(competencia -> {
			try {
				atualizarCompetencia(competencia, compExp.getEmail());
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	
	public void cadastrarCompetencia(Competencia comp, String email) throws IOException{
            try {
                Connection conn = BancoDados.conectar();
                CompetenciaDao compDao = new CompetenciaDao(conn);
                compDao.cadastrarCompetencia(comp, email);
                BancoDados.desconectar();
   
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
	
	public void cadastrarVagaCompetencia(String comp, int idVaga) throws IOException{
        try {
            Connection conn = BancoDados.conectar();
            VagaDao vagaDao = new VagaDao(conn);
            vagaDao.cadastrarVagaCompetencia(comp, idVaga);

        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public int cadastrarVaga(Vaga vaga) throws IOException{
        try {
            Connection conn = BancoDados.conectar();
            VagaDao vagaDao = new VagaDao(conn);
            int idVaga = vagaDao.cadastrarVaga(vaga);
            return idVaga;

        } catch (SQLException e) {
            e.printStackTrace();
        }
		return -1;
	}
	
	
	public Empresa getEmpresaByToken(String token){
		return null;
		
	}
}
