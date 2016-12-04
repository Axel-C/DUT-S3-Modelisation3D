package projetmode;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class Fenetre extends JFrame implements KeyListener {
	private Space space;
	private JMenuBar options;
	private JPanel globalContainer;
	private JPanel buttons;

	public Fenetre(Space space) {
		super("Projet Modelisation");
		setSize(900, 600);
		globalContainer = new JPanel(new BorderLayout());
		this.space = space;
		globalContainer.add(space, BorderLayout.CENTER);
		initializeButtons();
		globalContainer.add(buttons, BorderLayout.EAST);
		super.setContentPane(globalContainer);
		super.addKeyListener(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		space.adjustModel();
		space.repaint();
	}

	private void initializeButtons() {
		buttons = new JPanel(new GridLayout(3, 1, 2, 2));
		// Boutons de changement d'�chelle
		JPanel scalingButtons = new JPanel(new GridLayout(2, 1));
		scalingButtons.setBorder(new TitledBorder("Zoom"));
		JButton zoomButton = new JButton("+");
		zoomButton.setFocusable(false);
		JButton dezoomButton = new JButton("-");
		dezoomButton.setFocusable(false);
		zoomButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				space.scaleModel(new Matrix(new double[][] { { 1.05 }, { 1.05 }, { 1.05 }, { 1 } }));
				space.repaint();
			}
		});
		dezoomButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				space.scaleModel(new Matrix(new double[][] { { 0.95 }, { 0.95 }, { 0.95 }, { 1 } }));
				space.repaint();
			}
		});
		scalingButtons.add(zoomButton);
		scalingButtons.add(dezoomButton);
		buttons.add(scalingButtons);
		// Boutons de translation et bouton d'ajustement
		JPanel translationButtons = new JPanel(new BorderLayout());
		translationButtons.setBorder(new TitledBorder("Translation"));
		JButton upTranslationButton = new JButton("Haut");
		upTranslationButton.setFocusable(false);
		upTranslationButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				space.translateModel(new Matrix(new double[][] { { 0 }, { 5 }, { 0 }, { 1 } }));
				space.repaint();
			}

		});
		translationButtons.add(upTranslationButton, BorderLayout.NORTH);
		JButton downTranslationButton = new JButton("Bas");
		downTranslationButton.setFocusable(false);
		downTranslationButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				space.translateModel(new Matrix(new double[][] { { 0 }, { -5 }, { 0 }, { 1 } }));
				space.repaint();
			}

		});
		translationButtons.add(downTranslationButton, BorderLayout.SOUTH);
		JButton leftTranslationButton = new JButton("Gauche");
		leftTranslationButton.setFocusable(false);
		leftTranslationButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				space.translateModel(new Matrix(new double[][] { { -5 }, { 0 }, { 0 }, { 1 } }));
				space.repaint();
			}

		});
		translationButtons.add(leftTranslationButton, BorderLayout.WEST);
		JButton rightTranslationButton = new JButton("Droite");
		rightTranslationButton.setFocusable(false);
		rightTranslationButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				space.translateModel(new Matrix(new double[][] { { 5 }, { 0 }, { 0 }, { 1 } }));
				space.repaint();
			}

		});
		translationButtons.add(rightTranslationButton, BorderLayout.EAST);
		JButton adjustementButton = new JButton("Ajuster");
		adjustementButton.setFocusable(false);
		adjustementButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				space.adjustModel();
				space.repaint();
			}
		});
		translationButtons.add(adjustementButton, BorderLayout.CENTER);
		buttons.add(translationButtons);
		// Boutons de rotation
		JPanel rotationButtons = new JPanel(new GridLayout(3, 3));
		rotationButtons.setBorder(new TitledBorder("Rotation"));
		JButton rotationXLeftButton = new JButton("Gauche");
		rotationXLeftButton.setFocusable(false);
		rotationXLeftButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				space.rotateModel(Axis.X, -2);
				space.repaint();
			}
		});
		rotationButtons.add(rotationXLeftButton);

		rotationButtons.add(new JLabel("Axe X", JLabel.CENTER));

		JButton rotationXRightButton = new JButton("Droite");
		rotationXRightButton.setFocusable(false);
		rotationXRightButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				space.rotateModel(Axis.X, 2);
				space.repaint();
			}
		});
		rotationButtons.add(rotationXRightButton);

		JButton rotationYLeftButton = new JButton("Gauche");
		rotationYLeftButton.setFocusable(false);
		rotationYLeftButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				space.rotateModel(Axis.Y, -2);
				space.repaint();
			}
		});
		rotationButtons.add(rotationYLeftButton);

		rotationButtons.add(new JLabel("Axe Y", JLabel.CENTER));

		JButton rotationYRightButton = new JButton("Droite");
		rotationYRightButton.setFocusable(false);
		rotationYRightButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				space.rotateModel(Axis.Y, 2);
				space.repaint();
			}
		});
		rotationButtons.add(rotationYRightButton);

		JButton rotationZLeftButton = new JButton("Gauche");
		rotationZLeftButton.setFocusable(false);
		rotationZLeftButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				space.rotateModel(Axis.Z, -2);
				space.repaint();
			}
		});
		rotationButtons.add(rotationZLeftButton);

		rotationButtons.add(new JLabel("Axe Z", JLabel.CENTER));

		JButton rotationZRightButton = new JButton("Droite");
		rotationZRightButton.setFocusable(false);
		rotationZRightButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				space.rotateModel(Axis.Z, 2);
				space.repaint();
			}
		});
		rotationButtons.add(rotationZRightButton);
		buttons.add(rotationButtons);
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
		}
		// System.out.println(space.getModel().getFaces()[0]);
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
