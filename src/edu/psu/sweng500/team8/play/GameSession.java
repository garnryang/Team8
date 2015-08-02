package edu.psu.sweng500.team8.play;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import edu.psu.sweng500.team8.coreDataStructures.Board;
import edu.psu.sweng500.team8.coreDataStructures.Cell;
import edu.psu.sweng500.team8.coreDataStructures.CellGrid;
import edu.psu.sweng500.team8.coreDataStructures.Constraint;
import edu.psu.sweng500.team8.coreDataStructures.Puzzle;

public class GameSession implements Serializable {
	private static final long serialVersionUID = 071615;
	private Board board = new Board();
	private ActionManager actionManager = new ActionManager();
	private boolean isPencilMarkMode = false;

	private List<CellChangedListener> cellChangedListeners = new ArrayList<CellChangedListener>();

	public GameSession(Puzzle puzzle, CellGrid overloadCellGrid) {
		this.board.initialize(puzzle, overloadCellGrid);
	}

	public Board getGameBoard() {
		return board;
	}

	public void subscribeForCellChanges(CellChangedListener listener) {
		this.cellChangedListeners.add(listener);
	}

	public void unsubscribeForCellChanges(CellChangedListener listener) {
		this.cellChangedListeners.remove(listener);
	}

	public void enterNumber(Cell currentCell, int number) {

		/* We don't have to clear empty cell */
		if (0 != currentCell.getNumber() || 0 != number) {
			/* keep track of the last action */
			SudokuAction sudokuAction = new SudokuAction(new CellGrid(
					board.getCellGrid()));
			actionManager.addUndoAction(sudokuAction);
			
			if (number == 0) {
				currentCell.clearNumber();
				currentCell.getPencilMarks().clear();
			} else {
				currentCell.setNumber(number);
				updatePencilMark(currentCell, number);
			}			

			// TODO: Unit test this
			fireCellNumberChanged(currentCell, number);
		}
	}

	//FIXME: Clean this up, fix undo/redo, move it to pencil mark manager
	private void updatePencilMark(Cell currentCell, int number) {

		currentCell.getPencilMarks().clear();

		/* Delete PencilMark for the same row/column/block */
		Iterator<Constraint> iterator = this.board.getCellConstraints(currentCell).getIterator();
		while (iterator.hasNext()) {
			Constraint currentConstraint = iterator.next();
			for (Cell eachCell : currentConstraint.getCells()) {
				enterPencilMark(eachCell, number, false);
			}
		}
	}

	public void enterPencilMark(Cell currentCell, int number, boolean isEnter) {

		// Add the action to the undo stack
		SudokuAction sudokuAction = new SudokuAction(new CellGrid(
				board.getCellGrid()));
		//this.actionManager.addAction(sudokuAction);
		
		Set<Integer> pencilMarks = currentCell.getPencilMarks();

		if (0 == number) {
			pencilMarks.clear();
		} else {
			if (isEnter) {
				pencilMarks.add(number);
			} else {
				pencilMarks.remove(number);
			}
		}

		fireCellNumberChanged(currentCell, -1);
	}
	
	public boolean hasUndoActions() {
		return this.actionManager.hasUndoActions();
	}
	
	public boolean hasRedoActions() {
		return this.actionManager.hasRedoActions();
	}

	//FIXME: Why is this called refresh and why is it even here?? Remove it.
	/**
	 * Should we just have a getter instead?
	 * 
	 * @return
	 */
	public Board refresh() {
		return this.board;
	}

	public Object getHelp(HelpType helpType) {
		switch (helpType) {
		case RULES:
			return getRules();
		case ABOUT:
			return getAbout();
		case HINT:
			return getHint();
		}
		return null;
	}

	private Object getRules() {
		return null;
	}

	private Object getAbout() {
		return null;
	}

	private Object getHint() {
		return null;
	}

	/**
	 * saveFile can be name on a designated file or file's path/name
	 * 
	 * @param saveFile
	 */
	public void doSave(String saveFile) {

		/* TODO implement */
	}

	/**
	 * saveFile can be name on a designated file or file's path/name
	 * 
	 * @param saveFile
	 */
	public void doLoad(String saveFile) {
		/* TODO implement */
	}

	public void doRedo() {
		actionManager.doRedo(board.getCellGrid());
	}

	public void doUndo() {
		actionManager.doUndo(board.getCellGrid());
	}

	private void fireCellNumberChanged(Cell cell, int newNumber) {
		for (CellChangedListener listener : this.cellChangedListeners) {
			listener.cellChanged(cell, newNumber);
		}
	}

	//TODO: why is this state necessary? Should only be in the UI
	public void setPencilMarkMode(boolean isPencilMarkMode) {
		this.isPencilMarkMode = isPencilMarkMode;

	}

	public boolean isPencilMarkMode() {
		return this.isPencilMarkMode;
	}
}
