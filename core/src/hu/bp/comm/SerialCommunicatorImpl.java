package hu.bp.comm;


public class SerialCommunicatorImpl implements SerialCommunicator {

	// S:1342:P:1:M:500
	public final int PART_USEC = 1;
	public final int PART_PIR = 3;
	public final int PART_MAX = 5;

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
			String parts[] = serialReaderListener.getData().split(":",6);

			String sPir = "0";
			String sMicrosecs = "0";
			String sMax = "500";


			try {
				sPir = parts[PART_PIR];
				sMicrosecs = parts[PART_USEC];
				sMax = parts[PART_MAX];
			}
			catch (Exception e) {
				System.out.println("ERROR:" + e.getMessage());
			}

			boolean pir = ("1".equals(sPir));
			int microsec = Math.round(Float.parseFloat(sMicrosecs));
			int max = Math.round(Float.parseFloat(sMax));

			data.setPir(pir);
			data.setUsec(microsec);
			data.setMaxDistance(max);
		}

		return data;
	}

}
