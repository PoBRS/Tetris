package tetris.backend;

public class Tetrimino {
	
	private Block block1;
	private Block block2;
	private Block block3;
	private Block block4;
	
	public Tetrimino(EnumShape shape)
	{
		//Spawn blocks
		if (shape == EnumShape.O)
		{
			this.block1 = 
		}
	}
	
	public void Move()
	{
		
		//Bas (Selon le timer tick)

		//Gauche (Selon la flèche pesée. Accélère pendant le hold)
		
		//Droite (Selon la flèche pesée. Accélère pendant le hold)
	}
	
	public void Rotate()
	{
		//Algo selon pivot
	}
	
	private boolean Deactivate()
	{
		return false;
	}
	
	
	
}
