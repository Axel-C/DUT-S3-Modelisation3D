import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ThreeDimensionalSpace extends JPanel {
	File plyFile;
	MyReader reader;
	public ThreeDimensionalSpace(File plyFile) {
		super();
		this.plyFile = plyFile;
		try {
			reader = new MyReader(new FileReader(plyFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g) {
	}
}
