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
	public boolean validarUsuario(String username) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM candidato WHERE nome = '"+username+"'");
			rs = st.executeQuery();
			System.out.println("rs: "+rs);
			if(rs == null) {
				return false;
			}else {				
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
