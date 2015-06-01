package CoreDataStructures;

import static org.junit.Assert.*;

import org.junit.Test;

import CoreDataStructures.CellCoordinates;

/** Test the helper methods since I don't trust my math
 */
public class CellCoordinatesTests {
	@Test
	public void getBlockIndexRows0Through2Columns0Through2Returns0() {
		for (int rowIndex = 0; rowIndex < 3; rowIndex++) {
			for (int columnIndex = 0; columnIndex < 3; columnIndex++) {
				CellCoordinates coordinates = new CellCoordinates(rowIndex, columnIndex);
				assertEquals(0, coordinates.getBlockIndex());
			}
		}
	}
	
	@Test
	public void getBlockIndexRows0Through2Columns3Through5Returns1() {
		for (int rowIndex = 0; rowIndex < 3; rowIndex++) {
			for (int columnIndex = 3; columnIndex < 6; columnIndex++) {
				CellCoordinates coordinates = new CellCoordinates(rowIndex, columnIndex);
				assertEquals(1, coordinates.getBlockIndex());
			}
		}
	}
	
	@Test
	public void getBlockIndexRows0Through2Columns6Through8Returns2() {
		for (int rowIndex = 0; rowIndex < 3; rowIndex++) {
			for (int columnIndex = 6; columnIndex < 9; columnIndex++) {
				CellCoordinates coordinates = new CellCoordinates(rowIndex, columnIndex);
				assertEquals(2, coordinates.getBlockIndex());
			}
		}
	}
	
	@Test
	public void getBlockIndexRows3Through5Columns0Through2Returns3() {
		for (int rowIndex = 3; rowIndex < 6; rowIndex++) {
			for (int columnIndex = 0; columnIndex < 3; columnIndex++) {
				CellCoordinates coordinates = new CellCoordinates(rowIndex, columnIndex);
				assertEquals(3, coordinates.getBlockIndex());
			}
		}
	}
	
	@Test
	public void getBlockIndexRows3Through5Columns3Through5Returns4() {
		for (int rowIndex = 3; rowIndex < 6; rowIndex++) {
			for (int columnIndex = 3; columnIndex < 6; columnIndex++) {
				CellCoordinates coordinates = new CellCoordinates(rowIndex, columnIndex);
				assertEquals(4, coordinates.getBlockIndex());
			}
		}
	}
	
	@Test
	public void getBlockIndexRows3Through5Columns6Through8Returns5() {
		for (int rowIndex = 3; rowIndex < 6; rowIndex++) {
			for (int columnIndex = 6; columnIndex < 9; columnIndex++) {
				CellCoordinates coordinates = new CellCoordinates(rowIndex, columnIndex);
				assertEquals(5, coordinates.getBlockIndex());
			}
		}
	}
	
	@Test
	public void getBlockIndexRows6Through8Columns0Through2Returns6() {
		for (int rowIndex = 6; rowIndex < 9; rowIndex++) {
			for (int columnIndex = 0; columnIndex < 3; columnIndex++) {
				CellCoordinates coordinates = new CellCoordinates(rowIndex, columnIndex);
				assertEquals(6, coordinates.getBlockIndex());
			}
		}
	}
	
	@Test
	public void getBlockIndexRows6Through8Columns3Through5Returns7() {
		for (int rowIndex = 6; rowIndex < 9; rowIndex++) {
			for (int columnIndex = 3; columnIndex < 6; columnIndex++) {
				CellCoordinates coordinates = new CellCoordinates(rowIndex, columnIndex);
				assertEquals(7, coordinates.getBlockIndex());
			}
		}
	}
	
	@Test
	public void getBlockIndexRows6Through8Columns6Through8Returns8() {
		for (int rowIndex = 6; rowIndex < 9; rowIndex++) {
			for (int columnIndex = 6; columnIndex < 9; columnIndex++) {
				CellCoordinates coordinates = new CellCoordinates(rowIndex, columnIndex);
				assertEquals(8, coordinates.getBlockIndex());
			}
		}
	}
}
