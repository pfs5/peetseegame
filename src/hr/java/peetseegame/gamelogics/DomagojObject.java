package hr.java.peetseegame.gamelogics;

import static hr.java.peetseegame.main.GlobalSettings.*;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DomagojObject implements IGameObject {

	private static String path = "./src/resources/domagoj.png";
	private static int X_INIT = 0;
	private static int Y_INIT = 0;

	// Image
	private int[] imagePixels;
	private int height;
	private int width;

	// Position
	private int x;
	private int y;

	public DomagojObject() {
		initGraphics();

		x = X_INIT;
		y = Y_INIT;
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
			System.out.println("Error loading domagoj.");
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

				pixels[globalRow * WINDOW_WIDTH + globalCol] = imagePixels[row * width + col];
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
			x -= 1;
		}
		if (keys[KeyEvent.VK_RIGHT]) {

			x += 1;
		}
		if (keys[KeyEvent.VK_UP]) {
			y = Math.abs(y - 1);
		}
		if (keys[KeyEvent.VK_DOWN]) {
			y += 1;
		}
	}

}
