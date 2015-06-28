package edu.psu.sweng500.team8.solver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import edu.psu.sweng500.team8.coreDataStructures.Board;
import edu.psu.sweng500.team8.coreDataStructures.Cell;
import edu.psu.sweng500.team8.coreDataStructures.CellGrid;


class GuessAndCheckSolver implements Solver {
	public CellGrid findUniqueSolutionOrNull(Board board) {
		List<CellGrid> foundSolutions = new ArrayList<CellGrid>();
		
		tryRecursiveGuessAndCheck(new Board(board), foundSolutions, true);
		
		return (foundSolutions.size() == 1) ? foundSolutions.get(0) : null;
	}
	
	private static boolean tryRecursiveGuessAndCheck(
			Board board, 
			List<CellGrid> foundSolutions, 
			boolean stopAfter2Solutions) {
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
			if (tryRecursiveGuessAndCheck(board, foundSolutions, stopAfter2Solutions))
			{
				//Successfully found a solution
				success = true;
			} 
			
			if (stopAfter2Solutions && foundSolutions.size() > 1)
				return success; //Found 2 solutions -- quit now
			
			//Clear it and try a different one.
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
