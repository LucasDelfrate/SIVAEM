package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import dao.BancoDados;
import dao.EmpresaDao;
import dao.candidatoDAO;
import enums.CadastroEnum;
import enums.CnpjEnum;
import enums.EdicaoEnum;
import enums.EmailEnum;
import models.Candidato;
import models.Empresa;

public class CadastroController {
	
	public CadastroEnum validarCadastro(Candidato candidato) throws IOException {
		Boolean isUsernameValid = validarUsername(candidato.getUser());
		EmailEnum isEmailValid = validarEmail(candidato.getEmail());
		Boolean isPasswordValid = validarSenha(candidato.getPassword());
		System.out.println("USER: "+isUsernameValid +" Email: "+ isEmailValid + " Password: "+ isPasswordValid);
		if(!isUsernameValid) {
			return CadastroEnum.ERRO;
		}else if(isEmailValid == EmailEnum.JA_CADASTRADO) {
			return CadastroEnum.EMAIL_CADASTRADO;
		}else if(isEmailValid == EmailEnum.CARACTERES_INVALIDOS) {
			return CadastroEnum.ERRO;
		}else if(!isPasswordValid) {
			return CadastroEnum.ERRO;
		}else {
			return CadastroEnum.SUCESSO;
		}
	}
	public CadastroEnum validarCadastroEmpresa(Empresa empresa) throws IOException {
		EmailEnum isEmailValid = validarEmail(empresa.getEmail());
		Boolean isPasswordValid = validarSenha(empresa.getSenha());
		CnpjEnum isCnpjValid = validarCnpj(empresa.getCnpj());
		
		
		System.out.println("Email:  |"+ isEmailValid + " Password:  |"+ isPasswordValid+ " cnpj:  |"+ isCnpjValid);
		if(isEmailValid == EmailEnum.JA_CADASTRADO) {
			return CadastroEnum.EMAIL_CADASTRADO;
		}else if(isEmailValid == EmailEnum.CARACTERES_INVALIDOS) {
			return CadastroEnum.ERRO;
		}else if(!isPasswordValid) {
			return CadastroEnum.ERRO;
		}else if(isCnpjValid == CnpjEnum.ERRO) {
			return CadastroEnum.ERRO;
		}else if(isCnpjValid == CnpjEnum.JA_CADASTRADO) {
			return CadastroEnum.CNPJ_CADASTRADO;
		}else {
			return CadastroEnum.SUCESSO;
		}
	}
	
	public boolean validarUsername(String username) throws IOException {
		System.out.println("Usuario: "+ username);
		if(username == null || username.length() == 0 || username.length()<6 || username.length()>30) {
			return false;
		}else return true;
	}
	
	public EmailEnum validarEmail(String email) throws IOException {
			if(email == null || email.length()>50 || email.length()<7 || !email.contains("@") || email.length()==0) {
				return EmailEnum.CARACTERES_INVALIDOS;		 	
			}else {
				try {
					Connection conn = BancoDados.conectar();
					Boolean response = new candidatoDAO(conn).validarEmail(email);
					BancoDados.desconectar();
					if(response == true) {
						return EmailEnum.SUCESSO;
					}else return EmailEnum.JA_CADASTRADO;
				}catch (SQLException e) {
					e.printStackTrace();
					return EmailEnum.ERRO;
				}
		}
	}
	
	public Boolean validarSenha(String password) {
			if(password == null || password.length()==0 || password.length() > 8) {				
				return false;
			}else {				
				return true;
			}
	}
	public CnpjEnum validarCnpj(String cnpj) throws IOException {
	    if (cnpj == null) {
	        return CnpjEnum.ERRO;
	    }
	    if (cnpj.length() != 14) {
	        return CnpjEnum.ERRO;
	    }
	    if (!cnpj.matches("\\d+")) {
	        return CnpjEnum.ERRO;
	    }else {
	    	try {
				Connection conn = BancoDados.conectar();
				Boolean response = new EmpresaDao(conn).validarCnpj(cnpj);
				BancoDados.desconectar();
				if(response == true) {
					return CnpjEnum.SUCESSO;
				}else return CnpjEnum.JA_CADASTRADO;
			}catch (SQLException e) {
				e.printStackTrace();
				return CnpjEnum.ERRO;
			}
	    }
	}
	public CadastroEnum validarEdicao(Empresa emp) throws IOException {
		Boolean isPasswordValid = validarSenha(emp.getSenha());
		CnpjEnum isCnpjValid = validarCnpj(emp.getCnpj());
		
	
		 if(!isPasswordValid) {
			return CadastroEnum.ERRO;
		}else if(isCnpjValid == CnpjEnum.ERRO) {
			return CadastroEnum.ERRO;
		}else if(isCnpjValid == CnpjEnum.JA_CADASTRADO) {
			return CadastroEnum.CNPJ_CADASTRADO;
		}else {
			return CadastroEnum.SUCESSO;
		}
	}
}
