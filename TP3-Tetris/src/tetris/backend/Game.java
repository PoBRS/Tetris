package tetris.backend;

public class Game {
	private Block gameGrid[][];
			
	public Game(int nbCasesX, int nbCasesY)
	{
		for (int i = 0; i < nbCasesX; i++)
		{
			for (int j = 0; j < nbCasesY; j++)
			{
				gameGrid[i][j] = new Block(i,j);
			}
		}
	}
	
	public void Pause()
	{
		
	}
	
	
}
