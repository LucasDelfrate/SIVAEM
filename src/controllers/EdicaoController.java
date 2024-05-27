package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import dao.BancoDados;
import dao.candidatoDAO;
import enums.EdicaoEnum;
import models.Candidato;

public class EdicaoController {
	public EdicaoEnum validarEdicao(Candidato cand) throws IOException {
		Boolean isUsernameValid = validarUsername(cand.getUser());
		if(isUsernameValid == true) {
			String email = cand.getEmail();
			if(email == null || email.length()>50 || email.length()<7 || !email.contains("@") || email.length()==0) {
				System.out.println("erro email - tela cadastro controller");
				return EdicaoEnum.ERRO;		
			}else {
				Boolean res = validarSenha(cand.getPassword());
				if(res == false) {
					System.out.println("erro senha - tela cadastro controller");
					return EdicaoEnum.ERRO;	
				}else {
					try {
						Connection conn = BancoDados.conectar();
						Candidato response = new candidatoDAO(conn).getCandidato(email);
						BancoDados.desconectar();
						if(response == null) {
							System.out.println("erro get candidato - tela cadastro controller");
							return EdicaoEnum.ERRO;
						}else {
							return EdicaoEnum.SUCESSO;
						}
					}catch (SQLException e) {
						e.printStackTrace();
						return EdicaoEnum.ERRO;
					}
				}
			}
		}else {
			System.out.println("erro user - tela cadastro controller");
			return EdicaoEnum.ERRO;			
		}
	}
	
	public boolean validarUsername(String username) throws IOException {
		System.out.println("Usuario: "+ username);
		if(username == null || username.length() == 0 || username.length()<6 || username.length()>30) {
			return false;
		}else return true;
	}
	public Boolean validarSenha(String password) {
		if(password == null || password.length()==0 || password.length() > 8) {				
			return false;
		}else {
			return true;
		}
}
}
