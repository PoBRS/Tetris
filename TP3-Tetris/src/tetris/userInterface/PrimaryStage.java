
package tetris.userInterface;

import tetris.userInterface.menu.MenuScene;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;

/**
 * Classe représentant l'application.
 * 
 * @author Pierre-Olivier Boulet
 * @author Raphaël Sylvain
 * 
 * @since 09/12/2013
 * 
 * 
 */
public class PrimaryStage extends Application
{
    public static final String DEFAULT_TITLE = "Tetris - Raphaël Sylvain et Pierre-Olivier Boulet";

    /**
     * Fonction qui set les propriétés de le fenêtre et qui se crée une nouvelle scène.
     */
    public void start(Stage primaryStage)
    {
	primaryStage.setResizable(false);
	primaryStage.centerOnScreen();

	primaryStage.setTitle(DEFAULT_TITLE);
	primaryStage.setScene(new MenuScene(primaryStage, new Group()));

	primaryStage.show();
	primaryStage.sizeToScene();

    }

}