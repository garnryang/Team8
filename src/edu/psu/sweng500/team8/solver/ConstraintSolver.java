package edu.psu.sweng500.team8.solver;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import edu.psu.sweng500.team8.coreDataStructures.Board;
import edu.psu.sweng500.team8.coreDataStructures.Cell;
import edu.psu.sweng500.team8.coreDataStructures.CellConstraints;
import edu.psu.sweng500.team8.coreDataStructures.CellGrid;
import edu.psu.sweng500.team8.coreDataStructures.Constraint;

class ConstraintSolver implements Solver {
	public CellGrid findUniqueSolutionOrNull(Board board) {
		//Create a copy so we don't mess up the original
		Board copiedBoard = new Board(board); 
				
		//Loop until there are no available cells
		while (copiedBoard.hasOpenCells()) {
			//First try to find cells where there is only one possible number that can fit
			if (!tryToFillCellsWithOnlyOneAvailableNumber(copiedBoard)) {	
				//If that didn't work, loop over each constraint and look for numbers
				//where there is only one cell where it can fit
				if (!tryToFillCellsWithOnlyOneValidLocationForTheNumber(copiedBoard))
				{
					//Constraint solving failed. Skip out.
					break;
				}
			}
		}
		
		if (!copiedBoard.hasOpenCells()) {
			//Cool, we solved it just with constraints. This means there can only be one solution.
			return copiedBoard.getCellGrid();
		} 
		
		//Default to the guess and check solver, which should be the catch-all
		Solver guessAndCheckSolver = new GuessAndCheckSolver();
		return guessAndCheckSolver.findUniqueSolutionOrNull(copiedBoard);
	}
	
	public static Cell tryToFillACellWithOnlyOneAvailableNumber(Board board) {
		//Check for cells where there is only one available number
		Cell mostConstrainedCell = CommonSolverMethods.getMostConstrainedCell(board);

		Set<Integer> availableNumbers = board.getCellConstraints(mostConstrainedCell).getAvailableNumbers();
		if (availableNumbers.size() > 1)
			return null;

		//Sweet, there's only 1 available number, so the number is obvious
		int number = availableNumbers.toArray(new Integer[0])[0];
		mostConstrainedCell.setNumber(number);
		
		return mostConstrainedCell;
	}
	
	public static Cell tryToFillACellWithOnlyOneValidLocationForTheNumber(Board board) {
		return tryToFillACellWithOnlyOneValidLocationForTheNumber(board, true);
	}
	
	private static Cell tryToFillACellWithOnlyOneValidLocationForTheNumber(Board board, boolean breakOnSuccess) {
		Cell filledCell = null;
		
		//Build a queue of constraints sorted by the fewest available numbers
		ConstraintQueue queue = buildQueue(board);

		//Check each constraint
		while (!queue.isEmpty()) {
			Constraint currentConstraint = queue.dequeue();
			
			List<Cell> openCells = currentConstraint.getOpenCells();
			
			//Foreach available number in the constraint, 
			//check if there is only one cell where that number can fit
			
			//Use an iterator so we can safely remove while iterating
			Set<Integer> availableNumbers = currentConstraint.getAvailableNumbers();
 			Iterator<Integer> numberIterator = availableNumbers.iterator();
			while (numberIterator.hasNext()) {
				Integer currentNumber = numberIterator.next();
				
				Cell onlyCellThisNumberCanFitIn = null;
				for (Cell currentCell : openCells) {
					if (canNumberFitHere(board, currentCell, currentNumber)) {
						if (onlyCellThisNumberCanFitIn != null){
							//Bugger, we've found 2 cells it can fit in.
							onlyCellThisNumberCanFitIn = null;
							break;
						} else {
							onlyCellThisNumberCanFitIn = currentCell;
						}
					}
				}
				
				if (onlyCellThisNumberCanFitIn != null) {					
					//Sweet! We found a number where there is only one cell it can fit in.
					filledCell = onlyCellThisNumberCanFitIn;
					
					//Populate the cell.
					filledCell.setNumber(currentNumber);
					
					if (breakOnSuccess)
						return filledCell;
					
					//Remove it from the open cells
					openCells.remove(onlyCellThisNumberCanFitIn);
					
					//Remove it from the available numbers
					numberIterator.remove();
					
					//Re-sort the constraint queue
					queue.updateOnCellChange(board.getCellConstraints(onlyCellThisNumberCanFitIn));
				}
			}
		}
		
		return filledCell;
	}
	
