
package tetris.userInterface;

import java.io.File;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import tetris.backend.Case;
import tetris.backend.EnumShape;
import tetris.backend.Game;
import tetris.backend.NewTetrominoListener;

public class MainScene extends Scene implements NewTetrominoListener
{
    private Game currentGame;
    private AudioClip spin;
    private AudioClip beep;
    private AudioClip crash;
    private BlockGraphics[][] blocks = new BlockGraphics[10][20];

    private HUD hud;
    private Chronometer chronometer;
    private int numberLineCompleted;

    public MainScene(Stage primaryStage, Group root)
    {
	super(root);
	this.numberLineCompleted = 0;
	this.currentGame = new Game();
	setHud(new HUD(root, this.currentGame));
	this.currentGame.setNewTetrominoListener(this);
	Grid gameGrid = new Grid(root);

	Case[][] matchingCase = this.currentGame.getGameGrid();

	this.chronometer = new Chronometer(this);

	for (int x = 0; x < 10; x++)
	{
	    for (int y = 0; y < 20; y++)
	    {
		blocks[x][y] = new BlockGraphics(matchingCase[x][y + 4]);
		gameGrid.add(blocks[x][y], x, y);
	    }
	}
	getHud().setCenter(gameGrid);
	// hud.setRight(new Score(root));
	// this.currentGame.SpawnTetrimino();
	String linkTetrisA = new File("ressources/TetrisA.mp3").toURI().toString();
	Runnable musicRunnable = new MusicPlayer(linkTetrisA);
	Thread musicThread = new Thread(musicRunnable);
	musicThread.start();

	String linkSpin = new File("ressources/Spin.mp3").toURI().toString();
	this.spin = new AudioClip(linkSpin);
	this.spin.setVolume(0.5);

	String linkBeep = new File("ressources/Beep.mp3").toURI().toString();
	this.beep = new AudioClip(linkBeep);

	String linkCrash = new File("ressources/Crash.mp3").toURI().toString();
	this.crash = new AudioClip(linkCrash);

	this.setOnKeyPressed(new EventHandler<KeyEvent>()
	{
	    public void handle(KeyEvent key)
	    {
		if (key.getCode() == KeyCode.LEFT)
		{
		    MainScene.this.currentGame.getCurrentTetrimino().MoveLeft();
		    MainScene.this.beep.play();
		}

		if (key.getCode() == KeyCode.RIGHT)
		{
		    MainScene.this.currentGame.getCurrentTetrimino().MoveRight();
		    MainScene.this.beep.play();
		}
		if (key.getCode() == KeyCode.DOWN)
		{

		    MainScene.this.getCurrentGame().getCurrentTetrimino().MoveDown();

		}

		if (key.getCode() == KeyCode.UP)
		{

		    MainScene.this.currentGame.getCurrentTetrimino().RotateCallManager();
		    MainScene.this.spin.play();
		}

		if (key.getCode() == KeyCode.ENTER)
		{
		    // Add code to pause the game
		}

		if (key.getCode() == KeyCode.SPACE)
		{
		    while (MainScene.this.getCurrentGame().getCurrentTetrimino().MoveDown())
		    {
			// Repeat until it's completely down, please.
		    }
		}
	    }
	});

    }

    public Game getCurrentGame()
    {
	return this.currentGame;
    }

    public AudioClip getCrash()
    {
	return crash;
    }

    public HUD getHud()
    {
	return hud;
    }

    public void setHud(HUD hud)
    {
	this.hud = hud;
    }

    public BlockGraphics[][] getBlocks()
    {
	return blocks;
    }

    @Override
    public void onNewTetromino(EnumShape nextTetromino)
    {
	this.crash.play();

    }
}