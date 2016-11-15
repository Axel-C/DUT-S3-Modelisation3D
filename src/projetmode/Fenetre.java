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
				space.scaleModel(new Matrix(new double[][] { { 1 }, { 1 }, { 1 }, { 1 } }));
				space.repaint();
			}
		});
		xTranslationSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				space.translateModel(new Matrix(new double[][] { { 0 }, { 0 }, { 0 }, { 1 } }));
				space.repaint();
			}
		});
		yTranslationSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				space.translateModel(new Matrix(new double[][] { { 0 }, { 0 }, { 0 }, { 1 } }));
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

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_Z:
			space.translateModel(new Matrix(new double[][] { { 0 }, { 5 }, { 0 }, { 1 } }));
			space.repaint();
			break;
		case KeyEvent.VK_S:
			space.translateModel(new Matrix(new double[][] { { 0 }, { -5 }, { 0 }, { 1 } }));
			space.repaint();
			break;
		case KeyEvent.VK_D:
			space.translateModel(new Matrix(new double[][] { { 5 }, { 0 }, { 0 }, { 1 } }));
			space.repaint();
			break;
		case KeyEvent.VK_Q:
			space.translateModel(new Matrix(new double[][] { { -5 }, { 0 }, { 0 }, { 1 } }));
			space.repaint();
			break;
		case KeyEvent.VK_A:
			space.scaleModel(new Matrix(new double[][] { { 1.05 }, { 1.05 }, { 1.05 }, { 1 } }));
			space.repaint();
			break;
		case KeyEvent.VK_E:
			space.scaleModel(new Matrix(new double[][] { { 0.95 }, { 0.95 }, { 0.95 }, { 1 } }));
			space.repaint();
			break;
		case KeyEvent.VK_NUMPAD8:
			space.rotateModel(Axis.X, 2);
			space.repaint();
			break;
		case KeyEvent.VK_NUMPAD2:
			space.rotateModel(Axis.X, -2);
			space.repaint();
			break;
		case KeyEvent.VK_NUMPAD4:
			space.rotateModel(Axis.Y, -2);
			space.repaint();
			break;
		case KeyEvent.VK_NUMPAD6:
			space.rotateModel(Axis.Y, 2);
			space.repaint();
			break;
		case KeyEvent.VK_NUMPAD1:
			space.rotateModel(Axis.Z, -2);
			space.repaint();
			break;
		case KeyEvent.VK_NUMPAD9:
			space.rotateModel(Axis.Z, 2);
			space.repaint();
			break;
		case KeyEvent.VK_R:
			space.adjustModel();
			space.repaint();
			break;
		case KeyEvent.VK_T:
			space.adjustTranslating();
			space.repaint();
		case KeyEvent.VK_G:
			space.adjustScaling();
			space.repaint();
		}
		// System.out.println(space.getModel().getFaces()[0]);
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
