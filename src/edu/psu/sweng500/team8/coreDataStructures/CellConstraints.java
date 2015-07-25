package edu.psu.sweng500.team8.coreDataStructures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/** Represents the constraints for a cell (its row/column/block)
 */
public class CellConstraints {
	private Row row;
	private Column column;
	private Block block;

	public CellConstraints(Row row, Column column, Block block) {
		this.row = row;
		this.column = column;
		this.block = block;
	}

	public Row getRow() {
		return this.row;
	}

	public Column getColumn() {
		return this.column;
	}

	public Block getBlock() {
		return this.block;
	}

	public Set<Integer> getAvailableNumbers() {
		//Get the numbers that are available in all 3 constraints
		Set<Integer> numberSet = this.row.getAvailableNumbers();
		numberSet.retainAll(this.column.getAvailableNumbers());
		numberSet.retainAll(this.block.getAvailableNumbers());
		return numberSet;
	}

	public final Set<Integer> getUsedNumbers() {
		Set<Integer> usedNumbers = Helpers.getValidNumberSet();
		usedNumbers.removeAll(getAvailableNumbers());
		return usedNumbers;
	}
	
	public Iterator<Constraint> getIterator() {
		return new ConstraintIterator(this);
	}

	private class ConstraintIterator implements Iterator<Constraint> {
		private CellConstraints constraints;
		private int index = 0;

		public ConstraintIterator(CellConstraints constraints) {
			this.constraints = constraints;
		}

		public boolean hasNext() {
			return this.index < 2;
		}

		public Constraint next() {
			Constraint next = null;
			switch (this.index)
			{
			case 0:
				next = this.constraints.getRow();
				break;
			case 1:
				next = this.constraints.getColumn();
				break;
			case 2:
				next = this.constraints.getBlock();
				break;
			default:
				throw new NoSuchElementException();
			}

			this.index++;
			return next;
		}

		/**
		 * We do not have to implement as we don't need it
		 * but we still need to have this added since Iterator interface requires it
		 */
		public void remove() {
			// Auto-generated method stub
			// why was this not added? (JN) Because it's not required by the interface, nor do we need it.
			// Iterator interface requires remote method to be implemented whehter we need it or not
		}
	}
}
