package edu.psu.sweng500.team8.gui;

import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRootPane;
import javax.swing.JToggleButton;

import org.junit.Assert;
import org.junit.Test;

import edu.psu.sweng500.team8.coreDataStructures.Cell;
import edu.psu.sweng500.team8.coreDataStructures.Cell.ValueType;
import edu.psu.sweng500.team8.coreDataStructures.Puzzle.DifficultyLevel;

/**
 * 
 * This is UI Integration/Regression Test
 * This is heavily coupled with the code that if the code changes, this test must be updated
 * 
 *
 */
public class SudokuGUITest {

	@Test
	public void testUtility() {
		/* This is to help developers to determine components */
		
		SudokuGUI sudokuGUI = new SudokuGUI();
		JPanel mainJPanel = ((JPanel)((JLayeredPane)((JRootPane) sudokuGUI.getComponents()[0]).getComponents()[1]).getComponents()[0]);
		
		int i = 0;
		for (Component eachComponent : mainJPanel.getComponents()) {
			
			System.out.println(i++ + " :  " + eachComponent.getClass().getName());
		}
	} 
	
	@Test
	public void testSudokuGUI_NewGame() {
		
		SudokuGUI sudokuGUI = new SudokuGUI();
		JPanel mainJPanel = ((JPanel)((JLayeredPane)((JRootPane) sudokuGUI.getComponents()[0]).getComponents()[1]).getComponents()[0]);
		
		JRadioButton hardRadioButton = (JRadioButton)mainJPanel.getComponents()[4];
		System.out.println("hard : " + hardRadioButton.getText());
		
		/* Select HARD Difficulty */
		hardRadioButton.doClick();
		
		JButton newGameButton = (JButton)mainJPanel.getComponents()[18];
		System.out.println("New Game : " + newGameButton.getText());
		newGameButton.doClick();
		
		/* Verify the game is in Normal Mode */
		Assert.assertFalse(sudokuGUI.getGameSession().isPencilMarkMode());
		
		/* Verify puzzle associated with the game matches the Difficulty Setting : HARD */
		Assert.assertEquals(DifficultyLevel.Hard, sudokuGUI.getGameSession().getGameBoard().getCurrentPuzzle().getDifficulty());
		
		BoardGUI boardGUI = (BoardGUI)mainJPanel.getComponents()[0];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				Cell currentCell = sudokuGUI.getGameSession().getGameBoard().getCellGrid().getCell(i, j);
				if (!currentCell.hasNumber()) {
					
					CellGUI emptyCellGUI = boardGUI.findCorresdpondingCellGUI(currentCell);
					
					/* Verify CellGUI is empty when data object Cell has no number */
					Assert.assertEquals("", emptyCellGUI.getNumberInputField().getText());
					
					/* Verify NumberInputField is visible */
					Assert.assertTrue(emptyCellGUI.getNumberInputField().isVisible());
					
					Assert.assertEquals(ValueType.UserDefined, currentCell.getType());
					
					/* User Number does NOT have highlight */
					Assert.assertEquals(0, emptyCellGUI.getNumberInputField().getHighlighter().getHighlights().length);
					
					/* Pencil Mark Display Cell should not be visible at this point */
					Assert.assertFalse(emptyCellGUI.getpPencilMarkDisplayCell().isVisible());
				} else {
					CellGUI nonEmptyCellGUI = boardGUI.findCorresdpondingCellGUI(currentCell);
					
					/* Verify CellGUI is not empty when data object Cell has a number */
					Assert.assertNotEquals("", nonEmptyCellGUI.getNumberInputField().getText());
					
					/* Verify NumberInputField is visible */
					Assert.assertTrue(nonEmptyCellGUI.getNumberInputField().isVisible());
					
					Assert.assertEquals(ValueType.Given, currentCell.getType());
					
					/* Given Number has highlight */
					Assert.assertNotEquals(0, nonEmptyCellGUI.getNumberInputField().getHighlighter().getHighlights().length);
					
					/* Pencil Mark Display Cell should not be visible at this point */
					Assert.assertFalse(nonEmptyCellGUI.getpPencilMarkDisplayCell().isVisible());
				}
			}
		}
	}
	
	@Test
	public void testSudokuGUI_NormalMode_EnteringNumber_EmptyCell() {
		
		SudokuGUI sudokuGUI = new SudokuGUI();
		JPanel mainJPanel = ((JPanel)((JLayeredPane)((JRootPane) sudokuGUI.getComponents()[0]).getComponents()[1]).getComponents()[0]);

		/* Select HARD Difficulty */
		JRadioButton hardRadioButton = (JRadioButton)mainJPanel.getComponents()[4];
		hardRadioButton.doClick();
		
		JButton newGameButton = (JButton)mainJPanel.getComponents()[18];
		newGameButton.doClick();
		
		BoardGUI boardGUI = (BoardGUI)mainJPanel.getComponents()[0];
		
		/* Find Empty Cell */
		Cell emptyCell = null;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				Cell currentCell = sudokuGUI.getGameSession().getGameBoard().getCellGrid().getCell(i, j);
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
		FocusEvent focusEvent = new FocusEvent(selectedCellGUI.getNumberInputField(),
				FocusEvent.FOCUS_GAINED);
		FocusListener[] focusListeners = selectedCellGUI.getNumberInputField()
				.getFocusListeners();
		focusListeners[2].focusGained(focusEvent);

		/* Number Button for 8 is clicked*/
		NumberButtonGUI numberButtonGUI = (NumberButtonGUI)mainJPanel.getComponents()[7];
		JToggleButton number_8 = (JToggleButton)numberButtonGUI.getComponent(7);
		/* doClick won't work because we have mouseListener not actionLister */
		
		MouseEvent mouseEvent = new MouseEvent(number_8,
				MouseEvent.MOUSE_RELEASED, 0l, 0, 0, 0, 1, false);
		MouseListener[] mouseListeners = number_8.getMouseListeners();
		number_8.setSelected(true);
		mouseListeners[1].mouseReleased(mouseEvent);
		
		/* Verify CellGUI has 8 */
		Assert.assertEquals("8", selectedCellGUI.getNumberInputField().getText());
		
		
		/* Number Button for 3 is clicked*/
		JToggleButton number_3 = (JToggleButton)numberButtonGUI.getComponent(2);  
		/* doClick won't work because we have mouseListener not actionLister */
		
		mouseEvent = new MouseEvent(number_3,
				MouseEvent.MOUSE_RELEASED, 0l, 0, 0, 0, 1, false);
		mouseListeners = number_3.getMouseListeners();
		number_3.setSelected(true);
		mouseListeners[1].mouseReleased(mouseEvent);
		
		/* Verify CellGUI has 3 */
		Assert.assertEquals("3", selectedCellGUI.getNumberInputField().getText());
		
		/* Verify NumberInputPad 8 is unselected */
		number_8 = (JToggleButton)numberButtonGUI.getComponent(7);
		Assert.assertFalse(number_8.isSelected());
		
		
		/* Number Button for 3 is un-clicked*/
		number_3 = (JToggleButton)numberButtonGUI.getComponent(2);  
		/* doClick won't work because we have mouseListener not actionLister */
		
		mouseEvent = new MouseEvent(number_3,
				MouseEvent.MOUSE_RELEASED, 0l, 0, 0, 0, 1, false);
		mouseListeners = number_3.getMouseListeners();
		number_3.setSelected(false);
		mouseListeners[1].mouseReleased(mouseEvent);
		
		/* Verify CellGUI is empty */
		Assert.assertEquals("", selectedCellGUI.getNumberInputField().getText());
	}
	
	@Test
	public void testSudokuGUI_PencilMarkMode_EnteringNumber_EmptyCell() {

		SudokuGUI sudokuGUI = new SudokuGUI();
		JPanel mainJPanel = ((JPanel)((JLayeredPane)((JRootPane) sudokuGUI.getComponents()[0]).getComponents()[1]).getComponents()[0]);
		
		JRadioButton hardRadioButton = (JRadioButton)mainJPanel.getComponents()[4];
		
		/* Select HARD Difficulty */
		hardRadioButton.doClick();
		
		JButton newGameButton = (JButton)mainJPanel.getComponents()[18];
		newGameButton.doClick();
		
		JToggleButton pencilMarkModeButton = (JToggleButton)mainJPanel.getComponents()[16];
		pencilMarkModeButton.doClick();

		/* Verify the game is in Pencil Mark Mode */
		Assert.assertTrue(sudokuGUI.getGameSession().isPencilMarkMode());
		
		BoardGUI boardGUI = (BoardGUI)mainJPanel.getComponents()[0];
		
		/* Find Empty Cell */
		Cell emptyCell = null;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				Cell currentCell = sudokuGUI.getGameSession().getGameBoard().getCellGrid().getCell(i, j);
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
		FocusEvent focusEvent = new FocusEvent(selectedCellGUI.getNumberInputField(),
				FocusEvent.FOCUS_GAINED);
		FocusListener[] focusListeners = selectedCellGUI.getNumberInputField()
				.getFocusListeners();
		focusListeners[2].focusGained(focusEvent);

		/* Number Button for 8 is clicked*/
		NumberButtonGUI numberButtonGUI = (NumberButtonGUI)mainJPanel.getComponents()[7];
		JToggleButton number_8 = (JToggleButton)numberButtonGUI.getComponent(7);
		/* doClick won't work because we have mouseListener not actionLister */

		MouseEvent mouseEvent = new MouseEvent(number_8,
				MouseEvent.MOUSE_RELEASED, 0l, 0, 0, 0, 1, false);
		MouseListener[] mouseListeners = number_8.getMouseListeners();
		number_8.setSelected(true);
		mouseListeners[1].mouseReleased(mouseEvent);
		
		/* Verify CellGUI has 8 on Pencil Mark */
		Assert.assertEquals("8", ((JLabel)selectedCellGUI.getpPencilMarkDisplayCell().getComponent(7)).getText());
		
		Assert.assertEquals("", ((JLabel)selectedCellGUI.getpPencilMarkDisplayCell().getComponent(0)).getText());
		Assert.assertEquals("", ((JLabel)selectedCellGUI.getpPencilMarkDisplayCell().getComponent(1)).getText());
		Assert.assertEquals("", ((JLabel)selectedCellGUI.getpPencilMarkDisplayCell().getComponent(2)).getText());
		Assert.assertEquals("", ((JLabel)selectedCellGUI.getpPencilMarkDisplayCell().getComponent(3)).getText());
		Assert.assertEquals("", ((JLabel)selectedCellGUI.getpPencilMarkDisplayCell().getComponent(4)).getText());
		Assert.assertEquals("", ((JLabel)selectedCellGUI.getpPencilMarkDisplayCell().getComponent(5)).getText());
		Assert.assertEquals("", ((JLabel)selectedCellGUI.getpPencilMarkDisplayCell().getComponent(6)).getText());
		Assert.assertEquals("", ((JLabel)selectedCellGUI.getpPencilMarkDisplayCell().getComponent(8)).getText());
		
		/* Number Button for 3 is clicked*/
		JToggleButton number_3 = (JToggleButton)numberButtonGUI.getComponent(2);  
		
		mouseEvent = new MouseEvent(number_3,
				MouseEvent.MOUSE_RELEASED, 0l, 0, 0, 0, 1, false);
		mouseListeners = number_3.getMouseListeners();
		number_3.setSelected(true);
		mouseListeners[1].mouseReleased(mouseEvent);
		
		/* Verify CellGUI has 8 and 3 on Pencil Mark */
		Assert.assertEquals("8", ((JLabel)selectedCellGUI.getpPencilMarkDisplayCell().getComponent(7)).getText());
		Assert.assertEquals("3", ((JLabel)selectedCellGUI.getpPencilMarkDisplayCell().getComponent(2)).getText());
		
		Assert.assertEquals("", ((JLabel)selectedCellGUI.getpPencilMarkDisplayCell().getComponent(0)).getText());
		Assert.assertEquals("", ((JLabel)selectedCellGUI.getpPencilMarkDisplayCell().getComponent(1)).getText());
		Assert.assertEquals("", ((JLabel)selectedCellGUI.getpPencilMarkDisplayCell().getComponent(3)).getText());
		Assert.assertEquals("", ((JLabel)selectedCellGUI.getpPencilMarkDisplayCell().getComponent(4)).getText());
		Assert.assertEquals("", ((JLabel)selectedCellGUI.getpPencilMarkDisplayCell().getComponent(5)).getText());
		Assert.assertEquals("", ((JLabel)selectedCellGUI.getpPencilMarkDisplayCell().getComponent(6)).getText());
		Assert.assertEquals("", ((JLabel)selectedCellGUI.getpPencilMarkDisplayCell().getComponent(8)).getText());
		
		/* Verify NumberInputPad 8 is still selected */
		number_8 = (JToggleButton)numberButtonGUI.getComponent(7);
		Assert.assertTrue(number_8.isSelected());
		
		/* Number Button for 3 is un-clicked*/
		number_3 = (JToggleButton)numberButtonGUI.getComponent(2);  
		
		mouseEvent = new MouseEvent(number_3,
				MouseEvent.MOUSE_RELEASED, 0l, 0, 0, 0, 1, false);
		mouseListeners = number_3.getMouseListeners();
		number_3.setSelected(false);
		mouseListeners[1].mouseReleased(mouseEvent);
		
		/* Verify CellGUI has only 8 */
		Assert.assertEquals("8", ((JLabel)selectedCellGUI.getpPencilMarkDisplayCell().getComponent(7)).getText());
		
		Assert.assertEquals("", ((JLabel)selectedCellGUI.getpPencilMarkDisplayCell().getComponent(0)).getText());
		Assert.assertEquals("", ((JLabel)selectedCellGUI.getpPencilMarkDisplayCell().getComponent(1)).getText());
		Assert.assertEquals("", ((JLabel)selectedCellGUI.getpPencilMarkDisplayCell().getComponent(2)).getText());
		Assert.assertEquals("", ((JLabel)selectedCellGUI.getpPencilMarkDisplayCell().getComponent(3)).getText());
		Assert.assertEquals("", ((JLabel)selectedCellGUI.getpPencilMarkDisplayCell().getComponent(4)).getText());
		Assert.assertEquals("", ((JLabel)selectedCellGUI.getpPencilMarkDisplayCell().getComponent(5)).getText());
		Assert.assertEquals("", ((JLabel)selectedCellGUI.getpPencilMarkDisplayCell().getComponent(6)).getText());
		Assert.assertEquals("", ((JLabel)selectedCellGUI.getpPencilMarkDisplayCell().getComponent(8)).getText());
		
	}
}