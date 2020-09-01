package bancoDeDados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Tabela {
	public static void criaTabela (String tableName) {
		PreparedStatement stmt = null;
		Connection con = ConnectionFactory.getConnection();
		
		try {
			stmt = con.prepareStatement(
				"CREATE TABLE " + tableName + " " +
				"(codigo INTEGER not NULL, " +
	            " nome VARCHAR(255), " +
	            " peso_medio FLOAT, " +
	            " PRIMARY KEY ( codigo ))");
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Falha ao criar Table " + tableName);
			System.out.println(e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public static void deletaTabela (String tableName) {
		PreparedStatement stmt = null;
		Connection con = ConnectionFactory.getConnection();
		
		try {
			stmt = con.prepareStatement("DROP TABLE " + tableName);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Falha ao droppar Table " + tableName);
			System.out.println(e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public static String getTableName () {
		String tableName = "";
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection con = ConnectionFactory.getConnection();
		
		try {
			stmt = con.prepareStatement("SHOW tables");
			rs = stmt.executeQuery();
			while (rs.next()) {
				tableName = rs.getString(1);
			}
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Falha ao receber Table Name");
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return tableName;
	}
}
