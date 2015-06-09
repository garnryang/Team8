package edu.psu.sweng500.team8.solver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import edu.psu.sweng500.team8.coreDataStructures.Board;
import edu.psu.sweng500.team8.coreDataStructures.CellGrid;
import edu.psu.sweng500.team8.coreDataStructures.Puzzle;


public class ConstraintSolverTests {
	@Test
	public void canSolveEasyPuzzle() {
		Puzzle testPuzzle = TestPuzzles.getEasyPuzzle();
		CellGrid expectedSolution = testPuzzle.getSolution();

		Board boardToSolve = new Board();
		boardToSolve.Initialize(testPuzzle);
		ISolver solver = SolverFactory.getSolverThatTriesConstraintsFirst();

		List<CellGrid> foundSolutions = solver.findAllSolutions(boardToSolve);

		//Verify it found only one solution
		assertNotNull(foundSolutions);
		assertEquals(1, foundSolutions.size());
		//Check it
		assertTrue(expectedSolution.valuesAreEqual(foundSolutions.get(0)));
	}

	@Test
	public void canSolveMediumPuzzle() {
		Puzzle testPuzzle = TestPuzzles.getMediumPuzzle();
		CellGrid expectedSolution = testPuzzle.getSolution();

		Board boardToSolve = new Board();
		boardToSolve.Initialize(testPuzzle);
		ISolver solver = SolverFactory.getSolverThatTriesConstraintsFirst();

		List<CellGrid> foundSolutions = solver.findAllSolutions(boardToSolve);

		//Verify it found only one solution
		assertNotNull(foundSolutions);
		assertEquals(1, foundSolutions.size()); 

		//Check it
		assertTrue(expectedSolution.valuesAreEqual(foundSolutions.get(0)));
	}

	@Test
	public void canSolveHardPuzzle() {
		Puzzle testPuzzle = TestPuzzles.getHardPuzzle();
		CellGrid expectedSolution = testPuzzle.getSolution();

		Board boardToSolve = new Board();
		boardToSolve.Initialize(testPuzzle);
		ISolver solver = SolverFactory.getSolverThatTriesConstraintsFirst();

		List<CellGrid> foundSolutions = solver.findAllSolutions(boardToSolve);

		//Verify it found only one solution
		assertNotNull(foundSolutions);
		assertEquals(1, foundSolutions.size()); 
		//Check it
		assertTrue(expectedSolution.valuesAreEqual(foundSolutions.get(0)));
	}

	@Test
	public void canFindAllSolutions() {
		Puzzle testPuzzle = TestPuzzles.getPuzzleWithMultipleSolutions();

		Board boardToSolve = new Board();
		boardToSolve.Initialize(testPuzzle);
		ISolver solver = SolverFactory.getSolverThatTriesConstraintsFirst();

		List<CellGrid> foundSolutions = solver.findAllSolutions(boardToSolve);
		assertNotNull(foundSolutions);

		assertEquals(8, foundSolutions.size());
	}
	
	@Test
	public void returnsNullIfThereIsMoreThanOneSolution() {
		Puzzle testPuzzle = TestPuzzles.getPuzzleWithMultipleSolutions();

		Board boardToSolve = new Board();
		boardToSolve.Initialize(testPuzzle);
		ISolver solver = SolverFactory.getSolverThatTriesConstraintsFirst();

		CellGrid foundSolution = solver.findUniqueSolutionOrNull(boardToSolve);
		assertNull(foundSolution);
	}
}
