
package tetris.userInterface.hud;

import java.io.FileNotFoundException;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import tetris.backend.EnumShape;
import tetris.backend.Game;
import tetris.backend.LevelListener;
import tetris.backend.LineListener;
import tetris.backend.NewTetrominoListener;
import tetris.backend.ScoreListener;
import tetris.backend.Tetromino;

/**
 * Classe représentant l'affichage autour de la grille de jeu.
 * 
 * @author Pierre-Olivier Boulet
 * @author Raphaël Sylvain
 * 
 * @since 09/12/2013
 * 
 */
public class HUD extends BorderPane implements NewTetrominoListener, LineListener, LevelListener, ScoreListener
{

    private Game game;

    private GridPane leftPanel;
    private GridPane stats;
    private StackPane topHudEvent;
    private StackPane leftHudHighScores;
    private BorderPane rightHUDStats;

    private Label lblEventOfTheGame;
    private Label lblScore;
    private Label lblLevel;
    private Label lblLines;
    private NextTetromino nextPiece;
    private int numberLineCleared;
    private int numberLineCompletedTotal;
    private int level;
    private int score;

    private Label labelScoreTxt;
    private Label labelLevelTxt;
    private Label labelLinesTxt;
    private Label labelHighScore;
    private Label labelScore;
    private Label labelTop1Name;
    private Label labelTop1Score;
    private Label labelTop2Name;
    private Label labelTop2Score;
    private Label labelTop3Name;
    private Label labelTop3Score;
    private ScoreBoard scoreBoard;

    /**
     * Constructeur du HUD
     * 
     * @param parent
     *            -> Le conteneur où ajouter le HUD.
     * @param game
     *            -> La partie en cours.
     */
    public HUD(Group parent, Game game)
    {
	super();
	this.game = game;

	this.setUpScoreBoard();
	this.setUpTopHud();
	this.setRightHud();
	this.setLeftHud();
	this.setUpListeners();

	this.setTop(this.topHudEvent);
	this.setRight(this.rightHUDStats);
	this.setLeft(this.leftHudHighScores);

	this.changeEvent(0);
	this.updateLevel();

	parent.getChildren().add(this);
    }

    /**
     * Fonction qui set les listeners.
     */
    private void setUpListeners()
    {
	this.game.setNewTetrominoListener(this);
	this.game.setLevelListener(this);
	this.game.setLineListener(this);
	this.game.setScoreListener(this);
    }

    /**
     * Fonction qui crée le haut du HUD
     */
    private void setUpTopHud()
    {
	this.lblEventOfTheGame = new Label();
	this.lblEventOfTheGame.setAlignment(Pos.CENTER);
	this.lblEventOfTheGame.setTextFill(Color.RED);
	this.lblEventOfTheGame.setFont(Font.font("Arial", 40));

	this.topHudEvent = new StackPane();
	Rectangle emptySpace = new Rectangle();
	emptySpace.setHeight(40);
	emptySpace.setWidth(180);
	emptySpace.setFill(Color.BLACK);
	emptySpace.setStroke(Color.DARKGREEN);
	emptySpace.setStrokeWidth(3);
	emptySpace.setOpacity(0.8);

	this.topHudEvent.getChildren().addAll(emptySpace, this.lblEventOfTheGame);
    }

    /**
     * Fonction qui crée le scoreBoard avec les meilleurs résultats.
     */
    private void setUpScoreBoard()
    {
	try
	{
	    this.scoreBoard = new ScoreBoard();
	}
	catch (FileNotFoundException e)
	{
	    e.printStackTrace();
	}
    }

