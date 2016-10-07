import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SpaceTest {
	File plyFile= new File("livrable1/data/test_file1.ply");
	File plyFileTxt= new File("livrable1/data/test_file1.txt");
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
		assertFalse(ouvrable(plyFileTxt));
	}

}
