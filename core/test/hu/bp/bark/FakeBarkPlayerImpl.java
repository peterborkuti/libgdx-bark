package hu.bp.bark;

public class FakeBarkPlayerImpl extends BarkPlayer {

	public long endPlayTime = 0;
	public static long ONE_SEC = 1000;

	@Override
	protected void doPlay(double volume) {
		endPlayTime =
			System.currentTimeMillis() +
			delayAfterPlayInMillis + ONE_SEC;
	}

	@Override
	protected boolean isLibraryPlaying() {
		return System.currentTimeMillis() < endPlayTime;
	}

}
