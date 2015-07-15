package edu.psu.sweng500.team8.solver;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.psu.sweng500.team8.coreDataStructures.Board;
import edu.psu.sweng500.team8.coreDataStructures.Cell;
import edu.psu.sweng500.team8.coreDataStructures.Puzzle;
import edu.psu.sweng500.team8.coreDataStructures.Puzzle.DifficultyLevel;

public class HintGeneratorTests {
	@Test
	public void cangetHintWhenACellHasOnlyOneAvailableNumber() {
		Puzzle testPuzzle = getPuzzleWithCellWithOnlyOneAvailableNumber();
		Board testBoard = new Board();
		testBoard.Initialize(testPuzzle);
		
		HintInfo hint = HintGenerator.getHint(testBoard);
		
		assertTrue(hint != null);
		Cell filledCell = hint.getCell();
		assertEquals(0, filledCell.getCoordinates().getRowIndex());
		assertEquals(6, filledCell.getCoordinates().getColumnIndex());
		assertEquals(3, hint.getNumber());
		
		//This can change. Made something up for now.
		String expectedExplanation = "This cell's value must be 3 because "+
			"all the other numbers have already been used in the same row, column, and/or block.";
		assertEquals(expectedExplanation, hint.getExplanation());
	}
	
	@Test
	public void cangetHintWhenThereIsOnlyOneCellWhereANumberCanFit() {
		Puzzle testPuzzle = getPuzzleWhereANumberCanOnlyFitInOneCell();
		
		Board testBoard = new Board();
		testBoard.Initialize(testPuzzle);
		
		SolverFactory.getSolverThatTriesConstraintsFirst().findUniqueSolutionOrNull(testBoard);
		
		HintInfo hint = HintGenerator.getHint(testBoard);
		
		assertTrue(hint != null);
		Cell filledCell = hint.getCell();
		assertEquals(4, filledCell.getCoordinates().getRowIndex());
		assertEquals(2, filledCell.getCoordinates().getColumnIndex());
		assertEquals(4, hint.getNumber());
		
		//This can change. Made something up for now.
		String expectedExplanation = "This cell's value must be 4 because " +
				"it is the only cell in the row, column, and/or block where this number can go without violating Sudoku constraints.";
		assertEquals(expectedExplanation, hint.getExplanation());
	}
	
	/** 
	 * (0, 6) is the open cell with only one available number: 3
	 */
	private static Puzzle getPuzzleWithCellWithOnlyOneAvailableNumber() {
		//Got this one from my iphone Sudoku app...
		Puzzle puzzle = new Puzzle();
		puzzle.setDifficulty(DifficultyLevel.Medium);

		puzzle.setCellNumber(0, 2, 8);
		puzzle.setCellNumber(0, 7, 5);

		puzzle.setCellNumber(1, 0, 6);
		
		puzzle.setCellNumber(1, 2, 3);
		puzzle.setCellNumber(1, 3, 8);
		puzzle.setCellNumber(1, 4, 5);
		puzzle.setCellNumber(1, 5, 2);
		puzzle.setCellNumber(1, 6, 1);
		puzzle.setCellNumber(1, 7, 9);
		puzzle.setCellNumber(1, 8, 7);

		puzzle.setCellNumber(2, 0, 1);
		puzzle.setCellNumber(2, 3, 7);
		puzzle.setCellNumber(2, 4, 4);
		puzzle.setCellNumber(2, 8, 2);

		puzzle.setCellNumber(3, 3, 8);
		puzzle.setCellNumber(3, 8, 3);

		puzzle.setCellNumber(4, 2, 4);

		puzzle.setCellNumber(5, 1, 7);
		puzzle.setCellNumber(5, 6, 4);
		puzzle.setCellNumber(5, 8, 5);

		puzzle.setCellNumber(6, 0, 3);
		puzzle.setCellNumber(6, 3, 9);
		puzzle.setCellNumber(6, 8, 8);

		puzzle.setCellNumber(7, 0, 5);
		puzzle.setCellNumber(7, 4, 3);
		puzzle.setCellNumber(7, 6, 6);
		puzzle.setCellNumber(7, 7, 2);
		puzzle.setCellNumber(7, 8, 1);

		puzzle.setCellNumber(8, 1, 6);
		puzzle.setCellNumber(8, 4, 5);
		puzzle.setCellNumber(8, 5, 8);	

		return puzzle;
	}
	
	/** 
	 * (4, 2) is the only place a 4 can fit
	 */
	private static Puzzle getPuzzleWhereANumberCanOnlyFitInOneCell() {
		//Started from: http://www.conceptispuzzles.com/index.aspx?uri=puzzle/sudoku/techniques
		//Used modified solver to find all cells with only one available number first, leaving this
		Puzzle puzzle = new Puzzle();
		puzzle.setDifficulty(DifficultyLevel.Medium);

		puzzle.setCellNumber(0, 2, 3);
		puzzle.setCellNumber(0, 6, 1);

		puzzle.setCellNumber(1, 1, 5);
		puzzle.setCellNumber(1, 7, 6);

		puzzle.setCellNumber(2, 0, 4);
		puzzle.setCellNumber(2, 3, 5);
		puzzle.setCellNumber(2, 5, 3);
		puzzle.setCellNumber(2, 8, 9);
		
		puzzle.setCellNumber(3, 2, 9);
		puzzle.setCellNumber(3, 3, 8);
		puzzle.setCellNumber(3, 4, 3);
		puzzle.setCellNumber(3, 6, 2);

		puzzle.setCellNumber(4, 3, 1);
		puzzle.setCellNumber(4, 5, 6);
		
		puzzle.setCellNumber(5, 0, 5);
		puzzle.setCellNumber(5, 1, 6);
		puzzle.setCellNumber(5, 2, 8);
		puzzle.setCellNumber(5, 3, 4);
		puzzle.setCellNumber(5, 4, 2);
		puzzle.setCellNumber(5, 5, 9);
		puzzle.setCellNumber(5, 6, 3);
		puzzle.setCellNumber(5, 7, 7);
		puzzle.setCellNumber(5, 8, 1);
		
		puzzle.setCellNumber(6, 0, 3);
		puzzle.setCellNumber(6, 3, 7);
		puzzle.setCellNumber(6, 5, 4);
		puzzle.setCellNumber(6, 8, 5);
		
		puzzle.setCellNumber(7, 1, 4);
		puzzle.setCellNumber(7, 7, 1);

		puzzle.setCellNumber(8, 2, 1);
		puzzle.setCellNumber(8, 6, 9);

		return puzzle;
	}
}
