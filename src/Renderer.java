
public class Renderer {
	private NoiseMap terrain;
	private double xDir;
	private double yDir;
	private double x;
	private double y;
	private double z;
	private final double RESOLUTION = 120;
	private final double FOV = 120;
	
	public Renderer(NoiseMap terrain) {
		this.terrain = terrain;
		this.xDir = 0;
		this.yDir = 0;
		this.x = 0;
		this.y = 0;
		this.z = 0;	
	}
	
	public void render(double x, double y, double xDir, double yDir) {
		
	}
	
	public void castRay(double x, double y, double xDir, double yDir) {
		
	}
}
