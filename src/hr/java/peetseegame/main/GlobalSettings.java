package hr.java.peetseegame.main;

import java.awt.Color;

public class GlobalSettings {
	// Game state
	public static enum STATE {
		GAME, PAUSE
	}

	// Frame and update rates
	public static final int UPS = 30;
	public static final int FPS = 300;

	// Screen settings
	public static final String TITLE = "PeeTsee/Game";
	public static final double RATIO = 9. / 16.;
	public static final int WINDOW_WIDTH = 500;
	public static final int WINDOW_HEIGHT = (int) (WINDOW_WIDTH * RATIO);
	public static final int SCALE = 2;
	public static final Color CLEAR_COLOR = Color.BLACK;

	public static final String BACKGROUND_PATH = "./src/resources/background.png";

	// Physics
	public static final double G_ACCELERATION = 1.5;

}
