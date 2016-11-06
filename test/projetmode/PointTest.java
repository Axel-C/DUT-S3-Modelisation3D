package projetmode;

import static org.junit.Assert.*;

import org.junit.Test;

public class PointTest {

	@Test
	public void testTranslate() {
		Point actualPoint = new Point(5, 10, 20);
		Matrix columnMatrix = new Matrix(new double[][] {
			{5}, {20}, {5}, {1}});
		Point expectedPoint = new Point(actualPoint.getX() + columnMatrix.getElement(0, 0), actualPoint.getY() + columnMatrix.getElement(1, 0),
				actualPoint.getZ() + columnMatrix.getElement(2, 0));
		actualPoint.translate(columnMatrix);
		assertEquals(expectedPoint.getX(), actualPoint.getX(), 0.1);
		assertEquals(expectedPoint.getY(), actualPoint.getY(), 0.1);
		assertEquals(expectedPoint.getZ(), actualPoint.getZ(), 0.1);
	}

	@Test
	public void testScale() {
		Point actualPoint = new Point(5, 10, 20);
		Matrix columnMatrix = new Matrix(new double[][] {
			{5}, {20}, {5}, {1}});
		Point expectedPoint = new Point(actualPoint.getX() * columnMatrix.getElement(0, 0), actualPoint.getY() * columnMatrix.getElement(1, 0),
				actualPoint.getZ() * columnMatrix.getElement(2, 0));
		actualPoint.scale(columnMatrix);
		assertEquals(expectedPoint.getX(), actualPoint.getX(), 0.1);
		assertEquals(expectedPoint.getY(), actualPoint.getY(), 0.1);
		assertEquals(expectedPoint.getZ(), actualPoint.getZ(), 0.1);
	}

}
