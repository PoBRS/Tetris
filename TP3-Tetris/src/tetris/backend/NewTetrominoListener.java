
package tetris.backend;

/**
 * Interface qui réagit à l'apparition d'un nouveau Tetromino dans le jeu.
 * 
 * @author Pierre-Olivier Boulet
 * @author Raphaël Sylvain
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
