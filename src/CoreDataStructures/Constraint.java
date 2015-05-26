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

		// Remove the used numbers from the available numbers
		for (int i = 0; i < 9; i++) {
			Cell currentCell = getCell(i);
			if (currentCell.hasNumber())
				usedNumbers.add(currentCell.getNumber());
		}

		return usedNumbers;
	}

	public final Set<Integer> getAvailableNumbers() {
		Set<Integer> availableNumbers = Helpers.getValidNumberSet();

		// Remove the used numbers from the available numbers
		for (int i = 0; i < 9; i++) {
			Cell currentCell = getCell(i);
			if (currentCell.hasNumber())
				availableNumbers.remove(currentCell.getNumber());
		}

		return availableNumbers;
	}

	protected abstract Cell getCell(int index);
}
