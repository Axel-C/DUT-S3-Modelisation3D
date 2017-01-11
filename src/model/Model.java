package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Observable;

import view.Space;

/**
 * Cette classe represente un modele 3D compose de plusieurs faces.
 * 
 * @author Groupe K5
 *
 */
public class Model extends Observable {
	private Face[] faces;
	private Point[] points;

	/**
	 * Instancie un modele 3D a partir d'un tableau de faces passe en parametre.
	 * 
	 * @param faces
	 *            Le tableau de faces.
	 */
	public Model(Face[] faces) {
		this.faces = faces;
		
		
	}

	/**
	 * Instancie un modele 3D a partir d'un fichier PLY passe en parametre.
	 * 
	 * @param plyFile
	 *            Le fichier PLY.
	 * @throws Exception
	 *             Si le fichier PLY est incorrect.
	 */
	public Model(File plyFile) throws Exception {
		constructFromPlyFile(plyFile);

	}
	
	public Model(String contenu) {
		Point[] points;
		String[] lignes = contenu.split("\n");
		int i = 0;
		while (!lignes[i].contains("element vertex")) {
			i++;
		}
		points = new Point[(Integer.valueOf(lignes[i].split(" ")[2]))];
		while (!lignes[i].contains("element face")) {
			i++;
		}
		faces = new Face[(Integer.valueOf(lignes[i].split(" ")[2]))];
		while (!lignes[i].equals("end_header")) {
			i++;
		}
		i++;
		Point cache;
		for (int j = 0; j < points.length; j++) {
			System.out.println(points.length);
			cache = new Point(Double.parseDouble(lignes[i].split(" ")[0]), Double.parseDouble(lignes[i].split(" ")[1]),
					Double.parseDouble(lignes[i].split(" ")[2]));
			points[j] = cache;
			i++;
		}

		for (int j = 1; j < faces.length; j++) {
			Point[] pointsOfFace = new Point[Integer.parseInt(lignes[i].split(" ")[0])];
			for (int k = 0; j < pointsOfFace.length; k++) {
				pointsOfFace[k] = points[Integer.parseInt(lignes[i].split(" ")[k + 1])];
			}
			faces[j] = new Face(pointsOfFace);
		}

		applyPaintersAlgorithm();

	}

	/**
	 * Renvoie la valeur X du point (parmi les points d'une face) contenu dans
	 * le tableau de Face "faces" pour lequel la valeur X est la plus petite.
	 * 
	 * @return Le plus petit X des faces de ce modele.
	 */
	public double getXMin() {
		double min = faces[0].getXMin();
		for (int i = 1; i < faces.length; i++) {
			if (faces[i].getXMin() < min)
				min = faces[i].getXMin();
		}
		return min;
	}

	/**
	 * Renvoie la valeur X du point (parmi les points d'une face) contenu dans
	 * le tableau de Face "faces" pour lequel la valeur X est la plus grande.
	 * 
	 * @return Le plus grand X des faces de ce modele.
	 */
	public double getXMax() {
		double max = faces[0].getXMax();
		for (int i = 1; i < faces.length; i++) {
			if (faces[i].getXMax() > max)
				max = faces[i].getXMax();
		}
		return max;
	}

	/**
	 * Renvoie la valeur Y du point (parmi les points d'une face) contenu dans
	 * le tableau de Face "faces" pour lequel la valeur Y est la plus petite.
	 * 
	 * @return Le plus petit Y des faces de ce modele.
	 */
	public double getYMin() {
		double min = faces[0].getYMin();
		for (int i = 1; i < faces.length; i++) {
			if (faces[i].getYMin() < min)
				min = faces[i].getYMin();
		}
		return min;
	}

	/**
	 * Renvoie la valeur Y du point (parmi les points d'une face) contenu dans
	 * le tableau de Face "faces" pour lequel la valeur Y est la plus petite.
	 * 
	 * @return Le plus grand Y des faces de ce modele.
	 */
	public double getYMax() {
		double max = faces[0].getYMax();
		for (int i = 1; i < faces.length; i++) {
			if (faces[i].getYMax() > max)
				max = faces[i].getYMax();
		}
		return max;
	}
	
