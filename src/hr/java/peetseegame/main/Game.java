package hr.java.peetseegame.main;

import static hr.java.peetseegame.main.GlobalSettings.*;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import hr.java.peetseegame.graphics.Screen;
import hr.java.peetseegame.input.Keyboard;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	// Game state
	private STATE gameState;

	// Game thread
	private Thread thread;
	private JFrame frame;
	private boolean running = false;

	// Game view
	private BufferedImage image = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	private Screen screen;
	private Keyboard keyboard;

	public Game() {
		Dimension size = new Dimension(WINDOW_WIDTH * SCALE, WINDOW_HEIGHT * SCALE);
		setPreferredSize(size);

		screen = new Screen();
		frame = new JFrame();
		keyboard = new Keyboard();

		gameState = STATE.GAME;

		frame.addKeyListener(keyboard);
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "DisplayThread");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double ns = 1000000000.0 / (UPS);
		double delta = 0;

		int frames = 0;
		int updates = 0;

		while (running) {
			ns = 1000000000.0 / (UPS);
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			// Update part
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}

			// Render part
			render();
			frames++;

			// FPS, UPS calculation
			if ((System.currentTimeMillis() - timer) > 1000) {
				timer += 1000;
				frame.setTitle(TITLE + " | " + "ups: " + updates + " fps: " + frames);

				updates = 0;
				frames = 0;
			}
		}

		stop();
	}

	public void update() {

		getKeyGame();
		if (gameState == STATE.GAME)
			screen.update();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		// Render screen
		screen.clear();
		screen.render();
		for (int i = 0; i < pixels.length; i++)
			pixels[i] = screen.pixels[i];

		Graphics g = bs.getDrawGraphics();
		// Drawing part
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

		g.dispose();
		bs.show();
	}

	public void getKeyGame() {
		screen.keyPressed(keyboard.keys);

		// PAUSE
		if (keyboard.isPause())
			if (gameState == STATE.GAME)
				gameState = STATE.PAUSE;
			else
				gameState = STATE.GAME;

	}

	public static void main(String[] args) {
		Game game = new Game();

		// Configure game window
		game.frame.setResizable(false);
		game.frame.setTitle(TITLE);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setFocusable(true);
		game.frame.setVisible(true);

		game.start();
	}

}
