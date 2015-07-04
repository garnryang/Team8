package edu.psu.sweng500.team8.gui;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.Group;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JFileChooser;
import javax.swing.LayoutStyle.ComponentPlacement;

import edu.psu.sweng500.team8.coreDataStructures.CellCoordinates;
import edu.psu.sweng500.team8.coreDataStructures.Puzzle;
import edu.psu.sweng500.team8.coreDataStructures.Puzzle.DifficultyLevel;
import edu.psu.sweng500.team8.play.GameSession;
import edu.psu.sweng500.team8.puzzleGenerator.PuzzleRepository;
import edu.psu.sweng500.team8.solver.HintGenerator;
import edu.psu.sweng500.team8.solver.HintInfo;

import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cliff_000
 */
public class SudokuGUI extends javax.swing.JFrame {

	private PuzzleRepository puzzleRepo = new PuzzleRepository(); //Not sure if there is a better place to put this
        /* we need to keep track of the current game */
	private GameSession gameSession;
        
	/**
	 * Creates new form SudokuGUI
	 */
	public SudokuGUI() {
		try {
			this.puzzleRepo.initialize();
		} catch (IOException e) {
			e.printStackTrace();
		}
		initComponents();
	}

	public void setMessage(String message) {
		lblMessage.setText(message);
	}
	
	public void clearMessage() {
		lblMessage.setText("");
	}
	
	private void arrangeLayout() {
		GroupLayout layout = new javax.swing.GroupLayout(getContentPane());

		Group difficultyRadioButtonGroup = layout
				.createParallelGroup(Alignment.TRAILING);
		difficultyRadioButtonGroup.addComponent(jLabel2); // difficulty
		difficultyRadioButtonGroup.addComponent(radEasy);
		difficultyRadioButtonGroup.addComponent(radMedium);
		difficultyRadioButtonGroup.addComponent(radHard);

		Group difficultyRadioButtonContainer = layout.createSequentialGroup();
		difficultyRadioButtonContainer.addGap(4);
		difficultyRadioButtonContainer.addGroup(difficultyRadioButtonGroup);

		Group actionButtonGroup = layout.createParallelGroup(Alignment.LEADING);
		actionButtonGroup.addComponent(jLabel3); // option
		actionButtonGroup.addComponent(btnUndo);
		actionButtonGroup.addComponent(btnRedo);
		actionButtonGroup.addComponent(jButton11);
		actionButtonGroup.addComponent(jButton10);
		actionButtonGroup.addComponent(jButton14);

		Group actionButtonContainer = layout.createSequentialGroup();
		actionButtonContainer.addGap(4);
		actionButtonContainer.addGroup(actionButtonGroup);

		ParallelGroup menuGroup = layout.createParallelGroup(Alignment.LEADING);
		menuGroup.addComponent(jLabel1, Alignment.TRAILING); // menu

		javax.swing.GroupLayout.Group menuContainer = layout
				.createParallelGroup(Alignment.LEADING);
		menuContainer.addGroup(menuGroup);
		menuContainer.addGroup(difficultyRadioButtonContainer);
		menuContainer.addGroup(actionButtonContainer);
		menuContainer.addGap(18);

		/**/
		SequentialGroup menuContainer_seq = layout.createSequentialGroup();
		menuContainer_seq.addGroup(menuContainer);

		SequentialGroup newGameMenuGroup = layout.createSequentialGroup();
		newGameMenuGroup.addComponent(btnNewGame); // new game
		newGameMenuGroup.addContainerGap();

		javax.swing.GroupLayout.Group menuNnewGame = layout
				.createParallelGroup(Alignment.TRAILING);
		menuNnewGame.addGroup(newGameMenuGroup);
		menuNnewGame.addGroup(menuContainer_seq);

		/**/

		SequentialGroup mainGroup = layout.createSequentialGroup();
		mainGroup.addContainerGap();
		mainGroup.addComponent(gameBoard, GroupLayout.PREFERRED_SIZE,
				GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
		mainGroup.addPreferredGap(ComponentPlacement.RELATED,
				GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
		mainGroup.addGroup(menuNnewGame);

		/**/
		Group parallelGroup = layout.createParallelGroup(Alignment.LEADING);
		parallelGroup.addGroup(mainGroup);
		layout.setHorizontalGroup(parallelGroup);

		/**/
		Group virticalGroup = layout.createParallelGroup(Alignment.TRAILING);

		SequentialGroup sg = layout.createSequentialGroup();
		sg.addComponent(jLabel1);
		sg.addGap(13);
		sg.addComponent(jLabel2);
		sg.addPreferredGap(ComponentPlacement.RELATED);
		sg.addComponent(radEasy);
		sg.addPreferredGap(ComponentPlacement.RELATED);
		sg.addComponent(radMedium);
		sg.addPreferredGap(ComponentPlacement.RELATED);
		sg.addComponent(radHard);
		sg.addPreferredGap(ComponentPlacement.RELATED, 229, Short.MAX_VALUE)
				.addComponent(jLabel3);
		sg.addPreferredGap(ComponentPlacement.RELATED);
		sg.addComponent(jButton10);
		sg.addPreferredGap(ComponentPlacement.RELATED);
		sg.addComponent(jButton11);
		sg.addPreferredGap(ComponentPlacement.RELATED);
		sg.addComponent(btnUndo);
		sg.addPreferredGap(ComponentPlacement.RELATED);
		sg.addComponent(btnRedo);
		sg.addPreferredGap(ComponentPlacement.RELATED);
		sg.addComponent(jButton14).addGap(18).addComponent(btnNewGame);
		sg.addContainerGap();

		SequentialGroup sg2 = layout.createSequentialGroup();
		sg2.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
		sg2.addComponent(gameBoard, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
		sg2.addGap(41);

		virticalGroup.addGroup(sg);
		virticalGroup.addGroup(sg2);
		layout.setVerticalGroup(virticalGroup);

		getContentPane().setLayout(layout);
	}
	
	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        radEasy = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        radMedium = new javax.swing.JRadioButton();
        radHard = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        btnUndo = new javax.swing.JButton();
        btnRedo = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        btnNewGame = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        gameBoard = new edu.psu.sweng500.team8.gui.GridPanel();
        btnHint = new javax.swing.JButton();
        lblMessage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        buttonGroup1.add(radEasy);
        radEasy.setSelected(true);
        radEasy.setText("Easy");
        radEasy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radEasyActionPerformed(evt);
            }
        });

