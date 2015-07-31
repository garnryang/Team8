package edu.psu.sweng500.team8.play;

import java.util.ArrayDeque;
import java.util.Deque;

import edu.psu.sweng500.team8.coreDataStructures.CellGrid;

public class ActionManager {

	/* If limit the size, we need a custom implementation */
	private Deque<SudokuAction> undoStack = new ArrayDeque<SudokuAction>();
	private Deque<SudokuAction> redoStack = new ArrayDeque<SudokuAction>();

	public void addUndoAction(SudokuAction sudokuAction) {
		
		if (!undoStack.isEmpty()) {
			if (undoStack.peek().equals(sudokuAction)) {
				/* duplicate call due to cell change listener isseu */
				/* do not push */
			} else {
				this.undoStack.push(sudokuAction);	
			}
		} else {
			this.undoStack.push(sudokuAction);	
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
			redoStack.push(undoAction);
			
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
			undoStack.push(redoAction);

			currentCellGridFromBoard.copyValues(previousCellGrid);
		}
	}
	
	//TODO: Unit test
	public boolean hasUndoActions() {
		return !this.undoStack.isEmpty();
	}
	
	//TODO: Unit test
	public boolean hasRedoActions() {
		return !this.redoStack.isEmpty();
	}
}
