package edu.psu.sweng500.team8.coreDataStructures;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;

import edu.psu.sweng500.team8.io.BinaryInputStream;
import edu.psu.sweng500.team8.io.BinaryOutputStream;

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
	
	@Test
	public void canSaveAndLoadFromFile() {
		CellGrid gridToSave = TestPuzzles.getEasyPuzzle().getCopyOfCellGrid();
		CellGrid loadedGrid = new CellGrid();
		
		try {
			//Save it
			BinaryOutputStream writeStream = new BinaryOutputStream("test.txt");
			gridToSave.save(writeStream);
			writeStream.close();
			
			//Load it
			BinaryInputStream readStream = new BinaryInputStream("test.txt");
			loadedGrid.load(readStream);
			readStream.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			fail("File exception thrown");
		}
		
		//Compare the cells
		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				Cell savedCell = gridToSave.getCell(row, column);
				Cell loadedCell = gridToSave.getCell(row, column);
				
				//Check the value
				assertEquals(savedCell.getNumber(), loadedCell.getNumber());
				
				//Check the type
				assertEquals(savedCell.getType(), loadedCell.getType());
			}
		}
		
		Path filePath = Paths.get("test.txt");
		try {
			Files.delete(filePath);
		} catch (IOException e) {
			e.printStackTrace();
			fail("Failed to delete file");
		}
	}
}
