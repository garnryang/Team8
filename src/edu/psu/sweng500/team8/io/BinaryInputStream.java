package edu.psu.sweng500.team8.io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

public class BinaryInputStream {
	private FileInputStream fileStream;
	private BufferedInputStream inputStream;
	
	public BinaryInputStream(String filename) throws FileNotFoundException {
		this.fileStream = new FileInputStream(filename);
		this.inputStream = new BufferedInputStream(fileStream);
	}
	
	public int readInt() throws IOException {
		ByteBuffer bytes = ByteBuffer.wrap(new byte[4]);
		this.inputStream.read(bytes.array());
		return bytes.getInt();
	}
	
	public void close() throws IOException {
		this.inputStream.close();
		this.fileStream.close();
	}
}
