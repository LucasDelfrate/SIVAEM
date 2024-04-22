package models;

import java.util.Arrays;

public class Candidato {
	
	private int id;
	private int token;
	private String user;
	private String password;
	private String email;
	private String operacao;
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getToken() {
		return token;
	}


	public void setToken(int token) {
		this.token = token;
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
		return "Candidato [user=" + user + ", password=" + password + ", operacao=" + operacao + "]";
	}
	
	
}
