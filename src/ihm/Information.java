package ihm;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import database.Fichier;

public class Information extends JPanel {
	Fichier fichier ;
	
	public Information(){
		LayoutManager layout = new BorderLayout();
		
		add(new JLabel("Nom du modele") , BorderLayout.NORTH);
		
		JPanel info = new JPanel();
		
		
		
		
		
		
		
//		JPanel controle = new JPanel();
//		controle.setLayout(new BoxLayout( ge , BoxLayout.X_AXIS));
//		JButton modifier = new JButton("Ouvrir");
//		controle.add(modifier);
//		JButton ouvrir = new JButton("Ouvrir");
//		controle.add(ouvrir);
//		JButton supprimer = new JButton("Ouvrir");
//		controle.add(supprimer);
				
		
		
				
		
		
	}
	
	public void update(Fichier f){
		
	}
	

}
