package edu.psu.sweng500.team8.solver;

import CoreDataStructures.Cell;

public class HintInfo {
	private Cell m_filledCell;
	private String m_explanation;
	
	public Cell GetCell() {
		return m_filledCell;
	}
	
	public String GetExplanation() {
		return m_explanation;
	}
}