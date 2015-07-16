package edu.psu.sweng500.team8.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter.HighlightPainter;

import edu.psu.sweng500.team8.coreDataStructures.Cell;
import edu.psu.sweng500.team8.coreDataStructures.Cell.ValueType;
import edu.psu.sweng500.team8.play.GameSession;

public class CellGUI extends JPanel {

	private static final int NUMBER_SIZE = 16;
	private static final int CELL_SIZE = 54;
	private static final Border SELECTED_BORDER = BorderFactory
			.createLineBorder(Color.blue, 3);
	private static final Border DEFAULT_BORDER = BorderFactory
			.createLineBorder(Color.black, 1);
	private static final Dimension CELL_DIMENSION = new Dimension(CELL_SIZE,
			CELL_SIZE);
	private static final Dimension NUMBER_DIMENSION = new Dimension(
			NUMBER_SIZE, NUMBER_SIZE);
	private static final HighlightPainter GIVEN_NUMBER_PAINTER = new DefaultHighlighter.DefaultHighlightPainter(
			Color.green);
	private static final HighlightPainter INCORRECT_NUMBER_PAINTER = new DefaultHighlighter.DefaultHighlightPainter(
			Color.red);

	private GameSession gameSession;
	private Cell cell;

	private JPanel numberInputCell;
	private JTextField numberInputField;
	private JPanel pencilMarkInputCell;
	private JPanel pencilMarkDisplayCell;
	private JLabel[][] pencilMarkDisplay;

	private MouseAdapter mouseAdapter;

	public CellGUI() {

		this.setMaximumSize(CELL_DIMENSION);
		this.setMinimumSize(CELL_DIMENSION);
		this.setPreferredSize(CELL_DIMENSION);
		this.setLayout(new GridBagLayout());

		this.initPencilMarkDisplayCell();
		this.initNumberInputCell();
		this.initPencilMarkInputCell();
	}

	public void populatePencilMark(Cell cell, GameSession gameSession) {

		this.numberInputCell.setVisible(false);
		this.pencilMarkDisplayCell.setVisible(false);
		this.pencilMarkInputCell.setVisible(true);

		this.pencilMarkInputCell.removeAll();
		if (cell.hasNumber()) {
			this.pencilMarkInputCell.setLayout(new BorderLayout());
			this.pencilMarkInputCell.setBorder(null);
			this.pencilMarkInputCell.add(buildReadOnlyTextField(cell));
		} else {
			this.pencilMarkInputCell.setLayout(new GridBagLayout());
			this.pencilMarkInputCell.setBorder(DEFAULT_BORDER);
			this.pencilMarkInputCell.add(buildPencilMarkInputField(cell));
		}
	}

	public void populate(Cell cell, GameSession gameSession, boolean isRefresh,
			FocusAdapter focusAdapter, MouseAdapter mouseAdapter) {

		this.gameSession = gameSession;
		this.cell = cell;
		this.mouseAdapter = mouseAdapter;

		/* Clear */
		this.numberInputField.setText("");
		this.numberInputField.addKeyListener(new CustomKeyListener(cell,
				gameSession));

		if (!isRefresh) {
			this.numberInputField.getHighlighter().removeAllHighlights();
			this.numberInputField.addFocusListener(focusAdapter);
			this.numberInputField.setBorder(DEFAULT_BORDER);
		}

		if (cell.hasNumber()) {
			this.numberInputCell.setVisible(true);
			this.pencilMarkDisplayCell.setVisible(false);
			this.pencilMarkInputCell.setVisible(false);
			this.numberInputField.setText(Integer.toString(cell.getNumber()));

			if (!isRefresh) {
				markGivenCell();
				this.numberInputField.setEditable(false);
				this.numberInputField.setFocusable(false);
			}

		} else {

			this.pencilMarkDisplayCell.setVisible(false);
			this.pencilMarkInputCell.setVisible(false);

			if (cell.getPencilMarks().isEmpty()) {
				this.numberInputCell.setVisible(true);
				this.numberInputField.setEditable(true);
				this.numberInputField.setFocusable(true);

			} else {
				cellLostFocus(null);
			}
		}
	}

	private void initPencilMarkInputCell() {
		this.pencilMarkInputCell = new JPanel();
		this.pencilMarkInputCell.setPreferredSize(new Dimension(CELL_SIZE,
				CELL_SIZE));
		this.pencilMarkInputCell.setMinimumSize(new Dimension(CELL_SIZE,
				CELL_SIZE));
		this.pencilMarkInputCell.setMaximumSize(new Dimension(CELL_SIZE,
				CELL_SIZE));
		this.pencilMarkInputCell.setVisible(false);
		this.add(this.pencilMarkInputCell);
	}

