
package tetris.userInterface.menu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Classe représentant la scene où sont afficher les meilleurs résultats.
 * 
 * @author Pierre-Olivier Boulet
 * @author Raphaël Sylvain
 * 
 * @since 09/12/2013
 * 
 */
public class HighScoreScene extends Scene
{
    private static final String BACKGROUND_PATH = "file:ressources/background/USSR.jpg";
    private static final String RESSOURCE_SCORE_FILE = "ressources/highscores.txt";
    private final static int MENU_WIDTH = 400;
    private final static int MENU_HEIGHT = 600;
    private Stage primaryStage;

    /**
     * Constructeur de la scène et ajout des compostant.
     * 
     * @param root
     *            -> Conteneur des composants de la scene.
     * @todo Séparer ce constructeur en sous-fonction qui crée chaqu'un leur composant.
     */
    public HighScoreScene(Stage primaryStage, Group root)
    {

	super(root);
	this.primaryStage = primaryStage;

	Image backgroundImage = new Image(BACKGROUND_PATH);
	Paint backgroundPaint = new ImagePattern(backgroundImage, 0, 0, MENU_WIDTH, MENU_HEIGHT, false);
	Rectangle background = new Rectangle(MENU_WIDTH, MENU_HEIGHT, backgroundPaint);

	VBox menuBox = new VBox(30);

	Label lblGameTitle = new Label("Tetris");
	lblGameTitle.setFont(new Font("Goudy Stout", 30));

	String tenScores[][] = this.getTenHighestScore();
	GridPane scoreGrid = new GridPane();
	scoreGrid.setVgap(20);
	scoreGrid.setHgap(50);
	for (int i = 0; i < 10; i++)
	{
	    String name = tenScores[i][0];
	    Label lblPosition = new Label(Integer.toString(i + 1) + " - ");
	    Label lblName = new Label(name);
	    Label lblScore = new Label(tenScores[i][1]);
	    DropShadow shadowEffect = new DropShadow();
	    shadowEffect.setColor(Color.BLACK);
	    shadowEffect.setOffsetY(-1);

	    lblPosition.setFont(new Font("Arial", 20));
	    lblName.setFont(new Font("Arial", 20));
	    lblScore.setFont(new Font("Arial", 20));

	    lblPosition.setEffect(shadowEffect);
	    lblName.setEffect(shadowEffect);
	    lblScore.setEffect(shadowEffect);

	    lblPosition.setTextFill(Color.WHITE);
	    lblName.setTextFill(Color.WHITE);
	    lblScore.setTextFill(Color.WHITE);

	    scoreGrid.add(lblPosition, 0, i);
	    scoreGrid.add(lblName, 1, i);
	    scoreGrid.add(lblScore, 2, i);

	}

	Button btnBack = new Button("Retour");
	btnBack.setOnAction(new EventHandler<ActionEvent>()
	{

	    @Override
	    public void handle(ActionEvent arg0)
	    {
		MenuScene menuScene = new MenuScene(HighScoreScene.this.primaryStage, new Group());
		HighScoreScene.this.primaryStage.setScene(menuScene);

	    }
	});

	menuBox.getChildren().addAll(lblGameTitle, scoreGrid, btnBack);
	menuBox.setAlignment(Pos.CENTER);
	menuBox.setLayoutX((MENU_WIDTH / 2) - 125);
	menuBox.setLayoutY(25);

	root.getChildren().addAll(background, menuBox);

    }

    /**
     * Fonction trouves les dix meilleurs scores dans le fichier contenant tout les scores.
     * 
     * @return -> Un tableau de Strings contenant le nom en position [][0] et le score en position [][1].
     * 
     * @todo -> décomposer cette fonction
     */
    private String[][] getTenHighestScore()
    {

	File file = new File(HighScoreScene.RESSOURCE_SCORE_FILE);

	String line;
	int nbScores = 0;
	String[][] top10 = new String[10][2];
	List<String[]> allScores = new ArrayList<String[]>();

	try
	{
	    BufferedReader br = new BufferedReader(new FileReader(file));
	    while ((line = br.readLine()) != null)
	    {
		nbScores++;

		String[] stats = line.split(" ");

		allScores.add(stats);
	    }
	    br.close();
	}
	catch (IOException e)
	{
	    e.printStackTrace();
	}

	for (int top10Pos = 0; top10Pos < 10; top10Pos++)
	{

	    int highestScore = 0;

	    if (allScores.size() > 0)
	    {
		for (int scorePos = 0; scorePos < nbScores; scorePos++)
		{
		    if (Integer.parseInt(allScores.get(scorePos)[1]) > Integer.parseInt(allScores.get(highestScore)[1]))
		    {
			highestScore = scorePos;
		    }
		}
		top10[top10Pos] = allScores.get(highestScore);
		allScores.remove(highestScore);
		nbScores--;
	    }
	    else
	    {
		top10[top10Pos][0] = "N/A";
		top10[top10Pos][1] = "N/A";
	    }
	}

	return top10;

    }
}
