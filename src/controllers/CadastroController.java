package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import dao.BancoDados;
import dao.candidatoDAO;
import enums.CadastroEnum;
import models.Candidato;

public class CadastroController {
	
	public CadastroEnum validarCadastro(Candidato candidato) throws IOException {
		Boolean isUsernameValid = validarUsername(candidato.getUser());
		Boolean isEmailValid = validarEmail(candidato.getEmail());
		Boolean isPasswordValid = validarSenha(candidato.getPassword());
		if(isUsernameValid) {
			return CadastroEnum.ERRO_USUARIO;
		}else if(isEmailValid) {
			return CadastroEnum.ERRO_EMAIL;
		}else if(isPasswordValid) {
			return CadastroEnum.ERRO_SENHA;
		}else {
			return CadastroEnum.SUCESSO;
		}
	}
	public boolean validarUsername(String username) throws IOException {
		if(username == null) {
			return false;
		}else {
			try {
    			Connection conn = BancoDados.conectar();
    			Boolean response = new candidatoDAO(conn).validarUsername(username);
    			BancoDados.desconectar();
    			if(response) {
    				return false;
    			}else {
    				return true;
    			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	public Boolean validarEmail(String email) throws IOException {
			if(email == null || email.length()>50 || email.length()<7 || !email.contains("@")) {
				return false;			
			}else {
				try {
					Connection conn = BancoDados.conectar();
					Boolean response = new candidatoDAO(conn).validarEmail(email);
					System.out.println("response: " + response);
					BancoDados.desconectar();
				}catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			return false;
		}
	}
	public Boolean validarSenha(String password) {
			if(password == null) {				
				return false;
			}else {
				for (int i = 0; i < password.length(); i++) {
		            if (!Character.isDigit(password.charAt(i))) {
		                return false; 
		            }
		            return true;
		        }
			}
		return false;
	}
}
