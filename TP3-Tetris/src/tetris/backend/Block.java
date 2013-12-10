
package tetris.backend;

import javafx.scene.paint.Color;

/**
 * Classe représentant un bloc.
 * 
 * @author Pierre-Olivier Boulet
 * @author Raphaël Sylvain
 * 
 * @see Tetromino
 * @see Case
 * 
 * @since 09/12/2013
 */

public class Block
{

    private int posX = 0;
    private int posY = 0;
    private boolean pivot = false;
    private boolean fixed = false;
    private Color colorBlock;

    /**
     * Constructeur de block.
     * 
     * @param posX
     *            -> Position X du bloque dans le jeu.
     * @param pos
     *            -> Position Y du bloque dans le jeu.
     * @param pivot
     *            -> Si ce bloque doit être considéré comme un pivot. True s'il est un pivot.
     * @param colorBlock
     *            -> La couleur du bloque.
     * 
     */
    public Block(int posX, int posY, boolean pivot, Color colorBlock)
    {
	this.pivot = pivot;
	this.setPosX(posX);
	this.setPosY(posY);
	this.setColorBlock(colorBlock);
    }

    /**
     * Getter de posY
     * 
     * @return -> La position Y
     */
    public int getPosY()
    {
	return this.posY;
    }

    /**
     * Setter de posY
     * 
     * @param posY
     *            -> La nouvelle position Y
     */
    public void setPosY(int posY)
    {
	this.posY = posY;
    }

    /**
     * Getter de posX
     * 
     * @return -> La position X
     */
    public int getPosX()
    {
	return this.posX;
    }

    /**
     * Setter de posX
     * 
     * @param posX
     *            -> La nouvelle position X
     */
    public void setPosX(int posX)
    {
	this.posX = posX;
    }

    /**
     * Getter de pivot
     * 
     * @return -> True si le block est un pivot; False sinon.
     */
    public boolean isPivot()
    {
	return this.pivot;
    }

    /**
     * Getter de fixed
     * 
     * @return -> True si le block est fixé; False sinon.
     */
    public boolean isFixed()
    {
	return this.fixed;
    }

    /**
     * Setter de fixed
     * 
     * @param fixed
     *            -> La nouvelle valeur de fixed.
     */
    public void setFixed(boolean fixed)
    {
	this.fixed = fixed;
    }

    /**
     * Getter de la couleur du block;
     * 
     * @return -> La couleur du block.
     * @see Colors
     */
    public Color getColorBlock()
    {
	return this.colorBlock;
    }

    /**
     * Setter de la couleur du block;
     * 
     * @param colorBlock
     *            -> La nouvelle couleur du block.
     * @see Colors
     */
    private void setColorBlock(Color colorBlock)
    {
	this.colorBlock = colorBlock;
    }
}
