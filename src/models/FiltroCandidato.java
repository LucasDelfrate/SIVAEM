package models;

import java.util.List;

public class FiltroCandidato {
	
	private List<Competencia> competenciasExperiencias;
	private String tipo;
	
	
	public List<Competencia> getCompetenciasExperiencias() {
		return competenciasExperiencias;
	}
	public void setCompetenciasExperiencias(List<Competencia> competenciasExperiencias) {
		this.competenciasExperiencias = competenciasExperiencias;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
