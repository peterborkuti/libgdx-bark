package hu.bp.comm;

public interface SerialReaderListener {
	public boolean eventReader(String read);
	public String getData();
	public boolean isData();
}
