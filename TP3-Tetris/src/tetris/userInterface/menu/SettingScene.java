
package tetris.userInterface.menu;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import tetris.userInterface.board.MainScene;

/**
 * Classe représentant la scène pour la fenètre de réglage.
 * 
 * @author Pierre-Olivier Boulet
 * @author Raphaël Sylvain
 * 
 * @since 09/12/2013
 * 
 * 
 */
public class SettingScene extends Scene
{
    private static final int MAXIMUM_NAME_LENGTH = 10;
    private static final String BACKGROUND_PATH = "file:ressources/background/Kremlin_Diorama_Effect.jpg";
    private final static int MAXIMUM_START_LEVEL = 9;
    private Stage primaryStage;
    private ComboBox<String> cbMusicSelection;
    private ComboBox<String> cbLevelSelection;
    private TextField txtName;

    /**
     * Constructeur de Setting Scene.
     * Créé tous les contrôles associés à l'interface.
     * 
     * @param primaryStage
     *            -> Fenêtre principale à laquelle on associe les scènes.
     * @param root
     *            -> Groupe de la scène.
     * @param StageWidth
     *            -> La largeur du Stage.
     * @param StageHeight
     *            -> La hauteur du Stage.
     * 
     * @todo Séparé le constructeur en sous-fonctions.
     */

    public SettingScene(Stage primaryStage, Group root, int StageWidth, int StageHeight)
    {
	super(root);
	this.primaryStage = primaryStage;

	Image backgroundImage = new Image(BACKGROUND_PATH);
	Paint backgroundPaint = new ImagePattern(backgroundImage, 0, 0, StageWidth, StageHeight, false);
	Rectangle background = new Rectangle(StageWidth, StageHeight, backgroundPaint);

	VBox menuBox = new VBox(75);
	menuBox.setAlignment(Pos.CENTER);
	Label lblGameTitle = new Label("Tetris");
	lblGameTitle.setFont(new Font("Goudy Stout", 30));

	Label lblMusicSelection = new Label("Thème : ");
	lblMusicSelection.setFont(new Font("Arial", 20));
	lblMusicSelection.setTextFill(Color.WHITE);
	ObservableList<String> musicOptions = FXCollections.observableArrayList("A", "B", "C");
	this.cbMusicSelection = new ComboBox<>(musicOptions);

	this.txtName = new TextField();
	this.txtName.textProperty().addListener(new ChangeListener<String>()
	{

	    @Override
	    public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue)
	    {
		// Check if the name is too long.
		if (newValue.length() > SettingScene.MAXIMUM_NAME_LENGTH)
		{
		    SettingScene.this.txtName.setText(oldValue);
		}
	    }
	});
	Label lblName = new Label("Nom : ");
	lblName.setTextFill(Color.WHITE);

	Label lblLevelSelection = new Label("Niveau: ");
	lblLevelSelection.setFont(new Font("Arial", 20));
	lblLevelSelection.setTextFill(Color.WHITE);
	ObservableList<String> levelOptions = FXCollections.observableArrayList();
	for (int i = 0; i < MAXIMUM_START_LEVEL; i++)
	{
	    levelOptions.add(i, Integer.toString(i + 1));
	}
	this.cbLevelSelection = new ComboBox<>(levelOptions);

	this.cbMusicSelection.getSelectionModel().selectFirst();

	Button btnStart = new Button("Débuter");
	btnStart.setOnAction(new EventHandler<ActionEvent>()
	{

	    @Override
	    public void handle(ActionEvent arg0)
	    {
		int level = Integer.parseInt(SettingScene.this.cbLevelSelection.getValue());
		int musicIndex = SettingScene.this.cbMusicSelection.getSelectionModel().getSelectedIndex();
		String name = SettingScene.this.txtName.getText();
		name = name.trim();
		name = name.replace(' ', '_');

		if (name.length() == 0)
		{
		    name = "Anonyme";
		}

		MainScene gameScene = new MainScene(SettingScene.this.primaryStage, new Group(), level, musicIndex, name);
		SettingScene.this.primaryStage.setScene(gameScene);

	    }
	});
	this.cbLevelSelection.getSelectionModel().selectFirst();

	HBox musicHBox = new HBox(20);
	musicHBox.getChildren().addAll(lblMusicSelection, this.cbMusicSelection);

	HBox nameHBox = new HBox(20);
	lblName.setFont(new Font("Arial", 20));
	nameHBox.getChildren().addAll(lblName, this.txtName);

	HBox levelHBox = new HBox(20);
	levelHBox.getChildren().addAll(lblLevelSelection, this.cbLevelSelection);

	menuBox.getChildren().addAll(lblGameTitle, musicHBox, levelHBox, nameHBox, btnStart);

	menuBox.setLayoutX((StageWidth / 2) - 100);
	menuBox.setLayoutY((StageHeight / 2) - 150);

	root.getChildren().addAll(background, menuBox);

    }
}
