package projetmode;

public class Matrix {
	private double[][] table;

	public Matrix(int m, int n) {
		table = new double[m][n];
	}

	public Matrix(double[][] table) {
		this.table = table;
	}

	public int getM() {
		return table.length;
	}

	public int getN() {
		return table[0].length;
	}

	public double getElement(int i, int j) {
		return table[i][j];
	}

	public void setElement(int i, int j, double element) {
		table[i][j] = element;
	}

	public Matrix multiply(Matrix matrix) {
		return Matrix.getMatrixProduct(this, matrix);
	}

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

	public static Matrix getTranslationMatrix(Matrix columnMatrix) {
		return new Matrix(new double[][] { { 1, 0, 0, columnMatrix.getElement(0, 0) }, { 0, 1, 0, columnMatrix.getElement(1, 0) },
				{ 0, 0, 1, columnMatrix.getElement(2, 0) }, { 0, 0, 0, 1 } });
	}

	public static Matrix getScalingMatrix(Matrix columnMatrix) {
		return new Matrix(new double[][] { { columnMatrix.getElement(0, 0), 0, 0, 0 }, { 0, columnMatrix.getElement(1, 0), 0, 0 },
				{ 0, 0, columnMatrix.getElement(2, 0), 0 }, { 0, 0, 0, 1 } });
	}

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
