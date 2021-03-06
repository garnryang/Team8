package edu.psu.sweng500.team8.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JToggleButton;

import org.junit.Assert;
import org.junit.Test;

import edu.psu.sweng500.team8.coreDataStructures.Cell;
import edu.psu.sweng500.team8.coreDataStructures.CellGrid;
import edu.psu.sweng500.team8.coreDataStructures.Puzzle;
import edu.psu.sweng500.team8.coreDataStructures.Puzzle.DifficultyLevel;
import edu.psu.sweng500.team8.play.GameSession;
import edu.psu.sweng500.team8.puzzleGenerator.PuzzleRepository;

public class FillPuzzleTests {

	@Test
	// UC3 Steps 1&2&3
	public void playerEntersNumber() {
		NumberButtonGUI numberButtonGUI = new NumberButtonGUI();
		
		DifficultyLevel difficulty = DifficultyLevel.Easy;
		PuzzleRepository puzzleRepo = new PuzzleRepository();
		Puzzle puzzle = puzzleRepo.getPuzzle(difficulty);
		GameSession newGame = new GameSession(puzzle);
		final BoardGUI gridPanel = new BoardGUI();
		numberButtonGUI.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent mouseEvent) {

				gridPanel.mouseClickedTaskForNumberInput(mouseEvent);
			}
		});
		numberButtonGUI.init(newGame);

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

		CellGUI cellGUI = gridPanel.findCorresdpondingCellGUI(new Cell(row,
				column));
		MouseEvent mouseEvent = new MouseEvent(cellGUI.getNumberInputField(),
				MouseEvent.MOUSE_RELEASED, 0l, 0, 0, 0, 1, false);
		MouseListener[] mouseListeners = cellGUI.getNumberInputField().getMouseListeners();
		mouseListeners[3].mouseReleased(mouseEvent);

		JToggleButton numberNineButton = (JToggleButton) numberButtonGUI
				.getComponent(8);
		mouseEvent = new MouseEvent(numberNineButton,
				MouseEvent.MOUSE_RELEASED, 0l, 0, 0, 0, 1, false);
		mouseListeners = numberNineButton.getMouseListeners();
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
		GameSession newGame = new GameSession(puzzle);
		final BoardGUI gridPanel = new BoardGUI();

		numberButtonGUI.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent mouseEvent) {

				gridPanel.mouseClickedTaskForNumberInput(mouseEvent);
			}
		});
		numberButtonGUI.init(newGame);

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

		CellGUI cellGUI = gridPanel.findCorresdpondingCellGUI(new Cell(row,
				column));
		MouseEvent mouseEvent = new MouseEvent(cellGUI.getNumberInputField(),
				MouseEvent.MOUSE_RELEASED, 0l, 0, 0, 0, 1, false);
		MouseListener[] mouseListeners = cellGUI.getNumberInputField().getMouseListeners();
		mouseListeners[3].mouseReleased(mouseEvent);
		
		JToggleButton numberNineButton = (JToggleButton) numberButtonGUI.getComponent(8);
		mouseEvent = new MouseEvent(numberNineButton,
				MouseEvent.MOUSE_RELEASED, 0l, 0, 0, 0, 1, false);
		mouseListeners = numberNineButton.getMouseListeners();
		numberNineButton.setSelected(true);
		mouseListeners[1].mouseReleased(mouseEvent);

		Assert.assertTrue(gridLogic.getCell(row, column).getPencilMarks()
				.isEmpty());
	}


	/**
	 * When a number is entered to a cell, corresponding row, column, and block 
	 * should have PencilMark updated, so the entered number is removed from PencilMark 
	 *  	
	 * UC3 Step 5
	 */
	@Test
	public void systemClearsAnyPenciledinValuesFromRowColumnBoxes() {

		NumberButtonGUI numberButtonGUI = new NumberButtonGUI();

		DifficultyLevel difficulty = DifficultyLevel.Easy;
		PuzzleRepository puzzleRepo = new PuzzleRepository();
		Puzzle puzzle = puzzleRepo.getPuzzle(difficulty);
		GameSession newGame = new GameSession(puzzle);
		final BoardGUI gridPanel = new BoardGUI();
		numberButtonGUI.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent mouseEvent) {

				gridPanel.mouseClickedTaskForNumberInput(mouseEvent);
			}
		});
		numberButtonGUI.init(newGame);

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
				Assert.assertTrue(currentCell.getPencilMarks().contains(
						designatedNumber));
			}
		}

		Cell cell = gridLogic.getCell(row, column);
		cell.getPencilMarks().add(9);

		CellGUI cellGUI = gridPanel.findCorresdpondingCellGUI(new Cell(row,
				column));
		MouseEvent mouseEvent = new MouseEvent(cellGUI.getNumberInputField(),
				MouseEvent.MOUSE_RELEASED, 0l, 0, 0, 0, 1, false);
		MouseListener[] mouseListeners = cellGUI.getNumberInputField().getMouseListeners();
		mouseListeners[3].mouseReleased(mouseEvent);

		JToggleButton numberNineButton = (JToggleButton) numberButtonGUI
				.getComponent(8);
		mouseEvent = new MouseEvent(numberNineButton,
				MouseEvent.MOUSE_RELEASED, 0l, 0, 0, 0, 1, false);
		mouseListeners = numberNineButton.getMouseListeners();
		numberNineButton.setSelected(true);
		mouseListeners[1].mouseReleased(mouseEvent);

		/* row check */
		for (int i = 0; i < 9; i++) {
			Cell currentCell = gridLogic.getCell(row, i);
			Assert.assertFalse(currentCell.getPencilMarks().contains(
					designatedNumber));
		}

		/* column check */
		for (int i = 0; i < 9; i++) {
			Cell currentCell = gridLogic.getCell(i, column);
			Assert.assertFalse(currentCell.getPencilMarks().contains(
					designatedNumber));
		}

		/* block check */
		int blockRow = row % 3; // 0 1 2
		int blockColumn = column / 3; // 0 1 2

		for (int j = 0; j < 3; j++) {
			int a = blockRow * 3 + j; // 012; 345; 678

			for (int k = 0; k < 3; k++) {
				int b = blockColumn * 3 + k; // 012; 345; 678

				Cell currentCell = gridLogic.getCell(a, b);
				Assert.assertFalse(currentCell.getPencilMarks().contains(
						designatedNumber));
			}
		}
	}
}
