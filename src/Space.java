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
		int a = 25;
		int b = super.getWidth()/2;
		int c = super.getHeight()/2;
		int[] xPoints = new int[model.getFaces()[0].getVertices().length];
		int[] yPoints = new int[model.getFaces()[0].getVertices().length];
		for (int i = 0; i < model.getFaces().length; i++) {
			for (int j = 0; j < model.getFaces()[i].getVertices().length; j++) {
				xPoints[j] = (int) ((model.getFaces()[i].getVertices()[j].getX() * a) + b);
				yPoints[j] = (int) ((model.getFaces()[i].getVertices()[j].getY() * a) + c);
			}
			if (paintMode == ONLY_SEGMENTS) {
				g.drawPolyline(xPoints, yPoints, model.getFaces()[i].getVertices().length);
			} else if (paintMode == ONLY_FACES) {
				g.fillPolygon(xPoints, yPoints, model.getFaces()[i].getVertices().length);
			} else {
				g.drawPolyline(xPoints, yPoints, model.getFaces()[i].getVertices().length);
				g.fillPolygon(xPoints, yPoints, model.getFaces()[i].getVertices().length);
			}
		}
	}
}
