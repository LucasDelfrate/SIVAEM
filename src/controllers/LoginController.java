package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import dao.BancoDados;
import dao.EmpresaDao;
import dao.candidatoDAO;
import enums.CadastroEnum;
import enums.LoginEnum;
import models.Candidato;

public class LoginController {
	
	public LoginEnum validarLogin(Candidato candidato) throws IOException {
		Boolean isCorrectLogin = validarDados(candidato);
		if(isCorrectLogin) {
			return LoginEnum.SUCESSO;
		}else {
			return LoginEnum.ERRO_USUARIO_E_SENHA;
		}
	}
	
	public Boolean validarDados(Candidato candidato) throws IOException {
			try {
    			Connection conn = BancoDados.conectar();
    			String email = candidato.getEmail();
    			String password = candidato.getPassword();
    			Boolean response = new candidatoDAO(conn).loginCandidato(email,password);
    			BancoDados.desconectar();
    			return response;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return false;
	}
	public String getUUID(String email) throws SQLException, IOException{
		Connection conn = BancoDados.conectar();
		String uuid = new candidatoDAO(conn).getUUID(email);
		if(uuid == null) {
			System.out.println("UUID NULO");
		}
		BancoDados.desconectar();
		
		return uuid;
	}
	public String getUUIDEmpresa(String email) throws SQLException, IOException{
		Connection conn = BancoDados.conectar();
		String uuid = new EmpresaDao(conn).getUUID(email);
		if(uuid == null) {
			System.out.println("UUID NULO");
		}
		BancoDados.desconectar();
		
		return uuid;
	}
}
