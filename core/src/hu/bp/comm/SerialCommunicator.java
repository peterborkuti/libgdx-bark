package hu.bp.comm;

public interface SerialCommunicator {
	public void stop();
	public void start();
	public boolean isDataAvailable();
	public BarkData getData();

}
