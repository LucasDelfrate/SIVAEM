package models;

public class Vaga {
	
	private String operacao;
	private String faixaSalarial;
	private String descricao;
	private String estado;
	private String[] competencias;
	public String getOperacao() {
		return operacao;
	}
	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	public String getFaixaSalarial() {
		return faixaSalarial;
	}
	public void setFaixaSalarial(String faixaSalarial) {
		this.faixaSalarial = faixaSalarial;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String[] getCompetencias() {
		return competencias;
	}
	public void setCompetencias(String[] competencias) {
		this.competencias = competencias;
	}
	
	

}
