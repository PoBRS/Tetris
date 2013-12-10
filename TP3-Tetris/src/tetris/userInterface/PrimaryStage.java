
package tetris.userInterface;

import tetris.userInterface.menu.MenuScene;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;

/**
 * Classe repr�sentant l'application.
 * 
 * @author Pierre-Olivier Boulet
 * @author Rapha�l Sylvain
 * 
 * @since 09/12/2013
 * 
 * 
 */
public class PrimaryStage extends Application
{
    public static final String DEFAULT_TITLE = "Tetris - Rapha�l Sylvain et Pierre-Olivier Boulet";

    /**
     * Fonction qui set les propri�t�s de le fen�tre et qui se cr�e une nouvelle sc�ne.
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