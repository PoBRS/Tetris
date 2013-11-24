package tetris.backend;

public class Game
{
	private Case gameGrid[][];

	private final static int NBCASES_X = 10;
	private final static int NBCASES_Y = 24;
	private Tetrimino currentTetrimino;

	public Game()
	{
		this.gameGrid = new Case[NBCASES_X][NBCASES_Y];
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
		this.currentTetrimino = new Tetrimino(EnumShape.O, this.gameGrid);
	}

	public Tetrimino getCurrentTetrimino()
	{
		return currentTetrimino;
	}

	public void Pause()
	{

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
