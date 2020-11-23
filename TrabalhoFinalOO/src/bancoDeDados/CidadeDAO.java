package bancoDeDados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import dados.Contaminado;
import dados.Saudavel;

public class CidadeDAO {
	public static void setContaminado (Contaminado contaminado) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("INSERT INTO " + "cleber" + " (codigo, nome, genero, estado_saudo)VALUES(?,?,?,?)");
			stmt.setInt(1, contaminado.getIdentificacao());
			stmt.setString(2, contaminado.getNomeCompleto());
			stmt.setString(3, contaminado.getGenero());
			stmt.setString(4, contaminado.getEstadoSaude());
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Falha ao registrar planta no BD!");
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public static void setSaudavel (Saudavel saudavel) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("INSERT INTO " + "cleber" + " (codigo, nome, genero, idade)VALUES(?,?,?,?)");
			stmt.setInt(1, saudavel.getIdentificacao());
			stmt.setString(2, saudavel.getNomeCompleto());
			stmt.setString(3, saudavel.getGenero());
			stmt.setInt(4, saudavel.getIdadeAnos());
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Falha ao registrar planta no BD!");
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
}
