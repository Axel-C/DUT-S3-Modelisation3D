import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class Options extends JFrame {
	public static int Xvector = 0 ;
	public static int Yvector = 0 ;
	public static int Zzoom = 5000 ;
	
	public Options(){
		// INITIAISATION FENETRE
		super("Options");
		setSize(300, 300);
		// CONTENU
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(new JLabel("Vecteur X :"));
		JSlider sliderX = new JSlider(-1000 , 1000);
		panel.add(sliderX);
		panel.add(new JLabel("Vecteur Y :"));
		JSlider sliderY = new JSlider(-1000 , 1000);
		panel.add(sliderY);
		panel.add(new JLabel("Zoom :"));
		JSlider zoom = new JSlider(0 , 10000);
		panel.add(zoom);
		
		setVisible(true);
		// EVENEMENT
				sliderX.addChangeListener(new ChangeListener() {
					
					@Override
					public void stateChanged(ChangeEvent e) {
						JSlider source = (JSlider) e.getSource() ;
						Xvector = source.getValue();
						Main.frame.refresh();
						
					}
				});
				sliderY.addChangeListener(new ChangeListener() {
					
					@Override
					public void stateChanged(ChangeEvent e) {
						JSlider source = (JSlider) e.getSource() ;
						Yvector = source.getValue();
						Main.frame.refresh();
						
					}
				});
				zoom.addChangeListener(new ChangeListener() {
					
					@Override
					public void stateChanged(ChangeEvent e) {
						JSlider source = (JSlider) e.getSource() ;
						Zzoom = source.getValue();
						Main.frame.refresh();
						
					}
				});
	}
}
