
package tetris.backend;

import javafx.scene.paint.Color;

/**
 * Classe représentant un tetromino, un ensemble de quatres blocks.
 * 
 * @author Pierre-Olivier Boulet
 * @author Raphaël Sylvain
 * 
 * @see Block
 * @see Game
 * 
 * @since 09/12/2013
 * 
 */
public class Tetromino
{

    public static final Color COLOR_O = Color.YELLOW;
    public static final Color COLOR_J = Color.BLUE;
    public static final Color COLOR_L = Color.ORANGE;
    public static final Color COLOR_T = Color.PURPLE;
    public static final Color COLOR_Z = Color.RED;
    public static final Color COLOR_S = Color.GREEN;
    public static final Color COLOR_I = Color.TURQUOISE;

    private final static int FIRST_LINE = 0;
    private final static int LAST_LINE = 23;
    private final static int FIRST_COLUMN = 0;
    private final static int LAST_COLUMN = 9;

    private Block[] blocks;
    private Case[][] gameGrid;

    /**
     * Constructeur d'un tetromino
     * 
     * @param currentGame
     *            -> La partie sur laquelle le Tetromino se base pour se créer.
     * 
     * @todo -> Passer en paramètre la grille de jeu et la forme de Tetromino à faire au lieu de toute la game en entier.
     * @todo -> Ajuster les tests en conséquences.
     */
    public Tetromino(Game currentGame)
    {
	this.gameGrid = currentGame.getGameGrid();
	this.blocks = new Block[4];
	this.createTetrominoShape(currentGame.getNextTetromino());
    }

    /**
     * Fonction qui crée le Tetromino selon la forme en paramètre.
     * 
     * @param shapeOfTetromino
     *            -> Forme de Tetromino à produire.
     */
    private void createTetrominoShape(EnumShape shapeOfTetromino)
    {
	// Preset for a Tetromino's shape.
	switch (shapeOfTetromino)
	{
	    case I:
		this.blocks[0] = this.createBlock(4, 0, false, Tetromino.COLOR_I);
		this.blocks[1] = this.createBlock(4, 1, false, Tetromino.COLOR_I);
		this.blocks[2] = this.createBlock(4, 2, false, Tetromino.COLOR_I);
		this.blocks[3] = this.createBlock(4, 3, false, Tetromino.COLOR_I);
		break;
	    case S:
		this.blocks[0] = this.createBlock(6, 2, false, Tetromino.COLOR_S);
		this.blocks[1] = this.createBlock(5, 2, false, Tetromino.COLOR_S);
		this.blocks[2] = this.createBlock(5, 3, false, Tetromino.COLOR_S);
		this.blocks[3] = this.createBlock(4, 3, false, Tetromino.COLOR_S);
		break;
	    case Z:
		this.blocks[0] = this.createBlock(4, 2, false, Tetromino.COLOR_Z);
		this.blocks[1] = this.createBlock(5, 2, false, Tetromino.COLOR_Z);
		this.blocks[2] = this.createBlock(5, 3, false, Tetromino.COLOR_Z);
		this.blocks[3] = this.createBlock(6, 3, false, Tetromino.COLOR_Z);
		break;
	    case T:
		this.blocks[0] = this.createBlock(5, 1, false, Tetromino.COLOR_T);
		this.blocks[1] = this.createBlock(5, 2, true, Tetromino.COLOR_T);
		this.blocks[2] = this.createBlock(5, 3, false, Tetromino.COLOR_T);
		this.blocks[3] = this.createBlock(4, 2, false, Tetromino.COLOR_T);
		break;
	    case L:
		this.blocks[0] = this.createBlock(4, 1, false, Tetromino.COLOR_L);
		this.blocks[1] = this.createBlock(4, 2, true, Tetromino.COLOR_L);
		this.blocks[2] = this.createBlock(4, 3, false, Tetromino.COLOR_L);
		this.blocks[3] = this.createBlock(5, 3, false, Tetromino.COLOR_L);
		break;
	    case J:
		this.blocks[0] = this.createBlock(5, 1, false, Tetromino.COLOR_J);
		this.blocks[1] = this.createBlock(5, 2, true, Tetromino.COLOR_J);
		this.blocks[2] = this.createBlock(5, 3, false, Tetromino.COLOR_J);
		this.blocks[3] = this.createBlock(4, 3, false, Tetromino.COLOR_J);
		break;
	    case O:
		this.blocks[0] = this.createBlock(4, 2, false, Tetromino.COLOR_O);
		this.blocks[1] = this.createBlock(4, 3, false, Tetromino.COLOR_O);
		this.blocks[2] = this.createBlock(5, 2, false, Tetromino.COLOR_O);
		this.blocks[3] = this.createBlock(5, 3, false, Tetromino.COLOR_O);

		break;
	}
    }

