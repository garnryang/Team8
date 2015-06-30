package edu.psu.sweng500.team8.coreDataStructures;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import edu.psu.sweng500.team8.io.BinaryInputStream;
import edu.psu.sweng500.team8.io.BinaryOutputStream;
import edu.psu.sweng500.team8.io.BinarySerializable;


/** Represents a cell (i.e. square) within the 9x9 Sudoku board
 * Contains the cell's type and current value
 */
public class Cell implements BinarySerializable {
	public enum ValueType {
		Given,
		UserDefined
	}
	
	private CellCoordinates m_coordinates;
	private int m_currentValue = 0;
	private ValueType m_type = ValueType.UserDefined;
	private Set<Integer> m_pencilMarks = new HashSet<Integer>();
	
	public Cell() {
		this(0,0); //Some default coordinates
	}
	
	public Cell(int rowIndex, int columnIndex) {
		m_coordinates = new CellCoordinates(rowIndex, columnIndex);
	}
	
	public Cell(Cell cellToCopy) {
		m_coordinates = cellToCopy.m_coordinates;
		m_currentValue = cellToCopy.m_currentValue;
		m_type = cellToCopy.m_type;
		m_pencilMarks = cellToCopy.m_pencilMarks;
	}
	
	public CellCoordinates getCoordinates() {
		return m_coordinates;
	}
	
	public int getNumber() { 
		return m_currentValue;
	}
	
	public void setNumber(int value) throws IllegalArgumentException {
		if (value < 1 || value > 9)
			throw new IllegalArgumentException("Value must be a digit from 1 to 9. To clear, use the clearNumber function.");
		
		m_currentValue = value;
	}
	
	public ValueType getType() {
		return m_type;
	}
	
	public void setType(ValueType type) {
		m_type = type;
	}
	
	public boolean hasNumber() {
		return m_currentValue != 0;
	}
	
	public void clearNumber() {
		m_currentValue = 0;
	}
	
	public Set<Integer> getPencilMarks() {
		return m_pencilMarks;
	}

	/** Note: assumes row/column indices have been initialized */
	@Override
	public void save(BinaryOutputStream stream) throws IOException {
		/*To save disk space, instead of writing the value type, 
			save given numbers as negative. Can change this later if needed. */
		int number = (m_type == ValueType.UserDefined) ? m_currentValue : -m_currentValue;
		stream.writeInt(number); 
		
		//TODO: Pencil marks
	}

	@Override
	public void load(BinaryInputStream stream) throws IOException {
		int number = stream.readInt();
		
		/*To save disk space, instead of writing the value type, 
		given numbers are saved as negative values. Can change this later if needed. */
		if (number > 0) {
			setNumber(number);
			m_type = ValueType.UserDefined;
		}
		else if (number < 0){
			setNumber(-number);
			m_type = ValueType.Given;
		}
		
		//TODO: Pencil marks
	}
}