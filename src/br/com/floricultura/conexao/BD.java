package br.com.floricultura.conexao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class BD {
	
	
	private static Connection conexao = null; 
	
	
	//método para fechar a conexão com o banco
	public static void fechandoConexao() {
		
		if(conexao != null) {
			try {
			
			conexao.close();
			
			}catch(SQLException e) {
				throw new BdExcecoes(e.getMessage());
			}
		}
	}
	
	//método para conectar com o bando de dados 
	public static Connection getConectando() {
		
		if(conexao == null) {
			try {
				
			//pegando as propriedades do banco de dados
			Properties props = carregarPropriedades(); 
			String url = props.getProperty("url");
			//Gerando uma conexão com o banco 
			conexao = DriverManager.getConnection(url, props);
			
			}catch(SQLException e) {
				throw new BdExcecoes(e.getMessage());
			}
			
		}
		return conexao; 
	}

	
	//metodo para pegar as propriedades do banco de dados la no arquivo bd.propriedades
	private static Properties carregarPropriedades() {
		try(FileInputStream fs = new FileInputStream("bd.propriedades")) {
			
			Properties propriedades = new Properties();
			//fazendo a leitura do arquivo bd.propriedades e guandando dentro do objeto "propriedades"
			propriedades.load(fs);
			
			return propriedades; 
			
		}catch(IOException e) {
			throw new BdExcecoes(e.getMessage());
		}
	}
	
	public static void fecharStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new BdExcecoes(e.getMessage());
			}
		}
	}

	public static void fecharResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new BdExcecoes(e.getMessage());
			}
		}
	}
	
	
	
}
