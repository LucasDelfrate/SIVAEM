package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Candidato;
import models.Competencia;
import models.Vaga;

public class VagaDao {
	private Connection conn;
	public VagaDao(Connection conn) {
		this.conn = conn;
	}
	public int cadastrarVaga(Vaga vaga) throws SQLException {
	    PreparedStatement st = null;
	    ResultSet rs = null;
	    int vagaId = -1; 
	    System.out.println("Faixa salarial: "+vaga.getFaixaSalarial());
	    try {
	        st = conn.prepareStatement("INSERT INTO vaga (nome, faixaSalarial, descricao, estado, email) values (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
	        st.setString(1, vaga.getNome());
	        st.setString(2, vaga.getFaixaSalarial());
	        st.setString(3, vaga.getDescricao());
	        st.setString(4, vaga.getEstado());
	        st.setString(5, vaga.getEmail());

	        st.executeUpdate();

	        rs = st.getGeneratedKeys();
	        if (rs.next()) {
	            vagaId = rs.getInt(1); 
	        }
	    } finally {
	        BancoDados.finalizarStatement(st);
	        BancoDados.finalizarResultSet(rs);
	       
	    }

	    return vagaId; 
	}
	
	public void cadastrarVagaRelacionamento(int idVaga, int idCompetencia) throws SQLException {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO vagacompetencia (vaga_id, competencia_id) values (?,?)");
			st.setInt(1, idVaga);
			st.setInt(2, idCompetencia);
			st.executeUpdate();
		}finally {
			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}

	
	public int cadastrarVagaCompetencia(Competencia comp, int idVaga) throws SQLException {
	    PreparedStatement st = null;
	    ResultSet rs = null;
	    int generatedId = -1; 

	    try {
	        st = conn.prepareStatement("INSERT INTO competenciaparavaga (nome) values (?)", Statement.RETURN_GENERATED_KEYS);
	        st.setString(1, comp.getDescricao());

	        st.executeUpdate();

	        rs = st.getGeneratedKeys();
	        if (rs.next()) {
	            generatedId = rs.getInt(1); 
	        }
	    } finally {
	    	cadastrarVagaRelacionamento(idVaga, generatedId); 
	    }

	    return generatedId;
	}

	
	public int apagarVaga(String email, String competencia) throws SQLException {
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
	
	public Boolean atualizarVaga(Competencia comp, String email) throws SQLException {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE competencia SET experiencia = ? WHERE email = ? AND descricao = ?");
			st.setString(1, comp.getExperiencia());
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
	
	public List<Vaga> listarVagas(Vaga vaga) {
		System.out.println("realizando o get das competencias");
	    List<Vaga> vagas = new ArrayList<>();
	    PreparedStatement st = null;
	    ResultSet rs = null;
	    try {
	        st = conn.prepareStatement("SELECT * FROM vaga WHERE email = ?");
	        st.setString(1, vaga.getEmail());
	        rs = st.executeQuery();
	        while (rs.next()) {
	            System.out.println("=== Vaga encontrada ===");
	            Vaga vaga1 = new Vaga();
	            vaga1.setId(rs.getInt("id"));
	            vaga1.setNome(rs.getString("nome"));
	            vagas.add(vaga1);
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
	    return vagas;
	}
	
}
