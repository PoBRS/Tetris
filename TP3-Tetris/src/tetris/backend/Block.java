package tetris.backend;

public class Block
{

	private int posX = 0;
	private int posY = 0;
	private boolean filled = false;
	private boolean pivot = false;
	private boolean fixed = false;

	public Block(int posX, int posY, boolean pivot)
	{
		this.posX = posX;
		this.posY = posY;
	}

	public int getPosY()
	{
		return posY;
	}

	public int getPosX()
	{
		return posX;
	}

	public void incrementPosY()
	{
		this.posY++;
	}

	public void incrementPosX()
	{
		this.posX++;
	}

	public void decrementPosY()
	{
		this.posY--;
	}

	public void decrementPosX()
	{
		this.posX--;
	}

	public boolean isPivot()
	{
		return pivot;
	}

	public boolean isFixed()
	{
		return fixed;
	}

	public void setFixed(boolean fixed)
	{
		this.fixed = fixed;
	}

	public boolean isFilled()
	{
		return filled;
	}

	public void changeFilled()
	{
		this.filled = !this.filled;
	}

}
