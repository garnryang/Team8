package CoreDataStructures;

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
}
