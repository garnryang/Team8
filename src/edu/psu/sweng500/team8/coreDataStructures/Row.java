package edu.psu.sweng500.team8.coreDataStructures;

/** Represents a horizontal row in the 9x9 Sudoku Grid
 * The block index and sub-indices are used to determine the actual cells in the 9x9 grid
 */
public class Row extends Constraint {
	private CellGrid m_grid;
	private int m_rowIndex;
	
	public Row(CellGrid grid, int rowIndex) {
		m_grid = grid;
		m_rowIndex = rowIndex;
	}
	
	protected Cell getCell(int index) {
		return m_grid.getCell(m_rowIndex, index);
	}
}
