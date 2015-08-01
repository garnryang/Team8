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

	@Test
	public void testEqualsObject() {
		
		final int TARGET_NUMBER = 3;
		final int TARGET_PENCIL_MARK = 8;
		
		CellGrid cellGrid_a = new CellGrid();
		cellGrid_a.getCell(0, 0).setNumber(TARGET_NUMBER);
		cellGrid_a.getCell(0, 0).getPencilMarks().add(TARGET_PENCIL_MARK);
		
		SudokuAction sudokuAction_a = new SudokuAction(cellGrid_a);
		
		CellGrid cellGrid_b = new CellGrid();
		SudokuAction sudokuAction_b = new SudokuAction(cellGrid_b);
		
		Assert.assertFalse(sudokuAction_a.equals(sudokuAction_b));
		
		cellGrid_b.getCell(0, 0).setNumber(TARGET_NUMBER);
		cellGrid_b.getCell(0, 0).getPencilMarks().add(TARGET_PENCIL_MARK);
		
		Assert.assertTrue(sudokuAction_a.equals(sudokuAction_b));		
	}
}
