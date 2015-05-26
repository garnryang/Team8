package edu.psu.sweng500.team8.poc.datastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Board {

	private Row[] rows;
	private Column[] columns;
	private SubBoard[][] subBoards;

	public Board() {
		rows = new Row[9];
		columns = new Column[9];

		for (int i = 0; i < 9; i++) {
			rows[i] = new Row(i);
			columns[i] = new Column();
		}

		subBoards = new SubBoard[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				subBoards[i][j] = new SubBoard();
			}
		}
	}
	
	public void populateWithBruteForce() throws Exception {

		int subBoardRow = 0;
		int subBoardColumn = 0;

		// row
		for (int i = 0; i < 9; i++) {
			if (i < 3) {
				subBoardRow = 0;
			} else if (3 <= i && i < 6) {
				subBoardRow = 1;
			} else {
				subBoardRow = 2;
			}

			// column

			for (int j = 0; j < 9; j++) {

				if (j < 3) {
					subBoardColumn = 0;
				} else if (3 <= j && j < 6) {
					subBoardColumn = 1;
				} else {
					subBoardColumn = 2;
				}

				Cell cell = new Cell();

				List<Integer> availableNumbers = new ArrayList<Integer>();

				List<Integer> availableNumbers_row = rows[i]
						.getAvailableNumbers();
				List<Integer> availableNumbers_column = columns[j]
						.getAvailableNumbers();
				List<Integer> availableNumbers_subBoard = subBoards[subBoardRow][subBoardColumn]
						.getAvailableNumbers();

				for (Integer rowNumb : availableNumbers_row) {
					if (availableNumbers_column.contains(rowNumb)
							&& availableNumbers_subBoard.contains(rowNumb)) {
						/* intersection found */
						availableNumbers.add(rowNumb);
					}
				}

				int size = availableNumbers.size(); // 1 ~ 9

				if (size == 0) {
					throw new Exception("Didn't work!");
				}

				Random rand = new Random();
				int randomPositoin = rand.nextInt(size);

				int suggested = availableNumbers.get(randomPositoin);

				cell.setValue(suggested);

				rows[i].addCell(cell);
				columns[j].addCell(cell);
				subBoards[subBoardRow][subBoardColumn].addCell(cell, i
						- subBoardRow * 3, j - subBoardColumn * 3);
			}
		}
	}

	public void populateWithSlightLogic() throws Exception {

		int subBoardRow = 0;
		int subBoardColumn = 0;

		Cell lastCell = null;
		Row lastRow = null;
		Column lastColumn = null;
		SubBoard lastSubBoard = null;

		int j = 0;

		// row
		for (int i = 0; i < 9; i++) {
			if (i < 3) {
				subBoardRow = 0;
			} else if (3 <= i && i < 6) {
				subBoardRow = 1;
			} else {
				subBoardRow = 2;
			}

			// column

			for (; j < 9; j++) {

				if (j < 3) {
					subBoardColumn = 0;
				} else if (3 <= j && j < 6) {
					subBoardColumn = 1;
				} else {
					subBoardColumn = 2;
				}

				Cell cell = new Cell();

				List<Integer> availableNumbers = new ArrayList<Integer>();

				List<Integer> availableNumbers_row = rows[i]
						.getAvailableNumbers();
				List<Integer> availableNumbers_column = columns[j]
						.getAvailableNumbers();
				List<Integer> availableNumbers_subBoard = subBoards[subBoardRow][subBoardColumn]
						.getAvailableNumbers();

				for (Integer rowNumb : availableNumbers_row) {
					if (availableNumbers_column.contains(rowNumb)
							&& availableNumbers_subBoard.contains(rowNumb)) {
						/* intersection found */
						availableNumbers.add(rowNumb);
					}
				}

				if (subBoardColumn == 1 && i - subBoardRow * 3 == 1) {

					/*
					 * If this is the first subBoard, it doesn't matter.
					 * however, if this is the second subBoard, we shouldn't
					 * exhaust all the options for the third subBoard. it all
					 * depends how many numbers from the second subBoard is
					 * taken by the first subBoard
					 */

					List<Integer> mustHave = new ArrayList<Integer>();
					Map<Integer, Boolean> subBoard3Used = subBoards[subBoardRow][2]
							.getUsedNumbers();
					for (Map.Entry<Integer, Boolean> eachUsed3rd : subBoard3Used
							.entrySet()) {
						if (Boolean.TRUE.equals(eachUsed3rd.getValue())
								&& rows[i].getAvailableNumbers().contains(
										eachUsed3rd.getKey())) {
							mustHave.add(eachUsed3rd.getKey());
						}
					}

					if (j - subBoardColumn * 3 == 0) {
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
					} else if (j - subBoardColumn * 3 == 1) {
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
					} else if (j - subBoardColumn * 3 == 2) {
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

				cell.setValue(suggested);

				rows[i].addCell(cell);
				columns[j].addCell(cell);
				subBoards[subBoardRow][subBoardColumn].addCell(cell, i
						- subBoardRow * 3, j - subBoardColumn * 3);

				cell.previousCell = lastCell;
				lastCell = cell;

				rows[i].previousRow = lastRow;
				lastRow = rows[i];

				columns[j].previousColumn = lastColumn;
				lastColumn = columns[j];

				lastSubBoard = subBoards[subBoardRow][subBoardColumn];
			}

			j = 0;
		}
	}

	public void populateWithSlightlyMoreLogic() {

		int subBoardRow = 0;
		int subBoardColumn = 0;

		Cell lastCell = null;
		Row lastRow = null;
		Column lastColumn = null;
		SubBoard lastSubBoard = null;

		int j = 0;

		// row
		for (int i = 0; i < 9; i++) {
			if (i < 3) {
				subBoardRow = 0;
			} else if (3 <= i && i < 6) {
				subBoardRow = 1;
			} else {
				subBoardRow = 2;
			}

			// column

			for (; j < 9; j++) {

				if (j < 3) {
					subBoardColumn = 0;
				} else if (3 <= j && j < 6) {
					subBoardColumn = 1;
				} else {
					subBoardColumn = 2;
				}

				Cell cell = new Cell();

				List<Integer> availableNumbers = new ArrayList<Integer>();

				List<Integer> availableNumbers_row = rows[i]
						.getAvailableNumbers();
				List<Integer> availableNumbers_column = columns[j]
						.getAvailableNumbers();
				List<Integer> availableNumbers_subBoard = subBoards[subBoardRow][subBoardColumn]
						.getAvailableNumbers();

				for (Integer rowNumb : availableNumbers_row) {
					if (availableNumbers_column.contains(rowNumb)
							&& availableNumbers_subBoard.contains(rowNumb)) {
						/* intersection found */
						availableNumbers.add(rowNumb);
					}
				}

				if (subBoardColumn == 1 && i - subBoardRow * 3 == 1) {

					/*
					 * If this is the first subBoard, it doesn't matter.
					 * however, if this is the second subBoard, we shouldn't
					 * exhaust all the options for the third subBoard. it all
					 * depends how many numbers from the second subBoard is
					 * taken by the first subBoard
					 */

					List<Integer> mustHave = new ArrayList<Integer>();
					Map<Integer, Boolean> subBoard3Used = subBoards[subBoardRow][2]
							.getUsedNumbers();
					for (Map.Entry<Integer, Boolean> eachUsed3rd : subBoard3Used
							.entrySet()) {
						if (Boolean.TRUE.equals(eachUsed3rd.getValue())
								&& rows[i].getAvailableNumbers().contains(
										eachUsed3rd.getKey())) {
							mustHave.add(eachUsed3rd.getKey());
						}
					}

					if (j - subBoardColumn * 3 == 0) {
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
					} else if (j - subBoardColumn * 3 == 1) {
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
					} else if (j - subBoardColumn * 3 == 2) {
						if (mustHave.size() == 1) {
							availableNumbers = mustHave;
						}
					}

				} else {
				}

				List<Integer> finalList = new ArrayList<Integer>();
				for (int availNumb : availableNumbers) {

					if (lastRow == null || lastRow.failedNumb.get(j) == null
							|| lastRow.failedNumb.get(j).isEmpty()) {
						finalList = availableNumbers;
						break;
						// finalList.add(availNumb);
					} else if (lastRow.failedNumb.get(j) != null
							&& !lastRow.failedNumb.get(j).contains(availNumb)) {
						finalList.add(availNumb);
					}
				}

				int size = finalList.size(); // 1 ~ 9

				if (size == 0) {
					/* the previous work(s) has gone wrong! */
					lastCell.getPreviousFailed().add(lastCell.getValue());
					// lastCell.setValue(0);

					List<Integer> failedList = lastRow.failedNumb.get(j - 1);
					if (failedList == null) {
						failedList = new ArrayList<Integer>();
					}

					failedList.add(lastCell.getValue());
					lastRow.failedNumb.put(j - 1, failedList);

					lastRow.removeLastCell();
					lastColumn.removeLastCell();
					lastSubBoard.removeLastCell();

					/* current column */
					if (j > 0) {

						if (i == lastRow.rowNumb) {

						}

						int t = 9;
						Map<Integer, List<Integer>> theMap = lastRow.failedNumb;
						for (int lowest : theMap.keySet()) {
							if (lowest < t) {
								t = lowest;
							}
						}

						if (j - 1 - failedList.size() < t - 1) {
							j = j - 1 - failedList.size();
						} else {
							j = t - 1;
						}
						if (j <= 0) {
							i = i - 2;
							j = 8;
							break;
						}

						continue;
					} else if (j == 0) {
						i = i - 2;
						j = 8;
						break;
					}
				}

				Random rand = new Random();
				int randomPositoin = rand.nextInt(size);

				int suggested = finalList.get(randomPositoin);

				cell.setValue(suggested);

				rows[i].addCell(cell);
				columns[j].addCell(cell);
				subBoards[subBoardRow][subBoardColumn].addCell(cell, i
						- subBoardRow * 3, j - subBoardColumn * 3);

				cell.previousCell = lastCell;
				lastCell = cell;

				rows[i].previousRow = lastRow;
				lastRow = rows[i];

				columns[j].previousColumn = lastColumn;
				lastColumn = columns[j];

				lastSubBoard = subBoards[subBoardRow][subBoardColumn];
			}

			j = 0;
		}
	}

	public void display() {
		for (int i = 0; i < 9; i++) {
			Row row = rows[i];
			for (int j = 0; j < 9; j++) {
				System.out.print(row.getCells()[j].getValue());
				System.out.print("_");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {

		Board board = null;

		long startTime = System.currentTimeMillis();
		boolean keepGoing = true;
		int counter = 0;

		while (keepGoing && counter < 100000)
			try {
				board = new Board();
				board.populateWithBruteForce();
				keepGoing = false;
			} catch (Exception e) {
				counter++;
			}
		long endTime = System.currentTimeMillis();

		System.out.println("Brute Force Mk. 1");
		System.out.println("Counter:" + counter);
		System.out.println(endTime - startTime + " ms");

		board.display();

		// ==
		startTime = System.currentTimeMillis();
		keepGoing = true;
		counter = 0;

		while (keepGoing && counter < 100000)
			try {
				board = new Board();
				board.populateWithSlightLogic();
				keepGoing = false;
			} catch (Exception e) {
				counter++;
			}
		endTime = System.currentTimeMillis();

		System.out.println("Brute Force Mk. 2");
		System.out.println("Counter:" + counter);
		System.out.println(endTime - startTime + " ms");

		board.display();
		
		
		// ==
		startTime = System.currentTimeMillis();
		keepGoing = true;
		counter = 0;

		while (keepGoing && counter < 100000)
			try {
				board = new Board();
				board.populateWithSlightlyMoreLogic();
				keepGoing = false;
			} catch (Exception e) {
				counter++;
			}
		endTime = System.currentTimeMillis();

		System.out.println("Brute Force Mk. 3");
		System.out.println("Counter:" + counter);
		System.out.println(endTime - startTime + " ms");

		board.display();

	}
}
