
package tetris.userInterface;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import tetris.backend.LineListener;

/**
 * Classe qui g�re l'animation des BlockGraphics de la grille de jeu.
 * 
 * @author Pierre-Olivier Boulet
 * @author Rapha�l Sylvain
 * 
 * @see BlockGraphics
 * 
 * @since 09/12/2013
 * 
 */
public class Chronometer implements LineListener
{
    private Timeline timelineAnimations;
    private MainScene mainScene;
    private int line = 0;
    private int blockPosX = 0;

    /**
     * Constructeur de chronometer.
     * 
     * @param mainScene
     *            -> La MainScene sur laquelle l'animation se d�roulera.
     */
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
		    Chronometer.this.StopChronometer();
		    for (int i = 0; i < 10; i++)
		    {
			(Chronometer.this.mainScene.getBlocks())[i][Chronometer.this.line - 4].unflash();
		    }
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

    /**
     * Fonction qui arr�te toutes animations.
     */
    public void StopChronometer()
    {
	this.timelineAnimations.stop();
    }

    /**
     * Fonction qui d�marre l'animation
     */
    private void startChronometer()
    {
	this.blockPosX = 0;
	this.timelineAnimations.play();
    }

    /**
     * 
     * L'action r�alis� lorsque le LineListener lance une notification.
     * On d�marre l'animation
     * 
     * @param line
     *            -> la ligne sur laquelle l'animation va se d�rouler.
     */
    public void onLineCompleted(int line)
    {
	this.line = line;
	this.startChronometer();
    }

}