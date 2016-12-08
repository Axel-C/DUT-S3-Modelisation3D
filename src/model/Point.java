package model;

/**
 * Cette classes represente un point 3D ayant pour coordonnees X,Y et Z.
 *
 * @author Groupe K5
 */
public class Point {
	private double x, y, z;

	/**
	 * Instancie un point a partir des coordonnees X,Y et Z passes en
	 * parametres.
	 * 
	 * @param x
	 *            La coordonnee X.
	 * @param y
	 *            La coordonnee Y.
	 * @param z
	 *            La coordonnee Z.
	 */
	public Point(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * @return La coordonnee X de ce point.
	 */
	public double getX() {
		return x;
	}

	/**
	 * @return La coordonnee Y de ce point.
	 */
	public double getY() {
		return y;
	}

	/**
	 * @return La coordonnee Z de ce point.
	 */
	public double getZ() {
		return z;
	}

		
	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setZ(double z) {
		this.z = z;
	}

	/**
	 * Translate ce point sur la matrice colonne passee en parametre.
	 * 
	 * @param columnMatrix
	 *            La matrice colonne sur laquelle ce point translatera.
	 */
	public void translate(Matrix columnMatrix) {
		/*
		 * Matrix columnMatrixProduct =
		 * Matrix.getMatrixProduct(Matrix.getTranslationMatrix(columnMatrix),
		 * new Matrix(new double[][] { {x}, {y}, {z}, {1}} )); x =
		 * columnMatrixProduct.getElement(0, 0); y =
		 * columnMatrixProduct.getElement(1, 0); z =
		 * columnMatrixProduct.getElement(2, 0);
		 */
		x += columnMatrix.getElement(0, 0);
		y += columnMatrix.getElement(1, 0);
		z += columnMatrix.getElement(2, 0);
	}

	/**
	 * Change l'echelle de ce point sur la matrice colonne passee en parametre.
	 * 
	 * @param columnMatrix
	 *            La matrice colonne sur laquelle ce point changera d'echelle.
	 */
	public void scale(Matrix columnMatrix) {
		/*
		 * Matrix columnMatrixProduct =
		 * Matrix.getMatrixProduct(Matrix.getScalingMatrix(columnMatrix), new
		 * Matrix(new double[][] { {x}, {y}, {z}, {1}} )); x =
		 * columnMatrixProduct.getElement(0, 0); y =
		 * columnMatrixProduct.getElement(1, 0); z =
		 * columnMatrixProduct.getElement(2, 0);
		 */
		x *= columnMatrix.getElement(0, 0);
		y *= columnMatrix.getElement(1, 0);
		z *= columnMatrix.getElement(2, 0);
	}

	/**
	 * Fait tourner le point autour de l'axe d'un angle en degres passes en
	 * parametres.
	 * 
	 * @param axis
	 *            L'axe autour duquel le point tournera.
	 * @param angleInDegrees
	 *            L'angle en degres de rotation autour de l'axe passe en
	 *            parametre.
	 */
	public void rotate(Axis axis, double angleInDegrees) {
		Matrix columnMatrixProduct = Matrix.getMatrixProduct(Matrix.getRotationMatrix(axis, angleInDegrees),
				new Matrix(new double[][] { { x }, { y }, { z }, { 1 } }));
		x = columnMatrixProduct.getElement(0, 0);
		y = columnMatrixProduct.getElement(1, 0);
		z = columnMatrixProduct.getElement(2, 0);
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ", " + z + ")";
	}
}
