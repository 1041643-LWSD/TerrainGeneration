public class NoiseMap {
	private double[][] map;
	private int resolution;
	
	public NoiseMap (int resolution, double gradient) {
		map = new double[resolution][resolution];
		for(int i = 0; i < resolution; i++) {
			for(int j = 0; j < resolution; j++) {
				int numNeighbors = 0;
				int totalNeighborShade = 0;
				for(int x = -1; x <= 1; x++) {
					for(int y = -1; y <= 1; y++) {
						if((x != 0 && y != 0)) {
							try {
								if(map[x + i][y + j] != 0.0) {
									numNeighbors++;
									totalNeighborShade += map[x + i][y + j];
								}
							} catch (Exception e) {
								
							}
						}
					}
				}
				if(numNeighbors == 0) {
					map[i][j] = Math.random();
					System.out.println("Made a random point!");
				} else {
					map[i][j] = totalNeighborShade/numNeighbors + Math.random() * gradient / 2;
				}
			}
		}
		this.resolution = resolution;
	}
	
	public void drawNoiseMap() {
		for(int i = 0; i < resolution; i++) {
			for(int j = 0; j < resolution; j++) {
				StdDraw.setPenColor((int)(255 * map[i][j]), (int)(255 * map[i][j]), (int)(255 * map[i][j]));
				System.out.println((int)(255 * map[i][j]));
				StdDraw.setPenRadius((double) 1/(resolution));
				StdDraw.point((double) i/resolution + (double) 1/(resolution * 2), (double) j/resolution + (double)1/(resolution * 2));
			}
		}
	}
}