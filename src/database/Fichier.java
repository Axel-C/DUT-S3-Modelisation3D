package database;

import java.sql.Date;

@SuppressWarnings("serial")
public class Fichier {
	public int id;
	public String nom;
	public Date date ;
	public String tags ;
	public String path ;
	public int points;
	public int segments;
	public int faces;

	public Fichier(int id, String nom,Date date ,String tags,String path , int points, int segments, int faces) {
		this.id = id;
		this.nom = nom;
		//this.date = date ;
		this.tags = tags ;
		this.path = path ;
		this.points = points;
		this.segments = segments;
		this.faces = faces;
	}

	


	public String toString() {
		return "[" + id + " , " + nom + " , " + " , " +tags + + points + " , " + segments + " , " + faces + "]";
	}

}