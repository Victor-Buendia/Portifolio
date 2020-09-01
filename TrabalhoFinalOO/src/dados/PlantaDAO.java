package dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bancoDeDados.ConnectionFactory;

public class PlantaDAO {
	public static void setBancoPlantas (Planta planta, String tableName) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("INSERT INTO " + tableName + " (codigo, nome, peso_medio)VALUES(?,?,?)");
			stmt.setInt(1, planta.getCodigo());
			stmt.setString(2, planta.getNome());
			stmt.setFloat(3, planta.getPesoMedio());
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Falha ao registrar planta no BD!");
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public static List<Planta> getBancoPlantas (String tableName) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Planta> bancoPlantas = new ArrayList<Planta>();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM " + tableName);
			rs = stmt.executeQuery();
			while (rs.next()) {
				bancoPlantas.add(new Planta(
					rs.getString("nome"), 
					rs.getInt("codigo"), 
					rs.getFloat("peso_medio")
				));
			}
		} catch (SQLException e) {
			System.out.println("Falha ao receber planta do banco de dados!");
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return bancoPlantas;
	}
	
}
