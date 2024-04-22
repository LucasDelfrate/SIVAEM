package dao;

import java.sql.SQLException;

import models.Candidato;
import models.ConexaoBanco;

public class UsuarioDAO {
	public void adicionarUsuarioCandidato(Candidato candidato) throws SQLException {
		String query = "INSERT INTO usuario VALUES (null, '"+candidato.getUser()+"', '"
				+candidato.getPassword()+"', '"+candidato.getEmail()+"')";
		ConexaoBanco.alterarBD(query);
	}
	public void removerUsuarioCandidato(Candidato candidato) throws SQLException {
		String query = "DELETE FROM usuario WHERE id = " + candidato.getId();
	}
}
