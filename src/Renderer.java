
public class Renderer {
	private final NoiseMap terrain;
	private double xDir;
	private double yDir;
	private double x;
	private double y;
	private double z;
	private double xResolution = 120;
	private double yResolution = 120;
	private double fov = 120;
	private double[][] screen;
	private boolean fps = true;
	private long lastFrameTime = System.currentTimeMillis();
	private int fpsValue = 0;
	double heightFactor = 2.0;
	
	public Renderer(NoiseMap terrain) {
		this.terrain = terrain;
		this.xDir = 0;
		this.yDir = 0;
		this.x = 0;
		this.y = 0;
		this.z = terrain.getHeightAt((int) x, (int) y);
		screen = new double[(int)xResolution][(int)yResolution];
	}

	public boolean changeFPS() {
		this.fps = !this.fps;
		return this.fps;
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

	public void moveForward(double distance) {
		this.y += distance;
	}

	public void moveBackward(double distance) {
		this.y -= distance;
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
	
	public void setZ(double n) {
		this.z = n;
	}

	public void setX(double n) {
		this.x = n;
	}

	public void setY(double n) {
		this.y = n;
	}

	public void setXDir(double n) {
		this.xDir = n;
	}

	public void setYDir(double n) {
		this.yDir = n;
	}

	public void renderScreen(boolean onMap) {
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledSquare(.5, .5, .5);
		if(onMap) {
			drawMap();
		} else {
			StdDraw.enableDoubleBuffering();
			for(int i = 0; i < xResolution; i++) {
				for(int j = 0; j < yResolution; j++) {
					int shade = (int)(255 * screen[i][j]);
					if(shade > 255) {
						shade = 255;
					}
					StdDraw.setPenColor(shade, shade, shade);
					StdDraw.setPenRadius(1.0 / xResolution);
					StdDraw.filledSquare((double)i/xResolution + (double)1/(xResolution * 2), (double)j/yResolution + (double)1/(yResolution * 2), 1/xResolution);
				}
			}
			if(fps) {
				updateFPS();
				StdDraw.setPenColor(StdDraw.WHITE);
				StdDraw.textLeft(0.01, 0.98, "FPS: " + fpsValue);
			}
			StdDraw.show();
		}
	}
	
	public void updateScreen() {
		for(int i = (int) -xResolution/2; i < (int) xResolution/2; i++) {
			for(int j = (int) -yResolution/2; j < (int) yResolution/2; j++) {
				double distance = castRay((fov * i/xResolution + xDir), (fov * j/yResolution + yDir));
				if (distance == Double.POSITIVE_INFINITY || distance <= 0) {
					screen[i + (int)xResolution/2][j + (int)yResolution/2] = 1;
				}
				else {
					screen[i + (int)xResolution/2][j + (int)yResolution/2] = 1.0 / (distance);
				}
			}
		}
	}

	public double castRay(double yawDeg, double pitchDeg) {
		double yaw   = Math.toRadians(yawDeg);
		double pitch = Math.toRadians(pitchDeg);

		double dx = Math.cos(pitch) * Math.cos(yaw);
		double dy = Math.cos(pitch) * Math.sin(yaw);
		double dz = Math.sin(pitch);

		double step = 0.05;
		double maxDist = 100;

		double rx = x;
		double ry = y;
		double rz = z;

		for (double dist = 0; dist < maxDist; dist += step) {
			rx += dx * step;
			ry += dy * step;
			rz += dz * step;

			int gx = (int) rx;
			int gy = (int) ry;

			if (gx < 0 || gy < 0 ||
				gx >= terrain.getResolution() ||
				gy >= terrain.getResolution())
				return maxDist;

			if (rz <= terrain.getHeightAt(gx, gy) * heightFactor) {
				return dist;
			}
		}
		return maxDist;
	}

	public void drawMap() {
		StdDraw.enableDoubleBuffering();
		terrain.drawNoiseMap();
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.setPenRadius((double) 3/terrain.getResolution());
		StdDraw.point(x/terrain.getResolution(), y/terrain.getResolution());
		StdDraw.show();
	}

	public void setHeightFactor(double factor) {
		this.heightFactor = factor;
	}

	public double getXDir() {
		return xDir;
	}

	public double getYDir() {
		return yDir;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	public void changeResolution(int n) {
		this.xResolution += n;
		this.yResolution += n;
		if(this.xResolution < 10) {
			this.xResolution = 10;
		}
		if(this.yResolution < 10) {
			this.yResolution = 10;
		}
		screen = new double[(int)xResolution][(int)yResolution];
		System.out.println("Decreased resolution to " + xResolution + " x " + yResolution);
	}

	public void changeFOV(int n) {
		this.fov += n;
		if(this.fov < 10) {
			this.fov = 10;
		}
		System.out.println("Changed FOV to " + fov);
	}

	public void increaseFOV(double amount) {
		this.fov += amount;
	}

	public double getCurrentHeight() {
		int gridX = (int) x;
		int gridY = (int) y;
		if(gridX < 0 || gridY < 0 || gridX >= terrain.getResolution() || gridY >= terrain.getResolution()) {
			return 0;
		}
		return terrain.getHeightAt(gridX, gridY);
	}

	public void updateFPS() {
    long now = System.currentTimeMillis();
    long delta = now - lastFrameTime;

    if (delta > 0) {
        fpsValue = (int)(1000.0 / delta);
    }

    lastFrameTime = now;
}

}