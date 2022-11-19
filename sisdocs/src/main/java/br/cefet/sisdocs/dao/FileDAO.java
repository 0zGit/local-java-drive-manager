package br.cefet.sisdocs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.cefet.sisdocs.model.File;
import br.cefet.sisdocs.model.Folder;

public class FileDAO {

	public void Adicionar(File file, Folder folder) throws SQLException {
		Connection con = (Connection) ConnectionFactory.getConnection();

		String sql1 = "INSERT INTO pasta(path) VALUES (?)";
		PreparedStatement stmt1 = con.prepareStatement(sql1);
		stmt1.setString(1, folder.getPath());

		// handle duplicated entry
		try {
			stmt1.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		stmt1.close();

		String sql2 = "SELECT id FROM pasta";
		PreparedStatement stmt2 = con.prepareStatement(sql2);
		ResultSet rs = stmt2.executeQuery();

		// get result
		int pathid = 0;
		while (rs.next()) {
			pathid = rs.getInt("id");
		}
		stmt2.close();

		String sql3 = "INSERT INTO arquivo(name,pathid) VALUES (?,?)";
		PreparedStatement stmt3 = con.prepareStatement(sql3);
		stmt3.setString(1, file.getName());
		stmt3.setInt(2, pathid);

		stmt3.execute();
		stmt3.close();

		con.close();
	}

	public List<File> listarTodos() throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select name from arquivo";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		File file = null;
		List<File> files = new ArrayList<File>();

		while (rs.next()) {
			file = new File();
			file.setName(rs.getString("name"));
			files.add(file);
		}
		stmt.close();
		con.close();

		return files;
	}

	public boolean Delete(String name) throws SQLException {
		Connection con = (Connection) ConnectionFactory.getConnection();

		Boolean result = true;
		
		String sql = "DELETE FROM arquivo WHERE name = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1,name);

		try {
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}
		
		stmt.close();
		con.close();

		return result;
	}
}
