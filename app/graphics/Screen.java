package app.graphics;

import java.awt.image.DataBufferByte;
import java.util.Random;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Screen {

	private int width, height;
	public int[][] pixels;

	public int[][] tiles = new int[64][64];

	private Random random = new Random();

	//private int[][] testImagePixels;

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width][height];
		//testImagePixels = loadImage("res/images.jpg");
	}

	public int[][] loadImage(String path) {
		int[][] result;
		try {
			BufferedImage image = ImageIO.read(getClass().getResource(path));
			int width = image.getWidth();
			int height = image.getHeight();
			result = new int[width][height];

			for (int row = 0; row < height; row++) {
				for (int col = 0; col < width; col++) {
					result[col][row] = image.getRGB(col, row);
				}
			}
		}
		catch (IOException e) { result = null; }
		return result;
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
				//if (x < testImagePixels.length && y < testImagePixels[0].length) {
					//pixels[x][y] = 0xffffff + testImagePixels[x][y]; // for jpg
					//pixels[x][y] = testImagePixels[x][y]; // for png
				//}
				//else {
				//	pixels[x][y] = 0x000000;
				//}
			}
		}
	}

}