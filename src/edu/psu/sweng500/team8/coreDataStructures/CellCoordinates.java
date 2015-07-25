package edu.psu.sweng500.team8.coreDataStructures;

import java.io.Serializable;

public class CellCoordinates  implements Serializable {
	private static final long serialVersionUID = 1L;
	private int rowIndex;
	private int columnIndex;
	
	public CellCoordinates(int rowIndex, int columnIndex) {
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
	}
	
	public int getRowIndex() {
		return this.rowIndex;
	}
	
	public int getColumnIndex() {
		return this.columnIndex;
	}
	
	public int getBlockIndex() {
		return (this.rowIndex / 3) * 3 + this.columnIndex / 3;
	}
}
