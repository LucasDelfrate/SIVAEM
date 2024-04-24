package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Candidato;

public class candidatoDAO {
	private Connection conn;
	public candidatoDAO(Connection conn) {
		this.conn = conn;
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
	public boolean validarUsername(String username) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM candidato WHERE nome = ?");
			st.setString(1, username);
			rs = st.executeQuery();
			if(rs.next()) {
				return false;
			}else {				
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean validarEmail(String email) {
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
		}
	}
	
	public boolean loginCandidato(String email, String senha) {
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
		}
	}
	
	public String getUUID(String email) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM candidato WHERE email = ?");
			st.setString(1, email);
			rs = st.executeQuery();
			if(rs.next()) {
				System.out.println("UUID ENCONTRADO");
				String uuid = rs.getString("UUID");
				return uuid;
			}else {		
				System.out.println("==================================== UUID NÃO ENCONTRADO ========================================");
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}
