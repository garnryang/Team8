package edu.psu.sweng500.team8.coreDataStructures;

import java.io.IOException;

import edu.psu.sweng500.team8.coreDataStructures.Cell.ValueType;
import edu.psu.sweng500.team8.io.BinaryInputStream;
import edu.psu.sweng500.team8.io.BinaryOutputStream;
import edu.psu.sweng500.team8.io.BinarySerializable;

public class Puzzle implements BinarySerializable {
	public enum DifficultyLevel {
		Easy, //0
		Medium, //1
		Hard //2
	}
	
	private DifficultyLevel m_difficulty = DifficultyLevel.Medium;
	private CellGrid m_grid = new CellGrid();
	private CellGrid m_solution;
	
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

	@Override
	public void save(BinaryOutputStream stream) throws IOException {
		stream.writeInt(toInt(m_difficulty));
		m_grid.save(stream);
		m_solution.save(stream);
	}

	@Override
	public void load(BinaryInputStream stream) throws IOException {
		m_difficulty = toDifficultyLevel(stream.readInt());
		m_grid.load(stream);
		m_solution = new CellGrid();
		m_solution.load(stream);
	}	
	
	private static int toInt(DifficultyLevel level) {
		switch (level) {
		case Easy:
			return 0;
		case Medium:
			return 1;
		case Hard:
			return 2;
		}
		return 0; //Unreachable
	}
	
	private static DifficultyLevel toDifficultyLevel(int level) {
		switch (level) {
		case 0:
			return DifficultyLevel.Easy;
		case 1:
			return DifficultyLevel.Medium;
		case 2:
			return DifficultyLevel.Hard;
		}
		
		throw new IllegalArgumentException();
	}
}