	private void initNumberInputCell() {
		this.numberInputCell = new JPanel();
		this.numberInputCell.setPreferredSize(new Dimension(CELL_SIZE,
				CELL_SIZE));
		this.numberInputCell
				.setMinimumSize(new Dimension(CELL_SIZE, CELL_SIZE));
		this.numberInputCell
				.setMaximumSize(new Dimension(CELL_SIZE, CELL_SIZE));
		this.numberInputCell.setVisible(true);

		this.numberInputCell.setLayout(new BorderLayout());

		this.add(this.numberInputCell);
		this.numberInputField = new JTextField();
		this.numberInputField
				.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		this.numberInputField.setBorder(DEFAULT_BORDER);

		this.numberInputCell.add(this.numberInputField);
	}

	/* TODO - This can be either combined with CombinationCell or its own class */
	private void initPencilMarkDisplayCell() {

		this.pencilMarkDisplay = new JLabel[3][3];
		this.pencilMarkDisplayCell = new JPanel();
		this.pencilMarkDisplayCell.setPreferredSize(CELL_DIMENSION);
		this.pencilMarkDisplayCell.setMinimumSize(CELL_DIMENSION);
		this.pencilMarkDisplayCell.setMaximumSize(CELL_DIMENSION);
		this.pencilMarkDisplayCell.setLayout(new GridBagLayout());
		this.pencilMarkDisplayCell.setBorder(DEFAULT_BORDER);
		this.pencilMarkDisplayCell.setVisible(false);
		this.add(this.pencilMarkDisplayCell);

		GridBagConstraints gridBagConstraints;

		for (int rowIndex = 0; rowIndex < 3; rowIndex++) {
			for (int columnIndex = 0; columnIndex < 3; columnIndex++) {

				JLabel numberLabel = new JLabel();
				numberLabel.setHorizontalAlignment(SwingConstants.CENTER);
				numberLabel.setMaximumSize(NUMBER_DIMENSION);
				numberLabel.setMinimumSize(NUMBER_DIMENSION);
				numberLabel.setPreferredSize(NUMBER_DIMENSION);

				gridBagConstraints = new GridBagConstraints();
				gridBagConstraints.gridx = columnIndex;
				gridBagConstraints.gridy = rowIndex;
				gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;

				this.pencilMarkDisplayCell.add(numberLabel, gridBagConstraints);
				this.pencilMarkDisplay[rowIndex][columnIndex] = numberLabel;
			}
		}
	}

	/**
	 * Utility method to mark current CellGUI's JTextField
	 */
	private void markGivenCell() {

		try {
			// (JN): What is the significance of 0,3?
			this.numberInputField.getHighlighter().addHighlight(0, 3,
					GIVEN_NUMBER_PAINTER);
		} catch (BadLocationException e) {
			/* TODO - handle properly */
			e.printStackTrace();
		}
	}

	/**
	 * Update numberInputField's border to SELECTED_BORDER
	 * 
	 */
	public void selectCell() {

		this.numberInputField.setBorder(SELECTED_BORDER);
	}

	/**
	 * 
	 * set number to numberInputField, make numberInputCell visible, and select
	 * the cell
	 * 
	 * @param number
	 * 
	 */
	public void updateSelectedCellFromHint(int number) {
		this.numberInputField.setText(Integer.toString(number));

		this.numberInputCell.setVisible(true);
		this.pencilMarkDisplayCell.setVisible(false);
		this.pencilMarkInputCell.setVisible(false);

		this.selectCell();
	}

	/**
	 * Update numberInputField's border to DEFAULT_BORDER
	 */
	public void unselect() {

		this.numberInputField.setBorder(DEFAULT_BORDER);
	}

	private JTextField buildReadOnlyTextField(Cell cell) {
		JTextField textField = new JTextField();
		textField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		textField.setBorder(DEFAULT_BORDER);
		textField.setEditable(false);
		textField.setText(Integer.toString(cell.getNumber()));

		if (ValueType.Given.equals(cell.getType())) {
			HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(
					Color.green);

			try {
				textField.getHighlighter().addHighlight(0, 3, painter);
			} catch (BadLocationException e) {
				/* TODO - better error handling */
				e.printStackTrace();
			}
		}

		return textField;
	}

	private CombinationCell buildPencilMarkInputField(Cell cell) {
		CombinationCell combinationCell = new CombinationCell(cell,
				this.gameSession);

		/*
		 * disable any number used according to the cellConstraints for given
		 * cell
		 */
		for (int usedNumber : this.gameSession.getGameBoard()
				.getCellConstraints(cell).getUsedNumbers()) {
			((JToggleButton) combinationCell.getComponent(usedNumber - 1))
					.setEnabled(false);
			((JToggleButton) combinationCell.getComponent(usedNumber - 1))
					.setText("");
		}

		for (int k = 1; k <= 9; k++) {
			if (cell.getPencilMarks().contains(k)) {
				((JToggleButton) combinationCell.getComponent(k - 1))
						.setSelected(true);
			} else {
				((JToggleButton) combinationCell.getComponent(k - 1))
						.setSelected(false);
			}
		}

		return combinationCell;
	}

