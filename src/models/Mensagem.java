package models;

public class Mensagem {
	
	private String operacao;
	private String email;
	private int[] candidatos;
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
	public int[] getCandidatos() {
		return candidatos;
	}
	public void setCandidatos(int[] candidatos) {
		this.candidatos = candidatos;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
}
