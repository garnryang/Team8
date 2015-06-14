package edu.psu.sweng500.team8.coreDataStructures;

import java.util.Set;

import org.junit.Test;
import org.mockito.Mockito;

public class CellConstraintsTest {

	//	@Test
	//	public void testCellConstraints() {
	//		fail("Not yet implemented");
	//	}
	//
	//	@Test
	//	public void testGetRow() {
	//		fail("Not yet implemented");
	//	}
	//
	//	@Test
	//	public void testGetColumn() {
	//		fail("Not yet implemented");
	//	}
	//
	//	@Test
	//	public void testGetBlock() {
	//		fail("Not yet implemented");
	//	}

	@Test
	public void testGetAvailableNumbers() {

		CellGrid mockCellGrid = DataStructureTestHelper.buildSudokuManually();
		/* Overriding */
		Mockito.when(mockCellGrid.getCell(6, 0)).thenReturn(new Cell());
		Mockito.when(mockCellGrid.getCell(6, 1)).thenReturn(new Cell());
		Mockito.when(mockCellGrid.getCell(6, 2)).thenReturn(new Cell());
		Mockito.when(mockCellGrid.getCell(6, 3)).thenReturn(new Cell());
		Mockito.when(mockCellGrid.getCell(6, 4)).thenReturn(new Cell());
		Mockito.when(mockCellGrid.getCell(6, 5)).thenReturn(new Cell());
		Mockito.when(mockCellGrid.getCell(6, 6)).thenReturn(new Cell());
		Mockito.when(mockCellGrid.getCell(6, 7)).thenReturn(new Cell());
		Mockito.when(mockCellGrid.getCell(6, 8)).thenReturn(new Cell());

		Mockito.when(mockCellGrid.getCell(7, 0)).thenReturn(new Cell());
		Mockito.when(mockCellGrid.getCell(7, 1)).thenReturn(new Cell());
		Mockito.when(mockCellGrid.getCell(7, 2)).thenReturn(new Cell());
		Mockito.when(mockCellGrid.getCell(7, 3)).thenReturn(new Cell());
		Mockito.when(mockCellGrid.getCell(7, 4)).thenReturn(new Cell());
		Mockito.when(mockCellGrid.getCell(7, 5)).thenReturn(new Cell());
		Mockito.when(mockCellGrid.getCell(7, 6)).thenReturn(new Cell());
		Mockito.when(mockCellGrid.getCell(7, 7)).thenReturn(new Cell());
		Mockito.when(mockCellGrid.getCell(7, 8)).thenReturn(new Cell());

		Mockito.when(mockCellGrid.getCell(8, 0)).thenReturn(new Cell());
		Mockito.when(mockCellGrid.getCell(8, 1)).thenReturn(new Cell());
		Mockito.when(mockCellGrid.getCell(8, 2)).thenReturn(new Cell());
		Mockito.when(mockCellGrid.getCell(8, 3)).thenReturn(new Cell());
		Mockito.when(mockCellGrid.getCell(8, 4)).thenReturn(new Cell());
		Mockito.when(mockCellGrid.getCell(8, 5)).thenReturn(new Cell());
		Mockito.when(mockCellGrid.getCell(8, 6)).thenReturn(new Cell());
		Mockito.when(mockCellGrid.getCell(8, 7)).thenReturn(new Cell());
		Mockito.when(mockCellGrid.getCell(8, 8)).thenReturn(new Cell());

		Row mockRow = new Row(mockCellGrid, 6);
		Column mockColumn = new Column(mockCellGrid, 0);
		Block mockBlock = new Block(mockCellGrid, 6);
		CellConstraints cellConstraints = new CellConstraints(mockRow, mockColumn, mockBlock);

		Set<Integer> availableNumbers = cellConstraints.getAvailableNumbers();
		if (!availableNumbers.contains(2)) {
			org.junit.Assert.fail("2 must be available");
		}
		if (!availableNumbers.contains(3)) {
			org.junit.Assert.fail("3 must be available");
		}
		if (!availableNumbers.contains(9)) {
			org.junit.Assert.fail("9 must be available");
		}
	}	
}
