
package tetris.userInterface.board;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Classe représentant l'affichage de la grille de jeu.
 * 
 * @author Pierre-Olivier Boulet
 * @author Raphaël Sylvain
 * 
 * @since 09/12/2013
 * 
 */
public class Grid extends GridPane
{
    private static final int MAX_X = 10;
    private static final int MAX_Y = 20;
    private Rectangle rectangleArray[][] = new Rectangle[MAX_X][MAX_Y];
    private ArrayList<Rectangle> rectangleForPause = new ArrayList<>();
    private Group root;

    /**
     * Constructeur de grille
     * 
     * @param root
     *            -> Group où ajouter la grille de jeu.
     */
    public Grid(Group root)
    {
	this.root = root;
	this.createEmptyGrid();
	this.setRectangleForPause();
    }

    /**
     * Fonction affichant l'état pause de la grille de jeu.
     */
    public void PauseDisplay()
    {
	for (Rectangle gridCase : this.rectangleForPause)
	{
	    gridCase.setFill(Color.WHITE);
	}
    }

    /**
     * Fonction affichange l'état normal de la grille de jeu.
     */
    public void UnPauseDisplay()
    {
	for (Rectangle gridCase : this.rectangleForPause)
	{
	    gridCase.setFill(Color.BLACK);
	}
    }

    /**
     * Fonction qui construit une grille de jeu et l'ajoute dans le root.
     */
    private void createEmptyGrid()
    {
	for (int x = 0; x < MAX_X; x++)
	{
	    for (int y = 0; y < MAX_Y; y++)
	    {
		Rectangle emptySpace = new Rectangle();
		emptySpace.setHeight(28);
		emptySpace.setWidth(28);
		emptySpace.setOpacity(0.5);
		emptySpace.setFill(Color.BLACK);
		emptySpace.setStroke(Color.BLACK);
		emptySpace.setStrokeWidth(2.5);
		this.rectangleArray[x][y] = emptySpace;
		this.add(emptySpace, x, y);
	    }
	}
	this.root.getChildren().add(this);
    }

    /**
     * Fonction créant et jouant l'animation de la fin d'une partie.
     * 
     * @todo -> Faire l'animation avec un timeLine.
     */
    public void GameEndEvent()
    {
	for (int y = MAX_Y - 1; y >= 0; y--)
	{
	    for (int x = 0; x < MAX_X; x++)
	    {
		this.rectangleArray[x][y].setOpacity(1);

		try
		{
		    // 0.01 seconds pause for an animation.
		    Thread.sleep(10);
		}
		catch (InterruptedException e)
		{
		}
	    }
	}
    }

    /**
     * Fonction sélectionnant les cases à modifiés pour afficher l'état de jeu
     * 
     * @todo -> Séparer cette fonction en sous-fonction pour avoir un code plus propre.
     */
    private void setRectangleForPause()
    {
	// P
	for (int x = 3; x < 8; x++)
	{
	    this.rectangleForPause.add(this.rectangleArray[x][0]);
	}
	this.rectangleForPause.add(this.rectangleArray[5][1]);
	this.rectangleForPause.add(this.rectangleArray[7][1]);
	for (int x = 5; x < 8; x++)
	{
	    this.rectangleForPause.add(this.rectangleArray[x][2]);
	}

	// A
	for (int x = 3; x < 8; x++)
	{
	    for (int y = 4; y < 7; y = y + 2)
	    {
		this.rectangleForPause.add(this.rectangleArray[x][y]);
	    }

	}
	this.rectangleForPause.add(this.rectangleArray[5][5]);
	this.rectangleForPause.add(this.rectangleArray[7][5]);

	// U
	for (int x = 3; x < 8; x++)
	{
	    for (int y = 8; y < 11; y = y + 2)
	    {
		this.rectangleForPause.add(this.rectangleArray[x][y]);
	    }

	}
	this.rectangleForPause.add(this.rectangleArray[3][9]);

	// S
	for (int y = 12; y < 15; y++)
	{
	    for (int x = 3; x < 8; x = x + 2)
	    {
		this.rectangleForPause.add(this.rectangleArray[x][y]);
	    }
	}
	this.rectangleForPause.add(this.rectangleArray[6][12]);
	this.rectangleForPause.add(this.rectangleArray[4][14]);

	// E
	for (int y = 16; y < 19; y++)
	{
	    for (int x = 3; x < 8; x = x + 2)
	    {
		this.rectangleForPause.add(this.rectangleArray[x][y]);
	    }
	}

	this.rectangleForPause.add(this.rectangleArray[6][16]);
	this.rectangleForPause.add(this.rectangleArray[4][16]);

    }

}