        jLabel1.setText("Menu");

        buttonGroup1.add(radMedium);
        radMedium.setText("Medium");

        buttonGroup1.add(radHard);
        radHard.setText("Hard");
        radHard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radHardActionPerformed(evt);
            }
        });

        jLabel2.setText("Difficulty");

        jButton10.setText("Save");

        jButton11.setText("Load");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        btnUndo.setText("Undo");
        btnUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doUndo(evt);
            }
        });

        btnRedo.setText("Redo");
        btnRedo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doRedo(evt);
            }
        });

        jButton14.setText("Help");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        btnNewGame.setText("New Game");
        btnNewGame.setName("btnNewGame"); // NOI18N
        btnNewGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewGameActionPerformed(evt);
            }
        });

        jLabel3.setText("Options");

        btnHint.setText("Hint");
        btnHint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE))
                    .addComponent(gameBoard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnNewGame)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(radMedium, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(radHard)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2)
                                .addComponent(radEasy))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(4, 4, 4)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnUndo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnRedo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3)
                                        .addComponent(btnHint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGap(18, 18, 18))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radEasy)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radMedium)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radHard)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnHint)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUndo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRedo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton14)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(gameBoard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnNewGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHintActionPerformed
        //Get a hint
    	if (this.gameSession == null)
    		return;
    	
        HintInfo hint = HintGenerator.getHint(this.gameSession.getGameBoard());
        if (hint != null) {
        	CellCoordinates coordinates = hint.GetCell().getCoordinates();
            this.gameBoard.selectCell(coordinates.getRowIndex(), coordinates.getColumnIndex());
            this.gameBoard.setSelectedCellNumber(hint.GetCell().getNumber());
            setMessage(hint.GetExplanation());
        }
    }//GEN-LAST:event_btnHintActionPerformed

    private void doUndo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doUndo
    	this.gameSession.doUndo();
		this.gameBoard.refreshPanel();
    }//GEN-LAST:event_doUndo

    private void doRedo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doRedo
    	this.gameSession.doRedo();
		this.gameBoard.refreshPanel();
    }//GEN-LAST:event_doRedo

	private void radEasyActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_radEasyActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_radEasyActionPerformed

	private void radHardActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_radHardActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_radHardActionPerformed

	private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton11ActionPerformed
		// TODO add your handling code here:
		// Create a file chooser
		final JFileChooser fc = new JFileChooser();
		// In response to a button click:
		int returnVal = fc.showOpenDialog(getComponent(0));

	}// GEN-LAST:event_jButton11ActionPerformed

	private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton14ActionPerformed
		// TODO add your handling code here:
		openHelp();
	}// GEN-LAST:event_jButton14ActionPerformed

	private void openHelp() {
		// TODO Auto-generated method stub

	}

	private void btnNewGameActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnNewGameActionPerformed
		DifficultyLevel difficulty;
		if (radEasy.isSelected())
			difficulty = DifficultyLevel.Easy;
		else if (radMedium.isSelected())
			difficulty = DifficultyLevel.Medium;
		else
			difficulty = DifficultyLevel.Hard;

		Puzzle puzzle = this.puzzleRepo.getPuzzle(difficulty);
		this.gameSession = new GameSession(puzzle);
		this.gameBoard.populatePanel(gameSession.getGameBoard().getCellGrid(),
				gameSession);
	}// GEN-LAST:event_btnNewGameActionPerformed

	/**
     * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(SudokuGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SudokuGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SudokuGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SudokuGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new SudokuGUI().setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHint;
    private javax.swing.JButton btnNewGame;
    private javax.swing.JButton btnRedo;
    private javax.swing.JButton btnUndo;
    private javax.swing.ButtonGroup buttonGroup1;
    private edu.psu.sweng500.team8.gui.GridPanel gameBoard;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton14;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JRadioButton radEasy;
    private javax.swing.JRadioButton radHard;
    private javax.swing.JRadioButton radMedium;
    // End of variables declaration//GEN-END:variables
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
