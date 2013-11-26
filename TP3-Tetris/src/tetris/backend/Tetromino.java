package tetris.backend;

import javafx.scene.paint.Color;

public class Tetromino
{
	final static int FIRST_LINE = 0;
	final static int LAST_LINE = 23;
	final static int FIRST_COLUMN = 0;
	final static int LAST_COLUMN = 9;

	private Block[] blocks;
	private Case[][] gameGrid;

	public Tetromino(EnumShape shape, Case[][] gameGrid)
	{
		blocks = new Block[4];
		this.gameGrid = gameGrid;
		// L, S, Z, T, I, J, O;
		switch (shape)
		{
		case I:
			blocks[0] = CreateBlock(4, 0, false, Color.TURQUOISE);
			blocks[1] = CreateBlock(4, 1, false, Color.TURQUOISE);
			blocks[2] = CreateBlock(4, 2, false, Color.TURQUOISE);
			blocks[3] = CreateBlock(4, 3, false, Color.TURQUOISE);
			break;
		case S:
			blocks[0] = CreateBlock(6, 2, false, Color.GREEN);
			blocks[1] = CreateBlock(5, 2, false, Color.GREEN);
			blocks[2] = CreateBlock(5, 3, false, Color.GREEN);
			blocks[3] = CreateBlock(4, 3, false, Color.GREEN);
			break;
		case Z:
			blocks[0] = CreateBlock(4, 2, false, Color.RED);
			blocks[1] = CreateBlock(5, 2, false, Color.RED);
			blocks[2] = CreateBlock(5, 3, false, Color.RED);
			blocks[3] = CreateBlock(6, 3, false, Color.RED);
			break;
		case T:
			blocks[0] = CreateBlock(5, 1, false, Color.PURPLE);
			blocks[1] = CreateBlock(5, 2, true, Color.PURPLE);
			blocks[2] = CreateBlock(5, 3, false, Color.PURPLE);
			blocks[3] = CreateBlock(4, 2, false, Color.PURPLE);
			break;
		case L:
			blocks[0] = CreateBlock(4, 1, false, Color.ORANGE);
			blocks[1] = CreateBlock(4, 2, true, Color.ORANGE);
			blocks[2] = CreateBlock(4, 3, false, Color.ORANGE);
			blocks[3] = CreateBlock(5, 3, false, Color.ORANGE);
			break;
		case J:
			blocks[0] = CreateBlock(5, 1, false, Color.BLUE);
			blocks[1] = CreateBlock(5, 2, true, Color.BLUE);
			blocks[2] = CreateBlock(5, 3, false, Color.BLUE);
			blocks[3] = CreateBlock(4, 3, false, Color.BLUE);
			break;
		case O:
			// 4,5 X
			// 2,3 Y

			blocks[0] = CreateBlock(4, 2, false, Color.YELLOW);
			blocks[1] = CreateBlock(4, 3, false, Color.YELLOW);
			blocks[2] = CreateBlock(5, 2, false, Color.YELLOW);
			blocks[3] = CreateBlock(5, 3, false, Color.YELLOW);

			break;
		}
	}

	private Block CreateBlock(int x, int y, boolean pivot, Color colorBlock)
	{
		Block newBlock = new Block(x, y, pivot, colorBlock);
		gameGrid[x][y].setBlock(newBlock);
		return newBlock;
	}

