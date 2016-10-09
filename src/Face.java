import java.util.Arrays;

public class Face implements Comparable<Face>{
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
	
	public double getMaximumZ(){
		double max = vertices[0].getZ() ;
		 for(int i = 1 ; i < vertices.length ; i++){
			 if(vertices[i].getZ() > max ){
				 max = vertices[i].getZ();
			 }
		 }
		return max ;
	}

	@Override
	public int compareTo(Face face) {
		double z1 = this.getMaximumZ();
		double z2 = face.getMaximumZ();
		return (int) (z1 - z2) ;
	}
	

}
