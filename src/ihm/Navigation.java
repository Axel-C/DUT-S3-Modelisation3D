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
import database.Menu;
import sun.swing.SwingAccessor;

public class Navigation extends JPanel {
	DefaultListModel<Fichier> dataModel = new DefaultListModel<>() ;
	ArrayList<Fichier>  cache = Data.getInstance().findAll() ; 
	Information info ;
	JList<Fichier> liste ;
	Menu2 menu ;
	
	/**
	 * Créer un menu de navigation 
	 * @param menu
	 */
	public Navigation( Menu2 menu){
		this.info = menu.info ;
		this.menu = menu ;
		
		BorderLayout layout = new BorderLayout();
		setLayout(layout);
		
		
		
		
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
				new Menu(menu);
				
				
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
	
	/**
	 * Actualise la liste des modeles
	 */
	public void refresh(){
		int x = menu.getX();
		int y = menu.getY();
		menu.dispose();
		Menu2 m = new Menu2();
		m.setLocation(x, y);
		
		
		
				
		
		
		
		
		
		
	}
	
	

}