    /**
     * Fonction qui va refaire l'affichage du prochain Tetromino
     * 
     * @param nextTetromino
     *            La forme du prochain Tetromino.
     */
    private void resetNextTetromino(EnumShape nextTetromino)
    {
	this.nextPiece.resetItem();

	switch (nextTetromino)
	{
	    case I:
		this.nextPiece.fillItem(2, 0, Tetromino.COLOR_I);
		this.nextPiece.fillItem(2, 1, Tetromino.COLOR_I);
		this.nextPiece.fillItem(2, 2, Tetromino.COLOR_I);
		this.nextPiece.fillItem(2, 3, Tetromino.COLOR_I);
		break;
	    case J:
		this.nextPiece.fillItem(2, 0, Tetromino.COLOR_J);
		this.nextPiece.fillItem(2, 1, Tetromino.COLOR_J);
		this.nextPiece.fillItem(2, 2, Tetromino.COLOR_J);
		this.nextPiece.fillItem(1, 2, Tetromino.COLOR_J);
		break;
	    case L:
		this.nextPiece.fillItem(1, 0, Tetromino.COLOR_L);
		this.nextPiece.fillItem(1, 1, Tetromino.COLOR_L);
		this.nextPiece.fillItem(1, 2, Tetromino.COLOR_L);
		this.nextPiece.fillItem(2, 2, Tetromino.COLOR_L);
		break;

	    case O:

		this.nextPiece.fillItem(1, 1, Tetromino.COLOR_O);
		this.nextPiece.fillItem(2, 1, Tetromino.COLOR_O);
		this.nextPiece.fillItem(1, 2, Tetromino.COLOR_O);
		this.nextPiece.fillItem(2, 2, Tetromino.COLOR_O);
		break;

	    case S:
		this.nextPiece.fillItem(3, 1, Tetromino.COLOR_S);
		this.nextPiece.fillItem(2, 1, Tetromino.COLOR_S);
		this.nextPiece.fillItem(2, 2, Tetromino.COLOR_S);
		this.nextPiece.fillItem(1, 2, Tetromino.COLOR_S);
		break;
	    case T:
		this.nextPiece.fillItem(1, 2, Tetromino.COLOR_T);
		this.nextPiece.fillItem(2, 2, Tetromino.COLOR_T);
		this.nextPiece.fillItem(2, 1, Tetromino.COLOR_T);
		this.nextPiece.fillItem(3, 2, Tetromino.COLOR_T);
		break;

	    case Z:
		this.nextPiece.fillItem(1, 1, Tetromino.COLOR_Z);
		this.nextPiece.fillItem(2, 1, Tetromino.COLOR_Z);
		this.nextPiece.fillItem(2, 2, Tetromino.COLOR_Z);
		this.nextPiece.fillItem(3, 2, Tetromino.COLOR_Z);
		break;

	}
	this.rightHUDStats.setTop(this.nextPiece);
    }

    /**
     * Fonction qui change le message en haut dépendament du nombre de lignes fait en un coup.
     * 
     * @param numberLineCleared
     *            -> Le nombre de ligne fait en un coup.
     */
    private void changeEvent(int numberLineCleared)
    {
	String message;
	switch (numberLineCleared)
	{
	    case 1:

		message = "Single";
		break;
	    case 2:
		message = "Double";
		break;
	    case 3:
		message = "Triple";
		break;
	    case 4:
		message = "TETRIS!";
		break;
	    default:
		message = "";
		break;
	}

	this.lblEventOfTheGame.setText(message);
    }

    /**
     * Fonction qui crée la partie droite du HUD.
     */
    private void setRightHud()
    {
	this.rightHUDStats = new BorderPane();
	this.nextPiece = new NextTetromino();

	this.resetNextTetromino(this.game.getNextTetromino());

	this.setRightHudLabelStyle();

	this.stats = new GridPane();
	this.stats.add(this.labelScoreTxt, 0, 0);
	this.stats.add(this.lblScore, 0, 1);
	this.stats.add(this.labelLevelTxt, 0, 2);
	this.stats.add(this.lblLevel, 1, 2);
	this.stats.add(this.labelLinesTxt, 0, 3);
	this.stats.add(this.lblLines, 1, 3);
	this.stats.setAlignment(Pos.CENTER);
	this.stats.setVgap(10);

	StackPane stack = new StackPane();

	stack.getChildren().addAll(this.setRightHUDVisual(), this.stats);
	this.rightHUDStats.setTop(this.nextPiece);
	this.rightHUDStats.setCenter(stack);
    }

