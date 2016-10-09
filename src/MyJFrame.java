

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MyJFrame extends JFrame {
	private Space space;
	
	public MyJFrame(Space space){
		super("Projet Modelisation");
		setSize(500, 500);
		this.space = space;
		setContentPane(space);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		new Options();
		
		
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
