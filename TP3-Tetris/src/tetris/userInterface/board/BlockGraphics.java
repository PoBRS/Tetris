
package tetris.userInterface.board;

import javafx.scene.effect.Glow;
import javafx.scene.shape.Rectangle;
import tetris.backend.Case;
import tetris.backend.CaseListener;

/**
 * Classe qui repr�sente visuellement une case dans la grille de jeu.
 * 
 * @author Pierre-Olivier Boulet
 * @author Rapha�l Sylvain
 * 
 * @see Case
 * @see CaseListener
 * 
 * @since 09/12/2013
 */
public class BlockGraphics extends Rectangle implements CaseListener
{

    private static final int BLOCK_HEIGHT = 30;
    private static final int BLOCK_WIDTH = 30;
    private Case matchingCase;

    /**
     * Constructeur de BlockGraphics
     * 
     * @param matchingCase
     *            La case de la grille de jeu associ�e � ce blockGraphic
     */
    public BlockGraphics(Case matchingCase)
    {
	this.setVisible(false);
	this.matchingCase = matchingCase;
	this.matchingCase.setCaseListener(this);

	this.setHeight(BlockGraphics.BLOCK_HEIGHT);
	this.setWidth(BlockGraphics.BLOCK_WIDTH);
    }

    /**
     * L'action r�alis� lorsque le CaseListener lance une notification.
     * Si la case est maintenant rempli, on r�v�le le block.
     * Si elle est maintenant vide, on cache le block.
     */
    public void onCaseStateChanged()
    {
	if (this.matchingCase.getBlock() == null)
	{
	    this.Hide();
	}
	else
	{
	    this.Reveal();
	}
    }

    /**
     * Fonction qui cache le block.
     */
    public void Hide()
    {
	this.setVisible(false);
    }

    /**
     * Fonction qui rend visible le block et assigne une couleur � ce block.
     */
    public void Reveal()
    {
	if (this.matchingCase.getBlock() != null)
	{
	    this.setFill(matchingCase.getBlock().getColorBlock());
	    this.setVisible(true);
	}
    }

    /**
     * Fonction qui ajoute un effet d'�clairage au block.
     */
    public void flash()
    {
	BlockGraphics.this.setEffect(new Glow(5.00));
    }

    /**
     * Fonction qui enl�ve l'effet d'�clairage au block.
     */
    public void unflash()
    {
	BlockGraphics.this.setEffect(new Glow(0));
    }
}
