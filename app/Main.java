package app;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.Color;
import java.awt.Graphics;
import app.graphics.Screen;

public class Main extends Canvas implements Runnable {
	public static final long serialVersionUID = 1L;

	public static int width = 900;
	public static int height = width / 16 * 9;  // 16x9 aspect ratio

	private Screen screen;

	private Thread thread;
	private JFrame frame;
	private boolean running = false;

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

	public Main() {
		Dimension size = new Dimension(width, height);
		setPreferredSize(size);

		screen = new Screen(width, height);

		frame = new JFrame();
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle("Rain | " + updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
	}

	public void update() {

	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		screen.clear();
		screen.render();

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				pixels[width*i+j] = screen.pixels[i][j];
			}
		}

		Graphics g = bs.getDrawGraphics();

		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

		g.dispose();  // Release system resources
		bs.show();  // Buffer swapping - show the next available buffer
	}

	public static void main (String[] args) {
		Main app = new Main();	
		app.frame.setResizable(false);
		app.frame.setTitle("Rain");
		app.frame.add(app);
		app.frame.pack();
		app.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.frame.setLocationRelativeTo(null);
		app.frame.setVisible(true);

		app.start();
	}

}