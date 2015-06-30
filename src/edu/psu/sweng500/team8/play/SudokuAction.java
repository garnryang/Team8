package edu.psu.sweng500.team8.play;

import java.awt.Color;

import edu.psu.sweng500.team8.coreDataStructures.CellCoordinates;

/**
 * 
 * This is a Proof of Concept while writing some JUnit test cases
 * This represents an action of adding/removing/updating a number on a cell
 * where previousValue is existing number (0 representing empty cell)
 * and newValue is the value being entered (0 representing deleting existing number)
 *
 */
public class SudokuAction {
	private CellCoordinates cellCordinates;
	private int previousValue;
	private int newValue;

	public SudokuAction(CellCoordinates cellCoordinates,
			int previousValue, int newVale) {
		this.cellCordinates = cellCoordinates;
		this.previousValue = previousValue;
		this.newValue = newVale;
	}
	
	public CellCoordinates getCellCordinates() {
		return cellCordinates;
	}

	public void setCellCordinates(CellCoordinates cellCordinates) {
		this.cellCordinates = cellCordinates;
	}

	public int getPreviousValue() {
		return previousValue;
	}
	
	public void setPreviousValue(int previousValue) {
		this.previousValue = previousValue;
	}

	public int getNewValue() {
		return newValue;
	}

	public void setNewValue(int newValue) {
		this.newValue = newValue;
	}
	
	}
