
package tetris.userInterface;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Classe représentant l'affichage du prochain Tetromino
 * 
 * @author Pierre-Olivier Boulet
 * @author Raphaël Sylvain
 * 
 * @since 09/12/2013
 * 
 * @see HUD
 * 
 */
public class NextTetromino extends GridPane
{
    private static final double GRID_CASE_FILLED_OPACITY = 0.8;
    private static final double GRID_CASE_BASE_OPACITY = 0.3;
    private static final int GRID_CASE_WIDTH = 30;
    private static final int GRID_CASE_HEIGHT = 30;
    private static final Color GRID_CASE_BASE_COLOR = Color.WHITE;
    Rectangle[][] gridArray = new Rectangle[4][4];

    /**
     * Constructeur de l'affichage du prochain Tetromino.
     * Crée une grille.
     */
    public NextTetromino()
    {
	super();
	for (int y = 0; y < 4; y++)
	{
	    for (int x = 0; x < 4; x++)
	    {
		Rectangle nextTetrominoBlock = new Rectangle();
		nextTetrominoBlock.setHeight(NextTetromino.GRID_CASE_HEIGHT);
		nextTetrominoBlock.setWidth(NextTetromino.GRID_CASE_WIDTH);
		nextTetrominoBlock.setFill(NextTetromino.GRID_CASE_BASE_COLOR);
		nextTetrominoBlock.setStroke(Color.BLACK);
		nextTetrominoBlock.setStrokeWidth(1);
		nextTetrominoBlock.setOpacity(NextTetromino.GRID_CASE_BASE_OPACITY);
		gridArray[x][y] = nextTetrominoBlock;
		this.add(nextTetrominoBlock, x, y);
	    }
	}

    }

    /**
     * Fonction change la couleur de la grille de l'affichage du NextTetromino
     * 
     * @param x
     *            -> La position horizontal dans la grille à colorier.
     * @param y
     *            -> La position vertical dans la grille à colorier.
     * @param blockColor
     *            -> La couleur avec laquelle remplir la grille.
     */
    public void fillItem(int x, int y, Color blockColor)
    {
	gridArray[x][y].setFill(blockColor);
	gridArray[x][y].setOpacity(NextTetromino.GRID_CASE_FILLED_OPACITY);

    }

    /**
     * Fonction qui remet toutes les cases de la grilles de l'affichage
     * du prochain tetromino dans leur étant originel.
     */
    public void resetItem()
    {

	for (int y = 0; y < 4; y++)
	{
	    for (int x = 0; x < 4; x++)
	    {
		gridArray[x][y].setFill(NextTetromino.GRID_CASE_BASE_COLOR);
		gridArray[x][y].setOpacity(NextTetromino.GRID_CASE_BASE_OPACITY);
	    }
	}
    }

}
