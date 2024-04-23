package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoBanco {
	
	private static Connection conexao;
	
	private static void conectarBanco(){
		 try {
	            Class.forName("org.sqlite.JDBC");
	            conexao = DriverManager.getConnection("jdbc:sqlite:banco.sqlite");
	            Statement statement = conexao.createStatement();
	           
        }catch (ClassNotFoundException e) {
            System.out.println("Classe JDBC do SQLite não encontrada.");
        }catch (SQLException e) {
            System.out.println("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
	}
	
	private static void desconectarBanco(){
		if(conexao==null) {
			return;
		}
		try {
			if(!conexao.isClosed()) {
				conexao.close();
			}
		}catch(SQLException e) {
			System.out.println("Não foi possível fechar a conexão com o banco");
		}
	}
	public static Connection getConexao() {
	
			 if(conexao==null) {
				 {
					 System.out.println("Conexão ainda não iniciada");	
					 return null;
				 }
			 }
 
	        return conexao;
	 }
	public static void limparUsuarios() throws SQLException {
		conexao = getConexao();
		Statement stm = conexao.createStatement();
		stm.executeUpdate("DROP TABLE IF EXISTS usuario");
	}
	public static void iniciarBD() {
		try {
			conexao = getConexao();
			Statement stm = conexao.createStatement();
			stm.executeUpdate("DROP TABLE IF EXISTS usuario");
			stm.executeUpdate("CREATE TABLE usuario (" + " id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
					+ "nome TEXT NOT NULL," + "senha TEXT NOT NULL," + "email TEXT NOT NULL)");
			
			stm.executeUpdate("INSERT INTO usuario VALUES (null, 'admin', 'admin','admin@email.com')");
			System.out.println("Banco iniciado com sucesso!");
		}catch(SQLException e) {
			 System.err.println("Não foi possível abrir a conexão com o banco");
		 }
	}
	
	public static void alterarBD(String query) throws SQLException {
		Connection bd = ConexaoBanco.getConexao();
		Statement stm = bd.createStatement();
		stm.executeUpdate(query);
		stm.close();
	}
	
	
}

