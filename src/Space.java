import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
		if(paintMode == ONLY_SEGMENTS) {
			
		} else if(paintMode == ONLY_FACES) {
			
		} else {
			
		}
	}
}
