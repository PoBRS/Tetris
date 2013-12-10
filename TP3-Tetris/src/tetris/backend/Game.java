
package tetris.backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Classe maitresse du jeu.
 * Elle permet de manipuler les pièces du jeu dans le tableau de jeu.
 * Elle gère la physique du jeu.
 * 
 * @author Raphaël Sylvain
 * @author Pierre-Olivier Boulet
 * 
 * @since 09/12/2013
 * 
 */
public class Game
{
    private Case gameGrid[][];

    private final static int NBCASES_X = 10;
    private final static int NBCASES_Y = 24;

    private Tetromino currentTetromino;
    private EnumShape nextTetromino;
    private TimerTask GameEvents;
    private Timer timer;

    private boolean allowedToClearLine = false;
    private boolean isPause = false;

    private ArrayList<LineListener> lineListener = new ArrayList<>();
    private ArrayList<LevelListener> levelListener = new ArrayList<>();
    private ArrayList<NewTetrominoListener> newTetrominoListener = new ArrayList<>();
    private ArrayList<ScoreListener> scoreListener = new ArrayList<>();
    private ArrayList<GameEndListener> gameEndListener = new ArrayList<>();

    private int level = 0;
    private int score = 0;

    private int linesForNextLevel = 5;
    private int nbLinesCleared = 0;

