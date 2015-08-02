package edu.psu.sweng500.team8.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.filechooser.FileFilter;

import edu.psu.sweng500.team8.coreDataStructures.Board;
import edu.psu.sweng500.team8.coreDataStructures.Cell;
import edu.psu.sweng500.team8.coreDataStructures.CellCoordinates;
import edu.psu.sweng500.team8.coreDataStructures.CellGrid;
import edu.psu.sweng500.team8.coreDataStructures.Puzzle;
import edu.psu.sweng500.team8.coreDataStructures.Puzzle.DifficultyLevel;
import edu.psu.sweng500.team8.coreDataStructures.SavePackage;
import edu.psu.sweng500.team8.play.CellChangedListener;
import edu.psu.sweng500.team8.play.GameSession;
import edu.psu.sweng500.team8.puzzleGenerator.PuzzleRepository;
import edu.psu.sweng500.team8.solver.HintGenerator;
import edu.psu.sweng500.team8.solver.HintInfo;

public class SudokuGUI extends javax.swing.JFrame implements
		CellChangedListener {
	/*
	 * Not really necessary since we're not serializing the UI, but just to keep
	 * Java happy...
	 */
	private static final long serialVersionUID = 1L;

	/* Not sure if there is a better place to put this */
	private PuzzleRepository puzzleRepo = new PuzzleRepository();
	/* we need to keep track of the current game */
	private GameSession gameSession;
	private static final String WIN_MESSAGE = "You won! Start a new game to play again.";

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
		
		this.numberInputPad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent mouseEvent) {
				gameBoard.mouseClickedTaskForNumberInput(mouseEvent);
			}
		});
	}

	public void setMessage(String message) {
		this.txtAreaMessage.setText(message);
	}

	public void clearMessage() {
		this.txtAreaMessage.setText("");
	}

	@Override
	public void cellChanged(Cell cell, int newNumber) {

		/*
		 * Cell number changed. Clear the message and any highlighted incorrect
		 * numbers.
		 */
		this.clearMessage();
		this.gameBoard.clearHighlightedIncorrectCells();

		if (gameIsComplete()) {
			// Player won the game
			this.gameBoard.disableEditing();
			setMessage(WIN_MESSAGE);
			btnCheck.setEnabled(false);
			btnHint.setEnabled(false);
			btnUndo.setEnabled(false);
			btnRedo.setEnabled(false);
		} else {
			/*
			 * issue 225 - related pencil marks should be immediately cleared
			 * when a cell gets a number
			 */
			this.gameBoard.refreshPencilMarkDisplayOnRelatedCells(cell);
			updateUndoRedoButtonStates();
		}
	}
	
	@Override
	public void pencilMarksChanged(Cell cell, Set<Integer> newPencilMarks) {
		this.gameBoard.refreshPencilMarkDisplayOnRelatedCells(cell);
		updateUndoRedoButtonStates();
	}

	private boolean gameIsComplete() {
		Board board = this.gameSession.getGameBoard();
		return (!board.hasOpenCells() && board.getIncorrectCells().isEmpty());
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	private void initComponents() {

		buttonGroup1 = new javax.swing.ButtonGroup();
		radEasy = new javax.swing.JRadioButton();
		jLabel1 = new javax.swing.JLabel();
		radMedium = new javax.swing.JRadioButton();
		radHard = new javax.swing.JRadioButton();
		jLabel2 = new javax.swing.JLabel();
		btnSave = new javax.swing.JButton();
		btnLoad = new javax.swing.JButton();
		btnUndo = new javax.swing.JButton();
		btnRedo = new javax.swing.JButton();
		jButton14 = new javax.swing.JButton();
		btnNewGame = new javax.swing.JButton();
		jLabel3 = new javax.swing.JLabel();
		gameBoard = new BoardGUI();
		btnHint = new javax.swing.JButton();
		pencilMarkButton = new JToggleButton();
		btnCheck = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		buttonGroup1.add(radEasy);
		radEasy.setSelected(true);
		radEasy.setText("Easy");

		jLabel1.setText("Menu");

		buttonGroup1.add(radMedium);
		radMedium.setText("Medium");

		buttonGroup1.add(radHard);
		radHard.setText("Hard");

		this.jLabel2.setText("Difficulty");

		this.btnSave.setText("Save");
		this.btnSave.setEnabled(false);
		this.btnSave.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					btnSaveActionPerformed(evt);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		this.btnLoad.setText("Load");
		this.btnLoad.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnLoadActionPerformed(evt);
			}
		});

		this.btnUndo.setText("Undo");
		this.btnUndo.setEnabled(false);
		this.btnUndo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				doUndo(evt);
			}
		});

		this.btnRedo.setText("Redo");
		this.btnRedo.setEnabled(false);
		this.btnRedo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				doRedo(evt);
			}
		});

		this.jButton14.setText("Help");
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

		this.btnHint.setText("Hint");
		this.btnHint.setEnabled(false);
		this.btnHint.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnHintActionPerformed(evt);
			}
		});

		this.pencilMarkButton.setText("Pencil Mark");
		this.pencilMarkButton.setEnabled(false);
		this.pencilMarkButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						pencilMarkMode(evt);
					}
				});

		this.btnCheck.setText("Check");
		this.btnCheck.setEnabled(false);
		this.btnCheck.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCheckActionPerformed(evt);
			}
		});

		txtAreaMessage = new JTextArea();
		txtAreaMessage.setWrapStyleWord(true);
		txtAreaMessage.setLineWrap(true);
		txtAreaMessage.setEditable(false);

		numberInputPad = new NumberButtonGUI();

		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		layout.setHorizontalGroup(layout
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												Alignment.LEADING)
												.addComponent(
														gameBoard,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addGroup(
														layout.createSequentialGroup()
																.addGap(10)
																.addComponent(
																		txtAreaMessage,
																		GroupLayout.PREFERRED_SIZE,
																		480,
																		GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												Alignment.LEADING)
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
																				.addComponent(
																						radHard)
																				.addGroup(
																						layout.createParallelGroup(
																								Alignment.TRAILING)
																								.addComponent(
																										jLabel2)
																								.addComponent(
																										radEasy)))
																.addGap(34)
																.addComponent(
																		numberInputPad,
																		GroupLayout.PREFERRED_SIZE,
																		170,
																		GroupLayout.PREFERRED_SIZE))
												.addGroup(
														layout.createSequentialGroup()
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				Alignment.LEADING,
																				false)
																				.addComponent(
																						jButton14,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						btnUndo,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						btnRedo,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						jLabel3)
																				.addComponent(
																						btnHint,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						btnLoad,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						btnSave,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						btnCheck,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						pencilMarkButton,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addGroup(
																						Alignment.TRAILING,
																						layout.createParallelGroup(
																								Alignment.LEADING,
																								false)
																								.addComponent(
																										btnExit,
																										GroupLayout.DEFAULT_SIZE,
																										GroupLayout.DEFAULT_SIZE,
																										Short.MAX_VALUE)
																								.addComponent(
																										btnNewGame,
																										GroupLayout.DEFAULT_SIZE,
																										GroupLayout.DEFAULT_SIZE,
																										Short.MAX_VALUE)))))
								.addGap(27)));
		layout.setVerticalGroup(layout
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jLabel1)
																.addGap(13)
																.addComponent(
																		jLabel2)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		radEasy)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		radMedium)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		radHard)
																.addPreferredGap(
																		ComponentPlacement.RELATED,
																		157,
																		Short.MAX_VALUE)
																.addComponent(
																		jLabel3)
																.addGap(25)
																.addComponent(
																		pencilMarkButton)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		btnCheck)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		btnHint)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		btnSave)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		btnLoad)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		btnUndo)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		btnRedo)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		jButton14)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		btnNewGame))
												.addGroup(
														layout.createSequentialGroup()
																.addContainerGap(
																		GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addComponent(
																		gameBoard,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		ComponentPlacement.UNRELATED)
																.addComponent(
																		txtAreaMessage,
																		GroupLayout.PREFERRED_SIZE,
																		45,
																		GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnExit).addGap(13))
				.addGroup(
						layout.createSequentialGroup()
								.addGap(11)
								.addComponent(numberInputPad,
										GroupLayout.PREFERRED_SIZE, 164,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));
		getContentPane().setLayout(layout);
		getContentPane().setPreferredSize(new Dimension(800, 600));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void btnHintActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnHintActionPerformed
		// Get a hint
		if (this.gameSession == null) {
			return;
		}

		HintInfo hint = HintGenerator.getHint(this.gameSession.getGameBoard());

		/* TODO - make this message constant */
		String message = "Sorry, no hint available";
		if (hint != null) {
			CellCoordinates coordinates = hint.getCell().getCoordinates();

			this.gameBoard.updateSelectedCellFromHint(coordinates,
					hint.getNumber());
			if (hint.getNumber() != 0)
				this.gameSession.enterNumber(hint.getCell(), hint.getNumber());

			message = hint.getExplanation();
			if (gameIsComplete()) {
				// If hint resulted in completing the game, add the Win message
				// and disable editing.
				this.gameBoard.disableEditing();
				message += " " + WIN_MESSAGE;
			}
		}

		setMessage(message);
	}

	private void doUndo(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_doUndo
		this.setMessage("");
		this.gameSession.doUndo();
		this.gameBoard.populatePanel(this.gameSession, true,
				this.gameSession.isPencilMarkMode(), this.numberInputPad);

		this.numberInputPad.updateForFocusedCell(this.gameBoard.getSelectedCell());
		updateUndoRedoButtonStates();
	}

	private void doRedo(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_doRedo
		this.setMessage("");
		this.gameSession.doRedo();
		this.gameBoard.populatePanel(this.gameSession, true,
				this.gameSession.isPencilMarkMode(), this.numberInputPad);

		this.numberInputPad.updateForFocusedCell(this.gameBoard.getSelectedCell());
		updateUndoRedoButtonStates();
	}

	private void updateUndoRedoButtonStates() {
		btnUndo.setEnabled(this.gameSession.hasUndoActions());
		btnRedo.setEnabled(this.gameSession.hasRedoActions());
	}

	private void btnCheckActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCheckActionPerformed
		if (this.gameSession == null)
			return;

		Set<Cell> incorrectCells = this.gameSession.getGameBoard()
				.getIncorrectCells();
		String message = (incorrectCells.isEmpty()) ? "All values are correct so far!"
				: "";
		this.setMessage(message);
		this.gameBoard.highlightIncorrectCells(incorrectCells);

	}// GEN-LAST:event_btnCheckActionPerformed

	/**
	 * LOAD Action
	 * 
	 * @param evt
	 */
	private void btnLoadActionPerformed(ActionEvent evt) {

		final JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new FileFilter() {
			@Override
			public boolean accept(File file) {
				return (file.getName().toUpperCase().endsWith(".SUDOKU") || file
						.isDirectory());
			}

			@Override
			public String getDescription() {
				return "Sudoku files";
			}
		});

		fc.setAcceptAllFileFilterUsed(false);
		int returnVal = fc.showOpenDialog(getComponent(0));

		if (returnVal == JFileChooser.APPROVE_OPTION) {

			SavePackage savePackage = null;
			try {
				FileInputStream fileIn = new FileInputStream(fc
						.getSelectedFile().getAbsolutePath());
				ObjectInputStream in = new ObjectInputStream(fileIn);
				savePackage = (SavePackage) in.readObject();
				in.close();
				fileIn.close();

				Puzzle puzzle = savePackage.getPuzzle();

				DifficultyLevel difficulty = puzzle.getDifficulty();
				if (difficulty == DifficultyLevel.Easy) {
					radEasy.setSelected(true);
				} else if (difficulty == DifficultyLevel.Medium) {
					radMedium.setSelected(true);
				} else {
					radHard.setSelected(true);
				}

				CellGrid cellGrid = savePackage.getCellGrid();

				this.loadSession(puzzle, cellGrid);

				/* We need to populate non given cells */

			} catch (IOException i) {
				i.printStackTrace();
			} catch (ClassNotFoundException c) {
				c.printStackTrace();
			}
		}
	}

	/**
	 * SAVE Action
	 * 
	 * @param evt
	 * @throws FileNotFoundException
	 */
	private void btnSaveActionPerformed(ActionEvent evt)
			throws FileNotFoundException {

		final JFileChooser fc = new JFileChooser();

		fc.setFileFilter(new FileFilter() {
			@Override
			public boolean accept(File file) {
				return (file.getName().toUpperCase().endsWith(".SUDOKU") || file
						.isDirectory());
			}

			@Override
			public String getDescription() {
				return "Sudoku files";
			}
		});

		fc.setAcceptAllFileFilterUsed(false);

		int returnVal = fc.showSaveDialog(getComponent(0));

		if (returnVal == JFileChooser.APPROVE_OPTION) {

			File f = fc.getSelectedFile();
			String path = f.getAbsolutePath();

			if (f.exists()) {
				int result = JOptionPane.showConfirmDialog(this,
						"The file exists, overwrite?", "Existing file",
						JOptionPane.YES_NO_CANCEL_OPTION);

				switch (result) {
				case JOptionPane.YES_OPTION:
					fc.approveSelection();
					savePuzzle(path);
					/* Why do we need to remark given cells ?? */
					// gameBoard.remarkGivenCells(this.gameSession.getGameBoard()
					// .getCellGrid());
					return;
				case JOptionPane.NO_OPTION:
					return;
				case JOptionPane.CLOSED_OPTION:
					return;
				case JOptionPane.CANCEL_OPTION:
					fc.cancelSelection();
					return;
				}
			}

			fc.approveSelection();
			savePuzzle(path);
			/* Why do we need to remark given cells ?? */
			// gameBoard.remarkGivenCells(this.gameSession.getGameBoard()
			// .getCellGrid());
			/* Why do we need to re- populate ?? */
			// this.pencilMarkGridPanel.populate(gameSession);
		}
	}

	private void savePuzzle(String path) {
		try {

			if (!path.toLowerCase().endsWith(".sudoku")) {
				path = path + ".sudoku";
			}

			SavePackage savePackage = new SavePackage();
			savePackage.setCellGrid(this.gameSession.getGameBoard()
					.getCellGrid());
			savePackage.setPuzzle(this.gameSession.getGameBoard()
					.getCurrentPuzzle());

			FileOutputStream fileOut = new FileOutputStream(path);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);

			out.writeObject(savePackage);
			out.close();
			fileOut.close();

		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	private void loadSession(Puzzle puzzle, CellGrid overloadedCellGrid) {

		this.gameSession = (overloadedCellGrid == null) ? new GameSession(puzzle) : new GameSession(puzzle, overloadedCellGrid);

		this.gameSession.subscribeForCellChanges(this);
		this.numberInputPad.init(this.gameSession);
		this.gameBoard.populatePanel(gameSession, false, false,
				this.numberInputPad);

		this.btnSave.setEnabled(true);
		this.btnHint.setEnabled(true);
		this.btnCheck.setEnabled(true);
		this.pencilMarkButton.setEnabled(true);
		updateUndoRedoButtonStates();
	}

	private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton14ActionPerformed
		// TODO add your handling code here:
		openHelp();
	}// GEN-LAST:event_jButton14ActionPerformed

	private void openHelp() {
		// TODO Auto-generated method stub

	}

	private void btnNewGameActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnNewGameActionPerformed
		this.setMessage("");

		DifficultyLevel difficulty = null;

		if (radEasy.isSelected()) {
			difficulty = DifficultyLevel.Easy;
		} else if (radMedium.isSelected()) {
			difficulty = DifficultyLevel.Medium;
		} else {
			difficulty = DifficultyLevel.Hard;
		}

		Puzzle puzzle = this.puzzleRepo.getPuzzle(difficulty);

		loadSession(puzzle, null);
	}

	private void pencilMarkMode(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnHintActionPerformed

		if (this.gameSession == null) {
			return;
		}

		boolean isPencilMarkMode = this.pencilMarkButton.isSelected();

		this.gameSession.setPencilMarkMode(isPencilMarkMode);
		this.btnHint.setEnabled(!isPencilMarkMode);
		this.btnCheck.setEnabled(!isPencilMarkMode);
		this.gameBoard.populatePanel(gameSession, true, isPencilMarkMode,
				this.numberInputPad);
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed"
		// desc=" Look and feel setting code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase
		 * /tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(SudokuGUI.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(SudokuGUI.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(SudokuGUI.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(SudokuGUI.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
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
	private BoardGUI gameBoard;
	private javax.swing.JButton btnSave; /* name changed from jButton10 */
	private javax.swing.JButton btnLoad; /* name changed from jButton11 */
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
	private NumberButtonGUI numberInputPad;
	private JButton btnExit;
	
	/**
	 * for testing
	 * @return
	 */
	GameSession getGameSession() {
		return this.gameSession;
	}
}
