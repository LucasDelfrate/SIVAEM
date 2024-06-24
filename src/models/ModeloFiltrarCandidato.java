package models;

public class ModeloFiltrarCandidato {
	
	private String operacao;
	private FiltroCandidato filtros;
	private String token;
	
	
	public String getOperacao() {
		return operacao;
	}
	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	public FiltroCandidato getFiltros() {
		return filtros;
	}
	public void setFiltros(FiltroCandidato filtros) {
		this.filtros = filtros;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	

}
