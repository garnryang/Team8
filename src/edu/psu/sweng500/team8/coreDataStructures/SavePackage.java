package edu.psu.sweng500.team8.coreDataStructures;

import java.io.Serializable;

public class SavePackage implements Serializable {

	private CellGrid cellGrid;
	private Puzzle puzzle;

	public CellGrid getCellGrid() {
		return cellGrid;
	}

	public void setCellGrid(CellGrid cellGrid) {
		this.cellGrid = cellGrid;
	}

	public Puzzle getPuzzle() {
		return puzzle;
	}

	public void setPuzzle(Puzzle puzzle) {
		this.puzzle = puzzle;
	}
}
