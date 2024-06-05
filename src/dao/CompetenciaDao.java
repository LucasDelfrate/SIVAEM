package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import models.Competencia;

public class CompetenciaDao {
	private Connection conn;
	public CompetenciaDao(Connection conn) {
		this.conn = conn;
	}
	public void cadastrarCompetencia(Competencia comp) throws SQLException {
		PreparedStatement st = null;
		System.out.println("Realizando cadastro de competencia no banco");
		try {
			st = conn.prepareStatement("INSERT INTO competencia (descricao, experiencia) values (?,?)");
			st.setString(1, comp.getDescricao());
			st.setString(2, comp.getExperiencia());
			st.executeUpdate();
		}finally {
			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}
}
