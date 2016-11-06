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

	public void adjustModel() {
		// TODO
		int minWidth = super.getWidth() / 2;
		int maxWidth = super.getWidth();
		int minHeight = super.getHeight() / 2;
		int maxHeight = super.getHeight();
		Point mark;
		do {
			mark = model.getFaces()[0].getPoints()[0];
			if(mark.getX() < 0) {
				translateModel(new Matrix(new double[][] {{1}, {0}, {0}, {1}}));
			} else if(mark.getY() < 0) {
				translateModel(new Matrix(new double[][] {{0}, {1}, {0}, {1}}));
			} else if(mark.getZ() < 0) {
				new Matrix(new double[][] {{0}, {0}, {1}, {1}});
			}
		} while(mark.getX() < 0 || mark.getY() < 0 || mark.getZ() < 0);
		do {
			mark = model.getFaces()[0].getPoints()[0];
			
			System.out.println(mark);
		} while (mark.getX() < minWidth || mark.getX() > maxWidth
				|| mark.getY() < minHeight || mark.getY() > maxHeight
				|| mark.getZ() < minWidth + minHeight || mark.getZ() > maxWidth + maxHeight);
		System.out.println("The model is adjusted.");
	}

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
