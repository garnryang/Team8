package CoreDataStructures;

public class CellCoordinates {
	private int m_rowIndex;
	private int m_columnIndex;
	
	public CellCoordinates(int rowIndex, int columnIndex) {
		m_rowIndex = rowIndex;
		m_columnIndex = columnIndex;
	}
	
	public int getRowIndex() {
		return m_rowIndex;
	}
	
	public int getColumnIndex() {
		return m_columnIndex;
	}
	
	public int getBlockIndex() {
		return (m_rowIndex / 3) * 3 + m_columnIndex / 3;
	}
}