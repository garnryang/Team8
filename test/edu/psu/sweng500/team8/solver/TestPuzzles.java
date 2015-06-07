package edu.psu.sweng500.team8.solver;

import edu.psu.sweng500.team8.coreDataStructures.Puzzle;
import edu.psu.sweng500.team8.coreDataStructures.Puzzle.DifficultyLevel;


/**
 * This is a helper class
 *
 */
public final class TestPuzzles {
	private TestPuzzles() {
		
	}
	
	public static Puzzle getEasyPuzzle() {
		//http://www.puzzles.ca/sudoku_puzzles/sudoku_easy_025.html
		//#25
		Puzzle puzzle = new Puzzle();
		puzzle.setDifficulty(DifficultyLevel.Easy);
		
		//Set the solution first
		puzzle.setCellNumber(0, 0, 1);
		puzzle.setCellNumber(0, 1, 2);
		puzzle.setCellNumber(0, 2, 9);
		puzzle.setCellNumber(0, 3, 5);
		puzzle.setCellNumber(0, 4, 8);
		puzzle.setCellNumber(0, 5, 7);
		puzzle.setCellNumber(0, 6, 3);
		puzzle.setCellNumber(0, 7, 4);
		puzzle.setCellNumber(0, 8, 6);
		
		puzzle.setCellNumber(1, 0, 5);
		puzzle.setCellNumber(1, 1, 3);
		puzzle.setCellNumber(1, 2, 6);
		puzzle.setCellNumber(1, 3, 2);
		puzzle.setCellNumber(1, 4, 4);
		puzzle.setCellNumber(1, 5, 9);
		puzzle.setCellNumber(1, 6, 7);
		puzzle.setCellNumber(1, 7, 8);
		puzzle.setCellNumber(1, 8, 1);
		
		puzzle.setCellNumber(2, 0, 4);
		puzzle.setCellNumber(2, 1, 7);
		puzzle.setCellNumber(2, 2, 8);
		puzzle.setCellNumber(2, 3, 6);
		puzzle.setCellNumber(2, 4, 3);
		puzzle.setCellNumber(2, 5, 1);
		puzzle.setCellNumber(2, 6, 9);
		puzzle.setCellNumber(2, 7, 5);
		puzzle.setCellNumber(2, 8, 2);
		
		puzzle.setCellNumber(3, 0, 7);
		puzzle.setCellNumber(3, 1, 4);
		puzzle.setCellNumber(3, 2, 2);
		puzzle.setCellNumber(3, 3, 8);
		puzzle.setCellNumber(3, 4, 9);
		puzzle.setCellNumber(3, 5, 6);
		puzzle.setCellNumber(3, 6, 1);
		puzzle.setCellNumber(3, 7, 3);
		puzzle.setCellNumber(3, 8, 5);
		
		puzzle.setCellNumber(4, 0, 9);
		puzzle.setCellNumber(4, 1, 5);
		puzzle.setCellNumber(4, 2, 3);
		puzzle.setCellNumber(4, 3, 1);
		puzzle.setCellNumber(4, 4, 7);
		puzzle.setCellNumber(4, 5, 4);
		puzzle.setCellNumber(4, 6, 6);
		puzzle.setCellNumber(4, 7, 2);
		puzzle.setCellNumber(4, 8, 8);
		
		puzzle.setCellNumber(5, 0, 8);
		puzzle.setCellNumber(5, 1, 6);
		puzzle.setCellNumber(5, 2, 1);
		puzzle.setCellNumber(5, 3, 3);
		puzzle.setCellNumber(5, 4, 5);
		puzzle.setCellNumber(5, 5, 2);
		puzzle.setCellNumber(5, 6, 4);
		puzzle.setCellNumber(5, 7, 7);
		puzzle.setCellNumber(5, 8, 9);
		
		puzzle.setCellNumber(6, 0, 2);
		puzzle.setCellNumber(6, 1, 9);
		puzzle.setCellNumber(6, 2, 4);
		puzzle.setCellNumber(6, 3, 7);
		puzzle.setCellNumber(6, 4, 6);
		puzzle.setCellNumber(6, 5, 8);
		puzzle.setCellNumber(6, 6, 5);
		puzzle.setCellNumber(6, 7, 1);
		puzzle.setCellNumber(6, 8, 3);
		
		puzzle.setCellNumber(7, 0, 3);
		puzzle.setCellNumber(7, 1, 1);
		puzzle.setCellNumber(7, 2, 7);
		puzzle.setCellNumber(7, 3, 9);
		puzzle.setCellNumber(7, 4, 2);
		puzzle.setCellNumber(7, 5, 5);
		puzzle.setCellNumber(7, 6, 8);
		puzzle.setCellNumber(7, 7, 6);
		puzzle.setCellNumber(7, 8, 4);
		
		puzzle.setCellNumber(8, 0, 6);
		puzzle.setCellNumber(8, 1, 8);
		puzzle.setCellNumber(8, 2, 5);
		puzzle.setCellNumber(8, 3, 4);
		puzzle.setCellNumber(8, 4, 1);
		puzzle.setCellNumber(8, 5, 3);
		puzzle.setCellNumber(8, 6, 2);
		puzzle.setCellNumber(8, 7, 9);
		puzzle.setCellNumber(8, 8, 7);
		
		puzzle.setSolution(puzzle.getCopyOfCellGrid());
		
		//Now clear cells to make the puzzle
		puzzle.clearCell(0, 2);
		puzzle.clearCell(0, 3);
		puzzle.clearCell(0, 4);
		puzzle.clearCell(0, 6);
		puzzle.clearCell(0, 7);
		
		puzzle.clearCell(1, 0);
		puzzle.clearCell(1, 1);
		puzzle.clearCell(1, 2);
		puzzle.clearCell(1, 5);
		puzzle.clearCell(1, 7);
		
		puzzle.clearCell(2, 0);
		puzzle.clearCell(2, 1);
		puzzle.clearCell(2, 3);
		puzzle.clearCell(2, 4);
		puzzle.clearCell(2, 7);
		puzzle.clearCell(2, 8);
		
		puzzle.clearCell(3, 1);
		puzzle.clearCell(3, 2);
		puzzle.clearCell(3, 3);
		puzzle.clearCell(3, 5);
		puzzle.clearCell(3, 7);
		puzzle.clearCell(3, 8);
		
		puzzle.clearCell(4, 3);
		puzzle.clearCell(4, 4);
		puzzle.clearCell(4, 6);
		puzzle.clearCell(4, 7);
		puzzle.clearCell(4, 8);
		
		puzzle.clearCell(5, 0);
		puzzle.clearCell(5, 2);
		puzzle.clearCell(5, 5);
		puzzle.clearCell(5, 6);
		puzzle.clearCell(5, 8);
		
		puzzle.clearCell(6, 0);
		puzzle.clearCell(6, 2);
		puzzle.clearCell(6, 4);
		puzzle.clearCell(6, 5);
		puzzle.clearCell(6, 8);
		
		puzzle.clearCell(7, 1);
		puzzle.clearCell(7, 5);
		puzzle.clearCell(7, 8);
		
		puzzle.clearCell(8, 1);
		puzzle.clearCell(8, 2);
		puzzle.clearCell(8, 3);
		puzzle.clearCell(8, 4);
		puzzle.clearCell(8, 5);
		puzzle.clearCell(8, 6);
		puzzle.clearCell(8, 7);
		puzzle.clearCell(8, 8);
		
		return puzzle;
	}
	
