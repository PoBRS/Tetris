
package tetris.userInterface;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import tetris.backend.EnumShape;
import tetris.backend.Game;
import tetris.backend.LineListener;
import tetris.backend.NewTetrominoListener;

public class HUD extends BorderPane implements NewTetrominoListener, LineListener
{

    private Game game;
    private Label lblEventOfTheGame;
    private BorderPane rightPanel;
    private Label lblScore;
    private NextTetromino nextPiece;

    public HUD(Group parent, Game game)
    {
	super();
	this.game = game;
	this.lblEventOfTheGame = new Label();
	this.lblEventOfTheGame.setAlignment(Pos.CENTER);
	this.rightPanel = new BorderPane();
	this.lblScore = new Label("0");
	this.game.setNewTetrominoListener(this);
	this.game.setLineListener(this);
	this.nextPiece = new NextTetromino();
	this.resetNextTetromino(this.game.getNextTetromino());
	this.rightPanel.setTop(this.nextPiece);

	Label placeHolder2 = new Label("Meilleurs scores et core de la partie actuelle");
	Label placeHolder3 = new Label("Prochaine partie");
	Label placeHolder4 = new Label("Niveau de jeu actuel");
	Label placeHolder5 = new Label("Nombre de lignes complétées");

	this.setLeft(lblScore);
	this.setRight(this.rightPanel);

	this.changeEvent(0);

	parent.getChildren().add(this);
    }

    public void setScore(int numberLineCleared)
    {
	int scoreToAdd;
	/**
	 * Once we'll have levels, just multiply scoeToAdd with the levels.
	 */
	switch (numberLineCleared)
	{
	    case 1:

		scoreToAdd = 40;
		break;
	    case 2:
		scoreToAdd = 100;
		break;
	    case 3:
		scoreToAdd = 300;
		break;
	    case 4:
		scoreToAdd = 1200;
		break;
	    default:
		scoreToAdd = 0;
		break;
	}
	int currentScore = Integer.parseInt(this.lblScore.getText());
	int newScore = currentScore + scoreToAdd;
	this.lblScore.setText(Integer.toString(newScore));
	return;
    }

    public void resetNextTetromino(EnumShape nextTetromino)
    {
	nextPiece.resetItem();

	switch (nextTetromino)
	{
	    case I:
		nextPiece.fillItem(2, 0);
		nextPiece.fillItem(2, 1);
		nextPiece.fillItem(2, 2);
		nextPiece.fillItem(2, 3);
		break;
	    case J:
		nextPiece.fillItem(2, 0);
		nextPiece.fillItem(2, 1);
		nextPiece.fillItem(2, 2);
		nextPiece.fillItem(1, 2);
		break;
	    case L:
		nextPiece.fillItem(1, 0);
		nextPiece.fillItem(1, 1);
		nextPiece.fillItem(1, 2);
		nextPiece.fillItem(2, 2);
		break;

	    case O:

		nextPiece.fillItem(1, 1);
		nextPiece.fillItem(2, 1);
		nextPiece.fillItem(1, 2);
		nextPiece.fillItem(2, 2);
		break;

	    case S:
		nextPiece.fillItem(3, 1);
		nextPiece.fillItem(2, 1);
		nextPiece.fillItem(2, 2);
		nextPiece.fillItem(1, 2);
		break;
	    case T:
		nextPiece.fillItem(1, 2);
		nextPiece.fillItem(2, 2);
		nextPiece.fillItem(2, 1);
		nextPiece.fillItem(3, 2);
		break;

	    case Z:
		nextPiece.fillItem(1, 1);
		nextPiece.fillItem(2, 1);
		nextPiece.fillItem(2, 2);
		nextPiece.fillItem(3, 2);
		break;

	}
	this.rightPanel.setTop(nextPiece);
    }

    public void changeEvent(int numberLineCleared)
    {
	String message;
	switch (numberLineCleared)
	{
	    case 1:

		message = "Line.";
		break;
	    case 2:
		message = "Combo!";
		break;
	    case 3:
		message = "Three in a row!";
		break;
	    case 4:
		message = "TETRIS!!";
		break;
	    default:
		message = "";
		break;
	}

	this.lblEventOfTheGame.setText(message);
	this.setTop(this.lblEventOfTheGame);
    }

    @Override
    public void onNewTetromino(EnumShape nextTetromino)
    {
	this.resetNextTetromino(nextTetromino);

    }

    @Override
    public void onLineCompleted(int line)
    {
	this.setScore(1); // Temp, for testing.

    }
}