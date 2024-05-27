package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import dao.BancoDados;
import dao.EmpresaDao;
import dao.candidatoDAO;
import enums.LoginEnum;
import models.Candidato;
import models.Empresa;

public class LoginEmpresaController {
	public LoginEnum validarLoginEmpresa(Empresa emp) throws IOException {
		Boolean isCorrectLogin = validarDados(emp);
		if(isCorrectLogin) {
			return LoginEnum.SUCESSO;
		}else {
			return LoginEnum.ERRO_USUARIO_E_SENHA;
		}
	}
	public Boolean validarDados(Empresa empresa) throws IOException {
		try {
			Connection conn = BancoDados.conectar();
			String email = empresa.getEmail();
			String password = empresa.getSenha();
			Boolean response = new EmpresaDao(conn).loginEmpresa(email,password);
			BancoDados.desconectar();
			return response;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return false;
}
}
