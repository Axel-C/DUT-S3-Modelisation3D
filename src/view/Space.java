package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.Axis;
import model.Matrix;
import model.Model;
import model.Point;

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
		//placer le centre de la figure en 0,0,0 pour appliquer la rotation
		Point center = model.getCenter();
		if (axis==Axis.X) {
			model.translate(new Matrix(new double[][]{{0},{-center.getY()},{-center.getZ()}}));
		} else if (axis==Axis.Y) {
			model.translate(new Matrix(new double[][]{{-center.getX()},{0},{-center.getZ()}}));
		} else {
			model.translate(new Matrix(new double[][]{{-center.getX()},{-center.getY()},{0}}));
		}
		model.rotate(axis, angleInDegrees);
		// Replacer la figure dans sa position d'origine.
		if (axis==Axis.X) {
			model.translate(new Matrix(new double[][]{{0},{center.getY()},{center.getZ()}}));
		} else if (axis==Axis.Y) {
			model.translate(new Matrix(new double[][]{{center.getX()},{0},{center.getZ()}}));
		} else {
			model.translate(new Matrix(new double[][]{{center.getX()},{center.getY()},{0}}));
		}

	}

	/**
	 * Ajuste le modele, sa taille est recalculee suivant la taille de la frame
	 * qui la contient, et le modele est replace au centre de la frame.
	 */
	public void adjustModel() {
		adjustScaling();
		adjustTranslating();
	}

	/**
	 * Ajuste le modele, sa taille est recalculee suivant la taille de la frame
	 * qui la contient,
	 */
	public void adjustScaling() {
		int maxWidth = super.getWidth()-100;
		int maxHeight = super.getHeight()-100;
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
	public void adjustTranslating() {
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
		Model model2 = new Model(model.getFaces());
		int[] xPoints;
		int[] yPoints;
		int[] xPointsOmbre;
		int[] yPointsOmbre;
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, super.getWidth(), super.getHeight());
		model2.applyPaintersAlgorithm();
		for (int i = 0; i < model2.getFaces().length; i++) {
			xPointsOmbre = new int[model2.getFaces()[i].getOmbre().length];
			yPointsOmbre = new int[model2.getFaces()[i].getOmbre().length];
			for (int j = 0; j < model2.getFaces()[i].getOmbre().length; j++) {
				xPointsOmbre[j] = (int) ((model2.getFaces()[i].getOmbre()[j].getX()));
				yPointsOmbre[j] = super.getHeight() - ((int) ((model2.getFaces()[i].getOmbre()[j].getY())));				
			}
			g.setColor(Color.GRAY);
			g.fillPolygon(xPointsOmbre, yPointsOmbre, model2.getFaces()[i].getOmbre().length);
		}

		for (int i = 0; i < model.getFaces().length; i++) {
			xPoints = new int[model.getFaces()[i].getPoints().length];
			yPoints = new int[model.getFaces()[i].getPoints().length];

			for (int j = 0; j < model.getFaces()[i].getPoints().length; j++) {
				xPoints[j] = (int) ((model.getFaces()[i].getPoints()[j].getX()));
				yPoints[j] = super.getHeight() - ((int) ((model.getFaces()[i].getPoints()[j].getY())));
			}
			if (paintMode == ONLY_SEGMENTS) {
				//g.setColor(Color.BLACK);
				//g.drawPolygon(xPoints, yPoints, model.getFaces()[i].getPoints().length);
			} else if (paintMode == ONLY_FACES) {
				//g.setColor(Color.YELLOW);
				//g.fillPolygon(xPoints, yPoints, model.getFaces()[i].getPoints().length);
			} else {
				g.setColor(Color.YELLOW);
				g.fillPolygon(xPoints, yPoints, model.getFaces()[i].getPoints().length);
				g.setColor(Color.BLACK);
				g.drawPolygon(xPoints, yPoints, model.getFaces()[i].getPoints().length);
			}

		}
	}
}