    /**
     * Fonction qui ajoute un block dans la grille de jeu.
     * 
     * @param x
     *            -> La position horizontal du block dans la grille de jeu.
     * @param y
     *            -> La position vertical du block dans la grille de jeu.
     * @param pivot
     *            -> Boolean indiquant si ce Tetromino est un pivot.
     * @param colorBlock
     *            -> La couleur de block.
     * @return -> Le block créé.
     */
    private Block createBlock(int x, int y, boolean pivot, Color colorBlock)
    {
	Block newBlock = new Block(x, y, pivot, colorBlock);
	this.gameGrid[x][y].setBlock(newBlock);
	return newBlock;
    }

    /**
     * Fonction qui change les position tout les blocks composant le tetromino selon des valeurs en paramètre.
     * 
     * @param translateX
     *            -> La translation horizontal à effectuer.
     * @param translateY
     *            -> La translation vertical à effectuer.
     * @exception -> Les valeurs passer en paramètres doivent garder l'intégrité du Tetromino.
     */
    private void setNewPositionTranslate(int translateX, int translateY)
    {
	for (int i = 0; i < 4; i++)
	{
	    Block blockToMove = this.blocks[i];
	    int blockPosXInit = blockToMove.getPosX();
	    int blockPosYInit = blockToMove.getPosY();
	    int blockPosXFinal = (blockPosXInit + translateX);
	    int blockPosYFinal = (blockPosYInit + translateY);

	    this.blocks[i].setPosX(blockPosXFinal);
	    this.blocks[i].setPosY(blockPosYFinal);

	    this.gameGrid[blockPosXInit][blockPosYInit].setBlock(null);
	}
	this.changePosOnGrid();
    }

    /**
     * Fonction qui appliques les changements de position des blocks composant le tetromino dans la grille.
     */
    private void changePosOnGrid()
    {

	for (int i = 0; i < 4; i++)
	{
	    Block blockToMove = this.blocks[i];
	    int blockPosXNew = blockToMove.getPosX();
	    int blockPosYNew = blockToMove.getPosY();
	    this.gameGrid[blockPosXNew][blockPosYNew].setBlock(blockToMove);
	}
    }

    /**
     * Fonction qui bouge le Tetromino vers la gauche. Il vérifie si ce mouvement est possible avant de l'exécuter.
     * 
     * @return -> True si le mouvement a pu être effectué, false si le mouvement est imposssible.
     */
    public boolean MoveLeft()
    {
	boolean allowedMove = this.AllowedMoveHorizontal(-1);

	if (allowedMove)
	{
	    this.setNewPositionTranslate(-1, 0);
	    return true;
	}
	else
	{
	    return false;
	}
    }

    /**
     * Fonction qui s'assure que le mouvement horizontal est possible.
     * 
     * @param displacementAmplitude
     *            -> La distance de translation des blocks. Négatif pour mouvement vers la gauche, positif pour vers la droite.
     * @return -> True si le mouvment est acceptable, sinon False.
     */
    private boolean AllowedMoveHorizontal(int displacementAmplitude)
    {
	int blockPosX;
	int blockPosY;
	int leftBlockPosX;
	Block blockToCheck;

	for (int i = 0; i < 4; i++)
	{
	    blockPosX = this.blocks[i].getPosX();
	    blockPosY = this.blocks[i].getPosY();

	    if (displacementAmplitude == -1 && blockPosX == Tetromino.FIRST_COLUMN)
	    {
		return false;
	    }

	    if (displacementAmplitude == 1 && blockPosX == Tetromino.LAST_COLUMN)
	    {
		return false;
	    }

	    leftBlockPosX = this.blocks[i].getPosX() + displacementAmplitude;
	    blockToCheck = this.gameGrid[leftBlockPosX][blockPosY].getBlock();

	    if (blockToCheck != null && blockToCheck.isFixed())
	    {
		return false;
	    }

	}
	return true;
    }

