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
 * @author ScottL
 *
 */
public class CustomKeyListener extends KeyAdapter implements KeyListener {

	private Cell cell;
	private GameSession gameSession;

	public CustomKeyListener(Cell cell, GameSession gameSession) {
		this.cell = cell;
		this.gameSession = gameSession;
	}

	/* TODO problem with this approach is any key after a number is entered will delete the number 
	 * We could differentiate number keys and some other valid keys such as delete, backspace, arrow, etc.
	 * to give them a specific bahavior to be handled so we don't have users accidentally delete entered number by pressing an arrow key 
	 * Please share your ideas on how we can improve this */
	public void keyReleased(KeyEvent keyEvent) {

		JTextField textField = (JTextField) keyEvent.getSource();
		String text = textField.getText();

		try {

			int enteredNumber = Integer.parseInt(text);

			if (enteredNumber < 1 || enteredNumber > 9) {
				gameSession.enterNumber(this.cell, 0);
				textField.setText("");
			} else {
				gameSession
						.enterNumber(this.cell, Integer.parseInt(String
								.valueOf(keyEvent.getKeyChar())));
			}

		} catch (NumberFormatException ex) {
			gameSession.enterNumber(this.cell, 0);
			textField.setText("");
		}
	}
}