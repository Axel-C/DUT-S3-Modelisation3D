package projetmode;

/**
 * Cette classes repr�sente un point 3D ayant pour coordonn�es X,Y et Z.
 *
 * @author Groupe K5
 */
public class Point {
	private double x, y, z;

	/**
	 * Instancie un point � partir des coordonn�es X,Y et Z pass�s en
	 * param�tres.
	 * 
	 * @param x
	 *            La coordonn�e X.
	 * @param y
	 *            La coordonn�e Y.
	 * @param z
	 *            La coordonn�e Z.
	 */
	public Point(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * @return La coordonn�e X de ce point.
	 */
	public double getX() {
		return x;
	}

	/**
	 * @return La coordonn�e Y de ce point.
	 */
	public double getY() {
		return y;
	}

	/**
	 * @return La coordonn�e Z de ce point.
	 */
	public double getZ() {
		return z;
	}

	/**
	 * Translate ce point sur la matrice colonne pass�e en param�tre.
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
	 * Change l'�chelle de ce point sur la matrice colonne pass�e en param�tre.
	 * 
	 * @param columnMatrix
	 *            La matrice colonne sur laquelle ce point changera d'�chelle.
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
	 * Fait tourner le point autour de l'axe d'un angle en degr�s pass�s en
	 * param�tres.
	 * 
	 * @param axis
	 *            L'axe autour duquel le point tournera.
	 * @param angleInDegrees
	 *            L'angle en degr�s de rotation autour de l'axe pass� en
	 *            param�tre.
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