    /**
     * Fonction qui donne la position la plus basse du Tetromino.
     * 
     * @return -> La position Y du block le plus bas du Tetromino.
     */
    public int findTetrominoLowestPosition()
    {
	int tetrominoLandingCase = 0;

	for (int i = 0; i < 4; i++)
	{
	    if (this.blocks[i].getPosY() > tetrominoLandingCase)
	    {
		tetrominoLandingCase = this.blocks[i].getPosY();
	    }
	}

	return tetrominoLandingCase;
    }

    /**
     * Fonction qui bouge le Tetromino vers la droite. Il vérifie si ce mouvement est possible avant exécuter.
     * 
     * @return -> True si le mouvement a pu être effectué, false si le mouvement est imposssible.
     */
    public boolean MoveRight()
    {
	boolean allowedMove = this.AllowedMoveHorizontal(1);

	if (allowedMove)
	{
	    this.setNewPositionTranslate(1, 0);
	    return true;
	}
	else
	{
	    return false;
	}

    }

    /**
     * Fonction qui bouge le Tetromino vers le bas. Il vérifie si ce mouvement est possible avant de l'exécuter.
     * 
     * @return -> True si le mouvement a pu être effectué, false si le mouvment est impossible.
     * 
     * @todo -> Séparé la recherche du block sous un block dans une nouvelle fonction.
     */
    public boolean MoveDown()
    {
	boolean allowedMove = true;

	int blockPosY;
	int blockPosX;
	int blockPosYInferior;
	Block inferiorBlock;

	for (int i = 0; i < 4; i++)
	{

	    blockPosY = this.blocks[i].getPosY();
	    blockPosX = this.blocks[i].getPosX();

	    // A block is on the last line.
	    if (blockPosY == Tetromino.LAST_LINE)
	    {
		allowedMove = false;
	    }
	    else
	    {

		// Find the block under a block.
		blockPosYInferior = this.blocks[i].getPosY() + 1;
		inferiorBlock = this.gameGrid[blockPosX][blockPosYInferior].getBlock();

		// If a block is on top of a fixed block.
		if (inferiorBlock != null && inferiorBlock.isFixed())
		{
		    allowedMove = false;
		}
	    }

	}

	if (allowedMove)
	{
	    this.setNewPositionTranslate(0, 1);
	}
	return allowedMove;

    }

    /**
     * Fonction qui fait tourner un block dans le sens horaire.
     * Il choisi l'algorithme qui effectura le mouvement selon certains critères.
     * 
     * @return -> True si la rotation a pu être effectué, false sinon.
     */
    public boolean RotateCallManager()
    {

	double pivotX = 0;
	double pivotY = 0;
	boolean rotationSuccessful = false;
	Block pivotBlock = this.getPivotBlock();

	if (pivotBlock == null)
	{
	    double[] pivotPos;
	    pivotPos = findPivotPos();
	    pivotX = pivotPos[0];
	    pivotY = pivotPos[1];
	}
	else
	{
	    pivotX = pivotBlock.getPosX();
	    pivotY = pivotBlock.getPosY();

	}

	rotationSuccessful = this.rotateBlockAroundPivot(pivotX, pivotY);
	return rotationSuccessful;
    }

    /**
     * Fonction qui permet de trouver quel est le point de pivot d'un Tetromino si celui-ci n'a pas été donné.
     * 
     * @return -> Tableau contenant les positions horizontals et verticals du pivot.
     */
    private double[] findPivotPos()
    {
	int maxX = 0;
	int maxY = 0;
	int minX = Integer.MAX_VALUE;
	int minY = Integer.MAX_VALUE;
	int sumX = 0;
	int sumY = 0;
	double[] pivotPos = new double[2];

	for (Block blockToRotate : this.blocks)
	{
	    int blockPosX = blockToRotate.getPosX();
	    int blockPosY = blockToRotate.getPosY();

	    if (blockPosX > maxX)
	    {
		maxX = blockPosX;
	    }

	    if (blockPosX < minX)
	    {
		minX = blockPosX;
	    }
	    if (blockPosY > maxY)
	    {
		maxY = blockPosY;
	    }
	    if (blockPosY < minY)
	    {
		minY = blockPosY;
	    }

	}

	for (int i = minX; i <= maxX; i++)
	{
	    sumX += i;
	}
	for (int i = minY; i <= maxY; i++)
	{
	    sumY += i;
	}

	pivotPos[0] = (sumX / (double) (maxX - minX + 1));
	pivotPos[1] = (sumY / (double) (maxY - minY + 1));
	return pivotPos;

    }

