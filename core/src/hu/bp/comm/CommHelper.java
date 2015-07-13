package hu.bp.comm;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.TooManyListenersException;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

import gnu.io.CommPort;
/**
 * apt-get install librxtx-java
 * Add RXTXcomm.java to build path (See this project's Serial Communication
 * User Lib
 */
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

/**
 * codebase:
 * https://www.henrypoon.com/blog/2011/01/01/serial-communication-in-java
 * -with-example-program/
 * 
 * @author Peter Borkuti
 *
 */
public class CommHelper implements ApplicationListener {
	// Arduino Serial Settings
	private int BAUD_RATE = 9600;
	final static int STOP_BIT = SerialPort.STOPBITS_1;
	final static int PARITY = SerialPort.PARITY_NONE;
	final static int DATA_BITS = SerialPort.DATABITS_8;
	final static boolean DTR = true;
	final static int FLOW_CONTROL = SerialPort.FLOWCONTROL_NONE;

	/**
	 * http://stackoverflow.com/questions/10382578/flow-controll-settings-for-
	 * serial-communication-between-java-rxtx-and-arduino
	 * 
	 */
	final static boolean RTS = true;

	// the timeout value for connecting with the port
	final static int TIMEOUT = 2000;

	// some ascii values for for certain things
	final static int SPACE_ASCII = 32;
	final static int DASH_ASCII = 45;
	final static int NEW_LINE_ASCII = 10;
	private InputStream in;
	private OutputStream out;

	private CommPort commPort = null;
	private boolean isOpen = false;

	public void listPorts() {
		Gdx.app.log("CommPort", "list port");

		@SuppressWarnings("unchecked")
		java.util.Enumeration<CommPortIdentifier> ports = CommPortIdentifier
				.getPortIdentifiers();

		while (ports.hasMoreElements()) {
			CommPortIdentifier curPort = ports.nextElement();
			Gdx.app.log("CommPort",
					"Port:" + curPort.getName() + "-" + curPort.getPortType());
		}
	}

	private void initSerialPort(SerialPort port)
			throws UnsupportedCommOperationException, IOException,
			TooManyListenersException {
		// Arduino: 8-N-1, speed : 115200
		port.setSerialPortParams(BAUD_RATE, DATA_BITS, STOP_BIT, PARITY);
		port.setDTR(DTR);
		port.setFlowControlMode(FLOW_CONTROL);
		port.setRTS(RTS);

		in = port.getInputStream();
		out = port.getOutputStream();

		// (new Thread(new SerialWriter(out))).start();

		port.addEventListener(new SerialReader(in));
		port.notifyOnDataAvailable(true);
	}

	private void connect(CommPortIdentifier port) {

		try {
			commPort = port.open("Barking", TIMEOUT);

			SerialPort serialPort = (SerialPort) commPort;

			initSerialPort(serialPort);

			isOpen = true;

		} catch (PortInUseException e) {
			Gdx.app.error("CommHelper", port + " is in use. (" + e.toString()
					+ ")");
		} catch (Exception e) {
			Gdx.app.error("CommHelper",
					"Failed to open " + port + "(" + e.toString() + ")");
		}
	}

	public void connectToFirstSerialPort() {
		connectToFirstSerialPort(BAUD_RATE);
	}

	public void connectToFirstSerialPort(int baudRate) {
		Gdx.app.log("CommPort", "connect to first port");

		BAUD_RATE = baudRate;

		@SuppressWarnings("unchecked")
		java.util.Enumeration<CommPortIdentifier> ports = CommPortIdentifier
				.getPortIdentifiers();

		while (ports.hasMoreElements()) {
			CommPortIdentifier curPort = ports.nextElement();

			Gdx.app.log("CommPort",
					"Port:" + curPort.getName() + "-" + curPort.getPortType());

			if (curPort.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				connect(curPort);
				break;
			}

		}
	}

	public boolean isOpen() {
		return isOpen;
	}

	@Override
	public void create() {
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void render() {
	}

	@Override
	public void pause() {
		Gdx.app.log("CommHelper", "pause");
		stop();
	}

	@Override
	public void resume() {
		Gdx.app.log("CommHelper", "resume");
		if (!isOpen) {
			connectToFirstSerialPort();
		}
	}

	private void stop() {
		if (commPort != null && isOpen) {
			commPort.close();
			commPort = null;
			isOpen = false;
		}
	}

	@Override
	public void dispose() {
		Gdx.app.log("CommHelper", "dispose");
		stop();
	}

}
