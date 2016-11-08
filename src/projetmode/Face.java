package projetmode;

public class Face {
	private Point[] points;

	public Face(Point[] points) {
		this.points = points;
	}

	public Point[] getPoints() {
		return points;
	}

	public void translate(Matrix columnMatrix) {
		for (int i = 0; i < points.length; i++) {
			points[i].translate(columnMatrix);
		}
	}

	public void scale(Matrix columnMatrix) {
		for (int i = 0; i < points.length; i++) {
			points[i].scale(columnMatrix);
		}
	}

	public void rotate(Axis axis, double angleInDegrees) {
		for (int i = 0; i < points.length; i++) {
			points[i].rotate(axis, angleInDegrees);
		}
	}
	
	public double getGreaterZ() {
		double greaterZ = getPoints()[0].getZ();
		for(int i = 1; i < getPoints().length; i++) {
			if(getPoints()[i].getZ() > greaterZ) {
				greaterZ = getPoints()[i].getZ();
			}
		}
		return greaterZ;
	}
	
	/**
	 * @author Groupe K5
	 * Renvoie la valeur X du point contenu dans le tableau de Point "points"
	 * pour lequel la valeur X est la plus petite de tout les points du tableau.
	 * @return double min
	 * */
	public double getXMin(){
		double min=points[0].getX();
		for (int i = 1 ; i < points.length ; i++){
			if (points[i].getX()<min)
				min = points[i].getX();
		}
		return min;
	}
	
	/**
	 * @author Groupe K5
	 * Renvoie la valeur X du point contenu dans le tableau de Point "points"
	 * pour lequel la valeur X est la plus grande de tout les points du tableau.
	 * @return double max
	 * */
	public double getXMax(){
		double max=points[0].getX();
		for (int i = 1 ; i < points.length ; i++){
			if (points[i].getX()>max)
				max = points[i].getX();
		}
		return max;
	}
	
	/**
	 * @author Groupe K5
	 * Renvoie la valeur Y du point contenu dans le tableau de Point "points"
	 * pour lequel la valeur Y est la plus petite de tout les points du tableau.
	 * @return double min
	 * */
	public double getYMin(){
		double min=points[0].getY();
		for (int i = 1 ; i < points.length ; i++){
			if (points[i].getY()<min)
				min = points[i].getY();
		}
		return min;
	}
	
	/**
	 * @author Groupe K5
	 * Renvoie la valeur Y du point contenu dans le tableau de Point "points"
	 * pour lequel la valeur Y est la plus grande de tout les points du tableau.
	 * @return double max
	 * */
	public double getYMax(){
		double max=points[0].getY();
		for (int i = 1 ; i < points.length ; i++){
			if (points[i].getY()>max)
				max = points[i].getY();
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
