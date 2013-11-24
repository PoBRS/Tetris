package tetris.backend;

public class Tetrimino
{
	final static int FIRST_LINE = 0;
	final static int LAST_LINE = 23;
	final static int FIRST_COLUMN = 0;
	final static int LAST_COLUMN = 9;

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
			blocks[0] = CreateBlock(4, 0, false);
			blocks[0] = CreateBlock(4, 1, true); // not so sure
			blocks[0] = CreateBlock(4, 2, false);
			blocks[0] = CreateBlock(4, 3, false);
			break;
		case S:
			blocks[0] = CreateBlock(6, 2, false);
			blocks[0] = CreateBlock(5, 2, false);
			blocks[0] = CreateBlock(5, 3, true); // not so sure
			blocks[0] = CreateBlock(4, 3, false);
			break;
		case Z:
			blocks[0] = CreateBlock(4, 2, false);
			blocks[0] = CreateBlock(5, 2, true); // not so sure
			blocks[0] = CreateBlock(5, 3, false);
			blocks[0] = CreateBlock(6, 3, false);
			break;
		case T:
			blocks[0] = CreateBlock(5, 1, false);
			blocks[1] = CreateBlock(5, 2, true);
			blocks[2] = CreateBlock(5, 3, false);
			blocks[3] = CreateBlock(4, 2, false);
			break;
		case L:
			blocks[0] = CreateBlock(4, 1, false);
			blocks[1] = CreateBlock(4, 2, true);
			blocks[2] = CreateBlock(4, 3, false);
			blocks[3] = CreateBlock(5, 3, false);
			break;
		case J:
			blocks[0] = CreateBlock(5, 1, false);
			blocks[1] = CreateBlock(5, 2, true);
			blocks[2] = CreateBlock(5, 3, false);
			blocks[3] = CreateBlock(4, 3, false);
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
		int blockPosX;
		int blockPosY;
		int leftBlockPosX;
		Block leftBlock;

		for (int i = 0; i < 4; i++)
		{
			blockPosX = blocks[i].getPosX();
			blockPosY = blocks[i].getPosY();

			if (blockPosX == FIRST_COLUMN)
			{
				allowedMove = false;
			} else
			{
				leftBlockPosX = blocks[i].getPosX() - 1;
				leftBlock = this.gameGrid[leftBlockPosX][blockPosY].getBlock();

				if (leftBlock != null && leftBlock.isFixed())
				{
					allowedMove = false;
				}
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
		int blockPosX;
		int blockPosY;
		int rightBlockPosX;
		Block rightBlock;

		for (int i = 0; i < 4; i++)
		{
			blockPosX = blocks[i].getPosX();
			blockPosY = blocks[i].getPosY();

			if (blockPosX == LAST_COLUMN)
			{
				allowedMove = false;
			} else
			{
				rightBlockPosX = blocks[i].getPosX() + 1;
				rightBlock = this.gameGrid[rightBlockPosX][blockPosY].getBlock();

				if (rightBlock != null && rightBlock.isFixed())
				{
					allowedMove = false;
				}
			}
		}

		if (allowedMove)
		{
			this.SetNewPosition(1, 0);
		}

	}

	public boolean MoveDown()
	{
		boolean allowedMove = true;

		int blockPosY;
		int blockPosX;
		int blockPosYInferior;
		Block inferiorBlock;

		for (int i = 0; i < 4; i++)
		{

			blockPosY = blocks[i].getPosY();
			blockPosX = blocks[i].getPosX();

			// A block is on the last line.
			if (blockPosY == LAST_LINE)
			{
				allowedMove = false;
			} else
			{

				// A block is on top of another fixed block. If a block is in
				// movement(in the current tetrimino), the block will still
				// fall.
				blockPosYInferior = blocks[i].getPosY() + 1;
				inferiorBlock = this.gameGrid[blockPosX][blockPosYInferior].getBlock();

				if (inferiorBlock != null && inferiorBlock.isFixed())
				{
					allowedMove = false;
				}
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
		Block pivotBlock = this.getPivotBlock();
		if (pivotBlock != null)
		{
			int pivotX = pivotBlock.getPosX();
			int pivotY = pivotBlock.getPosY();

			for (Block blockToRotate : this.blocks)
			{
				int relativeXToPivot = blockToRotate.getPosX() - pivotX;
				int relativeYToPivot = blockToRotate.getPosY() - pivotY;

				int newPointXRelativeToPivot = -relativeYToPivot; // the magic
																	// of
																	// rotation.
																	// The point
																	// x is the
																	// negative
																	// old point
																	// y.
				int newPointYRelativeToPivot = relativeXToPivot; // And the new
																	// y is the
																	// old x.

				blockToRotate.setPosX(pivotX + newPointXRelativeToPivot);
				blockToRotate.setPosY(pivotY + newPointYRelativeToPivot);

				this.ChangePosOnGrid();
			}
		}

	}

	/**
	 * Fonction qui retourne le block du tetrimino représentant le pivot.
	 * 
	 * @return le block pivot.
	 */
	private Block getPivotBlock()
	{
		for (Block block : this.blocks)
		{
			if (block.isPivot())
			{
				return block;
			}
		}

		return null;
	}

	public void Deactivate()
	{
		for (int i = 0; i < 4; i++)
		{
			blocks[i].setFixed(true);
		}
	}

	public Block[] getBlocks()
	{
		return this.blocks;
	}

}
