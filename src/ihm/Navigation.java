package ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import database.Data;
import database.Fichier;

public class Navigation extends JPanel {
	DefaultListModel<Fichier> dataModel = new DefaultListModel<>() ;
	ArrayList<Fichier>  cache = Data.list() ; 
	
	public Navigation(){
		BorderLayout layout = new BorderLayout();
		setLayout(layout);
		
		JTextField search = new JTextField();
		search.setText("Recherche");
		search.setMinimumSize(new Dimension(200, 50));
		add(search, BorderLayout.NORTH);
		
		
		for(Fichier f:cache){
			dataModel.addElement(f);
		}
		
		JList<Fichier> liste = new JList<>(dataModel) ;
		
		add(liste, BorderLayout.CENTER);
		
		JButton ajouter = new JButton("Ajouter");
		add(ajouter , BorderLayout.SOUTH);
		
		
		setPreferredSize(new Dimension(200, 200));
		
	}

}
