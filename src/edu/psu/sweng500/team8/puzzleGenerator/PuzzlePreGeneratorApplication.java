package edu.psu.sweng500.team8.puzzleGenerator;

public class PuzzlePreGeneratorApplication {
	private static int PUZZLES_TO_GENERATE = 1000;
	
	public static void main(String[] args) {
		PuzzleRepository.writePuzzlesToFile(PUZZLES_TO_GENERATE);
		System.out.println("Done");
	}
}