    /**
     * Fonction qui crée la partie gauche de HUD.
     */
    private void setLeftHud()
    {
	this.leftPanel = new GridPane();
	this.leftHudHighScores = new StackPane();

	this.leftPanel.setVgap(50);
	this.leftPanel.setHgap(5);
	this.leftPanel.setAlignment(Pos.CENTER);

	this.setLeftHudLabelStyle();

	this.leftPanel.add(this.labelHighScore, 0, 0);
	this.leftPanel.add(this.labelScore, 1, 0);
	this.leftPanel.add(this.labelTop1Name, 0, 1);
	this.leftPanel.add(this.labelTop1Score, 1, 1);
	this.leftPanel.add(this.labelTop2Name, 0, 2);
	this.leftPanel.add(this.labelTop2Score, 1, 2);
	this.leftPanel.add(this.labelTop3Name, 0, 3);
	this.leftPanel.add(this.labelTop3Score, 1, 3);

	this.leftHudHighScores.getChildren().addAll(this.setLeftHUDVisual(), this.leftPanel);
    }

    /**
     * Fonction qui crée le fond pour les éléments à gauche du HUD.
     * 
     * @return -> Un rectangle pour le fond des éléments de gauche du HUD
     */
    private Rectangle setLeftHUDVisual()
    {
	Rectangle leftHUDVisual = new Rectangle();
	leftHUDVisual.setHeight(370);
	leftHUDVisual.setWidth(180);
	leftHUDVisual.setFill(Color.BLACK);
	leftHUDVisual.setOpacity(0.8);
	return leftHUDVisual;
    }

    /**
     * Fonction qui crée le fond pour les éléments à droite du HUD.
     * 
     * @return -> Un rectangle pour le fond des élément de doite du HUD
     */
    private Rectangle setRightHUDVisual()
    {
	Rectangle rightHudVisual = new Rectangle();
	rightHudVisual.setHeight(180);
	rightHudVisual.setWidth(100);
	rightHudVisual.setFill(Color.BLACK);
	rightHudVisual.setOpacity(0.8);
	rightHudVisual.setStrokeWidth(3);
	return rightHudVisual;
    }

    /**
     * Fonction qui set le style des labels à gauche du HUD.
     */
    private void setLeftHudLabelStyle()
    {
	this.labelHighScore = new Label("MEILLEURS");
	this.labelScore = new Label("SCORES");
	this.labelTop1Name = new Label("1. " + this.scoreBoard.getTop3()[0][0]);
	this.labelTop1Score = new Label(this.scoreBoard.getTop3()[0][1]);
	this.labelTop2Name = new Label("2. " + this.scoreBoard.getTop3()[1][0]);
	this.labelTop2Score = new Label(this.scoreBoard.getTop3()[1][1]);
	this.labelTop3Name = new Label("3. " + this.scoreBoard.getTop3()[2][0]);
	this.labelTop3Score = new Label(this.scoreBoard.getTop3()[2][1]);

	this.labelHighScore.setFont(Font.font("Arial", 16));
	this.labelScore.setFont(Font.font("Arial", 16));
	this.labelTop1Name.setFont(Font.font("Arial", 16));
	this.labelTop1Score.setFont(Font.font("Arial", 16));
	this.labelTop2Name.setFont(Font.font("Arial", 16));
	this.labelTop2Score.setFont(Font.font("Arial", 16));
	this.labelTop3Name.setFont(Font.font("Arial", 16));
	this.labelTop3Score.setFont(Font.font("Arial", 16));

	this.labelHighScore.setTextFill(Color.WHITE);
	this.labelScore.setTextFill(Color.WHITE);
	this.labelTop1Name.setTextFill(Color.WHITE);
	this.labelTop1Score.setTextFill(Color.WHITE);
	this.labelTop2Name.setTextFill(Color.WHITE);
	this.labelTop2Score.setTextFill(Color.WHITE);
	this.labelTop3Name.setTextFill(Color.WHITE);
	this.labelTop3Score.setTextFill(Color.WHITE);
    }

