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
import javax.swing.JComboBox;
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
	JComboBox<String> choix ;
	
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
					new Menu(f.nom, f.path, f.getTagsToString(), Menu.MODIFIER , menu );
				}
				
			}
		});
		
		
		JButton ouvrir = new JButton("Ouvrir");
		controle.add(ouvrir);
		
		ouvrir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int item = choix.getSelectedIndex() ;
				System.out.println(item);
				int mode = 0 ;
				if(item == 0){
					mode = Space.SEGMENTS_AND_FACES ;
				}else if(item == 1){
					mode = Space.ONLY_SEGMENTS ;
				}else if(item == 2){
					mode = Space.ONLY_FACES ;
				}
				try {
					Model model = new Model(new File(fichier.path)); 
					Space space = new Space(model, mode);
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
				menu.liste.refresh();
				
				
			}
		});
		
				
		
		choix = new JComboBox<>();
		choix.addItem("Segment et Face");
		choix.addItem("Segment");
		choix.addItem("Face");
		controle.add(choix);
		
				
		
		
	}
	
	
	public void select(Fichier f){
		fichier = f ;
		nom.setText("Nom du modele : " + f.nom);
		path.setText("Chemin d'acces : " + f.path);
		tags.setText("Mots Cles : " + f.getTagsToString());
		
	}
	

}
