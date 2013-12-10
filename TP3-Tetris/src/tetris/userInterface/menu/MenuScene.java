
package tetris.userInterface.menu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Classe représentant l'affichage autour de la grille de jeu.
 * 
 * @author Pierre-Olivier Boulet
 * @author Raphaël Sylvain
 * 
 * @since 09/12/2013
 * 
 */
public class MenuScene extends Scene
{
    private static final String BACKGROUND_PATH = "file:ressources/background/Kremlin.jpg";
    private final static int MENU_WIDTH = 400;
    private final static int MENU_HEIGHT = 600;
    private Stage primaryStage;

    /**
     * Constructeur de MenuScene.
     * Crée les composantes et les ajoutes dans le conteneur.
     * 
     * @param primaryStage
     *            -> La fenêtre où ajouter les nouvelles Scene.
     * @param root
     *            -> Le conteneur des composants de MenuScene.
     * 
     * @todo -> Séparer le constructeur en plusieurs sous-classes.
     */
    public MenuScene(Stage primaryStage, Group root)
    {

	super(root);
	this.primaryStage = primaryStage;

	Image backgroundImage = new Image(BACKGROUND_PATH);
	Paint backgroundPaint = new ImagePattern(backgroundImage, 0, 0, MENU_WIDTH, MENU_HEIGHT, false);
	Rectangle background = new Rectangle(MENU_WIDTH, MENU_HEIGHT, backgroundPaint);

	VBox menuBox = new VBox(75);

	Label lblGameTitle = new Label("Tetris");

	lblGameTitle.setFont(new Font("Goudy Stout", 30));
	Button btnGameMode = new Button("Mode de jeu principal");
	btnGameMode.setOnAction(new EventHandler<ActionEvent>()
	{

	    @Override
	    public void handle(ActionEvent arg0)
	    {
		SettingScene gameScene = new SettingScene(MenuScene.this.primaryStage, new Group(), MenuScene.MENU_WIDTH, MenuScene.MENU_HEIGHT);
		MenuScene.this.primaryStage.setScene(gameScene);
	    }
	});

	Button btnHighScores = new Button("Meilleurs scores");
	btnHighScores.setOnAction(new EventHandler<ActionEvent>()
	{
	    @Override
	    public void handle(ActionEvent arg0)
	    {
		HighScoreScene highScoreScene = new HighScoreScene(MenuScene.this.primaryStage, new Group());
		MenuScene.this.primaryStage.setScene(highScoreScene);
	    }
	});

	Button btnQuit = new Button("Quitter");
	btnQuit.setOnAction(new EventHandler<ActionEvent>()
	{

	    @Override
	    public void handle(ActionEvent arg0)
	    {
		MenuScene.this.primaryStage.close();

	    }
	});
	menuBox.setAlignment(Pos.CENTER);

	menuBox.getChildren().addAll(lblGameTitle, btnGameMode, btnHighScores, btnQuit);

	menuBox.setLayoutX((MENU_WIDTH / 2) - 100);
	menuBox.setLayoutY((MENU_HEIGHT / 2) - 150);

	root.getChildren().addAll(background, menuBox);

    }
}
