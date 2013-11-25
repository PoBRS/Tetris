package tetris.userInterface;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;



public class PrimaryStage extends Application {
	public static final String DEFAULT_TITLE = "Tetris - Raphaël Sylvain et Pierre-Olivier Boulet";

	public static final double DEFAULT_LOCATION_X = 200.0;
	public static final double DEFAULT_LOCATION_Y = 30.0;

	public void start(Stage primaryStage) {
		
			primaryStage.setResizable(false);
			primaryStage.setTitle(DEFAULT_TITLE);
			primaryStage.setX(DEFAULT_LOCATION_X);
			primaryStage.setY(DEFAULT_LOCATION_Y);

			primaryStage.setScene(new MainScene(primaryStage, new Group()));
			
			primaryStage.show();
			primaryStage.sizeToScene();


	}
}