package CoreDataStructures;

import java.util.HashSet;
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

	protected abstract Cell getCell(int index);
}
