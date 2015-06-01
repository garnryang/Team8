package Solver;

import java.util.List;

import CoreDataStructures.Board;
import CoreDataStructures.CellGrid;

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
