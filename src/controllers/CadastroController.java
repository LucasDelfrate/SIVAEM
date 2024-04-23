package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import dao.BancoDados;
import dao.candidatoDAO;
import models.Candidato;

public class CadastroController {
	
	public boolean validarCadastro(Candidato candidato) throws IOException {
		String usuario = candidato.getUser();
		if(usuario == null) {
			System.out.println("O usuario é nulo");
			return false;
		}else {
			try {
    			Connection conn = BancoDados.conectar();
    			Boolean response = new candidatoDAO(conn).validarUsuario(usuario);
    			System.out.println("response: " + response);
    			BancoDados.desconectar();
    			if(response) {
    				return response;
    			}else {
    				return response;
    			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
		
	}
}
