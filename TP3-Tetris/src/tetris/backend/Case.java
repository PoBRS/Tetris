package tetris.backend;

public class Case
{
	private Block block;
	private int posX;
	private int posY;

	public Case(int posX, int posY)
	{
		this.setBlock(null);
		this.posX = posX;
		this.posY = posY;
	}

	public Block getBlock()
	{
		return this.block;
	}

	public void setBlock(Block block)
	{
		this.block = block;
	}
}
