package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Matrix;

public final class TranslationHaut extends ModelController implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		model.translate(new Matrix(new double[][] { { 0 }, { 5 }, { 0 }, { 1 } }));
		space.repaint();
	}
}
