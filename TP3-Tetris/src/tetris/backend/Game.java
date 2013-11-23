package tetris.backend;

public class Game
{
	private Case gameGrid[][];
	private final static int NBCASES_X = 10;
	private final static int NBCASES_Y = 24;

	public static void main(String[] args)
	{
		Game game = new Game();
	}

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
		SpawnTetrimino();
		PrintGame();
	}

	public void SpawnTetrimino()
	{
		Tetrimino tetrimino = new Tetrimino(EnumShape.O, this.gameGrid);
		tetrimino.MoveDown();
		tetrimino.MoveDown();
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
}
