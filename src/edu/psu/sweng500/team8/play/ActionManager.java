package edu.psu.sweng500.team8.play;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;

import edu.psu.sweng500.team8.coreDataStructures.CellGrid;

public class ActionManager  implements Serializable {

	/* If limit the size, we need a custom implementation */
	private Deque<SudokuAction> sudokuActionStack;
	private Deque<SudokuAction> sudokuActionStackForUndo;

	public ActionManager() {
		this.sudokuActionStack = new ArrayDeque<SudokuAction>();
		this.sudokuActionStackForUndo = new ArrayDeque<SudokuAction>();
	}

	public void addAction(SudokuAction sudokuAction) {
		this.sudokuActionStack.add(sudokuAction);
	}

	/**
	 * Undo last action such as entering a number, deleting a number or redo
	 * 
	 */
	public void doUndo(CellGrid currentCellGridFromBoard) {
		/*
		 * revert last action on the sudokuActionStack, and put that action into
		 * sudokuActionStackForUndo
		 */
		
		if (!sudokuActionStack.isEmpty()) {
			SudokuAction lastAction = sudokuActionStack.pop(); 
			CellGrid previousCellGrid = lastAction.getCellGrid(); 
			
			SudokuAction undoAction = new SudokuAction(new CellGrid(currentCellGridFromBoard));
			sudokuActionStackForUndo.add(undoAction);
			
			currentCellGridFromBoard.copyValues(previousCellGrid);
		}
	}

	/**
	 * redo last action reverted back back undo
	 */
	public void doRedo(CellGrid currentCellGridFromBoard) {
		/* redo last action reverted back by undo */
		
		if (!sudokuActionStackForUndo.isEmpty()) {
			SudokuAction lastActionUndone = sudokuActionStackForUndo.pop();
			CellGrid previousCellGrid = lastActionUndone.getCellGrid();
			
			SudokuAction redoAction = new SudokuAction(new CellGrid(currentCellGridFromBoard));
			sudokuActionStack.add(redoAction);

			currentCellGridFromBoard.copyValues(previousCellGrid);
		}
	}
}
