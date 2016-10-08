import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SpaceTest {
	File plyFile= new File("livrable1/data/test_file1.ply");
	File plyFileText= new File("livrable1/data/test_file1.txt");
	Space s=new Space(new Model(plyFile),3);
	
	private boolean ouvrable(File file) {
		String ext="";
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
	
	@Test
	public void testVertex() {
		
	}
	
	public void testFaces() {
		
	}

}
