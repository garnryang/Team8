package edu.psu.sweng500.team8.gui;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JToggleButton;

import edu.psu.sweng500.team8.coreDataStructures.Board;
import edu.psu.sweng500.team8.coreDataStructures.Cell;
import edu.psu.sweng500.team8.coreDataStructures.CellCoordinates;
import edu.psu.sweng500.team8.coreDataStructures.Puzzle;
import edu.psu.sweng500.team8.coreDataStructures.Puzzle.DifficultyLevel;
import edu.psu.sweng500.team8.play.CellChangedListener;
import edu.psu.sweng500.team8.play.GameSession;
import edu.psu.sweng500.team8.puzzleGenerator.PuzzleRepository;
import edu.psu.sweng500.team8.solver.HintGenerator;
import edu.psu.sweng500.team8.solver.HintInfo;

import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cliff_000
 */
public class SudokuGUI extends javax.swing.JFrame implements CellChangedListener {

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
		this.txtAreaMessage.setText(message);
	}
	
	public void clearMessage() {
		this.txtAreaMessage.setText("");
	}
	
	@Override
	public void cellChanged(Cell cell, int newNumber) {
		//Cell number changed. Clear the message and any highlighted incorrect numbers.
		clearMessage();
		this.gameBoard.clearHighlightedIncorrectCells();
		
		Board board = this.gameSession.getGameBoard();
		if (!board.hasOpenCells()) {
			//Check the board against the solution
			if (board.getIncorrectCells().isEmpty()) {
				//Player won the game
				this.gameBoard.disableEditing();
				setMessage("You won! Start a new game to play again.");
			}
		}
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
        pencilMarkGridPanel = new PencilMarkGridPanel();
        btnHint = new javax.swing.JButton();
        pencilMarkButton = new JToggleButton();
        btnCheck = new javax.swing.JButton();

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
        
        pencilMarkButton.setText("Pencil Mark");
        pencilMarkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	pencilMarkMode(evt);
            }
        });

        gameBoard.setVisible(true);
        pencilMarkGridPanel.setVisible(false);

		btnCheck.setText("Check");
        btnCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckActionPerformed(evt);
            }
        });
        
        txtAreaMessage = new JTextArea();
        txtAreaMessage.setWrapStyleWord(true);
        txtAreaMessage.setLineWrap(true);
        txtAreaMessage.setEditable(false);
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(gameBoard, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(pencilMarkGridPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(10)
        					.addComponent(txtAreaMessage, GroupLayout.PREFERRED_SIZE, 480, GroupLayout.PREFERRED_SIZE)))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createParallelGroup(Alignment.LEADING)
        					.addComponent(jLabel1, Alignment.TRAILING)
        					.addComponent(radMedium, Alignment.TRAILING))
        				.addComponent(radHard)
        				.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        					.addComponent(jLabel2)
        					.addComponent(radEasy))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(4)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        						.addComponent(jButton14, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(btnUndo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(btnRedo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(jLabel3)
        						.addComponent(btnHint, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(jButton11, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(jButton10, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(btnCheck, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(pencilMarkButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(btnNewGame, Alignment.TRAILING))))
        			.addGap(18))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jLabel1)
        					.addGap(13)
        					.addComponent(jLabel2)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(radEasy)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(radMedium)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(radHard)
        					.addPreferredGap(ComponentPlacement.RELATED, 147, Short.MAX_VALUE)
        					.addComponent(jLabel3)
        					.addGap(25)
        					.addComponent(pencilMarkButton)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(btnCheck)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(btnHint)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(jButton10)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(jButton11)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(btnUndo)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(btnRedo)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(jButton14)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(btnNewGame))
        				.addGroup(layout.createSequentialGroup()
        					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(gameBoard, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(pencilMarkGridPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addGroup(layout.createSequentialGroup()
        							.addPreferredGap(ComponentPlacement.UNRELATED)
        							.addComponent(txtAreaMessage, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)))))
        			.addGap(42))
        );
        getContentPane().setLayout(layout);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHintActionPerformed
        //Get a hint
    	if (this.gameSession == null)
    		return;
    	
        HintInfo hint = HintGenerator.getHint(this.gameSession.getGameBoard());
        if (hint != null) {
        	CellCoordinates coordinates = hint.getCell().getCoordinates();
            this.gameBoard.selectCell(coordinates.getRowIndex(), coordinates.getColumnIndex());
            /* Any numbered entered should go through gameSession.enterNumber method for other business logics */
            this.gameSession.enterNumber(hint.getCell(), hint.getNumber());
            this.gameBoard.setSelectedCellNumber(hint.getNumber());
			setMessage(hint.getExplanation());
        }
    }//GEN-LAST:event_btnHintActionPerformed

    private void doUndo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doUndo
    	setMessage("");
    	this.gameSession.doUndo();
		this.gameBoard.refreshPanel();
    }//GEN-LAST:event_doUndo

    private void doRedo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doRedo
    	setMessage("");
    	this.gameSession.doRedo();
		this.gameBoard.refreshPanel();
    }//GEN-LAST:event_doRedo

    private void btnCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckActionPerformed
    	if (this.gameSession == null)
    		return;
    	
        Set<Cell> incorrectCells = this.gameSession.getGameBoard().getIncorrectCells();
        String message = (incorrectCells.isEmpty()) ? "All values are correct so far!" : "";
        setMessage(message);
        this.gameBoard.highlightIncorrectCells(incorrectCells);
        
    }//GEN-LAST:event_btnCheckActionPerformed

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
		setMessage("");
		DifficultyLevel difficulty;
		if (radEasy.isSelected())
			difficulty = DifficultyLevel.Easy;
		else if (radMedium.isSelected())
			difficulty = DifficultyLevel.Medium;
		else
			difficulty = DifficultyLevel.Hard;

		Puzzle puzzle = this.puzzleRepo.getPuzzle(difficulty);
		this.gameSession = new GameSession(puzzle);
		this.gameSession.subscribeForCellChanges(this);
		this.gameBoard.populatePanel(gameSession.getGameBoard().getCellGrid(),
				gameSession);
		this.pencilMarkGridPanel.populate(gameSession);
		
	}// GEN-LAST:event_btnNewGameActionPerformed

    private void pencilMarkMode(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHintActionPerformed

    	if (this.gameSession == null)
    		return;

    	if (this.pencilMarkButton.isSelected()) {
    		System.out.println("CLICKED");
    		this.gameBoard.setVisible(false);
    		this.pencilMarkGridPanel.setVisible(true);
    		this.pencilMarkGridPanel.refresh();
    	} else {
    		System.out.println("UNCLICKED");
    		this.gameBoard.setVisible(true);
    		this.pencilMarkGridPanel.setVisible(false);
    	}
    }
	
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
    private javax.swing.JButton btnCheck;
    private javax.swing.JButton btnHint;
    private javax.swing.JButton btnNewGame;
    private javax.swing.JButton btnRedo;
    private javax.swing.JButton btnUndo;
    private javax.swing.ButtonGroup buttonGroup1;
    private edu.psu.sweng500.team8.gui.GridPanel gameBoard;
    private PencilMarkGridPanel pencilMarkGridPanel;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton14;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JRadioButton radEasy;
    private javax.swing.JRadioButton radHard;
    private javax.swing.JRadioButton radMedium;
    private javax.swing.JTextArea txtAreaMessage;
    // End of variables declaration//GEN-END:variables
    
    private JToggleButton pencilMarkButton;
    
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
