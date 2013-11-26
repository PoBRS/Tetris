package tetris.userInterface;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class HUD extends BorderPane
{
	public HUD(Group parent)
	{
		super();
		Label placeHolder1 = new Label("Évènements de la partie actuelle");
		Label placeHolder2 = new Label("Meilleurs scores et core de la partie actuelle");
		Label placeHolder3 = new Label("Prochaine partie");
		Label placeHolder4 = new Label("Niveau de jeu actuel");
		Label placeHolder5 = new Label("Nombre de lignes complétées");
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(10);
		vbox.getChildren().addAll(placeHolder3, placeHolder4, placeHolder5);

		this.setLeft(placeHolder2);
		this.setTop(placeHolder1);

		parent.getChildren().add(this);
	}
}