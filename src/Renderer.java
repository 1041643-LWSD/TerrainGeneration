
public class Renderer {
	private NoiseMap terrain;
	private double xDir;
	private double yDir;
	private double x;
	private double y;
	private double z;
	private final double xRESOLUTION = 120;
	private final double yRESOLUTION = 120;
	private final double FOV = 120;
	private double[][] screen;
	
	public Renderer(NoiseMap terrain) {
		this.terrain = terrain;
		this.xDir = 0;
		this.yDir = 0;
		this.x = 0;
		this.y = 0;
		this.z = 0;	
		screen = new double[(int)xRESOLUTION][(int)yRESOLUTION];
	}

	public void moveUp(double distance) {
		this.z += distance;
	}

	public void moveDown(double distance) {
		 this.z -= distance;
	 }

	public void moveLeft(double distance) {
		this.x -= distance;
	}

	public void moveRight(double distance) {
		this.x += distance;
	}

	public void turnLeft(double angle) {
		this.xDir -= angle;
	}

	public void turnRight(double angle) {
		this.xDir += angle;
	}

	public void turnUp(double angle) {
		this.yDir += angle;
	}

	public void turnDown(double angle) {
		this.yDir -= angle;
	}

	public void renderScreen() {
		StdDraw.enableDoubleBuffering();
		for(int i = 0; i < xRESOLUTION; i++) {
			for(int j = 0; j < yRESOLUTION; j++) {
				int shade = (int)(255 * screen[i][j]);
				if(shade > 255) {
					shade = 255;
				}
				StdDraw.setPenColor(shade, shade, shade);
				StdDraw.setPenRadius(1.0 / xRESOLUTION);
				StdDraw.point((double)i/xRESOLUTION + (double)1/(xRESOLUTION * 2), (double)j/yRESOLUTION + (double)1/(yRESOLUTION * 2));
			}
		}
		StdDraw.show();
	}
	
	public void updateScreen() {
		for(int i = (int) -xRESOLUTION/2; i < (int) xRESOLUTION/2; i++) {
			for(int j = (int) -yRESOLUTION/2; j < (int) yRESOLUTION/2; j++) {
				double distance = castRay((FOV * i/xRESOLUTION + xDir), (FOV * j/yRESOLUTION + yDir));
				if (distance == Double.POSITIVE_INFINITY || distance <= 0) {
					screen[i + (int)xRESOLUTION/2][j + (int)yRESOLUTION/2] = 0;
				}
				else {
					screen[i + (int)xRESOLUTION/2][j + (int)yRESOLUTION/2] = 1.0 / distance;
				}
			}
		}
	}
	
	public double castRay(double xDirection, double yDirection) {
		Ray ray = new Ray(x, y, this.z, xDirection, yDirection);
		double minDistance = 100000;
		for(int i = 0; i < terrain.getResolution(); i++) {
			for(int j = 0; j < terrain.getResolution(); j++) {
				double t = ray.collidingWithBox(i, i + 1, j, j + 1, 0, terrain.getHeightAt(i, j));
				if(t != -1) {
					double distance = ray.getDistance(t);
					if(distance < minDistance) {
						minDistance = distance;
					}
				}
			}
		}
		return minDistance;
	}
}