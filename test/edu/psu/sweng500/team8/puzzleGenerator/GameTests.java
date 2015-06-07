package edu.psu.sweng500.team8.puzzleGenerator;

import org.junit.Test;

import edu.psu.sweng500.team8.coreDataStructures.Puzzle.DifficultyLevel;



public class GameTests {

	@Test //UC2 Step 1
	public void testForGetNewGameWithDifficulty() {
		Game game = new Game(DifficultyLevel.Easy);
			
			AssertTrue(!game == null);			
	}
	
	@Test //UC2 Step 2
	public void testSystemLoadsPreGeneratedGame(){
		Game game = new Game(DifficultyLevel.Easy);
		BoardGUI boardGui = new BoardGUI();
		
		boardGui.setGame(game);
		
		AssetTrue(boardGui.hasGame());
	}
	
	@Test //UC2 Step 3
	public void testsSystemPresentsPuzzleToUser(){
		Game game = new Game(DifficultyLevel.Easy);
		BoardGUI boardGui = new BoardGUI();
		
		boardGui.setGame(game);
		
		AssertTrue(boardGui.display());
	}
}