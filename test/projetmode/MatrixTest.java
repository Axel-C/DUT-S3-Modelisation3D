package projetmode;

import static org.junit.Assert.assertEquals;
import model.*;

import java.util.Random;

import org.junit.Test;

public class MatrixTest {
	Random random = new Random();

	@Test
	public void testGetMatrixProduct() {
		Matrix matrixA = new Matrix(new double[][] { { 2, 3 }, { 4, 4 } });
		Matrix matrixB = new Matrix(new double[][] { { 2 }, { 1 } });
		Matrix actualMatrix = Matrix.getMatrixProduct(matrixA, matrixB);
		Matrix expectedMatrix = new Matrix(new double[][] { { 7 }, { 12 } });
		for (int i = 0; i < actualMatrix.getM(); i++) {
			for (int j = 0; j < actualMatrix.getN(); j++) {
				assertEquals(expectedMatrix.getElement(i, j), actualMatrix.getElement(i, j), 0.001);
			}
		}

	}

	@Test
	public void testTranslationMatrix() {
		Matrix actualMatrix = Matrix.getTranslationMatrix(new Matrix(new double[][] { { 2 }, { 2 }, { 3 }, { 1 } }));
		Matrix expectedMatrix = new Matrix(
				new double[][] { { 1, 0, 0, 2 }, { 0, 1, 0, 2 }, { 0, 0, 1, 3 }, { 0, 0, 0, 1 } });
		for (int i = 0; i < actualMatrix.getM(); i++) {
			for (int j = 0; j < actualMatrix.getN(); j++) {
				assertEquals(expectedMatrix.getElement(i, j), actualMatrix.getElement(i, j), 0.1);
			}
		}
	}

	@Test
	public void testScalingMatrix() {
		Matrix actualMatrix = Matrix.getScalingMatrix(new Matrix(new double[][] { { 4 }, { 2 }, { 7 }, { 1 } }));
		Matrix expectedMatrix = new Matrix(
				new double[][] { { 4, 0, 0, 0 }, { 0, 2, 0, 0 }, { 0, 0, 7, 0 }, { 0, 0, 0, 1 } });
		for (int i = 0; i < actualMatrix.getM(); i++) {
			for (int j = 0; j < actualMatrix.getN(); j++) {
				assertEquals(expectedMatrix.getElement(i, j), actualMatrix.getElement(i, j), 0.1);
			}
		}
	}

	@Test
	public void testXRotationMatrix() {
		double angleInDegrees = random.nextDouble() * 360;
		double angleInRadians = angleInDegrees * (Math.PI / 180);
		Matrix actualMatrix = Matrix.getRotationMatrix(Axis.X, angleInDegrees);
		Matrix expectedMatrix = new Matrix(
				(new double[][] { { 1, 0, 0, 0 }, { 0, Math.cos(angleInRadians), -Math.sin(angleInRadians), 0 },
						{ 0, Math.sin(angleInRadians), Math.cos(angleInRadians), 0 }, { 0, 0, 0, 1 } }));
		for (int i = 0; i < actualMatrix.getM(); i++) {
			for (int j = 0; j < actualMatrix.getN(); j++) {
				assertEquals(expectedMatrix.getElement(i, j), actualMatrix.getElement(i, j), 0.1);
			}
		}
	}

	@Test
	public void testYRotationMatrix() {
		double angleInDegrees = random.nextDouble() * 360;
		double angleInRadians = angleInDegrees * (Math.PI / 180);
		Matrix actualMatrix = Matrix.getRotationMatrix(Axis.Y, angleInDegrees);
		Matrix expectedMatrix = new Matrix(
				new double[][] { { Math.cos(angleInRadians), 0, Math.sin(angleInRadians), 0 }, { 0, 1, 0, 0 },
						{ -Math.sin(angleInRadians), 0, Math.cos(angleInRadians), 0 }, { 0, 0, 0, 1 } });
		for (int i = 0; i < actualMatrix.getM(); i++) {
			for (int j = 0; j < actualMatrix.getN(); j++) {
				assertEquals(expectedMatrix.getElement(i, j), actualMatrix.getElement(i, j), 0.1);
			}
		}
	}

	@Test
	public void testZRotationMatrix() {
		double angleInDegrees = random.nextDouble() * 360;
		double angleInRadians = angleInDegrees * (Math.PI / 180);
		Matrix actualMatrix = Matrix.getRotationMatrix(Axis.Z, angleInDegrees);
		Matrix expectedMatrix = new Matrix(
				new double[][] { { Math.cos(angleInRadians), -Math.sin(angleInRadians), 0, 0 },
						{ Math.sin(angleInRadians), Math.cos(angleInRadians), 0, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 1 } });
		for (int i = 0; i < actualMatrix.getM(); i++) {
			for (int j = 0; j < actualMatrix.getN(); j++) {
				assertEquals(expectedMatrix.getElement(i, j), actualMatrix.getElement(i, j), 0.1);
			}
		}
	}

}
