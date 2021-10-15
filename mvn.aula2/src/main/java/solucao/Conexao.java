package solucao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

	
		//com.mysql.jdbc.driver  jdbc:mysql://SERVIDOR:PORTA/BANCO_DADOS
	
		private final String SERVIDOR = "127.0.0.1";
		private final String PORTA = "3306";
		private final String BANCO_DADOS = "PodutoJava";
		private final String URL = "jdbc:mysql://" + SERVIDOR + ":" + PORTA + "/" + BANCO_DADOS;
		
		private final String USUARIO = "root";
		private final String SENHA = "";
		
		private Connection conexao;
		
		public Conexao() throws Exception{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
			conexao.setAutoCommit(true);
			
			
		}
		
		public Connection getConexao() {
			return conexao;
		}
}
