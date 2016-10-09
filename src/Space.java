

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Space extends JPanel {
	public static final int ONLY_SEGMENTS = 1, ONLY_FACES = 2, SEGMENTS_AND_FACES = 3;
	private Model model;
	private int paintMode;

	public Space(Model model, int paintMode) {
		super();
		this.model = model;
		this.paintMode = paintMode;
		super.repaint();
	}

	@Override
	public void paint(Graphics g) {
		int zoom = 1500;
		int xAxis = super.getWidth()/2;
		int yAxis = super.getHeight()/4;
		int[] xPoints = new int[model.getFaces()[0].getVertices().length];
		int[] yPoints = new int[model.getFaces()[0].getVertices().length];
		for (int i = 0; i < model.getFaces().length; i++) {
			for (int j = 0; j < model.getFaces()[i].getVertices().length; j++) {
				xPoints[j] = (int) ((model.getFaces()[i].getVertices()[j].getX() * zoom) + xAxis);
				yPoints[j] = super.getHeight() - ((int) ((model.getFaces()[i].getVertices()[j].getY() * zoom) + yAxis));
			}
			if (paintMode == ONLY_SEGMENTS) {
				g.setColor(Color.BLACK);
				g.drawPolygon(xPoints, yPoints, model.getFaces()[i].getVertices().length);
			} else if (paintMode == ONLY_FACES) {
				g.setColor(Color.GRAY);
				g.fillPolygon(xPoints, yPoints, model.getFaces()[i].getVertices().length);
			} else {
				g.setColor(Color.BLACK);
				g.drawPolygon(xPoints, yPoints, model.getFaces()[i].getVertices().length);
				g.setColor(Color.GRAY);
				g.fillPolygon(xPoints, yPoints, model.getFaces()[i].getVertices().length);
			}
		}
	}
}
