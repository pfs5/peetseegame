package hr.java.peetseegame.main;

public class GlobalSettings {
	// Game state
	public static enum STATE {
		GAME, PAUSE
	}

	// Frame and update rates
	public static final int UPS = 30;
	public static final int FPS = 60;

	// Screen settings
	public static final String TITLE = "PeeTsee/Game";
	public static final double RATIO = 9. / 16.;
	public static final int WINDOW_WIDTH = 500;
	public static final int WINDOW_HEIGHT = (int) (WINDOW_WIDTH * RATIO);
	public static final int SCALE = 2;

}
