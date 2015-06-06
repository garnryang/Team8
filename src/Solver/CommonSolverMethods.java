package Solver;

import java.util.List;
import java.util.Set;

import CoreDataStructures.Board;
import CoreDataStructures.Cell;

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
