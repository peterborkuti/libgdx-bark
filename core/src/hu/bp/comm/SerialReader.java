package hu.bp.comm;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.IOException;
import java.io.InputStream;

public class SerialReader implements SerialPortEventListener {

	InputStream in;

	private volatile String lastLine;

	private volatile StringBuffer tmp = new StringBuffer();

	private final SerialReaderListener listener;

	public SerialReader(InputStream in, SerialReaderListener listener) {
		this.in = in;
		this.listener = listener;
	}

	public void read() {
		byte[] buffer = new byte[1024];
		int len = -1;
		try {
			while ((len = this.in.read(buffer)) > -1) {
				System.out.println(tmp);
				tmp.append(new String(buffer, 0, len));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (listener.eventReader(tmp.toString())) {
			tmp.setLength(0);
		};
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