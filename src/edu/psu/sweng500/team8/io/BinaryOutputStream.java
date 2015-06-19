package edu.psu.sweng500.team8.io;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class BinaryOutputStream {
	private FileOutputStream fileStream;
	private BufferedOutputStream outputStream;
	
	public BinaryOutputStream(String filename) throws FileNotFoundException {
		this.fileStream = new FileOutputStream(filename);
		this.outputStream = new BufferedOutputStream(fileStream);
	}
	
	public void writeInt(int data) throws IOException {
		byte[] bytes = ByteBuffer.allocate(4).putInt(data).array();
		this.outputStream.write(bytes);
	}
	
	public void close() throws IOException {
		this.outputStream.flush();
		this.fileStream.flush();
		this.outputStream.close();
		this.fileStream.close();
	}
}
