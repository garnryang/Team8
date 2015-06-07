package edu.psu.sweng500.team8.puzzleGenerator;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

import edu.psu.sweng500.team8.coreDataStructures.CellGrid;
import edu.psu.sweng500.team8.coreDataStructures.Puzzle.DifficultyLevel;

public class PuzzleTests {

	@Test //For UC1 Step1
	public void testsGeneratedSolutionfor4SudokuConstraints() {
				
		CellGrid[] grid = SolutionGenerator.generateSolutions(1);
		
		//check for all cells occupied
		for(int i = 1; i<10; i++){
			int cellToTest = grid[0].getCell(0, i).getNumber();
			assertTrue(cellToTest > 0 && cellToTest <10);
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

	@Test //For UC1 Step2
	public void testsGeneratedPuzzleForatleasr18EmptyCells(){
		CellGrid[] grid = SolutionGenerator.generateSolutions(1);
		PuzzleGenerator pg = new PuzzleGenerator(grid[0],DifficultyLevel.Hard);
		
		CellGrid pz = pg.makePuzzle();
		
		
		int emptyCellCounter = 0;
		
		for(int i = 0; i < 81; i++){
			 int r = ((int)Math.ceil(i/9.0)-1);
			 int c = (i%9)-1;
			 int test = pz.getCell(r, c==-1?8:c).getNumber();
			 
			 if(test == 0) emptyCellCounter++;
			 
			 assertTrue(emptyCellCounter >= 18);
		 }
	}
	
	@Test //For UC1 Step3
	public void testsforOneSolution(){
		CellGrid[] grid = SolutionGenerator.generateSolutions(1);
		PuzzleGenerator pg = new PuzzleGenerator(grid[0],DifficultyLevel.Easy);
		
		CellGrid pz = pg.makePuzzle();
		
		DLX dlx = new DLX(pz);
		int test = dlx.Solve();
		
		assertTrue(test == 1);
	}
	
	@Test //For UC1 Step4
	public void testsForWhetherSystemCategorizes(){
		int test = DifficultyLevel.Easy.ordinal();
		assertTrue(test == 36);
	}
	
	@Test //For UC1 Step 5
	public void testsForGameSaves(){
		Game game = new Game(DifficultyLevel.Easy){
			assertTrue(game.Save());
		}
	}
}
