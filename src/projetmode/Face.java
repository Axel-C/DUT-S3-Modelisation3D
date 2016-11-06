package projetmode;

public class Face {
	private Point[] points;

	public Face(Point[] points) {
		this.points = points;
	}

	public Point[] getPoints() {
		return points;
	}

	public void translate(Matrix columnMatrix) {
		for (int i = 0; i < points.length; i++) {
			points[i].translate(columnMatrix);
		}
	}

	public void scale(Matrix columnMatrix) {
		for (int i = 0; i < points.length; i++) {
			points[i].scale(columnMatrix);
		}
	}

	public void rotate(Axis axis, double angleInDegrees) {
		for (int i = 0; i < points.length; i++) {
			points[i].rotate(axis, angleInDegrees);
		}
	}
	
	public double getGreaterZ() {
		double greaterZ = getPoints()[0].getZ();
		for(int i = 1; i < getPoints().length; i++) {
			if(getPoints()[i].getZ() > greaterZ) {
				greaterZ = getPoints()[i].getZ();
			}
		}
		return greaterZ;
	}

	@Override
	public String toString() {
		String string = "{" + points[0];
		for (int i = 1; i < points.length; i++) {
			string += ", " + points[i];
		}
		string += "}";
		return string;
	}
}
