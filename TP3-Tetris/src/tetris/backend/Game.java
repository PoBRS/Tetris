package tetris.backend;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game
{
	private Case gameGrid[][];

	private final static int NBCASES_X = 10;
	private final static int NBCASES_Y = 24;
	private final static int NBCASES_Y_JEU = 20;
	private Tetromino currentTetromino;
	private EnumShape next;

	private static final List<EnumShape> VALUES = Collections.unmodifiableList(Arrays.asList(EnumShape.values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	public Game()
	{
		this.gameGrid = new Case[NBCASES_X][NBCASES_Y];
		this.next = VALUES.get(RANDOM.nextInt(SIZE));

		for (int i = 0; i < NBCASES_X; i++)
		{
			for (int j = 0; j < NBCASES_Y; j++)
			{
				this.gameGrid[i][j] = new Case(i, j);
			}

		}

	}

	public void SpawnTetrimino()
	{

		if (this.currentTetromino != null)
		{
			this.currentTetromino.Deactivate();
		}
		this.currentTetromino = new Tetromino(next, this.gameGrid);
		this.next = VALUES.get(RANDOM.nextInt(SIZE));
	}

	public boolean CheckLineIsComplete(int line)
	{
		int nbFilledCases = 0;
		for (int x = 0; x < Game.NBCASES_X; x++)
		{
			if (this.gameGrid[x][line].getBlock() != null)
			{
				nbFilledCases++;
			}
		}

		if (nbFilledCases == 10)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public void ClearLine(int line)
	{
		for (int column = 0; column < 10; column++)
		{
			this.gameGrid[column][line].setBlock(null);
		}

		for (int y = line; y > 0; y--)
		{
			for (int x = 0; x < Game.NBCASES_X; x++)
			{
				this.gameGrid[x][y].setBlock(this.gameGrid[x][y - 1].getBlock());
			}
		}
	}

	public boolean LostGame()
	{
		for (int y = 3; y >= 0; y--)
		{
			for (int x = 0; x < Game.NBCASES_X; x++)
			{
				if (this.gameGrid[x][y].getBlock() != null)
				{
					return true;
				}
			}
		}
		return false;
	}

	public Tetromino getCurrentTetrimino()
	{
		return currentTetromino;
	}

	public void PrintGame()
	{
		for (int y = 0; y < NBCASES_Y; y++)
		{
			for (int x = 0; x < NBCASES_X; x++)
			{
				this.gameGrid[x][y].Print();
			}
			System.out.println();
		}
	}

	public Case[][] getGameGrid()
	{
		return gameGrid;
	}

}
