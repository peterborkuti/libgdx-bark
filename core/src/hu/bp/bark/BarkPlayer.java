package hu.bp.bark;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;

public abstract class BarkPlayer {

	volatile long lastMoveDetectedMillis = 0;
	volatile long startDelayInMillis = 0;
	volatile long delayAfterPlayInMillis = 0;

	final ArrayList<String> fileNames = new ArrayList<String>();

	public BarkPlayer(String fileNamePattern, int minIndex, int maxIndex) {
		for (int i = minIndex; i < maxIndex; i++) {
			String fileName = String.format(fileNamePattern, i);
			System.out.println(fileName);
			System.out.println(fileName);
			fileNames.add(fileName);
		}
	}

	//should be called when sound ends
	public void SoundEnded() {
		startDelayInMillis = System.currentTimeMillis();
	}

	public boolean isPlaying() {
		return
			isLibraryPlaying() ||
			(System.currentTimeMillis() < 
				startDelayInMillis + delayAfterPlayInMillis);
	}

	protected abstract void doPlay(float volume);
	protected abstract boolean isLibraryPlaying();

	public void play(int delayInMs, float volume) {
		delayAfterPlayInMillis = delayInMs;
		doPlay(volume);
	}

	public void rememberMoveDetection(boolean moveDetected) {
		if (moveDetected) {
			lastMoveDetectedMillis = System.currentTimeMillis();
		}
	};

	public boolean moveDetectedIn(boolean moveDetected, int seconds) {
		rememberMoveDetection(moveDetected);
		return ((System.currentTimeMillis() - lastMoveDetectedMillis) < (seconds * 1000));
	};

	public boolean needPlay(Distance distance, boolean moveDetected, int seconds) {
		return (Distance.FAR != distance) && moveDetectedIn(moveDetected, seconds);
	}
}
