
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

	public void renderScreen() {

	}
	
	public void updateScreen(double x, double y, double xDir, double yDir) {
		for(int i = (int) -xRESOLUTION/2; i < (int) xRESOLUTION/2; i++) {
			for(int j = (int) -yRESOLUTION/2; j < (int) xRESOLUTION/2; j++) {
				
			}
		}
	}
	
	public double castRay(double x, double y, double xDir, double yDir, int screenX, int screenY) {
		Ray ray = new Ray(x, y, z, xDir, yDir);
		double minDistance = 10
		for(int i = 0; i < terrain.getResolution(); i++) {
			for(int j = 0; j < terrain.getResolution(); j++) {
				double t = ray.collidingWithBox(i, i + 1, j, j + 1, 0, terrain.getHeightAt(i, j);
				if(t != -1) {
					if(minDistance = 0)
				}
			}
		}
	}
}