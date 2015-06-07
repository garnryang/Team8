package edu.psu.sweng500.team8.coreDataStructures;

/** Represents a vertical column in the 9x9 Sudoku Grid
 * The block index and sub-indices are used to determine the actual cells in the 9x9 grid
 */
public class Column extends Constraint {
	private CellGrid m_grid;
	private int m_columnIndex;
	
	public Column(CellGrid grid, int columnIndex) {
		m_grid = grid;
		m_columnIndex = columnIndex;
	}
	
	protected Cell getCell(int index) {
		return m_grid.getCell(index, m_columnIndex);
	}
}
