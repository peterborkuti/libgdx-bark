package hu.bp.comm;

public class RandomSerialCommunicator implements SerialCommunicator {

	public static final int DATA_ARRIVAL_DELAY = 200; // ms
	private long lastData = 0;

	@Override
	public void stop() {
	}

	@Override
	public void start() {
	}

	@Override
	public boolean isDataAvailable() {
		if ((System.currentTimeMillis() - lastData) > DATA_ARRIVAL_DELAY) {
			lastData = System.currentTimeMillis();

			return true;
		}

		return false;
	}

	@Override
	public BarkData getData() {
		BarkData data = new BarkData();
		data.setMaxDistance(500);
		data.setPir(Math.random() < 0.2);
		data.setDistance((int)Math.round(Math.random() * 500));

		return data;
	}

}
