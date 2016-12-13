package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Matrix;

public final class ZoomArriere extends ModelController implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		space.scaleModel(new Matrix(new double[][] { { 0.95 }, { 0.95 }, { 0.95 }, { 1 } }));
		space.repaint();
	}
}