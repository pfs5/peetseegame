package hr.java.peetseegame.graphics;

import static hr.java.peetseegame.main.GlobalSettings.*;

public class Screen {
	public int[] pixels;

	public Screen() {
		pixels = new int[WINDOW_WIDTH * WINDOW_HEIGHT];
	}

	public void clear() {
		for (int y = 0; y < WINDOW_HEIGHT; y++)
			for (int x = 0; x < WINDOW_WIDTH; x++)
				pixels[y * WINDOW_WIDTH + x] = 0x000000;
	}

	public void render() {
	}

	public void update() {
	}

}
