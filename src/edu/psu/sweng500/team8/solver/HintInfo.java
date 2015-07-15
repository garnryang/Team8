package edu.psu.sweng500.team8.solver;

import edu.psu.sweng500.team8.coreDataStructures.Cell;

public class HintInfo {
	private Cell cellToFill;
	private int number;
	private String explanation;
	
	public HintInfo(Cell cellToFill, int number, String explanation) {
		this.cellToFill = cellToFill;
		this.number = number;
		this.explanation = explanation;
	}
	
	public Cell getCell() {
		return this.cellToFill;
	}
	
	public int getNumber() {
		return this.number;
	}
	
	public String getExplanation() {
		return this.explanation;
	}
}
