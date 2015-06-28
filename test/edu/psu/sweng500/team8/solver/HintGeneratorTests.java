package edu.psu.sweng500.team8.solver;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.psu.sweng500.team8.coreDataStructures.Board;
import edu.psu.sweng500.team8.coreDataStructures.Cell;
import edu.psu.sweng500.team8.coreDataStructures.Puzzle;
import edu.psu.sweng500.team8.coreDataStructures.Puzzle.DifficultyLevel;

public class HintGeneratorTests {
	@Test
	public void canGetHintWhenACellHasOnlyOneAvailableNumber() {
		Puzzle testPuzzle = getPuzzleWithCellWithOnlyOneAvailableNumber();
		Board testBoard = new Board();
		testBoard.Initialize(testPuzzle);
		
		HintInfo hint = HintGenerator.getHint(testBoard);
		
		assertTrue(hint != null);
		Cell filledCell = hint.GetCell();
		assertEquals(0, filledCell.getCoordinates().getRowIndex());
		assertEquals(6, filledCell.getCoordinates().getColumnIndex());
		assertEquals(3, filledCell.getNumber());
		
		//This can change. Made something up for now.
		String expectedExplanation = "This cell's value must be 3 because it is the only number that can fit in this cell";
		assertEquals(expectedExplanation, hint.GetExplanation());
	}
	
	@Test
	public void canGetHintWhenThereIsOnlyOneCellWhereANumberCanFit() {
		Puzzle testPuzzle = getPuzzleWhereANumberCanOnlyFitInOneCell();
		Board testBoard = new Board();
		testBoard.Initialize(testPuzzle);
		
		HintInfo hint = HintGenerator.getHint(testBoard);
		
		assertTrue(hint != null);
		Cell filledCell = hint.GetCell();
		assertEquals(0, filledCell.getCoordinates().getRowIndex());
		assertEquals(0, filledCell.getCoordinates().getColumnIndex());
		assertEquals(7, filledCell.getNumber());
		
		//This can change. Made something up for now.
		String expectedExplanation = "This cell's value must be 7 because it is the only cell where this number can fit";
		assertEquals(expectedExplanation, hint.GetExplanation());
	}
	
	/** 
	 * (1, 1) is the open cell with only one available number: 4
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
	 * (0, 0) is the only place a 7 can fit
	 */
	private static Puzzle getPuzzleWhereANumberCanOnlyFitInOneCell() {
		//Got this one from my iphone Sudoku app...
		//Same puzzle as above with the 4 filled in
		Puzzle puzzle = new Puzzle();
		puzzle.setDifficulty(DifficultyLevel.Medium);

		puzzle.setCellNumber(0, 2, 8);
		puzzle.setCellNumber(0, 7, 5);

		puzzle.setCellNumber(1, 0, 6);
		puzzle.setCellNumber(1, 1, 4);
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
}
