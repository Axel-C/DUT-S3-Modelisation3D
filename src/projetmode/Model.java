package projetmode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class Model {
	private Face[] faces;

	public Model(Face[] faces) {
		this.faces = faces;
	}

	public Model(File plyFile) throws IOException {
		constructFromPlyFile(plyFile);
		
	}
	
	public Model(String contenu){
		Point[] points;
		String ligne ;
		String[] lignes = contenu.split("\n");
		int i = 0 ;
		while(!lignes[i].contains("element vertex") ){
			i++ ;
		}
		points = new Point[(Integer.valueOf(lignes[i].split(" ")[2]))];
		while(!lignes[i].contains("element face") ){
			i++ ;
		}
		faces = new Face[(Integer.valueOf(lignes[i].split(" ")[2]))];
		while (!lignes[i].equals("end_header")) {
			i++ ;
		}
		i++ ;
		Point cache ;
		for(int j = 0 ; j < points.length ; j++){
			System.out.println(points.length);
			cache = new Point(Double.parseDouble(lignes[i].split(" ")[0]), Double.parseDouble(lignes[i].split(" ")[1]),Double.parseDouble(lignes[i].split(" ")[2]));
			points[j] = cache ;
			i++ ;
		}
		
		for(int j = 1 ; j < faces.length ; j++){
			Point[] pointsOfFace = new Point[Integer.parseInt(lignes[i].split(" ")[0])];
			for (int k = 0; j < pointsOfFace.length; k++) {
				pointsOfFace[k] = points[Integer.parseInt(lignes[i].split(" ")[k + 1])];
			}
			faces[j] = new Face(pointsOfFace);
		}
		
		applyPaintersAlgorithm();
		
	}

	public Face[] getFaces() {
		return faces;
	}
	
	public void applyPaintersAlgorithm() {
		Collections.sort(Arrays.asList(faces), new Comparator<Face>() {
			@Override
			public int compare(Face f1, Face f2) {
				if(f1.getGreaterZ() < f2.getGreaterZ()) {
					return 1;
				} else if(f1.getGreaterZ() > f2.getGreaterZ()) {
					return -1;
				} else {
					return 0;
				}
			}
		});
	}

	public void constructFromPlyFile(File plyFile) throws IOException {
		Point[] points;
		BufferedReader bufferedReader;
		String lineRead = "";
		bufferedReader = new BufferedReader(new FileReader(plyFile));
		while (!lineRead.contains("element vertex")) {
			lineRead = bufferedReader.readLine();
		}
		points = new Point[(Integer.valueOf(lineRead.split(" ")[2]))];
		while (!lineRead.contains("element face")) {
			lineRead = bufferedReader.readLine();
		}
		faces = new Face[(Integer.valueOf(lineRead.split(" ")[2]))];
		while (!lineRead.equals("end_header")) {
			lineRead = bufferedReader.readLine();
		}
		for (int i = 0; i < points.length; i++) {
			lineRead = bufferedReader.readLine();
			points[i] = new Point(Double.valueOf(lineRead.split(" ")[0]), Double.valueOf(lineRead.split(" ")[1]),
					Double.valueOf(lineRead.split(" ")[2]));
		}
		for (int i = 0; i < faces.length; i++) {
			lineRead = bufferedReader.readLine();
			Point[] pointsOfFace = new Point[Integer.valueOf(lineRead.split(" ")[0])];
			for (int j = 0; j < pointsOfFace.length; j++) {
				pointsOfFace[j] = points[Integer.valueOf(lineRead.split(" ")[j + 1])];
			}
			faces[i] = new Face(pointsOfFace);
		}
		bufferedReader.close();
		applyPaintersAlgorithm();
	}

	public void translate(Matrix columnMatrix) {
		HashSet<Point> translatedPoints = new HashSet<>();
		for (int i = 0; i < faces.length; i++) {
			for (int j = 0; j < faces[i].getPoints().length; j++) {
				if(!translatedPoints.contains(faces[i].getPoints()[j])) {
					faces[i].getPoints()[j].translate(columnMatrix);
					translatedPoints.add(faces[i].getPoints()[j]);
				}
			}
		}
	}

	public void scale(Matrix columnMatrix) {
		HashSet<Point> scaledPoints = new HashSet<>();
		for (int i = 0; i < faces.length; i++) {
			for (int j = 0; j < faces[i].getPoints().length; j++) {
				if(!scaledPoints.contains(faces[i].getPoints()[j])) {
					faces[i].getPoints()[j].scale(columnMatrix);
					scaledPoints.add(faces[i].getPoints()[j]);
				}
			}
		}
	}

	public void rotate(Axis axis, double angleInDegrees) {
		HashSet<Point> rotatedPoints = new HashSet<>();
		for (int i = 0; i < faces.length; i++) {
			for (int j = 0; j < faces[i].getPoints().length; j++) {
				if(!rotatedPoints.contains(faces[i].getPoints()[j])) {
					faces[i].getPoints()[j].rotate(axis, angleInDegrees);
					rotatedPoints.add(faces[i].getPoints()[j]);
				}
			}
		}
	}

	public String toString() {
		String string = "[";
		for (int i = 0; i < faces.length; i++) {
			string += faces[i];
		}
		string += "]";
		return string;
	}
}
