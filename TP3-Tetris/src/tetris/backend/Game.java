
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
		this.gameGrid[i][j] = new Case(i, j);
	    }
	}
    }

    public void SpawnTetrimino()
    {
	Tetrimino tetrimino = new Tetrimino(null, this.gameGrid);
    }

    public void Pause()
    {

    }

}
