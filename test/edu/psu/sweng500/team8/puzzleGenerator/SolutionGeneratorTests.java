package edu.psu.sweng500.team8.puzzleGenerator;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import edu.psu.sweng500.team8.coreDataStructures.Board;
import edu.psu.sweng500.team8.coreDataStructures.CellGrid;
import edu.psu.sweng500.team8.coreDataStructures.Cell.ValueType;
import edu.psu.sweng500.team8.puzzleGenerator.SolutionGenerator;

public class SolutionGeneratorTests {
	
	private final static int TRIAL_LIMIT = 1000;

	/**
	 * This test is checking to see if Board.populationIteration() method can
	 * generate a fully populated board (without checking if it's satisfying
	 * other Sudoku rules) within reasonable number of trials (1,000).
	 */
	@Test
	public void testInitialBoardGenerationWithinLimit() {

		Board board = boardGeneration();

		if (board != null) {

			CellGrid cellGrid = board.getCellGrid();

			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					Assert.assertTrue("Must not be zero (0)",
							cellGrid.getCell(i, j).getNumber() != 0);
					Assert.assertEquals(ValueType.Given, cellGrid.getCell(i, j).getType());
					// System.out.println(cellGrid.getCell(i, j).getNumber());
				}
			}
		} else {
			Assert.fail("Board Generation Failed within " + TRIAL_LIMIT
					+ " trials!");
		}
	}

	/**
	 * This test is checking to see if Board.populationIteration() method can
	 * generate a board where all the rows are without any duplicates, all the
	 * columns are without any duplicates, and all the blocks are without any
	 * duplicates. This test will try to generate a reasonable number of full
	 * boards (30) to make sure the result is repeatable.
	 */
	@Test
	public void testInitialBoardGenerationCheckingSudokuRule() {

		final int NUMB_BOARDS_TO_BE_GENERATED = 30;

		for (int i = 0; i < NUMB_BOARDS_TO_BE_GENERATED; i++) {
			Board board = boardGeneration();
			eachIteration(board);
		}
	}
	
	/**
	 * This test is checking to see if SolutionGenerator.generateSolution method can
	 * generate a board where all the rows are without any duplicates, all the
	 * columns are without any duplicates, and all the blocks are without any
	 * duplicates. This test will try to generate a reasonable number of full
	 * boards (30) to make sure the result is repeatable.
	 */
	@Test
	public void testInitialBoardGenerationCheckingSudokuRuleUsingSolutionGenerator() {

		final int NUMB_BOARDS_TO_BE_GENERATED = 30;

		for (int i = 0; i < NUMB_BOARDS_TO_BE_GENERATED; i++) {
			CellGrid cellGrid = SolutionGenerator.generateSolution(); 
			eachIteration(cellGrid);
		}
	}
	
	private static void eachIteration(Board board) {
		if (board != null) {
			eachIteration(board.getCellGrid());
		} else {
			Assert.fail("Board Generation Failed within " + TRIAL_LIMIT
					+ " trials!");
		}
	}
	
	/**
	 * This method test a single full board generation checking rows, columns,
	 * and blocks. A full board is generated within a reasonable number of
	 * trials (1,000).
	 */
	private static void eachIteration(CellGrid cellGrid) {
		
		/* if a board is generated, we believe it's fully populated, at least */
		if (cellGrid != null) {

			/* preparing for block check */
			Map<Integer, Map<Integer, Boolean>> mapOfBlockMaps = new HashMap<>();
			for (int i = 0; i < 9; i++) {
				mapOfBlockMaps.put(i, new HashMap<Integer, Boolean>());
			}

			/* preparing for column check */
			Map<Integer, Map<Integer, Boolean>> mapOfColumnMaps = new HashMap<>();
			for (int i = 0; i < 9; i++) {
				mapOfColumnMaps.put(i, new HashMap<Integer, Boolean>());
			}

			/* iterating through each row */
			for (int i = 0; i < 9; i++) {

				/* preparing for row check */
				Map<Integer, Boolean> rowMap = new HashMap<Integer, Boolean>();

				/* iterating through each column */
				for (int j = 0; j < 9; j++) {
					int blockIndex = (i / 3) * 3 + j / 3;

					Map<Integer, Boolean> blockMap = mapOfBlockMaps
							.get(blockIndex);
					Boolean isInBlock = blockMap.get(cellGrid.getCell(i, j)
							.getNumber());

					if (null == isInBlock) {
						blockMap.put(cellGrid.getCell(i, j).getNumber(),
								Boolean.TRUE);
					} else {
						if (isInBlock) {

							for (int k = 0; k < 9; k++) {
								for (int l = 0; l < 9; l++) {
									System.out.print(cellGrid.getCell(k, l)
											.getNumber());
									System.out.print("_");
								}
								System.out.println();
							}

							Assert.fail("Duplicated Number Detected on the block: "
									+ blockIndex
									+ " and the value is: "
									+ cellGrid.getCell(i, j).getNumber());
						}
					}

					Map<Integer, Boolean> columnMap = mapOfColumnMaps.get(j);
					Boolean isInColumn = columnMap.get(cellGrid.getCell(i, j)
							.getNumber());

					if (null == isInColumn) {
						columnMap.put(cellGrid.getCell(i, j).getNumber(),
								Boolean.TRUE);
					} else {
						if (isInColumn) {

							for (int k = 0; k < 9; k++) {
								for (int l = 0; l < 9; l++) {
									System.out.print(cellGrid.getCell(k, l)
											.getNumber());
									System.out.print("_");
								}
								System.out.println();
							}

							Assert.fail("Duplicated Number Detected on the column: "
									+ j
									+ " and the value is: "
									+ cellGrid.getCell(i, j).getNumber());
						}
					}

					Boolean isInRow = rowMap.get(cellGrid.getCell(i, j)
							.getNumber());

					if (null == isInRow) {
						rowMap.put(cellGrid.getCell(i, j).getNumber(),
								Boolean.TRUE);
					} else {
						if (isInRow) {

							for (int k = 0; k < 9; k++) {
								for (int l = 0; l < 9; l++) {
									System.out.print(cellGrid.getCell(k, l)
											.getNumber());
									System.out.print("_");
								}
								System.out.println();
							}

							Assert.fail("Duplicated Number Detected on the row: "
									+ i
									+ " and the value is: "
									+ cellGrid.getCell(i, j).getNumber());
						}
					}
				}
			}

		} else {
			Assert.fail("Board Generation Failed within " + TRIAL_LIMIT
					+ " trials!");
		}
	}
	
	private static Board boardGeneration() {
		Board board = null;

		/* a full board is generated within 1,000 trials */
		for (int i = 0; i < TRIAL_LIMIT; i++) {
			board = SolutionGenerator.tryCreateSolution();
			if (board != null)
				break;
		}
		
		
		return board;
	} 
}
