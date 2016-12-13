package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Matrix;

public final class ZoomAvant extends ModelController implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		space.scaleModel(new Matrix(new double[][] { { 1.05 }, { 1.05 }, { 1.05 }, { 1 } }));
		space.repaint();
	}
}