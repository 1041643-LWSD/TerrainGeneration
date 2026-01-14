public class NoiseMapRunner {
	public static void main(String[] args) {
		NoiseMap map = new NoiseMap(100, .999);
		StdDraw.enableDoubleBuffering();
		map.drawNoiseMap();
		StdDraw.show();
		System.out.println("Drew the noise map!");
	}
}