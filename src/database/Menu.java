package database;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JFrame{
	
	public Menu(){
		setTitle("Menu");
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// PANEL
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		getContentPane().add(panel, BorderLayout.CENTER);
		
		// BOUTTONS
		
		JPanel boutons = new JPanel() ;
		boutons.setLayout(new BoxLayout(boutons, BoxLayout.X_AXIS));
		getContentPane().add(boutons, BorderLayout.SOUTH);
		
		JButton ouvrir = new JButton("Ouvrir");
		boutons.add(ouvrir);
		
		// CONTENU
		
		ArrayList<Fichier> fichiers = Data.getData();
		for(Fichier f:fichiers){
			panel.add(f);
		}
		
		setVisible(true);
	}
}
