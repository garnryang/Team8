package edu.psu.sweng500.team8.solver;

import java.util.Set;

import edu.psu.sweng500.team8.coreDataStructures.Board;
import edu.psu.sweng500.team8.coreDataStructures.Cell;

public final class HintGenerator {
	private HintGenerator() {

	} 

	public static HintInfo getHint(Board board) {
		HintInfo hint = checkForErrors(board);
		if (hint == null)
			hint = tryToFillACellWithOnlyOneAvailableNumber(board);
		if (hint == null)
			hint = tryToFillACellWithOnlyOneValidLocationForTheNumber(board);
		
		return hint;
	}
	
	private static HintInfo checkForErrors(Board board) {
		Set<Cell> incorrectCells = board.getIncorrectCells();
		if (incorrectCells.size() > 0) {
			Cell anIncorrectCell = incorrectCells.toArray(new Cell[0])[0];
			return new HintInfo(anIncorrectCell, 0, "The value in this cell is incorrect. Fix it before continuing.");
		}
		
		return null;
	}

	private static HintInfo tryToFillACellWithOnlyOneAvailableNumber(Board board) {
		Cell filledCell = ConstraintSolver.tryToFillACellWithOnlyOneAvailableNumber(board);

		if (filledCell == null) 
			return null;
		
		//Minor hack...Get the number then clear the cell so that we can save the board state beforehand
		int number = filledCell.getNumber();
		filledCell.clearNumber();
		return new HintInfo(filledCell, number, "This cell's value must be " + number
				+ " because all the other numbers have already been used in the same row, column, and/or block.");
	}
	
	private static HintInfo tryToFillACellWithOnlyOneValidLocationForTheNumber(Board board) {
		Cell filledCell = ConstraintSolver.tryToFillACellWithOnlyOneValidLocationForTheNumber(board);
		
		if (filledCell == null) 
			return null;
		
		//Minor hack...Get the number then clear the cell so that we can save the board state beforehand
		int number = filledCell.getNumber();
		filledCell.clearNumber();
		return new HintInfo(filledCell, number, "This cell's value must be " + number + 
				" because it is the only cell in the row, column, and/or block where this number can go "
				+ "without violating Sudoku constraints.");
	}
}
