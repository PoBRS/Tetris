
package tetris.userInterface;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicPlayer implements Runnable
{
    String musicURI;

    MusicPlayer(String musicURI)
    {
	this.musicURI = musicURI;
    }

    @Override
    public void run()
    {
	Media mediaToPlay = new Media(musicURI);
	MediaPlayer musicPlayer = new MediaPlayer(mediaToPlay);
	musicPlayer.setCycleCount(musicPlayer.INDEFINITE);
	musicPlayer.setVolume(0.1);
	musicPlayer.play();

    }

}
