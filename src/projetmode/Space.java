package projetmode;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Space extends JPanel {
	public static final int ONLY_SEGMENTS = 1, ONLY_FACES = 2, SEGMENTS_AND_FACES = 3;
	private Model model;
	private int paintMode;
	private int zoom, xVector, yVector;

	public Space(Model model, int paintMode) {
		super();
		this.model = model;
		this.paintMode = paintMode;
		repaint();
	}

	public Model getModel() {
		return model;
	}

	public int getZoom() {
		return zoom;
	}

	public void setZoom(int zoom) {
		this.zoom = zoom;
	}

	public int getXVector() {
		return xVector;
	}

	public void setXVector(int xVector) {
		this.xVector = xVector;
	}

	public int getYVector() {
		return yVector;
	}

	public void setYVector(int yVector) {
		this.yVector = yVector;
	}

	@Override
	public void paint(Graphics g) {
		int[] xPoints = new int[model.getFaces()[0].getVertices().length];
		int[] yPoints = new int[model.getFaces()[0].getVertices().length];
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, super.getWidth(), super.getHeight());
		for (int i = 0; i < model.getFaces().length; i++) {
			for (int j = 0; j < model.getFaces()[i].getVertices().length; j++) {
				xPoints[j] = (int) ((model.getFaces()[i].getVertices()[j].getX() * zoom) + xVector);
				yPoints[j] = super.getHeight()
						- ((int) ((model.getFaces()[i].getVertices()[j].getY() * zoom) + yVector));
			}
			if (paintMode == ONLY_SEGMENTS) {
				g.setColor(Color.BLACK);
				g.drawPolygon(xPoints, yPoints, model.getFaces()[i].getVertices().length);
			} else if (paintMode == ONLY_FACES) {
				g.setColor(Color.YELLOW);
				g.fillPolygon(xPoints, yPoints, model.getFaces()[i].getVertices().length);
			} else {
				g.setColor(Color.YELLOW);
				// int r = new Random().nextInt(255);
				// g.setColor(new Color(r, 254, 0));
				g.fillPolygon(xPoints, yPoints, model.getFaces()[i].getVertices().length);
				g.setColor(Color.BLACK);
				g.drawPolygon(xPoints, yPoints, model.getFaces()[i].getVertices().length);
			}
		}
	}
}
