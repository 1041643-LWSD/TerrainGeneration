public class Ray {
	private double[] directionVector;
	private double x;
	private double y;
	private double z;
	
	public Ray(double x, double y, double z, double xDir, double yDir) {
		directionVector = new double[]{Math.cos(xDir), Math.cos(yDir), Math.sqrt(1 - (x*x + y*y))};
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public boolean collidingWithBox(double xMin, double xMax, double yMin, double yMax, double zMin, double zMax) {
		double t = (xMin - x) / directionVector[0];
		Point point = new Point(x + directionVector[0] * t, y + directionVector[1] * t, z + directionVector[2] * t);
		if (point.inBox(xMin, xMax, yMin, yMax, zMin, zMax)) {
			return true;
		}
		t = (xMax - x) / directionVector[0];
		point = new Point(x + directionVector[0] * t, y + directionVector[1] * t, z + directionVector[2] * t);
		if (point.inBox(xMin, xMax, yMin, yMax, zMin, zMax)) {
			return true;
		}
		t = (yMin - y) / directionVector[1];
		point = new Point(x + directionVector[0] * t, y + directionVector[1] * t, z + directionVector[2] * t);
		if (point.inBox(xMin, xMax, yMin, yMax, zMin, zMax)) {
			return true;
		}
		t = (yMax - y) / directionVector[1];
		point = new Point(x + directionVector[0] * t, y + directionVector[1] * t, z + directionVector[2] * t);
		if (point.inBox(xMin, xMax, yMin, yMax, zMin, zMax)) {
			return true;
		}
		t = (zMin - z) / directionVector[2];
		point = new Point(x + directionVector[0] * t, y + directionVector[1] * t, z + directionVector[2] * t);
		if (point.inBox(xMin, xMax, yMin, yMax, zMin, zMax)) {
			return true;
		}
		t = (zMax - z) / directionVector[2];
		point = new Point(x + directionVector[0] * t, y + directionVector[1] * t, z + directionVector[2] * t);
		if (point.inBox(xMin, xMax, yMin, yMax, zMin, zMax)) {
			return true;
		}
		return false;
	}
}