package hr.java.peetseegame.gamelogics;

public interface IGameObject {
	public void render (int [] pixels);
	public void update ();
	public void keyPressed (boolean [] keys);
}
