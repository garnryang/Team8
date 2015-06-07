package edu.psu.sweng500.team8.coreDataStructures;

import CoreDataStructures.Cell;

import static org.junit.Assert.*;
import org.junit.Test;

public class CellTest {
	
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
}