	public static Puzzle getMediumPuzzle() {
		//http://www.puzzles.ca/sudoku_puzzles/sudoku_medium_075.html
		//#75
		Puzzle puzzle = new Puzzle();
		puzzle.setDifficulty(DifficultyLevel.Medium);
		
		//Set the solution first
		puzzle.setCellNumber(0, 0, 1);
		puzzle.setCellNumber(0, 1, 4);
		puzzle.setCellNumber(0, 2, 6);
		puzzle.setCellNumber(0, 3, 5);
		puzzle.setCellNumber(0, 4, 3);
		puzzle.setCellNumber(0, 5, 9);
		puzzle.setCellNumber(0, 6, 8);
		puzzle.setCellNumber(0, 7, 7);
		puzzle.setCellNumber(0, 8, 2);
		
		puzzle.setCellNumber(1, 0, 9);
		puzzle.setCellNumber(1, 1, 7);
		puzzle.setCellNumber(1, 2, 3);
		puzzle.setCellNumber(1, 3, 6);
		puzzle.setCellNumber(1, 4, 2);
		puzzle.setCellNumber(1, 5, 8);
		puzzle.setCellNumber(1, 6, 5);
		puzzle.setCellNumber(1, 7, 4);
		puzzle.setCellNumber(1, 8, 1);
		
		puzzle.setCellNumber(2, 0, 5);
		puzzle.setCellNumber(2, 1, 2);
		puzzle.setCellNumber(2, 2, 8);
		puzzle.setCellNumber(2, 3, 7);
		puzzle.setCellNumber(2, 4, 1);
		puzzle.setCellNumber(2, 5, 4);
		puzzle.setCellNumber(2, 6, 3);
		puzzle.setCellNumber(2, 7, 6);
		puzzle.setCellNumber(2, 8, 9);
		
		puzzle.setCellNumber(3, 0, 2);
		puzzle.setCellNumber(3, 1, 5);
		puzzle.setCellNumber(3, 2, 4);
		puzzle.setCellNumber(3, 3, 3);
		puzzle.setCellNumber(3, 4, 8);
		puzzle.setCellNumber(3, 5, 6);
		puzzle.setCellNumber(3, 6, 1);
		puzzle.setCellNumber(3, 7, 9);
		puzzle.setCellNumber(3, 8, 7);
		
		puzzle.setCellNumber(4, 0, 3);
		puzzle.setCellNumber(4, 1, 8);
		puzzle.setCellNumber(4, 2, 9);
		puzzle.setCellNumber(4, 3, 4);
		puzzle.setCellNumber(4, 4, 7);
		puzzle.setCellNumber(4, 5, 1);
		puzzle.setCellNumber(4, 6, 2);
		puzzle.setCellNumber(4, 7, 5);
		puzzle.setCellNumber(4, 8, 6);
		
		puzzle.setCellNumber(5, 0, 6);
		puzzle.setCellNumber(5, 1, 1);
		puzzle.setCellNumber(5, 2, 7);
		puzzle.setCellNumber(5, 3, 2);
		puzzle.setCellNumber(5, 4, 9);
		puzzle.setCellNumber(5, 5, 5);
		puzzle.setCellNumber(5, 6, 4);
		puzzle.setCellNumber(5, 7, 8);
		puzzle.setCellNumber(5, 8, 3);
		
		puzzle.setCellNumber(6, 0, 8);
		puzzle.setCellNumber(6, 1, 3);
		puzzle.setCellNumber(6, 2, 2);
		puzzle.setCellNumber(6, 3, 9);
		puzzle.setCellNumber(6, 4, 5);
		puzzle.setCellNumber(6, 5, 7);
		puzzle.setCellNumber(6, 6, 6);
		puzzle.setCellNumber(6, 7, 1);
		puzzle.setCellNumber(6, 8, 4);
		
		puzzle.setCellNumber(7, 0, 4);
		puzzle.setCellNumber(7, 1, 9);
		puzzle.setCellNumber(7, 2, 5);
		puzzle.setCellNumber(7, 3, 1);
		puzzle.setCellNumber(7, 4, 6);
		puzzle.setCellNumber(7, 5, 3);
		puzzle.setCellNumber(7, 6, 7);
		puzzle.setCellNumber(7, 7, 2);
		puzzle.setCellNumber(7, 8, 8);
		
		puzzle.setCellNumber(8, 0, 7);
		puzzle.setCellNumber(8, 1, 6);
		puzzle.setCellNumber(8, 2, 1);
		puzzle.setCellNumber(8, 3, 8);
		puzzle.setCellNumber(8, 4, 4);
		puzzle.setCellNumber(8, 5, 2);
		puzzle.setCellNumber(8, 6, 9);
		puzzle.setCellNumber(8, 7, 3);
		puzzle.setCellNumber(8, 8, 5);
		
		puzzle.setSolution(puzzle.getCopyOfCellGrid());
		
		//Now clear cells to make the puzzle
		puzzle.clearCell(0, 1);
		puzzle.clearCell(0, 2);
		puzzle.clearCell(0, 3);
		puzzle.clearCell(0, 4);
		puzzle.clearCell(0, 6);
		puzzle.clearCell(0, 7);
		
		puzzle.clearCell(1, 0);
		puzzle.clearCell(1, 2);
		puzzle.clearCell(1, 3);
		puzzle.clearCell(1, 7);
		
		puzzle.clearCell(2, 0);
		puzzle.clearCell(2, 3);
		puzzle.clearCell(2, 4);
		puzzle.clearCell(2, 5);
		puzzle.clearCell(2, 6);
		puzzle.clearCell(2, 7);
		puzzle.clearCell(2, 8);
		
		puzzle.clearCell(3, 0);
		puzzle.clearCell(3, 1);
		puzzle.clearCell(3, 2);
		puzzle.clearCell(3, 3);
		puzzle.clearCell(3, 4);
		puzzle.clearCell(3, 6);
		puzzle.clearCell(3, 8);
		
		puzzle.clearCell(4, 3);
		puzzle.clearCell(4, 5);
		puzzle.clearCell(4, 7);
		puzzle.clearCell(4, 8);
		
		puzzle.clearCell(5, 0);
		puzzle.clearCell(5, 2);
		puzzle.clearCell(5, 3);
		puzzle.clearCell(5, 4);
		puzzle.clearCell(5, 7);
		
		puzzle.clearCell(6, 0);
		puzzle.clearCell(6, 1);
		puzzle.clearCell(6, 2);
		puzzle.clearCell(6, 3);
		puzzle.clearCell(6, 4);
		puzzle.clearCell(6, 5);
		puzzle.clearCell(6, 8);
		
		puzzle.clearCell(7, 1);
		puzzle.clearCell(7, 3);
		puzzle.clearCell(7, 6);
		puzzle.clearCell(7, 7);
		puzzle.clearCell(7, 8);
		
		puzzle.clearCell(8, 1);
		puzzle.clearCell(8, 2);
		puzzle.clearCell(8, 3);
		puzzle.clearCell(8, 5);
		puzzle.clearCell(8, 7);
		puzzle.clearCell(8, 8);
		
		return puzzle;
	}
	
