package hr.java.peetseegame.base;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Engine extends JFrame {

	/**
	 * Default serial version ID.
	 */
	private static final long serialVersionUID = 1L;

	private static final int WIDTH = 500;
	private static final int HEIGHT = 800;
	
	private static final String DEFAULT_TITLE = "Title";

	public Engine() {
		add(new Game(WIDTH, HEIGHT));
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setTitle(DEFAULT_TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				Engine game = new Engine();
				game.setVisible(true);
			}
		});
	}

}
