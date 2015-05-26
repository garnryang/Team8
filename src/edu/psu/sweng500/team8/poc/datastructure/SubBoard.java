package edu.psu.sweng500.team8.poc.datastructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubBoard {

	private int rowTrack;
	private int columnTrack;
	private Cell[][] cells;
	private final static Integer[] FIXED_NUMBERS = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	private Map<Integer, Boolean> usedNumbers;

	public SubBoard() {
		
		rowTrack = 0;
		columnTrack = 0;
		
		cells = new Cell[3][3];
		usedNumbers = new HashMap<Integer, Boolean>();

		for (Integer number : FIXED_NUMBERS) {
			usedNumbers.put(number, Boolean.FALSE);
		}
	}

	public int getSize() {
		return 9 - getAvailableNumbers().size();
	}

	
	public void addCell(Cell cell, int row, int column) {

		cells[row][column] = cell;
		usedNumbers.put(cell.getValue(), Boolean.TRUE);
		rowTrack = row;
		columnTrack = column;
	}

	public Map<Integer, Boolean> getUsedNumbers() {
		return this.usedNumbers;
	}
	
	public List<Integer> getAvailableNumbers() {

		List<Integer> unUsedNumbers = new ArrayList<Integer>();

		for (Map.Entry<Integer, Boolean> entry : usedNumbers.entrySet()) {
			if (!Boolean.TRUE.equals(entry.getValue())) {
				unUsedNumbers.add(entry.getKey());
			}
		}

		return unUsedNumbers;
	}
	
	public void removeLastCell() {
		usedNumbers.put(cells[rowTrack][columnTrack].getValue(), Boolean.FALSE);
		cells[rowTrack][columnTrack] = new Cell();
	}
}
