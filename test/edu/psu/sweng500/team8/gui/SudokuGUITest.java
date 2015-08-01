package edu.psu.sweng500.team8.gui;

import static org.junit.Assert.fail;

import java.awt.Component;
import java.awt.Robot;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRootPane;

import org.junit.Test;
import org.mockito.Mockito;

import edu.psu.sweng500.team8.puzzleGenerator.PuzzleRepository;

/**
 * 
 * This is UI Integration/Regression Test
 * This is heavily coupled with the code that if the code changes, this test must be updated
 * 
 *
 */
public class SudokuGUITest {

	@Test
	public void testSudokuGUI() {
		
		PuzzleRepository mockPuzzleRepo = Mockito.mock(PuzzleRepository.class);
		
		SudokuGUI sudokuGUI = new SudokuGUI(mockPuzzleRepo);
		JPanel mainJPanel = ((JPanel)((JLayeredPane)((JRootPane) sudokuGUI.getComponents()[0]).getComponents()[1]).getComponents()[0]);
		
		JRadioButton easyRadioButton = (JRadioButton)mainJPanel.getComponents()[6];
		System.out.println("easy : " + easyRadioButton.getText());
		easyRadioButton.setSelected(true);
		
		JButton startButton = (JButton)mainJPanel.getComponents()[18];
		System.out.println("New Game : " + startButton.getText());
		startButton.doClick();
		
//		sudokuGUI.get
		
		
//		btnNewGame.addActionListener(new java.awt.event.ActionListener() {
//			public void actionPerformed(java.awt.event.ActionEvent evt) {
//				btnNewGameActionPerformed(evt);
//			}
//		});
		
		
		System.out.println("========");
				
		
		BoardGUI boardGUI = (BoardGUI)mainJPanel.getComponents()[0];
		
		NumberButtonGUI numberButtonGUI = (NumberButtonGUI)mainJPanel.getComponents()[7];
		
		JRadioButton medRadioButton = (JRadioButton)mainJPanel.getComponents()[3];
		System.out.println("med : " + medRadioButton.getText());
		
		JRadioButton hardRadioButton = (JRadioButton)mainJPanel.getComponents()[4];
		System.out.println("hard : " + hardRadioButton.getText());
		
		
//		JButton startButton = (JButton)mainJPanel.getComponents()[18];
//		System.out.println("New Game : " + startButton.getText());
		
		int i = 0;
		for (Component eachComponent : mainJPanel.getComponents()) {
			
			System.out.println(i++ + " :  " + eachComponent.getClass().getName());
//			if (eachComponent instanceof JRadioButton) {
//				
//			}
		}
	}

	@Test
	public void testSetMessage() {
		fail("Not yet implemented");
	}

	@Test
	public void testClearMessage() {
		fail("Not yet implemented");
	}

	@Test
	public void testCellChanged() {
		fail("Not yet implemented");
	}

	@Test
	public void testMain() {
		fail("Not yet implemented");
	}

}
