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

	@Test //For UC1 Step1
	public void testsGeneratedSolutionfor4SudokuConstraints() {
				
		CellGrid grid = SolutionGenerator.generateSolution();
		
		//check for all cells occupied
		for(int i = 1; i < 9; i++){
			int cellToTest = grid.getCell(0, i).getNumber();
			assertTrue(cellToTest > 0 && cellToTest < 10);
		}
		
		//check for row constraint
		for(int j = 0; j < 9; j++) {
			Map<Integer, Boolean> rowCheck = new HashMap<Integer, Boolean>();
			for(int i = 0; i < 9; i++){
				int cellToTest = grid.getCell(j, i).getNumber();
				
				Boolean existing = rowCheck.get(cellToTest);
				if (null != existing) {
					Assert.fail("Numbers should be unique in each row");
				}				
				rowCheck.put(cellToTest, Boolean.TRUE);
			}
		}
		
		//check for column constraint
		for(int j = 0; j < 9; j++) {
			Map<Integer, Boolean> columnCheck = new HashMap<Integer, Boolean>();
			for(int i = 0; i < 9; i++){
				int cellToTest = grid.getCell(i, j).getNumber();
				
				Boolean existing = columnCheck.get(cellToTest);
				if (null != existing) {
					Assert.fail("Numbers should be unique in each column");
				}				
				columnCheck.put(cellToTest, Boolean.TRUE);
			}
		}
		
		//check for box constraint
		for(int i = 0; i < 9; i++) { // block index
			
			int blockRow = i%3; // 0 1 2 
			int blockColumn = i/3; // 0 1 2 
			
			Map<Integer, Boolean> blockCheck = new HashMap<Integer, Boolean>();
			
			for (int j = 0; j < 3; j++) {
				int a = blockRow*3 + j; //012; 345; 678
				
				for (int k = 0; k < 3; k++) {
					int b = blockColumn*3 + k; //012; 345; 678
					
					int cellToTest = grid.getCell(a, b).getNumber();
					
					Boolean existing = blockCheck.get(cellToTest);
					if (null != existing) {
						Assert.fail("Numbers should be unique in each column");
					}				
					blockCheck.put(cellToTest, Boolean.TRUE);
				}
			}
		}
	}

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
		puzzleBoard.initialize(puzzle, null);
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
