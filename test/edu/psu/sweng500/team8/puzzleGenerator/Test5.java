package edu.psu.sweng500.team8.puzzleGenerator;

import edu.psu.sweng500.team8.coreDataStructures.CellGrid;
import edu.psu.sweng500.team8.coreDataStructures.Puzzle.DifficultyLevel;



/**
 * FIXME: Class name should be more descriptive
 * TODO: Determine if this is still needed or valid
 * Is this an integration test case?
 */
public class Test5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CellGrid cg = PuzzleGenerator.makePuzzle(DifficultyLevel.Easy).getCopyOfCellGrid();
		
		for(int i = 0; i<9; i++){
			for(int j = 0; j<9; j++){
				System.out.print(cg.getCell(i, j).getNumber());
			}
		}
		
		DLX dlx = new DLX(cg);
		
		int test = dlx.Solve();
		
		System.out.println("\ns:"+test);
	}

}
