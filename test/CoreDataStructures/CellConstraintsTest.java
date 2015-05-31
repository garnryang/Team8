package CoreDataStructures;

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

		CellGrid mockCellGrid = buildSudokuManually();
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

	/**
	 *  
	 * http://en.wikipedia.org/wiki/Sudoku#/media/File:Sudoku-by-L2G-20050714_solution.svg 

		5 3 4 6 7 8 9 1 2 
		6 7 2 1 9 5 3 4 8
		1 9 8 3 4 2 5 6 7
		8 5 9 7 6 1 4 2 3
		4 2 6 8 5 3 7 9 1
		7 1 3 9 2 4 8 5 6
		9 6 1 5 3 7 2 8 4
		2 8 7 4 1 9 6 3 5
		3 4 5 2 8 6 1 7 9
				
	*/
	private static CellGrid buildSudokuManually() {

		Cell mockCell_1 = new Cell();
		mockCell_1.setNumber(1);

		Cell mockCell_2 = new Cell();
		mockCell_2.setNumber(2);

		Cell mockCell_3 = new Cell();
		mockCell_3.setNumber(3);

		Cell mockCell_4 = new Cell();
		mockCell_4.setNumber(4);

		Cell mockCell_5 = new Cell();
		mockCell_5.setNumber(5);

		Cell mockCell_6 = new Cell();
		mockCell_6.setNumber(6);

		Cell mockCell_7 = new Cell();
		mockCell_7.setNumber(7);

		Cell mockCell_8 = new Cell();
		mockCell_8.setNumber(8);

		Cell mockCell_9 = new Cell();
		mockCell_9.setNumber(9);


		CellGrid mockCellGrid = Mockito.mock(CellGrid.class);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				Mockito.when(mockCellGrid.getCell(i, j)).thenReturn(new Cell());
			}
		}

		Mockito.when(mockCellGrid.getCell(0, 0)).thenReturn(mockCell_5);
		Mockito.when(mockCellGrid.getCell(0, 1)).thenReturn(mockCell_3);
		Mockito.when(mockCellGrid.getCell(0, 2)).thenReturn(mockCell_4);
		Mockito.when(mockCellGrid.getCell(0, 3)).thenReturn(mockCell_6);
		Mockito.when(mockCellGrid.getCell(0, 4)).thenReturn(mockCell_7);
		Mockito.when(mockCellGrid.getCell(0, 5)).thenReturn(mockCell_8);
		Mockito.when(mockCellGrid.getCell(0, 6)).thenReturn(mockCell_9);
		Mockito.when(mockCellGrid.getCell(0, 7)).thenReturn(mockCell_1);
		Mockito.when(mockCellGrid.getCell(0, 8)).thenReturn(mockCell_2);

		Mockito.when(mockCellGrid.getCell(1, 0)).thenReturn(mockCell_6);
		Mockito.when(mockCellGrid.getCell(1, 1)).thenReturn(mockCell_7);
		Mockito.when(mockCellGrid.getCell(1, 2)).thenReturn(mockCell_2);
		Mockito.when(mockCellGrid.getCell(1, 3)).thenReturn(mockCell_1);
		Mockito.when(mockCellGrid.getCell(1, 4)).thenReturn(mockCell_9);
		Mockito.when(mockCellGrid.getCell(1, 5)).thenReturn(mockCell_5);
		Mockito.when(mockCellGrid.getCell(1, 6)).thenReturn(mockCell_3);
		Mockito.when(mockCellGrid.getCell(1, 7)).thenReturn(mockCell_4);
		Mockito.when(mockCellGrid.getCell(1, 8)).thenReturn(mockCell_8);

		Mockito.when(mockCellGrid.getCell(2, 0)).thenReturn(mockCell_1);
		Mockito.when(mockCellGrid.getCell(2, 1)).thenReturn(mockCell_9);
		Mockito.when(mockCellGrid.getCell(2, 2)).thenReturn(mockCell_8);
		Mockito.when(mockCellGrid.getCell(2, 3)).thenReturn(mockCell_3);
		Mockito.when(mockCellGrid.getCell(2, 4)).thenReturn(mockCell_4);
		Mockito.when(mockCellGrid.getCell(2, 5)).thenReturn(mockCell_2);
		Mockito.when(mockCellGrid.getCell(2, 6)).thenReturn(mockCell_5);
		Mockito.when(mockCellGrid.getCell(2, 7)).thenReturn(mockCell_6);
		Mockito.when(mockCellGrid.getCell(2, 8)).thenReturn(mockCell_7);

		Mockito.when(mockCellGrid.getCell(3, 0)).thenReturn(mockCell_8);
		Mockito.when(mockCellGrid.getCell(3, 1)).thenReturn(mockCell_5);
		Mockito.when(mockCellGrid.getCell(3, 2)).thenReturn(mockCell_9);
		Mockito.when(mockCellGrid.getCell(3, 3)).thenReturn(mockCell_7);
		Mockito.when(mockCellGrid.getCell(3, 4)).thenReturn(mockCell_6);
		Mockito.when(mockCellGrid.getCell(3, 5)).thenReturn(mockCell_1);
		Mockito.when(mockCellGrid.getCell(3, 6)).thenReturn(mockCell_4);
		Mockito.when(mockCellGrid.getCell(3, 7)).thenReturn(mockCell_2);
		Mockito.when(mockCellGrid.getCell(3, 8)).thenReturn(mockCell_3);

		Mockito.when(mockCellGrid.getCell(4, 0)).thenReturn(mockCell_4);
		Mockito.when(mockCellGrid.getCell(4, 1)).thenReturn(mockCell_2);
		Mockito.when(mockCellGrid.getCell(4, 2)).thenReturn(mockCell_6);
		Mockito.when(mockCellGrid.getCell(4, 3)).thenReturn(mockCell_8);
		Mockito.when(mockCellGrid.getCell(4, 4)).thenReturn(mockCell_5);
		Mockito.when(mockCellGrid.getCell(4, 5)).thenReturn(mockCell_3);
		Mockito.when(mockCellGrid.getCell(4, 6)).thenReturn(mockCell_7);
		Mockito.when(mockCellGrid.getCell(4, 7)).thenReturn(mockCell_9);
		Mockito.when(mockCellGrid.getCell(4, 8)).thenReturn(mockCell_1);

		Mockito.when(mockCellGrid.getCell(5, 0)).thenReturn(mockCell_7);
		Mockito.when(mockCellGrid.getCell(5, 1)).thenReturn(mockCell_1);
		Mockito.when(mockCellGrid.getCell(5, 2)).thenReturn(mockCell_3);
		Mockito.when(mockCellGrid.getCell(5, 3)).thenReturn(mockCell_9);
		Mockito.when(mockCellGrid.getCell(5, 4)).thenReturn(mockCell_2);
		Mockito.when(mockCellGrid.getCell(5, 5)).thenReturn(mockCell_4);
		Mockito.when(mockCellGrid.getCell(5, 6)).thenReturn(mockCell_8);
		Mockito.when(mockCellGrid.getCell(5, 7)).thenReturn(mockCell_5);
		Mockito.when(mockCellGrid.getCell(5, 8)).thenReturn(mockCell_6);

		Mockito.when(mockCellGrid.getCell(6, 0)).thenReturn(mockCell_9);
		Mockito.when(mockCellGrid.getCell(6, 1)).thenReturn(mockCell_6);
		Mockito.when(mockCellGrid.getCell(6, 2)).thenReturn(mockCell_1);
		Mockito.when(mockCellGrid.getCell(6, 3)).thenReturn(mockCell_5);
		Mockito.when(mockCellGrid.getCell(6, 4)).thenReturn(mockCell_3);
		Mockito.when(mockCellGrid.getCell(6, 5)).thenReturn(mockCell_7);
		Mockito.when(mockCellGrid.getCell(6, 6)).thenReturn(mockCell_2);
		Mockito.when(mockCellGrid.getCell(6, 7)).thenReturn(mockCell_8);
		Mockito.when(mockCellGrid.getCell(6, 8)).thenReturn(mockCell_4);

		Mockito.when(mockCellGrid.getCell(7, 0)).thenReturn(mockCell_2);
		Mockito.when(mockCellGrid.getCell(7, 1)).thenReturn(mockCell_8);
		Mockito.when(mockCellGrid.getCell(7, 2)).thenReturn(mockCell_7);
		Mockito.when(mockCellGrid.getCell(7, 3)).thenReturn(mockCell_4);
		Mockito.when(mockCellGrid.getCell(7, 4)).thenReturn(mockCell_1);
		Mockito.when(mockCellGrid.getCell(7, 5)).thenReturn(mockCell_9);
		Mockito.when(mockCellGrid.getCell(7, 6)).thenReturn(mockCell_6);
		Mockito.when(mockCellGrid.getCell(7, 7)).thenReturn(mockCell_3);
		Mockito.when(mockCellGrid.getCell(7, 8)).thenReturn(mockCell_5);

		Mockito.when(mockCellGrid.getCell(8, 0)).thenReturn(mockCell_3);
		Mockito.when(mockCellGrid.getCell(8, 1)).thenReturn(mockCell_4);
		Mockito.when(mockCellGrid.getCell(8, 2)).thenReturn(mockCell_5);
		Mockito.when(mockCellGrid.getCell(8, 3)).thenReturn(mockCell_2);
		Mockito.when(mockCellGrid.getCell(8, 4)).thenReturn(mockCell_8);
		Mockito.when(mockCellGrid.getCell(8, 5)).thenReturn(mockCell_6);
		Mockito.when(mockCellGrid.getCell(8, 6)).thenReturn(mockCell_1);
		Mockito.when(mockCellGrid.getCell(8, 7)).thenReturn(mockCell_7);
		Mockito.when(mockCellGrid.getCell(8, 8)).thenReturn(mockCell_9);
		
		return mockCellGrid;
	}
}
