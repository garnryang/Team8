package edu.psu.sweng500.team8.play;

import java.util.ArrayDeque;
import java.util.Deque;

import edu.psu.sweng500.team8.coreDataStructures.CellGrid;

public class ActionManager {

	/* If limit the size, we need a custom implementation */
	private Deque<SudokuAction> undoStack = new ArrayDeque<SudokuAction>();
	private Deque<SudokuAction> redoStack = new ArrayDeque<SudokuAction>();

	public void addUndoAction(SudokuAction sudokuAction) {

		/* push sudokuAction only when undoStack is empty OR the top action isn't the same as incoming action */
		if (undoStack.isEmpty() || !undoStack.peek().equals(sudokuAction)) {
			this.undoStack.push(sudokuAction);
		} 
	}
	
	private void addRedoAction(SudokuAction sudokuAction) {

		/* push sudokuAction only when redoStack is empty OR the top action isn't the same as incoming action */
		if (redoStack.isEmpty() || !redoStack.peek().equals(sudokuAction)) {
			this.redoStack.push(sudokuAction);
		}
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
		
		if (!undoStack.isEmpty()) {
			
			SudokuAction lastAction = undoStack.pop(); 
			CellGrid previousCellGrid = lastAction.getCellGrid(); 
			
			SudokuAction undoAction = new SudokuAction(new CellGrid(currentCellGridFromBoard));
			addRedoAction(undoAction);
			
			currentCellGridFromBoard.copyValues(previousCellGrid);
		}
	}

	/**
	 * redo last action reverted back back undo
	 */
	public void doRedo(CellGrid currentCellGridFromBoard) {
		/* redo last action reverted back by undo */
		
		if (!redoStack.isEmpty()) {
			SudokuAction lastActionUndone = redoStack.pop();
			CellGrid previousCellGrid = lastActionUndone.getCellGrid();
			
			SudokuAction redoAction = new SudokuAction(new CellGrid(currentCellGridFromBoard));
			addUndoAction(redoAction);

			currentCellGridFromBoard.copyValues(previousCellGrid);
		}
	}
	
	public boolean hasUndoActions() {
		return !this.undoStack.isEmpty();
	}
	
	public boolean hasRedoActions() {
		return !this.redoStack.isEmpty();
	}
}
