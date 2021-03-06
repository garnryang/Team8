package edu.psu.sweng500.team8.solver;

import java.util.List;
import java.util.Set;

import edu.psu.sweng500.team8.coreDataStructures.Board;
import edu.psu.sweng500.team8.coreDataStructures.Cell;


final class CommonSolverMethods {
	private CommonSolverMethods() {
		
	}
	
	public static Cell getMostConstrainedCell(Board board) {
		int leastAvailableNumbers = 10;
		Cell mostConstrainedCell = null;
		List<Cell> openCells = board.getOpenCells();
		for (Cell cell : openCells) {
			Set<Integer> availableNumbers = board.getCellConstraints(cell).getAvailableNumbers();
			
			if (availableNumbers.size() < leastAvailableNumbers) {
				mostConstrainedCell = cell;
				leastAvailableNumbers = availableNumbers.size();
			}
			
			if (leastAvailableNumbers == 1)
				break; //Can't get any better
		}
		
		return mostConstrainedCell;
	}
}
