package edu.psu.sweng500.team8.coreDataStructures;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;


public class CellGridTest {
	
	@Test
	public void defaultConstructorInitializes9x9GridOfUniqueEmptyCells() {
		CellGrid testGrid = new CellGrid();
		
		//Keep track of which cells were encountered to verify they are not encountered more than once
		HashSet<Cell> encounteredCells = new HashSet<Cell>(81);
		for (int rowIndex = 0; rowIndex < 9; rowIndex++) {
			for (int columnIndex = 0; columnIndex < 9; columnIndex++) {
				Cell currentCell = testGrid.getCell(rowIndex, columnIndex);
				 
				assertNotNull("A null cell was encountered, which is not expected", 
						currentCell);
				assertFalse("A cell was encountered more than once, which is not expected", 
						encounteredCells.contains(currentCell));
				
				assertFalse("A cell was encountered with a number already populated, which is not expected", 
						currentCell.hasNumber());
				encounteredCells.add(currentCell);
			}
		}		
	}

	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void getCellThrowsExceptionIfRowIndexIsLessThan0() {
		CellGrid testGrid = new CellGrid();
		
		testGrid.getCell(-1, 5);
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void getCellThrowsExceptionIfRowIndexIsGreaterThan8() {
		CellGrid testGrid = new CellGrid();
		
		testGrid.getCell(9, 5);
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void getCellThrowsExceptionIfColumnIndexIsLessThan0() {
		CellGrid testGrid = new CellGrid();
		
		testGrid.getCell(5, -1);
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void getCellThrowsExceptionIfColumnIndexIsGreaterThan8() {
		CellGrid testGrid = new CellGrid();
		
		testGrid.getCell(5, 9);
	}
	
	@Test
	public void testCopyLineConstructor() {
		CellGrid mockCellGrid = DataStructureTestHelper.buildSudokuManually();
		CellGrid targetCellGrid = new CellGrid(mockCellGrid);
		
		if (mockCellGrid == targetCellGrid) {
			Assert.fail("two cannot be the same instance");
		}
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				Cell originalCell = mockCellGrid.getCell(i,  j);
				Cell targetCell = targetCellGrid.getCell(i,  j);
				
				Assert.assertEquals(originalCell.getNumber(), targetCell.getNumber());
			}
		}
	}
	
	@Test
	public void valuesAreEqualReturnsTrueForGridsWithSameValues() {
		CellGrid mockCellGrid = DataStructureTestHelper.buildSudokuManually();
		CellGrid targetCellGrid = new CellGrid(mockCellGrid);

		Assert.assertTrue(targetCellGrid.valuesAreEqual(mockCellGrid));
	}
}
