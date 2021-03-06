
package tetris.backend;

/**
 * Interface qui r�agit au changement d'une case.
 * 
 * @author Pierre-Olivier Boulet
 * @author Rapha�l Sylvain
 * 
 * @see Case
 * 
 * @since 09/12/2013
 * 
 */
public interface CaseListener
{

    public void onCaseStateChanged();

}
