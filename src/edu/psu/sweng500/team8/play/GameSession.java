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

	public GameSession(Puzzle puzzle) {
		this.board.initialize(puzzle);
	}
	
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

	private void updatePencilMark(Cell currentCell, int number) {

		currentCell.getPencilMarks().clear();

		/* Delete PencilMark for the same row/column/block */
		Iterator<Constraint> iterator = this.board.getCellConstraints(currentCell).getIterator();
		while (iterator.hasNext()) {
			Constraint currentConstraint = iterator.next();
			for (Cell eachCell : currentConstraint.getCells()) {
				updatePencilMark(eachCell, number, false);
			}
		}
	}

	/**
	 * Used for all the cases where Pencil Mark should be updated not directly by PencilMark input by user 
	 * @param currentCell
	 * @param number
	 * @param isEnter
	 */
	public void updatePencilMark(Cell currentCell, int number, boolean isEnter) {

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
	}
	
	/**
	 * Used for directly entering PencilMark 
	 * @param currentCell
	 * @param number
	 * @param isEnter
	 */
	public void enterPencilMark(Cell currentCell, int number, boolean isEnter) {

		SudokuAction sudokuAction = new SudokuAction(new CellGrid(
				board.getCellGrid()));
		this.actionManager.addUndoAction(sudokuAction);
		
		updatePencilMark(currentCell, number, isEnter);

		firePencilMarksChanged(currentCell, currentCell.getPencilMarks());
	}
	
	public boolean hasUndoActions() {
		return this.actionManager.hasUndoActions();
	}
	
	public boolean hasRedoActions() {
		return this.actionManager.hasRedoActions();
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
	
	private void firePencilMarksChanged(Cell cell, Set<Integer> newPencilMarks) {
		for (CellChangedListener listener : this.cellChangedListeners) {
			listener.pencilMarksChanged(cell, newPencilMarks);
		}
	}

	/**
	 * We need this here because we are using it for numberButtonGUI's behavior change
	 * as well as undo/redo logic 
	 * We used to use this for Key-entering-interaction
	 * @param isPencilMarkMode
	 */
	public void setPencilMarkMode(boolean isPencilMarkMode) {
		this.isPencilMarkMode = isPencilMarkMode;

	}

	public boolean isPencilMarkMode() {
		return this.isPencilMarkMode;
	}
}
