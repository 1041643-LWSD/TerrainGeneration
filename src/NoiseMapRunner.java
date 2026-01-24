public class NoiseMapRunner {
	public static void main(String[] args) {
		Renderer renderer = new Renderer(new NoiseMap(200, .5));
		double moveSpeed = .1;
		double turnSpeed = 2;
		boolean onMap = false;
		boolean debug = false;
		while (true) { 					
			if(StdDraw.hasNextKeyTyped()) {
				char key = StdDraw.nextKeyTyped();
				if(key == 'q') {
					renderer.moveUp(moveSpeed);
				}
				if(key == 'e') {
					renderer.moveDown(moveSpeed);
				}
				if(key == 'd') {
					renderer.moveLeft(moveSpeed);
				}
				if(key == 'a') {
					renderer.moveRight(moveSpeed);
				}
				if(key == 'i') {
					renderer.turnUp(turnSpeed);
				}
				if(key == 'k') {
					renderer.turnDown(turnSpeed);
				}
				if(key == 'j') {
					renderer.turnLeft(turnSpeed);
				}
				if(key == 'l') {
					renderer.turnRight(turnSpeed);
				}
				if(key == 'w') {
					renderer.moveForward(moveSpeed);
				}
				if(key == 's') {
					renderer.moveBackward(moveSpeed);
				}
				if(key == 'm') {
					onMap = !onMap;
				}
				if(key == 'p') {
					debug = !debug;
				}
				if(key == '1') {
					renderer.increaseResolution();
				} if(key == '2') {
					renderer.decreaseResolution();
				}
				if(key == 'z') {
					renderer.increaseFOV();
				}
				if(key == 'x') {
					renderer.decreaseFOV();
				}
			}
			renderer.updateScreen();
			renderer.renderScreen(onMap);
			if(debug) {
				System.out.println("X: " + renderer.getX() + " Y: " + renderer.getY() + " Z: " + renderer.getZ() + "xDir: " + renderer.getXDir() + " yDir: " + renderer.getYDir());
			}
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