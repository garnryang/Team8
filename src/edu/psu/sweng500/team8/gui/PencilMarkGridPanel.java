/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.psu.sweng500.team8.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter.HighlightPainter;

import edu.psu.sweng500.team8.coreDataStructures.Cell;
import edu.psu.sweng500.team8.coreDataStructures.Cell.ValueType;
import edu.psu.sweng500.team8.play.GameSession;

public class PencilMarkGridPanel extends javax.swing.JPanel {
	// private JPanel board;
	private JPanel[][] pencilMarkCell = new JPanel[9][9];
	private GameSession gameSession;

	public PencilMarkGridPanel() {

	}

	public void populate(GameSession gameSession) {
		this.gameSession = gameSession;
		initComponents();
	}

	private void initComponents() {

		this.removeAll();

		this.setPreferredSize(new java.awt.Dimension(500, 500));
		this.setLayout(new java.awt.GridLayout(9, 9));

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				Cell cell = this.gameSession.getGameBoard().getCellGrid()
						.getCell(i, j);
				if (cell.hasNumber()) {

					JTextField textField = new JTextField();

					textField
							.setHorizontalAlignment(javax.swing.JTextField.CENTER);
					textField.setBorder(javax.swing.BorderFactory
							.createLineBorder(new java.awt.Color(0, 0, 0)));
					textField.setText(Integer.toString(cell.getNumber()));

					HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(
							Color.green);

					try {
						textField.getHighlighter().addHighlight(0, 3, painter);
					} catch (BadLocationException e) {
						e.printStackTrace();
					}

					textField.setEditable(false);

					textField.setPreferredSize(new Dimension(18 * 3, 18 * 3));

					pencilMarkCell[i][j] = new JPanel();

					pencilMarkCell[i][j].setPreferredSize(new Dimension(18 * 3,
							18 * 3));
					pencilMarkCell[i][j].setBorder(null);

					pencilMarkCell[i][j].add(textField);
					this.add(pencilMarkCell[i][j]);

				} else {

					pencilMarkCell[i][j] = new JPanel();

					pencilMarkCell[i][j].setPreferredSize(new Dimension(18 * 3,
							18 * 3));
					pencilMarkCell[i][j].setBorder(null);

					CombinationCell combinationCell = new CombinationCell(cell, this.gameSession);
					pencilMarkCell[i][j].add(combinationCell);
					
					for (int k = 1; k <= 9; k++) {
						
						if (this.gameSession.getGameBoard().getCellConstraints(cell).getUsedNumbers().contains(k)) {
							((JToggleButton)((CombinationCell)pencilMarkCell[i][j].getComponent(0)).getComponent(k-1)).setEnabled(false);	
						}
					}
					
					this.add(pencilMarkCell[i][j]);
				}
			}
		}
	}

	public void refresh() {

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {

				Cell cell = this.gameSession.getGameBoard().getCellGrid()
						.getCell(i, j);

				if (cell.hasNumber()) {
					/* cell has a number */
					if (!ValueType.Given.equals(cell.getType())) {
						/* This cell has a user-entered number */
						pencilMarkCell[i][j].removeAll();

						JTextField textField = new JTextField();

						textField
								.setHorizontalAlignment(javax.swing.JTextField.CENTER);
						textField.setBorder(javax.swing.BorderFactory
								.createLineBorder(new java.awt.Color(0, 0, 0)));
						textField.setText(Integer.toString(cell.getNumber()));

						textField.setEditable(false);

						textField
								.setPreferredSize(new Dimension(18 * 3, 18 * 3));

						pencilMarkCell[i][j].add(textField);
					} else {
						/* Do not touch Given numbers */
					}
				} else {
					/* cell does not have a number */
					if (pencilMarkCell[i][j].getComponent(0) instanceof JTextField) {
						/* if JTextField is already there, it means the cell got a number and it was deleted afterwards. 
						 * We need to put the PencilMark back */
						pencilMarkCell[i][j].removeAll();
						
						CombinationCell combinationCell = new CombinationCell(cell, this.gameSession);
						pencilMarkCell[i][j].add(combinationCell);
					} else {
						/* pencilMark buttons should be pre-populated 
						 * pencilMark buttons should be disabled for given numbers (possibly user-defined numbers) 
						 * TODO - Talk to the team if we want to disable user-defined numbers for pencil Marks 
						 * TODO - We may not have to select/unselect every time we refresh, if we keep track of updated cells */
						
						for (int k = 1; k <= 9; k++) {
							
							if (cell.getPencilMarks().contains(k)) {
								((JToggleButton)((CombinationCell)pencilMarkCell[i][j].getComponent(0)).getComponent(k-1)).setSelected(true);	
							} else {
								((JToggleButton)((CombinationCell)pencilMarkCell[i][j].getComponent(0)).getComponent(k-1)).setSelected(false);
							}
						}
					}
				}
			}
		}
	}
}