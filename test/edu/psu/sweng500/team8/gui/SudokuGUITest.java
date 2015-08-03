package edu.psu.sweng500.team8.gui;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRootPane;
import javax.swing.JToggleButton;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.psu.sweng500.team8.coreDataStructures.Cell;
import edu.psu.sweng500.team8.coreDataStructures.Cell.ValueType;
import edu.psu.sweng500.team8.coreDataStructures.CellGrid;
import edu.psu.sweng500.team8.coreDataStructures.Puzzle.DifficultyLevel;

/**
 * 
 * This is UI Integration/Regression Test This is heavily coupled with the code
 * that if the code changes, this test must be updated
 * 
 *
 */
public class SudokuGUITest {

	private static SudokuGUI sudokuGUI;
	private JPanel mainJPanel;
	
	@BeforeClass
	public static void setUpClass() {
		sudokuGUI = new SudokuGUI();
	}

	@AfterClass
	public static void tearDownClass() {
		sudokuGUI = null;
	}
	
	@Before
	public void setUp() {

		mainJPanel = ((JPanel) ((JLayeredPane) ((JRootPane) sudokuGUI
				.getComponents()[0]).getComponents()[1]).getComponents()[0]);
		
		JToggleButton pencilMarkModeButton = (JToggleButton) mainJPanel
				.getComponents()[16];
		pencilMarkModeButton.setSelected(false);
		
		NumberButtonGUI numberButtonGUI = (NumberButtonGUI) mainJPanel
				.getComponents()[7];
		
		for (int i = 0; i < 9; i++) {
			JToggleButton numberInputButton = (JToggleButton) numberButtonGUI
					.getComponent(i);
			numberInputButton.setSelected(false);
		}
		
		if (null != sudokuGUI.getGameSession()) {
			if (sudokuGUI.getGameSession().isPencilMarkMode()) {
				sudokuGUI.getGameSession().setPencilMarkMode(false);
			}
			
			if (sudokuGUI.isGameChanged()) {
				sudokuGUI.setGameChanged(false);
			} 
		}
	}
	
	@After
	public void tearDown() {
		
	}

	/**
	 * This is to help developers to determine components
	 */
	@Test
	public void testUtility() {

		int i = 0;
		for (Component eachComponent : mainJPanel.getComponents()) {

			System.out.println(i++ + " :  "
					+ eachComponent.getClass().getName());
		}
	}

