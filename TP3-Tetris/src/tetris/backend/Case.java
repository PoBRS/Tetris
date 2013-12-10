
package tetris.backend;

import java.util.ArrayList;

/**
 * Classe représentant une case de la grille de jeu.
 * 
 * @author Pierre-Olivier Boulet
 * @author Raphaël Sylvain
 * 
 * @since 09/12/2013
 */
public class Case
{
    private ArrayList<CaseListener> caseListeners = new ArrayList<>();
    private Block block;

    /**
     * Constructeur de case. Construit une case vide.
     */
    public Case()
    {
	this.setBlock(null);
    }

    /**
     * Getter de block.
     * 
     * @return -> Le block qui est dans la case. Null si la case est vide
     */
    public Block getBlock()
    {
	return this.block;
    }

    /**
     * Setter de block. Envoie une notification onStateChanged de CaseListener.
     * 
     * @param block
     *            -> La block qui sera dans la case.
     * @see CaseListener
     */
    public void setBlock(Block block)
    {
	this.block = block;
	for (CaseListener listener : this.caseListeners)
	{
	    listener.onCaseStateChanged();
	}
    }

    /**
     * Fonction qui fait sortir la case pour l'affichage. Affiche un X s'il y a un block, un espace s'il y a pas de block.
     */
    public void Print()
    {
	if (this.block == null)
	{
	    System.out.print(" ");
	}

	else
	{
	    System.out.print("X");
	}
    }

    /**
     * Fonction qui ajoute un listener de case.
     * 
     * @param caseListener
     *            -> Un listener a ajouter à la liste des listeners.
     */
    public void setCaseListener(CaseListener caseListener)
    {
	this.caseListeners.add(caseListener);
    }
}
