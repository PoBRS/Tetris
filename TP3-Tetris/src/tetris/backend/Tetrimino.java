package tetris.backend;

public class Tetrimino
{

	private Block[] blocks;
	private Case[][] gameGrid;

	public Tetrimino(EnumShape shape, Case[][] gameGrid)
	{
		blocks = new Block[4];
		this.gameGrid = gameGrid;
		// L, S, Z, T, I, J, O;
		switch (shape)
		{
		case I:
			break;
		case S:
			break;
		case Z:
			break;
		case T:
			break;
		case L:
			break;
		case J:
			break;
		case O:
			// 4,5 X
			// 2,3 Y

			blocks[0] = CreateBlock(4, 2, false);
			blocks[1] = CreateBlock(4, 3, false);
			blocks[2] = CreateBlock(5, 2, false);
			blocks[3] = CreateBlock(5, 3, false);

			break;
		}
	}

	private Block CreateBlock(int x, int y, boolean pivot)
	{
		Block newBlock = new Block(x, y, pivot);
		gameGrid[x][y].setBlock(newBlock);
		return newBlock;
	}

	public void MoveLeft()
	{
		// Gauche (Selon la flèche pesée. Accélère pendant le hold)

	}

	public void MoveRight()
	{
		// Droite (Selon la flèche pesée. Accélère pendant le hold)
	}

	public boolean MoveDown()
	{
		// Bas (Selon le timer tick)

		// Si le bloc ne peut plus descendre
		return false;
	}

	public void Rotate()
	{
		// Algo selon pivot
	}

	private boolean Deactivate()
	{
		return false;
	}

	public Block[] getBlocks()
	{
		return this.blocks;
	}

}
