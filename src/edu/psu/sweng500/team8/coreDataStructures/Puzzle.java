package edu.psu.sweng500.team8.coreDataStructures;

import java.io.IOException;

import edu.psu.sweng500.team8.coreDataStructures.Cell.ValueType;
import edu.psu.sweng500.team8.io.BinaryInputStream;
import edu.psu.sweng500.team8.io.BinaryOutputStream;
import edu.psu.sweng500.team8.io.BinarySerializable;

public class Puzzle implements BinarySerializable {
	public enum DifficultyLevel {
		Easy, // 0
		Medium, // 1
		Hard // 2
	}

	private DifficultyLevel difficulty = DifficultyLevel.Medium;
	private CellGrid grid = new CellGrid();
	private CellGrid solution;

	public Puzzle() {

	}

	public Puzzle(CellGrid startingGrid, CellGrid solution) {
		this.grid = new CellGrid(startingGrid); // Make a copy so we have full
												// control
		this.solution = solution; // Should be read-only, so copy is not necessary
	}

	public DifficultyLevel getDifficulty() {
		return this.difficulty;
	}

	public void setDifficulty(DifficultyLevel difficulty) {
		this.difficulty = difficulty;
	}

	public CellGrid getSolution() {
		return this.solution;
	}

	public void setSolution(CellGrid solution) {
		this.solution = solution;
	}

	public void setCellNumber(int row, int column, int value) {
		Cell cell = this.grid.getCell(row, column);
		cell.setNumber(value);
		cell.setType(ValueType.Given);
	}

	public void clearCell(int row, int column) {
		Cell cell = this.grid.getCell(row, column);
		cell.clearNumber();
		cell.setType(ValueType.UserDefined);
	}

	public CellGrid getCopyOfCellGrid() {
		return new CellGrid(this.grid);
	}

	@Override
	public void save(BinaryOutputStream stream) throws IOException {
		stream.writeInt(toInt(this.difficulty));
		this.grid.save(stream);
		this.solution.save(stream);
	}

	@Override
	public void load(BinaryInputStream stream) throws IOException {
		this.difficulty = toDifficultyLevel(stream.readInt());
		this.grid.load(stream);
		this.solution = new CellGrid();
		this.solution.load(stream);
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
		return 0; // Unreachable
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
