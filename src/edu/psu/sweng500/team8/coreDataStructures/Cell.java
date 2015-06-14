package edu.psu.sweng500.team8.coreDataStructures;

import java.util.HashSet;
import java.util.Set;


/** Represents a cell (i.e. square) within the 9x9 Sudoku board
 * Contains the cell's type and current value
 */
public class Cell {
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
}
