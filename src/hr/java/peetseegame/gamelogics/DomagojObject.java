package hr.java.peetseegame.gamelogics;

import static hr.java.peetseegame.main.GlobalSettings.*;

import java.awt.event.KeyEvent;

import hr.java.peetseegame.main.GlobalSettings;

public class DomagojObject extends AbstractGameObject {

	private static final String path = "./src/resources/domagoj.png";

	// Image
	private int[] imagePixels;

	// Position
	private int x;
	private int y;

	// Animation
	private static final int JUMP_SPEED = 14;
	private boolean jumpState;
	private int verticalSpeed;
	private int horizontalSpeed = 3;
	private int gravity = 2;

	public DomagojObject() {
		imagePixels = loadImage(path);

		x = GlobalSettings.WINDOW_WIDTH / 2;
		y = GlobalSettings.WINDOW_HEIGHT - height;
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
		if (jumpState) {
			verticalSpeed -= gravity;

			y -= verticalSpeed;

			int yRelativeToFloor = GlobalSettings.WINDOW_HEIGHT - height;

			if (y > yRelativeToFloor) {
				y = yRelativeToFloor;
				jumpState = false;
			}
		}

	}

	@Override
	public void keyPressed(boolean[] keys) {

		// Movement
		if (keys[KeyEvent.VK_LEFT]) {
			x -= horizontalSpeed;
		}

		if (keys[KeyEvent.VK_RIGHT]) {
			x += horizontalSpeed;
		}

		if (jumpState) {
			// Disable UP and DOWN actions while jumping.
			return;
		}

		if (keys[KeyEvent.VK_UP]) {
			verticalSpeed = JUMP_SPEED;
			jumpState = true;
		}

		if (keys[KeyEvent.VK_DOWN]) {
		}
	}

}