	private static boolean tryToFillCellsWithOnlyOneAvailableNumber(Board board) {
		//Check for cells where there is only one available number
		boolean filledACell = false;

		while (board.hasOpenCells()) {
			Cell filledCell = tryToFillACellWithOnlyOneAvailableNumber(board);
			if (filledCell != null)
				filledACell = true;
			else
				break;
			//Optimization idea: this may have caused cells within the same constraint(s) to also have one available number.
			//Can check those next rather than starting the iteration over.
		}
		
		return filledACell;
	}

	private static boolean tryToFillCellsWithOnlyOneValidLocationForTheNumber(Board board) {
		return tryToFillACellWithOnlyOneValidLocationForTheNumber(board, false) != null;
	}
	
	private static ConstraintQueue buildQueue(Board board) {
		ConstraintQueue queue = new ConstraintQueue();
		
		List<Cell> openCells = board.getOpenCells();
		for (Cell c : openCells) {
			queue.enqueue(board.getCellConstraints(c));
		}
		
		return queue;
	}
	
	private static boolean canNumberFitHere(Board board, Cell cell, Integer number) {
		return board.getCellConstraints(cell).getAvailableNumbers().contains(number);
	}
	
	/** Queue of constraints where the most constrained constraint
	 * (i.e. has the least available numbers, except 0) is at the front of the queue
	 */
	private static class ConstraintQueue {
		private PriorityQueue<Constraint> m_queue = 
				new PriorityQueue<Constraint>(27, new ConstraintComparator());

		public boolean isEmpty() {
			return m_queue.isEmpty();
		}
		
		public void enqueue(CellConstraints constraints) {
			Iterator<Constraint> iterator = constraints.getIterator();

			while (iterator.hasNext())
			{
				Constraint constraint = iterator.next();
				enqueue(constraint);
			}
		}
		
		public void enqueue(Constraint constraint) {
			if (constraint.getAvailableNumbers().size() > 0 && !m_queue.contains(constraint)) //Ignore fully constrained constraints
				m_queue.add(constraint);
		}
		
		public Constraint dequeue() {
			return m_queue.poll();
		}
		
		/** 
		 * If a cell changes value, its constraints may change priority, 
		 * so we need to update the queue
		 * @param cellConstraints Constraints of the cell that changed
		 */
		public void updateOnCellChange(CellConstraints cellConstraints) {
			Iterator<Constraint> iterator = cellConstraints.getIterator();
			
			while (iterator.hasNext()) {
				Constraint constraint = iterator.next();
				//Unfortunately, the only way to re-prioritize is to remove and re-add constraint
				m_queue.remove(constraint);
				enqueue(constraint); //Using enqueue to ignore constraints with 0 available numbers
			}
		}
		
		/** Comparator based on the number of available numbers
		 */
		private class ConstraintComparator implements Comparator<Constraint> {
			
			public int compare(Constraint constraint1, Constraint constraint2) {
				int constraint1AvailableNumbers = constraint1.getAvailableNumbers().size();
				int constraint2AvailableNumbers = constraint2.getAvailableNumbers().size();
				
				if (constraint1AvailableNumbers < constraint2AvailableNumbers)
					return -1;
				
				if (constraint1AvailableNumbers == constraint2AvailableNumbers)
					return 0;
				
				return 1;
			}
		}
	}
}
