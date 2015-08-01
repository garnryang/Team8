package edu.psu.sweng500.team8.play;

import org.junit.Assert;
import org.junit.Test;

import edu.psu.sweng500.team8.coreDataStructures.CellGrid;

public class SudokuActionTest {

	@Test
	public void testGetCellGrid() {
		
		final int TARGET_NUMBER = 3;
		final int TARGET_PENCIL_MARK = 8;
		
		CellGrid cellGrid_a = new CellGrid();
		cellGrid_a.getCell(0, 0).setNumber(TARGET_NUMBER);
		cellGrid_a.getCell(0, 0).getPencilMarks().add(TARGET_PENCIL_MARK);
		
		SudokuAction sudokuAction_a = new SudokuAction(cellGrid_a);
		
		Assert.assertTrue(cellGrid_a.equals(sudokuAction_a.getCellGrid()));
	}
}
