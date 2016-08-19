package hr.java.peetseegame.gamelogics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AbstractGameObject implements IGameObject {

	protected int width;
	protected int height;
	
	protected int [] loadImage(String path) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(path));

			this.width = img.getWidth();
			this.height = img.getHeight();
			int [] imagePixels = new int[height * width];

			for (int row = 0; row < height; row++) {
				for (int col = 0; col < width; col++) {
					imagePixels[row * width + col] = img.getRGB(col, row);
				}
			}
			return imagePixels;
		} catch (IOException e) {
			System.out.println("Error loading image from path \"" + path + "\"");
		}
		return null;
	}
	
	@Override
	public void render(int[] pixels) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(boolean[] keys) {
		// TODO Auto-generated method stub
		
	}

}
