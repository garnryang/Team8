package edu.psu.sweng500.team8.coreDataStructures;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import edu.psu.sweng500.team8.coreDataStructures.Cell;
import edu.psu.sweng500.team8.coreDataStructures.Cell.ValueType;
import edu.psu.sweng500.team8.io.BinaryInputStream;
import edu.psu.sweng500.team8.io.BinaryOutputStream;
import static org.junit.Assert.*;

import org.junit.Test;

public class CellTests {
	
	@Test
	public void defaultConstructorCreatesEmptyAndUserDefinedCell() {
		Cell testCell = new Cell();
		
		assertEquals("Cell value was not 0, which is not expected", 0, testCell.getNumber());
		assertEquals("Cell value was not UserDefined, which is not expected", Cell.ValueType.UserDefined, testCell.getType());
	}
	
	@Test
	public void copyConstructorCopiesValueAndType() {
		Cell originalCell = new Cell();
		originalCell.setNumber(5);
		originalCell.setType(Cell.ValueType.Given);
		
		Cell copiedCell = new Cell(originalCell);
		assertEquals("Copied number was not the same as the original, which is not expected", 5, copiedCell.getNumber());
		assertEquals("Copied type was not the same as the original, which is not expected", Cell.ValueType.Given, copiedCell.getType());
	}
	
	@Test
	public void numberCanBeSetAndRetrieved() {
		Cell testCell = new Cell();
		
		testCell.setNumber(5);
		assertEquals("Cell number did not match the set number, which is not expected", 5, testCell.getNumber());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setNumberThrowsIfValueIsLessThan1() {
		Cell testCell = new Cell();
		
		testCell.setNumber(0);
		fail("An exception was not thrown, which is not expected");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setNumberThrowsIfValueIsGreaterThan9() {
		Cell testCell = new Cell();
		
		testCell.setNumber(10);
		fail("An exception was not thrown, which is not expected");
	}
	
	@Test
	public void TypeCanBeSetAndRetrieved() {
		Cell testCell = new Cell();
		
		testCell.setType(Cell.ValueType.Given);
		assertEquals("Cell type was not Given, which is not expected", Cell.ValueType.Given, testCell.getType());
		
		testCell.setType(Cell.ValueType.UserDefined);
		assertEquals("Cell type was not UserDefined, which is not expected", Cell.ValueType.UserDefined, testCell.getType());
	}
	
	@Test
	public void hasNumberReturnsTrueIfNumberIsNot0() {
		Cell testCell = new Cell();
		
		testCell.setNumber(1);
		assertTrue("hasNumber was not true, which is not expected", testCell.hasNumber());
	}
	
	@Test
	public void hasNumberReturnsFalseIfNumberIsCleared() {
		Cell testCell = new Cell();
		
		assertFalse("hasNumber returned true, which is not expected", testCell.hasNumber());
		testCell.setNumber(5);
		testCell.clearNumber();
		assertFalse("hasNumber returned true, which is not expected", testCell.hasNumber());
	}
	
	@Test
	public void clearNumberSetsNumberTo0() {
		Cell testCell = new Cell();
		
		testCell.setNumber(5);
		testCell.clearNumber();
		assertEquals("Cell number was not 0, which is not expected", 0, testCell.getNumber());
		assertFalse("hasNumber returned true, which is not expected", testCell.hasNumber());
	}
	
	@Test
	public void canSaveAndLoadFromFile() {
		//Create a user-defined cell
		Cell userDefinedCellToSave = new Cell(); 
		userDefinedCellToSave.setNumber(7);
		userDefinedCellToSave.setType(ValueType.UserDefined);
		
		//Create a given cell
		Cell givenCellToSave = new Cell();
		givenCellToSave.setNumber(4);
		givenCellToSave.setType(ValueType.Given);
		
		Cell loadedUserDefinedCell = new Cell();
		Cell loadedGivenCell = new Cell();
		try {
			//Save them
			BinaryOutputStream writeStream = new BinaryOutputStream("test.txt");
			userDefinedCellToSave.save(writeStream);
			givenCellToSave.save(writeStream);
			writeStream.close();
			
			//Load them
			BinaryInputStream readStream = new BinaryInputStream("test.txt");
			loadedUserDefinedCell.load(readStream);
			loadedGivenCell.load(readStream);
			readStream.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			fail("File exception thrown");
		}
		
		//Compare the cells
		assertEquals(7, loadedUserDefinedCell.getNumber());
		assertEquals(ValueType.UserDefined, loadedUserDefinedCell.getType());
		assertEquals(4, loadedGivenCell.getNumber());
		assertEquals(ValueType.Given, loadedGivenCell.getType());
		
		Path filePath = Paths.get("test.txt");
		try {
			Files.delete(filePath);
		} catch (IOException e) {
			e.printStackTrace();
			fail("Failed to delete file");
		}
	}
}
