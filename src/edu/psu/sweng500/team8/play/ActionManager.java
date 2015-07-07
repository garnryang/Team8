package edu.psu.sweng500.team8.play;

import java.util.Stack;

import edu.psu.sweng500.team8.coreDataStructures.CellGrid;

public class ActionManager {

	/* We can create a properties file to store constant values 
	 * and make it available external to the compiled JAR
	 * so they can be adjusted on the fly. 
	 * Or we can still include it in the JAR but as a separate file
	 * so we can change it through the development process.  */
	private static final int STACK_SIZE_LIMIT = 10;
	
	private Stack<SudokuAction> sudokuActionStack;
	private Stack<SudokuAction> sudokuActionStackForUndo;

	public ActionManager() {
		this.sudokuActionStack = new Stack<SudokuAction>();
		this.sudokuActionStack.setSize(STACK_SIZE_LIMIT);		
		this.sudokuActionStackForUndo = new Stack<SudokuAction>();
		this.sudokuActionStackForUndo.setSize(STACK_SIZE_LIMIT);
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
		
		if (null != sudokuActionStack.peek()) {
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
		
		if (null != sudokuActionStackForUndo.peek()) {
			SudokuAction lastActionUndone = sudokuActionStackForUndo.pop();
			CellGrid previousCellGrid = lastActionUndone.getCellGrid();
			
			SudokuAction redoAction = new SudokuAction(new CellGrid(currentCellGridFromBoard));
			sudokuActionStack.add(redoAction);

			currentCellGridFromBoard.copyValues(previousCellGrid);
		}
	}
}
