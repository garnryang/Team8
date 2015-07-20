package edu.psu.sweng500.team8.gui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
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
import javax.swing.ImageIcon;
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

import javax.swing.JLabel;
import javax.swing.UIManager;
import com.jgoodies.forms.factories.DefaultComponentFactory;

/**
 *
 */
public class SudokuGUI_Mark2 extends javax.swing.JFrame implements
		CellChangedListener {

	/* Not sure if there is a better place to put this */
	private PuzzleRepository puzzleRepo = new PuzzleRepository();
	/* we need to keep track of the current game */
	private GameSession gameSession;
	private static final String WIN_MESSAGE = "You won! Start a new game to play again.";

	/**
	 * Creates new form SudokuGUI
	 */
	public SudokuGUI_Mark2() {
		setTitle("Lion Sudoku");
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

		if (newNumber < 0) {
			/* pencil mark change */
			this.gameBoard.refreshPencilMarkDisplayOnRelatedCells(cell);
			return;
		}

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
		} else {
			/*
			 * issue 225 - related pencil marks should be immediately cleared
			 * when a cell gets a number
			 */
			this.gameBoard.refreshPencilMarkDisplayOnRelatedCells(cell);
		}
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
		radMedium = new javax.swing.JRadioButton();
		radHard = new javax.swing.JRadioButton();
		btnSave = new javax.swing.JButton();
		btnLoad = new javax.swing.JButton();
		btnUndo = new javax.swing.JButton();
		btnRedo = new javax.swing.JButton();
		jButton14 = new javax.swing.JButton();
		btnNewGame = new javax.swing.JButton();
		gameBoard = new BoardGUI();
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

		buttonGroup1.add(radMedium);
		radMedium.setText("Medium");

		buttonGroup1.add(radHard);
		radHard.setText("Hard");
		radHard.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				radHardActionPerformed(evt);
			}
		});

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
		
		JLabel label = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/lion.png")).getImage();
		label.setIcon(new ImageIcon(img));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(gameBoard, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createSequentialGroup()
							.addGap(10)
							.addComponent(txtAreaMessage, GroupLayout.PREFERRED_SIZE, 480, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(numberInputPad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewGame)
						.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(Alignment.LEADING, layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(btnHint, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(pencilMarkButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
									.addComponent(btnLoad, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnRedo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(Alignment.LEADING)
									.addComponent(btnSave, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
									.addComponent(btnCheck, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnUndo, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
									.addComponent(jButton14, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
									.addComponent(radEasy)
									.addComponent(radMedium)
									.addComponent(radHard)))
							.addComponent(label, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(93, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(gameBoard, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtAreaMessage, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addGap(42))
				.addGroup(layout.createSequentialGroup()
					.addGap(11)
					.addComponent(numberInputPad, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(pencilMarkButton)
						.addComponent(btnCheck))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnHint)
						.addComponent(btnSave))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLoad, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnUndo))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRedo)
						.addComponent(jButton14))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewGame)
						.addComponent(radEasy))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(radMedium)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(radHard)
					.addGap(22))
		);
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
		this.gameBoard.populatePanel(this.gameSession, true, false,
				this.numberInputPad);
	}

	private void doRedo(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_doRedo
		this.setMessage("");
		this.gameSession.doRedo();
		this.gameBoard.populatePanel(this.gameSession, true, false,
				this.numberInputPad);
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

	private void radEasyActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_radEasyActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_radEasyActionPerformed

	private void radHardActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_radHardActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_radHardActionPerformed

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

		this.gameSession = new GameSession(puzzle, overloadedCellGrid);

		this.gameSession.subscribeForCellChanges(this);
		this.numberInputPad.init(buildNumberInputMouseAdapter(),
				this.gameSession);
		this.gameBoard.populatePanel(gameSession, false, false,
				this.numberInputPad);

		this.btnSave.setEnabled(true);
		this.btnHint.setEnabled(true);
		this.btnCheck.setEnabled(true);
		this.btnRedo.setEnabled(true);
		this.btnUndo.setEnabled(true);
		this.pencilMarkButton.setEnabled(true);
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
		this.btnRedo.setEnabled(!isPencilMarkMode);
		this.btnUndo.setEnabled(!isPencilMarkMode);
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
		 * 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(SudokuGUI_Mark2.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(SudokuGUI_Mark2.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(SudokuGUI_Mark2.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(SudokuGUI_Mark2.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new SudokuGUI_Mark2().setVisible(true);
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
	private javax.swing.JRadioButton radEasy;
	private javax.swing.JRadioButton radHard;
	private javax.swing.JRadioButton radMedium;
	private javax.swing.JTextArea txtAreaMessage;
	// End of variables declaration//GEN-END:variables

	private JToggleButton pencilMarkButton;
	private NumberButtonGUI numberInputPad;
	/**
	 * @wbp.nonvisual location=381,9
	 */
	private final JLabel label_1 = DefaultComponentFactory.getInstance().createTitle("New JGoodies title");

	private MouseAdapter buildNumberInputMouseAdapter() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				gameBoard.mouseClickedTaskForNumberInput(mouseEvent);
			}
		};
	}
}
