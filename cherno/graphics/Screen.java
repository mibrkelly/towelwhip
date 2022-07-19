package cherno.graphics;

import java.util.Random;

public class Screen {

	private int width, height;
	public int[][] pixels;

	public int[][] tiles = new int[64][64];

	private Random random = new Random();

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width][height];

		for (int i = 0; i < 64; i++) {
			for (int j = 0; j < 64; j++) {
				tiles[i][j] = random.nextInt(0xffffff);
			}
		}

	}

	public void clear() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				pixels[i][j] = 0;
			}
		}
	}

	public void render() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x][y] = tiles[x >> 4][y >> 4];
			}
		}
	}

}