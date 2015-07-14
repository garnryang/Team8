package edu.psu.sweng500.team8.gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

import edu.psu.sweng500.team8.coreDataStructures.Cell;
import edu.psu.sweng500.team8.play.GameSession;

/**
 * Prototype until we have better number entering system
 * 
 *
 */
public class CustomKeyListener extends KeyAdapter implements KeyListener {

	private Cell cell;
	private GameSession gameSession;

	public CustomKeyListener(Cell cell, GameSession gameSession) {
		this.cell = cell;
		this.gameSession = gameSession;
	}

	/*
	 * TODO problem with this approach is any key after a number is entered will
	 * delete the number We could differentiate number keys and some other valid
	 * keys such as delete, backspace, arrow, etc. to give them a specific
	 * bahavior to be handled so we don't have users accidentally delete entered
	 * number by pressing an arrow key Please share your ideas on how we can
	 * improve this
	 */
	/*
	 * (JN): Use the actual text box text as David did -- this will handle the
	 * Delete/arrow keys. I changed this. An enhancement idea would be to use
	 * the arrow keys to select adjacent cells (e.g. right arrow moves one cell
	 * to the right)
	 */
	public void keyReleased(KeyEvent keyEvent) {

		JTextField textField = (JTextField) keyEvent.getSource();
		String text = textField.getText();

		try {

			int enteredNumber = Integer.parseInt(text);

			if (enteredNumber < 1 || enteredNumber > 9) {
				this.gameSession.enterNumber(this.cell, 0);
				textField.setText("");
			} else {
				this.gameSession.enterNumber(this.cell, enteredNumber);
			}

		} catch (NumberFormatException ex) {
			this.gameSession.enterNumber(this.cell, 0);
			textField.setText("");
		}
	}
}