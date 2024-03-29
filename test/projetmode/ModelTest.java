package projetmode;

import static org.junit.Assert.*;
import model.*;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ModelTest {
	File plyFile = new File("livrable1/data/apple.ply");
	File plyFileText = new File("livrable1/data/test_file1.txt");

	Point[] plyVertex = new Point[8];
	{
		plyVertex[0] = new Point(-1.0, -1.0, -1.0);
		plyVertex[1] = new Point(1.0, -1.0, -1.0);
		plyVertex[2] = new Point(1.0, 1.0, -1.0);
		plyVertex[3] = new Point(-1.0, 1.0, -1.0);
		plyVertex[4] = new Point(-1.0, -1.0, 1.0);
		plyVertex[5] = new Point(1.0, -1.0, 1.0);
		plyVertex[6] = new Point(1.0, 1.0, 1.0);
		plyVertex[7] = new Point(-1.0, 1.0, 1.0);

	}

	Face[] fa = new Face[6];
	{
		fa[0] = new Face(new Point[] { plyVertex[0], plyVertex[1], plyVertex[2], plyVertex[3] });
		fa[1] = new Face(new Point[] { plyVertex[5], plyVertex[4], plyVertex[7], plyVertex[6] });
		fa[2] = new Face(new Point[] { plyVertex[6], plyVertex[2], plyVertex[1], plyVertex[5] });
		fa[3] = new Face(new Point[] { plyVertex[3], plyVertex[7], plyVertex[4], plyVertex[0] });
		fa[4] = new Face(new Point[] { plyVertex[7], plyVertex[3], plyVertex[2], plyVertex[6] });
		fa[5] = new Face(new Point[] { plyVertex[5], plyVertex[1], plyVertex[0], plyVertex[4] });
	}

	private boolean ouvrable(File file) {
		String ext = "";
		if (file.getName().lastIndexOf(".") > 0) {
			ext = file.getName().substring(file.getName().lastIndexOf("."));
		}
		return ext.equals(".ply");
	}

	@Test
	public void testOuvrable() {
		assertTrue(ouvrable(plyFile));
		assertFalse(ouvrable(plyFileText));
	}

	/*@Test
	public void testBarycentre() throws Exception {
		Model m= new Model(plyFile);
		Point expected= new Point(0.0,0.0,0.0);
		//System.out.println(m.getPoints().length);
		for(int i=0; i<m.getPoints().length; i++) {
			expected.setX(expected.getX()+ m.getPoints()[i].getX());
			expected.setY(expected.getY()+ m.getPoints()[i].getY());
			expected.setZ(expected.getZ()+ m.getPoints()[i].getZ());
		}
		Point actual = m.barycentre();
		System.out.println(expected + " ," + actual);
		assertEquals(expected.getX(),actual.getX(),0.0);
		assertEquals(expected.getY(),actual.getY(),0.0);
		assertEquals(expected.getZ(),actual.getZ(),0.0);
	}*/

	/*	
	 * @Test public void testVertex() { try { m = new Model(plyFile); } catch
	 * (Exception e) { e.printStackTrace(); } for (int i = 0; i <
	 * plyVertex.length; i++) { assertEquals(plyVertex[i].getX(),
	 * m.getVertices()[i].getX(), 0.1); assertEquals(plyVertex[i].getY(),
	 * m.getVertices()[i].getY(), 0.1); assertEquals(plyVertex[i].getZ(),
	 * m.getVertices()[i].getZ(), 0.1); }
	 * 
	 * }
	 */

	/*
	 * @Test public void testFace() { try { m = new Model(plyFile); } catch
	 * (Exception e) { e.printStackTrace(); } for (int i = 0; i < fa.length;
	 * i++) { for (int j = 0; j < fa[i].getVertices().length; j++) {
	 * assertEquals(fa[i].getVertices()[j].getX(),
	 * m.getFaces()[i].getVertices()[j].getX(), 0.1);
	 * assertEquals(fa[i].getVertices()[j].getY(),
	 * m.getFaces()[i].getVertices()[j].getY(), 0.1);
	 * assertEquals(fa[i].getVertices()[j].getZ(),
	 * m.getFaces()[i].getVertices()[j].getZ(), 0.1); } }
	 * 
	 * }
	 */
}