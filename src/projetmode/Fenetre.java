package projetmode;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class Fenetre extends JFrame implements KeyListener {
	private Space space;
	private JMenuBar options;

	public Fenetre(Space space) {
		super("Projet Modelisation");
		setSize(900, 600);
		this.space = space;
		setContentPane(space);
		// initializeOptions();
		// setJMenuBar(options);
		super.addKeyListener(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	@SuppressWarnings("unused")
	private void initializeOptions() {
		// TODO 
		options = new JMenuBar();
		final JSlider scalingSlider = new JSlider(0, 5000);
		final JSlider xTranslationSlider = new JSlider(-super.getWidth() / 2, super.getWidth() * 2);
		final JSlider yTranslationSlider = new JSlider(-super.getHeight() / 2, super.getHeight() * 2);
		scalingSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				space.scaleModel(new Matrix(new double[][] {{1}, {1}, {1}, {1}}));
				space.repaint();
			}
		});
		xTranslationSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				space.translateModel(new Matrix(new double[][] {{0}, {0}, {0}, {1}}));
				space.repaint();
			}
		});
		yTranslationSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				space.translateModel(new Matrix(new double[][] {{0}, {0}, {0}, {1}}));
				space.repaint();
			}
		});
		options.add(new JLabel("Zoom:"));
		options.add(scalingSlider);
		options.add(new JLabel("Translation X:"));
		options.add(xTranslationSlider);
		options.add(new JLabel("Translation Y:"));
		options.add(yTranslationSlider);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_Z) {
			space.translateModel(new Matrix(new double[][] {{0}, {3}, {0}, {1}}));
			space.repaint();
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			space.translateModel(new Matrix(new double[][] {{0}, {-3}, {0}, {1}}));
			space.repaint();
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			space.translateModel(new Matrix(new double[][] {{3}, {0}, {0}, {1}}));
			space.repaint();
		} else if (e.getKeyCode() == KeyEvent.VK_Q) {
			space.translateModel(new Matrix(new double[][] {{-3}, {0}, {0}, {1}}));
			space.repaint();
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			space.scaleModel(new Matrix(new double[][] {{1.03}, {1.03}, {1.03}, {1}}));
			space.repaint();
		} else if (e.getKeyCode() == KeyEvent.VK_E) {
			space.scaleModel(new Matrix(new double[][] {{0.97}, {0.97}, {0.97}, {1}}));
			space.repaint();
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			space.rotateModel(Axis.X, 1);
			space.repaint();
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			space.rotateModel(Axis.X, -1);
			space.repaint();
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			space.rotateModel(Axis.Y, -1);
			space.repaint();
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			space.rotateModel(Axis.Y, 1);
			space.repaint();
		} else if (e.getKeyCode() == KeyEvent.VK_I) {
			space.rotateModel(Axis.Z, -1);
			space.repaint();
		} else if (e.getKeyCode() == KeyEvent.VK_K) {
			space.rotateModel(Axis.Z, 1);
			space.repaint();
		} else if (e.getKeyCode() == KeyEvent.VK_R) {
			space.adjustModel();
			space.repaint();
		}
		System.out.println(space.getModel().getFaces()[0]);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
