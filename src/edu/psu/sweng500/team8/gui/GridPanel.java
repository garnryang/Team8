/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.psu.sweng500.team8.gui;

import edu.psu.sweng500.team8.coreDataStructures.Cell;
import edu.psu.sweng500.team8.coreDataStructures.CellCoordinates;
import edu.psu.sweng500.team8.coreDataStructures.CellGrid;
import edu.psu.sweng500.team8.play.GameSession;
import javax.swing.JTextField;

public class GridPanel extends javax.swing.JPanel {
	private JTextField[][] controlGrid = new JTextField[9][9];
	private GameSession gameSession;

    /**
     * Creates new form GridPanel
     */
    public GridPanel() {
        initComponents();
        initializeGrid();
    }

	public void populatePanel(CellGrid grid, GameSession gameSession) {
		clearGrid();

		this.gameSession = gameSession;

		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				Cell cell = grid.getCell(row, column);
				if (cell.hasNumber()) {
					this.controlGrid[row][column].setText(Integer.toString(cell.getNumber()));
				}
				
				/*
				 * FIXME - adding prototype keyListener to continue working
				 * on redo/undo
				 */
//				this.controlGrid[row][column].setFocusable(true);
				this.controlGrid[row][column]
						.addKeyListener(new CustomKeyListener(cell,
								gameSession));

			}
		}
	}

	private void clearGrid() {
		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				this.controlGrid[row][column].setText("");
			}
		}
	}

	public void refreshPanel() {
		clearGrid();

		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				Cell cell = this.gameSession.getGameBoard().getCellGrid().getCell(row, column);
				if (cell.hasNumber()) {
					this.controlGrid[row][column].setText(Integer.toString(cell
							.getNumber()));
				}
			}
		}
	}

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

		board.add(txtCell00);

		board.add(txtCell01);

		board.add(txtCell02);

		board.add(txtCell03);

		board.add(txtCell04);

		board.add(txtCell05);

		board.add(txtCell06);

		board.add(txtCell07);

		board.add(txtCell08);

		board.add(txtCell10);

		board.add(txtCell11);

		board.add(txtCell12);
		board.add(txtCell13);

		board.add(txtCell14);

		board.add(txtCell15);

		board.add(txtCell16);

		board.add(txtCell17);

		board.add(txtCell18);

		board.add(txtCell20);

		board.add(txtCell21);

		board.add(txtCell22);

		board.add(txtCell23);

		board.add(txtCell24);

		board.add(txtCell25);

		board.add(txtCell26);

		board.add(txtCell27);

		board.add(txtCell28);

		board.add(txtCell30);

		board.add(txtCell31);

		board.add(txtCell32);

		board.add(txtCell33);

		board.add(txtCell34);

		board.add(txtCell35);

		board.add(txtCell36);

		board.add(txtCell37);

		board.add(txtCell38);

		board.add(txtCell40);

		board.add(txtCell41);

		board.add(txtCell42);

		board.add(txtCell43);

		board.add(txtCell44);

		board.add(txtCell45);

		board.add(txtCell46);

		board.add(txtCell47);

		board.add(txtCell48);

		board.add(txtCell50);

		board.add(txtCell51);

		board.add(txtCell52);

		board.add(txtCell53);

		board.add(txtCell54);

		board.add(txtCell55);

		board.add(txtCell56);

		board.add(txtCell57);

		board.add(txtCell58);

		board.add(txtCell60);

		board.add(txtCell61);

		board.add(txtCell62);

		board.add(txtCell63);

		board.add(txtCell64);

		board.add(txtCell65);

		board.add(txtCell66);

		board.add(txtCell67);

		board.add(txtCell68);

		board.add(txtCell70);

		board.add(txtCell71);

		board.add(txtCell72);

		board.add(txtCell73);

		board.add(txtCell74);

		board.add(txtCell75);

		board.add(txtCell76);

		board.add(txtCell77);

		board.add(txtCell78);

		board.add(txtCell80);

		board.add(txtCell81);

		board.add(txtCell82);

		board.add(txtCell83);

		board.add(txtCell84);

		board.add(txtCell85);

		board.add(txtCell86);

		board.add(txtCell87);

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
