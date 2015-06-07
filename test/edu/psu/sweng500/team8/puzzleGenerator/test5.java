package edu.psu.sweng500.team8.puzzleGenerator;

import CoreDataStructures.CellGrid;
import CoreDataStructures.Puzzle.DifficultyLevel;



public class test5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SolutionGenerator solGen = new SolutionGenerator();
		CellGrid[] grid = solGen.generateSolutions(1);
		PuzzleGenerator pg = new PuzzleGenerator(grid[0],DifficultyLevel.Easy);
		
		CellGrid cg = pg.makePuzzle();
		
		for(int i = 0; i<9; i++){
			for(int j = 0; j<9; j++){
				System.out.print(cg.getCell(i, j).getNumber());
			}
		}
		
		@SuppressWarnings("unused")
		DLX dlx = new DLX(cg);
		
		int test = dlx.Solve();
		
		System.out.println("\ns:"+test);
	}

}
