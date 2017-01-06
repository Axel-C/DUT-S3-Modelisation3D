package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Axis;
import model.Matrix;
import view.Space;

public class KeyboardControls implements KeyListener {
	
	Space space;
	
	public KeyboardControls (Space space) {
		this.space=space;
	}
	

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_Z:
			space.translateModel(new Matrix(new double[][] { { 0 }, { 5 }, { 0 }, { 1 } }));
			space.repaint();
			break;
		case KeyEvent.VK_S:
			space.translateModel(new Matrix(new double[][] { { 0 }, { -5 }, { 0 }, { 1 } }));
			space.repaint();
			break;
		case KeyEvent.VK_D:
			space.translateModel(new Matrix(new double[][] { { 5 }, { 0 }, { 0 }, { 1 } }));
			space.repaint();
			break;
		case KeyEvent.VK_Q:
			space.translateModel(new Matrix(new double[][] { { -5 }, { 0 }, { 0 }, { 1 } }));
			space.repaint();
			break;
		case KeyEvent.VK_A:
			space.scaleModel(new Matrix(new double[][] { { 1.05 }, { 1.05 }, { 1.05 }, { 1 } }));
			space.repaint();
			break;
		case KeyEvent.VK_E:
			space.scaleModel(new Matrix(new double[][] { { 0.95 }, { 0.95 }, { 0.95 }, { 1 } }));
			space.repaint();
			break;
		case KeyEvent.VK_NUMPAD8:
			space.rotateModel(Axis.X, 2);
			space.repaint();
			break;
		case KeyEvent.VK_V:
			space.rotateModel(Axis.Z, 2);
			space.repaint();
			break;
		case KeyEvent.VK_NUMPAD2:
			space.rotateModel(Axis.X, -2);
			space.repaint();
			break;
		case KeyEvent.VK_NUMPAD4:
			space.rotateModel(Axis.Y, -2);
			space.repaint();
			break;
		case KeyEvent.VK_NUMPAD6:
			space.rotateModel(Axis.Y, 2);
			space.repaint();
			break;
		case KeyEvent.VK_NUMPAD1:
			space.rotateModel(Axis.Z, -2);
			space.repaint();
			break;
		case KeyEvent.VK_NUMPAD9:
			space.rotateModel(Axis.Z, 2);
			space.repaint();
			break;
		case KeyEvent.VK_R:
			space.adjustModel();
			space.repaint();
			break;
		}
		// System.out.println(space.getModel().getFaces()[0]);
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