    /**
     * Fonction qui fait tourner un block autour d'un pivot.
     * 
     * @param pivotX
     *            -> La position horizontal du pivot
     * @param pivotY
     *            -> La position vertical du pivot
     * @return True si la rotation a pu être effectué, False sinon.
     * 
     * @todo Séparer cette fonction pour la rendre plus propre.
     */
    private boolean rotateBlockAroundPivot(double pivotX, double pivotY)
    {
	int[][] newPosBlocks = new int[4][2];
	int currentBlockInArray = 0;
	boolean validRotation = true;

	for (Block blockToRotate : this.blocks)
	{
	    int blockToRotatePosX = blockToRotate.getPosX();
	    int blockToRotatePosY = blockToRotate.getPosY();
	    this.gameGrid[blockToRotatePosX][blockToRotatePosY].setBlock(null);

	    double relativeXToPivot = blockToRotate.getPosX() - pivotX;
	    double relativeYToPivot = blockToRotate.getPosY() - pivotY;

	    // The rotation
	    int newPointXRelativeToPivot = (int) Math.round(-relativeYToPivot); // The point x is the negative old point y.
	    int newPointYRelativeToPivot = (int) Math.round(relativeXToPivot); // And the new y is the old x.

	    int newPositionX = (int) this.roundBelowZero(pivotX + newPointXRelativeToPivot);
	    int newPositionY = (int) this.roundBelowZero(pivotY + newPointYRelativeToPivot);

	    newPosBlocks[currentBlockInArray][0] = newPositionX;
	    newPosBlocks[currentBlockInArray][1] = newPositionY;

	    currentBlockInArray++;

	}

	// Verify space to fill.
	for (int i = 0; i < 4 && validRotation; i++)
	{
	    int newPosX = newPosBlocks[i][0];
	    int newPosY = newPosBlocks[i][1];

	    if (newPosX < Tetromino.FIRST_COLUMN || newPosX > Tetromino.LAST_COLUMN)
	    {
		validRotation = false;
	    }

	    if (newPosY < Tetromino.FIRST_LINE || newPosY > Tetromino.LAST_LINE)
	    {
		validRotation = false;
	    }

	    if (validRotation && this.gameGrid[newPosX][newPosY].getBlock() != null)
	    {
		validRotation = false;
	    }
	}

	// Block move
	if (validRotation)
	{
	    for (int i = 0; i < 4; i++)
	    {
		this.blocks[i].setPosX(newPosBlocks[i][0]);
		this.blocks[i].setPosY(newPosBlocks[i][1]);
	    }
	}
	this.changePosOnGrid();
	return validRotation;
    }

    /**
     * Fonction mathématique.
     * Arrondit vers les bas les valeurs négatives, mais conserve la valeur si elle est positive
     * 
     * @param val
     *            -> La valeur à arrondir.
     * @return -> La valeur arrondit vers le bas si elle est négative, la même valeur si elle est positive.
     */
    private double roundBelowZero(double val)
    {
	if (val < 0)
	{
	    return Math.floor(val);
	}
	else
	{
	    return val;
	}
    }

    /**
     * Fonction qui retourne le block du Tetromino représentant le pivot.
     * 
     * @return -> Le block pivot, null si aucun block n'est un pivot.
     */
    private Block getPivotBlock()
    {
	for (Block block : this.blocks)
	{
	    if (block.isPivot())
	    {
		return block;
	    }
	}

	return null;
    }

    /**
     * Fonction qui fixe les blocks du Tetromino actuel.
     * 
     * @see Block
     */
    public void Deactivate()
    {
	for (int i = 0; i < 4; i++)
	{
	    this.blocks[i].setFixed(true);
	}
    }

    /**
     * Getter des blocks du Tetromino;
     * 
     * @return -> Un tableau de block, les blocks du Tetrimino.
     */
    public Block[] getBlocks()
    {
	return this.blocks;
    }
}