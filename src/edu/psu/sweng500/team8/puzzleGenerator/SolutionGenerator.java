package edu.psu.sweng500.team8.puzzleGenerator;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import edu.psu.sweng500.team8.coreDataStructures.Board;
import edu.psu.sweng500.team8.coreDataStructures.Cell;
import edu.psu.sweng500.team8.coreDataStructures.CellConstraints;
import edu.psu.sweng500.team8.coreDataStructures.CellGrid;
import edu.psu.sweng500.team8.coreDataStructures.Cell.ValueType;

public class SolutionGenerator {
	public static CellGrid generateSolution() {
		// Keep going until we get a valid solution
		Board board = null;
		do { // Average number of cycles over a few thousand runs is ~80.
			board = tryCreateSolution();
		} while (board == null);

		return board.getCellGrid();
	}

	public static Board tryCreateSolution() {
		Board board = new Board();

		for (int rowIndex = 0; rowIndex < 9; rowIndex++) {
			for (int columnIndex = 0; columnIndex < 9; columnIndex++) {
				Integer[] availableNumbers = getAvailableNumbers(board,
						rowIndex, columnIndex);

				int size = availableNumbers.length; // 1 ~ 9
				if (size == 0) {
					return null;
				}

				// Pick a random number from the available numbers
				Random rand = new Random();
				int randomIndex = rand.nextInt(size);
				int suggested = availableNumbers[randomIndex];
				
				//Set the value on the cell and set the type to Given
				Cell currentCell = board.getCell(rowIndex, columnIndex);
				currentCell.setNumber(suggested);
				currentCell.setType(ValueType.Given);
			}
		}

		return board;
	}

	private static Integer[] getAvailableNumbers(Board board, int rowIndex,
			int columnIndex) {
		Cell currentCell = board.getCell(rowIndex, columnIndex);
		CellConstraints constraints = board.getCellConstraints(currentCell);

		Set<Integer> availableNumbers = constraints.getAvailableNumbers();

		int blockIndex = currentCell.getCoordinates().getBlockIndex();

		if ((blockIndex % 3 == 1) && (rowIndex % 3 == 1)) {

			/*
			 * If this is the first block, it doesn't matter. however, if this
			 * is the second block, we shouldn't exhaust all the options for the
			 * third block. it all depends how many numbers from the second
			 * block is taken by the first block
			 */

			// Get the numbers used in block 3 that are available in the current
			// row
			int thirdBlockIndex = blockIndex + 1;

			Set<Integer> subBoard3Used = board.getBlock(thirdBlockIndex)
					.getUsedNumbers();

			Set<Integer> mustHave = new HashSet<Integer>();

			for (int eachUsed3rd : subBoard3Used) {
				if (availableNumbers.contains(eachUsed3rd)) {
					mustHave.add(eachUsed3rd);
				}
			}

			if (columnIndex % 3 == 0) { // First column in the block
				if (mustHave.size() == 3) {
					availableNumbers = mustHave;
				} else if (mustHave.size() == 2) {
					mustHave.addAll(availableNumbers);
				} else if (mustHave.size() == 1) {
					// (JN) I'm a little lost in this part. Why do we break at
					// 2?
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
			} else if (columnIndex % 3 == 1) { // Second column in the block
				if (mustHave.size() == 2) {
					availableNumbers = mustHave;
				} else if (mustHave.size() == 1) {
					// Confused here too...why do we only add one number?
					for (int eachAvail : availableNumbers) {
						if (!mustHave.contains(eachAvail)) {
							mustHave.add(eachAvail);
							break;
						}
					}
				}
			} else { // Third column in the block
				if (mustHave.size() == 1) {
					availableNumbers = mustHave;
				}
			}
		}

		return availableNumbers.toArray(new Integer[0]); // Return it as an
															// array so it can
															// be indexed into
	}
}