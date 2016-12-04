package database;

import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Fichier {
	public int id;
	public String nom;
	public String date ;
	public String[] tags ;
	public String path ;
	public int points;
	public int segments;
	public int faces;
	/**
	 * Creer un objet Fichier correspondant a un modele dans la base de donnee
	 * @param id
	 * @param nom
	 * @param date
	 * @param tags
	 * @param path
	 * @param points
	 * @param segments
	 * @param faces
	 */
	public Fichier(int id, String nom,String date ,String tags,String path , int points, int segments, int faces) {
		this.id = id;
		this.nom = nom;
		//this.date = date ;
		this.tags = tags.split(" ") ;
		this.path = path ;
		this.points = points;
		this.segments = segments;
		this.faces = faces;
	}
	/**
	 * Construis un fichier a partir d'un ResultSet
	 * @param rs ResultSet 
	 */
	public Fichier(ResultSet rs){
		try {
			
			id = rs.getInt(1);
			nom = rs.getString(2);
			date = rs.getString(3);
			tags = rs.getString(4).split(" ");
			path = rs.getString(5);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	/**
	 * Affiche le fichier sous forme de chaine de caractere 
	 */
	public String toString() {
		String retour =  id + " , " + nom + "\n , " + path + " , "  ;
				for(int i = 0 ; i < tags.length ; i++){
					retour += tags[i] + " " ;
				}
		return retour ;
	}
	
	/**
	 * Affiche le Fichier sous forme de JPanel
	 * @return un objet JPanel
	 */
	public JPanel panel(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6, 2));
		panel.add(new JLabel("id"));
		panel.add(new JLabel("" + id));
		panel.add(new JLabel("nom"));
		panel.add(new JLabel(nom));
		
				
		return panel ;
	}
	/**
	 * Retourn les mots cle sous forme de chaine de caractere
	 * @return chaine de caractere avec tout les mots cles separe par des espaces
	 */
	public  String getTagsToString(){
		String retour = "" ;
		for(int i = 0 ; i < tags.length ; i++){
			retour += " " + tags[i] ;
		}
		return retour ;
	}

}