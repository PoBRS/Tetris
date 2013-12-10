
package tetris.backend;

/**
 * Interface qui réagit au changement de score.
 * 
 * @author Pierre-Olivier Boulet
 * @author Raphaël Sylvain
 * 
 * @see Game
 * 
 * @since 09/12/2013
 * 
 */

public interface ScoreListener
{
    public void onScoreUpdated(int score);

}
