package cherno.input;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Keyboard  implements KeyListener {

	private boolean[] keys = new boolean[120];
	public boolean up, down, left, right;

	public void update() {
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
	}

	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {

	}

}