package edu.psu.sweng500.team8.coreDataStructures;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import edu.psu.sweng500.team8.solver.TestPuzzles;


public class PencilMarkManagerTests {
	@Test //For UC5 - Add notes to square
	public void canAddPencilMarksToCell() {
		//Initialize the board with a puzzle
		Puzzle testPuzzle = TestPuzzles.getEasyPuzzle();
		Board testBoard = new Board();
		testBoard.Initialize(testPuzzle);
		PencilMarkManager pencilMarkManager = new PencilMarkManager(testBoard);
		
		//Pick a cell to add pencil marks
		Cell testCell = testBoard.getOpenCells().get(0);
		Set<Integer> availableNumbers = testBoard.getCellConstraints(testCell).getAvailableNumbers();
		
		for (Integer number : availableNumbers) {
			pencilMarkManager.addPencilMark(number, testCell);
			assertTrue(testCell.getPencilMarks().contains(number));
		}
	}
	
	@Test //For UC5 - Add notes to square
	public void canRemovePencilMarksFromCell() {
		//Initialize the board with a puzzle
		Puzzle testPuzzle = TestPuzzles.getEasyPuzzle();
		Board testBoard = new Board();
		testBoard.Initialize(testPuzzle);
		PencilMarkManager pencilMarkManager = new PencilMarkManager(testBoard);
		
		//Pick a cell to add pencil marks
		Cell testCell = testBoard.getOpenCells().get(0);
		Set<Integer> availableNumbers = testBoard.getCellConstraints(testCell).getAvailableNumbers();
		
		for (Integer number : availableNumbers) {
			pencilMarkManager.addPencilMark(number, testCell);
			
			//Now remove it
			pencilMarkManager.removePencilMark(number, testCell);
			assertFalse(testCell.getPencilMarks().contains(number));
		}
	}
}
