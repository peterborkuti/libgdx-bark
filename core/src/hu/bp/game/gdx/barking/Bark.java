package hu.bp.game.gdx.barking;

import hu.bp.bark.BarkPlayerForGdx;
import hu.bp.bark.Distance;
import hu.bp.comm.CommHelper;
import hu.bp.comm.SerialLineReader;
import hu.bp.comm.SerialReaderListener;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class Bark extends ApplicationAdapter {

	private static final int MAX_DISTANCE = 500; // cm

	private void startSerialReading() {
		if (commHelper == null) {
			commHelper = new CommHelper(serialReaderListener);
		}

		commHelper.listPorts();

		if (!commHelper.isOpen()) {
			commHelper.connectToFirstSerialPort(115200);
		}
	}

	@Override
	public void pause() {
		if (commHelper != null) {
			commHelper.pause();
		}

		super.pause();
	}

	@Override
	public void resume() {
		super.resume();

		startSerialReading();
	}

	@Override
	public void dispose() {
		if (commHelper != null) {
			commHelper.dispose();
		}

		super.dispose();
	}

	BarkPlayerForGdx player = new BarkPlayerForGdx("bark-%2d.wav", 1, 6);

	private boolean BARK = true;
	private SerialReaderListener serialReaderListener = new SerialLineReader();
	private CommHelper commHelper;


	@Override
	public void create () {
		startSerialReading();

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		boolean pir = false;
		int distance = MAX_DISTANCE;

		if ((serialReaderListener != null) && serialReaderListener.isData()) {
			String parts[] = serialReaderListener.getData().split(":",4);
			pir = ("1".equals(parts[3]));
			int microsec = Math.round(Float.parseFloat(parts[1]));
			if (pir && microsec != 0) {
				distance = Math.round(microsec / 74.0f / 2.0f);
				Gdx.app.log("Bark","distance:" + distance);
			}
		}

		Distance dist = Distance.get(distance, MAX_DISTANCE);

		if (BARK && player.needPlay(dist, pir, 5)) {
			player.play(200, 1);
		}
	}

}
