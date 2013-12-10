
package tetris.userInterface;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import tetris.backend.GameEndListener;
import tetris.userInterface.board.MainScene;

/**
 * Classe qui s'oocupe de l'enregistrement du Score.
 * 
 * @author Pierre-Olivier Boulet
 * @Raphaël Sylvain
 * 
 */
public class Score implements GameEndListener
{
    private static final String RESSOURCES_HIGHSCORE_FILE = "ressources/highscores.txt";
    private String name;
    private MainScene mainScene;
    private int score = 0;

    /**
     * Constructeur de Score.
     * Se met à l'écoute du GameEndListener
     * 
     * @param mainScene
     *            -> La Scene de jeu
     * @param name
     *            -> Le nom du joueur.
     * 
     * @see GameEndListener
     */
    public Score(MainScene mainScene, String name)
    {
	mainScene.getCurrentGame().setGameEndListener(this);
	this.mainScene = mainScene;
	this.name = name;
    }

    /**
     * L'action réalisé lorsque le GameEndListener lance une notification.
     * On enregistre le nom du joueur et son score dans le fichier.
     */
    @Override
    public void onGameEnd()
    {
	this.score = this.mainScene.getCurrentGame().getScore();
	try
	{
	    FileWriter outFile = new FileWriter(Score.RESSOURCES_HIGHSCORE_FILE, true);
	    PrintWriter out = new PrintWriter(outFile);
	    out.println(this.name + " " + this.score);
	    out.close();
	}
	catch (IOException e)
	{
	    e.printStackTrace();
	}
    }

}
