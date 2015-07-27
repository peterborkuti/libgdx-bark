package hu.bp.comm;


public class SerialCommunicatorImpl implements SerialCommunicator {

	private SerialReaderListener serialReaderListener;
	private CommHelper commHelper;

	public SerialCommunicatorImpl() {
		serialReaderListener = new SerialLineReader();
	}

	@Override
	public void stop() {
		if (commHelper != null) {
			commHelper.stop();
		}
	}

	@Override
	public void start() {
		if (commHelper == null) {
			commHelper = new CommHelper(serialReaderListener);
		}

		if (!commHelper.isOpen()) {
			commHelper.connectToFirstSerialPort(115200);
		}

	}

	@Override
	public boolean isDataAvailable() {
		return serialReaderListener.isData();
	}

	@Override
	public BarkData getData() {
		BarkData data = new BarkData();

		if ((serialReaderListener != null) && serialReaderListener.isData()) {
			String parts[] = serialReaderListener.getData().split(":",4);

			boolean pir = ("1".equals(parts[3]));
			int microsec = Math.round(Float.parseFloat(parts[1]));

			data.setPir(pir);
			data.setUsec(microsec);
		}

		return data;
	}

}
