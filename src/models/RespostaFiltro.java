package models;

import java.util.List;

public class RespostaFiltro {
	
	private String operacao;
	private List<CandidatosResposta> candidatos;
	private int status;
	public String getOperacao() {
		return operacao;
	}
	public void setOperacao(String operacao) {
		this.operacao = operacao;

	
	
	

}
	public List<CandidatosResposta> getCandidatos() {
		return candidatos;
	}
	public void setCandidatos(List<CandidatosResposta> candidatos) {
		this.candidatos = candidatos;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}