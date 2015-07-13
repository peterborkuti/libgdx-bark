package hu.bp.comm;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.IOException;
import java.io.InputStream;

public class SerialReader implements SerialPortEventListener {

	InputStream in;

	public SerialReader(InputStream in) {
		this.in = in;
	}

	public void read() {
		byte[] buffer = new byte[1024];
		int len = -1;
		try {
			while ((len = this.in.read(buffer)) > -1) {
				System.out.println(len);
				System.out.println(new String(buffer, 0, len));
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