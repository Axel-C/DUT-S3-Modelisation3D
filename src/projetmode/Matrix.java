package projetmode;

/**
 * Cette classe repr�sente une matrice M x N contenant des double.
 * 
 * @author Groupe K5
 *
 */
public class Matrix {
	private double[][] table;

	/**
	 * Instancie une matrice nulle de taille M x N.
	 * 
	 * @param m
	 *            Le nombre de lignes de la matrice.
	 * @param n
	 *            Le nombre de colonnes de la matrice.
	 */
	public Matrix(int m, int n) {
		table = new double[m][n];
	}

	/**
	 * Instancie une matrice � partir du tableau de double pass� en param�tre.
	 * 
	 * @param table
	 *            Le tableau de double.
	 */
	public Matrix(double[][] table) {
		this.table = table;
	}

	/**
	 * Retourne le nombre de lignes de cette matrice.
	 * 
	 * @return Le nombre de lignes de cette matrice.
	 */
	public int getM() {
		return table.length;
	}

	/**
	 * Retourne le nombre colonnes de cette matrice.
	 * 
	 * @return Le nombre de colonnes de cette matrice.
	 */
	public int getN() {
		return table[0].length;
	}

	/**
	 * Retourne l'�l�ment � la position i,j de cette matrice.
	 * 
	 * @param i
	 *            L'indice de ligne.
	 * @param j
	 *            L'indice de colonne.
	 * @return
	 */
	public double getElement(int i, int j) {
		return table[i][j];
	}

	/**
	 * Modifie l'�l�ment � la position i,j par l'�l�ment pass� en param�tre de
	 * cette matrice.
	 * 
	 * @param i
	 *            L'indice de ligne
	 * @param j
	 *            L'indice de colonne.
	 * @param element
	 *            Le nouvel �l�ment.
	 */
	public void setElement(int i, int j, double element) {
		table[i][j] = element;
	}

	/**
	 * Renvoie la matrice produit de cette matrice par la matrice pass�e en
	 * param�tre.
	 * 
	 * @param matrix
	 *            La matrice � multiplier.
	 * @return La matrice produit de cette matrice par la matrice � multiplier.
	 */
	public Matrix multiply(Matrix matrix) {
		return Matrix.getMatrixProduct(this, matrix);
	}

	/**
	 * Renvoie la matrice produit des matrices A et B pass�es en param�tres.
	 * 
	 * @param matrixA
	 *            La matrice A.
	 * @param matrixB
	 *            La matrice B.
	 * @return La matrice produit de la matrice A par la matrice B.
	 */
	public static Matrix getMatrixProduct(Matrix matrixA, Matrix matrixB) {
		Matrix matrixProduct = new Matrix(matrixA.getM(), matrixB.getN());
		for (int i = 0; i < matrixProduct.getM(); i++) {
			for (int j = 0; j < matrixProduct.getN(); j++) {
				for (int k = 0; k < matrixProduct.getM(); k++) {
					matrixProduct.setElement(i, j,
							matrixProduct.getElement(i, j) + (matrixA.getElement(i, k) * matrixB.getElement(k, j)));
				}
			}
		}
		return matrixProduct;
	}

	/**
	 * Renvoie la matrice de translation du vecteur pass� en param�tre.
	 * 
	 * @param columnMatrix
	 *            Le vecteur.
	 * @return La matrice translation.
	 */
	public static Matrix getTranslationMatrix(Matrix columnMatrix) {
		return new Matrix(
				new double[][] { { 1, 0, 0, columnMatrix.getElement(0, 0) }, { 0, 1, 0, columnMatrix.getElement(1, 0) },
						{ 0, 0, 1, columnMatrix.getElement(2, 0) }, { 0, 0, 0, 1 } });
	}

	/**
	 * Renvoie la matrice de changement d'�chelle du vecteur pass� en param�tre.
	 * 
	 * @param columnMatrix
	 *            Le vecteur.
	 * @return La matrice de changement d'�chelle.
	 */
	public static Matrix getScalingMatrix(Matrix columnMatrix) {
		return new Matrix(
				new double[][] { { columnMatrix.getElement(0, 0), 0, 0, 0 }, { 0, columnMatrix.getElement(1, 0), 0, 0 },
						{ 0, 0, columnMatrix.getElement(2, 0), 0 }, { 0, 0, 0, 1 } });
	}

	/**
	 * Renvoie la matrice de rotation de l'axe et de l'angle en degr�s pass� en
	 * param�tre.
	 * 
	 * @param axis
	 *            L'axe de rotation
	 * @param angleInDegrees
	 *            L'angle en degr�s.
	 * @return La matrice de rotation.
	 */
	public static Matrix getRotationMatrix(Axis axis, double angleInDegrees) {
		double angleInRadians = angleInDegrees * (Math.PI / 180);
		Matrix rotationMatrix;
		if (axis.equals(Axis.X)) {
			rotationMatrix = new Matrix(
					new double[][] { { 1, 0, 0, 0 }, { 0, Math.cos(angleInRadians), -Math.sin(angleInRadians), 0 },
							{ 0, Math.sin(angleInRadians), Math.cos(angleInRadians), 0 }, { 0, 0, 0, 1 } });
		} else if (axis.equals(Axis.Y)) {
			rotationMatrix = new Matrix(new double[][] { { Math.cos(angleInRadians), 0, Math.sin(angleInRadians), 0 },
					{ 0, 1, 0, 0 }, { -Math.sin(angleInRadians), 0, Math.cos(angleInRadians), 0 }, { 0, 0, 0, 1 } });
		} else {
			rotationMatrix = new Matrix(new double[][] { { Math.cos(angleInRadians), -Math.sin(angleInRadians), 0, 0 },
					{ Math.sin(angleInRadians), Math.cos(angleInRadians), 0, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 1 } });
		}
		return rotationMatrix;
	}

	@Override
	public String toString() {
		String string = "";
		for (int i = 0; i < getM(); i++) {
			string += "(" + table[i][0];
			for (int j = 1; j < getN(); j++) {
				string += ", " + table[i][j];
			}
			string += ") \n";
		}
		return string;
	}
}
