package database;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Fichier extends JPanel{
	private int id ;
	public String nom ; 
	public String contenu ;
	public int points ;
	public int segments ;
	public int faces ;
	
	public Fichier(int id , String nom , String contenu , int points , int segments , int faces ){
		this.id = id ;
		this.nom = nom ;
		this.contenu = contenu ;
		this.points = points ;
		this.segments = segments ;
		this.faces = faces ;
		add(new JLabel(nom));
		setMaximumSize(new Dimension(200, 20));
	}
	
	public String toString(){
		return "[" + id + " , " + nom + " , " +points + " , " + segments + " , " + faces + "]" ;
	}
	
	
	
}
