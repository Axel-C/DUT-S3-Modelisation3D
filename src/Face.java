import java.util.Arrays;

public class Face {
	private Vertex[] vertices;

	public Face(Vertex[] vertices) {
		this.vertices = vertices;
	}

	public Vertex[] getVertices() {
		return vertices;
	}

	@Override
	public String toString() {
		return "Face [vertices=" + Arrays.toString(vertices) + "]";
	}

}
