
package tetris.userInterface;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import tetris.backend.LineListener;

public class Chronometer implements LineListener
{
    private Timeline timelineAnimations;
    private boolean active = false;
    private MainScene mainScene;
    private int line = 0;
    private int blockPosX = 0;

    public Chronometer(final MainScene mainScene)
    {
	this.mainScene = mainScene;
	this.mainScene.getCurrentGame().setLineListener(this);
	this.timelineAnimations = new Timeline(new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>()
	{

	    @Override
	    public void handle(ActionEvent event)
	    {
		if (Chronometer.this.blockPosX >= 10)
		{
		    Chronometer.this.stopChronometer();
		    Chronometer.this.mainScene.getCurrentGame().setAllowedToClearLine(true);
		}
		else
		{
		    (Chronometer.this.mainScene.getBlocks())[Chronometer.this.blockPosX][Chronometer.this.line - 4].flash();
		    blockPosX++;
		}
	    }
	}));

	this.timelineAnimations.setCycleCount(Timeline.INDEFINITE);
    }

    // public void resetTimer(final double timerInterval)
    // {
    // KeyFrame keyFrame = new KeyFrame(Duration.millis(timerInterval), new EventHandler<ActionEvent>()
    // {
    // @Override
    // public void handle(ActionEvent event)
    // {
    // if (!mainScene.getCurrentGame().getCurrentTetrimino().MoveDown())
    // {
    // Chronometer.this.mainScene.getCrash().play();
    //
    // if (mainScene.getCurrentGame().LostGame())
    // {
    // Chronometer.this.stopChronometer();
    // }
    //
    // int lineToCheck = mainScene.getCurrentGame().getCurrentTetrimino().findLandingPosY();
    // int numberLineCompleted = 0;
    // for (int i = lineToCheck - 3; i <= lineToCheck; i++)
    // {
    // if (mainScene.getCurrentGame().CheckLineIsComplete(i))
    // {
    // mainScene.getCurrentGame().ClearLine(i);
    // numberLineCompleted++;
    // }
    // }
    // mainScene.getHud().changeEvent(numberLineCompleted);
    // mainScene.getCurrentGame().SpawnTetrimino();
    // mainScene.getHud().resetNextTetromino();
    // mainScene.getHud().setScore(numberLineCompleted);
    // }
    // }
    // });
    // this.timelineInGame.stop();
    // this.timelineInGame.getKeyFrames().setAll(keyFrame);
    // this.timelineInGame.play();
    // }

    public void stopChronometer()
    {
	this.timelineAnimations.stop();
	this.active = false;

    }

    public void startChronometer()
    {
	Chronometer.this.blockPosX = 0;
	this.timelineAnimations.play();
	this.active = true;
    }

    public boolean isActive()
    {
	return active;
    }

    public void onLineCompleted(int line)
    {
	this.line = line;
	this.startChronometer();
    }

}