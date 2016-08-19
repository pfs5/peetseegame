package hr.java.peetseegame.gamelogics;

import static hr.java.peetseegame.main.GlobalSettings.*;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import hr.java.peetseegame.main.GlobalSettings;

public class DomagojObject implements IGameObject {

	private static final String path = "./src/resources/domagoj.png";

	// Image
	private int[] imagePixels;
	private int height;
	private int width;

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
		initGraphics();

		x = GlobalSettings.WINDOW_WIDTH / 2;
		y = GlobalSettings.WINDOW_HEIGHT - height;
	}

	private void initGraphics() {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(path));

			this.width = img.getWidth();
			this.height = img.getHeight();
			imagePixels = new int[height * width];

			for (int row = 0; row < height; row++) {
				for (int col = 0; col < width; col++) {
					imagePixels[row * width + col] = img.getRGB(col, row);
				}
			}
		} catch (IOException e) {
			System.out.println("Error loading Domagoj.");
		}
	}

	@Override
	public void render(int[] pixels) {
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				int globalRow = row + y;
				int globalCol = col + x;

				if (globalRow > WINDOW_WIDTH && globalCol > WINDOW_HEIGHT) {
					continue;
				}

				int globalIndex = globalRow * WINDOW_WIDTH + globalCol;
				int localIndex = row * width + col;

				if (globalIndex < 0 || globalIndex >= pixels.length) {
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
