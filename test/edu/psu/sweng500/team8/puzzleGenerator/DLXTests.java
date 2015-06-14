package edu.psu.sweng500.team8.puzzleGenerator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.psu.sweng500.team8.coreDataStructures.Board;
import edu.psu.sweng500.team8.coreDataStructures.CellGrid;
import edu.psu.sweng500.team8.coreDataStructures.Puzzle;
import edu.psu.sweng500.team8.solver.TestPuzzles;

public class DLXTests {
	@Test
	public void canSolveEasyPuzzle() {
		Puzzle testPuzzle = TestPuzzles.getEasyPuzzle();
		CellGrid expectedSolution = testPuzzle.getSolution();

		Board boardToSolve = new Board();
		boardToSolve.Initialize(testPuzzle);
		DLX solver = new DLX(boardToSolve.getCellGrid());
		
		int numSolutions = solver.Solve();
		
		//Verify it found only one solution
		assertEquals(1, numSolutions);
		
		//TODO: Verify the solution it found
	}

	@Test
	public void canSolveMediumPuzzle() {
		Puzzle testPuzzle = TestPuzzles.getMediumPuzzle();
		CellGrid expectedSolution = testPuzzle.getSolution();

		Board boardToSolve = new Board();
		boardToSolve.Initialize(testPuzzle);
		DLX solver = new DLX(boardToSolve.getCellGrid());
		
		int numSolutions = solver.Solve();
		
		//Verify it found only one solution
		assertEquals(1, numSolutions);
	}

	@Test
	public void canSolveHardPuzzle() {
		Puzzle testPuzzle = TestPuzzles.getHardPuzzle();
		CellGrid expectedSolution = testPuzzle.getSolution();

		Board boardToSolve = new Board();
		boardToSolve.Initialize(testPuzzle);
		DLX solver = new DLX(boardToSolve.getCellGrid());
		
		int numSolutions = solver.Solve();
		
		//Verify it found only one solution
		assertEquals(1, numSolutions);
	}

	@Test
	public void canFindAllSolutions() {
		Puzzle testPuzzle = TestPuzzles.getPuzzleWithMultipleSolutions();

		Board boardToSolve = new Board();
		boardToSolve.Initialize(testPuzzle);
		DLX solver = new DLX(boardToSolve.getCellGrid());
		
		int numSolutions = solver.Solve();
		
		//Verify it found only one solution
		assertEquals(8, numSolutions);
	}
}
