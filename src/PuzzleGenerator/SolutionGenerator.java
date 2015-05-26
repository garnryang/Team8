package PuzzleGenerator;

import CoreDataStructures.Board;
import CoreDataStructures.CellGrid;

public class SolutionGenerator {

	public static void main(String[] args) {

		Board board = null;
		final int TRIAL_LIMIT = 1000;
		boolean keepGoing = true;
		int breakCounter = 0;

		while (keepGoing && breakCounter < TRIAL_LIMIT) {
			try {
				board = new Board();
				board.populationIteration();				
				keepGoing = false;
			} catch (Exception e) {
				breakCounter++;
			}
		}

		System.out.println("failed: " + breakCounter);

		if (board != null) {

			CellGrid cellGrid = board.getCellGrid();
			
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(cellGrid.getCell(i, j).getNumber());
					System.out.print(" ");
				}
				System.out.println();
			}
		}

		else {
			System.out.println("Board cannot be populated");
		}
	}
}