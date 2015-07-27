package hu.bp.bark;

import hu.bp.comm.SerialCommunicator;

public interface Bark {

	public void setSerialCommunicator(SerialCommunicator comm);

	public void setBarkPlayer(BarkPlayer player);

	public void setMaxDelayAfterBark(long delay);

	public void stop();

	public void start();

	public void bark();
}
