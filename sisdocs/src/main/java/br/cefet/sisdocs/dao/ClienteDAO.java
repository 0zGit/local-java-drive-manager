package br.cefet.sisdocs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.cefet.sisdocs.model.Cliente;

public class ClienteDAO {
	public void adicionar(Cliente cliente) throws SQLException {
		//Faz a conexao
		Connection con = ConnectionFactory.getConnection();
		
		//Monta a string sql
		String sql = "insert cliente(nome,login, senha) "
				+ " values (?,?,?)";
		
		//Prepara a injeção do valor 
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, cliente.getNome());
		stmt.setString(2, cliente.getLogin());
		stmt.setString(3, cliente.getSenha());
		
		// Executa o sql
		stmt.execute();
		
		// Fecha as conexões
		stmt.close(); 
		con.close();
		
	}


	public Cliente logar(String login, String senha) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select id, nome, login, senha from cliente "
				+ " where login = ? and senha = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, login);
		stmt.setString(2, senha);
		
		ResultSet rs = stmt.executeQuery();
		
		Cliente c = null;
		
		if(rs.next()) {
			c = new Cliente();
			c.setId(rs.getInt("id"));
			c.setNome(rs.getString("nome"));
			c.setLogin(rs.getString("login"));
			c.setSenha(rs.getString("senha"));
		}
		
		stmt.close();
		con.close();
		return c;
	}
}
