package edu.psu.sweng500.team8.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.psu.sweng500.team8.coreDataStructures.CellCoordinates;
import edu.psu.sweng500.team8.play.GameSession;

public class BoardGUI extends JPanel {

	private static final int BOARD_SIZE = 486;

	private BlockGUI[][] blocks;
	private GameSession gameSession;
	
	private CellGUI selectedCell;
	
//	private int selectedRowIndex;
//	private int selectedColumnIndex;

	public BoardGUI() {

//		selectedRowIndex = -1;
//		selectedColumnIndex = -1;
		
		setPreferredSize(new Dimension(BOARD_SIZE, BOARD_SIZE));
		setMaximumSize(new Dimension(BOARD_SIZE, BOARD_SIZE));
		setMinimumSize(new Dimension(BOARD_SIZE, BOARD_SIZE));

		blocks = new BlockGUI[3][3];

		GridBagConstraints gridBagConstraints;
		setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(0, 0, 0)));
		setLayout(new java.awt.GridBagLayout());

		blocks[0][0] = new BlockGUI();
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
//		gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 0);
		add(blocks[0][0], gridBagConstraints);

		blocks[0][1] = new BlockGUI();
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
//		gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 0);
		add(blocks[0][1], gridBagConstraints);

		blocks[0][2] = new BlockGUI();
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
//		gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 0);
		add(blocks[0][2], gridBagConstraints);

		blocks[1][0] = new BlockGUI();
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
//		gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
		add(blocks[1][0], gridBagConstraints);

		blocks[1][1] = new BlockGUI();
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		add(blocks[1][1], gridBagConstraints);

		blocks[1][2] = new BlockGUI();
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
//		gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 1);
		add(blocks[1][2], gridBagConstraints);

		blocks[2][0] = new BlockGUI();
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
//		gridBagConstraints.insets = new java.awt.Insets(0, 1, 1, 0);
		add(blocks[2][0], gridBagConstraints);

		blocks[2][1] = new BlockGUI();
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
//		gridBagConstraints.insets = new java.awt.Insets(0, 0, 1, 0);
		add(blocks[2][1], gridBagConstraints);

		blocks[2][2] = new BlockGUI();
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
//		gridBagConstraints.insets = new java.awt.Insets(0, 0, 1, 1);
		add(blocks[2][2], gridBagConstraints);
	}

	public void populatePanel(GameSession gameSession, boolean isRefresh) {

		this.gameSession = gameSession;
		this.selectedCell = null;

		blocks[0][0].populate(gameSession.getGameBoard().getBlock(0),
				gameSession, isRefresh, buildFocusAdapter(), buildMouseAdapter());
		blocks[0][1].populate(gameSession.getGameBoard().getBlock(1),
				gameSession, isRefresh, buildFocusAdapter(), buildMouseAdapter());
		blocks[0][2].populate(gameSession.getGameBoard().getBlock(2),
				gameSession, isRefresh, buildFocusAdapter(), buildMouseAdapter());

		blocks[1][0].populate(gameSession.getGameBoard().getBlock(3),
				gameSession, isRefresh, buildFocusAdapter(), buildMouseAdapter());
		blocks[1][1].populate(gameSession.getGameBoard().getBlock(4),
				gameSession, isRefresh, buildFocusAdapter(), buildMouseAdapter());
		blocks[1][2].populate(gameSession.getGameBoard().getBlock(5),
				gameSession, isRefresh, buildFocusAdapter(), buildMouseAdapter());

		blocks[2][0].populate(gameSession.getGameBoard().getBlock(6),
				gameSession, isRefresh, buildFocusAdapter(), buildMouseAdapter());
		blocks[2][1].populate(gameSession.getGameBoard().getBlock(7),
				gameSession, isRefresh, buildFocusAdapter(), buildMouseAdapter());
		blocks[2][2].populate(gameSession.getGameBoard().getBlock(8),
				gameSession, isRefresh, buildFocusAdapter(), buildMouseAdapter());
	}
	
	public void populatePencilMark(GameSession gameSession) {

		this.gameSession = gameSession;

		blocks[0][0].populatePencilMark(gameSession.getGameBoard().getBlock(0),
				gameSession);
		blocks[0][1].populatePencilMark(gameSession.getGameBoard().getBlock(1),
				gameSession);
		blocks[0][2].populatePencilMark(gameSession.getGameBoard().getBlock(2),
				gameSession);

		blocks[1][0].populatePencilMark(gameSession.getGameBoard().getBlock(3),
				gameSession);
		blocks[1][1].populatePencilMark(gameSession.getGameBoard().getBlock(4),
				gameSession);
		blocks[1][2].populatePencilMark(gameSession.getGameBoard().getBlock(5),
				gameSession);

		blocks[2][0].populatePencilMark(gameSession.getGameBoard().getBlock(6),
				gameSession);
		blocks[2][1].populatePencilMark(gameSession.getGameBoard().getBlock(7),
				gameSession);
		blocks[2][2].populatePencilMark(gameSession.getGameBoard().getBlock(8),
				gameSession);
	}

	public void updateSelectedCellFromHint(CellCoordinates cellCoordinates, int number) {
		
		if (null != selectedCell) {
			/* existing selection, clear it */
			selectedCell.unselect();			
		}
		
		selectedCell = blocks[cellCoordinates.getBlockIndex()/3][cellCoordinates.getBlockIndex()%3].getSelectedCell(cellCoordinates);
		selectedCell.updateSelectedCellFromHint(number);
	}
	
	private FocusAdapter buildFocusAdapter() {

		return new FocusAdapter() {
			public void focusGained(FocusEvent focusEvent) {
				cellGainedFocus(focusEvent);
			}

			public void focusLost(FocusEvent focusEvent) {
				cellLostFocus(focusEvent);
			}
		};
	}
	
	private void cellGainedFocus(FocusEvent focusEvent) {
		CellGUI newSelectedCell = (CellGUI) ((JTextField) focusEvent.getSource())
				.getParent().getParent();
		
		if (null != this.selectedCell) {
			/* existing selection, clear it */
			this.selectedCell.unselect();			
		}

		this.selectedCell = newSelectedCell;
		this.selectedCell.selectCell();
	}

	private void cellLostFocus(FocusEvent focusEvent) {
		CellGUI unselectedCell = (CellGUI) ((JTextField) focusEvent.getSource())
				.getParent().getParent();
		unselectedCell.cellLostFocus(focusEvent);
	}	
	
	private MouseAdapter buildMouseAdapter() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				mouseClickedTask(mouseEvent);
			}
		};
	}
	
	private void mouseClickedTask(MouseEvent mouseEvent) {
		if (null != this.selectedCell) {
			/* existing selection, clear it */
			this.selectedCell.unselect();			
		}
		
		CellGUI currentPencilMarkDisplayCell = (CellGUI)((JPanel)(((JLabel)(mouseEvent.getSource())).getParent())).getParent();
		this.selectedCell = currentPencilMarkDisplayCell;
		
		currentPencilMarkDisplayCell.mouseClicked();
	}
}
