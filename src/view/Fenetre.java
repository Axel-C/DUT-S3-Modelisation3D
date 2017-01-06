package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.Ajustement;
import controller.KeyboardControls;
import controller.ModelController;
import controller.RotateXLeft;
import controller.RotateXRight;
import controller.RotateYLeft;
import controller.RotateYRight;
import controller.RotateZLeft;
import controller.RotateZRight;
import controller.TranslationBas;
import controller.TranslationDroite;
import controller.TranslationGauche;
import controller.TranslationHaut;
import controller.ZoomArriere;
import controller.ZoomAvant;
import model.Matrix;

@SuppressWarnings("serial")
public class Fenetre extends JFrame{

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
		ModelController.setSpace(space); 
		initializeButtons();
		globalContainer.add(buttons, BorderLayout.EAST);
		super.setContentPane(globalContainer);
		super.addKeyListener(new KeyboardControls(space));
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
		zoomButton.addActionListener(new ZoomAvant());
		dezoomButton.addActionListener(new ZoomArriere());
		scalingButtons.add(zoomButton);
		scalingButtons.add(dezoomButton);
		buttons.add(scalingButtons);
		// Boutons de translation et bouton d'ajustement
		JPanel translationButtons = new JPanel(new BorderLayout());
		translationButtons.setBorder(new TitledBorder("Translation"));
		JButton upTranslationButton = new JButton("Haut");
		upTranslationButton.setFocusable(false);
		upTranslationButton.addActionListener(new TranslationHaut());
		translationButtons.add(upTranslationButton, BorderLayout.NORTH);
		JButton downTranslationButton = new JButton("Bas");
		downTranslationButton.setFocusable(false);
		downTranslationButton.addActionListener(new TranslationBas());
		translationButtons.add(downTranslationButton, BorderLayout.SOUTH);
		JButton leftTranslationButton = new JButton("Gauche");
		leftTranslationButton.setFocusable(false);
		leftTranslationButton.addActionListener(new TranslationGauche());
		translationButtons.add(leftTranslationButton, BorderLayout.WEST);
		JButton rightTranslationButton = new JButton("Droite");
		rightTranslationButton.setFocusable(false);
		rightTranslationButton.addActionListener(new TranslationDroite());
		translationButtons.add(rightTranslationButton, BorderLayout.EAST);
		JButton adjustementButton = new JButton("Ajuster");
		adjustementButton.setFocusable(false);
		adjustementButton.addActionListener(new Ajustement());
		translationButtons.add(adjustementButton, BorderLayout.CENTER);
		buttons.add(translationButtons);
		// Boutons de rotation
		JPanel rotationButtons = new JPanel(new GridLayout(3, 3));
		rotationButtons.setBorder(new TitledBorder("Rotation"));
		JButton rotationXLeftButton = new JButton("Arrière");
		rotationXLeftButton.setFocusable(false);
		rotationXLeftButton.addActionListener(new RotateXLeft());
		rotationButtons.add(rotationXLeftButton);

		rotationButtons.add(new JLabel("Axe X", JLabel.CENTER));

		JButton rotationXRightButton = new JButton("Avant");
		rotationXRightButton.setFocusable(false);
		rotationXRightButton.addActionListener(new RotateXRight());
		rotationButtons.add(rotationXRightButton);
		
		JButton rotationYRightButton = new JButton("Gauche");
		rotationYRightButton.setFocusable(false);
		rotationYRightButton.addActionListener(new RotateYRight());
		rotationButtons.add(rotationYRightButton);

		rotationButtons.add(new JLabel("Axe Y", JLabel.CENTER));
		
		JButton rotationYLeftButton = new JButton("Droite");
		rotationYLeftButton.setFocusable(false);
		rotationYLeftButton.addActionListener(new RotateYLeft());
		rotationButtons.add(rotationYLeftButton);

		JButton rotationZLeftButton = new JButton("Horaire");
		rotationZLeftButton.setFocusable(false);
		rotationZLeftButton.addActionListener(new RotateZLeft());
		rotationButtons.add(rotationZLeftButton);

		rotationButtons.add(new JLabel("Axe Z", JLabel.CENTER));

		JButton rotationZRightButton = new JButton("Anti-horaire");
		rotationZRightButton.setFocusable(false);
		rotationZRightButton.addActionListener(new RotateZRight());
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

}