	public static Puzzle getHardPuzzle() {
		//http://www.puzzles.ca/sudoku_puzzles/sudoku_hard_075.html
		//#75
		Puzzle puzzle = new Puzzle();
		puzzle.setDifficulty(DifficultyLevel.Hard);
		
		//Set the solution first
		puzzle.setCellNumber(0, 0, 2);
		puzzle.setCellNumber(0, 1, 9);
		puzzle.setCellNumber(0, 2, 4);
		puzzle.setCellNumber(0, 3, 7);
		puzzle.setCellNumber(0, 4, 1);
		puzzle.setCellNumber(0, 5, 5);
		puzzle.setCellNumber(0, 6, 3);
		puzzle.setCellNumber(0, 7, 8);
		puzzle.setCellNumber(0, 8, 6);
		
		puzzle.setCellNumber(1, 0, 1);
		puzzle.setCellNumber(1, 1, 5);
		puzzle.setCellNumber(1, 2, 3);
		puzzle.setCellNumber(1, 3, 8);
		puzzle.setCellNumber(1, 4, 6);
		puzzle.setCellNumber(1, 5, 2);
		puzzle.setCellNumber(1, 6, 4);
		puzzle.setCellNumber(1, 7, 7);
		puzzle.setCellNumber(1, 8, 9);
		
		puzzle.setCellNumber(2, 0, 6);
		puzzle.setCellNumber(2, 1, 7);
		puzzle.setCellNumber(2, 2, 8);
		puzzle.setCellNumber(2, 3, 9);
		puzzle.setCellNumber(2, 4, 3);
		puzzle.setCellNumber(2, 5, 4);
		puzzle.setCellNumber(2, 6, 1);
		puzzle.setCellNumber(2, 7, 2);
		puzzle.setCellNumber(2, 8, 5);
		
		puzzle.setCellNumber(3, 0, 8);
		puzzle.setCellNumber(3, 1, 6);
		puzzle.setCellNumber(3, 2, 7);
		puzzle.setCellNumber(3, 3, 1);
		puzzle.setCellNumber(3, 4, 4);
		puzzle.setCellNumber(3, 5, 3);
		puzzle.setCellNumber(3, 6, 5);
		puzzle.setCellNumber(3, 7, 9);
		puzzle.setCellNumber(3, 8, 2);
		
		puzzle.setCellNumber(4, 0, 3);
		puzzle.setCellNumber(4, 1, 1);
		puzzle.setCellNumber(4, 2, 2);
		puzzle.setCellNumber(4, 3, 5);
		puzzle.setCellNumber(4, 4, 7);
		puzzle.setCellNumber(4, 5, 9);
		puzzle.setCellNumber(4, 6, 6);
		puzzle.setCellNumber(4, 7, 4);
		puzzle.setCellNumber(4, 8, 8);
		
		puzzle.setCellNumber(5, 0, 9);
		puzzle.setCellNumber(5, 1, 4);
		puzzle.setCellNumber(5, 2, 5);
		puzzle.setCellNumber(5, 3, 2);
		puzzle.setCellNumber(5, 4, 8);
		puzzle.setCellNumber(5, 5, 6);
		puzzle.setCellNumber(5, 6, 7);
		puzzle.setCellNumber(5, 7, 3);
		puzzle.setCellNumber(5, 8, 1);
		
		puzzle.setCellNumber(6, 0, 4);
		puzzle.setCellNumber(6, 1, 2);
		puzzle.setCellNumber(6, 2, 9);
		puzzle.setCellNumber(6, 3, 3);
		puzzle.setCellNumber(6, 4, 5);
		puzzle.setCellNumber(6, 5, 1);
		puzzle.setCellNumber(6, 6, 8);
		puzzle.setCellNumber(6, 7, 6);
		puzzle.setCellNumber(6, 8, 7);
		
		puzzle.setCellNumber(7, 0, 5);
		puzzle.setCellNumber(7, 1, 8);
		puzzle.setCellNumber(7, 2, 6);
		puzzle.setCellNumber(7, 3, 4);
		puzzle.setCellNumber(7, 4, 9);
		puzzle.setCellNumber(7, 5, 7);
		puzzle.setCellNumber(7, 6, 2);
		puzzle.setCellNumber(7, 7, 1);
		puzzle.setCellNumber(7, 8, 3);
		
		puzzle.setCellNumber(8, 0, 7);
		puzzle.setCellNumber(8, 1, 3);
		puzzle.setCellNumber(8, 2, 1);
		puzzle.setCellNumber(8, 3, 6);
		puzzle.setCellNumber(8, 4, 2);
		puzzle.setCellNumber(8, 5, 8);
		puzzle.setCellNumber(8, 6, 9);
		puzzle.setCellNumber(8, 7, 5);
		puzzle.setCellNumber(8, 8, 4);
		
		puzzle.setSolution(puzzle.getCopyOfCellGrid());
		
		//Now clear cells to make the puzzle
		puzzle.clearCell(0, 3);
		puzzle.clearCell(0, 4);
		puzzle.clearCell(0, 6);
		puzzle.clearCell(0, 7);
		puzzle.clearCell(0, 8);
		
		puzzle.clearCell(1, 0);
		puzzle.clearCell(1, 1);
		puzzle.clearCell(1, 2);
		puzzle.clearCell(1, 4);
		puzzle.clearCell(1, 5);
		puzzle.clearCell(1, 6);
		puzzle.clearCell(1, 7);
	
		puzzle.clearCell(2, 0);
		puzzle.clearCell(2, 2);
		puzzle.clearCell(2, 3);
		puzzle.clearCell(2, 4);
		puzzle.clearCell(2, 5);
		puzzle.clearCell(2, 7);
		puzzle.clearCell(2, 8);
	
		puzzle.clearCell(3, 0);
		puzzle.clearCell(3, 2);
		puzzle.clearCell(3, 3);
		puzzle.clearCell(3, 5);
		puzzle.clearCell(3, 6);
		puzzle.clearCell(3, 7);
		puzzle.clearCell(3, 8);
		
		puzzle.clearCell(4, 0);
		puzzle.clearCell(4, 1);
		puzzle.clearCell(4, 2);
		puzzle.clearCell(4, 4);
		puzzle.clearCell(4, 6);
		
		puzzle.clearCell(5, 0);
		puzzle.clearCell(5, 1);
		puzzle.clearCell(5, 2);
		puzzle.clearCell(5, 5);
		puzzle.clearCell(5, 7);
		
		puzzle.clearCell(6, 1);
		puzzle.clearCell(6, 2);
		puzzle.clearCell(6, 4);
		puzzle.clearCell(6, 5);
		puzzle.clearCell(6, 8);
		
		puzzle.clearCell(7, 0);
		puzzle.clearCell(7, 1);
		puzzle.clearCell(7, 2);
		puzzle.clearCell(7, 3);
		puzzle.clearCell(7, 5);
		puzzle.clearCell(7, 6);
		puzzle.clearCell(7, 7);
		puzzle.clearCell(7, 8);
		
		puzzle.clearCell(8, 0);
		puzzle.clearCell(8, 3);
		puzzle.clearCell(8, 4);
		puzzle.clearCell(8, 5);
		puzzle.clearCell(8, 6);
		puzzle.clearCell(8, 8);
		
		return puzzle;
	}
	
