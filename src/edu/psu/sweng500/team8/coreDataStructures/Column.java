package edu.psu.sweng500.team8.coreDataStructures;

/** Represents a vertical column in the 9x9 Sudoku Grid
 * The column index and sub-indices are used to determine the actual cells in the 9x9 grid
 */
public class Column extends Constraint {
	private static final long serialVersionUID = 1L;
	private CellGrid grid;
	private int columnIndex;
	
	public Column(CellGrid grid, int columnIndex) {
		this.grid = grid;
		this.columnIndex = columnIndex;
	}
	
	protected Cell getCell(int index) {
		return this.grid.getCell(index, this.columnIndex);
	}
}
