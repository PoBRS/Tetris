
package tetris.backend;

/**
 * Interface qui réagit à la completion d'une ligne.
 * 
 * @author Pierre-Olivier Boulet
 * @author Raphaël Sylvain
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
