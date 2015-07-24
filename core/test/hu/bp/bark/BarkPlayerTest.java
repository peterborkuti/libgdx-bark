package hu.bp.bark;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

public class BarkPlayerTest {
	BarkPlayer player;

	@Before
	public void setUp() {
		player = new FakeBarkPlayerImpl();
	}

	@Test
	public void testGenerateFileNames() {
		player.generateFileNames("%02d.wav", 1, 3);
		assertEquals(
			"01.wav,02.wav,03.wav",
			player.getFileNames().toString(","));
	}

	@Test
	public void testPlayDuration() throws InterruptedException {
		player.play(FakeBarkPlayerImpl.ONE_SEC, Math.random());

		//playing time is 1 sec, delay is 1 sec
		TimeUnit.MILLISECONDS.sleep(500);
		assertTrue(player.isPlaying());
		TimeUnit.MILLISECONDS.sleep(1000);
		assertTrue(player.isPlaying());
		TimeUnit.MILLISECONDS.sleep(1000);
		assertFalse(player.isPlaying());
	}

}
