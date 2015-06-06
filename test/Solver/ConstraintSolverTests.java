package Solver;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import Solver.ISolver;
import Solver.SolverFactory;
import CoreDataStructures.Board;
import CoreDataStructures.CellGrid;
import CoreDataStructures.Puzzle;

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
}
