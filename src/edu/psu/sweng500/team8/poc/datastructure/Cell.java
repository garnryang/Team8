package edu.psu.sweng500.team8.poc.datastructure;

import java.util.ArrayList;
import java.util.List;


public class Cell {

	public Cell previousCell;
	int value;
	
	private List<Integer> previouslyFailed;
	
	
	public Cell() {
		previousCell = null;
		value = 0;
		previouslyFailed = new ArrayList<Integer>();
	}
	
	public List<Integer> getPreviousFailed() {
		return this.previouslyFailed;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
}
