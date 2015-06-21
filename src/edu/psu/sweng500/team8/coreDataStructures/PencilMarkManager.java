package edu.psu.sweng500.team8.coreDataStructures;

public class PencilMarkManager {
	Board m_board;
	
	public PencilMarkManager(Board board) {
		m_board = board;
	}
	
	public void addPencilMark(int number, Cell cell) {	
		cell.getPencilMarks().add(number);
	}
	
	public void removePencilMark(int number, Cell cell) {
		cell.getPencilMarks().remove(number);
	}
}
