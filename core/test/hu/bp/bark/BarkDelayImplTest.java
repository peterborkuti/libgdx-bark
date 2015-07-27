package hu.bp.bark;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BarkDelayImplTest {
	private BarkDelay barkDelay;
	private long MAX_DELAY = 900;

	@Before
	public void setUp() {
		barkDelay = new BarkDelayImpl(MAX_DELAY, Distance.FAR.ordinal());
	}

	@Test
	public void testGetDelayInMillis() {
		for (Distance distance: Distance.values()) {
			long delay = barkDelay.getDelayInMillis(distance.ordinal());
			long minDelay = barkDelay.getMinDelay(distance.ordinal());
			long maxDelay = barkDelay.getMaxDelay(distance.ordinal());
			assertTrue(
				delay + " not in between (" + minDelay + "," + maxDelay +
				") for " + distance,
				isBetween(delay, minDelay, maxDelay));
		}
	}

	private boolean isBetween(long what, long min, long max) {
		return (min <= what) && (what <= max);
	}

	@Test
	public void testGetMinDelay() {
		assertEquals(0, barkDelay.getMinDelay(Distance.CLOSE.ordinal()));
		assertEquals(300, barkDelay.getMinDelay(Distance.NEAR.ordinal()));
		assertEquals(600, barkDelay.getMinDelay(Distance.BORDER.ordinal()));
		assertEquals(900, barkDelay.getMinDelay(Distance.FAR.ordinal()));
	}

	@Test
	public void testGetMaxDelay() {
		assertEquals(299, barkDelay.getMaxDelay(Distance.CLOSE.ordinal()));
		assertEquals(599, barkDelay.getMaxDelay(Distance.NEAR.ordinal()));
		assertEquals(899, barkDelay.getMaxDelay(Distance.BORDER.ordinal()));
		assertEquals(900, barkDelay.getMaxDelay(Distance.FAR.ordinal()));
	}
}
