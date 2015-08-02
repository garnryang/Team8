package edu.psu.sweng500.team8.coreDataStructures;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Data holder and controller for a Sudoku grid Used to validate game
 * constraints
 */
public class Board implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private CellGrid grid = new CellGrid(); // The actual 9x9 grid of cells
	private Row[] rows = new Row[9]; // Abstraction of rows
	private Column[] columns = new Column[9]; // Abstraction of columns
	private Block[] blocks = new Block[9]; // Abstraction of blocks
	private Puzzle currentPuzzle;

	public Board() {
		// Create rows, columns, and blocks
		for (int i = 0; i < 9; i++) {
			this.rows[i] = new Row(this.grid, i); // Rows from 0-8 go top to bottom
			this.columns[i] = new Column(this.grid, i); // Columns from 0-8 go left
														// to
			// right
			this.blocks[i] = new Block(this.grid, i); // Blocks from 0-8 go left to
			// right, top to bottom
		}
	}

	public Board(Board boardToCopy) {
		this.grid = new CellGrid(boardToCopy.getCellGrid());
		this.currentPuzzle = boardToCopy.getCurrentPuzzle();

		// Create rows, columns, and blocks
		for (int i = 0; i < 9; i++) {
			this.rows[i] = new Row(this.grid, i); // Rows from 0-8 go top to bottom
			this.columns[i] = new Column(this.grid, i); // Columns from 0-8 go left
														// to
			// right
			this.blocks[i] = new Block(this.grid, i); // Blocks from 0-8 go left to
			// right, top to bottom
		}
	}

	public CellGrid getCellGrid() {
		return this.grid;
	}

	public Row getRow(int index) {
		return this.rows[index];
	}

	public Column getColumn(int index) {
		return this.columns[index];
	}

	public Block getBlock(int index) {
		return this.blocks[index];
	}

	public Cell getCell(int rowIndex, int columnIndex) {
		return this.grid.getCell(rowIndex, columnIndex);
	}

	public CellConstraints getCellConstraints(Cell cell) {
		CellCoordinates coordinates = cell.getCoordinates();

		Row row = this.rows[coordinates.getRowIndex()];
		Column column = this.columns[coordinates.getColumnIndex()];
		Block block = this.blocks[coordinates.getBlockIndex()];

		return new CellConstraints(row, column, block);
	}

	public boolean hasOpenCells() {
		for (int rowIndex = 0; rowIndex < 9; rowIndex++) {
			for (int columnIndex = 0; columnIndex < 9; columnIndex++) {
				Cell cell = this.grid.getCell(rowIndex, columnIndex);

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
				Cell cell = this.grid.getCell(rowIndex, columnIndex);

				if (!cell.hasNumber())
					openCells.add(cell);
			}
		}

		return openCells;
	}

	public void initialize(Puzzle puzzle) {
		// Copy the cells without swapping the cell grid
		this.grid.copyValues(puzzle.getCopyOfCellGrid());
		this.currentPuzzle = puzzle;
	}
	
	public void initialize(Puzzle puzzle, CellGrid overloadCellGrid) {
		this.grid.copyValues(overloadCellGrid);
		this.currentPuzzle = puzzle;
	}

	public Puzzle getCurrentPuzzle() {
		return this.currentPuzzle;
	}

	public boolean puzzleIsSolved() {
		return this.grid.valuesAreEqual(this.currentPuzzle.getSolution());
	}

	public Set<Cell> getCellsViolatingConstraints() {
		// Check each constraint for duplicates
		Set<Cell> duplicateCells = new HashSet<Cell>();
		for (Row row : this.rows)
			duplicateCells.addAll(getCellsViolatingConstraint(row));

		for (Column column : this.columns)
			duplicateCells.addAll(getCellsViolatingConstraint(column));

		for (Block block : this.blocks)
			duplicateCells.addAll(getCellsViolatingConstraint(block));

		return duplicateCells;
	}

	public Set<Cell> getIncorrectCells() {
		// Get the cells that do not match the solution
		Set<Cell> incorrectCells = new HashSet<Cell>();
		CellGrid solutionGrid = this.currentPuzzle.getSolution();
		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				Cell localCell = getCell(row, column);
				if (!localCell.hasNumber())
					continue; // Skip empty cells

				Cell solutionCell = solutionGrid.getCell(row, column);
				if (localCell.getNumber() != solutionCell.getNumber())
					incorrectCells.add(localCell);
			}
		}

		return incorrectCells;
	}

	private Set<Cell> getCellsViolatingConstraint(Constraint constraint) {

		// Build a map of the numbers with cells containing that number
		HashMap<Integer, Set<Cell>> numbersToCells = new HashMap<Integer, Set<Cell>>(
				9);
		List<Cell> cells = constraint.getCells();
		for (Cell cell : cells) {
			int cellNumber = cell.getNumber();
			Set<Cell> cellsWithSameNumber = numbersToCells.get(cellNumber);

			if (cellsWithSameNumber == null) {
				cellsWithSameNumber = new HashSet<Cell>(2); // most likely only
															// need 1-2 numbers
				numbersToCells.put(cellNumber, cellsWithSameNumber);
			}
			cellsWithSameNumber.add(cell);
		}

		// Combine the cells with more than one entry
		Set<Cell> cellsWithDuplicates = new HashSet<Cell>();
		for (Set<Cell> cellSet : numbersToCells.values()) {
			if (cellSet.size() > 1)
				cellsWithDuplicates.addAll(cellSet);
		}

		return cellsWithDuplicates;
	}
}
