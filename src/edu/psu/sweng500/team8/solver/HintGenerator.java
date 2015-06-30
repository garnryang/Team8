package edu.psu.sweng500.team8.solver;

import java.util.Set;

import edu.psu.sweng500.team8.coreDataStructures.Board;
import edu.psu.sweng500.team8.coreDataStructures.Cell;

public final class HintGenerator {
	private HintGenerator() {

	} 

	public static HintInfo getHint(Board board) {
		return tryToFillACellWithOnlyOneAvailableNumber(board);
	}

	private static HintInfo tryToFillACellWithOnlyOneAvailableNumber(Board board) {
		//Check for cells where there is only one available number
		Cell mostConstrainedCell = CommonSolverMethods.getMostConstrainedCell(board);

		Set<Integer> availableNumbers = board.getCellConstraints(mostConstrainedCell).getAvailableNumbers();
		if (availableNumbers.size() > 1)
			return null;

		//Sweet, there's only 1 available number, so the number is obvious
		int number = availableNumbers.toArray(new Integer[0])[0];
		mostConstrainedCell.setNumber(number);

		return new HintInfo(mostConstrainedCell, "This cell's value must be " + number + " because it is the only number that can fit in this cell");
	}
}
