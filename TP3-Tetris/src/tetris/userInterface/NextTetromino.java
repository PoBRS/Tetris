
package tetris.userInterface;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class NextTetromino extends GridPane
{
    Rectangle[][] gridArray = new Rectangle[4][4];

    public NextTetromino()
    {
	super();
	for (int y = 0; y < 4; y++)
	{
	    for (int x = 0; x < 4; x++)
	    {
		Rectangle nextTetrominoBlock = new Rectangle();
		nextTetrominoBlock.setHeight(30);
		nextTetrominoBlock.setWidth(30);
		nextTetrominoBlock.setFill(Color.GREY);
		gridArray[x][y] = nextTetrominoBlock;
		this.add(nextTetrominoBlock, x, y);
	    }
	}

    }

    public void fillItem(int x, int y)
    {
	gridArray[x][y].setFill(Color.BLACK);
	this.getChildren().remove(gridArray[x][y]);
	this.add(gridArray[x][y], x, y);
    }

}
