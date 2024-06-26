package models;

import java.util.List;

public class Mensagem {
	
	private String operacao;
	private String email;
	private List<Integer> candidatos;
	private String token;
	
	
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
	public List<Integer> getCandidatos() {
		return candidatos;
	}
	public void setCandidatos(List<Integer> checkboxes) {
		this.candidatos = checkboxes;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
}
