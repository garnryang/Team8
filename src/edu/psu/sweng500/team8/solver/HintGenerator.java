package edu.psu.sweng500.team8.solver;

import edu.psu.sweng500.team8.coreDataStructures.Board;
import edu.psu.sweng500.team8.coreDataStructures.Cell;

public final class HintGenerator {
	private HintGenerator() {

	} 

	public static HintInfo getHint(Board board) {
		HintInfo hint = tryToFillACellWithOnlyOneAvailableNumber(board);
		if (hint == null)
			hint = tryToFillACellWithOnlyOneValidLocationForTheNumber(board);
		
		return hint;
	}

	private static HintInfo tryToFillACellWithOnlyOneAvailableNumber(Board board) {
		Cell filledCell = ConstraintSolver.tryToFillACellWithOnlyOneAvailableNumber(board);
		
		return (filledCell != null) ? 
				new HintInfo(filledCell, "This cell's value must be " + filledCell.getNumber() + " because it is the only number that can fit in this cell") : 
				null;
	}
	
	private static HintInfo tryToFillACellWithOnlyOneValidLocationForTheNumber(Board board) {
		Cell filledCell = ConstraintSolver.tryToFillACellWithOnlyOneValidLocationForTheNumber(board);
		
		return (filledCell != null) ? 
				new HintInfo(filledCell, "This cell's value must be " + filledCell.getNumber() + " because it is the only cell where this number can fit") :
				null;
	}
}
