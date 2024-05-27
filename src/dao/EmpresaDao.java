package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Candidato;
import models.Empresa;

public class EmpresaDao {
	private Connection conn;
	public EmpresaDao(Connection conn) {
		this.conn = conn;
	}
	
	public boolean validarCnpj(String cnpj) throws SQLException {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM empresa WHERE cnpj = ?");
			st.setString(1, cnpj);
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
	public void cadastrarEmpresa(Empresa emp) throws SQLException {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO empresa (uuid, cnpj, email, razaoSocial, ramo, descricao, senha) values (?,?,?,?,?,?,?)");
			st.setString(1, emp.getUUID());
			st.setString(2, emp.getCnpj());
			st.setString(3, emp.getEmail());
			st.setString(4, emp.getRazaoSocial());
			st.setString(5, emp.getRamo());
			st.setString(6, emp.getDescricao());
			st.setString(7, emp.getSenha());
			st.executeUpdate();
		}finally {
			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}
	public boolean loginEmpresa(String email, String senha) throws SQLException {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM empresa WHERE email = ? and senha = ?");
			st.setString(1, email);
			st.setString(2, senha);
			rs = st.executeQuery();
			if(rs.next()) {
				System.out.println("=== A busca retornou resultados ===");
				return true;
			}else {		
				System.out.println("=== Login Incorreto ===");
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
	public Empresa getEmpresaByEmail(String email) throws SQLException {
		PreparedStatement st = null;
		ResultSet rs = null;
            try {
            	st = conn.prepareStatement("SELECT * FROM empresa WHERE email = ?");
    			st.setString(1, email);
    			rs = st.executeQuery();
    			if(rs.next()) {
    				System.out.println("Candidato encontrado");
    				Empresa empresa = new Empresa();
    				empresa.setEmail(rs.getString("email"));
    				empresa.setCnpj(rs.getString("cnpj"));
    				empresa.setDescricao(rs.getString("descricao"));
    				empresa.setRamo(rs.getString("ramo"));
    				empresa.setRazaoSocial(rs.getString("razaoSocial"));
    				empresa.setSenha(rs.getString("senha"));
    				
    				return empresa;
    			}else {		
    				System.out.println("==================================== Empresa não encontrado ========================================");
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
	
	public int deleteEmpresa(String email) throws SQLException {
		System.out.println("====== " + email);
		PreparedStatement st = null;
		try {
			System.out.println("EMAIL ANTES DO DELETE:" + email);
			st = conn.prepareStatement("DELETE FROM empresa WHERE email = ?");
			st.setString(1, email);
			int linhasAfetadas = st.executeUpdate();
			return linhasAfetadas;
		}finally {
			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}
	public Boolean atualizarEmpresa(Empresa emp) throws SQLException {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE empresa SET razaoSocial = ?, senha = ?, ramo = ?, descricao = ?, cnpj = ? WHERE email = ?");
			st.setString(1, emp.getRazaoSocial());
			st.setString(2, emp.getSenha());
			st.setString(3, emp.getRamo());
			st.setString(4, emp.getDescricao());
			st.setString(5, emp.getCnpj());
			st.setString(6, emp.getEmail());
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
	
}
