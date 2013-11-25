package tetris.userInterface;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import tetris.backend.Case;
import tetris.backend.Game;

public class MainScene extends Scene
{
	private Game currentGame;
	private Chronometer chronometer;

	public MainScene(Stage primaryStage, Group root)
	{
		super(root);

		this.currentGame = new Game();
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

		this.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			public void handle(KeyEvent key)
			{
				if (key.getCode() == KeyCode.LEFT)
				{
					if (MainScene.this.chronometer.isActive())
					{
						MainScene.this.currentGame.getCurrentTetrimino().MoveLeft();
					}
				}

				if (key.getCode() == KeyCode.RIGHT)
				{
					if (MainScene.this.chronometer.isActive())
					{
						MainScene.this.currentGame.getCurrentTetrimino().MoveRight();
					}
				}

				if (key.getCode() == KeyCode.DOWN)
				{
					if (MainScene.this.chronometer.isActive())
					{
						if (!MainScene.this.getCurrentGame().getCurrentTetrimino().MoveDown())
						{
							MainScene.this.getCurrentGame().SpawnTetrimino();
						}
					}

				}

				if (key.getCode() == KeyCode.UP)
				{
					if (MainScene.this.chronometer.isActive())
					{
						MainScene.this.currentGame.getCurrentTetrimino().Rotate();
					}

				}

				if (key.getCode() == KeyCode.ENTER)
				{
					if (MainScene.this.chronometer.isActive())
					{
						MainScene.this.chronometer.arreterChronometre();
					}
					else
					{
						MainScene.this.chronometer.lancerChronometre();
					}
				}
			}
		});
		this.chronometer.lancerChronometre();
	}

	// gameGrid.add(new BlockGraphics(), 8, 18);

	public Game getCurrentGame()
	{
		return this.currentGame;
	}

}
