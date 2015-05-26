package CoreDataStructures;


/** Represents a cell (i.e. square) within the 9x9 Sudoku board
 * Contains the cell's type and current value
 */
public class Cell {
	public enum ValueType {
		Given,
		UserDefined
	}
	
	private int m_currentValue = 0;
	private ValueType m_type = ValueType.UserDefined;
	
	public Cell() {
	}
	
	public Cell(Cell cellToCopy) {
		m_currentValue = cellToCopy.m_currentValue;
		m_type = cellToCopy.m_type;
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
}
