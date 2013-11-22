package tetris.backend;

public class Tetrimino
{

	private Block[] blocks;

	public Tetrimino(EnumShape shape, Case[][] gameGrid)
	{

	}

	public void MoveLeft()
	{
		// Gauche (Selon la flèche pesée. Accélère pendant le hold)
	}

	public void MoveRight()
	{
		// Droite (Selon la flèche pesée. Accélère pendant le hold)
	}

	public boolean MoveDown()
	{
		// Bas (Selon le timer tick)

		// Si le bloc ne peut plus descendre
		return false;
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
		return blocks;
	}

}
