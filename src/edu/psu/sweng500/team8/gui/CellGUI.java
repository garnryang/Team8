package edu.psu.sweng500.team8.gui;

import java.awt.GridBagConstraints;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class CellGUI extends JPanel {
	
	private JLabel[][] numbers;
	
	public CellGUI() {
	
		numbers = new JLabel[3][3];
		
		GridBagConstraints gridBagConstraints;
		setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		setLayout(new java.awt.GridBagLayout());
    
		numbers[0][0] = new JLabel();

		numbers[0][0].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    numbers[0][0].setText("1");
    numbers[0][0].setMaximumSize(new java.awt.Dimension(18, 18));
    numbers[0][0].setMinimumSize(new java.awt.Dimension(18, 18));
    numbers[0][0].setPreferredSize(new java.awt.Dimension(18, 18));
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(1, 1, 0, 0);
    add(numbers[0][0], gridBagConstraints);

    numbers[0][1].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    numbers[0][1].setText("2");
    numbers[0][1].setMaximumSize(new java.awt.Dimension(18, 18));
    numbers[0][1].setMinimumSize(new java.awt.Dimension(18, 18));
    numbers[0][1].setPreferredSize(new java.awt.Dimension(18, 18));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(1, 0, 0, 0);
    add(numbers[0][1], gridBagConstraints);

    numbers[0][2].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    numbers[0][2].setText("3");
    numbers[0][2].setMaximumSize(new java.awt.Dimension(18, 18));
    numbers[0][2].setMinimumSize(new java.awt.Dimension(18, 18));
    numbers[0][2].setPreferredSize(new java.awt.Dimension(18, 18));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(1, 0, 0, 1);
    add(numbers[0][2], gridBagConstraints);

    numbers[1][0].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    numbers[1][0].setText("4");
    numbers[1][0].setMaximumSize(new java.awt.Dimension(18, 18));
    numbers[1][0].setMinimumSize(new java.awt.Dimension(18, 18));
    numbers[1][0].setPreferredSize(new java.awt.Dimension(18, 18));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
    add(numbers[1][0], gridBagConstraints);

    numbers[1][1].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    numbers[1][1].setText("5");
    numbers[1][1].setMaximumSize(new java.awt.Dimension(18, 18));
    numbers[1][1].setMinimumSize(new java.awt.Dimension(18, 18));
    numbers[1][1].setPreferredSize(new java.awt.Dimension(18, 18));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    add(numbers[1][1], gridBagConstraints);

    numbers[1][2].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    numbers[1][2].setText("6");
    numbers[1][2].setMaximumSize(new java.awt.Dimension(18, 18));
    numbers[1][2].setMinimumSize(new java.awt.Dimension(18, 18));
    numbers[1][2].setPreferredSize(new java.awt.Dimension(18, 18));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 1);
    add(numbers[1][2], gridBagConstraints);

    jLabel818.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel818.setText("7");
    jLabel818.setMaximumSize(new java.awt.Dimension(18, 18));
    jLabel818.setMinimumSize(new java.awt.Dimension(18, 18));
    jLabel818.setPreferredSize(new java.awt.Dimension(18, 18));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 1, 1, 0);
    jPanel91.add(jLabel818, gridBagConstraints);

    jLabel819.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel819.setText("8");
    jLabel819.setMaximumSize(new java.awt.Dimension(18, 18));
    jLabel819.setMinimumSize(new java.awt.Dimension(18, 18));
    jLabel819.setPreferredSize(new java.awt.Dimension(18, 18));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 1, 0);
    jPanel91.add(jLabel819, gridBagConstraints);

    jLabel820.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel820.setText("9");
    jLabel820.setMaximumSize(new java.awt.Dimension(18, 18));
    jLabel820.setMinimumSize(new java.awt.Dimension(18, 18));
    jLabel820.setPreferredSize(new java.awt.Dimension(18, 18));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 1, 1);
    jPanel91.add(jLabel820, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    jBox3.add(jPanel91, gridBagConstraints);
}
}
