package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Axis;

public final class RotateZRight extends ModelController implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		model.rotateModel(Axis.Z, 2);
		space.repaint();
	}
}