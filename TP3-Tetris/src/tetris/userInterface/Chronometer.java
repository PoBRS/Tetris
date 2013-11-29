
package tetris.userInterface;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class Chronometer
{
    private Timeline timelineInGame;
    private boolean active = false;
    private MainScene mainScene;

    public Chronometer(final MainScene mainScene)
    {
	this.mainScene = mainScene;
	this.timelineInGame = new Timeline(new KeyFrame(Duration.millis(800), new EventHandler<ActionEvent>()
	{

	    @Override
	    public void handle(ActionEvent event)
	    {
		Chronometer.this.resetTimer(800);
	    }
	}));

	this.timelineInGame.setCycleCount(Timeline.INDEFINITE);
    }

    public void resetTimer(final double timerInterval)
    {
	KeyFrame keyFrame = new KeyFrame(Duration.millis(timerInterval), new EventHandler<ActionEvent>()
	{
	    @Override
	    public void handle(ActionEvent event)
	    {
		if (!mainScene.getCurrentGame().getCurrentTetrimino().MoveDown())
		{
		    Chronometer.this.mainScene.getCrash().play();

		    if (mainScene.getCurrentGame().LostGame())
		    {
			Chronometer.this.stopChronometer();
		    }

		    int lineToCheck = mainScene.getCurrentGame().getCurrentTetrimino().findLandingPosY();
		    int numberLineCompleted = 0;
		    for (int i = lineToCheck - 3; i <= lineToCheck; i++)
		    {
			if (mainScene.getCurrentGame().CheckLineIsComplete(i))
			{
			    mainScene.getCurrentGame().ClearLine(i);
			    numberLineCompleted++;
			}
		    }
		    mainScene.getHud().changeEvent(numberLineCompleted);
		    mainScene.getCurrentGame().SpawnTetrimino();
		    mainScene.getHud().resetNextTetromino();
		    mainScene.getHud().setScore(numberLineCompleted);
		}
	    }
	});
	this.timelineInGame.stop();
	this.timelineInGame.getKeyFrames().setAll(keyFrame);
	this.timelineInGame.play();
    }

    public void stopChronometer()
    {
	this.timelineInGame.stop();
	this.active = false;

    }

    public void startChronometer()
    {
	this.timelineInGame.play();
	this.active = true;
    }

    public boolean isActive()
    {
	return active;
    }

}