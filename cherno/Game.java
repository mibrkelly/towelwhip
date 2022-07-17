package cherno;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.Color;
import java.awt.Graphics;
import cherno.graphics.Screen;

public class Game extends Canvas implements Runnable {
	public static final long serialVersionUID = 1L;

	public static int width = 900;
	public static int height = width / 16 * 9;  // 16x9 aspect ratio

	private Screen screen;

	private Thread thread;
	private JFrame frame;
	private boolean running = false;

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

	public Game() {
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
		
		
		while (running) {
			update();
			render();
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

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();

		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

		g.dispose();  // Release system resources
		bs.show();  // Buffer swapping - show the next available buffer
	}

	public static void main (String[] args) {
		Game game = new Game();	
		game.frame.setResizable(false);
		game.frame.setTitle("Rain");
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);

		game.start();
	}

}