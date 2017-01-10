package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Axis;
import model.Matrix;
import model.Model;
import view.Space;

public class KeyboardControls implements KeyListener {
	
	Space space;
	Model model;
	
	public KeyboardControls (Space space) {
		this.space=space;
		this.model=space.getModel();
	}
	

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_Z:
			model.translate(new Matrix(new double[][] { { 0 }, { 5 }, { 0 }, { 1 } }));
			break;
		case KeyEvent.VK_S:
			model.translate(new Matrix(new double[][] { { 0 }, { -5 }, { 0 }, { 1 } }));
			break;
		case KeyEvent.VK_D:
			model.translate(new Matrix(new double[][] { { 5 }, { 0 }, { 0 }, { 1 } }));
			break;
		case KeyEvent.VK_Q:
			model.translate(new Matrix(new double[][] { { -5 }, { 0 }, { 0 }, { 1 } }));
			break;
		case KeyEvent.VK_A:
			model.scale(new Matrix(new double[][] { { 1.05 }, { 1.05 }, { 1.05 }, { 1 } }));
			break;
		case KeyEvent.VK_E:
			model.scale(new Matrix(new double[][] { { 0.95 }, { 0.95 }, { 0.95 }, { 1 } }));
			break;
		case KeyEvent.VK_NUMPAD8:
			model.rotateModel(Axis.X, 2);
			break;
		case KeyEvent.VK_V:
			model.rotateModel(Axis.Z, 2);
			break;
		case KeyEvent.VK_NUMPAD2:
			model.rotateModel(Axis.X, -2);
			break;
		case KeyEvent.VK_NUMPAD4:
			model.rotateModel(Axis.Y, 2);
			break;
		case KeyEvent.VK_NUMPAD6:
			model.rotateModel(Axis.Y, -2);
			break;
		case KeyEvent.VK_NUMPAD1:
			model.rotateModel(Axis.Z, -2);
			break;
		case KeyEvent.VK_NUMPAD9:
			model.rotateModel(Axis.Z, 2);
			break;
		case KeyEvent.VK_R:
			model.adjust(space);
			break;
		}
		space.repaint();
		// System.out.println(space.getModel().getFaces()[0]);
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
