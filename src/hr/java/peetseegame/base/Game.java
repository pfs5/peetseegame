package hr.java.peetseegame.base;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener {

	/**
	 * Default serial version ID.
	 */
	private static final long serialVersionUID = 1L;

	private static final int DELAY = 3;

	private int width;
	private int height;

	private Timer timer;

	private GameState gameState;

	public Game(int width, int height) {
		this.width = width;
		this.height = height;

		initBoard();
	}

	public void initBoard() {
		// Necessary part
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.BLACK);
		setDoubleBuffered(true);

		timer = new Timer(DELAY, this);
		timer.start();

		initGame();
	}

	public void initGame() {
	}

	/**
	 * Drawing.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		if (gameState == GameState.GAME) {
			paintGame(g2d);
		}
	}

	public void paintGame(Graphics2D g2d) {
	}

	/**
	 * Periodic action.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

	/**
	 * Key listener.
	 */
	private class TAdapter extends KeyAdapter {

		@Override
		public void keyReleased(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
		}

	}

}
