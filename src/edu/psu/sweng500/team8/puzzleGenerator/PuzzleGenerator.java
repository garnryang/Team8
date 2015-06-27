package edu.psu.sweng500.team8.puzzleGenerator;

/** Takes a valid complete Sudoku grid as a CellGrid as turns it into a valid puzzle*/

import edu.psu.sweng500.team8.coreDataStructures.Board;
import edu.psu.sweng500.team8.coreDataStructures.CellGrid;
import edu.psu.sweng500.team8.coreDataStructures.Puzzle;
import edu.psu.sweng500.team8.coreDataStructures.Puzzle.DifficultyLevel;
import edu.psu.sweng500.team8.solver.ISolver;
import edu.psu.sweng500.team8.solver.SolverFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class PuzzleGenerator {
	private PuzzleGenerator() {

	}
	
	public static Puzzle makePuzzle(DifficultyLevel difficulty){
		CellGrid solutionGrid = SolutionGenerator.generateSolution();
		int revealedCellCount = 0;

		if(difficulty == DifficultyLevel.Easy) revealedCellCount = 40; //Recommended between 36-49
		else if (difficulty == DifficultyLevel.Medium) revealedCellCount = 33; //Recommended between 32-35
		else revealedCellCount = 29; //Recommended between 28-31

		int numCellsToClear = 81 - revealedCellCount; //Inverse

		//Loop until we get a puzzle with a unique solution
		Puzzle newPuzzle = null;
		do {
			newPuzzle = tryGeneratePuzzle(solutionGrid, numCellsToClear);
		} while (!hasUniqueSolution(newPuzzle));
		
		newPuzzle.setDifficulty(difficulty);
		return newPuzzle;
	}

	private static Puzzle tryGeneratePuzzle(CellGrid solution, int numCellsToClear) {
		Puzzle puzzle = new Puzzle(solution, solution);

		//To ensure no duplicates, shuffle the list of numbers
		List<Integer> numbersToRemove = new ArrayList<Integer>(81);
		for (int i = 0; i < 81; i++) {
			numbersToRemove.add(i);
		}

		Collections.shuffle(numbersToRemove);

		//Clear random cells in the grid
		for(int i = 0; i < numCellsToClear; i++){
			int randomNumber = numbersToRemove.get(i);
			int rowIndex = randomNumber / 9;
			int columnIndex = randomNumber % 9;
			puzzle.clearCell(rowIndex, columnIndex);
		}

		return puzzle;
	}

	private static boolean hasUniqueSolution(Puzzle puzzle) {
		Board puzzleBoard = new Board();
		puzzleBoard.Initialize(puzzle);
		ISolver solver = SolverFactory.getSolverThatTriesConstraintsFirst();
		CellGrid solution = solver.findUniqueSolutionOrNull(puzzleBoard);
		
		return solution != null;
		
		//Disabled due to Taiga issue #81
		//DLX solver = new DLX(puzzle.getCopyOfCellGrid());

		//return solver.Solve() == 1;
	}
}