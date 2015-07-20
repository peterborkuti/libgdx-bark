package hu.bp.bark;

import static org.junit.Assert.*;

import org.junit.Test;

public class DistanceTest {

	final static int MAX = 30;
	final static int close = 5;
	final static int near = 15;
	final static int border = 25;
	final static int far = 40;
	final static int tooClose = -1;
	final static int tooFar = 0;

	@Test
	public void isGet() {
		assertEquals(Distance.CLOSE, Distance.get(close, MAX));
		assertEquals(Distance.NEAR, Distance.get(near, MAX));
		assertEquals(Distance.BORDER, Distance.get(border, MAX));
		assertEquals(Distance.FAR, Distance.get(far, MAX));
		assertEquals(Distance.CLOSE, Distance.get(tooClose, MAX));
		assertEquals(Distance.FAR, Distance.get(tooFar, MAX));
	}

}
