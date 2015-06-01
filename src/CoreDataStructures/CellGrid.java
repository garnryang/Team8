package CoreDataStructures;

/** Raw representation of a 9x9 sudoku board
 * Intended only as a data structure without complex logic
 */
public class CellGrid {
	private Cell[][] m_cells = new Cell[9][9];
	
	public CellGrid() {
		//Initialize the cells
		for (int rowIndex = 0;  rowIndex < 9; rowIndex++) {
			for (int columnIndex = 0; columnIndex < 9; columnIndex++) {
				m_cells[rowIndex][columnIndex] = new Cell();
			}
		}
	}
	
	public CellGrid(CellGrid gridToCopy) {
		
		//Clone all of the cells
		for (int rowIndex = 0; rowIndex < 9; rowIndex++) {
			for (int columnIndex = 0; columnIndex < 9; columnIndex++) {
				m_cells[rowIndex][columnIndex] = new Cell(gridToCopy.getCell(rowIndex,columnIndex));
			}
		}
	}
	
	public Cell getCell(int row, int column) {
		return m_cells[row][column];
	}
	
	public boolean valuesAreEqual(CellGrid gridToCompare) {
		//Compare the cell values
		for (int rowIndex = 0; rowIndex < 9; rowIndex++) {
			for (int columnIndex = 0; columnIndex < 9; columnIndex++) {
				Cell myCell = m_cells[rowIndex][columnIndex];
				Cell otherCell = gridToCompare.getCell(rowIndex, columnIndex);
				if (myCell.getNumber() != otherCell.getNumber())
					return false;
			}
		}
		return true;
	}
}
