package PuzzleGenerator;

import java.util.Date;
import java.util.Random;
import java.util.Set;

import CoreDataStructures.Board;
import CoreDataStructures.CellConstraints;
import CoreDataStructures.CellGrid;
import CoreDataStructures.Helpers;

public class SolutionGenerator {
	public static void main(String[] args) {
		generateSolutions(1000);
	}

	public static CellGrid[] generateSolutions(int numSolutions) {
		CellGrid[] solutions = new CellGrid[numSolutions];
		
		Date startTime = new Date();
		for (int solutionIndex = 0; solutionIndex < numSolutions; solutionIndex++) {
			
			//Keep going until we get a valid solution
			Board board = null;
			do { //Average number of cycles over a few thousand runs is ~80. 
				board = tryCreateSolution();
			} while(board == null);
			
			solutions[solutionIndex] = board.getCellGrid();
		}
		
		Date endTime = new Date();
		long totalTimeMS = endTime.getTime() - startTime.getTime();
		double avgTime = totalTimeMS / (double) numSolutions;
		
		System.out.println("Total Time for " + numSolutions + " solutions: " + totalTimeMS);
		System.out.println("Average Time: " + avgTime);
		
		return solutions;
	}
	
	private static Board tryCreateSolution() {
		Board board = new Board();
		
		for (int rowIndex = 0; rowIndex < 9; rowIndex++) {
			for (int columnIndex = 0; columnIndex < 9; columnIndex++) {
				Integer[] availableNumbers = getAvailableNumbers(board, rowIndex, columnIndex);

				int size = availableNumbers.length; // 1 ~ 9
				if (size == 0) {
					return null;
				}

				//Pick a random number from the available numbers
				Random rand = new Random();
				int randomIndex = rand.nextInt(size);

				int suggested = availableNumbers[randomIndex];
				board.getCell(rowIndex, columnIndex).setNumber(suggested);
			}
		}
		
		return board;
	}

	private static Integer[] getAvailableNumbers(Board board, int rowIndex, int columnIndex) {
		CellConstraints constraints = board.getCellConstraints(rowIndex, columnIndex);

		Set<Integer> availableNumbers = constraints.getAvailableNumbers();

		int blockIndex = Helpers.getBlockIndex(rowIndex, columnIndex);
		if ((blockIndex % 3 == 1) && (rowIndex % 3 == 1)) {

			/*
			 * If this is the first block, it doesn't matter.
			 * however, if this is the second block, we shouldn't
			 * exhaust all the options for the third block. it all
			 * depends how many numbers from the second block is
			 * taken by the first block
			 */
			
			//Get the numbers used in block 3 that are available in the current row
			int thirdBlockIndex = blockIndex + 1;
			Set<Integer> mustHave = board.getBlock(thirdBlockIndex).getUsedNumbers();
			Set<Integer> currentRowAvailableNumbers = board.getRow(rowIndex).getAvailableNumbers();
			mustHave.retainAll(currentRowAvailableNumbers);

			if (columnIndex % 3 == 0) { //First column in the block
				if (mustHave.size() == 3) {
					availableNumbers = mustHave; 
				} else if (mustHave.size() == 2) {
					mustHave.addAll(availableNumbers);
				} else if (mustHave.size() == 1) {
					//(JN) I'm a little lost in this part. Why do we break at 2?
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
			} else if (columnIndex % 3 == 1) { //Second column in the block
				if (mustHave.size() == 2) {
					availableNumbers = mustHave;
				} else if (mustHave.size() == 1) {
					//Confused here too...why do we only add one number?
					for (int eachAvail : availableNumbers) {
						if (!mustHave.contains(eachAvail)) {
							mustHave.add(eachAvail);
							break;
						}
					}
				}
			} else { //Third column in the block 
				if (mustHave.size() == 1) {
					availableNumbers = mustHave;
				}
			}
		}

		return availableNumbers.toArray(new Integer[0]); //Return it as an array so it can be indexed into
	}
}