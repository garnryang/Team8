package edu.psu.sweng500.team8.puzzleGenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import edu.psu.sweng500.team8.coreDataStructures.Board;
import edu.psu.sweng500.team8.coreDataStructures.Cell;
import edu.psu.sweng500.team8.coreDataStructures.Cell.ValueType;
import edu.psu.sweng500.team8.coreDataStructures.CellGrid;
import edu.psu.sweng500.team8.coreDataStructures.Puzzle;
import edu.psu.sweng500.team8.coreDataStructures.Puzzle.DifficultyLevel;
import edu.psu.sweng500.team8.solver.Solver;
import edu.psu.sweng500.team8.solver.SolverFactory;

public class PuzzleGeneratorTests {

	@Test //For UC1 Step2
	public void testsGeneratedPuzzleForAtLeast18EmptyCells(){
		CellGrid puzzleGrid = PuzzleGenerator.makePuzzle(DifficultyLevel.Hard).getCopyOfCellGrid();

		int emptyCellCounter = 0;

		for(int i = 0; i < 9; i++){
			for (int j = 0; j < 9; j++) {
				if(puzzleGrid.getCell(i,  j).hasNumber()) 
					emptyCellCounter++;
			}
		}
		assertTrue(emptyCellCounter >= 18);
	}

	@Test //For UC1 Step3
	public void testsforOneSolution(){
		Puzzle puzzle = PuzzleGenerator.makePuzzle(DifficultyLevel.Easy);
		
		Board puzzleBoard = new Board();
		puzzleBoard.initialize(puzzle);
		Solver solver = SolverFactory.getSolverThatTriesConstraintsFirst();
		assertNotNull(solver.findUniqueSolutionOrNull(puzzleBoard));
	}
	
	//FIXME: Complete this test. 
	@Test //For UC1 Step4
	public void testsForWhetherSystemCategorizes(){
		int test = DifficultyLevel.Easy.ordinal();
		assertTrue(test == 36); 
	}
	
	@Test
	public void makePuzzleSetsPuzzleDifficulty() {
		Puzzle puzzle = PuzzleGenerator.makePuzzle(DifficultyLevel.Easy);
		assertEquals(DifficultyLevel.Easy, puzzle.getDifficulty());
		
		puzzle = PuzzleGenerator.makePuzzle(DifficultyLevel.Medium);
		assertEquals(DifficultyLevel.Medium, puzzle.getDifficulty());
		
		puzzle = PuzzleGenerator.makePuzzle(DifficultyLevel.Hard);
		assertEquals(DifficultyLevel.Hard, puzzle.getDifficulty());
	}
	
	@Test
	public void makePuzzleSetsValueTypeOfGivenCells() {
		Puzzle puzzle = PuzzleGenerator.makePuzzle(DifficultyLevel.Easy);
		
		CellGrid grid = puzzle.getCopyOfCellGrid();
		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				Cell currentCell = grid.getCell(row, column);
				
				//If it has a number, it should be a Given type. Otherwise it is UserDefined
				ValueType expectedType = (currentCell.hasNumber()) ? ValueType.Given : ValueType.UserDefined;
				assertEquals(expectedType, currentCell.getType());
			}
		}
	}
}
