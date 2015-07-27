package hu.bp.bark;

public class BarkDelayImpl implements BarkDelay {
	private double maxDelay;
	private double divider;

	public BarkDelayImpl(long maxDelayInMillis, int divider) {
		maxDelay = maxDelayInMillis;
		this.divider = divider;
	}

	@Override
	public long getMaxDelay(int ordinal) {
		if (ordinal < divider) {
			return getMinDelay(ordinal + 1) - 1; 
		}

		return Math.round(maxDelay);
	}

	@Override
	public long getMinDelay(int ordinal) {
		if (ordinal >= divider) {
			return Math.round(maxDelay);
		}

		return Math.round(((double)ordinal) * (maxDelay / divider));
	}

	@Override
	public long getDelayInMillis(int ordinal) {
		long d = getMaxDelay(ordinal) - getMinDelay(ordinal);
		return getMinDelay(ordinal) + Math.round(Math.random() * d);
	}

}
