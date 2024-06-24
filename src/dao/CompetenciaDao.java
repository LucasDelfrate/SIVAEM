package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Candidato;
import models.Competencia;

public class CompetenciaDao {
	private Connection conn;
	public CompetenciaDao(Connection conn) {
		this.conn = conn;
	}
	public void cadastrarCompetencia(Competencia comp, String email) throws SQLException {
		PreparedStatement st = null;
		System.out.println("Realizando cadastro de competencia no banco");
		try {
			st = conn.prepareStatement("INSERT INTO competencia (descricao, experiencia, email) values (?,?,?)");
			st.setString(1, comp.getDescricao());
			st.setInt(2, comp.getExperiencia());
			st.setString(3, email);
			st.executeUpdate();
		}finally {
			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}
	
	public int apagarCompetencia(String email, String competencia) throws SQLException {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM competencia WHERE email = ? AND descricao = ?");
			st.setString(1, email);
			st.setString(2, competencia);
			int linhasAfetadas = st.executeUpdate();
			return linhasAfetadas;
		}finally {
			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}
	
	public Boolean atualizarCompetencia(Competencia comp, String email) throws SQLException {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE competencia SET experiencia = ? WHERE email = ? AND descricao = ?");
			st.setInt(1, comp.getExperiencia());
			st.setString(2, email);
			st.setString(3, comp.getDescricao());
			st.executeUpdate();
			return true;
		}catch(SQLException e){
			System.out.println(e);
			return false;
		}finally {
			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}
	
	public List<Competencia> getCompetencias(String email) {
		System.out.println("realizando o get das competencias");
	    List<Competencia> competencias = new ArrayList<>();
	    PreparedStatement st = null;
	    ResultSet rs = null;
	    try {
	        st = conn.prepareStatement("SELECT * FROM competencia WHERE email = ?");
	        st.setString(1, email);
	        rs = st.executeQuery();
	        while (rs.next()) {
	            System.out.println("=== competencias encontradas ===");
	            Competencia comp = new Competencia();
	            comp.setDescricao(rs.getString("descricao"));
	            comp.setExperiencia(rs.getInt("experiencia")); 
	            competencias.add(comp);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    } finally {
	        try {
	            if (rs != null) {
	                rs.close();
	            }
	            if (st != null) {
	                st.close();
	            }
	            BancoDados.desconectar();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return competencias;
	}
	
}
