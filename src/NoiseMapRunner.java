import java.awt.event.KeyEvent;

public class NoiseMapRunner {
	public static void main(String[] args) {
		Renderer renderer = new Renderer(new NoiseMap(200, .5));
		double moveSpeed = .1;
		double turnSpeed = 3;
		boolean onMap = false;
		boolean debug = false;
		double yVel = 0;
		double playerHeight = .6;
		double mouseX= StdDraw.mouseX();
		double mouseY = StdDraw.mouseY();
		double newMouseX = 0;
		double newMouseY = 0;
		double sensitivity = 150;
		StdDraw.enableDoubleBuffering();
		double heightFactor = 10;
		renderer.setHeightFactor(heightFactor);
		while (true) {
			if(renderer.getZ() > renderer.getCurrentHeight() * heightFactor + playerHeight) {
				yVel -= .01;
			}
			renderer.setZ(renderer.getZ() + yVel);
			if(renderer.getZ() < renderer.getCurrentHeight() * heightFactor + playerHeight) {
				renderer.setZ(renderer.getCurrentHeight() * heightFactor + playerHeight);
				yVel = 0;
			}
			if(StdDraw.isKeyPressed(KeyEvent.VK_SPACE) && yVel == 0) {
				yVel += .12;
			}
			if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
				renderer.moveRight(moveSpeed * Math.cos(renderer.getXDir() * Math.PI / 180));
				renderer.moveForward(moveSpeed * Math.sin(renderer.getXDir() * Math.PI / 180));
			}
			if(StdDraw.isKeyPressed(KeyEvent.VK_S)) {
				renderer.moveRight(-moveSpeed * Math.cos(renderer.getXDir() * Math.PI / 180));
				renderer.moveForward(-moveSpeed * Math.sin(renderer.getXDir() * Math.PI / 180));
			}
			if(StdDraw.isKeyPressed(KeyEvent.VK_A)) {
				renderer.moveForward(-moveSpeed * Math.cos(renderer.getXDir() * Math.PI / 180));
				renderer.moveLeft(-moveSpeed * Math.sin(renderer.getXDir() * Math.PI / 180));
			}
			if(StdDraw.isKeyPressed(KeyEvent.VK_D)) {
				renderer.moveForward(moveSpeed * Math.cos(renderer.getXDir() * Math.PI / 180));
				renderer.moveLeft(moveSpeed * Math.sin(renderer.getXDir() * Math.PI / 180));
			}
			if(StdDraw.isKeyPressed(KeyEvent.VK_I)) {
				renderer.turnUp(turnSpeed);
			}
			if(StdDraw.isKeyPressed(KeyEvent.VK_K)) {
				renderer.turnDown(turnSpeed);
			}
			if(StdDraw.isKeyPressed(KeyEvent.VK_J)) {
				renderer.turnLeft(turnSpeed);
			}
			if(StdDraw.isKeyPressed(KeyEvent.VK_L)) {
				renderer.turnRight(turnSpeed);
			}
			if(StdDraw.isMousePressed()) {
				newMouseX = StdDraw.mouseX();
				newMouseY = StdDraw.mouseY();
				renderer.turnRight((newMouseX - mouseX) * sensitivity);
				renderer.turnUp((newMouseY - mouseY) * sensitivity);
			}
			if(StdDraw.isKeyPressed(KeyEvent.VK_E)) {
				renderer.setHeightAtCurrentPosition(renderer.getCurrentHeight() + .01);
			}
			if(StdDraw.isKeyPressed(KeyEvent.VK_Q)) {
				renderer.setHeightAtCurrentPosition(renderer.getCurrentHeight() - .01);
			}
			mouseX = StdDraw.mouseX();
			mouseY = StdDraw.mouseY();
			if(StdDraw.hasNextKeyTyped()) {
				char key = StdDraw.nextKeyTyped();
				if(key == 'm') {
					onMap = !onMap;
				}
				if(key == 'p') {
					debug = !debug;
				}
				if(key == '1') {
					renderer.changeResolution(5);
				}
				if(key == '2') {
					renderer.changeResolution(-5);
				}
				if(key == 'z') {
					renderer.changeFOV(5);
				}
				if(key == 'x') {
					renderer.changeFOV(-5);
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