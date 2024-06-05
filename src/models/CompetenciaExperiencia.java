package models;

import java.util.ArrayList;
import java.util.List;

public class CompetenciaExperiencia {
	
	public List<Competencia> competencias;
	private String operacao;
	private String email;
	private String token;
	
	public CompetenciaExperiencia() {
        this.competencias = new ArrayList<>();
    }
	
	public void adicionarCompetencia(Competencia comp){
		this.competencias.add(comp);
	}
	
	public List<Competencia> getCompetencias() {
		return competencias;
	}
	public void setCompetencias(List<Competencia> competencias) {
		this.competencias = competencias;
	}
	public String getOperacao() {
		return operacao;
	}
	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
	
	
}
