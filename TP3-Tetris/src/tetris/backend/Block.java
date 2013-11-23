package tetris.backend;

public class Block
{

	private int posX = 0;
	private int posY = 0;
	private boolean pivot = false;
	private boolean fixed = false;

	public Block(int posX, int posY, boolean pivot)
	{
		this.setPosX(posX);
		this.setPosY(posY);

	}

	public int getPosY()
	{
		return this.posY;
	}

	public void setPosY(int posY)
	{
		this.posY = posY;
	}

	public int getPosX()
	{
		return this.posX;
	}

	public void setPosX(int posX)
	{
		this.posX = posX;
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
		return this.pivot;
	}

	public boolean isFixed()
	{
		return this.fixed;
	}

	public void setFixed(boolean fixed)
	{
		this.fixed = fixed;
	}

}