    private static final List<EnumShape> VALUES = Collections.unmodifiableList(Arrays.asList(EnumShape.values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    /**
     * Constucteur de game selon le niveau.
     * 
     * @param level
     *            -> Le niveau de jeu.
     * @see Game
     */
    public Game(int level)
    {
	this();
	this.level = level - 1;

	this.adjustSpeed();

    }

    /**
     * Constructeur de game. Crée la grille de jeu et met un tetromino en jeu.
     * Lance la physique du jeu (timer).
     */
    public Game()
    {

	this.gameGrid = new Case[Game.NBCASES_X][Game.NBCASES_Y];
	this.nextTetromino = Game.VALUES.get(Game.RANDOM.nextInt(Game.SIZE));

	this.createCasesForBoard(Game.NBCASES_X, Game.NBCASES_Y);
	this.startTimer();

	this.SpawnTetrimino();
    }

    /**
     * Fonction qui crée les case du tableau de jeu
     * 
     * @param numberCaseX
     *            -> Le nombre de cases horizontales.
     * @param numberCaseY
     *            -> Le nombre de cases verticales.
     */
    private void createCasesForBoard(int numberCaseX, int numberCaseY)
    {
	for (int i = 0; i < numberCaseX; i++)
	{
	    for (int j = 0; j < numberCaseY; j++)
	    {
		this.gameGrid[i][j] = new Case();
	    }

	}
    }

    /**
     * Détermine l'effet d'un block lorsque celui-ci atterit.
     * Termine la partie si les conditions sont remplies.
     * Gère la supression d'une ligne si les conditions sont remplies.
     * Remet un tetrimino en jeu si la partie continue.
     */
    private void UpdateWhenBlockHitsGround()
    {
	if (!this.getCurrentTetrimino().MoveDown())
	{
	    if (this.isGameLost())
	    {
		this.Destroy();
	    }
	    else
	    {
		int lineToCheck = this.getCurrentTetrimino().findTetrominoLowestPosition();

		int nbClearedLinesInARow = 0;

		this.Pause();
		for (int i = lineToCheck - 3; i <= lineToCheck; i++)
		{
		    this.allowedToClearLine = false;

		    if (this.CheckLineIsComplete(i))
		    {
			while (this.allowedToClearLine == false)
			{
			    // Wait
			}
			this.ClearLine(i);
			nbClearedLinesInARow++;
		    }
		}
		this.addScore(nbClearedLinesInARow);
		this.SpawnTetrimino();
		this.startTimer();
		this.adjustSpeed();
	    }

	}
    }

    /**
     * Fonction qui ajoutes au score actuel un nombre derterminé par le niveau et le nombre de lignes fait en un coup.
     * 
     * @param nbLinesClearedInARow
     *            -> Le nombre de ligne qui a été fait en un coup.
     */
    private void addScore(int nbLinesClearedInARow)
    {
	if (nbLinesClearedInARow > 0)
	{
	    this.score = this.score + (nbLinesClearedInARow * 50) * (this.level + 1);
	    for (ScoreListener listener : this.scoreListener)
	    {
		listener.onScoreUpdated(this.score);
	    }
	}
    }

    /**
     * Fonction qui va créer un tetromino et l'ajoué dans le jeu.
     * Il va figé le tetromino actuel si celui-ci existe toujours.
     * Il envoit par la suite une notification au listeners de newTetromino.
     * 
     * @see Tetromino
     * @see NewTetrominoListener.
     */
    private void SpawnTetrimino()
    {

	if (this.currentTetromino != null)
	{
	    this.currentTetromino.Deactivate();
	}

	this.currentTetromino = new Tetromino(this);
	this.nextTetromino = Game.VALUES.get(Game.RANDOM.nextInt(Game.SIZE));

	if (this.newTetrominoListener != null)
	{
	    for (NewTetrominoListener listener : this.newTetrominoListener)
	    {
		listener.onNewTetromino(this.nextTetromino);
	    }
	}

    }

    /**
     * Fonction qui calcule le nombre de block qu'il y a sur une ligne et envoie une notification si la ligne est pleine.
     * 
     * @param line
     *            -> La ligne à vérifier si elle est pleine.
     * @return -> True si la ligne est pleine, False sinon.
     */
    private boolean CheckLineIsComplete(int line)
    {
	int nbFilledCases = 0;

	for (int x = 0; x < Game.NBCASES_X; x++)
	{
	    if (this.gameGrid[x][line].getBlock() != null)
	    {
		nbFilledCases++;
	    }
	}

	if (nbFilledCases == Game.NBCASES_X)
	{

	    if (this.lineListener != null)
	    {
		for (LineListener listener : this.lineListener)
		{
		    listener.onLineCompleted(line);
		}
	    }

	    return true;
	}

	return false;

    }

    /**
     * Fonction qui va supprimer une ligne de block dans la grille de jeu, puis va faire descendre les blocks au-dessus de cette ligne.
     * 
     * @param line
     *            -> Ligne à supprimer.
     */
    private void ClearLine(int line)
    {

	for (int column = 0; column < 10; column++)
	{
	    this.gameGrid[column][line].setBlock(null);
	}

	for (int y = line; y > 0; y--)
	{
	    for (int x = 0; x < Game.NBCASES_X; x++)
	    {
		this.gameGrid[x][y].setBlock(this.gameGrid[x][y - 1].getBlock());
	    }
	}
	this.nbLinesCleared++;
	this.VerifyLevelChange();

    }

    /**
     * Fonction qui ajoute un listener de ligne
     * 
     * @param lineListener
     *            -> Nouveau listener de ligne.
     */
    public void setLineListener(LineListener lineListener)
    {
	this.lineListener.add(lineListener);
    }

    /**
     * Fonction qui vérifie s'il faut augmenter de niveau.
     * S'il faut augmenter de niveau, il augemente de niveau et met de nouvelles contraintes pour monter de niveau.
     * Il envoit une notification au listener de level.
     */
    private void VerifyLevelChange()
    {
	if (this.nbLinesCleared == this.linesForNextLevel)
	{
	    this.level++;
	    this.linesForNextLevel = (this.level * 5);
	    for (LevelListener listener : this.levelListener)
	    {
		listener.onNewLevel();
	    }
	}
    }

    /**
     * Fonction qui ajuste la vitesse à laquelle un Tetromino tombe.
     */
    private void adjustSpeed()
    {
	this.GameEvents.cancel();
	this.timer.purge();

	this.GameEvents = new TimerTask()
	{
	    @Override
	    public void run()
	    {
		if (Game.this.currentTetromino != null)
		{
		    Game.this.UpdateWhenBlockHitsGround();
		}
	    }
	};
	this.timer.scheduleAtFixedRate(this.GameEvents, 0, 400 - (30 * this.level));

    }

    /**
     * Fonction qui ajoute un listener de nouveau tetromino.
     * 
     * @param newTetrominoListener
     *            -> Nouveau listener de nouveau tetromino à ajouter.
     */
    public void setNewTetrominoListener(NewTetrominoListener newTetrominoListener)
    {
	this.newTetrominoListener.add(newTetrominoListener);
    }

    /**
     * Fonction qui ajoute un listener de niveau
     * 
     * @param levelListener
     *            -> Nouveau listener de nouveau tetromino à ajouter.
     * 
     * @see LevelListener
     */
    public void setLevelListener(LevelListener levelListener)
    {
	this.levelListener.add(levelListener);
    }

    /**
     * Fonction qui ajoute un listener de fin de jeu.
     * 
     * @param gameEndListener
     *            -> Nouveau listener de fin de jeu.
     * 
     * @see GameEndListener
     */
    public void setGameEndListener(GameEndListener gameEndListener)
    {
	this.gameEndListener.add(gameEndListener);
    }

    /**
     * Fonction qui ajoute un listener de score.
     * 
     * @param scoreListener
     *            -> Nouveau listener de score.
     * 
     * @see ScoreListener
     */
    public void setScoreListener(ScoreListener scoreListener)
    {
	this.scoreListener.add(scoreListener);
    }

    /**
     * Fonction qui vérifie si une partie est perdu.
     * Envoie une notification aux listener de fin de jeu.
     * 
     * @return -> True si la partie est perdu, False sinon.
     * 
     * @see GameEndListener
     */
    private boolean isGameLost()
    {
	for (int y = 3; y >= 0; y--)
	{
	    for (int x = 0; x < Game.NBCASES_X; x++)
	    {
		if (this.gameGrid[x][y].getBlock() != null)
		{
		    for (GameEndListener listener : this.gameEndListener)
		    {
			listener.onGameEnd();
		    }
		    return true;

		}
	    }
	}
	return false;
    }

    /**
     * Getter du tetromino actuellement en jeu.
     * 
     * @return -> Le tetromino actuellement en jeu.
     * @see Tetromino
     */
    public Tetromino getCurrentTetrimino()
    {
	return this.currentTetromino;
    }

    /**
     * Fonction qui affiche l'état de la partie actuelle dans la console.
     * Les X sont des block, les espaces sont des cases vide.
     * 
     * @see Case
     * @see Block
     */
    public void PrintGame()
    {
	for (int y = 0; y < NBCASES_Y; y++)
	{
	    for (int x = 0; x < NBCASES_X; x++)
	    {
		this.gameGrid[x][y].Print();
	    }
	    System.out.println();
	}
    }

    /**
     * Getter de la grille de jeu.
     * 
     * @return -> Un tableau de deux dimensions représentant la grille de jeu.
     */
    public Case[][] getGameGrid()
    {
	return this.gameGrid;
    }

    /**
     * Getter du prochain Tetromino en jeu.
     * 
     * @return -> Le prochain Tetromino
     */
    public EnumShape getNextTetromino()
    {
	return this.nextTetromino;
    }

    /**
     * Setter de la permission de supprimer des lignes.
     * 
     * @param allowedToClearLine
     *            -> Nouvelle valeur boolean de la permission de supprimer des lignes.
     */
    public void setAllowedToClearLine(boolean allowedToClearLine)
    {
	this.allowedToClearLine = allowedToClearLine;
    }

    /**
     * Fonction qui déruit la physique du jeu (le timer).
     */
    public void Destroy()
    {
	this.timer.cancel();
	this.timer.purge();
    }

    /**
     * Getter du niveau
     * 
     * @return -> Le niveau de jeu.
     */
    public int getLevel()
    {
	return this.level;
    }

    /**
     * Fonction qui arrête la physique du jeu (le timer)
     */
    public void Pause()
    {
	this.timer.cancel();
	this.setPause(true);
    }

    /**
     * Fonction qui redémarre la physique du jeu (le timer)
     */
    public void Resume()
    {
	this.startTimer();
    }

    /**
     * Fonction qui crée un nouveau Timer pour la physique du jeu.
     */
    private void startTimer()
    {
	this.setPause(false);
	this.timer = new Timer();
	this.GameEvents = new TimerTask()
	{
	    @Override
	    public void run()
	    {
		if (Game.this.currentTetromino != null)
		{
		    Game.this.UpdateWhenBlockHitsGround();
		}
	    }
	};
	this.timer.scheduleAtFixedRate(this.GameEvents, 0, 400);
    }

    /**
     * Getter de isPause
     * 
     * @return -> True si isPause égale true, false sinon.
     */
    public boolean isPause()
    {
	return this.isPause;
    }

    /**
     * Setter de pause
     * 
     * @param isPause
     *            -> La nouvelle valeur de isPause.
     */
    private void setPause(boolean isPause)
    {
	this.isPause = isPause;
    }

    /**
     * Getter du score
     * 
     * @return -> Le score.
     */
    public int getScore()
    {
	return this.score;
    }
}