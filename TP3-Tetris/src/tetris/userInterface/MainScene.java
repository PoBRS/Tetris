
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
import tetris.backend.Game;

public class MainScene extends Scene
{
    private Game currentGame;

    private Chronometer chronometer;
    private AudioClip spin;
    private AudioClip beep;
    private AudioClip crash;

    private AudioClip tetrisA;

    public MainScene(Stage primaryStage, Group root)
    {
	super(root);

	this.currentGame = new Game();
	HUD hud = new HUD(root);

	Grid gameGrid = new Grid(root);

	Case[][] matchingCase = this.currentGame.getGameGrid();
	this.chronometer = new Chronometer(this);

	for (int x = 0; x < 10; x++)
	{
	    for (int y = 0; y < 20; y++)
	    {
		gameGrid.add(new BlockGraphics(matchingCase[x][y + 4]), x, y);
	    }
	}
	this.currentGame.SpawnTetrimino();
	hud.setCenter(gameGrid);
	hud.setRight(new Score(root));

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
		    if (MainScene.this.chronometer.isActive())
		    {
			MainScene.this.currentGame.getCurrentTetrimino().MoveLeft();
			MainScene.this.beep.play();
		    }
		}

		if (key.getCode() == KeyCode.RIGHT)
		{
		    if (MainScene.this.chronometer.isActive())
		    {
			MainScene.this.currentGame.getCurrentTetrimino().MoveRight();
			MainScene.this.beep.play();
		    }
		}

		if (key.getCode() == KeyCode.DOWN)
		{
		    if (MainScene.this.chronometer.isActive())
		    {
			MainScene.this.getCurrentGame().getCurrentTetrimino().MoveDown();
		    }

		}

		if (key.getCode() == KeyCode.UP)
		{
		    if (MainScene.this.chronometer.isActive())
		    {
			MainScene.this.currentGame.getCurrentTetrimino().RotateCallManager();
			MainScene.this.spin.play();
		    }

		}

		if (key.getCode() == KeyCode.ENTER)
		{
		    if (MainScene.this.chronometer.isActive())
		    {
			MainScene.this.chronometer.stopChronometer();
			;
		    }
		    else
		    {
			MainScene.this.chronometer.startChronometer();
		    }
		}

		if (key.getCode() == KeyCode.SPACE)
		{
		    while (MainScene.this.getCurrentGame().getCurrentTetrimino().MoveDown())
			;
		}
	    }
	});
	this.chronometer.startChronometer();

    }

    // gameGrid.add(new BlockGraphics(), 8, 18);

    public Game getCurrentGame()
    {
	return this.currentGame;
    }

    public AudioClip getCrash()
    {
	return crash;
    }
}