package hu.bp.game.gdx.barking;

import hu.bp.bark.Bark;
import hu.bp.bark.BarkImpl;
import hu.bp.bark.BarkPlayer;
import hu.bp.bark.BarkPlayerForGdx;
import hu.bp.comm.GraphicSerialCommunicator;
import hu.bp.comm.RandomSerialCommunicator;
import hu.bp.comm.SerialCommunicator;

import com.badlogic.gdx.Game;

public class BarkTestGame extends Game {

	private BarkPlayer player;
	private SerialCommunicator comm;
	private Bark bark;

	private BarkTestScreen testScreen;

	@Override
	public void create() {
		testScreen = new BarkTestScreen();

		this.setScreen(testScreen);

		player = new BarkPlayerForGdx("bark-%02d.wav", 1, 6);

		comm =
			new GraphicSerialCommunicator(
				testScreen.getEnemy(), testScreen.getPirSensor(),
				testScreen.getDistanceSensor());

		bark = new BarkImpl(comm, player);
	}

	@Override
	public void render () {
		super.render();
		bark.bark();
	}

}
