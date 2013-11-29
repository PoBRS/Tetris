
package tetris.userInterface;

import javafx.scene.effect.Glow;
import javafx.scene.shape.Rectangle;
import tetris.backend.Case;
import tetris.backend.CaseListener;

public class BlockGraphics extends Rectangle implements CaseListener
{

    private Case matchingCase;

    public BlockGraphics(Case matchingCase)
    {
	this.setVisible(false);
	this.matchingCase = matchingCase;
	this.matchingCase.setBlockListener(this);

	this.setHeight(30);
	this.setWidth(30);

	//
	// root.getChildren().add(this);
    }

    public void onStateChanged()
    {
	if (this.matchingCase.getBlock() == null)
	{
	    this.setVisible(false);
	}
	else
	{
	    this.setFill(matchingCase.getBlock().getColorBlock());
	    this.setVisible(true);
	}
    }

    public void flash()
    {

	BlockGraphics.this.setEffect(new Glow(5.00));

    }
}
