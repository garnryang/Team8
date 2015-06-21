package edu.psu.sweng500.team8.coreDataStructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
	
	public void Initialize(Puzzle puzzle) {
		//Copy the cells without swapping the cell grid
		m_grid.copyValues(puzzle.getCopyOfCellGrid());
		m_currentPuzzle = puzzle;
	}
	
	public Puzzle getCurrentPuzzle() {
		return m_currentPuzzle;
	}
	
	public boolean puzzleIsSolved() {
		return m_grid.valuesAreEqual(m_currentPuzzle.getSolution());
	}
	
	public Set<Cell> getCellsViolatingConstraints() {
		//Check each constraint for duplicates
		Set<Cell> duplicateCells = new HashSet<Cell>();
		for (Row row : m_rows)
			duplicateCells.addAll(getCellsViolatingConstraint(row));
		
		for (Column column : m_columns)
			duplicateCells.addAll(getCellsViolatingConstraint(column));
		
		for (Block block : m_blocks)
			duplicateCells.addAll(getCellsViolatingConstraint(block));
		
		return duplicateCells;
	}
	
	private Set<Cell> getCellsViolatingConstraint(Constraint constraint) {
		
		//Build a map of the numbers with cells containing that number
		HashMap<Integer, Set<Cell> > numbersToCells = new HashMap<Integer, Set<Cell> >(9);
		List<Cell> cells = constraint.getCells();
		for (Cell cell : cells) {
			int cellNumber = cell.getNumber();
			
			/**
			 * FIXME - This is JAVA 8 functionality and doesn't exist on Java 7
			 * If default value should be null, get(cellNumber) will yeild the same result
			 */
			//Set<Cell> cellsWithSameNumber = numbersToCells.getOrDefault(cellNumber, null);
			Set<Cell> cellsWithSameNumber = numbersToCells.get(cellNumber);
			
			
			
			if (cellsWithSameNumber == null)
			{
				cellsWithSameNumber = new HashSet<Cell>(2); //most likely only need 1-2 numbers
				numbersToCells.put(cellNumber, cellsWithSameNumber);
			}
			cellsWithSameNumber.add(cell);
		}
		
		//Combine the cells with more than one entry
		Set<Cell> cellsWithDuplicates = new HashSet<Cell>();
		for (Set<Cell> cellSet : numbersToCells.values()) {
			if (cellSet.size() > 1)
				cellsWithDuplicates.addAll(cellSet);
		}
		
		return cellsWithDuplicates;
	}
}
