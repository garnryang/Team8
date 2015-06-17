package edu.psu.sweng500.team8.play;

import java.util.Stack;

import edu.psu.sweng500.team8.coreDataStructures.Board;
import edu.psu.sweng500.team8.coreDataStructures.Cell;
import edu.psu.sweng500.team8.coreDataStructures.CellCoordinates;
import edu.psu.sweng500.team8.coreDataStructures.CellGrid;
import edu.psu.sweng500.team8.coreDataStructures.PencilMarkManager;
import edu.psu.sweng500.team8.coreDataStructures.Puzzle;
import edu.psu.sweng500.team8.coreDataStructures.Puzzle.DifficultyLevel;
import edu.psu.sweng500.team8.puzzleGenerator.PuzzleGenerator;
import edu.psu.sweng500.team8.puzzleGenerator.SolutionGenerator;

/**
 * 
 * If we are going to use Board class for operations, this class is not needed and we can move operations from this class to Board class
 * How are we going to response back to UI when redo/undo happens? Are we going to simply update board/grid and let UI call refresh?
 */
public class GameSession {
	
	private Board board = new Board();
	/* TODO - Do we want to have a player class? */
	// private Player player;
	
	private Stack<SudokuAction> sudokuActionQueue = new Stack<SudokuAction>();
	private Stack<SudokuAction> sudokuActionQueueForUndo = new Stack<SudokuAction>();
	
	public GameSession(DifficultyLevel difficulty) {
		CellGrid solution = SolutionGenerator.generateSolutions(1)[00];
		Puzzle puzzle = PuzzleGenerator.makePuzzle(solution, difficulty);
		this.board = new Board();
		board.Initialize(puzzle);
	}
	
	//DEPRECATED. TODO: Remove
	public GameSession(Board board) {
		this.board = board;
		this.sudokuActionQueue = new Stack<SudokuAction>();
		this.sudokuActionQueueForUndo = new Stack<SudokuAction>();
	}
	
	public Board getGameBoard() {
		return board;
	}
	
	/**
	 * Undo last action such as entering a number, deleting a number or redo
	 * 
	 */
	public void doUndo() {
		/* revert last action on the sudokuActionQueue, and put that action into sudokuActionQueueForUndo */
		if (!sudokuActionQueue.isEmpty()) {
			SudokuAction lastAction = sudokuActionQueue.pop();
		
			PencilMarkManager previousPencilMarkManager = lastAction.getPencilMarkManager();
			/* TODO - we need to get the current Pencil Mark state */
			PencilMarkManager currentPencilMarkManager = null;
			lastAction.setPencilMarkManager(currentPencilMarkManager);
			sudokuActionQueueForUndo.add(lastAction);
						
			Cell currentCell = board.getCell(lastAction.getCellCordinates().getRowIndex(), lastAction.getCellCordinates().getColumnIndex());
			if (0 == lastAction.getPreviousValue()) {
				currentCell.clearNumber();
			} else {
				/* TODO Is this how we are going to update value on a cell? */ 
				currentCell.setNumber(lastAction.getPreviousValue());
			}
			
			/* TODO -  use previousPencilMarkManager to go back to the PencilMark state
			 * before the action */
			/*TODO - implement pencil mark undo using previousPencilMarkManager */
		} 
	}
	
	/** 
	 * redo last action reverted back back undo  */
	public void doRedo() {
		/* redo last action reverted back by undo */
		if (!sudokuActionQueueForUndo.isEmpty()) {
			SudokuAction lastActionUndone = sudokuActionQueueForUndo.pop();
			
			PencilMarkManager previousPencilMarkManager = lastActionUndone.getPencilMarkManager();
			/* TODO - we need to get the current Pencil Mark state */
			PencilMarkManager currentPencilMarkManager = null;
			lastActionUndone.setPencilMarkManager(currentPencilMarkManager);
			
			sudokuActionQueue.add(lastActionUndone);
			
			Cell currentCell = board.getCell(lastActionUndone.getCellCordinates().getRowIndex(), lastActionUndone.getCellCordinates().getColumnIndex());
			if (0 == lastActionUndone.getNewValue()) {
				currentCell.clearNumber();
			} else {
				/* TODO How are we going to handle pencil mark updates? */
				currentCell.setNumber(lastActionUndone.getNewValue());
			}
			
			/* TODO -  use previousPencilMarkManager to go back to the PencilMark state
			 * before the action */
			/*TODO - implement pencil mark undo using previousPencilMarkManager */
		}	
	}
	
	/**
	 * Enter a number to given cellCoordinates
	 * If we do basic validation before calling this method, number can be an Integer
	 * If we do basic validation along with other more detailed validations on this method or forward,
	 * number should be a String.
	 * For different validation violations, how do we want to communicate to the caller?
	 * We can have, 
	 * invalid coordinate exception (which can only happen during the development where we put a number into hard-coded cell
	 * invalid value exception (if non-numeric value is entered or anything less than or greater than 1-9
	 *
	 * how do we want to communicate violation of sudoku rule?
	 * do we want to do that through a returned object?
	 * 
	 * If we want to use return object, we maybe able to use Enum to show previously defined exception cases.
	 * 
	 *
	 * @param cellCoordinates
	 * @param number
	 */
	public void enterNumber(CellCoordinates cellCoordinates, int number) {
		
		Cell currentCell = board.getCell(cellCoordinates.getRowIndex(), cellCoordinates.getColumnIndex());
		
		/* TODO - come back after PencilMarkMaanger is implement
		 * current assumption is that PecilMarkManager will be a snapshot of the current pencil mark status 
		 * When we do UNDO, we just will have to go back to the state
		 * When we do REDO, we just will have to go back to the state we are in AFTER the action */
		PencilMarkManager currentPencilMarkManager = null;
		SudokuAction sudokuAction = new SudokuAction(cellCoordinates, currentCell.getNumber(), number, currentPencilMarkManager);
		
		
		/* TODO Actual Board Update to be implemented 
		 * following is just simple number setting */
		/* TODO We need to implement pencil mark removal upon a number entered */
		currentCell.setNumber(number);
		
		sudokuActionQueue.add(sudokuAction);
		
		/* TODO - We need to have PencilMarkManager update here, and we */
	}
	
	/**
	 * Should we just have a getter instead?
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
	 * saveFile can be name on a designated file
	 * or file's path/name  
	 * @param saveFile
	 */
	public void doSave(String saveFile) {
		
		/* TODO implement */
	}
	
	/**
	 * saveFile can be name on a designated file
	 * or file's path/name
	 * @param saveFile
	 */
	public void doLoad(String saveFile) {
		/* TODO implement */
	}
}
