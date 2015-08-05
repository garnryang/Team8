package edu.psu.sweng500.team8.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import edu.psu.sweng500.team8.coreDataStructures.Cell;
import edu.psu.sweng500.team8.coreDataStructures.CellCoordinates;
import edu.psu.sweng500.team8.play.GameSession;


public class BoardGUI extends JPanel {
	private static final long serialVersionUID = 1L; //Not really necessary since we're not serializing the UI, but just to keep Java happy...
	private static final int BOARD_SIZE = 486 + 10;
	private static final Border DEFAULT_BORDER = BorderFactory
			.createLineBorder(Color.BLACK, 2);
	private BlockGUI[][] blocks;
	private GameSession gameSession;
	private CellGUI selectedCell;
	private Set<CellGUI> highlightedIncorrectCells = new HashSet<CellGUI>();
	private NumberButtonGUI numberInputPad;

	public BoardGUI() {

		this.setPreferredSize(new Dimension(BOARD_SIZE, BOARD_SIZE));
		this.setMaximumSize(new Dimension(BOARD_SIZE, BOARD_SIZE));
		this.setMinimumSize(new Dimension(BOARD_SIZE, BOARD_SIZE));
		this.setBorder(DEFAULT_BORDER);
				
		this.blocks = new BlockGUI[3][3];

		MouseAdapter mouseAdapter = new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent mouseEvent) {
				mouseClickedTask(mouseEvent);
			}
		};
		
		GridBagConstraints gridBagConstraints;
		this.setLayout(new java.awt.GridBagLayout());

		for (int rowIndex = 0; rowIndex < 3; rowIndex++) {
			for (int columnIndex = 0; columnIndex < 3; columnIndex++) {
				this.blocks[rowIndex][columnIndex] = new BlockGUI();
				gridBagConstraints = new GridBagConstraints();
				gridBagConstraints.gridx = columnIndex;
				gridBagConstraints.gridy = rowIndex;
				gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
				this.blocks[rowIndex][columnIndex].addMouseListener(mouseAdapter);
				add(this.blocks[rowIndex][columnIndex], gridBagConstraints);
			}
		}
	}

	public void populatePanel(GameSession gameSession, boolean isRefresh,
			boolean isPencilMarkMode, NumberButtonGUI numberInputPad) {

		this.gameSession = gameSession;
		this.numberInputPad = numberInputPad;

		for (int blockIndex = 0; blockIndex < 9; blockIndex++) {
			int rowIndex = blockIndex / 3;
			int columnIndex = blockIndex % 3;

			this.blocks[rowIndex][columnIndex].populate(gameSession
					.getGameBoard().getBlock(blockIndex), gameSession,
					isRefresh, isPencilMarkMode);
		}
	}

	public void updateSelectedCellFromHint(CellCoordinates cellCoordinates,
			int number) {

		if (null != this.selectedCell) {
			/* existing selection, clear it */
			this.selectedCell.unselect();
		}

		this.selectedCell = this.blocks[cellCoordinates.getBlockIndex() / 3][cellCoordinates
				.getBlockIndex() % 3].getCell(cellCoordinates);
		this.selectedCell.updateSelectedCellFromHint(number);
	}
	
	public Cell getSelectedCell() {
		return this.selectedCell.getCell();
	}

	public void mouseClickedTaskForNumberInput(MouseEvent mouseEvent) {

		/* a cell must be selected/focused before mouse number input can work */
		if (null == this.selectedCell) {
			/* nothing happens */
			return;
		}

		this.selectedCell.setNumberToCell(mouseEvent);

	}

	private void mouseClickedTask(MouseEvent mouseEvent) {
		if (null != this.selectedCell) {
			/* existing selection, clear it */
			this.selectedCell.unselect();
		}
		
		if (this.gameSession == null) //Game hasn't start
			return;

		CellGUI selectedCellGUI = null;
		if (mouseEvent.getSource() instanceof JLabel)
			selectedCellGUI = (CellGUI) ((JPanel) (((JLabel) (mouseEvent.getSource())).getParent())).getParent();
		else if (mouseEvent.getSource() instanceof JTextField)
			selectedCellGUI = (CellGUI) ((JTextField) mouseEvent.getSource()).getParent().getParent();
		else
			return;
		
		this.selectedCell = selectedCellGUI;

		this.selectedCell.selectCell();

		this.numberInputPad.updateForFocusedCell(this.selectedCell.getCell());
	}

	/**/
	public void highlightIncorrectCells(Set<Cell> incorrectCells) {
		clearHighlightedIncorrectCells();
		for (Cell incorrectCell : incorrectCells) {
			this.markIncorrectCell(incorrectCell);
		}
	}

	public void clearHighlightedIncorrectCells() {
		for (CellGUI eachCell : this.highlightedIncorrectCells) {
			eachCell.clearIncorrectCellMark();
		}

		this.highlightedIncorrectCells.clear();
	}

	public void disableEditing() {

		if (null != selectedCell) {
			selectedCell.unselect();
			selectedCell = null;
		}

		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 3; column++) {
				this.blocks[row][column].disableEditing();
			}
		}
	}

	private void markIncorrectCell(Cell cell) {

		CellGUI currentCell = findCorresdpondingCellGUI(cell);
		currentCell.markIncorrectCell();
		this.highlightedIncorrectCells.add(currentCell);
	}

	/**
	 * made public for unit testing 
	 * @param cell
	 * @return
	 */
	public CellGUI findCorresdpondingCellGUI(Cell cell) {
		CellCoordinates coordinates = cell.getCoordinates();
		return this.blocks[coordinates.getBlockIndex() / 3][coordinates
				.getBlockIndex() % 3].getCell(coordinates);

	}

	public void refreshPencilMarkDisplayOnRelatedCells(Cell cell) {

		Map<CellCoordinates, Boolean> updatedCells = new HashMap<CellCoordinates, Boolean>();
		// updatedCells.put(cell.getCoordinates(), Boolean.TRUE);

		List<Cell> blockCells = this.gameSession.getGameBoard()
				.getCellConstraints(cell).getBlock().getCells();
		for (Cell eachCellInBlock : blockCells) {

			CellCoordinates currentCoordinate = eachCellInBlock
					.getCoordinates();
			Boolean isUpdatedAlready = updatedCells.get(currentCoordinate);

			if (!Boolean.TRUE.equals(isUpdatedAlready)) {
				CellGUI cellGUI = findCorresdpondingCellGUI(eachCellInBlock);
				cellGUI.refreshPencilMarkDisplay();
				updatedCells.put(currentCoordinate, Boolean.TRUE);
			}
		}

		List<Cell> rowCells = this.gameSession.getGameBoard()
				.getCellConstraints(cell).getRow().getCells();
		for (Cell eachCellInRow : rowCells) {

			CellCoordinates currentCoordinate = eachCellInRow.getCoordinates();
			Boolean isUpdatedAlready = updatedCells.get(currentCoordinate);

			if (!Boolean.TRUE.equals(isUpdatedAlready)) {
				CellGUI cellGUI = findCorresdpondingCellGUI(eachCellInRow);
				cellGUI.refreshPencilMarkDisplay();
				updatedCells.put(currentCoordinate, Boolean.TRUE);
			}
		}

		List<Cell> columnCells = this.gameSession.getGameBoard()
				.getCellConstraints(cell).getColumn().getCells();
		for (Cell eachCellInColumn : columnCells) {

			CellCoordinates currentCoordinate = eachCellInColumn
					.getCoordinates();
			Boolean isUpdatedAlready = updatedCells.get(currentCoordinate);

			if (!Boolean.TRUE.equals(isUpdatedAlready)) {
				CellGUI cellGUI = findCorresdpondingCellGUI(eachCellInColumn);
				cellGUI.refreshPencilMarkDisplay();
				updatedCells.put(currentCoordinate, Boolean.TRUE);
			}
		}
	}
	

}
