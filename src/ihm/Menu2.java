package ihm;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Menu2 extends JFrame{
	private Navigation liste = new Navigation() ;
	private Information info = new Information();
	
	public static void main(String[] args ){
		new Menu2();
	}
	
	
	Menu2(){
		BorderLayout layout = new BorderLayout();
		setLayout(layout);
		
		add(liste, BorderLayout.WEST);
		add(info , BorderLayout.CENTER);
		
		
		setTitle("Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		setVisible(true);
	}

}
