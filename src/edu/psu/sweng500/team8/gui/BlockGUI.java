package edu.psu.sweng500.team8.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.MouseAdapter;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import edu.psu.sweng500.team8.coreDataStructures.Block;
import edu.psu.sweng500.team8.coreDataStructures.CellCoordinates;
import edu.psu.sweng500.team8.play.GameSession;

public class BlockGUI extends JPanel {
	private static final long serialVersionUID = 1L; //Not really necessary since we're not serializing the UI, but just to keep Java happy...
	private static final int BLOCK_SIZE = 162 + 2;
	private static final Border DEFAULT_BORDER = BorderFactory
			.createLineBorder(Color.BLACK, 2);

	private CellGUI[][] cells;

	public BlockGUI() {

		this.cells = new CellGUI[3][3];
		this.setPreferredSize(new java.awt.Dimension(BLOCK_SIZE, BLOCK_SIZE));
		this.setMaximumSize(new java.awt.Dimension(BLOCK_SIZE, BLOCK_SIZE));
		this.setMinimumSize(new java.awt.Dimension(BLOCK_SIZE, BLOCK_SIZE));
		this.setLayout(new GridBagLayout());
		this.setBorder(DEFAULT_BORDER);

		GridBagConstraints gridBagConstraints;

		for (int rowIndex = 0; rowIndex < 3; rowIndex++) {
			for (int columnIndex = 0; columnIndex < 3; columnIndex++) {
				this.cells[rowIndex][columnIndex] = new CellGUI();
				gridBagConstraints = new GridBagConstraints();
				gridBagConstraints.gridx = columnIndex;
				gridBagConstraints.gridy = rowIndex;
				gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
				this.add(this.cells[rowIndex][columnIndex], gridBagConstraints);
			}
		}
	}

	/**
	 * 
	 * iterate through each contained CellGUI and populate Given/User-defined
	 * numbers
	 * 
	 * @param block
	 * @param gameSession
	 * @param isRefresh
	 * @param focusAdapter
	 * @param mouseAdapter
	 * 
	 */
	public void populate(Block block, GameSession gameSession,
			boolean isRefresh, FocusAdapter focusAdapter,
			MouseAdapter mouseAdapter, boolean isPencilMarkMode) {

		for (int cellIndex = 0; cellIndex < 9; cellIndex++) {
			int rowIndex = cellIndex / 3;
			int columnIndex = cellIndex % 3;
			this.cells[rowIndex][columnIndex].populate(
					block.getCell(cellIndex), gameSession, isRefresh,
					focusAdapter, mouseAdapter, isPencilMarkMode);
		}
	}

	/**
	 * 
	 * @param cellCoordinates
	 * @return CellGUI according to given cellCoordinates
	 * 
	 */
	public CellGUI getSelectedCell(CellCoordinates cellCoordinates) {

		int rowIndex = cellCoordinates.getRowIndex() % 3;
		int columnIndex = cellCoordinates.getColumnIndex() % 3;
		return this.cells[rowIndex][columnIndex];
	}

	/**
	 * disable editing for contained CellGUI
	 */
	public void disableEditing() {

		for (int rowIndex = 0; rowIndex < 3; rowIndex++) {
			for (int columnIndex = 0; columnIndex < 3; columnIndex++) {
				this.cells[rowIndex][columnIndex].disableEditing();
			}
		}
	}
}
