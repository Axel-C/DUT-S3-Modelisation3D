package model;

/**
 * Cette classe represente une face 3D composee de plusieurs points.
 * 
 * @author Groupe K5
 *
 */
public class Face {
	private Point[] points;
	private Point[] ombre;

	/**
	 * Instancie une face a partir d'un tableau de points passe en parametre.
	 * 
	 * @param points
	 *            Le tableau de points.
	 */
	
	public Face(Point[] points) {
		this.points = points;
		ombre= new Point[points.length];
	}
	
	
	/**
	 * @return Les points dessinant l'ombre de cette face sous forme d'un tableau.
	 */
	public Point[] getOmbre() {
		Matrix m= new Matrix (new double[][] {{1.0,0.5,0.1},{0.0,0.5,0.0},{0.0,0.5,1.0}});
		for(int i= 0; i< ombre.length; i++) {
			Matrix res= m.multiply(new Matrix(new double[][] {{points[i].getX()},{points[i].getY()},{points[i].getZ()}}));
			ombre[i]=new Point(res.getElement(0, 0)-50,res.getElement(1, 0)+30,res.getElement(2, 0)-50);
		}
		return ombre;
	}



	/**
	 * @return Les points de cette face sous forme d'un tableau.
	 */
	public Point[] getPoints() {
		return points;
	}

	/**
	 * Translate cette face sur la matrice colonne passee en parametre.
	 * 
	 * @param columnMatrix
	 *            La matrice colonne sur laquelle cette face translatera.
	 */
	public void translate(Matrix columnMatrix) {
		for (int i = 0; i < points.length; i++) {
			points[i].translate(columnMatrix);
		}
	}

	/**
	 * Change l'echelle de cette face sur la matrice colonne passee en parametre.
	 * 
	 * @param columnMatrix
	 *            La matrice colonne sur laquelle cette face changera d'echelle.
	 */
	public void scale(Matrix columnMatrix) {
		for (int i = 0; i < points.length; i++) {
			points[i].scale(columnMatrix);
		}
	}

	/**
	 * @return Retourne le vecteur normal à la face.
	 */
	
	public Matrix normal() {
		Matrix v1 = new Matrix(3,1);
		Matrix v2 = new Matrix(3,1);
		v1.setElement(0, 0, (this.getPoints()[1].getX()-this.getPoints()[0].getX()));
		v1.setElement(1, 0, (this.getPoints()[1].getY()-this.getPoints()[0].getY()));
		v1.setElement(2, 0, (this.getPoints()[1].getZ()-this.getPoints()[0].getZ()));
		//Etant donné que les faces sont des triangles, on considérera qu'une face a 3 points
		v2.setElement(0, 0, (this.getPoints()[2].getX()-this.getPoints()[0].getX()));
		v2.setElement(1, 0, (this.getPoints()[2].getY()-this.getPoints()[0].getY()));
		v2.setElement(2, 0, (this.getPoints()[2].getZ()-this.getPoints()[0].getZ()));
		
		Matrix normal = new Matrix (3,1);
		
		normal.setElement(0, 0, (v1.getElement(1, 0)*v2.getElement(2, 0) - v1.getElement(2, 0)*v2.getElement(1, 0))); 
		normal.setElement(1, 0, (v1.getElement(2, 0)*v2.getElement(0, 0) - v1.getElement(0, 0)*v2.getElement(2, 0)));
		normal.setElement(2, 0, (v1.getElement(0, 0)*v2.getElement(1, 0) - v1.getElement(1, 0)*v2.getElement(0, 0)));
		
		return normal;
	}

	/**
	 * Fait tourner cette face autour de l'axe d'un angle passe en
	 * parametre.
	 * 
	 * @param axis
	 *            L'axe autour duquel la face tournera.
	 * @param angleInDegrees
	 *            L'angle de rotation (exprime en degres de) autour de l'axe passe en parametre.
	 */
	public void rotate(Axis axis, double angleInDegrees) {
		for (int i = 0; i < points.length; i++) {
			points[i].rotate(axis, angleInDegrees);
		}
	}

	/**
	 * Renvoie la valeur Z du point contenu dans le tableau de Point "points"
	 * pour lequel la valeur Z est la plus grande de tout les points du tableau.
	 * 
	 * @return Le plus petit Z des points de cette face.
	 */
	public double getGreaterZ() {
		double greaterZ = getPoints()[0].getZ();
		for (int i = 1; i < getPoints().length; i++) {
			if (getPoints()[i].getZ() > greaterZ) {
				greaterZ = getPoints()[i].getZ();
			}
		}
		return greaterZ;
	}

	/**
	 * Renvoie la valeur X du point contenu dans le tableau de Point "points"
	 * pour lequel la valeur X est la plus petite de tout les points du tableau.
	 * 
	 * @return Le plus petit X des points de cette face.
	 */
	public double getXMin() {
		double min = points[0].getX();
		for (int i = 1; i < points.length; i++) {
			if (points[i].getX() < min)
				min = points[i].getX();
		}
		return min;
	}

	/**
	 * Renvoie la valeur X du point contenu dans le tableau de Point "points"
	 * pour lequel la valeur X est la plus grande de tout les points du tableau.
	 * 
	 * @return Le plus grand X des points de cette face.
	 */
	public double getXMax() {
		double max = points[0].getX();
		for (int i = 1; i < points.length; i++) {
			if (points[i].getX() > max)
				max = points[i].getX();
		}
		return max;
	}

	/**
	 * Renvoie la valeur Y du point contenu dans le tableau de Point "points"
	 * pour lequel la valeur Y est la plus petite de tout les points du tableau.
	 * 
	 * @return Le plus petit Y des points de cette face.
	 */
	public double getYMin() {
		double min = points[0].getY();
		for (int i = 1; i < points.length; i++) {
			if (points[i].getY() < min)
				min = points[i].getY();
		}
		return min;
	}

	/**
	 * Renvoie la valeur Y du point contenu dans le tableau de Point "points"
	 * pour lequel la valeur Y est la plus grande de tout les points du tableau.
	 * 
	 * @return Le plus grand Y des points de cette face.
	 */
	public double getYMax() {
		double max = points[0].getY();
		for (int i = 1; i < points.length; i++) {
			if (points[i].getY() > max)
				max = points[i].getY();
		}
		return max;
	}
	
	/**
	 * Renvoie la valeur Z du point contenu dans le tableau de Point "points"
	 * pour lequel la valeur Z est la plus petite de tout les points du tableau.
	 * 
	 * @return Le plus petit Z des points de cette face.
	 */
	public double getZMin() {
		double min = points[0].getZ();
		for (int i = 1; i < points.length; i++) {
			if (points[i].getZ() < min)
				min = points[i].getZ();
		}
		return min;
	}

	/**
	 * Renvoie la valeur Z du point contenu dans le tableau de Point "points"
	 * pour lequel la valeur Z est la plus grande de tout les points du tableau.
	 * 
	 * @return Le plus grand Z des points de cette face.
	 */
	public double getZMax() {
		double max = points[0].getZ();
		for (int i = 1; i < points.length; i++) {
			if (points[i].getZ() > max)
				max = points[i].getZ();
		}
		return max;
	}
	
	@Override
	public String toString() {
		String string = "{" + points[0];
		for (int i = 1; i < points.length; i++) {
			string += ", " + points[i];
		}
		string += "}";
		return string;
	}
}
