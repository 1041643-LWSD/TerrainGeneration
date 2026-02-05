public class NoiseMap {
	private final double[][] map;
	private final int resolution;
	
	public NoiseMap (int resolution, double gradient) {
		map = new double[resolution][resolution];
		
		//fill in all of the cells
		for(int i = 0; i < resolution; i++) {
			for(int j = 0; j < resolution; j++) {
				int numNeighbors = 0;
				double totalNeighborShade = 0.0;
				//these two for loops check the neighbors around our cell
				for(int x = -1; x <= 1; x++) {
					for(int y = -1; y <= 1; y++) {
						if(!(x == 0 && y == 0)) {
							int newX = x + i;
							int newY = y + j;
							if(newX >= 0 && newX < resolution && newY >= 0 && newY < resolution) {
								if(map[newX][newY] != 0.0) {
									numNeighbors++;
									totalNeighborShade += map[newX][newY];
								}
							}
						}
					}
				}
				//make random points if the gradient is 1
				if(numNeighbors == 0 || gradient == 1) {
					map[i][j] = Math.random();
				} else {
					do {
						map[i][j] = totalNeighborShade/numNeighbors + (gradient/2.0 - Math.random() * gradient);
					} while(map[i][j] < 0 || map[i][j] > 1);			
				}
			}
		}
		this.resolution = resolution;
	}
	
	public void drawNoiseMap() {
		for(int i = 0; i < resolution; i++) {
			for(int j = 0; j < resolution; j++) {
				StdDraw.setPenColor((int)(255 * map[i][j]), (int)(255 * map[i][j]), (int)(255 * map[i][j]));
				//System.out.println((int)(255 * map[i][j]));
				StdDraw.setPenRadius((double) 1/(resolution));
				StdDraw.point((double) i/resolution + (double) 1/(resolution * 2), (double) j/resolution + (double)1/(resolution * 2));
			}
		}
	}

	public double getHeightAt(int x, int y) {
		return map[x][y];
	}

	public int getResolution() {
		return resolution;
	}

	public void setHeightAt(int x, int y, double height) {
		map[x][y] = height;
		if(height > 1) {
			map[x][y] = 1;
		}
		if(height < 0) {
			map[x][y] = 0;
		}
	}
}