package edu.psu.sweng500.team8.solver;

import java.util.List;

import edu.psu.sweng500.team8.coreDataStructures.Board;
import edu.psu.sweng500.team8.coreDataStructures.CellGrid;


/** Base interface for solvers
 */
public interface ISolver {
	/** Get all solutions to the current board state, if it exists
	 * @throws IllegalArgumentException There is no possible solution to the board,
	 * or a constraint has been violated.
	 * @param board Board to solve
	 * @return All possible solutions
	 */
	List<CellGrid> findAllSolutions(Board board);
}