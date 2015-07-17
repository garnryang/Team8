package edu.psu.sweng500.team8.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JToggleButton;

import edu.psu.sweng500.team8.coreDataStructures.Cell;
import edu.psu.sweng500.team8.play.GameSession;

/**
 * @deprecated
 *
 */
public class CombinationCell extends javax.swing.JPanel {

	private final static int PENCIL_MARK_SIZE = 16;
	private final static int PENCIL_MARk_TEXT_SIZE = 15;
	private final static Color PENCIL_MARK_COLOR = Color.BLACK;
	private final static Color PENCIL_MARK_BACKGROUND_COLOR = Color.WHITE;
	private final static Dimension PENCIL_MARK_NUMBER_DIMENSION = new Dimension(
			PENCIL_MARK_SIZE, PENCIL_MARK_SIZE);
	private final static Font PENCIL_MARK_FONT = new Font("Tahoma", Font.PLAIN,
			PENCIL_MARk_TEXT_SIZE);
	private final static PencilMarkButtonUI BUTTON_UI = new PencilMarkButtonUI();
	private Cell cell;
	private GameSession gameSession;

	public CombinationCell(Cell cell, GameSession gameSession) {

		this.cell = cell;
		this.gameSession = gameSession;
		this.setLayout(new GridBagLayout());

		for (int numberIndex = 1; numberIndex <= 9; numberIndex++) {

			JToggleButton tglbtnNewToggleButton = new JToggleButton(
					String.valueOf(numberIndex));
			tglbtnNewToggleButton.setFont(PENCIL_MARK_FONT);
			tglbtnNewToggleButton.setForeground(PENCIL_MARK_COLOR);
			tglbtnNewToggleButton.setUI(BUTTON_UI);
			tglbtnNewToggleButton.setBackground(PENCIL_MARK_BACKGROUND_COLOR);
			tglbtnNewToggleButton
					.setPreferredSize(PENCIL_MARK_NUMBER_DIMENSION);
			tglbtnNewToggleButton.setMaximumSize(PENCIL_MARK_NUMBER_DIMENSION);
			tglbtnNewToggleButton.setMinimumSize(PENCIL_MARK_NUMBER_DIMENSION);
			tglbtnNewToggleButton.addActionListener(buildActionListner());

			GridBagConstraints gbc_tglbtnNewToggleButton = new GridBagConstraints();
			gbc_tglbtnNewToggleButton.gridx = (numberIndex - 1) % 3;
			gbc_tglbtnNewToggleButton.gridy = (numberIndex - 1) / 3;
			this.add(tglbtnNewToggleButton, gbc_tglbtnNewToggleButton);
		}
	}

	private ActionListener buildActionListner() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				buttonAction(evt);
			}
		};
	}

	private void buttonAction(ActionEvent actionEvent) {

		JToggleButton button = (JToggleButton) actionEvent.getSource();

		boolean isSelected = button.isSelected();
		this.gameSession.enterPencilMark(this.cell,
				Integer.parseInt(button.getText()), isSelected);
	}
}
