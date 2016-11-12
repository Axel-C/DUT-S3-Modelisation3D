package projetmode;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Space extends JPanel {
	private Model model;
	private int paintMode;
	public final static int ONLY_SEGMENTS = 1;
	public final static int ONLY_FACES = 2;
	public final static int SEGMENTS_AND_FACES = 3;

	public Space(Model model, int paintMode) {
		this.model = model;
		this.paintMode = paintMode;
	}

	public Model getModel() {
		return model;
	}

	public void setPaintMode(int paintMode) {
		this.paintMode = paintMode;
	}

	public void translateModel(Matrix columnMatrix) {
		model.translate(columnMatrix);
	}

	public void scaleModel(Matrix columnMatrix) {
		model.scale(columnMatrix);
	}

	public void rotateModel(Axis axis, double angleInDegrees) {
		model.rotate(axis, angleInDegrees);
	}

	/**
	 * Ajuste le modele, sa taille est recalculée suivant la taille de la frame
	 * qui la contient, et le modele est replacé au centre de la frame.
	 */
	public void adjustModel() {
		adjustScaling();
		adjustTranslating();
	}

	/**
	 * Ajuste le modele, sa taille est recalculée suivant la taille de la frame
	 * qui la contient,
	 */
	private void adjustScaling() {
		int maxWidth = super.getWidth();
		int maxHeight = super.getHeight();
		// System.out.println("width : "+maxWidth+" ,Height : "+maxHeight);
		double range = model.getXMax() - model.getXMin();
		// System.out.println("range : "+range+" r : "+(maxWidth/range));
		double rapport;
		double rapportX = maxWidth / range;
		range = model.getYMax() - model.getYMin();
		double rapportY = maxHeight / range;
		rapport = Math.min(rapportX, rapportY);
		scaleModel(new Matrix(new double[][] { { rapport }, { rapport }, { rapport }, { 1 } }));
	}

	/**
	 * Ajuste le modele en le replacant au centre de la frame.
	 */
	private void adjustTranslating() {
		int maxWidth = super.getWidth();
		int maxHeight = super.getHeight();
		double Xmax = model.getXMax(), Ymax = model.getYMax();
		double Xmin = model.getXMin(), Ymin = model.getYMin();
		double translateX = maxWidth / 2 - (Xmax - (Xmax - Xmin) / 2),
				translateY = maxHeight / 2 - (Ymax - (Ymax - Ymin) / 2);
		translateModel(new Matrix(new double[][] { { translateX }, { translateY }, { 0 }, { 1 } }));
	}

	/**
	 * 
	 */
	@Override
	public void paint(Graphics g) {
		int[] xPoints;
		int[] yPoints;
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, super.getWidth(), super.getHeight());
		model.applyPaintersAlgorithm();
		for (int i = 0; i < model.getFaces().length; i++) {
			xPoints = new int[model.getFaces()[i].getPoints().length];
			yPoints = new int[model.getFaces()[i].getPoints().length];
			for (int j = 0; j < model.getFaces()[i].getPoints().length; j++) {
				xPoints[j] = (int) ((model.getFaces()[i].getPoints()[j].getX()));
				yPoints[j] = super.getHeight() - ((int) ((model.getFaces()[i].getPoints()[j].getY())));
			}
			if (paintMode == ONLY_SEGMENTS) {
				g.setColor(Color.BLACK);
				g.drawPolygon(xPoints, yPoints, model.getFaces()[i].getPoints().length);
			} else if (paintMode == ONLY_FACES) {
				g.setColor(Color.YELLOW);
				g.fillPolygon(xPoints, yPoints, model.getFaces()[i].getPoints().length);
			} else {
				g.setColor(Color.YELLOW);
				g.fillPolygon(xPoints, yPoints, model.getFaces()[i].getPoints().length);
				g.setColor(Color.BLACK);
				g.drawPolygon(xPoints, yPoints, model.getFaces()[i].getPoints().length);
			}
		}
	}
}
