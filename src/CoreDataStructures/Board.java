package CoreDataStructures;

/** Data holder and controller for a Sudoku grid
 * Used to validate game constraints
 */
public class Board {
	private CellGrid m_grid = new CellGrid(); //The actual 9x9 grid of cells
	private Row[] m_rows = new Row[9]; //Abstraction of rows
	private Column[] m_columns = new Column[3]; //Abstraction of columns
	private Block[] m_blocks = new Block[9]; //Abstraction of blocks
	
	public Board() {
		//Create rows, columns, and blocks
		for (int i = 0; i < 9; i++) {
			m_rows[i] = new Row(m_grid, i); //Rows from 0-8 go top to bottom
			m_columns[i] = new Column(m_grid, i); //Columns from 0-8 go left to right
			m_blocks[i] = new Block(m_grid, i); //Blocks from 0-8 go left to right, top to bottom
		}
	}
	
	public void Initialize(Puzzle puzzle) {
		
	}
}