    /**
     * Fonction qui set le style des lablels à droite du HUD.
     */
    private void setRightHudLabelStyle()
    {
	this.labelScoreTxt = new Label("Score: ");
	this.labelLevelTxt = new Label("Niveau: ");
	this.labelLinesTxt = new Label("Lignes: ");
	this.lblScore = new Label("0");
	this.lblLevel = new Label("0");
	this.lblLines = new Label("0");

	this.labelLevelTxt.setFont(Font.font("Arial", 18));
	this.labelLinesTxt.setFont(Font.font("Arial", 18));
	this.labelScoreTxt.setFont(Font.font("Arial", 22));
	this.lblLines.setFont(Font.font("Arial", 18));
	this.lblScore.setFont(Font.font("Arial", 26));
	this.lblLevel.setFont(Font.font("Arial", 18));

	this.lblLines.setTextFill(Color.WHITE);
	this.lblScore.setTextFill(Color.WHITE);
	this.lblLevel.setTextFill(Color.WHITE);
	this.labelLevelTxt.setTextFill(Color.WHITE);
	this.labelLinesTxt.setTextFill(Color.WHITE);
	this.labelScoreTxt.setTextFill(Color.WHITE);
    }

    /**
     * L'action réalisé lorsque le NewTetrominoListener lance une notification.
     * On change le prochain Tetromino
     * On change le message en haut du HUD
     */
    @Override
    public void onNewTetromino(EnumShape nextTetromino)
    {
	this.resetNextTetromino(nextTetromino);
	Platform.runLater(new Runnable()
	{

	    @Override
	    public void run()
	    {
		HUD.this.changeEvent(HUD.this.numberLineCleared);

		HUD.this.numberLineCleared = 0;
	    }

	});
    }

    /**
     * 
     * L'action réalisé lorsque le LineListener lance une notification.
     * On change le nombre total de ligne complété au total.
     */
    @Override
    public void onLineCompleted(int line)
    {

	this.numberLineCleared++;
	this.numberLineCompletedTotal++;
	Platform.runLater(new Runnable()
	{

	    @Override
	    public void run()
	    {
		HUD.this.updateLines();

	    }

	});
    }

    /**
     * Fonction qui change l'affichage pour avoir le bon niveau.
     */
    private void updateLevel()
    {
	this.level = this.game.getLevel() + 1;
	this.lblLevel.setText(Integer.toString(this.level));
    }

    /**
     * L'action réaliser lorsque le ScoreListener lance une notification
     * On change le score dans l'affichage.
     */
    public void onScoreUpdated(final int score)
    {
	Platform.runLater(new Runnable()
	{

	    @Override
	    public void run()
	    {
		HUD.this.updateScore(score);

	    }

	});
    }

    /**
     * Fonction qui change l'affichage pour avoir le bon score mis à jour.
     * 
     * @param score
     *            -> Le nouveau score.
     */
    private void updateScore(int score)
    {
	this.score = score;
	this.lblScore.setText(Integer.toString(this.score));
    }

    /**
     * Fonction qui change l'affichage pour avoir le bon nombre de lignes complétés.
     */
    private void updateLines()
    {
	this.lblLines.setText(Integer.toString(this.numberLineCompletedTotal));
    }

    /**
     * L'action réaliser lorsque le LevelListener lance une notification.
     * On change le niveau dans l'affichage.
     */
    @Override
    public void onNewLevel()
    {
	Platform.runLater(new Runnable()
	{

	    @Override
	    public void run()
	    {
		HUD.this.updateLevel();

	    }

	});

    }
}