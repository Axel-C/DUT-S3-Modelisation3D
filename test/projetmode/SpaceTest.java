package projetmode;

import java.io.File;

import org.junit.Test;

import projetmode.Model;

public class SpaceTest {
	File plyFile = new File("livrable1/data/test_file1.ply");
	Model m;

	@Test
	public void testConstructFromPlyFile() {
		try {
			m = new Model(plyFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
