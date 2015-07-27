package hu.bp.bark;

public interface BarkDelay {
	/**
	 * generates a random long between min and max for the
	 * given ordinal of a Distance enum
	 * 
	 * @param distace
	 * @return a random long between minDelay and maxDelay
	 */
	public long getDelayInMillis(int ordinal);

	public long getMaxDelay(int ordinal);
	public long getMinDelay(int ordinal);

}
