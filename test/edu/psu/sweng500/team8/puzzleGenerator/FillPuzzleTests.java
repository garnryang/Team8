package edu.psu.sweng500.team8.puzzleGenerator;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;
import javax.swing.JToggleButton;

import org.junit.Assert;
import org.junit.Test;

import edu.psu.sweng500.team8.coreDataStructures.Cell;
import edu.psu.sweng500.team8.coreDataStructures.CellGrid;
import edu.psu.sweng500.team8.coreDataStructures.Puzzle;
import edu.psu.sweng500.team8.coreDataStructures.Puzzle.DifficultyLevel;
import edu.psu.sweng500.team8.gui.BoardGUI;
import edu.psu.sweng500.team8.gui.CellGUI;
import edu.psu.sweng500.team8.gui.GridPanel;
import edu.psu.sweng500.team8.gui.NumberButtonGUI;
import edu.psu.sweng500.team8.play.GameSession;

/**
 * FIXME - GridPanel is deprecated
 * @author J
 *
 */
public class FillPuzzleTests {

	@Test
	// UC3 Steps 1&2&3
	public void playerEntersNumber() {

		NumberButtonGUI numberButtonGUI = new NumberButtonGUI();
		
		DifficultyLevel difficulty = DifficultyLevel.Easy;
		PuzzleRepository puzzleRepo = new PuzzleRepository(); 
		Puzzle puzzle = puzzleRepo.getPuzzle(difficulty);
		GameSession newGame = new GameSession(puzzle, null);
		
		final BoardGUI gridPanel = new BoardGUI();
		
		numberButtonGUI.init(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent mouseEvent) {
				
				gridPanel.mouseClickedTaskForNumberInput(mouseEvent);
			}
		}, newGame);
		
		gridPanel.populatePanel(newGame, false, false, numberButtonGUI);

		CellGrid gridLogic = newGame.getGameBoard().getCellGrid();

		int row = -1;
		int column = -1;

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int test = gridLogic.getCell(i, j).getNumber();

				if (test == 0) {
					row = i;
					column = j;
					break;
				}
				
				if (test == 0) {
					break;
				}
			}
		}

		Assert.assertNotEquals(-1, row);
		Assert.assertNotEquals(-1, column);
		Assert.assertEquals(0, gridLogic.getCell(row, column).getNumber());
		
		CellGUI cellGUI = gridPanel.findCorresdpondingCellGUI(new Cell(row, column));
		FocusEvent focusEvent = new FocusEvent(cellGUI.getNumberInputField(), FocusEvent.FOCUS_GAINED);
		FocusListener[] focusListeners = cellGUI.getNumberInputField().getFocusListeners();
		focusListeners[2].focusGained(focusEvent); // manually found out 
		
		
		JToggleButton numberNineButton = (JToggleButton)numberButtonGUI.getComponent(8);
		MouseEvent mouseEvent = new MouseEvent(numberNineButton, MouseEvent.MOUSE_RELEASED, 0l, 0, 0, 0, 1, false);
		MouseListener[] mouseListeners = numberNineButton.getMouseListeners();
		numberNineButton.setSelected(true);
		mouseListeners[1].mouseReleased(mouseEvent);
		
		Assert.assertEquals(9, gridLogic.getCell(row, column).getNumber());
	}

	@Test
	// UC3 Step 4
	public void systemClearsAnyPenciledinValuesInTheSquare() {
		
		NumberButtonGUI numberButtonGUI = new NumberButtonGUI();
		
		DifficultyLevel difficulty = DifficultyLevel.Easy;
		PuzzleRepository puzzleRepo = new PuzzleRepository(); 
		Puzzle puzzle = puzzleRepo.getPuzzle(difficulty);
		GameSession newGame = new GameSession(puzzle, null);
		final BoardGUI gridPanel = new BoardGUI();
		
		numberButtonGUI.init(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent mouseEvent) {
				
				gridPanel.mouseClickedTaskForNumberInput(mouseEvent);
			}
		}, newGame);
		
		gridPanel.populatePanel(newGame, false, false, numberButtonGUI);

		CellGrid gridLogic = newGame.getGameBoard().getCellGrid();

		int row = -1;
		int column = -1;

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int test = gridLogic.getCell(i, j).getNumber();

				if (test == 0) {
					row = i;
					column = j;
					break;
				}
				
				if (test == 0) {
					break;
				}
			}
		}

		Assert.assertNotEquals(-1, row);
		Assert.assertNotEquals(-1, column);
		
		Cell cell = gridLogic.getCell(row, column);
		cell.getPencilMarks().add(9);
		
		CellGUI cellGUI = gridPanel.findCorresdpondingCellGUI(new Cell(row, column));
		FocusEvent focusEvent = new FocusEvent(cellGUI.getNumberInputField(), FocusEvent.FOCUS_GAINED);
		FocusListener[] focusListeners = cellGUI.getNumberInputField().getFocusListeners();
		focusListeners[2].focusGained(focusEvent); // manually found out 
		
		JToggleButton numberNineButton = (JToggleButton)numberButtonGUI.getComponent(8);
		MouseEvent mouseEvent = new MouseEvent(numberNineButton, MouseEvent.MOUSE_RELEASED, 0l, 0, 0, 0, 1, false);
		MouseListener[] mouseListeners = numberNineButton.getMouseListeners();
		numberNineButton.setSelected(true);
		mouseListeners[1].mouseReleased(mouseEvent);
		
		Assert.assertTrue(gridLogic.getCell(row, column).getPencilMarks().isEmpty());
	}

	/**
	 * FIXME I don't know what this is trying to do...
	 */
	@Test
	// UC3 Step 5
	public void systemClearsAnyPenciledinValuesFromRowColumnBoxes() {
		
		DifficultyLevel difficulty = DifficultyLevel.Easy;
		PuzzleRepository puzzleRepo = new PuzzleRepository(); 
		Puzzle puzzle = puzzleRepo.getPuzzle(difficulty);
		GameSession newGame = new GameSession(puzzle, null);
		GridPanel gridPanel = new GridPanel();
		gridPanel.populatePanel(newGame.getGameBoard().getCellGrid(), newGame);

		CellGrid gridLogic = gridPanel.getGameSession().getGameBoard()
				.getCellGrid();
		JTextField[][] gridGUI = gridPanel.getControlGrid();

		int row = -1;
		int column = -1;

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int test = gridLogic.getCell(i, j).getNumber();

				if (test == 0) {
					row = i;
					column = j;
					break;
				}
				
				if (test == 0) {
					break;
				}
			}
		}

		Assert.assertNotEquals(-1, row);
		Assert.assertNotEquals(-1, column);
		Assert.assertEquals(0, gridLogic.getCell(row, column).getNumber());

		JTextField cellGUI = gridGUI[row][column];
		
		
		/* FIXME - We need UI way of entering Pencil Marks 
		 * Comeback and modify this once UI Pencil Mark is done */
		/* put pencil marks on all cells with 9 numbers */
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				Cell currentCell = gridLogic.getCell(i, j);
				
				for (int k = 1; k <= 9; k++) {
					newGame.enterPencilMark(currentCell, k, true);	
				}
			}
		}
	
		final int designatedNumber = 9;
		
		/* verify pencil mark contains the designatedNumber */
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				Cell currentCell = gridLogic.getCell(i, j);
				Assert.assertTrue(currentCell.getPencilMarks().contains(designatedNumber));
			}
		}
		
		/**/
		KeyListener[] keyListeners = cellGUI.getKeyListeners();
		KeyEvent ke = new KeyEvent (cellGUI, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_9, '9');//(char) KeyEvent.VK_9);
		keyListeners[0].keyTyped(ke);
		
		
		/* verify pencil mark doesn't not contain designatedNumber */
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				Cell currentCell = gridLogic.getCell(i, j);
				Assert.assertFalse(currentCell.getPencilMarks().contains(designatedNumber));
			}
		}
	}
}
