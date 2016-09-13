import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ThreeDimensionalSpace extends JPanel {
	Vertex[] vertices;
	Face[] faces;
	public ThreeDimensionalSpace(File plyFile) {
		super();
		convertPlyFile(plyFile);
	}
	
	public void paint(Graphics g) {
	}
	
	private void convertPlyFile(File plyFile) {
		BufferedReader bufferedReader;
		String lineRead = "";
		try {
			bufferedReader = new BufferedReader(new FileReader(plyFile));
			while (!lineRead.contains("element vertex")) {
				lineRead = bufferedReader.readLine();
			}
			vertices = new Vertex[(Integer.valueOf(lineRead.split(" ")[2]))];
			while (!lineRead.contains("element face")) {
				lineRead = bufferedReader.readLine();
			}
			faces = new Face[(Integer.valueOf(lineRead.split(" ")[2]))];
			while (!lineRead.equals("end_header")) {
				lineRead = bufferedReader.readLine();
			}
			for (int i = 0; i < vertices.length; i++) {
				lineRead = bufferedReader.readLine();
				vertices[i] = new Vertex(Integer.valueOf(lineRead.split(" ")[0]),
						Integer.valueOf(lineRead.split(" ")[1]), Integer.valueOf(lineRead.split(" ")[2]));
			}
			for(int i = 0; i < faces.length; i++) {
				lineRead = bufferedReader.readLine();
				Vertex[] faceVertices = new Vertex[Integer.valueOf(lineRead.split(" ")[0])];
				for(int j = 0; j < faceVertices.length; j ++) {
					faceVertices[j] = vertices[Integer.valueOf(lineRead.split(" ")[j + 1])];
				}
				faces[i] = new Face(faceVertices);
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
