package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import models.Candidato;

public class candidatoDAO {
	private Connection conn;
	public candidatoDAO(Connection conn) {
		this.conn = conn;
	}
	public Candidato getCandidatoByEmail(String email) throws SQLException {
		PreparedStatement st = null;
		ResultSet rs = null;
            try {
            	st = conn.prepareStatement("SELECT * FROM candidato WHERE email = ?");
    			st.setString(1, email);
    			rs = st.executeQuery();
    			if(rs.next()) {
    				System.out.println("Candidato encontrado");
    				Candidato candidato = new Candidato();
    				candidato.setEmail(rs.getString("email"));
    				candidato.setPassword(rs.getString("senha"));
    				candidato.setUser(rs.getString("nome"));
    				candidato.setUUID(email);
    				return candidato;
    			}else {		
    				System.out.println("==================================== Candidato não encontrado ========================================");
    				return null;
    			}
    		} catch (SQLException e) {
    			e.printStackTrace();
    			return null;
    		}	finally {
    			BancoDados.finalizarStatement(st);
    			BancoDados.desconectar();
    		}
	}

	
	public Boolean atualizarCandidato(Candidato cand) throws SQLException {
		PreparedStatement st = null;
		try {
			System.out.println("USER CAND: "+ cand.getUser());
			System.out.println("USER EMAIL: "+ cand.getEmail());
			System.out.println("USER PASSWORD: "+ cand.getPassword());
			System.out.println("USER TOKEN: "+ cand.getUUID());
			st = conn.prepareStatement("UPDATE candidato SET nome = ?, senha = ? WHERE email = ?");
			st.setString(1, cand.getUser());
			st.setString(2, cand.getPassword());
			st.setString(3, cand.getEmail());
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
	
	public int deleteUser(String email) throws SQLException {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM candidato WHERE email = ?");
			st.setString(1, email);
			int linhasAfetadas = st.executeUpdate();
			return linhasAfetadas;
		}finally {
			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}
	
	
	public void cadastrarUsuario(Candidato candidato) throws SQLException {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO candidato (uuid, nome, email, senha) values (?,?,?,?)");
			st.setString(1, candidato.getUUID());
			st.setString(2, candidato.getUser());
			st.setString(3, candidato.getEmail());
			st.setString(4, candidato.getPassword());
			st.executeUpdate();
		}finally {
			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}
	
	public boolean validarEmail(String email) throws SQLException {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM candidato WHERE email = ?");
			st.setString(1, email);
			rs = st.executeQuery();
			if(rs.next()) {
				return false;
			}else {				
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}
	
	public boolean loginCandidato(String email, String senha) throws SQLException {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM candidato WHERE email = ? and senha = ?");
			st.setString(1, email);
			st.setString(2, senha);
			rs = st.executeQuery();
			if(rs.next()) {
				System.out.println("A busca retornou resultados");
				return true;
			}else {		
				System.out.println("==================================== LOGIN INCORRETO ========================================");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}
	
	public String getUUID(String email) throws SQLException {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM candidato WHERE email = ?");
			st.setString(1, email);
			rs = st.executeQuery();
			if(rs.next()) {
				System.out.println("UUID ENCONTRADO");
				String uuid = rs.getString("UUID");
				System.out.println("uuid: " + uuid);
				return uuid;
			}else {		
				System.out.println("==================================== UUID NÃO ENCONTRADO ========================================");
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}
	
	public Candidato getCandidato(String email) throws SQLException {
		PreparedStatement st = null;
		ResultSet rs = null;
            try {
            	st = conn.prepareStatement("SELECT * FROM candidato WHERE email = ?");
    			st.setString(1, email);
    			rs = st.executeQuery();
    			if(rs.next()) {
    				System.out.println("Candidato encontrado");
    				Candidato candidato = new Candidato();
    				candidato.setEmail(rs.getString("email"));
    				candidato.setPassword(rs.getString("senha"));
    				candidato.setUser(rs.getString("nome"));
    				candidato.setUUID(rs.getString("UUID"));
    				return candidato;
    			}else {		
    				System.out.println("==================================== Candidato não encontrado ========================================");
    				return null;
    			}
    		} catch (SQLException e) {
    			e.printStackTrace();
    			return null;
    		}	finally {
    			BancoDados.finalizarStatement(st);
    			BancoDados.desconectar();
    		}
	}

}
