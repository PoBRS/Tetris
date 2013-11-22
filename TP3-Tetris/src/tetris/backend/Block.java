package tetris.backend;

public class Block {

	private int posX = 0;
	private int posY = 0;
	private boolean filled = false;
	
	public Block(int posX, int posY)
	{
		this.posX = posX;
		this.posY = posY;
	}
	
	public int getPosY() {
		return posY;
	}
	public int getPosX() {
		return posX;
	}
	
	public void incrementPosY() {
		this.posY++;
	}
	public void incrementPosX() {
		this.posX++;
	}
	
	public void decrementPosY() {
		this.posY--;
	}
	public void decrementPosX() {
		this.posX--;
	}
}
