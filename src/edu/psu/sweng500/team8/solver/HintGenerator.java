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
				new HintInfo(filledCell, "This cell's value must be " + filledCell.getNumber() 
						+ " because all the other numbers have already been used in the same row, column, and/or block") : 
				null;
	}
	
	private static HintInfo tryToFillACellWithOnlyOneValidLocationForTheNumber(Board board) {
		Cell filledCell = ConstraintSolver.tryToFillACellWithOnlyOneValidLocationForTheNumber(board);
		
		return (filledCell != null) ? 
				new HintInfo(filledCell, "This cell's value must be " + filledCell.getNumber() + 
						" because it is the only cell in the row, column, and/or block where this number can go "
						+ "without violating Sudoku constraints") :
				null;
	}
}
