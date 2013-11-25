package tetris.userInterface;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import tetris.backend.CaseListener;
import tetris.backend.Case;

public class BlockGraphics extends Rectangle implements CaseListener {
	
	private Case matchingCase;
	
	public BlockGraphics(Case matchingCase)
	{		
		this.setVisible(false);
		this.matchingCase = matchingCase;
		this.matchingCase.setBlockListener(this);
		
		this.setHeight(30);
		this.setWidth(30);
		this.setFill(Color.RED);

//		
//		root.getChildren().add(this);
	}
	
	public void onStateChanged() {
		if (this.matchingCase.getBlock() == null)
		{
			this.setVisible(false);
		}
		else
		{
			this.setVisible(true);
		}
	}
}

