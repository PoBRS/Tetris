package tetris.userInterface;

import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Grid extends GridPane{

	public Grid(Group root)
	{
	    for (int x = 0; x < 10; x++)
	    {
	    	for (int y = 0; y < 20; y++)
	    	{
	    	    Rectangle emptySpace = new Rectangle();
	    	    emptySpace.setHeight(28);
	    	    emptySpace.setWidth(28);
	    	    emptySpace.setOpacity(0.2);
	    	    emptySpace.setFill(Color.DARKGREEN);
	    	    emptySpace.setStroke(Color.BLACK);
	    	    emptySpace.setStrokeWidth(2.5);
	    		this.add(emptySpace, x, y);
	    	}
	    }
	    root.getChildren().add(this);
	}
	
}
