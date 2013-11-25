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
	private Tetrimino currentTetrimino;
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

		if (this.currentTetrimino != null)
		{
			this.currentTetrimino.Deactivate();
		}
		this.currentTetrimino = new Tetrimino(next, this.gameGrid);
		this.next = VALUES.get(RANDOM.nextInt(SIZE));
	}

	public Tetrimino getCurrentTetrimino()
	{
		return currentTetrimino;
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
