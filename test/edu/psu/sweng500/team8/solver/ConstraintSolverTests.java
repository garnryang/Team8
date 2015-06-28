package edu.psu.sweng500.team8.solver;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.psu.sweng500.team8.coreDataStructures.Board;
import edu.psu.sweng500.team8.coreDataStructures.CellGrid;
import edu.psu.sweng500.team8.coreDataStructures.Puzzle;
import edu.psu.sweng500.team8.coreDataStructures.TestPuzzles;

public class ConstraintSolverTests {
	@Test
	public void canSolveEasyPuzzle() {
		Puzzle testPuzzle = TestPuzzles.getEasyPuzzle();
		CellGrid expectedSolution = testPuzzle.getSolution();

		Board boardToSolve = new Board();
		boardToSolve.Initialize(testPuzzle);
		Solver solver = SolverFactory.getSolverThatTriesConstraintsFirst();

		CellGrid foundSolution = solver.findUniqueSolutionOrNull(boardToSolve);

		//Verify it found a solution
		assertNotNull(foundSolution);
		//Check it
		assertTrue(expectedSolution.valuesAreEqual(foundSolution));
	}

	@Test
	public void canSolveMediumPuzzle() {
		Puzzle testPuzzle = TestPuzzles.getMediumPuzzle();
		CellGrid expectedSolution = testPuzzle.getSolution();

		Board boardToSolve = new Board();
		boardToSolve.Initialize(testPuzzle);
		Solver solver = SolverFactory.getSolverThatTriesConstraintsFirst();

		CellGrid foundSolution = solver.findUniqueSolutionOrNull(boardToSolve);

		//Verify it found a solution
		assertNotNull(foundSolution);

		//Check it
		assertTrue(expectedSolution.valuesAreEqual(foundSolution));
	}

	@Test
	public void canSolveHardPuzzle() {
		Puzzle testPuzzle = TestPuzzles.getHardPuzzle();
		CellGrid expectedSolution = testPuzzle.getSolution();

		Board boardToSolve = new Board();
		boardToSolve.Initialize(testPuzzle);
		Solver solver = SolverFactory.getSolverThatTriesConstraintsFirst();

		CellGrid foundSolution = solver.findUniqueSolutionOrNull(boardToSolve);

		//Verify it found only one solution
		assertNotNull(foundSolution);
		//Check it
		assertTrue(expectedSolution.valuesAreEqual(foundSolution));
	}
	
	@Test
	public void returnsNullIfThereIsMoreThanOneSolution() {
		Puzzle testPuzzle = TestPuzzles.getPuzzleWithMultipleSolutions();

		Board boardToSolve = new Board();
		boardToSolve.Initialize(testPuzzle);
		Solver solver = SolverFactory.getSolverThatTriesConstraintsFirst();

		CellGrid foundSolution = solver.findUniqueSolutionOrNull(boardToSolve);
		assertNull(foundSolution);
	}
}
