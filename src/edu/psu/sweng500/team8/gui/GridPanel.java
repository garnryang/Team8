/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.psu.sweng500.team8.gui;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;

import edu.psu.sweng500.team8.coreDataStructures.Cell;
import edu.psu.sweng500.team8.coreDataStructures.CellCoordinates;
import edu.psu.sweng500.team8.coreDataStructures.CellGrid;
import edu.psu.sweng500.team8.coreDataStructures.Cell.ValueType;
import edu.psu.sweng500.team8.play.GameSession;

public class GridPanel extends javax.swing.JPanel {
	private JTextField[][] controlGrid = new JTextField[9][9];
	private GameSession gameSession;
	private JTextField selectedCell;
	private static final Border SELECTED_BORDER = BorderFactory.createLineBorder(Color.blue, 3);
	private static final Border DEFAULT_BORDER = BorderFactory.createLineBorder(Color.black, 1);
	
	/**
	 * Creates new form GridPanel
	 */
	public GridPanel() {
		initComponents();
		initializeGrid();
		/* David's change A begins */
		enforce();
		/* David's change A ends */
	}

	public void populatePanel(CellGrid grid, GameSession gameSession) {
		clearGrid();

		this.gameSession = gameSession;

		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				Cell cell = grid.getCell(row, column);
				if (cell.hasNumber()) {
					this.controlGrid[row][column].setText(Integer.toString(cell.getNumber()));

					markGivenCell(cell);
				}

				/* Scott's change A begins */
				this.controlGrid[row][column]
						.addKeyListener(new CustomKeyListener(cell, gameSession));
				/* Scott's change A ends */

			}
		}
	}

	public void setSelectedCellNumber(int number) {
		if (this.selectedCell != null)
			this.selectedCell.setText(Integer.toString(number));
	}
	
	public void selectCell(int row, int column) {
		clearSelectedCell();
		this.selectedCell = this.controlGrid[row][column];
		this.selectedCell.setBorder(SELECTED_BORDER);
		}
	
	/* David's change B begins */
	public void enforce() {
		enforceValidNumbers(txtCell00);
		enforceValidNumbers(txtCell01);
		enforceValidNumbers(txtCell02);
		enforceValidNumbers(txtCell03);
		enforceValidNumbers(txtCell04);
		enforceValidNumbers(txtCell05);
		enforceValidNumbers(txtCell06);
		enforceValidNumbers(txtCell07);
		enforceValidNumbers(txtCell08);

		enforceValidNumbers(txtCell10);
		enforceValidNumbers(txtCell11);
		enforceValidNumbers(txtCell12);
		enforceValidNumbers(txtCell13);
		enforceValidNumbers(txtCell14);
		enforceValidNumbers(txtCell15);
		enforceValidNumbers(txtCell16);
		enforceValidNumbers(txtCell17);
		enforceValidNumbers(txtCell18);

		enforceValidNumbers(txtCell20);
		enforceValidNumbers(txtCell21);
		enforceValidNumbers(txtCell22);
		enforceValidNumbers(txtCell23);
		enforceValidNumbers(txtCell24);
		enforceValidNumbers(txtCell25);
		enforceValidNumbers(txtCell26);
		enforceValidNumbers(txtCell27);
		enforceValidNumbers(txtCell28);

		enforceValidNumbers(txtCell30);
		enforceValidNumbers(txtCell31);
		enforceValidNumbers(txtCell32);
		enforceValidNumbers(txtCell33);
		enforceValidNumbers(txtCell34);
		enforceValidNumbers(txtCell35);
		enforceValidNumbers(txtCell36);
		enforceValidNumbers(txtCell37);
		enforceValidNumbers(txtCell38);

		enforceValidNumbers(txtCell40);
		enforceValidNumbers(txtCell41);
		enforceValidNumbers(txtCell42);
		enforceValidNumbers(txtCell43);
		enforceValidNumbers(txtCell44);
		enforceValidNumbers(txtCell45);
		enforceValidNumbers(txtCell46);
		enforceValidNumbers(txtCell47);
		enforceValidNumbers(txtCell48);

		enforceValidNumbers(txtCell50);
		enforceValidNumbers(txtCell51);
		enforceValidNumbers(txtCell52);
		enforceValidNumbers(txtCell53);
		enforceValidNumbers(txtCell54);
		enforceValidNumbers(txtCell55);
		enforceValidNumbers(txtCell56);
		enforceValidNumbers(txtCell57);
		enforceValidNumbers(txtCell58);

		enforceValidNumbers(txtCell60);
		enforceValidNumbers(txtCell61);
		enforceValidNumbers(txtCell62);
		enforceValidNumbers(txtCell63);
		enforceValidNumbers(txtCell64);
		enforceValidNumbers(txtCell65);
		enforceValidNumbers(txtCell66);
		enforceValidNumbers(txtCell67);
		enforceValidNumbers(txtCell68);

		enforceValidNumbers(txtCell70);
		enforceValidNumbers(txtCell71);
		enforceValidNumbers(txtCell72);
		enforceValidNumbers(txtCell73);
		enforceValidNumbers(txtCell74);
		enforceValidNumbers(txtCell75);
		enforceValidNumbers(txtCell76);
		enforceValidNumbers(txtCell77);
		enforceValidNumbers(txtCell78);

		enforceValidNumbers(txtCell80);
		enforceValidNumbers(txtCell81);
		enforceValidNumbers(txtCell82);
		enforceValidNumbers(txtCell83);
		enforceValidNumbers(txtCell84);
		enforceValidNumbers(txtCell85);
		enforceValidNumbers(txtCell86);
		enforceValidNumbers(txtCell87);
		enforceValidNumbers(txtCell88);
	}
	/* David's change B ends */
	
	/* David's change C begins */
	public void enforceValidNumbers(final JTextField jtf) {

		jtf.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				JTextField textField = (JTextField) e.getSource();
				String text = textField.getText();
				try {
					int n = Integer.parseInt(jtf.getText());
					if (n < 1 || n > 9) {
						throw new NumberFormatException(); //FIXME: Prefer not to use an exception unless necessary. Just clear the textbox.
					}
				} catch (NumberFormatException ex) {
					jtf.setText("");
				}

			}
		});
	}
	/* David's change C ends */

	/* Scott's change B begins */
	public void refreshPanel() {
		clearGrid();

		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				Cell cell = this.gameSession.getGameBoard().getCellGrid().getCell(row, column);
				if (cell.hasNumber()) {
					this.controlGrid[row][column].setText(Integer.toString(cell
							.getNumber()));
					
					if (cell.getType() == ValueType.Given)
						markGivenCell(cell);
				}
			}
		}
	}
	/* Scott's change B ends */

	private void initializeGrid() {
		this.controlGrid[0][0] = txtCell00;
		this.controlGrid[0][1] = txtCell01;
		this.controlGrid[0][2] = txtCell02;
		this.controlGrid[0][3] = txtCell03;
		this.controlGrid[0][4] = txtCell04;
		this.controlGrid[0][5] = txtCell05;
		this.controlGrid[0][6] = txtCell06;
		this.controlGrid[0][7] = txtCell07;
		this.controlGrid[0][8] = txtCell08;

		this.controlGrid[1][0] = txtCell10;
		this.controlGrid[1][1] = txtCell11;
		this.controlGrid[1][2] = txtCell12;
		this.controlGrid[1][3] = txtCell13;
		this.controlGrid[1][4] = txtCell14;
		this.controlGrid[1][5] = txtCell15;
		this.controlGrid[1][6] = txtCell16;
		this.controlGrid[1][7] = txtCell17;
		this.controlGrid[1][8] = txtCell18;

		this.controlGrid[2][0] = txtCell20;
		this.controlGrid[2][1] = txtCell21;
		this.controlGrid[2][2] = txtCell22;
		this.controlGrid[2][3] = txtCell23;
		this.controlGrid[2][4] = txtCell24;
		this.controlGrid[2][5] = txtCell25;
		this.controlGrid[2][6] = txtCell26;
		this.controlGrid[2][7] = txtCell27;
		this.controlGrid[2][8] = txtCell28;

		this.controlGrid[3][0] = txtCell30;
		this.controlGrid[3][1] = txtCell31;
		this.controlGrid[3][2] = txtCell32;
		this.controlGrid[3][3] = txtCell33;
		this.controlGrid[3][4] = txtCell34;
		this.controlGrid[3][5] = txtCell35;
		this.controlGrid[3][6] = txtCell36;
		this.controlGrid[3][7] = txtCell37;
		this.controlGrid[3][8] = txtCell38;

		this.controlGrid[4][0] = txtCell40;
		this.controlGrid[4][1] = txtCell41;
		this.controlGrid[4][2] = txtCell42;
		this.controlGrid[4][3] = txtCell43;
		this.controlGrid[4][4] = txtCell44;
		this.controlGrid[4][5] = txtCell45;
		this.controlGrid[4][6] = txtCell46;
		this.controlGrid[4][7] = txtCell47;
		this.controlGrid[4][8] = txtCell48;

		this.controlGrid[5][0] = txtCell50;
		this.controlGrid[5][1] = txtCell51;
		this.controlGrid[5][2] = txtCell52;
		this.controlGrid[5][3] = txtCell53;
		this.controlGrid[5][4] = txtCell54;
		this.controlGrid[5][5] = txtCell55;
		this.controlGrid[5][6] = txtCell56;
		this.controlGrid[5][7] = txtCell57;
		this.controlGrid[5][8] = txtCell58;

		this.controlGrid[6][0] = txtCell60;
		this.controlGrid[6][1] = txtCell61;
		this.controlGrid[6][2] = txtCell62;
		this.controlGrid[6][3] = txtCell63;
		this.controlGrid[6][4] = txtCell64;
		this.controlGrid[6][5] = txtCell65;
		this.controlGrid[6][6] = txtCell66;
		this.controlGrid[6][7] = txtCell67;
		this.controlGrid[6][8] = txtCell68;

		this.controlGrid[7][0] = txtCell70;
		this.controlGrid[7][1] = txtCell71;
		this.controlGrid[7][2] = txtCell72;
		this.controlGrid[7][3] = txtCell73;
		this.controlGrid[7][4] = txtCell74;
		this.controlGrid[7][5] = txtCell75;
		this.controlGrid[7][6] = txtCell76;
		this.controlGrid[7][7] = txtCell77;
		this.controlGrid[7][8] = txtCell78;

		this.controlGrid[8][0] = txtCell80;
		this.controlGrid[8][1] = txtCell81;
		this.controlGrid[8][2] = txtCell82;
		this.controlGrid[8][3] = txtCell83;
		this.controlGrid[8][4] = txtCell84;
		this.controlGrid[8][5] = txtCell85;
		this.controlGrid[8][6] = txtCell86;
		this.controlGrid[8][7] = txtCell87;
		this.controlGrid[8][8] = txtCell88;
	}

	private void markGivenCell(Cell cell) {
		HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(
				Color.green);

		CellCoordinates coordinates = cell.getCoordinates();
		try {
			this.controlGrid[coordinates.getRowIndex()][coordinates.getColumnIndex()].getHighlighter()
			.addHighlight(0, 3, painter); //(JN): What is the significance of 0,3?
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	private void clearSelectedCell() {
		if (this.selectedCell != null) {
			this.selectedCell.setBorder(DEFAULT_BORDER);
		}
		this.selectedCell = null;
	}
	private void clearGrid() {
		clearSelectedCell();
		
		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				this.controlGrid[row][column].setText("");				
				Highlighter hilite = this.controlGrid[row][column]
						.getHighlighter();
				hilite.removeAllHighlights();
			}
		}
	}
	
	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        board = new javax.swing.JPanel();
        txtCell00 = new javax.swing.JTextField();
        txtCell01 = new javax.swing.JTextField();
        txtCell02 = new javax.swing.JTextField();
        txtCell03 = new javax.swing.JTextField();
        txtCell04 = new javax.swing.JTextField();
        txtCell05 = new javax.swing.JTextField();
        txtCell06 = new javax.swing.JTextField();
        txtCell07 = new javax.swing.JTextField();
        txtCell08 = new javax.swing.JTextField();
        txtCell10 = new javax.swing.JTextField();
        txtCell11 = new javax.swing.JTextField();
        txtCell12 = new javax.swing.JTextField();
        txtCell13 = new javax.swing.JTextField();
        txtCell14 = new javax.swing.JTextField();
        txtCell15 = new javax.swing.JTextField();
        txtCell16 = new javax.swing.JTextField();
        txtCell17 = new javax.swing.JTextField();
        txtCell18 = new javax.swing.JTextField();
        txtCell20 = new javax.swing.JTextField();
        txtCell21 = new javax.swing.JTextField();
        txtCell22 = new javax.swing.JTextField();
        txtCell23 = new javax.swing.JTextField();
        txtCell24 = new javax.swing.JTextField();
        txtCell25 = new javax.swing.JTextField();
        txtCell26 = new javax.swing.JTextField();
        txtCell27 = new javax.swing.JTextField();
        txtCell28 = new javax.swing.JTextField();
        txtCell30 = new javax.swing.JTextField();
        txtCell31 = new javax.swing.JTextField();
        txtCell32 = new javax.swing.JTextField();
        txtCell33 = new javax.swing.JTextField();
        txtCell34 = new javax.swing.JTextField();
        txtCell35 = new javax.swing.JTextField();
        txtCell36 = new javax.swing.JTextField();
        txtCell37 = new javax.swing.JTextField();
        txtCell38 = new javax.swing.JTextField();
        txtCell40 = new javax.swing.JTextField();
        txtCell41 = new javax.swing.JTextField();
        txtCell42 = new javax.swing.JTextField();
        txtCell43 = new javax.swing.JTextField();
        txtCell44 = new javax.swing.JTextField();
        txtCell45 = new javax.swing.JTextField();
        txtCell46 = new javax.swing.JTextField();
        txtCell47 = new javax.swing.JTextField();
        txtCell48 = new javax.swing.JTextField();
        txtCell50 = new javax.swing.JTextField();
        txtCell51 = new javax.swing.JTextField();
        txtCell52 = new javax.swing.JTextField();
        txtCell53 = new javax.swing.JTextField();
        txtCell54 = new javax.swing.JTextField();
        txtCell55 = new javax.swing.JTextField();
        txtCell56 = new javax.swing.JTextField();
        txtCell57 = new javax.swing.JTextField();
        txtCell58 = new javax.swing.JTextField();
        txtCell60 = new javax.swing.JTextField();
        txtCell61 = new javax.swing.JTextField();
        txtCell62 = new javax.swing.JTextField();
        txtCell63 = new javax.swing.JTextField();
        txtCell64 = new javax.swing.JTextField();
        txtCell65 = new javax.swing.JTextField();
        txtCell66 = new javax.swing.JTextField();
        txtCell67 = new javax.swing.JTextField();
        txtCell68 = new javax.swing.JTextField();
        txtCell70 = new javax.swing.JTextField();
        txtCell71 = new javax.swing.JTextField();
        txtCell72 = new javax.swing.JTextField();
        txtCell73 = new javax.swing.JTextField();
        txtCell74 = new javax.swing.JTextField();
        txtCell75 = new javax.swing.JTextField();
        txtCell76 = new javax.swing.JTextField();
        txtCell77 = new javax.swing.JTextField();
        txtCell78 = new javax.swing.JTextField();
        txtCell80 = new javax.swing.JTextField();
        txtCell81 = new javax.swing.JTextField();
        txtCell82 = new javax.swing.JTextField();
        txtCell83 = new javax.swing.JTextField();
        txtCell84 = new javax.swing.JTextField();
        txtCell85 = new javax.swing.JTextField();
        txtCell86 = new javax.swing.JTextField();
        txtCell87 = new javax.swing.JTextField();
        txtCell88 = new javax.swing.JTextField();

        board.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        board.setPreferredSize(new java.awt.Dimension(500, 500));
        board.setLayout(new java.awt.GridLayout(9, 9));

        txtCell00.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell00.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell00.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell00);

        txtCell01.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell01.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell01.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell01);

        txtCell02.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell02.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell02.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell02);

        txtCell03.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell03.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell03.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell03);

        txtCell04.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell04.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell04.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell04);

        txtCell05.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell05.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell05.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell05);

        txtCell06.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell06.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell06.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell06);

        txtCell07.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell07.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell07.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell07);

        txtCell08.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell08.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell08.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell08);

        txtCell10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell10.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell10);

        txtCell11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell11.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell11);

        txtCell12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell12.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell12);

        txtCell13.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell13.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell13);

        txtCell14.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell14.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell14);

        txtCell15.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell15.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell15);

        txtCell16.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell16.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell16);

        txtCell17.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell17.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell17);

        txtCell18.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell18.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell18);

        txtCell20.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell20.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell20);

        txtCell21.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell21.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell21);

        txtCell22.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell22.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell22);

        txtCell23.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell23.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell23);

        txtCell24.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell24.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell24);

        txtCell25.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell25.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell25);

        txtCell26.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell26.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell26);

        txtCell27.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell27.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell27);

        txtCell28.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell28.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell28);

        txtCell30.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell30.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell30);

        txtCell31.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell31.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell31);

        txtCell32.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell32.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell32);

        txtCell33.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell33.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell33);

        txtCell34.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell34.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell34.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell34);

        txtCell35.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell35.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell35.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell35);

        txtCell36.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell36.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell36.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell36);

        txtCell37.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell37.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell37.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell37);

        txtCell38.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell38.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell38.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell38);

        txtCell40.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell40.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell40.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell40);

        txtCell41.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell41.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell41.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell41);

        txtCell42.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell42.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell42.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell42);

        txtCell43.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell43.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell43.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell43);

        txtCell44.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell44.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell44.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell44);

        txtCell45.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell45.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell45.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell45);

        txtCell46.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell46.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell46.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell46);

        txtCell47.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell47.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell47.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell47);

        txtCell48.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell48.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell48.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell48);

        txtCell50.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell50.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell50.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell50);

        txtCell51.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell51.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell51.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell51);

        txtCell52.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell52.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell52.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell52);

        txtCell53.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell53.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell53.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell53);

        txtCell54.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell54.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell54.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell54);

        txtCell55.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell55.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell55.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell55);

        txtCell56.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell56.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell56.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell56);

        txtCell57.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell57.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell57.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell57);

        txtCell58.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell58.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell58.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell58);

        txtCell60.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell60.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell60.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell60);

        txtCell61.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell61.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell61.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell61);

        txtCell62.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell62.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell62.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell62);

        txtCell63.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell63.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell63.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell63);

        txtCell64.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell64.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell64.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell64);

        txtCell65.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell65.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell65.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell65);

        txtCell66.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell66.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell66.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell66);

        txtCell67.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell67.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell67.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell67);

        txtCell68.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell68.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell68.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell68);

        txtCell70.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell70.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell70.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell70);

        txtCell71.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell71.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell71.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell71);

        txtCell72.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell72.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell72.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell72);

        txtCell73.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell73.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell73.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell73);

        txtCell74.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell74.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell74.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell74);

        txtCell75.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell75.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell75.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell75);

        txtCell76.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell76.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell76.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell76);

        txtCell77.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell77.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell77.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell77);

        txtCell78.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell78.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell78.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell78);

        txtCell80.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell80.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell80.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell80);

        txtCell81.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell81.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell81.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell81);

        txtCell82.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell82.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell82.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell82);

        txtCell83.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell83.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell83.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell83);

        txtCell84.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell84.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell84.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell84);

        txtCell85.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell85.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell85.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell85);

        txtCell86.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell86.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell86.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell86);

        txtCell87.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell87.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell87.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell87);

        txtCell88.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCell88.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCell88.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellGainedFocus(evt);
            }
        });
        board.add(txtCell88);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(board, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(board, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cellGainedFocus(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cellGainedFocus
        JTextField thisCell = (JTextField) evt.getSource();
        clearSelectedCell();
        this.selectedCell = thisCell;
        this.selectedCell.setBorder(SELECTED_BORDER);
    }//GEN-LAST:event_cellGainedFocus

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel board;
    private javax.swing.JTextField txtCell00;
    private javax.swing.JTextField txtCell01;
    private javax.swing.JTextField txtCell02;
    private javax.swing.JTextField txtCell03;
    private javax.swing.JTextField txtCell04;
    private javax.swing.JTextField txtCell05;
    private javax.swing.JTextField txtCell06;
    private javax.swing.JTextField txtCell07;
    private javax.swing.JTextField txtCell08;
    private javax.swing.JTextField txtCell10;
    private javax.swing.JTextField txtCell11;
    private javax.swing.JTextField txtCell12;
    private javax.swing.JTextField txtCell13;
    private javax.swing.JTextField txtCell14;
    private javax.swing.JTextField txtCell15;
    private javax.swing.JTextField txtCell16;
    private javax.swing.JTextField txtCell17;
    private javax.swing.JTextField txtCell18;
    private javax.swing.JTextField txtCell20;
    private javax.swing.JTextField txtCell21;
    private javax.swing.JTextField txtCell22;
    private javax.swing.JTextField txtCell23;
    private javax.swing.JTextField txtCell24;
    private javax.swing.JTextField txtCell25;
    private javax.swing.JTextField txtCell26;
    private javax.swing.JTextField txtCell27;
    private javax.swing.JTextField txtCell28;
    private javax.swing.JTextField txtCell30;
    private javax.swing.JTextField txtCell31;
    private javax.swing.JTextField txtCell32;
    private javax.swing.JTextField txtCell33;
    private javax.swing.JTextField txtCell34;
    private javax.swing.JTextField txtCell35;
    private javax.swing.JTextField txtCell36;
    private javax.swing.JTextField txtCell37;
    private javax.swing.JTextField txtCell38;
    private javax.swing.JTextField txtCell40;
    private javax.swing.JTextField txtCell41;
    private javax.swing.JTextField txtCell42;
    private javax.swing.JTextField txtCell43;
    private javax.swing.JTextField txtCell44;
    private javax.swing.JTextField txtCell45;
    private javax.swing.JTextField txtCell46;
    private javax.swing.JTextField txtCell47;
    private javax.swing.JTextField txtCell48;
    private javax.swing.JTextField txtCell50;
    private javax.swing.JTextField txtCell51;
    private javax.swing.JTextField txtCell52;
    private javax.swing.JTextField txtCell53;
    private javax.swing.JTextField txtCell54;
    private javax.swing.JTextField txtCell55;
    private javax.swing.JTextField txtCell56;
    private javax.swing.JTextField txtCell57;
    private javax.swing.JTextField txtCell58;
    private javax.swing.JTextField txtCell60;
    private javax.swing.JTextField txtCell61;
    private javax.swing.JTextField txtCell62;
    private javax.swing.JTextField txtCell63;
    private javax.swing.JTextField txtCell64;
    private javax.swing.JTextField txtCell65;
    private javax.swing.JTextField txtCell66;
    private javax.swing.JTextField txtCell67;
    private javax.swing.JTextField txtCell68;
    private javax.swing.JTextField txtCell70;
    private javax.swing.JTextField txtCell71;
    private javax.swing.JTextField txtCell72;
    private javax.swing.JTextField txtCell73;
    private javax.swing.JTextField txtCell74;
    private javax.swing.JTextField txtCell75;
    private javax.swing.JTextField txtCell76;
    private javax.swing.JTextField txtCell77;
    private javax.swing.JTextField txtCell78;
    private javax.swing.JTextField txtCell80;
    private javax.swing.JTextField txtCell81;
    private javax.swing.JTextField txtCell82;
    private javax.swing.JTextField txtCell83;
    private javax.swing.JTextField txtCell84;
    private javax.swing.JTextField txtCell85;
    private javax.swing.JTextField txtCell86;
    private javax.swing.JTextField txtCell87;
    private javax.swing.JTextField txtCell88;
    // End of variables declaration//GEN-END:variables

	/**
	 * for testing only
	 * @return
	 */
	public JTextField[][] getControlGrid() {
		return this.controlGrid;
	}

	/**
	 * for testing only
	 * @return
	 */
	public GameSession getGameSession() {
		return this.gameSession;
	}
}
