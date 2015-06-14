package edu.psu.sweng500.team8.coreDataStructures;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Data holder and controller for a Sudoku grid Used to validate game
 * constraints
 */
public class Board {
	private CellGrid m_grid = new CellGrid(); // The actual 9x9 grid of cells
	private Row[] m_rows = new Row[9]; // Abstraction of rows
	private Column[] m_columns = new Column[9]; // Abstraction of columns
	private Block[] m_blocks = new Block[9]; // Abstraction of blocks
	private Puzzle m_currentPuzzle;
	
	public Board() {
		// Create rows, columns, and blocks
		for (int i = 0; i < 9; i++) {
			m_rows[i] = new Row(m_grid, i); // Rows from 0-8 go top to bottom
			m_columns[i] = new Column(m_grid, i); // Columns from 0-8 go left to
													// right
			m_blocks[i] = new Block(m_grid, i); // Blocks from 0-8 go left to
												// right, top to bottom
		}
	}
	
	public Board(Board boardToCopy) {
		m_grid = new CellGrid(boardToCopy.getCellGrid());
		
		// Create rows, columns, and blocks
		for (int i = 0; i < 9; i++) {
			m_rows[i] = new Row(m_grid, i); // Rows from 0-8 go top to bottom
			m_columns[i] = new Column(m_grid, i); // Columns from 0-8 go left to
			// right
			m_blocks[i] = new Block(m_grid, i); // Blocks from 0-8 go left to
			// right, top to bottom
		}
	}

	public CellGrid getCellGrid() {
		return this.m_grid;
	}
	
	public Row getRow(int index) {
		return m_rows[index];
	}
	
	public Column getColumn(int index) {
		return m_columns[index];
	}
	
	public Block getBlock(int index) {
		return m_blocks[index];
	}
	
	public Cell getCell(int rowIndex, int columnIndex) {
		return m_grid.getCell(rowIndex, columnIndex);
	}
	
	public CellConstraints getCellConstraints(Cell cell) {
		CellCoordinates coordinates = cell.getCoordinates();
		
		Row row = m_rows[coordinates.getRowIndex()];
		Column column = m_columns[coordinates.getColumnIndex()];
		Block block = m_blocks[coordinates.getBlockIndex()];
		
		return new CellConstraints(row, column, block);
	}
	
	public boolean hasOpenCells() {
		for (int rowIndex = 0; rowIndex < 9; rowIndex++) {
			for (int columnIndex = 0; columnIndex < 9; columnIndex++) {
				Cell cell = m_grid.getCell(rowIndex, columnIndex);
				
				if (!cell.hasNumber())
					return true;
			}
		}
		
		return false;
	}
	
	public List<Cell> getOpenCells() {
		List<Cell> openCells = new ArrayList<Cell>();
		
		for (int rowIndex = 0; rowIndex < 9; rowIndex++) {
			for (int columnIndex = 0; columnIndex < 9; columnIndex++) {
				Cell cell = m_grid.getCell(rowIndex, columnIndex);
				
				if (!cell.hasNumber())
					openCells.add(cell);			
			}
		}
		
		return openCells;
	}
	
	/* FIXME - This cannot work. new Board starts with cellGrid and
	 * the constructor sets row, column and block
	 * setting a new m_grid from puzzle will not update row, column, and block */
	public void Initialize(Puzzle puzzle) {
		m_grid = puzzle.getCopyOfCellGrid();
		m_currentPuzzle = puzzle;
	}
	
	public Puzzle getCurrentPuzzle() {
		return m_currentPuzzle;
	}
	
	public boolean puzzleIsSolved() {
		//(JN 6/4/15) Easy enough to implement. Leaving it unimplemented for the sake of the test failure report.
		throw new UnsupportedOperationException();
	}
	
	public Set<Cell> getCellsViolatingConstraints() {
		throw new UnsupportedOperationException();
	}
}
