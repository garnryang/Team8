package edu.psu.sweng500.team8.puzzleGenerator;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.psu.sweng500.team8.coreDataStructures.CellGrid;
import edu.psu.sweng500.team8.coreDataStructures.Puzzle.DifficultyLevel;

public class PuzzleTests {

	//FIXME: This should be in SolutionGeneratorTests
	@Test //For UC1 Step1
	public void testsGeneratedSolutionfor4SudokuConstraints() {
				
		//FIXME: What is this test actually verifying? Seems to only be checking the first row,
		//and the constraint checks are incorrect
		
		CellGrid[] grid = SolutionGenerator.generateSolutions(1);
		
		//check for all cells occupied
		for(int i = 1; i < 9; i++){
			int cellToTest = grid[0].getCell(0, i).getNumber();
			assertTrue(cellToTest > 0 && cellToTest < 10);
		}
		
		//check for row constraint 
		for(int j = 0; j < 9; j++)
		{
			boolean test = false;
			for(int i = 0; i<9; i++){
				int cellToTest = grid[0].getCell(0, i).getNumber();
				if(cellToTest == j) test = true;
			}
			assertTrue(test);
		}
		
		//check for column constraint
		for(int j = 0; j < 9; j++)
		{
			boolean test = false;
			for(int i = 0; i<9; i++){
				int cellToTest = grid[0].getCell(i, 0).getNumber();
				if(cellToTest == j) test = true;
			}
			assertTrue(test);
		}
		
		//check for box constraint
		for(int j = 0; j < 9; j++)
		{
			for(int z = 0; z < 3; z++)
			{
				boolean test = false;
				for(int i = 0; i<3; i++){
					int cellToTest = grid[0].getCell(i, z).getNumber();
					if(cellToTest == j) test = true;
				}			
			assertTrue(test);
			}
		}
	}

	//FIXME: This should be in PuzzleGeneratorTests
	@Test //For UC1 Step2
	public void testsGeneratedPuzzleForatleasr18EmptyCells(){
		CellGrid[] grid = SolutionGenerator.generateSolutions(1);
		CellGrid puzzleGrid = PuzzleGenerator.makePuzzle(grid[0], DifficultyLevel.Hard).getCopyOfCellGrid();

		int emptyCellCounter = 0;

		for(int i = 0; i < 9; i++){
			for (int j = 0; j < 9; j++) {
				if(puzzleGrid.getCell(i,  j).hasNumber()) 
					emptyCellCounter++;
			}
		}
		assertTrue(emptyCellCounter >= 18);
	}

	//FIXME: This should be in PuzzleGeneratorTests
	@Test //For UC1 Step3
	public void testsforOneSolution(){
		CellGrid[] grid = SolutionGenerator.generateSolutions(1);
		CellGrid puzzleGrid = PuzzleGenerator.makePuzzle(grid[0], DifficultyLevel.Easy).getCopyOfCellGrid();
		
		DLX dlx = new DLX(puzzleGrid);
		int numSolutions = dlx.Solve();
		
		assertTrue(numSolutions == 1);
	}
	
	//FIXME: This should be in PuzzleGeneratorTests. Also, complete this test. 
	@Test //For UC1 Step4
	public void testsForWhetherSystemCategorizes(){
		int test = DifficultyLevel.Easy.ordinal();
		assertTrue(test == 36); 
	}
}