	private void SetNewPositionTranslate(int translateX, int translateY)
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
			}
			else
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
			this.SetNewPositionTranslate(-1, 0);
		}
	}

	public int findLandingPosY()
	{
		int tetrominoLandingCase = 0;

		for (int i = 0; i < 4; i++)
		{
			if (this.blocks[i].getPosY() > tetrominoLandingCase)
			{
				tetrominoLandingCase = this.blocks[i].getPosY();
			}
		}

		return tetrominoLandingCase;
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
			}
			else
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
			this.SetNewPositionTranslate(1, 0);
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
			}
			else
			{

				// A block is on top of another fixed block. If a block is in
				// movement(in the current tetromino), the block will still
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
			this.SetNewPositionTranslate(0, 1);
		}
		return allowedMove;

	}

	public void RotateCallManager()
	{

		double pivotX = 0;
		double pivotY = 0;

		Block pivotBlock = this.getPivotBlock();

		if (pivotBlock == null)
		{
			double[] pivotPosObtained;
			pivotPosObtained = findPivotPos();
			pivotX = pivotPosObtained[0];
			pivotY = pivotPosObtained[1];
		}
		else
		{
			pivotX = pivotBlock.getPosX();
			pivotY = pivotBlock.getPosY();

		}

		this.rotateBlockAroundPivot(pivotX, pivotY);

	}

	private double[] findPivotPos()
	{
		int maxX = 0;
		int maxY = 0;
		int minX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;
		int sumX = 0;
		int sumY = 0;
		double[] pivotPos = new double[2];

		for (Block blockToRotate : this.blocks)
		{
			int blockPosX = blockToRotate.getPosX();
			int blockPosY = blockToRotate.getPosY();

			if (blockPosX > maxX)
			{
				maxX = blockPosX;
			}

			if (blockPosX < minX)
			{
				minX = blockPosX;
			}
			if (blockPosY > maxY)
			{
				maxY = blockPosY;
			}
			if (blockPosY < minY)
			{
				minY = blockPosY;
			}

		}

		for (int i = minX; i <= maxX; i++)
		{
			sumX += i;
		}
		for (int i = minY; i <= maxY; i++)
		{
			sumY += i;
		}

		pivotPos[0] = (sumX / (double) (maxX - minX + 1));
		pivotPos[1] = (sumY / (double) (maxY - minY + 1));
		return pivotPos;

	}

	private void rotateBlockAroundPivot(double pivotX, double pivotY)
	{
		int[][] newPosBlocks = new int[4][2];
		int currentBlockInArray = 0;
		boolean validRotation = true;

		for (Block blockToRotate : this.blocks)
		{
			int blockToRotatePosX = blockToRotate.getPosX();
			int blockToRotatePosY = blockToRotate.getPosY();
			gameGrid[blockToRotatePosX][blockToRotatePosY].setBlock(null);

			double relativeXToPivot = blockToRotate.getPosX() - pivotX;
			double relativeYToPivot = blockToRotate.getPosY() - pivotY;

			int newPointXRelativeToPivot = (int) Math.round(-relativeYToPivot); // The point x is the negative old point y.
			int newPointYRelativeToPivot = (int) Math.round(relativeXToPivot); // And the new y is the old x.

			int newPositionX = (int) roundBelowZero(pivotX + newPointXRelativeToPivot);
			int newPositionY = (int) roundBelowZero(pivotY + newPointYRelativeToPivot);

			newPosBlocks[currentBlockInArray][0] = newPositionX;
			newPosBlocks[currentBlockInArray][1] = newPositionY;

			currentBlockInArray++;

		}

		// Verify space to fill.
		for (int i = 0; i < 4 && validRotation; i++)
		{
			int newPosX = newPosBlocks[i][0];
			int newPosY = newPosBlocks[i][1];

			if (newPosX < Tetromino.FIRST_COLUMN || newPosX > Tetromino.LAST_COLUMN)
			{
				validRotation = false;
			}

			if (newPosY < Tetromino.FIRST_LINE || newPosY > Tetromino.LAST_LINE)
			{
				validRotation = false;
			}

			if (validRotation && this.gameGrid[newPosX][newPosY].getBlock() != null)
			{
				validRotation = false;
			}
		}

		// Block move
		if (validRotation)
		{
			for (int i = 0; i < 4; i++)
			{
				blocks[i].setPosX(newPosBlocks[i][0]);
				blocks[i].setPosY(newPosBlocks[i][1]);
			}
		}
		this.ChangePosOnGrid();

	}

	private double roundBelowZero(double val)
	{
		if (val < 0)
		{
			return Math.floor(val);
		}
		else
		{
			return val;
		}
	}

	/**
	 * Fonction qui retourne le block du Tetromino représentant le pivot.
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
