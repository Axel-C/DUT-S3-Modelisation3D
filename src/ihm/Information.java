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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import database.Data;
import database.Fichier;
import database.Menu;
import model.Model;
import view.Fenetre;
import view.Space;

public class Information extends JPanel {
	Fichier fichier ;
	JLabel path ;
	JLabel tags ;
	JLabel nom ;
	JPanel info = new JPanel();
	Menu2 menu ;
	
	public Information(Menu2 menu){
		this.menu = menu ;
		
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
		modifier.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Fichier f = Data.getInstance().find(fichier.nom);
				if (f == null) {
					JOptionPane.showMessageDialog(null, "Fichier introuvable");

				} else {
					new Menu(f.nom, f.path, f.getTagsToString(), Menu.MODIFIER);
				}
				
			}
		});
		
		
		JButton ouvrir = new JButton("Ouvrir");
		controle.add(ouvrir);
		
		ouvrir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("DEBUG : " + fichier.path);
				try {
					Model model = new Model(new File(fichier.path)); 
					Space space = new Space(model, Space.SEGMENTS_AND_FACES);
					model.addObserver(space);
					new Fenetre(space);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		JButton supprimer = new JButton("Supprimer");
		controle.add(supprimer);
		add(controle , BorderLayout.SOUTH);
		
		
		supprimer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Data.getInstance().delete(fichier.id);
				int x = menu.getX();
				int y = menu.getY();
				menu.dispose();
				Menu2 m = new Menu2();
				m.setLocation(x, y);
				
				
			}
		});
		
				
		
		
				
		
		
	}
	
	
	public void select(Fichier f){
		fichier = f ;
		nom.setText("Nom du modele : " + f.nom);
		path.setText("Chemin d'acces : " + f.path);
		tags.setText("Mots Cles : " + f.getTagsToString());
		
	}
	

}
