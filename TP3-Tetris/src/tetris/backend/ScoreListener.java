
package tetris.backend;

/**
 * Interface qui r�agit au changement de score.
 * 
 * @author Pierre-Olivier Boulet
 * @author Rapha�l Sylvain
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
