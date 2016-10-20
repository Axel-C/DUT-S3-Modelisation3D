package projetmode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

public class Model {
	private Vertex[] vertices;
	private Face[] faces;

	public Model(File plyFile) throws IOException {
		constructFromPlyFile(plyFile);
	}

	public Vertex[] getVertices() {
		return vertices;
	}

	public Face[] getFaces() {
		return faces;
	}

	public void constructFromPlyFile(File plyFile) throws IOException {
		BufferedReader bufferedReader;
		String lineRead = "";
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
			vertices[i] = new Vertex(Double.valueOf(lineRead.split(" ")[0]), Double.valueOf(lineRead.split(" ")[1]),
					Double.valueOf(lineRead.split(" ")[2]));
		}
		for (int i = 0; i < faces.length; i++) {
			lineRead = bufferedReader.readLine();
			Vertex[] faceVertices = new Vertex[Integer.valueOf(lineRead.split(" ")[0])];
			for (int j = 0; j < faceVertices.length; j++) {
				faceVertices[j] = vertices[Integer.valueOf(lineRead.split(" ")[j + 1])];
			}
			faces[i] = new Face(faceVertices);
		}
		AlgoDuPeintre();
		bufferedReader.close();
	}

	public void AlgoDuPeintre() {
		Collections.sort(Arrays.asList(faces), Collections.reverseOrder());
	}
}
