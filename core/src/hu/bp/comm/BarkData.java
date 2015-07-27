package hu.bp.comm;

public class BarkData {
	@Override
	public String toString() {
		return "[" + getDistance() + "," + pir + "," + maxDistance + "]";
	}
	public int getUsec() {
		return usec;
	}
	public void setUsec(int usec) {
		this.usec = usec;
	}
	public boolean isPir() {
		return pir;
	}
	public void setPir(boolean pir) {
		this.pir = pir;
	}
	public int getMaxDistance() {
		return maxDistance;
	}
	public void setMaxDistance(int maxDistance) {
		this.maxDistance = maxDistance;
	}

	public void setDistance(int distance) {
		usec = distance * 2 * 74;
	}

	public int getDistance() {
		return Math.round(usec / 74.0f / 2.0f);
	}

	private int usec = 0;
	private boolean pir = false;
	private int maxDistance = 500;


}
