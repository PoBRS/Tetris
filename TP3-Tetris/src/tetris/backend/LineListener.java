
package tetris.backend;

/**
 * Interface qui r�agit � la completion d'une ligne.
 * 
 * @author Pierre-Olivier Boulet
 * @author Rapha�l Sylvain
 * 
 * @see Game
 * 
 * @since 09/12/2013
 * 
 */
public interface LineListener
{

    public void onLineCompleted(int line);
}
