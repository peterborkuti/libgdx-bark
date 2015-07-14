package hu.bp.comm;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SerialLineReaderTest {

	SerialReaderListener lineReader;

	@Before
	public void beforeTests() {
		lineReader = new SerialLineReader();
	}

	@Test
	public void testGetData_EmptyInput() {
		lineReader.eventReader("");
		assertTrue("".equals(lineReader.getData()));
	}

	@Test
	public void testGetData_NullInput() {
		lineReader.eventReader(null);
		assertTrue("".equals(lineReader.getData()));
	}

	@Test
	public void testGetData_OnlyNewLine() {
		assertTrue(lineReader.eventReader("\n"));
		assertTrue("".equals(lineReader.getData()));
	}

	@Test
	public void testGetData_StringEndWithNewLine() {
		assertTrue(lineReader.eventReader("Hello\n"));
		assertTrue("Hello".equals(lineReader.getData()));
	}


	@Test
	public void testGetData_StringWithoutNewLine() {
		assertFalse(lineReader.eventReader("Hello"));
		assertTrue("".equals(lineReader.getData()));
	}

	@Test
	public void testGetData_StringStartWithNewLine() {
		assertFalse(lineReader.eventReader("\nHello"));
		assertTrue("".equals(lineReader.getData()));
	}

	@Test
	public void testGetData_StringWithTwoNewLines1() {
		assertTrue(lineReader.eventReader("\nHello\n"));
		assertTrue("Hello".equals(lineReader.getData()));
	}

	@Test
	public void testGetData_StringWithTwoNewLines2() {
		assertTrue(lineReader.eventReader("abcd\nefghij\n"));
		assertTrue("efghij".equals(lineReader.getData()));
	}
}