	/**
	 * Renvoie la valeur Z du point (parmi les points d'une face) contenu dans
	 * le tableau de Face "faces" pour lequel la valeur Z est la plus petite.
	 * 
	 * @return Le plus grand Z des faces de ce modele.
	 */
	public double getZMax() {
		double max = faces[0].getZMax();
		for (int i = 1; i < faces.length; i++) {
			if (faces[i].getZMax() > max)
				max = faces[i].getZMax();
		}
		return max;
	}
	
	/**
	 * Renvoie la valeur Z du point (parmi les points d'une face) contenu dans
	 * le tableau de Face "faces" pour lequel la valeur Z est la plus petite.
	 * 
	 * @return Le plus petit Z des faces de ce modele.
	 */
	public double getZMin() {
		double min = faces[0].getZMin();
		for (int i = 1; i < faces.length; i++) {
			if (faces[i].getZMin() < min)
				min = faces[i].getZMin();
		}
		return min;
	}
	
	/**
	 * Renvoie le centre de gravite du modele sous la forme d'un point. 
	 * @return Le centre de gravite du modele.
	 */

	public Point barycentre() {
		Point centre = new Point(0.0,0.0,0.0);
		for(int i=0; i<this.getPoints().length; i++) {
			centre.setX(centre.getX()+this.getPoints()[i].getX());
			centre.setX(centre.getX()+this.getPoints()[i].getY());
			centre.setX(centre.getZ()+this.getPoints()[i].getZ());
		}
		centre.setX(centre.getX()/this.getPoints().length);
		centre.setY(centre.getY()/this.getPoints().length);
		centre.setZ(centre.getZ()/this.getPoints().length);
		return centre;
		
	}
	


	public Face[] getFaces() {
		return faces;
	}
	
	public Point[] getPoints() {
		return points;
	}

	/**
	 * @author Groupe K5 Applique l'algorithme du peintre a ce modele, cet
	 *         algorithme a pour but d'ameliorer l'impression de 3D en peignant
	 *         les faces dans le bon ordre.
	 */
	public void applyPaintersAlgorithm() {
		Collections.sort(Arrays.asList(faces), new Comparator<Face>() {
			@Override
			public int compare(Face f1, Face f2) {
				if (f1.getGreaterZ() < f2.getGreaterZ()) {
					return 1;
				} else if (f1.getGreaterZ() > f2.getGreaterZ()) {
					return -1;
				} else {
					return 0;
				}
			}
		});
	}

