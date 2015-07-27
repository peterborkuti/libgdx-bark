package hu.bp.bark;

import com.badlogic.gdx.Gdx;

import hu.bp.comm.BarkData;
import hu.bp.comm.SerialCommunicator;

public class BarkImpl implements Bark {

	private long maxDelayAfterBark = 1000;
	public final int REMEMBER_MOVING_FOR_SECONDS = 5;

	private BarkPlayer player;
	private SerialCommunicator communicator;

	public BarkImpl(SerialCommunicator comm, BarkPlayer player) {
		this.player = player;
		this.communicator = comm;
	}

	@Override
	public void setMaxDelayAfterBark(long delay) {
		maxDelayAfterBark = delay;
	}

	@Override
	public void stop() {
		communicator.stop();
	}

	@Override
	public void start() {
		communicator.start();
	}

	@Override
	public void bark() {
		if (communicator.isDataAvailable()) {
			BarkData data = communicator.getData();
			Gdx.app.log("BarkImpl", data.toString());

			Distance dist =
				Distance.get(data.getDistance(), data.getMaxDistance());

			if (player.needPlay(dist, data.isPir(), REMEMBER_MOVING_FOR_SECONDS)
				&& !player.isPlaying()) {

				BarkDelay barkDelay =
					new BarkDelayImpl(maxDelayAfterBark, Distance.FAR.ordinal());

				int ordinal = dist.ordinal();
				
				long delay = barkDelay.getDelayInMillis(ordinal);

				Gdx.app.log(
					"BarkImpl",
					ordinal + 
						"delay:" + delay + "(" + barkDelay.getMinDelay(ordinal) +
						"," + barkDelay.getMaxDelay(ordinal) + ")");

				player.play(delay, getVolume());
			}
		}
	}

	private double getVolume() {
		return 0.5 + Math.random() * 0.5;
	}

	@Override
	public void setSerialCommunicator(SerialCommunicator comm) {
		communicator = comm;
	}

	@Override
	public void setBarkPlayer(BarkPlayer player) {
		this.player = player;
	}


}
