package projetmode;


public class Point {
	private double x, y, z;

	public Point(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}
 
	public void translate(Matrix columnMatrix) {
		/* Matrix columnMatrixProduct = Matrix.getMatrixProduct(Matrix.getTranslationMatrix(columnMatrix), new Matrix(new double[][] {
			{x}, {y}, {z}, {1}} ));
		x = columnMatrixProduct.getElement(0, 0);
		y = columnMatrixProduct.getElement(1, 0);
		z = columnMatrixProduct.getElement(2, 0); */
		x += columnMatrix.getElement(0, 0);
		y += columnMatrix.getElement(1, 0);
		z += columnMatrix.getElement(2, 0);
	}
	
	public void scale(Matrix columnMatrix) {
		/* Matrix columnMatrixProduct = Matrix.getMatrixProduct(Matrix.getScalingMatrix(columnMatrix), new Matrix(new double[][] {
			{x}, {y}, {z}, {1}} ));
		x = columnMatrixProduct.getElement(0, 0);
		y = columnMatrixProduct.getElement(1, 0);
		z = columnMatrixProduct.getElement(2, 0); */
		x *= columnMatrix.getElement(0, 0);
		y *= columnMatrix.getElement(1, 0);
		z *= columnMatrix.getElement(2, 0);
	}
	
	public void rotate(Axis axis, double angleInDegrees) {
		Matrix columnMatrixProduct = Matrix.getMatrixProduct(Matrix.getRotationMatrix(axis, angleInDegrees), new Matrix(new double[][] {
			{x}, {y}, {z}, {1}} ));
		x = columnMatrixProduct.getElement(0, 0);
		y = columnMatrixProduct.getElement(1, 0);
		z = columnMatrixProduct.getElement(2, 0);
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ", " + z + ")";
	}
}
