
package tetris.userInterface.board;

import java.io.File;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import tetris.backend.Case;
import tetris.backend.EnumShape;
import tetris.backend.Game;
import tetris.backend.GameEndListener;
import tetris.backend.NewTetrominoListener;
import tetris.backend.Tetromino;
import tetris.userInterface.Chronometer;
import tetris.userInterface.Score;
import tetris.userInterface.hud.HUD;
import tetris.userInterface.menu.HighScoreScene;
import tetris.userInterface.menu.MenuScene;

/*
 * Classe maîtresse de l'interface. Elle représente la fenêtre principale de jeu.
 * Toute l'interface est contrôlée par cette classe.
 * 
 * @author Pierre-Olivier Boulet
 * @author Raphaël Sylvain
 */
public class MainScene extends Scene implements NewTetrominoListener, GameEndListener
{
    private Game currentGame;
    private AudioClip spin;
    private AudioClip beep;
    private AudioClip crash;
    private BlockGraphics[][] blocks = new BlockGraphics[10][20];
    private HUD hud;
    private Grid gameGrid;

    final static String BACKGROUND_PATH = "file:ressources/background/Kremlin_Night.jpg";
    final static String MP3A = "ressources/music/TetrisA.mp3";
    final static String MP3B = "ressources/music/TetrisB.mp3";
    final static String MP3C = "ressources/music/TetrisC.mp3";
    final static String MP3CRASH = "ressources/sound/Crash.mp3";
    final static String MP3SPIN = "ressources/sound/Spin.mp3";
    final static String MP3BEEP = "ressources/sound/Beep.mp3";

    // These warnings are inaccurate. Those classes are accessed by the listeners.
    // Even though they are not accessed directly, they react to changes in the game
    // and cannot be removed.
    @SuppressWarnings("unused")
    private Chronometer chronometer;
    @SuppressWarnings("unused")
    private Score score;

    private Stage primaryStage;
    private MediaPlayer musicPlayer;

    private boolean rotationLimit = false;

    /**
     * Constructeur de la MainScene. Créé tous les contrôles associés à l'interface.
     * 
     * @param primaryStage
     *            -> Fenêtre principale à laquelle on associe les scènes.
     * @param root
     *            -> Groupe de la main scène.
     * @param level
     *            -> Niveau initial de la partie.
     * @param theme
     *            -> Musique choisie par l'utilisateur.
     * @param name
     *            -> Nom de l'utilisateur.
     * 
     * @see HUD
     * @see HighScoreScene
     * @see Score
     * @see Chronometer
     * @see Grid
     */
    public MainScene(Stage primaryStage, Group root, int level, int theme, String name)
    {
	super(root);

	this.primaryStage = primaryStage;

	Image image = new Image("file:ressources/Tetris.jpg");
	Paint imagePattern = new ImagePattern(image, 0, 0, image.getWidth(), image.getHeight(), false);
	this.setFill(imagePattern);

	this.currentGame = new Game(level);
	setHud(new HUD(root, this.currentGame));

	this.gameGrid = new Grid(root);
	this.fillGameGrid();

	this.score = new Score(this, name);
	this.chronometer = new Chronometer(this);
	this.currentGame.setNewTetrominoListener(this);
	this.currentGame.setGameEndListener(this);

	this.setUserControls();
	this.setOnWindowClosed(primaryStage);

	this.setBackgroundImage();
	this.audioConfiguration();

	this.setupMusic(theme);

    }

    /**
     * Affichage de l'état de pause du jeu et arrêt de la musique.
     * 
     * @see BlockGraphics
     */
    public void pauseGame()
    {
	MainScene.this.currentGame.Pause();

	for (BlockGraphics blockDimension1[] : MainScene.this.blocks)
	{
	    for (BlockGraphics block : blockDimension1)
	    {
		block.Hide();
		this.musicPlayer.stop();

	    }
	}

	MainScene.this.gameGrid.PauseDisplay();
    }

    /**
     * Affichage de l'état actif du jeu et reprise de la musique.
     * 
     * @see BlockGraphics
     */
    public void resumeGame()
    {
	MainScene.this.currentGame.Resume();
	for (BlockGraphics blockDimension1[] : MainScene.this.blocks)
	{
	    for (BlockGraphics block : blockDimension1)
	    {
		block.Reveal();
		this.musicPlayer.play();
	    }
	}

	MainScene.this.gameGrid.UnPauseDisplay();
    }

    /**
     * Ajoute une image de fond au jeu.
     */
    private void setBackgroundImage()
    {
	Image image = new Image(MainScene.BACKGROUND_PATH);
	Paint imagePattern = new ImagePattern(image, 0, 0, image.getWidth() + 80, image.getHeight(), false);
	this.setFill(imagePattern);
    }

