public class NoiseMapRunner {
	public static void main(String[] args) {
		NoiseMap map = new NoiseMap(10, .9);
		map.drawNoiseMap();
		System.out.println("Drew the noise map!");
	}
}