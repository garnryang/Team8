package edu.psu.sweng500.team8.gui;

import java.awt.GridBagConstraints;

import javax.swing.JPanel;

import edu.psu.sweng500.team8.coreDataStructures.Block;
import edu.psu.sweng500.team8.coreDataStructures.Cell;
import edu.psu.sweng500.team8.coreDataStructures.CellCoordinates;
import edu.psu.sweng500.team8.play.GameSession;

import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.MouseAdapter;

public class BlockGUI extends JPanel {
	

	private static final int BLOCK_SIZE = 162; 
	
	private CellGUI[][] cells;
	
	public BlockGUI() {
		
		setPreferredSize(new java.awt.Dimension(BLOCK_SIZE, BLOCK_SIZE));
		setMaximumSize(new java.awt.Dimension(BLOCK_SIZE, BLOCK_SIZE));
		setMinimumSize(new java.awt.Dimension(BLOCK_SIZE, BLOCK_SIZE));
		
		cells = new CellGUI[3][3];

		GridBagConstraints gridBagConstraints;
		setBorder(new LineBorder(new Color(0, 0, 0), 0));
		setLayout(new java.awt.GridBagLayout());
		
		cells[0][0] = new CellGUI();
		
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		add(cells[0][0], gridBagConstraints);

		cells[0][1] = new CellGUI();
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		add(cells[0][1], gridBagConstraints);

		cells[0][2] = new CellGUI();
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		add(cells[0][2], gridBagConstraints);

		cells[1][0] = new CellGUI();
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
//		gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
		add(cells[1][0], gridBagConstraints);

		cells[1][1] = new CellGUI();
//		cells[1][1].setMaximumSize(new java.awt.Dimension(CELL_SIZE, CELL_SIZE));
//		cells[1][1].setMinimumSize(new java.awt.Dimension(CELL_SIZE, CELL_SIZE));
//		cells[1][1].setPreferredSize(new java.awt.Dimension(CELL_SIZE, CELL_SIZE));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		add(cells[1][1], gridBagConstraints);

		cells[1][2] = new CellGUI();
//		cells[1][2].setMaximumSize(new java.awt.Dimension(CELL_SIZE, CELL_SIZE));
//		cells[1][2].setMinimumSize(new java.awt.Dimension(CELL_SIZE, CELL_SIZE));
//		cells[1][2].setPreferredSize(new java.awt.Dimension(CELL_SIZE, CELL_SIZE));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
//		gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 1);
		add(cells[1][2], gridBagConstraints);

		cells[2][0] = new CellGUI();
//		cells[2][0].setMaximumSize(new java.awt.Dimension(CELL_SIZE, CELL_SIZE));
//		cells[2][0].setMinimumSize(new java.awt.Dimension(CELL_SIZE, CELL_SIZE));
//		cells[2][0].setPreferredSize(new java.awt.Dimension(CELL_SIZE, CELL_SIZE));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
//		gridBagConstraints.insets = new java.awt.Insets(0, 1, 1, 0);
		add(cells[2][0], gridBagConstraints);

		cells[2][1] = new CellGUI();
//		cells[2][1].setMaximumSize(new java.awt.Dimension(CELL_SIZE, CELL_SIZE));
//		cells[2][1].setMinimumSize(new java.awt.Dimension(CELL_SIZE, CELL_SIZE));
//		cells[2][1].setPreferredSize(new java.awt.Dimension(CELL_SIZE, CELL_SIZE));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
//		gridBagConstraints.insets = new java.awt.Insets(0, 0, 1, 0);
		add(cells[2][1], gridBagConstraints);

		cells[2][2] = new CellGUI();
//		cells[2][2].setMaximumSize(new java.awt.Dimension(CELL_SIZE, CELL_SIZE));
//		cells[2][2].setMinimumSize(new java.awt.Dimension(CELL_SIZE, CELL_SIZE));
//		cells[2][2].setPreferredSize(new java.awt.Dimension(CELL_SIZE, CELL_SIZE));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
//		gridBagConstraints.insets = new java.awt.Insets(0, 0, 1, 1);
		add(cells[2][2], gridBagConstraints);
	}	
	
	public void populate(Block block, GameSession gameSession, boolean isRefresh, FocusAdapter focusAdapter, MouseAdapter mouseAdapter) {
		
		cells[0][0].populate(block.getCell(0), gameSession, isRefresh, focusAdapter, mouseAdapter);
		cells[0][1].populate(block.getCell(1), gameSession, isRefresh, focusAdapter, mouseAdapter);
		cells[0][2].populate(block.getCell(2), gameSession, isRefresh, focusAdapter, mouseAdapter);
		
		cells[1][0].populate(block.getCell(3), gameSession, isRefresh, focusAdapter, mouseAdapter);
		cells[1][1].populate(block.getCell(4), gameSession, isRefresh, focusAdapter, mouseAdapter);
		cells[1][2].populate(block.getCell(5), gameSession, isRefresh, focusAdapter, mouseAdapter);
		
		cells[2][0].populate(block.getCell(6), gameSession, isRefresh, focusAdapter, mouseAdapter);
		cells[2][1].populate(block.getCell(7), gameSession, isRefresh, focusAdapter, mouseAdapter);
		cells[2][2].populate(block.getCell(8), gameSession, isRefresh, focusAdapter, mouseAdapter);
	}
	
	public void populatePencilMark(Block block, GameSession gameSession) {
		
		cells[0][0].populatePencilMark(block.getCell(0), gameSession);
		cells[0][1].populatePencilMark(block.getCell(1), gameSession);
		cells[0][2].populatePencilMark(block.getCell(2), gameSession);
		
		cells[1][0].populatePencilMark(block.getCell(3), gameSession);
		cells[1][1].populatePencilMark(block.getCell(4), gameSession);
		cells[1][2].populatePencilMark(block.getCell(5), gameSession);
		
		cells[2][0].populatePencilMark(block.getCell(6), gameSession);
		cells[2][1].populatePencilMark(block.getCell(7), gameSession);
		cells[2][2].populatePencilMark(block.getCell(8), gameSession);
	}
	
	public CellGUI getSelectedCell(CellCoordinates cellCoordinates) {
		return cells[cellCoordinates.getRowIndex()%3][cellCoordinates.getColumnIndex()%3];
	}
	
	public void disableEditing() {
		
		for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
        		cells[row][column].disableEditing();
            } 
    	}
	}
}
