package hu.bp.game.gdx.barking;

import hu.bp.bark.Bark;
import hu.bp.bark.BarkImpl;
import hu.bp.bark.BarkPlayer;
import hu.bp.bark.BarkPlayerForGdx;
import hu.bp.comm.RandomSerialCommunicator;
import hu.bp.comm.SerialCommunicator;

import com.badlogic.gdx.ApplicationAdapter;

public class BarkApp extends ApplicationAdapter {

	private BarkPlayer player;
	private SerialCommunicator comm;
	private Bark bark;


	@Override
	public void pause() {
		bark.stop();
		super.pause();
	}

	@Override
	public void resume() {
		bark.start();
		super.resume();

	}

	@Override
	public void dispose() {
		bark.stop();
		super.dispose();
	}

	@Override
	public void create () {
		player = new BarkPlayerForGdx("bark-%02d.wav", 1, 6);
		comm = new RandomSerialCommunicator();
		bark = new BarkImpl(comm, player);
	}

	@Override
	public void render () {
		bark.bark();
	}

}
