package edu.psu.sweng500.team8.play;

import edu.psu.sweng500.team8.coreDataStructures.Cell;

/** Listener interface for cell number changes */
public interface CellChangedListener {
	/** Cell number changed
	 * @param cell Cell that changed
	 * @param newNumber New number of the cell
	 */
	void cellChanged(Cell cell, int newNumber);
}
