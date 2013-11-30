
package tetris.backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Game
{
    private Case gameGrid[][];

    private final static int NBCASES_X = 10;
    private final static int NBCASES_Y = 24;
    private final static int NBCASES_Y_JEU = 20;
    private Tetromino currentTetromino;
    private EnumShape nextTetromino;
    private TimerTask GameEvents;
    private Timer timer;
    private boolean allowedToClearLine = false;
    private boolean allowedToSpawnTetromino = false;
    private LineListener lineListener;
    private ArrayList<NewTetrominoListener> newTetrominoListener = new ArrayList<>();

    private static final List<EnumShape> VALUES = Collections.unmodifiableList(Arrays.asList(EnumShape.values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public Game()
    {

	this.gameGrid = new Case[NBCASES_X][NBCASES_Y];
	this.nextTetromino = VALUES.get(RANDOM.nextInt(SIZE));

	for (int i = 0; i < NBCASES_X; i++)
	{
	    for (int j = 0; j < NBCASES_Y; j++)
	    {
		this.gameGrid[i][j] = new Case(i, j);
	    }

	}

	timer = new Timer();
	this.GameEvents = new TimerTask()
	{
	    @Override
	    public void run()
	    {
		if (Game.this.currentTetromino != null)
		{
		    Update();
		}
	    }
	};
	timer.scheduleAtFixedRate(GameEvents, 0, 400);
	this.SpawnTetrimino();
    }

    public void Update()
    {
	if (!this.getCurrentTetrimino().MoveDown())
	{
	    this.allowedToSpawnTetromino = true;
	    if (this.LostGame())
	    {
		this.timer.cancel();
		this.timer.purge();
	    }

	    int lineToCheck = this.getCurrentTetrimino().findLandingPosY();
	    for (int i = lineToCheck - 3; i <= lineToCheck; i++)
	    {
		this.allowedToClearLine = false;

		if (this.CheckLineIsComplete(i))
		{
		    while (this.allowedToClearLine == false)
		    {
			// Wait
		    }
		    this.ClearLine(i);
		}
	    }
	    while (this.allowedToSpawnTetromino == false)
	    {
	    }
	    this.SpawnTetrimino();
	}

    }

    private void SpawnTetrimino()
    {

	if (this.currentTetromino != null)
	{
	    this.currentTetromino.Deactivate();
	}
	this.currentTetromino = new Tetromino(this.nextTetromino, this.gameGrid);
	this.nextTetromino = VALUES.get(RANDOM.nextInt(SIZE));

	if (this.newTetrominoListener != null)
	{
	    for (NewTetrominoListener listener : this.newTetrominoListener)
	    {
		listener.onNewTetromino(this.nextTetromino);
	    }
	}

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
	    this.lineListener.onLineCompleted(line);
	    return true;
	}
	else
	{
	    return false;
	}
    }

    public void ClearLine(int line)
    {
	this.allowedToSpawnTetromino = false;
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

	this.allowedToSpawnTetromino = true;
    }

    public void setLineListener(LineListener lineListener)
    {
	this.lineListener = lineListener;
    }

    public void setNewTetrominoListener(NewTetrominoListener newTetrominoListener)
    {
	this.newTetrominoListener.add(newTetrominoListener);
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

    public EnumShape getNextTetromino()
    {
	return this.nextTetromino;
    }

    public void setNextTetromino(EnumShape next)
    {
	this.nextTetromino = next;
    }

    public void setAllowedToClearLine(boolean allowedToClearLine)
    {
	this.allowedToClearLine = allowedToClearLine;
    }

}
