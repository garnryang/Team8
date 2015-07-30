package edu.psu.sweng500.team8.coreDataStructures;

/** Represents a horizontal row in the 9x9 Sudoku Grid
 * The row index and sub-indices are used to determine the actual cells in the 9x9 grid
 */
public class Row extends Constraint {
	private static final long serialVersionUID = 1L;
	private CellGrid grid;
	private int rowIndex;
	
	public Row(CellGrid grid, int rowIndex) {
		this.grid = grid;
		this.rowIndex = rowIndex;
	}
	
	protected Cell getCell(int index) {
		return this.grid.getCell(this.rowIndex, index);
	}
}
