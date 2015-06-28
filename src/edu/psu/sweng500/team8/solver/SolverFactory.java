package edu.psu.sweng500.team8.solver;

public final class SolverFactory {
	private SolverFactory() {
	}
	
	public static Solver getSolverThatTriesConstraintsFirst() {
		return new ConstraintSolver();
	}
	
	public static Solver getStraightUpGuessAndCheckSolver() {
		return new GuessAndCheckSolver();
	}
}
