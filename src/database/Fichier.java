package database;

import java.awt.GridLayout;
import java.sql.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Fichier {
	public int id;
	public String nom;
	public String date ;
	public String[] tags ;
	public String path ;
	public int points;
	public int segments;
	public int faces;

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

	


	public String toString() {
		String retour = "[" + id + " , " + nom + "\n , " + " , "  ;
				for(int i = 0 ; i < tags.length ; i++){
					retour += tags[i] + " " ;
				}
		retour+=  points + " , " + segments + " , " + faces + "]";
		return retour ;
	}
	
	public JPanel panel(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6, 2));
		panel.add(new JLabel("id"));
		panel.add(new JLabel("" + id));
		panel.add(new JLabel("nom"));
		panel.add(new JLabel(nom));
		
				
		return panel ;
	}

}