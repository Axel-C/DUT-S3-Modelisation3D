package projetmode;

/**
 * Cette classes représente un point 3D ayant pour coordonnées X,Y et Z.
 *
 * @author Groupe K5
 */
public class Point {
	private double x, y, z;

	/**
	 * Instancie un point à partir des coordonnées X,Y et Z passés en
	 * paramètres.
	 * 
	 * @param x
	 *            La coordonnée X.
	 * @param y
	 *            La coordonnée Y.
	 * @param z
	 *            La coordonnée Z.
	 */
	public Point(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * @return La coordonnée X de ce point.
	 */
	public double getX() {
		return x;
	}

	/**
	 * @return La coordonnée Y de ce point.
	 */
	public double getY() {
		return y;
	}

	/**
	 * @return La coordonnée Z de ce point.
	 */
	public double getZ() {
		return z;
	}

	/**
	 * Translate ce point sur la matrice colonne passée en paramètre.
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
	 * Change l'échelle de ce point sur la matrice colonne passée en paramètre.
	 * 
	 * @param columnMatrix
	 *            La matrice colonne sur laquelle ce point changera d'échelle.
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
	 * Fait tourner le point autour de l'axe d'un angle en degrés passés en
	 * paramètres.
	 * 
	 * @param axis
	 *            L'axe autour duquel le point tournera.
	 * @param angleInDegrees
	 *            L'angle en degrés de rotation autour de l'axe passé en
	 *            paramètre.
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
