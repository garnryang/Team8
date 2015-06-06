package CoreDataStructures;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Represents a Sudoku constraint where the numbers 1-9 are permitted only once
 * Base class for row, column, and block
 */
public abstract class Constraint {

	public final Set<Integer> getUsedNumbers() {
		Set<Integer> usedNumbers = new HashSet<Integer>();
		
		// Add the used numbers 
		for (int i = 0; i < 9; i++) {
			Cell currentCell = getCell(i);
			if (currentCell.hasNumber())
				usedNumbers.add(currentCell.getNumber());
		}
		
		return usedNumbers;
	}

	public final Set<Integer> getAvailableNumbers() {
		//Remove the used numbers from the available numbers
		Set<Integer> availableNumbers = Helpers.getValidNumberSet();
		availableNumbers.removeAll(getUsedNumbers());
		return availableNumbers;
	}
	
	public final Integer[] getAvailableNumbersAsArray() {
		//Remove the used numbers from the available numbers
		Set<Integer> availableNumbers = getAvailableNumbers();
		return availableNumbers.toArray(new Integer[0]);
	}
	
	public final List<Cell> getCells() {
		List<Cell> cells = new ArrayList<Cell>(9);
		
		for (int i = 0; i < 9; i++) {
			cells.add(getCell(i));
		}
		
		return cells;
	}
	
	public final List<Cell> getOpenCells() {
		List<Cell> cells = getCells();
		
		//Use iterator loop so we can safely remove while iterating
		for (Iterator<Cell> iterator = cells.iterator(); iterator.hasNext();) {
		    Cell cell = iterator.next();
		    if (cell.hasNumber())
		        iterator.remove();
		}
		
		return cells;
	}

	protected abstract Cell getCell(int index);
}
