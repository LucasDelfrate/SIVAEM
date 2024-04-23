package models;

import java.util.Arrays;

public class Candidato {
	
	private String UUID;
	private String user;
	private String password;
	private String email;
	private String operacao;
	public String getUUID() {
		return UUID;
	}
	public void setUUID(String uUID) {
		UUID = uUID;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOperacao() {
		return operacao;
	}
	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	@Override
	public String toString() {
		return "Candidato [UUID=" + UUID + ", user=" + user + ", password=" + password + ", email=" + email
				+ ", operacao=" + operacao + "]";
	}
	
	


	
}
