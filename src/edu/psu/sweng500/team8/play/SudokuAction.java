package edu.psu.sweng500.team8.play;

import edu.psu.sweng500.team8.coreDataStructures.CellGrid;

/**
 * 
 * Since we are keeping track of both values and pencil marks on Cell object,
 * it makes more sense to simply keep track of a snapshot of CellGrid per action
 * This approach might not be suitable for a large scale objects as the memory usage could be intense
 * but for the purpose of this application, this makes the implementation much simpler
 * 
 *  
 */
public class SudokuAction {
	/* cellGrid is a snapshot of the board when the action is carried */
	private CellGrid cellGrid;
	
	public SudokuAction(CellGrid currentCellGrid) {
		this.cellGrid = currentCellGrid;
	}
	public CellGrid getCellGrid() {
		return cellGrid;
	}

	public void setCellGrid(CellGrid cellGrid) {
		this.cellGrid = cellGrid;
	}
}
