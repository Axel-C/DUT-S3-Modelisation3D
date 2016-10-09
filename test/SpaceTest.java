package test;
import src.*;

import java.io.File;
import org.junit.Test;

public class SpaceTest {
	File plyFile= new File("livrable1/data/test_file1.ply");
	Model m= new Model(plyFile);
	
	@Test
	public void testConstructFromPlyFile() {
		
	}
}
