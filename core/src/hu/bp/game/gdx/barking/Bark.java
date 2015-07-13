package hu.bp.game.gdx.barking;

import hu.bp.comm.CommHelper;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;

public class Bark extends ApplicationAdapter {

	private void startSerialReading() {
		if (commHelper == null) {
			commHelper = new CommHelper();
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

	Music barks[] = new Music[6];
	long delayInMillis;
	long startPlayingTime;
	int barkIndex;
	private boolean BARK = false;
	private CommHelper commHelper;

	private void initBarking() {
		for (int i = 1; i <= barks.length; i++) {
			//padding with zero from the left in two chars wide
			String num = ("00" + i).substring((""+i).length());
			String fileName = "bark-" + num + ".wav";
			barks[i - 1] = Gdx.audio.newMusic(Gdx.files.internal(fileName));
			barks[i - 1].setLooping(false);
		}

		barkIndex = MathUtils.random(barks.length - 1);
		delayInMillis = 200;
		startPlayingTime = TimeUtils.millis();
		delayInMillis = 200;
	}

	@Override
	public void create () {
		startSerialReading();

		initBarking();
	}

	private void bark() {
		if (!barks[barkIndex].isPlaying()) {
			if (TimeUtils.timeSinceMillis(startPlayingTime) > delayInMillis) {
				startPlayingTime = TimeUtils.millis();
				delayInMillis = MathUtils.random(100, 300);
				barkIndex = MathUtils.random(barks.length - 1);
				barks[barkIndex].setVolume((float)(Math.random() * 0.5 + 0.5));
				barks[barkIndex].play();
				Gdx.app.debug("bark", barkIndex + "," + delayInMillis);
			}
		}
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (BARK ) {
			bark();
		}
	}

}
