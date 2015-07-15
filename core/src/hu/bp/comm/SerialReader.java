package hu.bp.comm;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.IOException;
import java.io.InputStream;

public class SerialReader implements SerialPortEventListener {

	InputStream in;

	private volatile StringBuffer tmp = new StringBuffer("");

	private final SerialReaderListener listener;

	public SerialReader(InputStream in, SerialReaderListener listener) {
		this.in = in;
		this.listener = listener;
	}

	public synchronized void read() {
		byte[] buffer = new byte[1024];
		int len = -1;
		try {
			while (((len = this.in.read(buffer)) > 0)) {
				tmp.append(new String(buffer, 0, len));
				if (listener.eventReader(tmp.toString())) {
					tmp.setLength(0);
				};
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void serialEvent(SerialPortEvent event) {
		switch (event.getEventType()) {
		case SerialPortEvent.DATA_AVAILABLE:
			System.out.println("Data available");
			read();
			break;
		}
	}

}