package view;

import java.awt.*;
import java.util.*;

import javax.swing.JPanel;

import model.Matrix;
import model.Model;

@SuppressWarnings("serial")
public class Space extends JPanel implements Observer{
	private Model model;
	private int paintMode;
	private Matrix pointEclairage= new Matrix(new double[][] {{-20.0},{10.0},{30.0}});
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


	/**
	 * Ajuste le modele, sa taille est recalculee suivant la taille de la frame
	 * qui la contient, et le modele est replace au centre de la frame.
	 */
	

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
				xPointsOmbre[j] = (int) ((model2.getFaces()[i].getOmbre()[j].getX()))- (int) (pointEclairage.getElement(0, 0));
				yPointsOmbre[j] = super.getHeight() - ((int) ((model2.getFaces()[i].getOmbre()[j].getY())- (int) (pointEclairage.getElement(1, 0))));				
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
				g.setColor(Color.BLACK);
				g.drawPolygon(xPoints, yPoints, model.getFaces()[i].getPoints().length);
			} else if (paintMode == ONLY_FACES) {
				g.setColor(Color.YELLOW);
				g.fillPolygon(xPoints, yPoints, model.getFaces()[i].getPoints().length);
			} else {
				int b;
				if((model.getFaces()[i].getZMax()-model.getFaces()[i].normal().getElement(2, 0)) - pointEclairage.getElement(2, 0) > 0) {
					b= (int) (((model.getFaces()[i].getZMax()-model.getFaces()[i].normal().getElement(2, 0)))/256);
					b= (Math.abs(b)*30)+30;
					if (b>=255) {
						g.setColor(new Color(255,255,255));
					} else if (b<=0) {
						g.setColor(new Color(255,255,0));
					} else {
						g.setColor(new Color(255,255,b));
					}
				} else if ((model.getFaces()[i].getZMax()-model.getFaces()[i].normal().getElement(2, 0)) - pointEclairage.getElement(2, 0) < 0) {
					b= (int) (((model.getFaces()[i].getZMax()+model.getFaces()[i].normal().getElement(2, 0)))/256);
					b=(Math.abs(b)*30)+30;
					if (b>=255) {
						g.setColor(new Color(255,255-(b/256),(b/256)));
					} else if (b<=0) {
						g.setColor(new Color(255-(b/256),255-b,0));
					} else {
						g.setColor(new Color(255-b,255-b,(b/256)));
					}
				} else if ((model.getFaces()[i].getZMax()-model.getFaces()[i].normal().getElement(2, 0)) - pointEclairage.getElement(2, 0) == 0) {
					g.setColor(Color.YELLOW);
				}
				g.fillPolygon(xPoints, yPoints, model.getFaces()[i].getPoints().length);
				g.setColor(Color.BLACK);
				g.drawPolygon(xPoints, yPoints, model.getFaces()[i].getPoints().length);
			}
			
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		this.repaint();
		
	}
}

