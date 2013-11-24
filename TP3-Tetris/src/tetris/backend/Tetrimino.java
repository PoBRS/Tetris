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

	private void SetNewPosition(int translateX, int translateY)
	{
		for (int i = 0; i < 4; i++)
		{
			Block blockToMove = blocks[i];
			int blockPosXInit = blockToMove.getPosX();
			int blockPosYInit = blockToMove.getPosY();
			int blockPosXFinal = (blockPosXInit + translateX);
			int blockPosYFinal = (blockPosYInit + translateY);

			blocks[i].setPosX(blockPosXFinal);
			blocks[i].setPosY(blockPosYFinal);

			gameGrid[blockPosXInit][blockPosYInit].setBlock(null);
		}
		ChangePosOnGrid();
	}

	private void ChangePosOnGrid()
	{
		for (int i = 0; i < 4; i++)
		{
			Block blockToMove = blocks[i];
			int blockPosXNew = blockToMove.getPosX();
			int blockPosYNew = blockToMove.getPosY();
			gameGrid[blockPosXNew][blockPosYNew].setBlock(blockToMove);
		}
	}

	public void MoveLeft()
	{
		// Gauche (Selon la flèche pesée. Accélère pendant le hold)
		boolean allowedMove = true;
		for (int i = 0; i < 4; i++)
		{ 
			if (blocks[i].getPosX() == 0)
			{
				allowedMove = false;
			}
		}
		
		if (allowedMove)
			{
			this.SetNewPosition(-1, 0);
			}
	}

	public void MoveRight()
	{
		// Droite (Selon la flèche pesée. Accélère pendant le hold)
		boolean allowedMove = true;
		for (int i = 0; i < 4; i++)
		{ 
			if (blocks[i].getPosX() == 9)
			{
				allowedMove = false;
			}
		}
		
		if (allowedMove)
			{
			this.SetNewPosition(1, 0);
			}
		
	}

	public boolean MoveDown()
	{
		// Bas (Selon le timer tick)
		boolean allowedMove = true;
		for (int i = 0; i < 4; i++)
		{ 
			if (blocks[i].getPosY() == 23)
			{
				allowedMove = false;
			}
		}
		
		if (allowedMove)
			{
			this.SetNewPosition(0, 1);
			}
		return allowedMove;


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
