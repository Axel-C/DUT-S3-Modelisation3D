import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MyFrame extends JFrame {
	private ThreeDimensionalSpace threeDimensionalSpace;
	
	public MyFrame(ThreeDimensionalSpace threeDimensionalSpace){
		super("Projet Modelisation");
		super.setSize(500, 500);
		super.setContentPane(threeDimensionalSpace);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setVisible(true);
		
		/*JMenuBar menu= new JMenuBar();
		JMenu fichier = new JMenu("Fichier");
		menu.add(fichier);
		setJMenuBar(menu);*/
	}
	
	public void refresh() {
		threeDimensionalSpace.repaint();
	}
}
