package edu.psu.sweng500.team8.coreDataStructures;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import edu.psu.sweng500.team8.io.BinaryInputStream;
import edu.psu.sweng500.team8.io.BinaryOutputStream;
import edu.psu.sweng500.team8.io.BinarySerializable;

/**
 * Represents a cell (i.e. square) within the 9x9 Sudoku board Contains the
 * cell's type and current value
 */
public class Cell implements BinarySerializable {
	public enum ValueType {
		Given, UserDefined
	}

	private static final long serialVersionUID = 1L;
	private CellCoordinates coordinates;
	private int currentValue = 0;
	private ValueType type = ValueType.UserDefined;
	private Set<Integer> pencilMarks = new HashSet<Integer>();

	public Cell() {
		this(0, 0); // Some default coordinates
	}

	public Cell(int rowIndex, int columnIndex) {
		this.coordinates = new CellCoordinates(rowIndex, columnIndex);
	}

	public Cell(Cell cellToCopy) {
		this.coordinates = new CellCoordinates(
				cellToCopy.coordinates.getRowIndex(),
				cellToCopy.coordinates.getColumnIndex());
		this.currentValue = cellToCopy.currentValue;
		this.type = cellToCopy.type;
		this.pencilMarks.addAll(cellToCopy.pencilMarks);
	}

	public CellCoordinates getCoordinates() {
		return this.coordinates;
	}

	public int getNumber() {
		return this.currentValue;
	}

	public void setNumber(int value) throws IllegalArgumentException {
		if (value < 1 || value > 9)
			throw new IllegalArgumentException(
					"Value must be a digit from 1 to 9. To clear, use the clearNumber function.");

		this.currentValue = value;
	}

	public ValueType getType() {
		return this.type;
	}

	public void setType(ValueType type) {
		this.type = type;
	}

	public boolean hasNumber() {
		return this.currentValue != 0;
	}

	public void clearNumber() {
		this.currentValue = 0;
	}

	public Set<Integer> getPencilMarks() {
		return this.pencilMarks;
	}

	/** Note: assumes row/column indices have been initialized */
	@Override
	public void save(BinaryOutputStream stream) throws IOException {
		/*
		 * To save disk space, instead of writing the value type, save given
		 * numbers as negative. Can change this later if needed.
		 */
		int number = (this.type == ValueType.UserDefined) ? this.currentValue
				: -this.currentValue;
		stream.writeInt(number);
	}

	@Override
	public void load(BinaryInputStream stream) throws IOException {
		int number = stream.readInt();

		/*
		 * To save disk space, instead of writing the value type, given numbers
		 * are saved as negative values. Can change this later if needed.
		 */
		if (number > 0) {
			setNumber(number);
			this.type = ValueType.UserDefined;
		} else if (number < 0) {
			setNumber(-number);
			this.type = ValueType.Given;
		}
	}
}