package ihm;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

import database.Data;

public class Menu2 extends JFrame{
	public Navigation liste ;
	public Information info ;
	
	public static void main(String[] args ){
		new Menu2();
	}
	
	
	Menu2(){
		BorderLayout layout = new BorderLayout();
		setLayout(layout);
		
		info = new Information(this);
		liste = new Navigation(this);
		
		add(liste, BorderLayout.WEST);
		add(info , BorderLayout.CENTER);
		
		
		
		setTitle("Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		setVisible(true);
		
		
	}
	
	
	
}
