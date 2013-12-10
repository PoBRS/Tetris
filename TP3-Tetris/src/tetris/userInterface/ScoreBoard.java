package tetris.userInterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe d'affichage des trois meilleurs scores qui sont placés dans le HUD par la suite.
 * 
 * @author Pierre-Olivier Boulet
 * @author Raphaël Sylvain
 * 
 * @see HUD
 * 
 */
public class ScoreBoard
{
	List<String[]> scoreBoard = new ArrayList<String[]>(); // Tableau de tous les scores retrouvés dans highscores.txt.
	private int nbScores = 0;
	private String[][] top3; // Top 3 des scores retrouvés dans scoreBoard.

	/**
	 * Constructeur du ScoreBoard. Appelle les méthodes nécessaires à sa conception.
	 * 
	 * @throws FileNotFoundException
	 */
	public ScoreBoard() throws FileNotFoundException
	{
		this.top3 = new String[3][3];
		this.fillScoreBoard();
		this.findTop3ScoresInScoreBoard();

	}

	/**
	 * Recherche tous les scores dans le fichier "highscores.txt" et les ajoute à une liste.
	 * 
	 * @throws FileNotFoundException
	 */
	private void fillScoreBoard() throws FileNotFoundException
	{
		File file = new File("ressources/highscores.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		try
		{
			while ((line = br.readLine()) != null)
			{
				this.nbScores++;

				String[] stats = line.split(" ");

				this.scoreBoard.add(stats);
			}
			br.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Trouve les trois plus hauts résultats dans la liste du scoreBoard.
	 */
	private void findTop3ScoresInScoreBoard()
	{
		for (int top3Pos = 0; top3Pos < 3; top3Pos++)
		{

			int highestScore = 0;

			if (scoreBoard.size() > 0)
			{
				for (int scorePos = 0; scorePos < this.nbScores; scorePos++)
				{
					if (Integer.parseInt(scoreBoard.get(scorePos)[1]) > Integer.parseInt(scoreBoard.get(highestScore)[1]))
					{
						highestScore = scorePos;
					}
				}
				this.top3[top3Pos] = scoreBoard.get(highestScore);
				scoreBoard.remove(highestScore);
				this.nbScores--;
			}
			else
			{
				this.top3[top3Pos][0] = "N/A";
				this.top3[top3Pos][1] = "N/A";
			}
		}
	}

	/**
	 * Retourne le tableau des meilleurs scores avec les joueurs associés.
	 * 
	 * @return top3 -> Le tableau des trois meilleurs scores.
	 */
	public String[][] getTop3()
	{
		return top3;
	}
}
