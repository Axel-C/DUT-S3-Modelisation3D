package database;

import java.awt.ComponentOrientation;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javafx.geometry.Orientation;

public class Menu extends JFrame {
	public static final int AJOUTER = 0 ;
	public static final int MODIFIER = 1 ;
	
	
	
	
	public Menu(String nomPo , String pathPo , String tagsPo , int action){
		setTitle("Nouveau Fichier");
		setLayout(new GridLayout(5, 2));
		getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		// NOM
		JLabel nomlabel = new JLabel("Nom :");
		JTextField nom = new JTextField(nomPo);
		getContentPane().add(nomlabel);
		getContentPane().add(nom);
		
		//PATH
		add(new JLabel("Chemin d'acces :"));
		JTextField path = new JTextField(pathPo);
		add(path);
		
		//MOTS CLES
		add(new JLabel("Mots cles associée (separe par des espaces) :"));
		JTextField tags = new JTextField(tagsPo);
		add(tags);
		
		if(action == AJOUTER){
			JButton ajouter = new JButton("Ajouter");
		}else{
			
		}
		
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 600);
		setVisible(true);
	}
	
	public Menu(){
		new Menu("","","",0);
	}
	
}