package src;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MyJFrame extends JFrame {
	private Space space;
	
	public MyJFrame(Space space){
		super("Projet Modelisation");
		super.setSize(500, 500);
		this.space = space;
		super.setContentPane(space);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setVisible(true);
		
		/*JMenuBar menu= new JMenuBar();
		JMenu fichier = new JMenu("Fichier");
		menu.add(fichier);
		setJMenuBar(menu);*/
	}
	
	public Space getSpace() {
		return space;
	}
	
	public void refresh() {
		space.repaint();
	}
}
