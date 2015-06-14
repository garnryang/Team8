package edu.psu.sweng500.team8.coreDataStructures;

import edu.psu.sweng500.team8.coreDataStructures.Cell.ValueType;


public class Puzzle {
	public enum DifficultyLevel {
		Easy,
		Medium,
		Hard
	}
	
	private DifficultyLevel m_difficulty = DifficultyLevel.Medium;
	private CellGrid m_grid = new CellGrid();
	private CellGrid m_solution;
	
	//DEPRECATED. TODO: Remove
	public Puzzle() {
		
	}
	
	public Puzzle(CellGrid startingGrid, CellGrid solution) {
		m_grid = new CellGrid(startingGrid); //Make a copy so we have full control
		m_solution = solution; //Should be read-only, so copy is not necessary
	}
	
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
