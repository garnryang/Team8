package edu.psu.sweng500.team8.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;

import javax.swing.JToggleButton;

import edu.psu.sweng500.team8.coreDataStructures.Cell;
import edu.psu.sweng500.team8.play.GameSession;

public class NumberButtonGUI extends javax.swing.JPanel {
	private static final long serialVersionUID = 1L; //Not really necessary since we're not serializing the UI, but just to keep Java happy...
	
	private static final int BUTTON_SIZE = 54;
	private final static int BUTTON_TEXT_SIZE = 40;
	private final static Font BUTTON_FONT = new Font("Tahoma", Font.PLAIN,
			BUTTON_TEXT_SIZE);
	private final static Dimension BUTTON_DIMENSION = new Dimension(
			BUTTON_SIZE, BUTTON_SIZE);

	private GameSession gameSession;

	private JToggleButton[] buttons = new JToggleButton[9];
	
	public void init(GameSession gameSession) {
		this.gameSession = gameSession;
	}
	
	public void addMouseListener(MouseAdapter mouseAdapter) {
		for (int numberIndex = 1; numberIndex <= 9; numberIndex++) {
			buttons[numberIndex - 1].addMouseListener(mouseAdapter);
		}
	}

	public NumberButtonGUI() {

		this.setLayout(new GridBagLayout());

		for (int numberIndex = 1; numberIndex <= 9; numberIndex++) {

			/* 1 - 9 */
			JToggleButton numberInputButton = new JToggleButton(
					String.valueOf(numberIndex));

			numberInputButton.setFont(BUTTON_FONT);
			numberInputButton.setPreferredSize(BUTTON_DIMENSION);
			numberInputButton.setMaximumSize(BUTTON_DIMENSION);
			numberInputButton.setMinimumSize(BUTTON_DIMENSION);

			GridBagConstraints buttonGridBagConstraints = new GridBagConstraints();
			buttonGridBagConstraints.gridx = (numberIndex - 1) % 3;
			buttonGridBagConstraints.gridy = (numberIndex - 1) / 3;
			this.add(numberInputButton, buttonGridBagConstraints);
			buttons[numberIndex - 1] = numberInputButton;
		}

	}

	public void updateForFocusedCell(Cell cell) {
		
		boolean isPencilMarkMode = this.gameSession.isPencilMarkMode();

		/* Clear */
		for (int numberIndex = 1; numberIndex <= 9; numberIndex++) {
			buttons[numberIndex - 1].setSelected(false);
			buttons[numberIndex - 1].setEnabled(true);
		}

		if (isPencilMarkMode) {
			/* During PencilMark mode, a Cell with a number cannot be selected/focused.
			 * Only a cell with no number can be selected/focused. */
			if (!cell.hasNumber()) {

				/*
				 * TODO - 
				 * Disabling Pencil Mark Input might give out too much
				 * information Maybe we can enable this feature only for EASY
				 * mode?
				 */
				/*
				 * Set<Integer> unavailableNumbers =
				 * this.gameSession.getGameBoard()
				 * .getCellConstraints(cell).getUsedNumbers();
				 * 
				 * 
				 * for (int usedNumber : unavailableNumbers) {
				 * buttons[usedNumber-1].setEnabled(false); }
				 */

				/* For a cell with no number, any number in PencilMark set is pre-selected */
				for (int pencilMarked : cell.getPencilMarks()) {
					buttons[pencilMarked - 1].setSelected(true);
				}
			}
		} else {
			/* During Normal mode, a Cell with no number does not have to get NumberPad pre-selected */
			if (cell.hasNumber() && cell.getPencilMarks().isEmpty()) {
					buttons[cell.getNumber() - 1].setSelected(true);
			}
		}
	}

	/**
	 * for testing
	 * @return
	 */
	JToggleButton[] getButtons() {
		return this.buttons;
	}

}
