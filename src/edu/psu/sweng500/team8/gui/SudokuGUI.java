package edu.psu.sweng500.team8.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.Group;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JFileChooser;
import javax.swing.LayoutStyle.ComponentPlacement;

import edu.psu.sweng500.team8.coreDataStructures.Puzzle;
import edu.psu.sweng500.team8.coreDataStructures.Puzzle.DifficultyLevel;
import edu.psu.sweng500.team8.play.GameSession;
import edu.psu.sweng500.team8.puzzleGenerator.PuzzleRepository;

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
		actionButtonGroup.addComponent(jButton12);
		actionButtonGroup.addComponent(jButton13);
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
		sg.addComponent(jButton12);
		sg.addPreferredGap(ComponentPlacement.RELATED);
		sg.addComponent(jButton13);
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
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		buttonGroup1 = new javax.swing.ButtonGroup();
		radEasy = new javax.swing.JRadioButton();
		jLabel1 = new javax.swing.JLabel();
		radMedium = new javax.swing.JRadioButton();
		radHard = new javax.swing.JRadioButton();
		jLabel2 = new javax.swing.JLabel();
		jButton10 = new javax.swing.JButton();
		jButton10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveGame();
			}

			private void saveGame() {
				// TODO Auto-generated method stub

			}
		});
		jButton11 = new javax.swing.JButton();
		jButton12 = new javax.swing.JButton();
		jButton12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				undoMove();
			}

			private void undoMove() {
				// TODO Auto-generated method stub

			}
		});
		jButton13 = new javax.swing.JButton();
		jButton13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				redoMove();
			}

			private void redoMove() {
				// TODO Auto-generated method stub

			}
		});
		jButton14 = new javax.swing.JButton();
		btnNewGame = new javax.swing.JButton();
		jLabel3 = new javax.swing.JLabel();
		gameBoard = new edu.psu.sweng500.team8.gui.GridPanel();

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

		jButton12.setText("Undo");
		jButton12.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doUndo(e);
			}
		});

		jButton13.setText("Redo");
		jButton13.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doRedo(e);
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
		
		buttonPencilMark = new javax.swing.JButton();
		buttonPencilMark.setText("Pencil Marks");

		/*
		 * FIXME
		 * If we are going to continue to update GroupLayout, 
		 * it might be better to refactor this early on.
		 * I created a new method called arrangeLayout()
		 * which is refactored version of GroupLayout intialization.
		 * We can remove entire logic around GroupLayout and just call arrangeLayout();
		 * The reason I am not replacing it now is because I saw Jeremy's branch is actively updating this method.
		 * Once we are settle, we may considering using arrangeLayout() method
		 * 
		 * */
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		layout.setHorizontalGroup(layout
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(gameBoard,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED,
										GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addGroup(
										layout.createParallelGroup(
												Alignment.TRAILING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		btnNewGame)
																.addContainerGap())
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				Alignment.LEADING)
																				.addGroup(
																						layout.createParallelGroup(
																								Alignment.LEADING)
																								.addComponent(
																										jLabel1,
																										Alignment.TRAILING)
																								.addComponent(
																										radMedium,
																										Alignment.TRAILING))
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGap(4)
																								.addGroup(
																										layout.createParallelGroup(
																												Alignment.TRAILING)
																												.addComponent(
																														radHard)
																												.addComponent(
																														jLabel2)
																												.addComponent(
																														radEasy)))
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGap(4)
																								.addGroup(
																										layout.createParallelGroup(
																												Alignment.LEADING)
																												.addComponent(
																														jButton12)
																												.addComponent(
																														jButton13)
																												.addComponent(
																														jButton11)
																												.addComponent(
																														jButton10)
																												.addComponent(
																														jLabel3)
																												.addComponent(
																														jButton14))))
																.addGap(18)))));
		layout.setVerticalGroup(layout
				.createParallelGroup(Alignment.TRAILING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(jLabel1)
								.addGap(13)
								.addComponent(jLabel2)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(radEasy)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(radMedium)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(radHard)
								.addPreferredGap(ComponentPlacement.RELATED,
										229, Short.MAX_VALUE)
								.addComponent(jLabel3)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(jButton10)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(jButton11)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(jButton12)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(jButton13)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(jButton14).addGap(18)
								.addComponent(btnNewGame).addContainerGap())
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap(GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(gameBoard,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE).addGap(41)));
		getContentPane().setLayout(layout);

		pack();
	}// </editor-fold>//GEN-END:initComponents

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

	private void doUndo(java.awt.event.ActionEvent evt) {
		this.gameSession.doUndo();
		this.gameBoard.refreshPanel();
	}

	private void doRedo(java.awt.event.ActionEvent evt) {
		this.gameSession.doRedo();
		this.gameBoard.refreshPanel();
	}

	/* we need to keep track of the current game */
	private GameSession gameSession;

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
		gameSession = new GameSession(puzzle);
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
	private javax.swing.JButton btnNewGame;
	private javax.swing.ButtonGroup buttonGroup1;
	private edu.psu.sweng500.team8.gui.GridPanel gameBoard;
	private javax.swing.JButton jButton10;
	private javax.swing.JButton jButton11;
	private javax.swing.JButton jButton12;
	private javax.swing.JButton jButton13;
	private javax.swing.JButton jButton14;
	private javax.swing.JButton buttonPencilMark;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JRadioButton radEasy;
	private javax.swing.JRadioButton radHard;
	private javax.swing.JRadioButton radMedium;
	private final Action action = new SwingAction();

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
