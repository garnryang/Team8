package CoreDataStructures;

public class Puzzle {
	public enum DifficultyLevel {
		Easy,
		Medium,
		Hard
	}
	
	private CellGrid m_grid = new CellGrid();
	private DifficultyLevel m_difficulty = DifficultyLevel.Medium;
	private CellGrid m_solution;
	
	public DifficultyLevel getDifficulty() {
		return m_difficulty;
	}
}
