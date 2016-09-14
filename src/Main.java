import java.io.File;

public class Main {
	private static MyJFrame jFrame;
	public static void main(String[] args) {
		jFrame = new MyJFrame(new ThreeDimensionalSpace(new File("livrable1/data/test_file1.ply")));
		for(int i = 0; i < jFrame.getThreeDimensionalSpace().vertices.length; i++) {
			System.out.println(jFrame.getThreeDimensionalSpace().vertices[i]);
		}
		
		for(int i = 0; i < jFrame.getThreeDimensionalSpace().faces.length; i++) {
			System.out.println(jFrame.getThreeDimensionalSpace().faces[i]);
		}
	}
}
