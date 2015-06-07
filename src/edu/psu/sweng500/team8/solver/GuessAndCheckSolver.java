package edu.psu.sweng500.team8.solver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import edu.psu.sweng500.team8.coreDataStructures.Board;
import edu.psu.sweng500.team8.coreDataStructures.Cell;
import edu.psu.sweng500.team8.coreDataStructures.CellGrid;


class GuessAndCheckSolver implements ISolver {
	public List<CellGrid> findAllSolutions(Board board) {
		List<CellGrid> foundSolutions = new ArrayList<CellGrid>();
		
		tryRecursiveGuessAndCheck(new Board(board), foundSolutions);
		
		return foundSolutions;
	}
	
	private static boolean tryRecursiveGuessAndCheck(Board board, List<CellGrid> foundSolutions) {
		if (!board.hasOpenCells())
		{
			//Found a solution! But first check if it is a duplicate
			addIfNotDuplicate(board.getCellGrid(), foundSolutions);
			return true;
		}
		
		//Start with the most constrained cell
		Cell cellToTry = CommonSolverMethods.getMostConstrainedCell(board);
		Set<Integer> availableNumbers = board.getCellConstraints(cellToTry).getAvailableNumbers();

		boolean success = false;
		for (Integer number : availableNumbers) {
			cellToTry.setNumber(number);

			//Recursively try to solve the puzzle
			if (tryRecursiveGuessAndCheck(board, foundSolutions))
			{
				//Successfully found a solution, but quit yet since we want to find all solutions
				success = true;
			} 
			
			//Didn't work. Clear it and try a different one.
			cellToTry.clearNumber();
		}

		return success;
	}
	
	private static void addIfNotDuplicate(CellGrid solution, List<CellGrid> foundSolutions) {
		for (CellGrid foundSolution : foundSolutions) {
			if (solution.valuesAreEqual(foundSolution))
				return;
		}

		//Make a copy 
		foundSolutions.add(new CellGrid(solution));
	}
}
