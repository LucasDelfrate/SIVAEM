package models;

import java.util.List;

public class Filtro {
	
	private String operacao;
	private String token;
	private FiltroDentro filtros;
	public String getOperacao() {
		return operacao;
	}
	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public FiltroDentro getFiltros() {
		return filtros;
	}
	public void setFiltros(FiltroDentro filtros) {
		this.filtros = filtros;
	}
	
	
	

}
