package hu.bp.bark;

import com.badlogic.gdx.utils.Array;

public abstract class BarkPlayer {

	volatile long lastMoveDetectedMillis = 0;
	volatile long startDelayInMillis = 0;
	volatile long delayAfterPlayInMillis = 0;

	private volatile Array<String> fileNames;

	public Array<String> getFileNames() {
		return fileNames;
	}

	public void generateFileNames(String fileNamePattern, int minIndex, int maxIndex) {
		fileNames =  new Array<String>();

		for (int i = minIndex; i <= maxIndex; i++) {
			String fileName = String.format(fileNamePattern, i);
			fileNames.add(fileName);
		}
	}

	//should be called when sound ends
	public synchronized void SoundEnded() {
		startDelayInMillis = System.currentTimeMillis();
	}

	public boolean isPlaying() {
		return
			isLibraryPlaying() ||
			(System.currentTimeMillis() < 
				startDelayInMillis + delayAfterPlayInMillis);
	}

	protected abstract void doPlay(double d);
	protected abstract boolean isLibraryPlaying();

	public void play(long oNE_SEC, double d) {
		delayAfterPlayInMillis = oNE_SEC;
		doPlay(d);
	}

	public void rememberMoveDetection(boolean moveDetected) {
		if (moveDetected) {
			lastMoveDetectedMillis = System.currentTimeMillis();
		}
	}

	public boolean moveDetectedInSeconds(boolean moveDetected, int seconds) {
		rememberMoveDetection(moveDetected);
		return ((System.currentTimeMillis() - lastMoveDetectedMillis) < (seconds * 1000));
	}

	public boolean needPlay(Distance distance, boolean moveDetected, int seconds) {
		return (Distance.FAR != distance) && moveDetectedInSeconds(moveDetected, seconds);
	}
}
