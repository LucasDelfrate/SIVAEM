package models;

import java.util.List;

public class Resposta {
	
	private String operacao;
	private int status;
	private String msg;
	private String token;
	private String email;
	private String nome;
	private String senha;
	private String cnpj;
	private String descricao;
	private String ramo;
	private String razaoSocial;
	private List<Vaga> vagas;
	private List<Competencia> competencias;
	private int faixaSalarial;
	private String estado;
	private List<String> competenciasString;
	private String tipo;
	private FiltroCandidato filtros;
	private int idCandidato;
	private int experiencia;
	private String competencia;
	
	
	
	public int getIdCandidato() {
		return idCandidato;
	}

	public void setIdCandidato(int idCandidato) {
		this.idCandidato = idCandidato;
	}

	public int getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}

	public String getCompetencia() {
		return competencia;
	}

	public void setCompetencia(String competencia) {
		this.competencia = competencia;
	}

	public int getSetidCandidato() {
		return idCandidato;
	}

	public void setSetidCandidato(int setidCandidato) {
		this.idCandidato = setidCandidato;
	}

	public FiltroCandidato getFiltros() {
		return filtros;
	}

	public void setFiltros(FiltroCandidato filtros) {
		this.filtros = filtros;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<String> getCompetenciasString() {
		return competenciasString;
	}

	public void setCompetenciasString(List<String> competenciasString) {
		this.competenciasString = competenciasString;
	}

	public int getFaixaSalarial() {
		return faixaSalarial;
	}

	public void setFaixaSalarial(int faixaSalarial) {
		this.faixaSalarial = faixaSalarial;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<Vaga> getVagas() {
		return vagas;
	}

	public void setVagas(List<Vaga> vagas) {
		this.vagas = vagas;
	}

	public List<Competencia> getCompetencias() {
		return competencias;
	}

	public void setCompetencias(List<Competencia> competencias) {
		this.competencias = competencias;
	}

	public Resposta() {
		this.status = 0;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getRamo() {
		return ramo;
	}

	public void setRamo(String ramo) {
		this.ramo = ramo;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getOperacao() {
		return operacao;
	}
	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUser() {
		return nome;
	}
	public void setUser(String user) {
		this.nome = user;
	}

}
