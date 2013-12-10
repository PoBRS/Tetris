
package tetris.backend.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import tetris.backend.Block;
import tetris.backend.Case;
import tetris.backend.EnumShape;
import tetris.backend.Game;
import tetris.backend.Tetromino;

public class TetrominoTests
{
    private Game game;
    private Block block;
    private Tetromino tetromino;
    private Case[][] emptyGameGrid;

    @Before
    public void initTestEnvironnment()
    {
	this.emptyGameGrid = new Case[10][24];
	for (int i = 0; i < 10; i++)
	{
	    for (int j = 0; j < 24; j++)
	    {
		this.emptyGameGrid[i][j] = new Case();
	    }

	}

	game = mock(Game.class);
	block = mock(Block.class);

	when(game.getNextTetromino()).thenReturn(EnumShape.S);
	when(game.getGameGrid()).thenReturn(emptyGameGrid);

	when(block.isFixed()).thenReturn(true);

	this.tetromino = new Tetromino(game);
    }

    @Test
    public void givenEnumShape_whenConstructingTetromino_thenReturnNothing()
    {

	for (int blockNum = 0; blockNum < 4; blockNum++)
	{
	    assertNotNull(this.tetromino.getBlocks()[blockNum]);
	}

    }

    @Test
    public void givenMoveLeft_whenEmptyLeftCase_thenReturnTrue()
    {
	assertTrue(this.tetromino.MoveLeft());
    }

    @Test
    public void givenMoveLeft_whenFilledLeftCase_thenReturnFalse()
    {
	// Filling the entire board with blocks. Does not override the tetromino.
	for (int i = 0; i < 10; i++)
	{
	    for (int j = 0; j < 24; j++)
	    {
		this.emptyGameGrid[i][j].setBlock(this.block);
	    }
	}

	assertFalse(this.tetromino.MoveLeft());
    }

    @Test
    public void givenMoveLeft_whenFirstColumn_thenReturnFalse()
    {
	while (this.tetromino.MoveLeft())
	{
	    // Keep moving left until the tetromino is on the first column.
	    // The blocks are instanciated in a fixed position in a private method.
	}
	assertFalse(this.tetromino.MoveLeft());
    }

    @Test
    public void givenMoveRight_whenEmptyRightCase_thenReturnTrue()
    {
	assertTrue(this.tetromino.MoveRight());
    }

    @Test
    public void givenMoveRight_whenFilledRightCase_thenReturnFalse()
    {
	// Filling the entire board with blocks. Does not override the tetromino.
	for (int i = 0; i < 10; i++)
	{
	    for (int j = 0; j < 24; j++)
	    {
		this.emptyGameGrid[i][j].setBlock(this.block);
	    }
	}

	assertFalse(this.tetromino.MoveRight());
    }

    @Test
    public void givenMoveRight_whenLastColumn_thenReturnFalse()
    {
	while (this.tetromino.MoveRight())
	{
	    // Keep moving right until the tetromino is on the first column.
	    // The blocks are instanciated in a fixed position in a private method.
	}
	assertFalse(this.tetromino.MoveRight());
    }

    @Test
    public void givenMoveDown_whenEmptyDownCase_thenReturnTrue()
    {
	assertTrue(this.tetromino.MoveDown());
    }

    @Test
    public void givenMoveDown_whenFilledDownCase_thenReturnFalse()
    {
	// Filling the entire board with blocks. Does not override the tetromino.
	for (int i = 0; i < 10; i++)
	{
	    for (int j = 0; j < 24; j++)
	    {
		this.emptyGameGrid[i][j].setBlock(this.block);
	    }
	}

	assertFalse(this.tetromino.MoveDown());
    }

    @Test
    public void givenMoveDown_whenLastLine_thenReturnFalse()
    {
	while (this.tetromino.MoveDown())
	{
	    // Keep moving down until the tetromino is on the ground (last line).
	    // The blocks are instanciated in a fixed position in a private method.
	}
	assertFalse(this.tetromino.MoveDown());
    }

    @Test
    public void givenfindLandingPosYTest_whenNotMoveDown_thenReturnLandingPosY()
    {
	while (this.tetromino.MoveDown())
	{
	    // Hit the last available case.
	}

	// Should hit the ground since no other blocks are in the game.
	assertEquals(23, this.tetromino.findTetrominoLowestPosition());
    }

    @Test
    public void givenRotateCallManager_whenValidRotation_thenReturnTrue()
    {
	assertTrue(this.tetromino.RotateCallManager());
    }

    @Test
    public void givenRotateCallManager_whenIllegalRotation_thenReturnFalse()
    {
	// Filling the entire board with blocks. Does not override the tetromino.
	for (int i = 0; i < 10; i++)
	{
	    for (int j = 0; j < 24; j++)
	    {
		this.emptyGameGrid[i][j].setBlock(this.block);
	    }
	}
	assertFalse(this.tetromino.RotateCallManager());
    }

    @Test
    public void givenDeactivate_whenTetrominoIsActive_thenTetrominoBlocksAreFixed()
    {
	this.tetromino.Deactivate();
	for (int i = 0; i < 4; i++)
	{
	    assertTrue(this.tetromino.getBlocks()[i].isFixed());
	}
    }
}
