
package tetris.backend;

/**
 * Interface qui r�agit � l'apparition d'un nouveau Tetromino dans le jeu.
 * 
 * @author Pierre-Olivier Boulet
 * @author Rapha�l Sylvain
 * 
 * @see Game
 * 
 * @since 09/12/2013
 * 
 */

public interface NewTetrominoListener
{
    public void onNewTetromino(EnumShape nextTetromino);
}