	@Test
	public void testSudokuGUI_NewGame() {

		JRadioButton hardRadioButton = (JRadioButton) mainJPanel
				.getComponents()[4];
		System.out.println("hard : " + hardRadioButton.getText());

		/* Select HARD Difficulty */
		hardRadioButton.doClick();

		JButton newGameButton = (JButton) mainJPanel.getComponents()[18];
		System.out.println("New Game : " + newGameButton.getText());
		newGameButton.doClick();

		/* Verify the game is in Normal Mode */
		Assert.assertFalse(sudokuGUI.getGameSession().isPencilMarkMode());

		/*
		 * Verify puzzle associated with the game matches the Difficulty Setting
		 * : HARD
		 */
		Assert.assertEquals(DifficultyLevel.Hard, sudokuGUI.getGameSession()
				.getGameBoard().getCurrentPuzzle().getDifficulty());

		BoardGUI boardGUI = (BoardGUI) mainJPanel.getComponents()[0];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				Cell currentCell = sudokuGUI.getGameSession().getGameBoard()
						.getCellGrid().getCell(i, j);
				if (!currentCell.hasNumber()) {

					CellGUI emptyCellGUI = boardGUI
							.findCorresdpondingCellGUI(currentCell);

					/*
					 * Verify CellGUI is empty when data object Cell has no
					 * number
					 */
					Assert.assertEquals("", emptyCellGUI.getNumberInputField()
							.getText());

					/* Verify NumberInputField is visible */
					Assert.assertTrue(emptyCellGUI.getNumberInputField()
							.isVisible());

					Assert.assertEquals(ValueType.UserDefined,
							currentCell.getType());

					/* User Number does NOT have highlight */
					Assert.assertEquals(0, emptyCellGUI.getNumberInputField()
							.getHighlighter().getHighlights().length);

					/*
					 * Pencil Mark Display Cell should not be visible at this
					 * point
					 */
					Assert.assertFalse(emptyCellGUI.getpPencilMarkDisplayCell()
							.isVisible());
				} else {
					CellGUI nonEmptyCellGUI = boardGUI
							.findCorresdpondingCellGUI(currentCell);

					/*
					 * Verify CellGUI is not empty when data object Cell has a
					 * number
					 */
					Assert.assertNotEquals("", nonEmptyCellGUI
							.getNumberInputField().getText());

					/* Verify NumberInputField is visible */
					Assert.assertTrue(nonEmptyCellGUI.getNumberInputField()
							.isVisible());

					Assert.assertEquals(ValueType.Given, currentCell.getType());

					/* Given Number has highlight */
					Assert.assertNotEquals(0, nonEmptyCellGUI
							.getNumberInputField().getHighlighter()
							.getHighlights().length);

					/*
					 * Pencil Mark Display Cell should not be visible at this
					 * point
					 */
					Assert.assertFalse(nonEmptyCellGUI
							.getpPencilMarkDisplayCell().isVisible());
				}
			}
		}
	}

	@Test
	public void testSudokuGUI_NormalMode_EnteringNumber_EmptyCell() {

		/* Select HARD Difficulty */
		JRadioButton hardRadioButton = (JRadioButton) mainJPanel
				.getComponents()[4];
		hardRadioButton.doClick();

		JButton newGameButton = (JButton) mainJPanel.getComponents()[18];
		newGameButton.doClick();

		BoardGUI boardGUI = (BoardGUI) mainJPanel.getComponents()[0];

		/* Find Empty Cell */
		Cell emptyCell = null;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				Cell currentCell = sudokuGUI.getGameSession().getGameBoard()
						.getCellGrid().getCell(i, j);
				if (!currentCell.hasNumber()) {
					emptyCell = currentCell;
					break;
				}
			}

			if (null != emptyCell) {
				break;
			}
		}

		/* Empty CellGUI is focused */
		CellGUI selectedCellGUI = boardGUI.findCorresdpondingCellGUI(emptyCell);
		MouseEvent mouseEventNormal = new MouseEvent(selectedCellGUI.getNumberInputField(),
				MouseEvent.MOUSE_RELEASED, 0l, 0, 0, 0, 1, false);
		MouseListener[] mouseListenersNormal = selectedCellGUI.getNumberInputField().getMouseListeners();
		mouseListenersNormal[3].mouseReleased(mouseEventNormal);

		/* Number Button for 8 is clicked */
		NumberButtonGUI numberButtonGUI = (NumberButtonGUI) mainJPanel
				.getComponents()[7];
		JToggleButton number_8 = (JToggleButton) numberButtonGUI
				.getComponent(7);
		/* doClick won't work because we have mouseListener not actionLister */

		MouseEvent mouseEvent = new MouseEvent(number_8,
				MouseEvent.MOUSE_RELEASED, 0l, 0, 0, 0, 1, false);
		MouseListener[] mouseListeners = number_8.getMouseListeners();
		number_8.setSelected(true);
		mouseListeners[1].mouseReleased(mouseEvent);

		/* Verify CellGUI has 8 */
		Assert.assertEquals("8", selectedCellGUI.getNumberInputField()
				.getText());

		/* Number Button for 3 is clicked */
		JToggleButton number_3 = (JToggleButton) numberButtonGUI
				.getComponent(2);
		/* doClick won't work because we have mouseListener not actionLister */

		mouseEvent = new MouseEvent(number_3, MouseEvent.MOUSE_RELEASED, 0l, 0,
				0, 0, 1, false);
		mouseListeners = number_3.getMouseListeners();
		number_3.setSelected(true);
		mouseListeners[1].mouseReleased(mouseEvent);

		/* Verify CellGUI has 3 */
		Assert.assertEquals("3", selectedCellGUI.getNumberInputField()
				.getText());

		/* Verify NumberInputPad 8 is unselected */
		number_8 = (JToggleButton) numberButtonGUI.getComponent(7);
		Assert.assertFalse(number_8.isSelected());

		/* Number Button for 3 is un-clicked */
		number_3 = (JToggleButton) numberButtonGUI.getComponent(2);
		/* doClick won't work because we have mouseListener not actionLister */

		mouseEvent = new MouseEvent(number_3, MouseEvent.MOUSE_RELEASED, 0l, 0,
				0, 0, 1, false);
		mouseListeners = number_3.getMouseListeners();
		number_3.setSelected(false);
		mouseListeners[1].mouseReleased(mouseEvent);

		/* Verify CellGUI is empty */
		Assert.assertEquals("", selectedCellGUI.getNumberInputField().getText());
	}

	@Test
	public void testSudokuGUI_PencilMarkMode_EnteringNumber_EmptyCell() {

		JRadioButton hardRadioButton = (JRadioButton) mainJPanel
				.getComponents()[4];

		/* Select HARD Difficulty */
		hardRadioButton.doClick();

		JButton newGameButton = (JButton) mainJPanel.getComponents()[18];
		newGameButton.doClick();

		JToggleButton pencilMarkModeButton = (JToggleButton) mainJPanel
				.getComponents()[16];
		pencilMarkModeButton.doClick();

		/* Verify the game is in Pencil Mark Mode */
		Assert.assertTrue(sudokuGUI.getGameSession().isPencilMarkMode());

		BoardGUI boardGUI = (BoardGUI) mainJPanel.getComponents()[0];

		/* Find Empty Cell */
		Cell emptyCell = null;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				Cell currentCell = sudokuGUI.getGameSession().getGameBoard()
						.getCellGrid().getCell(i, j);
				if (!currentCell.hasNumber()) {
					emptyCell = currentCell;
					break;
				}
			}

			if (null != emptyCell) {
				break;
			}
		}

		/* Empty CellGUI is focused */
		CellGUI selectedCellGUI = boardGUI.findCorresdpondingCellGUI(emptyCell);
		MouseEvent mouseEventNormal = new MouseEvent(selectedCellGUI.getNumberInputField(),
				MouseEvent.MOUSE_RELEASED, 0l, 0, 0, 0, 1, false);
		MouseListener[] mouseListenersNormal = selectedCellGUI.getNumberInputField().getMouseListeners();
		mouseListenersNormal[3].mouseReleased(mouseEventNormal);

		/* Number Button for 8 is clicked */
		NumberButtonGUI numberButtonGUI = (NumberButtonGUI) mainJPanel
				.getComponents()[7];
		JToggleButton number_8 = (JToggleButton) numberButtonGUI
				.getComponent(7);
		/* doClick won't work because we have mouseListener not actionLister */

		MouseEvent mouseEvent = new MouseEvent(number_8,
				MouseEvent.MOUSE_RELEASED, 0l, 0, 0, 0, 1, false);
		MouseListener[] mouseListeners = number_8.getMouseListeners();
		number_8.setSelected(true);
		mouseListeners[1].mouseReleased(mouseEvent);

		/* Verify CellGUI has 8 on Pencil Mark */
		Assert.assertEquals("8", ((JLabel) selectedCellGUI
				.getpPencilMarkDisplayCell().getComponent(7)).getText());

		Assert.assertEquals("", ((JLabel) selectedCellGUI
				.getpPencilMarkDisplayCell().getComponent(0)).getText());
		Assert.assertEquals("", ((JLabel) selectedCellGUI
				.getpPencilMarkDisplayCell().getComponent(1)).getText());
		Assert.assertEquals("", ((JLabel) selectedCellGUI
				.getpPencilMarkDisplayCell().getComponent(2)).getText());
		Assert.assertEquals("", ((JLabel) selectedCellGUI
				.getpPencilMarkDisplayCell().getComponent(3)).getText());
		Assert.assertEquals("", ((JLabel) selectedCellGUI
				.getpPencilMarkDisplayCell().getComponent(4)).getText());
		Assert.assertEquals("", ((JLabel) selectedCellGUI
				.getpPencilMarkDisplayCell().getComponent(5)).getText());
		Assert.assertEquals("", ((JLabel) selectedCellGUI
				.getpPencilMarkDisplayCell().getComponent(6)).getText());
		Assert.assertEquals("", ((JLabel) selectedCellGUI
				.getpPencilMarkDisplayCell().getComponent(8)).getText());

		/* Number Button for 3 is clicked */
		JToggleButton number_3 = (JToggleButton) numberButtonGUI
				.getComponent(2);

		mouseEvent = new MouseEvent(number_3, MouseEvent.MOUSE_RELEASED, 0l, 0,
				0, 0, 1, false);
		mouseListeners = number_3.getMouseListeners();
		number_3.setSelected(true);
		mouseListeners[1].mouseReleased(mouseEvent);

		/* Verify CellGUI has 8 and 3 on Pencil Mark */
		Assert.assertEquals("8", ((JLabel) selectedCellGUI
				.getpPencilMarkDisplayCell().getComponent(7)).getText());
		Assert.assertEquals("3", ((JLabel) selectedCellGUI
				.getpPencilMarkDisplayCell().getComponent(2)).getText());

		Assert.assertEquals("", ((JLabel) selectedCellGUI
				.getpPencilMarkDisplayCell().getComponent(0)).getText());
		Assert.assertEquals("", ((JLabel) selectedCellGUI
				.getpPencilMarkDisplayCell().getComponent(1)).getText());
		Assert.assertEquals("", ((JLabel) selectedCellGUI
				.getpPencilMarkDisplayCell().getComponent(3)).getText());
		Assert.assertEquals("", ((JLabel) selectedCellGUI
				.getpPencilMarkDisplayCell().getComponent(4)).getText());
		Assert.assertEquals("", ((JLabel) selectedCellGUI
				.getpPencilMarkDisplayCell().getComponent(5)).getText());
		Assert.assertEquals("", ((JLabel) selectedCellGUI
				.getpPencilMarkDisplayCell().getComponent(6)).getText());
		Assert.assertEquals("", ((JLabel) selectedCellGUI
				.getpPencilMarkDisplayCell().getComponent(8)).getText());

		/* Verify NumberInputPad 8 is still selected */
		number_8 = (JToggleButton) numberButtonGUI.getComponent(7);
		Assert.assertTrue(number_8.isSelected());

		/* Number Button for 3 is un-clicked */
		number_3 = (JToggleButton) numberButtonGUI.getComponent(2);

		mouseEvent = new MouseEvent(number_3, MouseEvent.MOUSE_RELEASED, 0l, 0,
				0, 0, 1, false);
		mouseListeners = number_3.getMouseListeners();
		number_3.setSelected(false);
		mouseListeners[1].mouseReleased(mouseEvent);

		/* Verify CellGUI has only 8 */
		Assert.assertEquals("8", ((JLabel) selectedCellGUI
				.getpPencilMarkDisplayCell().getComponent(7)).getText());

		Assert.assertEquals("", ((JLabel) selectedCellGUI
				.getpPencilMarkDisplayCell().getComponent(0)).getText());
		Assert.assertEquals("", ((JLabel) selectedCellGUI
				.getpPencilMarkDisplayCell().getComponent(1)).getText());
		Assert.assertEquals("", ((JLabel) selectedCellGUI
				.getpPencilMarkDisplayCell().getComponent(2)).getText());
		Assert.assertEquals("", ((JLabel) selectedCellGUI
				.getpPencilMarkDisplayCell().getComponent(3)).getText());
		Assert.assertEquals("", ((JLabel) selectedCellGUI
				.getpPencilMarkDisplayCell().getComponent(4)).getText());
		Assert.assertEquals("", ((JLabel) selectedCellGUI
				.getpPencilMarkDisplayCell().getComponent(5)).getText());
		Assert.assertEquals("", ((JLabel) selectedCellGUI
				.getpPencilMarkDisplayCell().getComponent(6)).getText());
		Assert.assertEquals("", ((JLabel) selectedCellGUI
				.getpPencilMarkDisplayCell().getComponent(8)).getText());
	}

	@Test
	public void testSudokuGUI_NormalMode_EnteringNumber_ExistingPencilMark() {

		JRadioButton hardRadioButton = (JRadioButton) mainJPanel
				.getComponents()[4];

		/* Select HARD Difficulty */
		hardRadioButton.doClick();

		JButton newGameButton = (JButton) mainJPanel.getComponents()[18];
		newGameButton.doClick();

		Assert.assertFalse(sudokuGUI.getGameSession().isPencilMarkMode());
		
		JToggleButton pencilMarkModeButton = (JToggleButton) mainJPanel
				.getComponents()[16];
		pencilMarkModeButton.doClick();

		/* Verify the game is in Pencil Mark Mode */
		Assert.assertTrue(sudokuGUI.getGameSession().isPencilMarkMode());

		BoardGUI boardGUI = (BoardGUI) mainJPanel.getComponents()[0];

		/* Find Empty Cell */
		Cell emptyCell = null;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				Cell currentCell = sudokuGUI.getGameSession().getGameBoard()
						.getCellGrid().getCell(i, j);
				if (!currentCell.hasNumber()) {
					emptyCell = currentCell;
					break;
				}
			}

			if (null != emptyCell) {
				break;
			}
		}

		/* Empty CellGUI is focused */
		CellGUI selectedCellGUI = boardGUI.findCorresdpondingCellGUI(emptyCell);
		MouseEvent mouseEventNormal = new MouseEvent(selectedCellGUI.getNumberInputField(),
				MouseEvent.MOUSE_RELEASED, 0l, 0, 0, 0, 1, false);
		MouseListener[] mouseListenersNormal = selectedCellGUI.getNumberInputField().getMouseListeners();
		mouseListenersNormal[3].mouseReleased(mouseEventNormal);

		/* Number Button for 8 is clicked */
		NumberButtonGUI numberButtonGUI = (NumberButtonGUI) mainJPanel
				.getComponents()[7];
		JToggleButton number_8 = (JToggleButton) numberButtonGUI
				.getComponent(7);
		/* doClick won't work because we have mouseListener not actionLister */

		MouseEvent mouseEvent = new MouseEvent(number_8,
				MouseEvent.MOUSE_RELEASED, 0l, 0, 0, 0, 1, false);
		MouseListener[] mouseListeners = number_8.getMouseListeners();
		number_8.setSelected(true);
		mouseListeners[1].mouseReleased(mouseEvent);

		/*
		 * At this point, Pencil Mark is entered. Verification is done by other
		 * test case. Go back to normal mode
		 */
		pencilMarkModeButton.doClick();

		/* Verify the game is in Normal Mode */
		Assert.assertFalse(sudokuGUI.getGameSession().isPencilMarkMode());

		/*
		 * Since the cellGUI must show PencilMark, we cannot focus on the
		 * TextField. We need to click on PencilMarkDisplayCell. It doesn't
		 * matter on which cell we click
		 */
		JLabel pencilMarkDisplay = (JLabel) selectedCellGUI
				.getpPencilMarkDisplayCell().getComponent(0);
		mouseEvent = new MouseEvent(pencilMarkDisplay,
				MouseEvent.MOUSE_RELEASED, 0l, 0, 0, 0, 1, false);
		mouseListeners = pencilMarkDisplay.getMouseListeners();
		mouseListeners[0].mouseReleased(mouseEvent);

		/* Now, CellGUI is ready to get normal number input */
		/* Number Button for 6 is clicked */
		JToggleButton number_6 = (JToggleButton) numberButtonGUI
				.getComponent(5);

		mouseEvent = new MouseEvent(number_6, MouseEvent.MOUSE_RELEASED, 0l, 0,
				0, 0, 1, false);
		mouseListeners = number_6.getMouseListeners();
		number_6.setSelected(true);
		mouseListeners[1].mouseReleased(mouseEvent);

		/* Verify CellGUI has 6 */
		Assert.assertEquals("6", selectedCellGUI.getNumberInputField()
				.getText());
	}
	
	@Test
	public void testSudokuGUI_PencilMarkMode_EnteringNumber_ExistingPencilMark() {

		JRadioButton hardRadioButton = (JRadioButton) mainJPanel
				.getComponents()[4];

		/* Select HARD Difficulty */
		hardRadioButton.doClick();

		JButton newGameButton = (JButton) mainJPanel.getComponents()[18];
		newGameButton.doClick();

		/* Entering Pencil Mark Mode */ 
		JToggleButton pencilMarkModeButton = (JToggleButton) mainJPanel
				.getComponents()[16];
		pencilMarkModeButton.doClick();

		
		BoardGUI boardGUI = (BoardGUI) mainJPanel.getComponents()[0];

		/* Find Empty Cell */
		Cell emptyCell = null;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				Cell currentCell = sudokuGUI.getGameSession().getGameBoard()
						.getCellGrid().getCell(i, j);
				if (!currentCell.hasNumber()) {
					emptyCell = currentCell;
					break;
				}
			}

			if (null != emptyCell) {
				break;
			}
		}

		/* Empty CellGUI is focused */
		CellGUI selectedCellGUI = boardGUI.findCorresdpondingCellGUI(emptyCell);
		MouseEvent mouseEventNormal = new MouseEvent(selectedCellGUI.getNumberInputField(),
				MouseEvent.MOUSE_RELEASED, 0l, 0, 0, 0, 1, false);
		MouseListener[] mouseListenersNormal = selectedCellGUI.getNumberInputField().getMouseListeners();
		mouseListenersNormal[3].mouseReleased(mouseEventNormal);

		/* Number Button for 8 is clicked */
		NumberButtonGUI numberButtonGUI = (NumberButtonGUI) mainJPanel
				.getComponents()[7];
		JToggleButton number_8 = (JToggleButton) numberButtonGUI
				.getComponent(7);
		MouseEvent mouseEvent = new MouseEvent(number_8,
				MouseEvent.MOUSE_RELEASED, 0l, 0, 0, 0, 1, false);
		MouseListener[] mouseListeners = number_8.getMouseListeners();
		number_8.setSelected(true);
		mouseListeners[1].mouseReleased(mouseEvent);

		/* PencilMark has 8 */
		
		/* We need to focus another empty cell to remove focus from the current CellGUI, and come back */
		/* Find Empty Cell */
		emptyCell = null;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				Cell currentCell = sudokuGUI.getGameSession().getGameBoard()
						.getCellGrid().getCell(i, j);
				if (!currentCell.hasNumber() && currentCell.getPencilMarks().isEmpty()) {
					emptyCell = currentCell;
					break;
				}
			}

			if (null != emptyCell) {
				break;
			}
		}
		
		/* New Empty CellGUI is focused */
		CellGUI newEmptyCellGUI = boardGUI.findCorresdpondingCellGUI(emptyCell);
		mouseEventNormal = new MouseEvent(newEmptyCellGUI.getNumberInputField(),
				MouseEvent.MOUSE_RELEASED, 0l, 0, 0, 0, 1, false);
		mouseListenersNormal = newEmptyCellGUI.getNumberInputField().getMouseListeners();
		mouseListenersNormal[3].mouseReleased(mouseEventNormal);
		
		
		/* Re-foucs the CellGUI with PencilMarks */
		JLabel pencilMarkDisplay = (JLabel) selectedCellGUI
				.getpPencilMarkDisplayCell().getComponent(0);
		mouseEvent = new MouseEvent(pencilMarkDisplay,
				MouseEvent.MOUSE_RELEASED, 0l, 0, 0, 0, 1, false);
		mouseListeners = pencilMarkDisplay.getMouseListeners();
		mouseListeners[0].mouseReleased(mouseEvent);

		/* Number Button for 6 is clicked. This will add to PencilMarks */
		JToggleButton number_6 = (JToggleButton) numberButtonGUI
				.getComponent(5);
		mouseEvent = new MouseEvent(number_6, MouseEvent.MOUSE_RELEASED, 0l, 0,
				0, 0, 1, false);
		mouseListeners = number_6.getMouseListeners();
		number_6.setSelected(true);
		mouseListeners[1].mouseReleased(mouseEvent);

		
		/* Verify CellGUI has 8 and 6 for Pencil Mark */
		Assert.assertEquals("8", ((JLabel) selectedCellGUI
				.getpPencilMarkDisplayCell().getComponent(7)).getText());
		Assert.assertEquals("6", ((JLabel) selectedCellGUI
				.getpPencilMarkDisplayCell().getComponent(5)).getText());
		
		Assert.assertEquals("", ((JLabel) selectedCellGUI
				.getpPencilMarkDisplayCell().getComponent(0)).getText());
		Assert.assertEquals("", ((JLabel) selectedCellGUI
				.getpPencilMarkDisplayCell().getComponent(1)).getText());
		Assert.assertEquals("", ((JLabel) selectedCellGUI
				.getpPencilMarkDisplayCell().getComponent(2)).getText());
		Assert.assertEquals("", ((JLabel) selectedCellGUI
				.getpPencilMarkDisplayCell().getComponent(3)).getText());
		Assert.assertEquals("", ((JLabel) selectedCellGUI
				.getpPencilMarkDisplayCell().getComponent(4)).getText());
		Assert.assertEquals("", ((JLabel) selectedCellGUI
				.getpPencilMarkDisplayCell().getComponent(6)).getText());
		Assert.assertEquals("", ((JLabel) selectedCellGUI
				.getpPencilMarkDisplayCell().getComponent(8)).getText());
	}
	
	@Test
	public void testSudokuGUI_GameChangedStatus() {
		
		JButton newGameButton = (JButton)mainJPanel.getComponents()[18];
		newGameButton.doClick();
		
		BoardGUI boardGUI = (BoardGUI)mainJPanel.getComponents()[0];

		System.out.println("CHANGE?");
		System.out.println(sudokuGUI.isGameChanged());
		
		Assert.assertEquals(sudokuGUI.isGameChanged(), false);
		
		CellGrid cg = sudokuGUI.getGameSession().getGameBoard().getCellGrid();
		
		/* Find Empty Cell */
		Cell emptyCell = null;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				Cell currentCell = cg.getCell(i, j);
				if (!currentCell.hasNumber()) {
					emptyCell = currentCell;
					break;
				}
			}
			
			if (null != emptyCell) {
				break;
			}
		}
		
		/* Empty CellGUI is focused */
		CellGUI selectedCellGUI = boardGUI.findCorresdpondingCellGUI(emptyCell);
		MouseEvent mouseEventNormal = new MouseEvent(selectedCellGUI.getNumberInputField(),
				MouseEvent.MOUSE_RELEASED, 0l, 0, 0, 0, 1, false);
		MouseListener[] mouseListenersNormal = selectedCellGUI.getNumberInputField().getMouseListeners();
		mouseListenersNormal[3].mouseReleased(mouseEventNormal);

		/* Number Button for 8 is clicked*/
		NumberButtonGUI numberButtonGUI = (NumberButtonGUI)mainJPanel.getComponents()[7];
		JToggleButton number_8 = (JToggleButton)numberButtonGUI.getComponent(7);
		/* doClick won't work because we have mouseListener not actionLister */
		
		MouseEvent mouseEvent = new MouseEvent(number_8,
				MouseEvent.MOUSE_RELEASED, 0l, 0, 0, 0, 1, false);
		MouseListener[] mouseListeners = number_8.getMouseListeners();
		number_8.setSelected(true);
		mouseListeners[1].mouseReleased(mouseEvent);
		
		Assert.assertEquals(sudokuGUI.isGameChanged(), true);
	}

	@Test
	public void testSudokuGUI_GameChangedStatus_pencilMark() {
		
		JButton newGameButton = (JButton)mainJPanel.getComponents()[18];
		newGameButton.doClick();
		
		BoardGUI boardGUI = (BoardGUI)mainJPanel.getComponents()[0];

		CellGrid cg = sudokuGUI.getGameSession().getGameBoard().getCellGrid();
		
		/* Find Empty Cell */
		Cell emptyCell = null;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				Cell currentCell = cg.getCell(i, j);
				if (!currentCell.hasNumber()) {
					emptyCell = currentCell;
					break;
				}
			}
			
			if (null != emptyCell) {
				break;
			}
		}
		
		/* Empty CellGUI is focused */
		CellGUI selectedCellGUI = boardGUI.findCorresdpondingCellGUI(emptyCell);
		MouseEvent mouseEventNormal = new MouseEvent(selectedCellGUI.getNumberInputField(),
				MouseEvent.MOUSE_RELEASED, 0l, 0, 0, 0, 1, false);
		MouseListener[] mouseListenersNormal = selectedCellGUI.getNumberInputField().getMouseListeners();
		mouseListenersNormal[3].mouseReleased(mouseEventNormal);

		sudokuGUI.getGameSession().setPencilMarkMode(true);
		
		/* Number Button for 8 is clicked*/
		NumberButtonGUI numberButtonGUI = (NumberButtonGUI)mainJPanel.getComponents()[7];
		JToggleButton number_8 = (JToggleButton)numberButtonGUI.getComponent(7);
		/* doClick won't work because we have mouseListener not actionLister */
		
		MouseEvent mouseEvent = new MouseEvent(number_8,
				MouseEvent.MOUSE_RELEASED, 0l, 0, 0, 0, 1, false);
		MouseListener[] mouseListeners = number_8.getMouseListeners();
		number_8.setSelected(true);
		mouseListeners[1].mouseReleased(mouseEvent);
		
		Assert.assertEquals(sudokuGUI.isGameChanged(), true);
	}
}
