package edu.psu.sweng500.team8.play;

import java.util.Set;

import edu.psu.sweng500.team8.coreDataStructures.Cell;

/** Listener interface for cell number changes */
public interface CellChangedListener {
	/** Cell number changed
	 * @param cell Cell that changed
	 * @param newNumber New number of the cell
	 */
	void cellChanged(Cell cell, int newNumber);
	
	/** Cell pencil mark changed
	 * @param cell
	 * @param newPencilMarks New pencil marks of the cell
	 */
	void pencilMarksChanged(Cell cell, Set<Integer> newPencilMarks);
}
