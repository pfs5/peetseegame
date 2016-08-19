package hr.java.peetseegame.gamelogics;

import static hr.java.peetseegame.main.GlobalSettings.WINDOW_HEIGHT;
import static hr.java.peetseegame.main.GlobalSettings.WINDOW_WIDTH;

import hr.java.peetseegame.main.GlobalSettings;

public class BallObject extends AbstractGameObject {

	private static String path = "./src/resources/balun.png";
	private static int X_INIT = 100;
	private static int Y_INIT = 0;

	// Image
	private int[] imagePixels;

	// Position
	private int x;
	private int y;

	// Physics
	private double speedX;
	private double speedY;
	private double accelerationX;
	private double accelerationY;

	public BallObject() {
		imagePixels = loadImage(path);

		this.x = X_INIT;
		this.y = Y_INIT;

		this.speedX = -8;
		this.speedY = 0;
		this.accelerationX = 0;
		this.accelerationY = GlobalSettings.G_ACCELERATION;
	}

	@Override
	public void render(int[] pixels) {
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				int globalRow = row + y;
				int globalCol = col + x;

				int globalIndex = globalRow * WINDOW_WIDTH + globalCol;
				int localIndex = row * width + col;

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
		// Move according to speed
		x += speedX;
		y += speedY;

		// Increase speed with acceleration
		speedX += accelerationX;
		speedY += accelerationY;

		// Check bottom collision
		if (y + height >= WINDOW_HEIGHT) {
			speedY *= -0.6;
		}

		// Check horizontal collisions
		if (x <= 0 || x + width >= WINDOW_WIDTH) {
			speedX *= -0.7;
		}
	}

	@Override
	public void keyPressed(boolean[] keys) {
	}

}