    /**
     * Détruit la scène lorsque l'on ferme la fenêtre.
     * 
     * @see Game
     */
    private void setOnWindowClosed(Stage primaryStage)
    {
	primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>()
	{
	    @Override
	    public void handle(WindowEvent event)
	    {
		MainScene.this.currentGame.Destroy();
	    }
	});
    }

    /**
     * Ajoute les contrôles de jeu pour l'utilisateur.
     * 
     * @see Tetromino
     * @see Game
     */
    private void setUserControls()
    {
	this.setOnKeyPressed(new EventHandler<KeyEvent>()
	{
	    public void handle(KeyEvent key)
	    {
		if (key.getCode() == KeyCode.LEFT)
		{
		    if (!MainScene.this.currentGame.isPause())
		    {
			if (MainScene.this.currentGame.getCurrentTetrimino().MoveLeft())
			{
			    MainScene.this.beep.play();

			}
		    }
		}

		if (key.getCode() == KeyCode.RIGHT)
		{
		    if (!MainScene.this.currentGame.isPause())
		    {
			if (MainScene.this.currentGame.getCurrentTetrimino().MoveRight())
			{
			    MainScene.this.beep.play();

			}
		    }
		}
		if (key.getCode() == KeyCode.DOWN)
		{
		    if (!MainScene.this.currentGame.isPause())
		    {
			MainScene.this.getCurrentGame().getCurrentTetrimino().MoveDown();
		    }

		}

		if (key.getCode() == KeyCode.UP)
		{
		    if (!MainScene.this.currentGame.isPause() && MainScene.this.rotationLimit == false)
		    {
			if (MainScene.this.currentGame.getCurrentTetrimino().RotateCallManager())
			{
			    MainScene.this.rotationLimit = true;
			    MainScene.this.spin.play();
			}

		    }
		}

		if (key.getCode() == KeyCode.ENTER)
		{
		    if (MainScene.this.currentGame.isPause())
		    {
			MainScene.this.resumeGame();
		    }
		    else
		    {
			MainScene.this.pauseGame();
		    }
		}

		if (key.getCode() == KeyCode.SPACE)
		{
		    if (!MainScene.this.currentGame.isPause())
		    {
			while (MainScene.this.getCurrentGame().getCurrentTetrimino().MoveDown())
			{
			    // Repeat until it's completely down, please.
			}
		    }
		}
	    }
	});

	this.setOnKeyReleased(new EventHandler<KeyEvent>()
	{
	    public void handle(KeyEvent key)
	    {
		if (key.getCode() == KeyCode.UP)
		{
		    MainScene.this.rotationLimit = false;
		}
	    }
	});
    }

    /**
     * Retourne la partie instanciée par la MainScene.
     * 
     * @return
     */
    public Game getCurrentGame()
    {
	return this.currentGame;
    }

    /**
     * Retourne le clip audio "Crash".
     */
    public AudioClip getCrash()
    {
	return crash;
    }

    /**
     * Retourne le HUD de la MainScene.
     * 
     * @return HUD -> Le HUD de la MainScene.
     * 
     * @see HUD
     */
    public HUD getHud()
    {
	return hud;
    }

    /**
     * Associe un HUD à la MainScene.
     * 
     * @param hud
     *            -> Le HUD à associer.
     * 
     * @see HUD
     */
    public void setHud(HUD hud)
    {
	this.hud = hud;
    }

    /**
     * Retourne le tableau de blocks de la MainScene.
     * 
     * @return blocks -> Le tableau de blocks de la MainScene.
     * @see BlockGraphics
     */
    public BlockGraphics[][] getBlocks()
    {
	return blocks;
    }

    /**
     * Application du listener de création d'un nouveau Tetromino.
     * 
     * @param nextTetromino
     *            -> Forme du prochain tetromino de la partie.
     */
    @Override
    public void onNewTetromino(EnumShape nextTetromino)
    {
	this.crash.play();
    }

    /**
     * Lance la musique de fond décidée par l'utilisateur.
     * 
     * @param theme
     *            -> Musique décidée par l'utilisateur à lancer.
     */
    private void setupMusic(int theme)
    {
	String linkTetrisMusic;
	switch (theme)
	{
	    case 1:
		linkTetrisMusic = new File(MainScene.MP3B).toURI().toString();
		break;
	    case 2:
		linkTetrisMusic = new File(MainScene.MP3C).toURI().toString();
		break;
	    default:
		linkTetrisMusic = new File(MainScene.MP3A).toURI().toString();
		break;
	}

	Media musicMedia = new Media(linkTetrisMusic);
	this.musicPlayer = new MediaPlayer(musicMedia);
	this.musicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
	this.musicPlayer.setVolume(0.1);
	Platform.runLater(new Runnable()
	{

	    @Override
	    public void run()
	    {
		MainScene.this.musicPlayer.play();
	    }

	});
	;

    }

    /**
     * Création d'AudioClips représentant les sons du jeu.
     */
    private void audioConfiguration()
    {

	String linkSpin = new File(MainScene.MP3SPIN).toURI().toString();
	this.spin = new AudioClip(linkSpin);
	this.spin.setVolume(0.5);

	String linkBeep = new File(MainScene.MP3BEEP).toURI().toString();
	this.beep = new AudioClip(linkBeep);

	String linkCrash = new File(MainScene.MP3CRASH).toURI().toString();
	this.crash = new AudioClip(linkCrash);
    }

    /**
     * Création du tableau de BlockGraphics. Celui ci est contenu dans une Grid.
     * 
     * @see BlockGraphics
     * @see Grid
     */
    private void fillGameGrid()
    {

	Case[][] matchingCase = this.currentGame.getGameGrid();

	for (int x = 0; x < 10; x++)
	{
	    for (int y = 0; y < 20; y++)
	    {
		blocks[x][y] = new BlockGraphics(matchingCase[x][y + 4]);
		this.gameGrid.add(blocks[x][y], x, y);
	    }
	}
	getHud().setCenter(this.gameGrid);
    }

    /**
     * Gestion de l'affichage lorsqu'une partie est terminée.
     */
    @Override
    public void onGameEnd()
    {
	this.gameGrid.GameEndEvent();
	this.musicPlayer.stop();
	this.goBackToMenuScreen();

    }

    /**
     * Fonction permettant de retourner à l'écran d'accueil.
     * 
     * @see MenuScene
     */
    private void goBackToMenuScreen()
    {

	Platform.runLater(new Runnable()
	{

	    @Override
	    public void run()
	    {
		MenuScene menuScene = new MenuScene(MainScene.this.primaryStage, new Group());
		MainScene.this.primaryStage.setScene(menuScene);

	    }

	});
    }
}