	public void cellLostFocus(FocusEvent focusEvent) {

		CellGUI selectedCell = this;

		if (null != focusEvent) {
			// if (focusEvent.getSource() instanceof JTextField) {
			selectedCell = (CellGUI) ((JTextField) focusEvent.getSource())
					.getParent().getParent();
			// } else {
			// /* */
			// System.err.println("This is not supported.");
			// }
		}

		selectedCell.numberInputField.setBorder(DEFAULT_BORDER);

		if (this.cell.hasNumber()) {
			/* Number */
			selectedCell.numberInputCell.setVisible(true);
			selectedCell.pencilMarkDisplayCell.setVisible(false);
			selectedCell.pencilMarkInputCell.setVisible(false);
		} else {
			if (this.cell.getPencilMarks().isEmpty()) {
				/* Nothing */
				selectedCell.numberInputCell.setVisible(true);
				selectedCell.pencilMarkDisplayCell.setVisible(false);
				selectedCell.pencilMarkInputCell.setVisible(false);
			} else {
				/* Pencil Marks */
				selectedCell.numberInputCell.setVisible(false);
				selectedCell.pencilMarkDisplayCell.setVisible(true);
				selectedCell.pencilMarkInputCell.setVisible(false);
				for (int i = 1; i <= 9; i++) {

					int rowIndex = ((i - 1) / 3);
					int columnIndex = ((i - 1) % 3);

					this.pencilMarkDisplay[rowIndex][columnIndex]
							.setVisible(true);
					this.pencilMarkDisplay[rowIndex][columnIndex]
							.addMouseListener(this.mouseAdapter);

					if (this.cell.getPencilMarks().contains(i)) {
						this.pencilMarkDisplay[rowIndex][columnIndex]
								.setText(String.valueOf(i));
					} else {
						this.pencilMarkDisplay[rowIndex][columnIndex]
								.setText("");
					}
				}
			}
		}
	}

	/**
	 * 
	 * switch from Pencil Mark display mode to number input mode, select
	 * CellGUI, and set focus to numberInputField so any previous set can be
	 * lost
	 */
	public void switchtoNumberInput() {

		this.pencilMarkDisplayCell.setVisible(false);
		this.numberInputCell.setVisible(true);
		this.selectCell();
		this.numberInputField.requestFocus();
	}

	/**
	 * mark CellGUI for incorrect number
	 */
	public void markIncorrectCell() {

		try {
			this.numberInputField.getHighlighter().addHighlight(0, 3,
					INCORRECT_NUMBER_PAINTER);
			this.numberInputField.repaint();
		} catch (BadLocationException e) {
			/* TODO - handle properly */
			e.printStackTrace();
		}
	}

	/**
	 * remove marks from CellGUI
	 */
	public void clearIncorrectCellMark() {
		this.numberInputField.getHighlighter().removeAllHighlights();
	}

	/**
	 * disable this CellGUI from being edited
	 */
	public void disableEditing() {
		this.numberInputField.setFocusable(false);
		this.numberInputField.setEditable(false);
	}

	public void refreshPencilMarkDisplay() {
		if (this.cell.getPencilMarks().isEmpty()) {
			/*
			 * If this cell had a number, we don't have to worry about doing
			 * anything If this cell got its last pencil mark deleted, we need
			 * to make sure number cell is displayed now. Simply enabling number
			 * input cell and disabling others is enough
			 */
			this.numberInputCell.setVisible(true);
			this.pencilMarkDisplayCell.setVisible(false);
			this.pencilMarkInputCell.setVisible(false);
		} else {
			/*
			 * This cell either got its pencil mark updated or the pencil mark
			 * is the same regardless, we can simply re-populate pencil mark
			 * display cell
			 */

			/*
			 * TODO - take this logic out as a separate method as it is being
			 * used in other places
			 */
			for (int i = 1; i <= 9; i++) {

				int rowIndex = ((i - 1) / 3);
				int columnIndex = ((i - 1) % 3);

				this.pencilMarkDisplay[rowIndex][columnIndex].setVisible(true);
				this.pencilMarkDisplay[rowIndex][columnIndex]
						.addMouseListener(this.mouseAdapter);

				if (this.cell.getPencilMarks().contains(i)) {
					this.pencilMarkDisplay[rowIndex][columnIndex]
							.setText(String.valueOf(i));
				} else {
					this.pencilMarkDisplay[rowIndex][columnIndex].setText("");
				}
			}
		}
	}
}