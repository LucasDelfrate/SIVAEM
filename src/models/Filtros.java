package models;

import java.util.List;

public class Filtros {
	
	private List<CompetenciaExperiencia> competenciaExperiencia; 
	private String tipo;
	public List<CompetenciaExperiencia> getCompetenciaExperiencia() {
		return competenciaExperiencia;
	}
	public void setCompetenciaExperiencia(List<CompetenciaExperiencia> competenciaExperiencia) {
		this.competenciaExperiencia = competenciaExperiencia;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	

}
