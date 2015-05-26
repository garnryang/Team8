package edu.psu.sweng500.team8.poc.datastructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Row {

	public int rowNumb;
	public Map<Integer, List<Integer>> failedNumb;
	public Row previousRow;
	private Cell[] cells;
	private int cellPosition;
	private final static Integer[] FIXED_NUMBERS = {1, 2, 3, 4, 5, 6, 7, 8, 9};
	private Map<Integer, Boolean> usedNumbers;
	
	public Row(int rowNumb) {
		this.rowNumb = rowNumb;
		failedNumb = new HashMap<Integer, List<Integer>>();
		previousRow = null;
		cells = new Cell[9];
		cellPosition = 0;
		
		usedNumbers = new HashMap<Integer, Boolean>();
		
		for (Integer number : FIXED_NUMBERS) {
			usedNumbers.put(number, Boolean.FALSE);
		}		
	}
	
	public int getSize() {
		return cellPosition+1;
	}
	
	public void addCell(Cell cell) {
		cells[cellPosition++] = cell;
		usedNumbers.put(cell.getValue(), Boolean.TRUE);
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
	
	public Cell[] getCells() {
		return cells;
	}

	public Map<Integer, Boolean> getUsedNumbers() {
		return this.usedNumbers;
	}
	
	public void removeLastCell() {
		cellPosition--;
		int cellToBeRemoved = cells[cellPosition].getValue();
		usedNumbers.put(cellToBeRemoved, Boolean.FALSE);
		cells[cellPosition] = new Cell();
	}
}
