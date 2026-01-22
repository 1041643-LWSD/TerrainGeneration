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
	
	public double collidingWithBox(double xMin, double xMax, double yMin, double yMax, double zMin, double zMax) {
		double t = (xMin - x) / directionVector[0];
		Point point = new Point(x + directionVector[0] * t, y + directionVector[1] * t, z + directionVector[2] * t);
		if (point.inBox(xMin, xMax, yMin, yMax, zMin, zMax)) {
			if(t > 0) {
				return t;
			}
		}
		t = (xMax - x) / directionVector[0];
		point = new Point(x + directionVector[0] * t, y + directionVector[1] * t, z + directionVector[2] * t);
		if (point.inBox(xMin, xMax, yMin, yMax, zMin, zMax)) {
			if(t > 0) {
				return t;
			}
		}
		t = (yMin - y) / directionVector[1];
		point = new Point(x + directionVector[0] * t, y + directionVector[1] * t, z + directionVector[2] * t);
		if (point.inBox(xMin, xMax, yMin, yMax, zMin, zMax)) {
			if(t > 0) {
				return t;
			}
		}
		t = (yMax - y) / directionVector[1];
		point = new Point(x + directionVector[0] * t, y + directionVector[1] * t, z + directionVector[2] * t);
		if (point.inBox(xMin, xMax, yMin, yMax, zMin, zMax)) {
			if(t > 0) {
				return t;
			}
		}
		t = (zMin - z) / directionVector[2];
		point = new Point(x + directionVector[0] * t, y + directionVector[1] * t, z + directionVector[2] * t);
		if (point.inBox(xMin, xMax, yMin, yMax, zMin, zMax)) {
			if(t > 0) {
				return t;
			}
		}
		t = (zMax - z) / directionVector[2];
		point = new Point(x + directionVector[0] * t, y + directionVector[1] * t, z + directionVector[2] * t);
		if (point.inBox(xMin, xMax, yMin, yMax, zMin, zMax)) {
			if(t > 0) {
				return t;
			}
		}
		return -1;
	}
	
	public double getDistance(double t) {
		double x1 = x + directionVector[0] * t;
		double y1 = y + directionVector[1] * t;
		double z1 = z + directionVector[2] * t;
		return Math.sqrt((x - x1) * (x - x1) + 
				(y - y1) * (y - y1) +
				(z - z1) * (z - z1));
	}
}