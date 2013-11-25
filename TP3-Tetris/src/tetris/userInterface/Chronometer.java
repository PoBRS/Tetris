package tetris.userInterface;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.util.Duration;

public class Chronometer
{
	private Timeline timelineInGame;
	private boolean active = false;
	private Scene mainScene;

	public Chronometer(final MainScene mainScene)
	{
		this.mainScene = mainScene;
		this.timelineInGame = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				if (!mainScene.getCurrentGame().getCurrentTetrimino().MoveDown())
				{
					mainScene.getCurrentGame().SpawnTetrimino();
				}
			}
		}));

		this.timelineInGame.setCycleCount(Timeline.INDEFINITE);
	}

	public void arreterChronometre()
	{
		this.timelineInGame.stop();
		this.active = false;

	}

	public void lancerChronometre()
	{
		this.timelineInGame.play();
		this.active = true;
	}

	public boolean isActive()
	{
		return active;
	}

}