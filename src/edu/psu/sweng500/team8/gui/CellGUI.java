package edu.psu.sweng500.team8.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	private static final long serialVersionUID = 1L; //Not really necessary since we're not serializing the UI, but just to keep Java happy...
	
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
	private JPanel pencilMarkDisplayCell;
	private JLabel[][] pencilMarkDisplay;

	private boolean isPencilMarkMode;

	public CellGUI() {

		this.setMaximumSize(CELL_DIMENSION);
		this.setMinimumSize(CELL_DIMENSION);
		this.setPreferredSize(CELL_DIMENSION);
		this.setLayout(new GridBagLayout());

		this.initPencilMarkDisplayCell();
		this.initNumberInputCell();
	}

	public void populate(Cell cell, GameSession gameSession, boolean isRefresh,
			FocusAdapter focusAdapter, MouseAdapter mouseAdapter,
			boolean isPencilMarkMode) {

		this.gameSession = gameSession;
		this.cell = cell;
		this.isPencilMarkMode = isPencilMarkMode;

		/* Clear */
		this.numberInputField.setText("");

		if (!isRefresh) {
			this.numberInputField.getHighlighter().removeAllHighlights();
			this.numberInputField.addFocusListener(focusAdapter);
			this.numberInputField.setBorder(DEFAULT_BORDER);

			for (int rowIndex = 0; rowIndex < 3; rowIndex++) {
				for (int columnIndex = 0; columnIndex < 3; columnIndex++) {
					this.pencilMarkDisplay[rowIndex][columnIndex]
							.addMouseListener(mouseAdapter);		
				}
			}
		}

		if (cell.hasNumber()) {
			this.numberInputCell.setVisible(true);
			this.pencilMarkDisplayCell.setVisible(false);
			this.numberInputField.setText(Integer.toString(cell.getNumber()));

			if (!isRefresh) {
				if (ValueType.Given.equals(cell.getType())) {
					markGivenCell();
					this.numberInputField.setEditable(false);
					this.numberInputField.setFocusable(false);
				}
			}

			if (isPencilMarkMode) {
				this.numberInputField.setEditable(false);
				this.numberInputField.setFocusable(false);
			} else {
				if (!ValueType.Given.equals(cell.getType())) {
					this.numberInputField.setEditable(false); //set to false to kill the cursor
					this.numberInputField.setFocusable(true);
				}
			}

		} else {

			this.pencilMarkDisplayCell.setVisible(false);

			if (cell.getPencilMarks().isEmpty()) {
				this.numberInputCell.setVisible(true);
				this.numberInputField.setEditable(false); //set to false to kill the cursor
				this.numberInputField.setFocusable(true);

			} else {
				/* Pencil Marks */
				this.numberInputCell.setVisible(false);
				this.pencilMarkDisplayCell.setVisible(true);
				
				for (int i = 1; i <= 9; i++) {
					
					int rowIndex = ((i - 1) / 3);
					int columnIndex = ((i - 1) % 3);

					this.pencilMarkDisplay[rowIndex][columnIndex]
							.setVisible(true);

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
			e.printStackTrace();
		}
	}

	/**
	 * Update numberInputField's border to SELECTED_BORDER
	 * 
	 */
	public void selectCell() {

		this.numberInputField.setBorder(SELECTED_BORDER);
		this.pencilMarkDisplayCell.setBorder(SELECTED_BORDER);
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
		if (number != 0)
			this.numberInputField.setText(Integer.toString(number));

		this.numberInputCell.setVisible(true);
		this.pencilMarkDisplayCell.setVisible(false);

		this.selectCell();
	}

	public void setNumberToCell(MouseEvent mouseEvent) {

		JToggleButton theClickedButton = ((JToggleButton) mouseEvent
				.getSource());
		String keyValue = theClickedButton.getText();
		boolean isSelcted = theClickedButton.isSelected();
		JPanel numberPad = (JPanel) theClickedButton.getParent();

		if (isPencilMarkMode) {
			/* number clicked during pencil mark mode */
			int numberInt = Integer.parseInt(keyValue);
			this.gameSession.enterPencilMark(this.cell, numberInt, isSelcted);
			
		} else {

			/*
			 * If in normal mode, multiple-selection should not be allowed
			 * unselect other buttons there is a better way of doing this, but
			 * this is a hack
			 */
			for (int index = 0; index < 9; index++) {
				JToggleButton eachButton = (JToggleButton) numberPad
						.getComponent(index);
				if (!eachButton.getText().equals(theClickedButton.getText())) {
					eachButton.setSelected(false);
				}
			}

			if (isSelcted) {
				this.numberInputField.setText(keyValue);
				int numberInt = Integer.parseInt(keyValue);
				this.gameSession.enterNumber(this.cell, numberInt);
			} else {
				this.numberInputField.setText("");
				this.gameSession.enterNumber(this.cell, 0);
			}
		}
	}

	/**
	 * Update numberInputField's border to DEFAULT_BORDER
	 */
	public void unselect() {

		this.numberInputField.setBorder(DEFAULT_BORDER);
		this.pencilMarkDisplayCell.setBorder(DEFAULT_BORDER);
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
		} else {

			this.numberInputCell.setVisible(false);
			this.pencilMarkDisplayCell.setVisible(true);

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

				if (this.cell.getPencilMarks().contains(i)) {
					this.pencilMarkDisplay[rowIndex][columnIndex]
							.setText(String.valueOf(i));
				} else {
					this.pencilMarkDisplay[rowIndex][columnIndex].setText("");
				}
			}
		}
	}

	public Cell getCell() {
		return this.cell;
	}
	
	/**
	 * public for testing
	 * @return
	 */
	public JTextField getNumberInputField() {
		return this.numberInputField;
	}
	
	/**
	 * public for testing
	 * @return
	 */
	public JPanel getpPencilMarkDisplayCell() {
		return this.pencilMarkDisplayCell;
	}
}