package hu.bp.bark;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Music.OnCompletionListener;
import com.badlogic.gdx.math.MathUtils;

public class BarkPlayerForGdx extends BarkPlayer implements OnCompletionListener{

	private ArrayList<Music> barks = new ArrayList<Music>();

	private Music music;

	public BarkPlayerForGdx(String fileNamePattern, int minIndex, int maxIndex) {
		super(fileNamePattern, minIndex, maxIndex);
		for(String fileName: fileNames) {
			Gdx.app.log("BarkPlayer", fileName);
			Music m = Gdx.audio.newMusic(Gdx.files.internal(fileName));
			m.setLooping(false);
			m.setOnCompletionListener(this);
			barks.add(m);
		}
	}

	@Override
	protected void doPlay(float volume) {
		music = barks.get(MathUtils.random(barks.size() - 1));
		music.play();
	}

	@Override
	protected boolean isLibraryPlaying() {
		return music.isPlaying();
	}

	@Override
	public void onCompletion(Music music) {
		SoundEnded();
	}

}
