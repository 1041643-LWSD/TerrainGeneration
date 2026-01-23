public class NoiseMapRunner {
	public static void main(String[] args) {
		Renderer renderer = new Renderer(new NoiseMap(200, .5));
		while (true) { 					
			if(StdDraw.hasNextKeyTyped()) {
				char key = StdDraw.nextKeyTyped();
				if(key == 'w') {
					renderer.moveUp(1);
				}
				if(key == 's') {
					renderer.moveDown(1);
				}
				if(key == 'a') {
					renderer.moveLeft(1);
				}
				if(key == 'd') {
					renderer.moveRight(1);
				}
				if(key == 'i') {
					renderer.turnUp(5);
				}
				if(key == 'k') {
					renderer.turnDown(5);
				}
				if(key == 'j') {
					renderer.turnLeft(5);
				}
				if(key == 'l') {
					renderer.turnRight(5);
				}
			}
			renderer.updateScreen();
			renderer.renderScreen();
		}


		/*
		StdDraw.enableDoubleBuffering();
		for(int step = 0; step < 1000; step ++) {
			double i = step/1000.0;
			NoiseMap map = new NoiseMap(200, i);
			map.drawNoiseMap();
			System.out.println("Drew map with gradient " + i);
			StdDraw.show();
		}
		*/
		
		/*
		StdDraw.enableDoubleBuffering();
		NoiseMap map = new NoiseMap(200, .1);
		map.drawNoiseMap();
		StdDraw.show();
		*/
	}
}