	public static Puzzle getPuzzleWithMultipleSolutions() {
		//http://www.sudokudragon.com/unsolvable.htm
		//Actually has 8 solutions
		Puzzle puzzle = new Puzzle();
		puzzle.setDifficulty(DifficultyLevel.Hard);
		
		//There is no one valid solution, so skip that part
		puzzle.setCellNumber(0, 1, 8);
		puzzle.setCellNumber(0, 5, 9);
		puzzle.setCellNumber(0, 6, 7);
		puzzle.setCellNumber(0, 7, 4);
		puzzle.setCellNumber(0, 8, 3);
		
		puzzle.setCellNumber(1, 1, 5);
		puzzle.setCellNumber(1, 5, 8);
		puzzle.setCellNumber(1, 7, 1);
		
		puzzle.setCellNumber(2, 1, 1);
		
		puzzle.setCellNumber(3, 0, 8);
		puzzle.setCellNumber(3, 5, 5);
		
		puzzle.setCellNumber(4, 3, 8);
		puzzle.setCellNumber(4, 5, 4);
		
		puzzle.setCellNumber(5, 3, 3);
		puzzle.setCellNumber(5, 8, 6);
		
		puzzle.setCellNumber(6, 7, 7);
		
		puzzle.setCellNumber(7, 1, 3);
		puzzle.setCellNumber(7, 3, 5);
		puzzle.setCellNumber(7, 7, 8);
		
		puzzle.setCellNumber(8, 0, 9);
		puzzle.setCellNumber(8, 1, 7);
		puzzle.setCellNumber(8, 2, 2);
		puzzle.setCellNumber(8, 3, 4);
		puzzle.setCellNumber(8, 7, 5);
		
		return puzzle;
	}
}
