package edu.psu.sweng500.team8.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter.HighlightPainter;

import edu.psu.sweng500.team8.coreDataStructures.Cell;
import edu.psu.sweng500.team8.coreDataStructures.Cell.ValueType;
import edu.psu.sweng500.team8.play.GameSession;

public class CellGUI extends JPanel {

	private GameSession gameSession;
	private JPanel numberInputCell;
	private JTextField numberInputField;

	private JPanel pencilMarkInputCell;

	private static int NUMBER_SIZE = 16;
	private static int CELL_SIZE = 54;
	private static final Border SELECTED_BORDER = BorderFactory
			.createLineBorder(Color.blue, 3);
	private static final Border DEFAULT_BORDER = BorderFactory
			.createLineBorder(Color.black, 1);
	private Cell cell;

	private MouseAdapter mouseAdapter;
	
	/* Pencil Mark Display */
	private JPanel pencilMarkDisplayCell;
	private JLabel[][] pencilMarkDisplay;

	public CellGUI() {

		setMaximumSize(new Dimension(CELL_SIZE, CELL_SIZE));
		setMinimumSize(new Dimension(CELL_SIZE, CELL_SIZE));
		setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));

		setBorder(new LineBorder(new Color(0, 0, 0), 0));
		setLayout(new java.awt.GridBagLayout());

		initPencilMarkDisplayCell();
		initNumberInputCell();
		initPencilMarkInputCell();
	}

	public void populatePencilMark(Cell cell, GameSession gameSession) {

		this.numberInputCell.setVisible(false);
		this.pencilMarkDisplayCell.setVisible(false);
		this.pencilMarkInputCell.setVisible(true);

		pencilMarkInputCell.removeAll();
		if (cell.hasNumber()) {
			pencilMarkInputCell.setLayout(new BorderLayout());
			pencilMarkInputCell.setBorder(null);
			pencilMarkInputCell.add(buildReadOnlyTextField(cell));
		} else {
			pencilMarkInputCell.setLayout(new GridBagLayout());
			pencilMarkInputCell.setBorder(DEFAULT_BORDER);
			pencilMarkInputCell.add(buildPencilMarkInputField(cell));
		}
	}

	public void populate(Cell cell, GameSession gameSession, boolean isRefresh, FocusAdapter focusAdapter, MouseAdapter mouseAdapter) {

		this.gameSession = gameSession;
		this.cell = cell;
		this.mouseAdapter = mouseAdapter;

		/* Clear */
		this.numberInputField.setText("");
		this.numberInputField.addKeyListener(new CustomKeyListener(
				cell, gameSession));

		if (!isRefresh) {
			this.numberInputField.getHighlighter().removeAllHighlights();
			this.numberInputField.addFocusListener(focusAdapter);
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
		pencilMarkInputCell = new JPanel();
		pencilMarkInputCell
				.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
		pencilMarkInputCell.setMinimumSize(new Dimension(CELL_SIZE, CELL_SIZE));
		pencilMarkInputCell.setMaximumSize(new Dimension(CELL_SIZE, CELL_SIZE));
		pencilMarkInputCell.setVisible(false);
		add(pencilMarkInputCell);
	}

	private void initNumberInputCell() {
		numberInputCell = new JPanel();
		numberInputCell.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
		numberInputCell.setMinimumSize(new Dimension(CELL_SIZE, CELL_SIZE));
		numberInputCell.setMaximumSize(new Dimension(CELL_SIZE, CELL_SIZE));
		numberInputCell.setVisible(true);

		numberInputCell.setLayout(new BorderLayout());

		add(numberInputCell);
		numberInputField = new JTextField();
		numberInputField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		numberInputField.setBorder(DEFAULT_BORDER);

		numberInputCell.add(numberInputField);
	}

	/* TODO - This can be either combined with CombinationCell or its own class */
	private void initPencilMarkDisplayCell() {
		pencilMarkDisplayCell = new JPanel();
		pencilMarkDisplayCell.setPreferredSize(new Dimension(CELL_SIZE,
				CELL_SIZE));
		pencilMarkDisplayCell
				.setMinimumSize(new Dimension(CELL_SIZE, CELL_SIZE));
		pencilMarkDisplayCell
				.setMaximumSize(new Dimension(CELL_SIZE, CELL_SIZE));

		pencilMarkDisplayCell.setVisible(false);

		add(pencilMarkDisplayCell);

		pencilMarkDisplay = new JLabel[3][3];

		GridBagConstraints gridBagConstraints;
		pencilMarkDisplayCell.setLayout(new java.awt.GridBagLayout());
		pencilMarkDisplayCell.setBorder(DEFAULT_BORDER);

		pencilMarkDisplay[0][0] = new JLabel();
		pencilMarkDisplay[0][0]
				.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		pencilMarkDisplay[0][0].setText("1");
		pencilMarkDisplay[0][0].setMaximumSize(new java.awt.Dimension(
				NUMBER_SIZE, NUMBER_SIZE));
		pencilMarkDisplay[0][0].setMinimumSize(new java.awt.Dimension(
				NUMBER_SIZE, NUMBER_SIZE));
		pencilMarkDisplay[0][0].setPreferredSize(new java.awt.Dimension(
				NUMBER_SIZE, NUMBER_SIZE));
		pencilMarkDisplay[0][0].setVisible(false);
		
		
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		// gridBagConstraints.insets = new java.awt.Insets(1, 1, 0, 0);
		pencilMarkDisplayCell.add(pencilMarkDisplay[0][0], gridBagConstraints);

		pencilMarkDisplay[0][1] = new JLabel();
		pencilMarkDisplay[0][1]
				.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		pencilMarkDisplay[0][1].setText("2");
		pencilMarkDisplay[0][1].setMaximumSize(new java.awt.Dimension(
				NUMBER_SIZE, NUMBER_SIZE));
		pencilMarkDisplay[0][1].setMinimumSize(new java.awt.Dimension(
				NUMBER_SIZE, NUMBER_SIZE));
		pencilMarkDisplay[0][1].setPreferredSize(new java.awt.Dimension(
				NUMBER_SIZE, NUMBER_SIZE));
		pencilMarkDisplay[0][1].setVisible(false);
		
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		// gridBagConstraints.insets = new java.awt.Insets(1, 0, 0, 0);
		pencilMarkDisplayCell.add(pencilMarkDisplay[0][1], gridBagConstraints);

		pencilMarkDisplay[0][2] = new JLabel();
		pencilMarkDisplay[0][2]
				.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		pencilMarkDisplay[0][2].setText("3");
		pencilMarkDisplay[0][2].setMaximumSize(new java.awt.Dimension(
				NUMBER_SIZE, NUMBER_SIZE));
		pencilMarkDisplay[0][2].setMinimumSize(new java.awt.Dimension(
				NUMBER_SIZE, NUMBER_SIZE));
		pencilMarkDisplay[0][2].setPreferredSize(new java.awt.Dimension(
				NUMBER_SIZE, NUMBER_SIZE));
		pencilMarkDisplay[0][2].setVisible(false);
		
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		// gridBagConstraints.insets = new java.awt.Insets(1, 0, 0, 1);
		pencilMarkDisplayCell.add(pencilMarkDisplay[0][2], gridBagConstraints);

		pencilMarkDisplay[1][0] = new JLabel();
		pencilMarkDisplay[1][0]
				.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		pencilMarkDisplay[1][0].setText("4");
		pencilMarkDisplay[1][0].setMaximumSize(new java.awt.Dimension(
				NUMBER_SIZE, NUMBER_SIZE));
		pencilMarkDisplay[1][0].setMinimumSize(new java.awt.Dimension(
				NUMBER_SIZE, NUMBER_SIZE));
		pencilMarkDisplay[1][0].setPreferredSize(new java.awt.Dimension(
				NUMBER_SIZE, NUMBER_SIZE));
		pencilMarkDisplay[1][0].setVisible(false);
		
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		// gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
		pencilMarkDisplayCell.add(pencilMarkDisplay[1][0], gridBagConstraints);

		pencilMarkDisplay[1][1] = new JLabel();
		pencilMarkDisplay[1][1]
				.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		pencilMarkDisplay[1][1].setText("5");
		pencilMarkDisplay[1][1].setMaximumSize(new java.awt.Dimension(
				NUMBER_SIZE, NUMBER_SIZE));
		pencilMarkDisplay[1][1].setMinimumSize(new java.awt.Dimension(
				NUMBER_SIZE, NUMBER_SIZE));
		pencilMarkDisplay[1][1].setPreferredSize(new java.awt.Dimension(
				NUMBER_SIZE, NUMBER_SIZE));
		pencilMarkDisplay[1][1].setVisible(false);
		
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		pencilMarkDisplayCell.add(pencilMarkDisplay[1][1], gridBagConstraints);

		pencilMarkDisplay[1][2] = new JLabel();
		pencilMarkDisplay[1][2]
				.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		pencilMarkDisplay[1][2].setText("6");
		pencilMarkDisplay[1][2].setMaximumSize(new java.awt.Dimension(
				NUMBER_SIZE, NUMBER_SIZE));
		pencilMarkDisplay[1][2].setMinimumSize(new java.awt.Dimension(
				NUMBER_SIZE, NUMBER_SIZE));
		pencilMarkDisplay[1][2].setPreferredSize(new java.awt.Dimension(
				NUMBER_SIZE, NUMBER_SIZE));
		pencilMarkDisplay[1][2].setVisible(false);
		
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		// gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 1);
		pencilMarkDisplayCell.add(pencilMarkDisplay[1][2], gridBagConstraints);

		pencilMarkDisplay[2][0] = new JLabel();
		pencilMarkDisplay[2][0]
				.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		pencilMarkDisplay[2][0].setText("7");
		pencilMarkDisplay[2][0].setMaximumSize(new java.awt.Dimension(
				NUMBER_SIZE, NUMBER_SIZE));
		pencilMarkDisplay[2][0].setMinimumSize(new java.awt.Dimension(
				NUMBER_SIZE, NUMBER_SIZE));
		pencilMarkDisplay[2][0].setPreferredSize(new java.awt.Dimension(
				NUMBER_SIZE, NUMBER_SIZE));
		pencilMarkDisplay[2][0].setVisible(false);
		
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		// gridBagConstraints.insets = new java.awt.Insets(0, 1, 1, 0);
		pencilMarkDisplayCell.add(pencilMarkDisplay[2][0], gridBagConstraints);

		pencilMarkDisplay[2][1] = new JLabel();
		pencilMarkDisplay[2][1]
				.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		pencilMarkDisplay[2][1].setText("8");
		pencilMarkDisplay[2][1].setMaximumSize(new java.awt.Dimension(
				NUMBER_SIZE, NUMBER_SIZE));
		pencilMarkDisplay[2][1].setMinimumSize(new java.awt.Dimension(
				NUMBER_SIZE, NUMBER_SIZE));
		pencilMarkDisplay[2][1].setPreferredSize(new java.awt.Dimension(
				NUMBER_SIZE, NUMBER_SIZE));
		pencilMarkDisplay[2][1].setVisible(false);
		
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		// gridBagConstraints.insets = new java.awt.Insets(0, 0, 1, 0);
		pencilMarkDisplayCell.add(pencilMarkDisplay[2][1], gridBagConstraints);

		pencilMarkDisplay[2][2] = new JLabel();
		pencilMarkDisplay[2][2]
				.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		pencilMarkDisplay[2][2].setText("9");
		pencilMarkDisplay[2][2].setMaximumSize(new java.awt.Dimension(
				NUMBER_SIZE, NUMBER_SIZE));
		pencilMarkDisplay[2][2].setMinimumSize(new java.awt.Dimension(
				NUMBER_SIZE, NUMBER_SIZE));
		pencilMarkDisplay[2][2].setPreferredSize(new java.awt.Dimension(
				NUMBER_SIZE, NUMBER_SIZE));
		pencilMarkDisplay[2][2].setVisible(false);
		
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		// gridBagConstraints.insets = new java.awt.Insets(0, 0, 1, 1);
		pencilMarkDisplayCell.add(pencilMarkDisplay[2][2], gridBagConstraints);
	}


	private void markGivenCell() {
		HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(
				Color.green);

		/*
		 * TODO - THIS MAY NOT BE THE BEST PLACE TO HAVE THIS SET BUT I NEED
		 * THIS TO MAKE PENCIL MARK WORKING DO NOT FORGET REVISIT AND UPDATE
		 * ValueType logic
		 */
		cell.setType(ValueType.Given);

		try {
			// (JN): What is the significance of 0,3?
			this.numberInputField.getHighlighter().addHighlight(0, 3, painter);
		} catch (BadLocationException e) {
			e.printStackTrace();
			/* TODO - ignore or handle ? */
		}
	}

	private void selectCell(CellGUI selectedCell) {

		selectedCell.numberInputField.setBorder(SELECTED_BORDER);

		selectedCell.numberInputCell.setVisible(true);
		selectedCell.pencilMarkDisplayCell.setVisible(false);
		selectedCell.pencilMarkInputCell.setVisible(false);
	}
	
	public void selectCell() {
		selectCell(this);
	}
	
	public void updateSelectedCellFromHint(int number) {
		this.numberInputField.setText(Integer.toString(number));
		selectCell(this);
	}

	public void unselect() {
		this.numberInputField.setBorder(DEFAULT_BORDER);
	}

	private JTextField buildReadOnlyTextField(Cell cell) {
		JTextField textField = new JTextField();
		textField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		// textField.setBorder(javax.swing.BorderFactory.createLineBorder(new
		// java.awt.Color(0, 0, 0)));
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
			((JToggleButton) combinationCell.getComponent(usedNumber - 1)).setText("");;
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
			if (focusEvent.getSource() instanceof JTextField) {
				selectedCell = (CellGUI) ((JTextField) focusEvent.getSource())
						.getParent().getParent();
			} else {
				/* */
				System.out.println("This is not possible yet");
			}
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

					pencilMarkDisplay[rowIndex][columnIndex].setVisible(true);
					pencilMarkDisplay[rowIndex][columnIndex].addMouseListener(mouseAdapter);
					
					if (this.cell.getPencilMarks().contains(i)) {
						pencilMarkDisplay[rowIndex][columnIndex].setText(String.valueOf(i));
					} else {
						pencilMarkDisplay[rowIndex][columnIndex].setText("");
					}
				}
			}
		}
	}

	public void mouseClicked() {
		 /* switch from Pencil Mark display mode to number input mode */
		 this.pencilMarkDisplayCell.setVisible(false);
		 this.numberInputCell.setVisible(true);
		 selectCell();
		 this.numberInputField.requestFocus();
	}
}