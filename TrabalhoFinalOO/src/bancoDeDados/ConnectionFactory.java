package bancoDeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {
	public static Connection getConnection () {
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/banco_estados?useTimezone=true&serverTimezone=UTC", 
				"root", 
				"toor"
			);
		} catch (SQLException e) {
			System.out.println("Sem Conexao!\n" + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Falha na Conexao!\n" + e.getMessage());
			e.printStackTrace(); 
		}
		return con;
	}
	
	public static void closeConnection (Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			System.out.println("Falha ao fechar a Conexao!\n" + e.getMessage());
		}
	}
	
	public static void closeConnection (Connection con, PreparedStatement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
				closeConnection(con);
			}
		} catch (SQLException e) {
			System.out.println("Falha ao fechar o PreparedStatement!\n" + e.getMessage());
		}
	}
	
	public static void closeConnection (Connection con, PreparedStatement stmt, ResultSet rs) {	
		try {
			if (rs != null) {
				stmt.close();
				closeConnection(con);
			}
		} catch (SQLException e) {
			System.out.println("Falha ao fechar o ResultSet!\n" + e.getMessage());
		}
	}
}
