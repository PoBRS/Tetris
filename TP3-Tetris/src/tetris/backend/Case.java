package tetris.backend;


public class Case
{
	private CaseListener caseListener;
	private Block block;
	private int posX = 0;
	private int posY = 0;

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
		if (this.caseListener != null)
		{
			this.caseListener.onStateChanged();
		}
	}

	public void Print()
	{
		if (this.block == null)
		{
			System.out.print(" ");
		}

		else
		{
			System.out.print("X");
		}
	}
	public void setBlockListener(CaseListener caseListener) {
		this.caseListener = caseListener;
	}
}
