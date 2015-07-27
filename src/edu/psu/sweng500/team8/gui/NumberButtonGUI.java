package edu.psu.sweng500.team8.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JToggleButton;

import edu.psu.sweng500.team8.coreDataStructures.Cell;
import edu.psu.sweng500.team8.play.GameSession;

public class NumberButtonGUI extends javax.swing.JPanel {

	private static final int BUTTON_SIZE = 54;
	private final static int BUTTON_TEXT_SIZE = 40;
	private final static Font BUTTON_FONT = new Font("Tahoma", Font.PLAIN,
			BUTTON_TEXT_SIZE);
	private final static Dimension BUTTON_DIMENSION = new Dimension(
			BUTTON_SIZE, BUTTON_SIZE);

	private GameSession gameSession;

	private JToggleButton[] buttons = new JToggleButton[9];

	public void init(MouseAdapter mouseAdapter, GameSession gameSession) {

		this.gameSession = gameSession;

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
			/**/
			if (cell.hasNumber()) {

				if (cell.getPencilMarks().isEmpty()) {

					/*
					 * On Pencil Mark Mode, if we have a number, we do not allow
					 * update
					 */
					for (int numberIndex = 1; numberIndex <= 9; numberIndex++) {
						buttons[numberIndex - 1].setEnabled(false);
					}

					buttons[cell.getNumber() - 1].setSelected(true);
				} else {
					throw new RuntimeException(
							"Cell cannot have both Number and Pencil Marks at the same time.");
				}
			} else {

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

				for (int pencilMarked : cell.getPencilMarks()) {
					buttons[pencilMarked - 1].setSelected(true);
				}
			}
		} else {
			/**/
			if (cell.hasNumber()) {

				if (cell.getPencilMarks().isEmpty()) {
					buttons[cell.getNumber() - 1].setSelected(true);
				} else {
					throw new RuntimeException(
							"Cell cannot have both Number and Pencil Marks at the same time.");
				}
			} else {
				/* On Normal Mode, we only select Number */
			}
		}

	}
}
