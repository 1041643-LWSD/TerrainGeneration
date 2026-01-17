public class NoiseMapRunner {
	public static void main(String[] args) {
		
		StdDraw.enableDoubleBuffering();
		for(int step = 0; step < 1000; step ++) {
			double i = step/1000.0;
			NoiseMap map = new NoiseMap(200, i);
			map.drawNoiseMap();
			System.out.println("Drew map with gradient " + i);
			StdDraw.show();
		}
		
		/*
		StdDraw.enableDoubleBuffering();
		NoiseMap map = new NoiseMap(200, .1);
		map.drawNoiseMap();
		StdDraw.show();
		*/
	}
}