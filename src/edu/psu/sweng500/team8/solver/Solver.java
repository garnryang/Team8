package edu.psu.sweng500.team8.solver;

import edu.psu.sweng500.team8.coreDataStructures.Board;
import edu.psu.sweng500.team8.coreDataStructures.CellGrid;


/** Base interface for solvers
 */
public interface Solver {
	/** Get the unique solution to the current board state, if it exists
	 * @throws IllegalArgumentException There is no possible solution to the board,
	 * or a constraint has been violated.
	 * @param board Board to solve
	 * @return Unique solution, else null
	 */
	CellGrid findUniqueSolutionOrNull(Board board);
}
