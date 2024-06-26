package models;

import java.util.List;

public class Vaga {
	
	private int id;
	private String operacao;
	private int faixaSalarial;
	private String descricao;
	private String estado;
	private String nome;
	private List<String> competencias;
	private String email;
	private String token;
	private int status;
	
	

	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getOperacao() {
		return operacao;
	}
	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	public int getFaixaSalarial() {
		return faixaSalarial;
	}
	public void setFaixaSalarial(int faixaSalarial) {
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
	public List<String> getCompetencias() {
		return competencias;
	}
	public void setCompetencias(List<String> competencias) {
		this.competencias = competencias;
	}
	
	

}
