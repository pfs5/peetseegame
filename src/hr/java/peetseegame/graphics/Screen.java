package hr.java.peetseegame.graphics;

import static hr.java.peetseegame.main.GlobalSettings.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import hr.java.peetseegame.gamelogics.BallObject;
import hr.java.peetseegame.gamelogics.DomagojObject;
import hr.java.peetseegame.gamelogics.IGameObject;

public class Screen {

	private ArrayList<IGameObject> gameObjects;
	public int[] pixels;
	private int[] background;
	private int backgroundWidth;

	public Screen() {
		// Create pixels
		pixels = new int[WINDOW_HEIGHT * WINDOW_WIDTH];

		// Load background
		loadBackgroundImage();

		gameObjects = new ArrayList<IGameObject>();

		// Test objects
		gameObjects.add(new DomagojObject());
		gameObjects.add(new BallObject());
	}

	private void loadBackgroundImage() {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(BACKGROUND_PATH));

			int width = img.getWidth();
			int height = img.getHeight();
			backgroundWidth = width;

			background = new int[height * width];

			for (int row = 0; row < height; row++) {
				for (int col = 0; col < width; col++) {
					background[row * width + col] = img.getRGB(col, row);
				}
			}
		} catch (IOException e) {
			System.out.println("Error loading background image.");
		}
	}

	public void clear() {
		for (int row = 0; row < WINDOW_HEIGHT; row++) {
			for (int col = 0; col < WINDOW_WIDTH; col++) {
				pixels[row * WINDOW_WIDTH + col] = background[row * backgroundWidth + col];
			}
		}
	}

	public void update() {
		for (IGameObject o : gameObjects) {
			o.update();
		}
	}

	public void render() {
		for (IGameObject o : gameObjects) {
			o.render(pixels);
		}
	}

	public void keyPressed(boolean[] keys) {
		for (IGameObject o : gameObjects) {
			o.keyPressed(keys);
		}
	}

}
