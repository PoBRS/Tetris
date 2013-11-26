package tetris.userInterface;

import javafx.scene.Group;
import javafx.scene.layout.Pane;

public class Score extends Pane
{
	private int score = 0;
	private int nbLines = 0;
	private String scoreTxt = "";

	public Score(Group root)
	{
		//
		// scoreTxt = "0";
		// Label gameScore = new Label(scoreTxt);
		// this.getChildren().addAll(gameScore);
		// root.getChildren().add(this);
	}

}
