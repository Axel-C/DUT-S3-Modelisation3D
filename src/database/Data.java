package database;

import java.sql.*;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Data {
	static Connection c = null;
	static Statement stmt = null;
	static PreparedStatement ps = null ;

	
	/**
	 * Ouvre la connection avec la base de donnee
	 * @return
	 */
	public static boolean ouverture() {
		// OUVERTURE BASE DE DONNEE
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:data/modeles.db");
			System.out.println("Ouverture de la base reussie");
		} catch (SQLException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Base de donnee introuvable");
			return false;
		}
		return true;

	}
	/**
	 * Ferme la connection avec la base de donnee
	 * @throws SQLException
	 */
	public static void fermeture() {

		try {
			// stmt.close();
			c.close();
			System.out.println("Fermeture de la base");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * Recupere les fichiers contenu dans la base de donnee
	 * @return	Liste des fichiers
	 */
	public static ArrayList<Fichier> list() {
		ArrayList<Fichier> fichiers = new ArrayList<>();
		ouverture();
		try {

			// REQUETE

			stmt = c.createStatement();
			String querry = "SELECT * FROM Files ;";
			ResultSet rs = stmt.executeQuery(querry);

			// STOCKAGE

			while (rs.next()) {
				fichiers.add(new Fichier(rs.getInt(1), rs.getString(2), 
						rs.getString(3), rs.getString(4), rs.getString(5), 
						rs.getInt(6), rs.getInt(7), rs.getInt(8)));
			}
			

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		// RETOUR

		System.out.println("Donne recuperee");
		fermeture(); 
		return fichiers;

	}
	/**
	 * Supprime un fichier de la base donnee 
	 * @param id du fichier a supprimer 
	 * @return	reussite ou non de l'operation 
	 */
	public static boolean delete(int id) {
		try {
			stmt = c.createStatement();
			String querry = "DELETE FROM Files WHERE id = '" + id + "'";
			stmt.executeUpdate(querry);
			return true ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		
	}
	
	/**
	 * Ajoute un modele à la base de donnee
	 * @param nom
	 * @param tags
	 * @param path
	 * @return
	 */
	public static boolean add(String nom , String tags , String path){
		try {
			ouverture();
			stmt = c.createStatement();
			String querry = "INSERT INTO Files(nom , tags , path) VALUES('"+ nom +"','"+tags+"','"+path+"') ";
			System.out.println(querry);
			stmt.executeUpdate(querry);
			fermeture();
			return true ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Supprime un modele de la base de donnee
	 * @param name Nom du modele a supprimer
	 * @return
	 */
	public static boolean delete(String name) {
		try {
			ouverture();
			stmt = c.createStatement();
			String querry = "DELETE FROM Files WHERE nom = '" + name + "'";
			int r = stmt.executeUpdate(querry);
			fermeture();
			return (r == 1);
		} catch (SQLException e) {
			e.printStackTrace();
			return false ;
		}
		
		
	}
	/**
	 * Renvoi un objet Fichier du modele de la base de donnee dont le nom est passe en parametre
	 * @param nom Nom du modele
	 * @return
	 */
	public static Fichier find(String nom){
		Fichier retour = null ;
		try {
			ouverture();
			ps = c.prepareStatement("SELECT * FROM Files WHERE nom = ?");
			ps.setString(1, nom);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
			retour = new Fichier(rs);
			}
			fermeture();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retour ;
		
	}
	/**
	 * Modifie un Fichier dans la base de donnee
	 * @param nom Ancien nom du fichier 
	 * @param nouveauNom Nouveau nom du fichier
	 * @param tags Mots clés coresspondants au fichier
	 * @param path Chemin d'acces au fichier
	 * @return True si oprération reussi , false sinon
	 */
	public static boolean update(String nom ,String nouveauNom , String tags , String path){
		try {
			ouverture();
			stmt = c.createStatement();
			String querry = "UPDATE Files";
			querry += " SET nom = '" + nouveauNom + "'  ,  tags = '" + tags +"' , path = '" + path + "' " ;
			querry += " WHERE nom = '" + nom  + "'";
			System.out.println(querry);
			stmt.executeUpdate(querry);
			fermeture();
			return true ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	

}