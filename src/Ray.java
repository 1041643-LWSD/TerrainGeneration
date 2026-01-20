public class Ray {
	private double[] directionVector;
	private double x;
	private double y;
	private double z;
	
	public Ray(double x, double y, double z, double xDir, double yDir) {
		directionVector = new double[Math.cos(xDir), Math.cos(yDir), Math.sqrt(1 - (x*x + y*y))];
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public boolean collidingWithBox(double xMin, double xMax, double yMin, double yMax, double zMin, double zMax) {
		double t = (xMin - x)/directionVector[0];
		double point[] = new double[]
	}
}