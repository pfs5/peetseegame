package hr.java.peetseegame.gamelogics;

import static hr.java.peetseegame.main.GlobalSettings.*;

import java.awt.event.KeyEvent;

public class DomagojObject extends AbstractGameObject {

	private static String path = "./src/resources/domagoj.png";
	private static int X_INIT = 0;
	private static int Y_INIT = 0;

	// Image
	private int[] imagePixels;

	// Position
	private int x;
	private int y;

	public DomagojObject() {
		imagePixels = loadImage(path);

		x = X_INIT;
		y = Y_INIT;
	}

	@Override
	public void render(int[] pixels) {
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				int globalRow = row + y;
				int globalCol = col + x;

				int globalIndex = globalRow * WINDOW_WIDTH + globalCol;
				int localIndex = row * width + col;

				// Check out of bounds
				if (globalIndex < 0 || globalIndex >= pixels.length) {
					continue;
				}

				// Check alpha
				if ((imagePixels[localIndex] >> 24 & 0xff) == 0) {
					continue;
				}

				pixels[globalIndex] = imagePixels[localIndex];
			}
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(boolean[] keys) {
		// Movement
		if (keys[KeyEvent.VK_LEFT]) {
			x -= 5;
		}
		if (keys[KeyEvent.VK_RIGHT]) {

			x += 5;
		}
		if (keys[KeyEvent.VK_UP]) {
			y = Math.abs(y - 5);
		}
		if (keys[KeyEvent.VK_DOWN]) {
			y += 5;
		}
	}

}
