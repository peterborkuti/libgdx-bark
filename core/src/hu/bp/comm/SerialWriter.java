package hu.bp.comm;

import java.io.IOException;
import java.io.OutputStream;

public class SerialWriter implements Runnable {

	OutputStream out;

	/**
	 * http://stackoverflow.com/questions/10961714/how-to-properly-stop-the-thread-in-java
	 */
	private volatile boolean running = true;

	public void terminate() {
		running = false;
	}

	public SerialWriter(OutputStream out) {
		this.out = out;
	}

	public void run() {
		try {
			int c = 0;
			while ((c = System.in.read()) > -1) {
				this.out.write(c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}