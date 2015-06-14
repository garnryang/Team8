package edu.psu.sweng500.team8.solver;

public final class SolverFactory {
	private SolverFactory() {
	}
	
	public static ISolver getSolverThatTriesConstraintsFirst() {
		return new ConstraintSolver();
	}
	
	public static ISolver getStraightUpGuessAndCheckSolver() {
		return new GuessAndCheckSolver();
	}
}
