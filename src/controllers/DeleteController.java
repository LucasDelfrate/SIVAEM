package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import dao.BancoDados;
import dao.EmpresaDao;
import dao.candidatoDAO;
import enums.EmailEnum;

public class DeleteController {
	
	public EmailEnum validarEmailToDelete(String email) throws IOException{
		try {
			Connection conn = BancoDados.conectar();
			int response = new candidatoDAO(conn).deleteUser(email);
			BancoDados.desconectar();
			if(response == 1) {
				return EmailEnum.SUCESSO;
			}else return EmailEnum.NAO_ENCONTRADO;
		}catch (SQLException e) {
			e.printStackTrace();
			return EmailEnum.ERRO;
		}
	}
	
	public EmailEnum validarEmailToDeleteEmpresa(String email) throws IOException{
		try {
			Connection conn = BancoDados.conectar();
			int response = new EmpresaDao(conn).deleteEmpresa(email);
			System.out.println(response);
			BancoDados.desconectar();
			if(response == 1) {
				return EmailEnum.SUCESSO;
			}else return EmailEnum.NAO_ENCONTRADO;
		}catch (SQLException e) {
			e.printStackTrace();
			return EmailEnum.ERRO;
		}
	}

}
