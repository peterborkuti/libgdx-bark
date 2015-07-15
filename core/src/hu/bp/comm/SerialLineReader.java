package hu.bp.comm;

public class SerialLineReader implements SerialReaderListener {

	private volatile String line = "";

	@Override
	public boolean eventReader(String read) {
		if (read == null) {
			return false;
		}

		int right = read.lastIndexOf("\n");

		if (("".equals(read)) || (right == -1) || 
			(right < read.length() - 1)) {
			return false;
		}

		if (right == 0) {
			line = "";
			return true;
		}

		String lines[] = read.split("\n");

		line = lines[lines.length - 1];

		return true;
	}

	@Override
	public boolean isData() {
		return !"".equals(line);
	}

	@Override
	public String getData() {
		String tmp = line;
		line = "";

		return tmp;
	}

}
