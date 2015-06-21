package edu.psu.sweng500.team8.coreDataStructures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/** Represents the constraints for a cell (its row/column/block)
 */
public class CellConstraints {
	private Row m_row;
	private Column m_column;
	private Block m_block;

	public CellConstraints(Row row, Column column, Block block) {
		m_row = row;
		m_column = column;
		m_block = block;
	}

	public Row getRow() {
		return m_row;
	}

	public Column getColumn() {
		return m_column;
	}

	public Block getBlock() {
		return m_block;
	}

	public Set<Integer> getAvailableNumbers() {
		//Get the numbers that are available in all 3 constraints
		Set<Integer> numberSet = m_row.getAvailableNumbers();
		numberSet.retainAll(m_column.getAvailableNumbers());
		numberSet.retainAll(m_block.getAvailableNumbers());
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
		private CellConstraints m_constraints;
		private int m_index = 0;

		public ConstraintIterator(CellConstraints constraints) {
			m_constraints = constraints;
		}

		public boolean hasNext() {
			return m_index < 2;
		}

		public Constraint next() {
			Constraint next = null;
			switch (m_index)
			{
			case 0:
				next = m_constraints.getRow();
				break;
			case 1:
				next = m_constraints.getColumn();
				break;
			case 2:
				next = m_constraints.getBlock();
				break;
			default:
				throw new NoSuchElementException();
			}

			m_index++;
			return next;
		}

		/*@Override
		public void remove() {
			// TODO Auto-generated method stub
			// FIXME - why was this not added? (JN) Because it's not required by the interface, nor do we need it.
		}*/

	}
}
