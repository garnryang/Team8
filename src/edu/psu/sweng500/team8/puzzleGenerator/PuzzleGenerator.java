package edu.psu.sweng500.team8.puzzleGenerator;

/** Takes a valid complete Sudoku grid as a CellGrid as turns it into a valid puzzle*/

import java.util.Random;

import edu.psu.sweng500.team8.coreDataStructures.CellGrid;
import edu.psu.sweng500.team8.coreDataStructures.Puzzle.DifficultyLevel;



public class PuzzleGenerator{
	
	private CellGrid cellGrid;
	private DifficultyLevel difficultyLevel;
	
	public PuzzleGenerator(CellGrid inputGrid, DifficultyLevel level){
		cellGrid = inputGrid;
		difficultyLevel = level;
	}
	
	public CellGrid makePuzzle(){
		
		int revealedCellCount = 0;
		
		if(difficultyLevel == DifficultyLevel.Easy) revealedCellCount = 36;
		else if (difficultyLevel == DifficultyLevel.Medium) revealedCellCount = 29;
		else revealedCellCount = 26;
		
		int[] holdingArray = new int[revealedCellCount];
		
		Random randomGenerator = new Random();
		
		for(int i = 0; i < revealedCellCount; i++){
			int test = randomGenerator.nextInt(81);
			
			if(test != 0){
				//for(int j = 1; j <i-1; j++){
					//if(holdingArray[i-1] != test){
						holdingArray[i] = test;
			}else{
				i--;
				}
						
		}
		
		//blank cells in CellGrid
		 for(int i = 0; i < holdingArray.length; i++){
			 int r = ((int)Math.ceil(holdingArray[i]/9.0)-1);
			 int c = (holdingArray[i]%9)-1;
			 cellGrid.getCell(r, c==-1?8:c).clearNumber();
		 }
		 
		 return cellGrid;
	}
}