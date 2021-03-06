package edu.psu.sweng500.team8.coreDataStructures;

/** Represents a 3x3 Block (i.e. sub-board) of the 9x9 Sudoku Grid
 * Blocks in the 9x9 grid, as well as within the block itself are indexed as:
 * 0 1 2
 * 3 4 5
 * 6 7 8
 * The block index and sub-indices are used to determine the actual cells in the 9x9 grid
 */
public class Block extends Constraint {
	private static final long serialVersionUID = 1L;
	private CellGrid grid;
	private int blockIndex;
	
	public Block(CellGrid grid, int blockIndex) {
		this.grid = grid;
		this.blockIndex = blockIndex;
	}
	
	public Cell getCell(int index) {
		//Base row index is the (block index / 3) (truncated) * 3
		//Row within the block is index / 3 (truncated)
		int rowIndex = (this.blockIndex / 3) * 3 + index / 3;
		
		//Base column index is the (block index % 3) * 3
		//Column within the block is index % 3 
		int columnIndex = (this.blockIndex % 3) * 3 + index % 3;
		return this.grid.getCell(rowIndex, columnIndex);
	}
}
