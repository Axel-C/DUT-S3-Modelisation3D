package ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.CellRendererPane;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import database.Data;
import database.Fichier;
import database.Main;
import database.Menu;
import sun.swing.SwingAccessor;

public class Navigation extends JPanel {
	DefaultListModel<Fichier> dataModel = new DefaultListModel<>() ;
	ArrayList<Fichier>  cache = Data.getInstance().findAll() ; 
	Information info ;
	JList<Fichier> liste ;
	
	public Navigation( Information info){
		this.info = info ;
		
		BorderLayout layout = new BorderLayout();
		setLayout(layout);
		
		JTextField search = new JTextField();
		search.setText("Recherche");
		search.setMinimumSize(new Dimension(200, 50));
		add(search, BorderLayout.NORTH);
		
		
		for(Fichier f:cache){
			dataModel.addElement(f);
		}
		
		liste = new JList<>(dataModel) ;
		
		add(liste, BorderLayout.CENTER);
		
		JButton ajouter = new JButton("Ajouter");
		add(ajouter , BorderLayout.SOUTH);
		ajouter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Menu();
				
			}
		});
		
		setPreferredSize(new Dimension(200, 200));
		
		
		liste.setFont(new Font("Arial", Font.BOLD, 20));
		DefaultListCellRenderer renderer = (DefaultListCellRenderer)liste.getCellRenderer() ;
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		liste.setCellRenderer(renderer);
		liste.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				info.select(liste.getSelectedValue());
				
			}
		});
		
		liste.setSelectedIndex(0);
		
	}
	
	public void refresh(){
		remove(liste);
		
		cache = Data.getInstance().findAll();
		liste = new JList<>(dataModel) ;
		add(liste);
		
		liste.setFont(new Font("Arial", Font.BOLD, 20));
		DefaultListCellRenderer renderer = (DefaultListCellRenderer)liste.getCellRenderer() ;
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		liste.setCellRenderer(renderer);
		liste.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				info.select(liste.getSelectedValue());
				
			}
		});
		
		liste.setSelectedIndex(0);
		updateUI();
		
		
		
				
		
		
		
		
		
		
	}
	
	

}
