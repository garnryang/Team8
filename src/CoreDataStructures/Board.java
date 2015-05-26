package CoreDataStructures;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Data holder and controller for a Sudoku grid Used to validate game
 * constraints
 */
public class Board {
	private CellGrid m_grid = new CellGrid(); // The actual 9x9 grid of cells
	private Row[] m_rows = new Row[9]; // Abstraction of rows
	private Column[] m_columns = new Column[9]; // Abstraction of columns
	private Block[] m_blocks = new Block[9]; // Abstraction of blocks

	public Board() {
		// Create rows, columns, and blocks
		for (int i = 0; i < 9; i++) {
			m_rows[i] = new Row(m_grid, i); // Rows from 0-8 go top to bottom
			m_columns[i] = new Column(m_grid, i); // Columns from 0-8 go left to
													// right
			m_blocks[i] = new Block(m_grid, i); // Blocks from 0-8 go left to
												// right, top to bottom
		}
	}

	public CellGrid getCellGrid() {
		return this.m_grid;
	}
	
	public void Initialize(Puzzle puzzle) {

	}

	public void populationIteration() throws Exception {

		// row
		for (int i = 0; i < 9; i++) {

			// column
			for (int j = 0; j < 9; j++) {

				List<Integer> availableNumbers = new ArrayList<Integer>();

				Set<Integer> availableNumbers_row = m_rows[i]
						.getAvailableNumbers();
				
				Set<Integer> availableNumbers_column = m_columns[j]
						.getAvailableNumbers();
				
				int blockIndex = 0;
				/* TODO - What is the best way to find Block index? */
				if (i < 3) {
					if (j < 3) {
						blockIndex = 0;
					} else if (3 <= j && j < 6) {
						blockIndex = 1;
					} else {
						blockIndex = 2;
					}
				} else if (3 <= i && i < 6) {
					if (j < 3) {
						blockIndex = 3;
					} else if (3 <= j && j < 6) {
						blockIndex = 4;
					} else {
						blockIndex = 5;
					}
				} else {
					if (j < 3) {
						blockIndex = 6;
					} else if (3 <= j && j < 6) {
						blockIndex = 7;
					} else {
						blockIndex = 8;
					}
				}
				
				Set<Integer> availableNumbers_subBoard = m_blocks[blockIndex]
						.getAvailableNumbers();

				for (Integer rowNumb : availableNumbers_row) {
					if (availableNumbers_column.contains(rowNumb)
							&& availableNumbers_subBoard.contains(rowNumb)) {
						/* intersection found */
						availableNumbers.add(rowNumb);
					}
				}

				if ((blockIndex == 1 || blockIndex == 4 || blockIndex == 7)
						&& (i == 1 || i == 4 || i == 7)) {

					/*
					 * If this is the first subBoard, it doesn't matter.
					 * however, if this is the second subBoard, we shouldn't
					 * exhaust all the options for the third subBoard. it all
					 * depends how many numbers from the second subBoard is
					 * taken by the first subBoard
					 */

					List<Integer> mustHave = new ArrayList<Integer>();

					int thirdBlockIndex = 2;
					if (blockIndex == 1) {
						thirdBlockIndex = 2;
					} else if (blockIndex == 2) {
						thirdBlockIndex = 5;
					} else {
						thirdBlockIndex = 8;
					}

					Set<Integer> subBoard3Used = m_blocks[thirdBlockIndex]
							.getUsedNumbers();

					for (int eachUsed3rd : subBoard3Used) {
						if (m_rows[i].getAvailableNumbers().contains(
								eachUsed3rd)) {
							mustHave.add(eachUsed3rd);
						}
					}

					if (j == 0 || j == 3 || j == 6) {
						if (mustHave.size() == 3) {
							availableNumbers = mustHave;
						} else if (mustHave.size() == 2) {

							for (int eachAvail : availableNumbers) {
								if (!mustHave.contains(eachAvail)) {
									mustHave.add(eachAvail);
									break;
								}
							}

						} else if (mustHave.size() == 1) {

							int counter = 0;
							for (int eachAvail : availableNumbers) {
								if (!mustHave.contains(eachAvail)) {
									mustHave.add(eachAvail);
									counter++;

									if (counter == 2) {
										break;
									}
								}
							}
						}
					} else if (j == 1 || j == 4 || j == 7) {// - subBoardColumn
															// * 3 == 1) {
						if (mustHave.size() == 2) {
							availableNumbers = mustHave;
						} else if (mustHave.size() == 1) {
							for (int eachAvail : availableNumbers) {
								if (!mustHave.contains(eachAvail)) {
									mustHave.add(eachAvail);
									break;
								}
							}
						}
					} else if (j == 2 || j == 5 || j == 8) {// - subBoardColumn
															// * 3 == 2) {
						if (mustHave.size() == 1) {
							availableNumbers = mustHave;
						}
					}

				}

				int size = availableNumbers.size(); // 1 ~ 9

				if (size == 0) {
					throw new Exception("We cannot proceed");
				}

				Random rand = new Random();
				int randomPositoin = rand.nextInt(size);

				int suggested = availableNumbers.get(randomPositoin);

				m_grid.getCell(i, j).setNumber(suggested);
			}
		}
	}
}
