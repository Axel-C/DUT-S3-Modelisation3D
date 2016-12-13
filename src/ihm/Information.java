package ihm;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.Year;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import database.Data;
import database.Fichier;
import model.Model;
import view.Space;

public class Information extends JPanel {
	Fichier fichier ;
	JLabel path ;
	JLabel tags ;
	JLabel nom ;
	JPanel info = new JPanel();
	
	public Information(){
		
		
		LayoutManager layout = new BorderLayout();
		setLayout(layout);
		nom = new JLabel("Nom du modele");
		add(nom , BorderLayout.NORTH);
		
		
		info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
		add(info , BorderLayout.CENTER);
		info.setBorder(new TitledBorder(null, "Informations"));
		path = new JLabel("Chemin d'acces : ");
		info.add(path);
		tags = new JLabel("Mots Cles : ");
		info.add(tags);
		
		
		
		
		
		
		
		
		
		
		
		
		JPanel controle = new JPanel();
		controle.setLayout(new BoxLayout( controle , BoxLayout.X_AXIS));
		JButton modifier = new JButton("Modifier");
		controle.add(modifier);
		JButton ouvrir = new JButton("Ouvrir");
		controle.add(ouvrir);
		JButton supprimer = new JButton("Supprimer");
		controle.add(supprimer);
		add(controle , BorderLayout.SOUTH);
		
		
		supprimer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Data.getInstance().delete(fichier.id);
				
			}
		});
		
				
		
		
				
		
		
	}
	
	
	public void select(Fichier f){
		nom.setText("Nom du modele : " + f.nom);
		path.setText("Chemin d'acces : " + f.path);
		tags.setText("Mots Cles : " + f.getTagsToString());
		
	}
	

}
