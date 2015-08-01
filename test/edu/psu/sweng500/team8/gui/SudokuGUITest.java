package edu.psu.sweng500.team8.gui;

import static org.junit.Assert.fail;

import java.awt.Component;

import javax.swing.JPanel;

import org.junit.Test;

public class SudokuGUITest {

	@Test
	public void testSudokuGUI() {
		
		SudokuGUI sudokuGUI = new SudokuGUI(null);
		
		System.out.println("========");
				
		for (Component eachComponent : ((JPanel) sudokuGUI.getComponents()[0]).getComponents()) {
		
			System.out.println(eachComponent.getClass().getName());
			
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
