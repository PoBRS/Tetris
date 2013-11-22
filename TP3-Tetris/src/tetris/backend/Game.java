package tetris.backend;

public class Game
{
	private Case gameGrid[][];

	public Game(int nbCasesX, int nbCasesY)
	{
		for (int i = 0; i < nbCasesX; i++)
		{
			for (int j = 0; j < nbCasesY; j++)
			{
				gameGrid[i][j] = new Case(i, j);
			}
		}
	}

	public SpawnTetrimino()
	{
		Tetrimino tetrimino = new Tetrimino(null, gameGrid);
	}

	public void Pause()
	{

	}

}
