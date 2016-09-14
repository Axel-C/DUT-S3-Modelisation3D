import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MyJFrame extends JFrame {
	private ThreeDimensionalSpace threeDimensionalSpace;
	
	public MyJFrame(ThreeDimensionalSpace threeDimensionalSpace){
		super("Projet Modelisation");
		super.setSize(500, 500);
		this.threeDimensionalSpace = threeDimensionalSpace;
		super.setContentPane(threeDimensionalSpace);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setVisible(true);
		
		/*JMenuBar menu= new JMenuBar();
		JMenu fichier = new JMenu("Fichier");
		menu.add(fichier);
		setJMenuBar(menu);*/
	}
	
	public ThreeDimensionalSpace getThreeDimensionalSpace() {
		return threeDimensionalSpace;
	}
	
	public void refresh() {
		threeDimensionalSpace.repaint();
	}
}
