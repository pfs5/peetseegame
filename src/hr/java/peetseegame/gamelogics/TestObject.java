package hr.java.peetseegame.gamelogics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class TestObject implements IGameObject {

	private int x;
	private int y;

	private int width;
	private int height;

	public TestObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;

		this.width = width;
		this.height = height;
	}

	@Override
	public void render(int [] pixels) {
		
	}

	@Override
	public void update() {
	}

	@Override
	public void keyPressed(boolean[] keys) {
		// Movement
		if (keys[KeyEvent.VK_LEFT])
			x -= 1;
		if (keys[KeyEvent.VK_RIGHT])
			x += 1;
		if (keys[KeyEvent.VK_UP])
			y -= 1;
		if (keys[KeyEvent.VK_DOWN])
			y += 1;
	}

}
