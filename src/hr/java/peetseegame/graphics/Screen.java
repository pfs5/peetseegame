package hr.java.peetseegame.graphics;

import static hr.java.peetseegame.main.GlobalSettings.*;

import java.util.ArrayList;

import hr.java.peetseegame.gamelogics.DomagojObject;
import hr.java.peetseegame.gamelogics.IGameObject;

public class Screen {

	private ArrayList<IGameObject> gameObjects;
	public int[] pixels;

	public Screen() {
		// Create pixels
		pixels = new int[WINDOW_HEIGHT * WINDOW_WIDTH];

		gameObjects = new ArrayList<IGameObject>();

		// Test objects
		gameObjects.add(new DomagojObject());
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0xff000000;
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
