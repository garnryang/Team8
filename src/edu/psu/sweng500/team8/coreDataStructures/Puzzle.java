package edu.psu.sweng500.team8.coreDataStructures;

import edu.psu.sweng500.team8.coreDataStructures.Cell.ValueType;


public class Puzzle {
	public enum DifficultyLevel {
		Easy,
		Medium,
		Hard
	}
	
	private CellGrid m_grid = new CellGrid();
	private DifficultyLevel m_difficulty = DifficultyLevel.Medium;
	private CellGrid m_solution;
	
	public DifficultyLevel getDifficulty() {
		return m_difficulty;
	}
	
	public void setDifficulty(DifficultyLevel difficulty) {
		m_difficulty = difficulty;
	}
	
	public CellGrid getSolution() {
		return  m_solution;
	}
	
	public void setSolution(CellGrid solution) {
		m_solution = solution;
	}
	
	public void setCellNumber(int row, int column, int value) {
		Cell cell = m_grid.getCell(row, column);
		cell.setNumber(value);
		cell.setType(ValueType.Given);
	}
	
	public void clearCell(int row, int column) {
		Cell cell = m_grid.getCell(row, column);
		cell.clearNumber();
		cell.setType(ValueType.UserDefined);
	}
	
	public CellGrid getCopyOfCellGrid() {
		return new CellGrid(m_grid);
	}
}
