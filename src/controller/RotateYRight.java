package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Axis;

public final class RotateYRight extends ModelController implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		model.rotateModel(Axis.Y, 2);
		space.repaint();
	}
}
