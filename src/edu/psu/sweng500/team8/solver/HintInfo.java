package edu.psu.sweng500.team8.solver;

import edu.psu.sweng500.team8.coreDataStructures.Cell;

public class HintInfo {
	private Cell m_filledCell;
	private String m_explanation;
	
	public HintInfo(Cell filledCell, String explanation) {
		m_filledCell = filledCell;
		m_explanation = explanation;
	}
	
	public Cell GetCell() {
		return m_filledCell;
	}
	
	public String GetExplanation() {
		return m_explanation;
	}
}
