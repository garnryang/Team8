package edu.psu.sweng500.team8.gui;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;

import edu.psu.sweng500.team8.coreDataStructures.Cell;
import edu.psu.sweng500.team8.play.GameSession;

public class CombinationCell extends javax.swing.JPanel {
	
	private final static int PENCIL_MARK_SIZE = 18;
	private final static int PENCIL_MARk_TEXT_SIZE = 15;
	private final static Color PENCIL_MARK_COLOR = Color.BLACK;
	private final static Color PENCIL_MARK_BACKGROUND_COLOR = Color.WHITE;
	private Cell cell;
	private GameSession gameSession;
	
	public CombinationCell(Cell cell, GameSession gameSession) {

		this.cell = cell;
		this.gameSession = gameSession;
		
		setPreferredSize(new Dimension(PENCIL_MARK_SIZE*3, PENCIL_MARK_SIZE*3));
		setBorder(null);
		setAlignmentY(Component.TOP_ALIGNMENT);
		setAlignmentX(Component.LEFT_ALIGNMENT);
		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);
		
		//==
		JToggleButton tglbtnNewToggleButton_1 = new JToggleButton("1");
		tglbtnNewToggleButton_1.setFont(new Font("Tahoma", Font.PLAIN, PENCIL_MARk_TEXT_SIZE));
		tglbtnNewToggleButton_1.setMargin(new Insets(0, 0, 0, 0));
		tglbtnNewToggleButton_1.setForeground(PENCIL_MARK_COLOR);
		tglbtnNewToggleButton_1.setUI(new PencilMarkButtonUI());
		tglbtnNewToggleButton_1.setBackground(PENCIL_MARK_BACKGROUND_COLOR);
		tglbtnNewToggleButton_1.setPreferredSize(new Dimension(PENCIL_MARK_SIZE, PENCIL_MARK_SIZE));
		
		tglbtnNewToggleButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	buttonAction(evt);
            }
        });
		
		GridBagConstraints gbc_tglbtnNewToggleButton_1 = new GridBagConstraints();
		gbc_tglbtnNewToggleButton_1.gridx = 0;
		gbc_tglbtnNewToggleButton_1.gridy = 0;
		add(tglbtnNewToggleButton_1, gbc_tglbtnNewToggleButton_1);
		
		//==
		JToggleButton tglbtnNewToggleButton_2 = new JToggleButton("2");
		tglbtnNewToggleButton_2.setFont(new Font("Tahoma", Font.PLAIN, PENCIL_MARk_TEXT_SIZE));
		tglbtnNewToggleButton_2.setMargin(new Insets(0, 0, 0, 0));
		tglbtnNewToggleButton_2.setForeground(PENCIL_MARK_COLOR);
		tglbtnNewToggleButton_2.setUI(new PencilMarkButtonUI());
		tglbtnNewToggleButton_2.setBackground(PENCIL_MARK_BACKGROUND_COLOR);
		tglbtnNewToggleButton_2.setPreferredSize(new Dimension(PENCIL_MARK_SIZE, PENCIL_MARK_SIZE));
		
		tglbtnNewToggleButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	buttonAction(evt);
            }
        });
		
		GridBagConstraints gbc_tglbtnNewToggleButton_2 = new GridBagConstraints();
		gbc_tglbtnNewToggleButton_2.anchor = GridBagConstraints.NORTHWEST;
		gbc_tglbtnNewToggleButton_2.gridx = 1;
		gbc_tglbtnNewToggleButton_2.gridy = 0;
		add(tglbtnNewToggleButton_2, gbc_tglbtnNewToggleButton_2);
		
		//==
		JToggleButton tglbtnNewToggleButton_3 = new JToggleButton("3");
		tglbtnNewToggleButton_3.setFont(new Font("Tahoma", Font.PLAIN, PENCIL_MARk_TEXT_SIZE));
		tglbtnNewToggleButton_3.setMargin(new Insets(0, 0, 0, 0));
		tglbtnNewToggleButton_3.setForeground(PENCIL_MARK_COLOR);
		tglbtnNewToggleButton_3.setUI(new PencilMarkButtonUI());
		tglbtnNewToggleButton_3.setBackground(PENCIL_MARK_BACKGROUND_COLOR);
		tglbtnNewToggleButton_3.setPreferredSize(new Dimension(PENCIL_MARK_SIZE, PENCIL_MARK_SIZE));

		tglbtnNewToggleButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	buttonAction(evt);
            }
        });

		
		GridBagConstraints gbc_tglbtnNewToggleButton_3 = new GridBagConstraints();
		gbc_tglbtnNewToggleButton_3.anchor = GridBagConstraints.NORTHWEST;
		gbc_tglbtnNewToggleButton_3.gridx = 2;
		gbc_tglbtnNewToggleButton_3.gridy = 0;
		add(tglbtnNewToggleButton_3, gbc_tglbtnNewToggleButton_3);
		
		//==
		JToggleButton tglbtnNewToggleButton_4 =new JToggleButton("4");
		tglbtnNewToggleButton_4.setFont(new Font("Tahoma", Font.PLAIN, PENCIL_MARk_TEXT_SIZE));
		tglbtnNewToggleButton_4.setMargin(new Insets(0, 0, 0, 0));
		tglbtnNewToggleButton_4.setForeground(PENCIL_MARK_COLOR);
		tglbtnNewToggleButton_4.setUI(new PencilMarkButtonUI());
		tglbtnNewToggleButton_4.setBackground(PENCIL_MARK_BACKGROUND_COLOR);
		tglbtnNewToggleButton_4.setPreferredSize(new Dimension(PENCIL_MARK_SIZE, PENCIL_MARK_SIZE));
		
		tglbtnNewToggleButton_4.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	buttonAction(evt);
            }
        });

		
		GridBagConstraints gbc_tglbtnNewToggleButton_4 = new GridBagConstraints();
		gbc_tglbtnNewToggleButton_4.anchor = GridBagConstraints.NORTHWEST;
		gbc_tglbtnNewToggleButton_4.gridx = 0;
		gbc_tglbtnNewToggleButton_4.gridy = 1;
		add(tglbtnNewToggleButton_4, gbc_tglbtnNewToggleButton_4);
		
		//==
		JToggleButton tglbtnNewToggleButton_5 = new JToggleButton("5");
		tglbtnNewToggleButton_5.setFont(new Font("Tahoma", Font.PLAIN, PENCIL_MARk_TEXT_SIZE));
		tglbtnNewToggleButton_5.setMargin(new Insets(0, 0, 0, 0));
		tglbtnNewToggleButton_5.setForeground(PENCIL_MARK_COLOR);
		tglbtnNewToggleButton_5.setUI(new PencilMarkButtonUI());
		tglbtnNewToggleButton_5.setBackground(PENCIL_MARK_BACKGROUND_COLOR);
		tglbtnNewToggleButton_5.setPreferredSize(new Dimension(PENCIL_MARK_SIZE, PENCIL_MARK_SIZE));
		
		tglbtnNewToggleButton_5.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	buttonAction(evt);
            }
        });

		
		GridBagConstraints gbc_tglbtnNewToggleButton_5 = new GridBagConstraints();
		gbc_tglbtnNewToggleButton_5.anchor = GridBagConstraints.NORTHWEST;
		gbc_tglbtnNewToggleButton_5.gridx = 1;
		gbc_tglbtnNewToggleButton_5.gridy = 1;
		add(tglbtnNewToggleButton_5, gbc_tglbtnNewToggleButton_5);
		
		//==
		JToggleButton tglbtnNewToggleButton_6 = new JToggleButton("6");
		tglbtnNewToggleButton_6.setFont(new Font("Tahoma", Font.PLAIN, PENCIL_MARk_TEXT_SIZE));
		tglbtnNewToggleButton_6.setMargin(new Insets(0, 0, 0, 0));
		tglbtnNewToggleButton_6.setForeground(PENCIL_MARK_COLOR);
		tglbtnNewToggleButton_6.setUI(new PencilMarkButtonUI());
		tglbtnNewToggleButton_6.setBackground(PENCIL_MARK_BACKGROUND_COLOR);
		tglbtnNewToggleButton_6.setPreferredSize(new Dimension(PENCIL_MARK_SIZE, PENCIL_MARK_SIZE));

		tglbtnNewToggleButton_6.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	buttonAction(evt);
            }
        });

		GridBagConstraints gbc_tglbtnNewToggleButton_6 = new GridBagConstraints();
		gbc_tglbtnNewToggleButton_6.anchor = GridBagConstraints.NORTHWEST;
		gbc_tglbtnNewToggleButton_6.gridx = 2;
		gbc_tglbtnNewToggleButton_6.gridy = 1;
		add(tglbtnNewToggleButton_6, gbc_tglbtnNewToggleButton_6);
		
		//==
		JToggleButton tglbtnNewToggleButton_7 = new JToggleButton("7");
		tglbtnNewToggleButton_7.setFont(new Font("Tahoma", Font.PLAIN, PENCIL_MARk_TEXT_SIZE));
		tglbtnNewToggleButton_7.setMargin(new Insets(0, 0, 0, 0));
		tglbtnNewToggleButton_7.setForeground(PENCIL_MARK_COLOR);
		tglbtnNewToggleButton_7.setUI(new PencilMarkButtonUI());
		tglbtnNewToggleButton_7.setBackground(PENCIL_MARK_BACKGROUND_COLOR);
		tglbtnNewToggleButton_7.setPreferredSize(new Dimension(PENCIL_MARK_SIZE, PENCIL_MARK_SIZE));
		
		tglbtnNewToggleButton_7.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	buttonAction(evt);
            }
        });
		
		GridBagConstraints gbc_tglbtnNewToggleButton_7 = new GridBagConstraints();
		gbc_tglbtnNewToggleButton_7.anchor = GridBagConstraints.NORTHWEST;
		gbc_tglbtnNewToggleButton_7.gridx = 0;
		gbc_tglbtnNewToggleButton_7.gridy = 2;
		add(tglbtnNewToggleButton_7, gbc_tglbtnNewToggleButton_7);
		
		//==
		JToggleButton tglbtnNewToggleButton_8 = new JToggleButton("8");
		tglbtnNewToggleButton_8.setFont(new Font("Tahoma", Font.PLAIN, PENCIL_MARk_TEXT_SIZE));
		tglbtnNewToggleButton_8.setMargin(new Insets(0, 0, 0, 0));
		tglbtnNewToggleButton_8.setForeground(PENCIL_MARK_COLOR);
		tglbtnNewToggleButton_8.setUI(new PencilMarkButtonUI());
		tglbtnNewToggleButton_8.setBackground(PENCIL_MARK_BACKGROUND_COLOR);		
		tglbtnNewToggleButton_8.setPreferredSize(new Dimension(PENCIL_MARK_SIZE, PENCIL_MARK_SIZE));
		
		tglbtnNewToggleButton_8.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	buttonAction(evt);
            }
        });
		
		GridBagConstraints gbc_tglbtnNewToggleButton_8 = new GridBagConstraints();
		gbc_tglbtnNewToggleButton_8.anchor = GridBagConstraints.NORTHWEST;
		gbc_tglbtnNewToggleButton_8.gridx = 1;
		gbc_tglbtnNewToggleButton_8.gridy = 2;
		add(tglbtnNewToggleButton_8, gbc_tglbtnNewToggleButton_8);
		
		//==
		JToggleButton tglbtnNewToggleButton_9 = new JToggleButton("9");
		tglbtnNewToggleButton_9.setFont(new Font("Tahoma", Font.PLAIN, PENCIL_MARk_TEXT_SIZE));
		tglbtnNewToggleButton_9.setMargin(new Insets(0, 0, 0, 0));
		tglbtnNewToggleButton_9.setForeground(PENCIL_MARK_COLOR);
		tglbtnNewToggleButton_9.setUI(new PencilMarkButtonUI());
		tglbtnNewToggleButton_9.setBackground(PENCIL_MARK_BACKGROUND_COLOR);
		tglbtnNewToggleButton_9.setPreferredSize(new Dimension(PENCIL_MARK_SIZE, PENCIL_MARK_SIZE));
		
		tglbtnNewToggleButton_9.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	buttonAction(evt);
            }
        });
		
		GridBagConstraints gbc_tglbtnNewToggleButton_9 = new GridBagConstraints();
		gbc_tglbtnNewToggleButton_9.anchor = GridBagConstraints.NORTHWEST;
		gbc_tglbtnNewToggleButton_9.gridx = 2;
		gbc_tglbtnNewToggleButton_9.gridy = 2;
		add(tglbtnNewToggleButton_9, gbc_tglbtnNewToggleButton_9);
	}
	
	private void buttonAction(ActionEvent actionEvent) {
		JToggleButton button = (JToggleButton) actionEvent.getSource();
		if (button.isSelected()) {
			gameSession.enterPencilMark(this.cell, Integer.parseInt(button.getText()), true);
			
			System.out.println("SELECTED " + button.getText());
			System.out.println("CURRENT MARKS: " + this.cell.getPencilMarks());
		} else {
			gameSession.enterPencilMark(this.cell, Integer.parseInt(button.getText()), false);
			
			System.out.println("UNSELECTED " + button.getText());
			System.out.println("CURRENT MARKS: " + this.cell.getPencilMarks());
		}
	}
}
