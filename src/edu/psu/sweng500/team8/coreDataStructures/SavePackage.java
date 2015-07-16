package edu.psu.sweng500.team8.coreDataStructures;

import java.io.Serializable;

import javax.swing.JTextField;

public class SavePackage  implements Serializable {
	private CellGrid m_cellGrid;
	private Puzzle m_puzzle;
	private JTextField[][] m_controlGrid;
	
	public CellGrid getCellGrid() {
		return m_cellGrid;
	}
	public void setCellGrid(CellGrid m_cellGrid) {
		this.m_cellGrid = m_cellGrid;
	}
	public Puzzle getPuzzle() {
		return m_puzzle;
	}
	public void setPuzzle(Puzzle m_puzzle) {
		this.m_puzzle = m_puzzle;
	}
	public JTextField[][] getControlGrid() {
		return m_controlGrid;
	}
	public void setControlGrid(JTextField[][] m_controlGrid) {
		this.m_controlGrid = m_controlGrid;
	}
}
