package edu.psu.sweng500.team8.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;

import javax.swing.JButton;

public class NumberButtonGUI extends javax.swing.JPanel {

//	private static final int BLOCK_SIZE = 162+2;
	private static final int BUTTON_SIZE = 54;
//	private static final Border DEFAULT_BORDER = BorderFactory
//			.createLineBorder(Color.BLACK, 2);
	
	private final static int BUTTON_TEXT_SIZE = 40;
	private final static Font BUTTON_FONT = new Font("Tahoma", Font.PLAIN,
			BUTTON_TEXT_SIZE);
	private final static Dimension BUTTON_DIMENSION = new Dimension(
			BUTTON_SIZE, BUTTON_SIZE);
	
//	private final static Color PENCIL_MARK_COLOR = Color.BLACK;
//	private final static Color PENCIL_MARK_BACKGROUND_COLOR = Color.WHITE;
	
	
//	private final static PencilMarkButtonUI BUTTON_UI = new PencilMarkButtonUI();
//	private Cell cell;
//	private GameSession gameSession;

	
	public void init(MouseAdapter mouseAdapter) {
	for (int numberIndex = 1; numberIndex <= 9; numberIndex++) {
			buttons[numberIndex - 1].addMouseListener(mouseAdapter);
		}
	}
	
	JButton[] buttons = new JButton[9];
	
	
	public NumberButtonGUI() {
		
		this.setLayout(new GridBagLayout());
	
		for (int numberIndex = 1; numberIndex <= 9; numberIndex++) {
			
			/* 1 - 9 */
			JButton numberInputButton = new JButton(String.valueOf(numberIndex));
			
			numberInputButton.setFont(BUTTON_FONT);
			numberInputButton
					.setPreferredSize(BUTTON_DIMENSION);
			numberInputButton.setMaximumSize(BUTTON_DIMENSION);
			numberInputButton.setMinimumSize(BUTTON_DIMENSION);
//			numberInputButton.addActionListener(buildActionListner());

			GridBagConstraints buttonGridBagConstraints = new GridBagConstraints();
			buttonGridBagConstraints.gridx = (numberIndex - 1) % 3;
			buttonGridBagConstraints.gridy = (numberIndex - 1) / 3;
			this.add(numberInputButton, buttonGridBagConstraints);
			buttons[numberIndex - 1] = numberInputButton;
		}
	
	}
//	
//	
//	public NumberButtonGUI(Cell cell, GameSession gameSession) {
//
//		this.cell = cell;
//		this.gameSession = gameSession;
//		this.setLayout(new GridBagLayout());
//
//		for (int numberIndex = 1; numberIndex <= 9; numberIndex++) {
//
//			JToggleButton numberInputButton = new JToggleButton(
//					String.valueOf(numberIndex));
//			numberInputButton.setFont(PENCIL_MARK_FONT);
//			numberInputButton.setForeground(PENCIL_MARK_COLOR);
//			numberInputButton.setUI(BUTTON_UI);
//			numberInputButton.setBackground(PENCIL_MARK_BACKGROUND_COLOR);
//			numberInputButton
//					.setPreferredSize(PENCIL_MARK_NUMBER_DIMENSION);
//			numberInputButton.setMaximumSize(PENCIL_MARK_NUMBER_DIMENSION);
//			numberInputButton.setMinimumSize(PENCIL_MARK_NUMBER_DIMENSION);
//			numberInputButton.addActionListener(buildActionListner());
//
//			GridBagConstraints gbc_numberInputButton = new GridBagConstraints();
//			gbc_numberInputButton.gridx = (numberIndex - 1) % 3;
//			gbc_numberInputButton.gridy = (numberIndex - 1) / 3;
//			this.add(numberInputButton, gbc_numberInputButton);
//		}
//	}
//
//	private ActionListener buildActionListner() {
//		return new ActionListener() {
//			public void actionPerformed(ActionEvent evt) {
//				buttonAction(evt);
//			}
//		};
//	}
//
//	private void buttonAction(ActionEvent actionEvent) {
//
//		JToggleButton button = (JToggleButton) actionEvent.getSource();
//
//		boolean isSelected = button.isSelected();
//		this.gameSession.enterPencilMark(this.cell,
//				Integer.parseInt(button.getText()), isSelected);
//	}
}
