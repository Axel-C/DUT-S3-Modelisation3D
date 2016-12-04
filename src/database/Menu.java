package database;

import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Menu extends JFrame {
	public static final int AJOUTER = 0 ;
	public static final int MODIFIER = 1 ;
	
	
	
	/**
	 * Affiche un nouveau Menu pour ajouter ou modifier un model de la base
	 * @param nomPo nom
	 * @param pathPo chemin d'access
	 * @param tagsPo mots cles
	 * @param action 0 pour AJOUTER et 1 pour MODIFIER
	 */
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
			ajouter.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Data.add(nom.getText(), tags.getText(), path.getText());
					Menu.this.dispose();
					
				}
			});
			add(ajouter);
		}else{
			JButton modifier = new JButton("Modifier");
			modifier.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Data.update(nomPo, nom.getText(), tags.getText(), path.getText());
					
				}
			});
			add(modifier);
		}
		
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 250);
		setVisible(true);
	}
	/**
	 * Creer un menu preconstruit pour ajouter
	 */
	public Menu(){
		new Menu("","","",0);
	}
	
}