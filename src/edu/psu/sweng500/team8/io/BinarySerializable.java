package edu.psu.sweng500.team8.io;

import java.io.IOException;

/** Interface for objects that are serializable in binary format
 */
public interface BinarySerializable {
	/** Save the object to the stream
	 * @param type Type of serialization. If this is not needed for the object, just ignore it
	 * @param stream Binary stream to write to
	 * @throws IOException 
	 */
	public void save(BinaryOutputStream stream) throws IOException;
	
	/** Initialize the object from the stream
	 * * @param type Type of serialization. If this is not needed for the object, just ignore it
	 * @param stream Binary stream to read from
	 * @throws IOException 
	 */
	public void load(BinaryInputStream stream) throws IOException;
}
