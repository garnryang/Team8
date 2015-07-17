package edu.psu.sweng500.team8.coreDataStructures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

import edu.psu.sweng500.team8.coreDataStructures.TestPuzzles;

public class BoardTests {
	@Test //For UC4 steps 2-3
	public void puzzleIsSolvedReturnsTrueWhenBoardMatchesSolution() {
		Puzzle puzzle = TestPuzzles.getMediumPuzzle();
		Board testBoard = new Board();
		testBoard.initialize(puzzle, null);
		
		fillInTheSolution(testBoard);
		
		assertTrue(testBoard.puzzleIsSolved());
	}
	
	@Test //For UC4 Extension 2a
	public void puzzleIsSolvedReturnsFalseWhenBoardIsIncorrect() {
		Puzzle puzzle = TestPuzzles.getMediumPuzzle();
		Board testBoard = new Board();
		testBoard.initialize(puzzle, null);
		
		//Set one of the user-defined cells incorrect
		Cell openCell = testBoard.getOpenCells().get(0);
		int solutionValue = getCorrespondingCellFromSolution(testBoard, openCell).getNumber();
		int invalidNumber = (solutionValue < 9) ? solutionValue + 1 : solutionValue - 1; 
		openCell.setNumber(invalidNumber);
		
		//Fill in the rest of the solution
		fillInTheSolution(testBoard);
		
		assertFalse(testBoard.puzzleIsSolved());
	}
	
	public void canGetCellsThatAreIncorrect() {
		//UC12
		Puzzle puzzle = TestPuzzles.getEasyPuzzle();
		Board testBoard = new Board();
		testBoard.initialize(puzzle, null);
		
		//Set some user-defined cells incorrect
		Cell openCell1 = testBoard.getOpenCells().get(0);
		int solutionValue1 = getCorrespondingCellFromSolution(testBoard, openCell1).getNumber();
		int invalidNumber1 = (solutionValue1 < 9) ? solutionValue1 + 1 : solutionValue1 - 1; 
		openCell1.setNumber(invalidNumber1);

		Cell openCell2 = testBoard.getOpenCells().get(0);
		int solutionValue2 = getCorrespondingCellFromSolution(testBoard, openCell2).getNumber();
		int invalidNumber2 = (solutionValue2 < 9) ? solutionValue2 + 1 : solutionValue2 - 1; 
		openCell2.setNumber(invalidNumber2);
		
		//Get the incorrect cells. 
		Set<Cell> retrievedIncorrectCells = testBoard.getIncorrectCells();
		
		//Verify it contains the incorrect cells
		assertEquals(2, retrievedIncorrectCells.size());
		assertTrue(retrievedIncorrectCells.contains(openCell1));
		assertTrue(retrievedIncorrectCells.contains(openCell2));
	}
	
	@Test //For UC4 extension 2a1
	public void canGetCellsThatViolateConstraints() {
		Puzzle puzzle = TestPuzzles.getEasyPuzzle();
		Board testBoard = new Board();
		testBoard.initialize(puzzle, null);
		
		//Set one of the user-defined cells incorrect
		Cell openCell = testBoard.getOpenCells().get(0);
		int solutionValue = getCorrespondingCellFromSolution(testBoard, openCell).getNumber();
		int invalidNumber = (solutionValue < 9) ? solutionValue + 1 : solutionValue - 1; 
		openCell.setNumber(invalidNumber);
		
		//Fill in the rest of the solution
		fillInTheSolution(testBoard);
		
		//Get the cells with duplicate numbers. 
		//Should only be cells within the same row/column/block as the cell we changed
		Set<Cell> expectedViolatingCells = getCellsInSameConstraintsWithSameNumber(testBoard, openCell);
		Set<Cell> retrievedViolatingCells = testBoard.getCellsViolatingConstraints();
		
		//Verify the sets are equal
		assertEquals(expectedViolatingCells.size(), retrievedViolatingCells.size());
		assertTrue(retrievedViolatingCells.containsAll(expectedViolatingCells));
	}
	
	@Test
	public void initializeDoesNotReplaceTheCellGrid() {
		//Test for a bug that was discovered...
		//Initialize replaced the cell grid, which invalidated the rows/columns/blocks
		Puzzle puzzle = TestPuzzles.getMediumPuzzle();
		Board testBoard = new Board();
		
		CellGrid boardBeforeInitialize = testBoard.getCellGrid();
		testBoard.initialize(puzzle, null);
		CellGrid boardAfterInitialize = testBoard.getCellGrid();
		
		assertEquals(boardBeforeInitialize, boardAfterInitialize);
	}
	
	private static void fillInTheSolution(Board board) {
		//Fill in all the open cells with numbers from the solution
		CellGrid solution = board.getCurrentPuzzle().getSolution();
		for (Cell openCell : board.getOpenCells()) {
			int row = openCell.getCoordinates().getRowIndex();
			int column = openCell.getCoordinates().getColumnIndex();
			
			Cell solutionCell = solution.getCell(row, column);
			openCell.setNumber(solutionCell.getNumber());
		}
	}
	
	private static Cell getCorrespondingCellFromSolution(Board board, Cell cell) {
		CellGrid solution = board.getCurrentPuzzle().getSolution();
		int rowIndex = cell.getCoordinates().getRowIndex();
		int columnIndex = cell.getCoordinates().getColumnIndex();
		
		return solution.getCell(rowIndex, columnIndex);
	}
	
	private static Set<Cell> getCellsInSameConstraintsWithSameNumber(Board board, Cell cell) {
		CellConstraints constraints = board.getCellConstraints(cell);
		
		//Iterate the cell's constraints and get cells with the same number
		Set<Cell> duplicateCellSet = new HashSet<Cell>();
		Iterator<Constraint> iterator = constraints.getIterator();
		while (iterator.hasNext()) {
			Constraint currentConstraint = iterator.next();
			
			for (Cell constraintCell : currentConstraint.getCells()) {
				if (constraintCell != cell && constraintCell.getNumber() == cell.getNumber())
					duplicateCellSet.add(constraintCell);
			}
		}
		
		//If there are any duplicates, count the cell itself as a duplicate
		if (duplicateCellSet.size() > 0) 
			duplicateCellSet.add(cell);
		
		return duplicateCellSet;
	}
}
