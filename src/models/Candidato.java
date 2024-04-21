package models;

public class Candidato {
	
	protected String user;
	protected char[] password;
	protected String operacao;
	
	public void setUser(String user) {
		this.user = user;
	}
	public void setPassword(char[] password) {
		this.password = password;
	}
	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	public String getUser() {
		return user;
	}
	public char[] getPassword() {
		return password;
	}
	public String getOperacao() {
		return operacao;
	}

}
