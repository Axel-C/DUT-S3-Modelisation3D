package database;

import java.sql.*;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Data {
	static Connection c = null;
	static Statement stmt = null;

	/**
	 * Renvoi le contenu de la table "Files" de la base de donnée
	 * 
	 * @return une ArrayList avec le contenu de la table
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */

	public static boolean ouverture() {
		// OUVERTURE BASE DE DONNEE
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			System.out.println("Ouverture de la base reussie");
		} catch (SQLException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Base de donnée introuvable");
			return false;
		}
		return true;

	}

	public static void fermeture() throws SQLException {

		stmt.close();
		c.close();
	}

	public static ArrayList<Fichier> getData() {
		ArrayList<Fichier> fichiers = new ArrayList<>();
		try {

			// REQUETE

			stmt = c.createStatement();
			String querry = "SELECT * FROM Files ;";
			ResultSet rs = stmt.executeQuery(querry);

			// STOCKAGE

			while (rs.next()) {
				fichiers.add(new Fichier(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
						rs.getInt(6)));
			}

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		// RETOUR

		System.out.println("Donne recuperee");
		return fichiers;

	}

	public static boolean deleteData(int id) {
		try {
			stmt = c.createStatement();
			String querry = "DELETE FROM Files WHERE id = '" + id + "'";
			stmt.executeUpdate(querry);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

}