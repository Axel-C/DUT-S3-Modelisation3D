package projetmode;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class MyJFrame extends JFrame {
	private Space space;
	private JMenuBar options;

	public MyJFrame(Space space) {
		super("Projet Modelisation");
		setSize(500, 500);
		this.space = space;
		setContentPane(space);
		initializeOptions();
		setJMenuBar(options);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}

	private void initializeOptions() {
		options = new JMenuBar();
		JSlider zoomSlider = new JSlider(0, 10000, 0);
		JSlider xVectorSlider = new JSlider(-1000, 1000, -1000);
		JSlider yVectorSlider = new JSlider(-1000, 1000, -1000);
		zoomSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				space.setZoom(zoomSlider.getValue());
				space.repaint();
			}
		});
		xVectorSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				space.setXVector(xVectorSlider.getValue());
				space.repaint();
			}
		});
		yVectorSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				space.setYVector(yVectorSlider.getValue());
				space.repaint();
			}
		});
		options.add(new JLabel("Zoom:"));
		options.add(zoomSlider);
		options.add(new JLabel("Translation X:"));
		options.add(xVectorSlider);
		options.add(new JLabel("Translation Y:"));
		options.add(yVectorSlider);
	}
}
