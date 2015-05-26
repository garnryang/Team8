package CoreDataStructures.Tests;

import java.util.HashSet;

import static org.junit.Assert.*;
import org.junit.Test;

import CoreDataStructures.Cell;
import CoreDataStructures.CellGrid;

public class CellGridTests {
	
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
}