	/**
	 * Construit le modele a partir d'un fichier PLY passe en parametre.
	 * 
	 * @param plyFile
	 *            Le fichier PLY
	 * @throws Exception
	 *             Si le fichier PLY est incorrect.
	 */
	public void constructFromPlyFile(File plyFile) throws Exception {
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

	/**
	 * Translate le modele sur la matrice colonne passee en parametre.
	 * 
	 * @param columnMatrix
	 *            La matrice colonne.
	 */
	public void translate(Matrix columnMatrix) {
		HashSet<Point> translatedPoints = new HashSet<>();
		for (int i = 0; i < faces.length; i++) {
			for (int j = 0; j < faces[i].getPoints().length; j++) {
				if (!translatedPoints.contains(faces[i].getPoints()[j])) {
					faces[i].getPoints()[j].translate(columnMatrix);
					translatedPoints.add(faces[i].getPoints()[j]);
				}
			}
		}
		this.setChanged();
		notifyObservers();
	}

	/**
	 * Change l'echelle du modele sur la matrice colonne passee en parametre.
	 * 
	 * @param columnMatrix
	 *            La matrice colonne.
	 */
	public void scale(Matrix columnMatrix) {
		Point center = this.getCenter();
		this.translate(new Matrix(new double[][]{{-center.getX()},{-center.getY()},{-center.getZ()}}));
		HashSet<Point> scaledPoints = new HashSet<>();
		for (int i = 0; i < faces.length; i++) {
			for (int j = 0; j < faces[i].getPoints().length; j++) {
				if (!scaledPoints.contains(faces[i].getPoints()[j])) {
					faces[i].getPoints()[j].scale(columnMatrix);
					scaledPoints.add(faces[i].getPoints()[j]);
				}
			}
		}
		this.translate(new Matrix(new double[][]{{center.getX()},{center.getY()},{center.getZ()}}));
		this.setChanged();
		notifyObservers();
	}
	
	/** 
	 * Applique la rotation du modèle par rapport au centre de la figure
	 * @param axis L'axe selon lequel où souhaite faire la rotation
	 * @param angleInDegrees l'angle duquel on souhaite que la figure soit tournée
	 */
	public void rotateModel(Axis axis, double angleInDegrees) {
		//placer le centre de la figure en 0,0,0 pour appliquer la rotation
		Point center = this.getCenter();
		if (axis==Axis.X) {
			this.translate(new Matrix(new double[][]{{0},{-center.getY()},{-center.getZ()}}));
		} else if (axis==Axis.Y) {
			this.translate(new Matrix(new double[][]{{-center.getX()},{0},{-center.getZ()}}));
		} else {
			this.translate(new Matrix(new double[][]{{-center.getX()},{-center.getY()},{0}}));
		}
		this.rotate(axis, angleInDegrees);
		// Replacer la figure dans sa position d'origine.
		if (axis==Axis.X) {
			this.translate(new Matrix(new double[][]{{0},{center.getY()},{center.getZ()}}));
		} else if (axis==Axis.Y) {
			this.translate(new Matrix(new double[][]{{center.getX()},{0},{center.getZ()}}));
		} else {
			this.translate(new Matrix(new double[][]{{center.getX()},{center.getY()},{0}}));
		}

	}

	/**
	 * Fait tourner le modele autour de l'axe et d'un angle en degres passes en
	 * parametres.
	 * 
	 * @param axis
	 *            L'axe.
	 * @param angleInDegrees
	 *            L'angle en degres.
	 */
	public void rotate(Axis axis, double angleInDegrees) {
		HashSet<Point> rotatedPoints = new HashSet<>();
		for (int i = 0; i < faces.length; i++) {
			for (int j = 0; j < faces[i].getPoints().length; j++) {
				if (!rotatedPoints.contains(faces[i].getPoints()[j])) {
					faces[i].getPoints()[j].rotate(axis, angleInDegrees);
					rotatedPoints.add(faces[i].getPoints()[j]);
				}
			}
		}
		this.setChanged();
		notifyObservers();
	}
	
	public void adjust(Space space) {
		adjustScaling(space);
		adjustTranslating(space);
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Ajuste le modele, sa taille est recalculee suivant la taille de la frame
	 * qui la contient,
	 */
	public void adjustScaling(Space space) {
		int maxWidth = space.getWidth()-100;
		int maxHeight = space.getHeight()-100;
		// System.out.println("width : "+maxWidth+" ,Height : "+maxHeight);
		double range = this.getXMax() - this.getXMin();
		// System.out.println("range : "+range+" r : "+(maxWidth/range));
		double rapport;
		double rapportX = maxWidth / range;
		range = this.getYMax() - this.getYMin();
		double rapportY = maxHeight / range;
		rapport = Math.min(rapportX, rapportY);
		scale(new Matrix(new double[][] { { rapport }, { rapport }, { rapport }, { 1 } }));
	}

	/**
	 * Ajuste le modele en le replacant au centre de la frame.
	 */
	public void adjustTranslating(Space space) {
		int maxWidth = space.getWidth();
		int maxHeight = space.getHeight();
		double Xmax = this.getXMax(), Ymax = this.getYMax();
		double Xmin = this.getXMin(), Ymin = this.getYMin();
		double translateX = maxWidth / 2 - (Xmax - (Xmax - Xmin) / 2),
				translateY = maxHeight / 2 - (Ymax - (Ymax - Ymin) / 2);
		translate(new Matrix(new double[][] { { translateX }, { translateY }, { 0 }, { 1 } }));
	}
	
	public Point getCenter(){
		 double XMax=getXMax(),YMax=getYMax(),ZMax=getZMax();
		 double XMin=getXMin(),YMin=getYMin(),ZMin=getZMin();
		 return new Point(XMin+(XMax-XMin)/2,YMin+(YMax-YMin)/2,ZMin+(ZMax-ZMin)/2);
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
