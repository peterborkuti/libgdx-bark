package hu.bp.game.gdx.barking;

import hu.bp.comm.CommHelper;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;

public class Bark extends ApplicationAdapter {
	Music barks[] = new Music[6];
	long delayInMillis;
	long startPlayingTime;
	int barkIndex;
	@Override
	public void create () {
		CommHelper ports = new CommHelper();
		ports.listPorts();

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
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
}
