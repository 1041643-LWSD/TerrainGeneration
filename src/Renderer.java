
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

	}
	
	public void castRay(double x, double y, double xDir, double yDir) {
		Ray ray = new Ray(x, y, z, xDir, yDir);
		for(int i = 0; i < terrain.getResolution(); i++) {
			for(int j = 0; j < terrain.getResolution(); j++) {
				double boxHeight = terrain.getHeightAt(i, j);
				if(ray.collidingWithBox(i, i + 1, j, j + 1, boxHeight, boxHeight + 1)) {
					
				}
			}
		}
	}
}