package edu.psu.sweng500.team8.puzzleGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import edu.psu.sweng500.team8.coreDataStructures.Puzzle;
import edu.psu.sweng500.team8.coreDataStructures.Puzzle.DifficultyLevel;
import edu.psu.sweng500.team8.io.BinaryInputStream;
import edu.psu.sweng500.team8.io.BinaryOutputStream;

public class PuzzleRepository {
	private static String PUZZLE_PATH = "Puzzles/pregenerated.pzls";
	private Hashtable<DifficultyLevel, List<Puzzle>> puzzlesIndexedByDifficulty = 
			new Hashtable<DifficultyLevel, List<Puzzle>>(3);

	public PuzzleRepository() {
		//Initialize lists for each difficulty 
		this.puzzlesIndexedByDifficulty.put(DifficultyLevel.Easy, new ArrayList<Puzzle>());
		this.puzzlesIndexedByDifficulty.put(DifficultyLevel.Medium, new ArrayList<Puzzle>());
		this.puzzlesIndexedByDifficulty.put(DifficultyLevel.Hard, new ArrayList<Puzzle>());
	}

	public static void writePuzzlesToFile(int numPuzzlesPerDifficulty) throws IOException {
		writePuzzlesToFile(numPuzzlesPerDifficulty, PUZZLE_PATH);
	}

	public static void writePuzzlesToFile(int numPuzzlesPerDifficulty, String filePath) throws IOException {
		//Generate puzzles for each difficulty
		List<Puzzle> puzzles = generatePuzzles(numPuzzlesPerDifficulty, DifficultyLevel.Easy);
		puzzles.addAll(generatePuzzles(numPuzzlesPerDifficulty, DifficultyLevel.Medium));
		puzzles.addAll(generatePuzzles(numPuzzlesPerDifficulty, DifficultyLevel.Hard));

		//Serialize each puzzle
		BinaryOutputStream writeStream = new BinaryOutputStream(filePath);
		writeStream.writeInt(puzzles.size());
		for (Puzzle puzzle : puzzles) {
			puzzle.save(writeStream);	
		}
		writeStream.close();
	}

	public void initialize() throws IOException {		
		initialize(PUZZLE_PATH);
	}

	public void initialize(String filePath) throws IOException {
		//Read puzzles from the file
		BinaryInputStream readStream = new BinaryInputStream(filePath);
		int numPuzzles = readStream.readInt();
		for (int i = 0; i < numPuzzles; i++) {
			readPuzzle(readStream);
		}
		readStream.close();

		shufflePuzzles();
	}

	public Puzzle getPuzzle(DifficultyLevel difficulty) {
		//Get the next queued puzzle for the difficulty
		List<Puzzle> puzzles = this.puzzlesIndexedByDifficulty.get(difficulty);
		if (puzzles.isEmpty())
			puzzles.addAll(generatePuzzles(10, difficulty)); //Generate a few more puzzles. Hopefully this won't ever be hit.

		return puzzles.remove(0);
	}

	public void clear() {
		this.puzzlesIndexedByDifficulty.get(DifficultyLevel.Easy).clear();
		this.puzzlesIndexedByDifficulty.get(DifficultyLevel.Medium).clear();
		this.puzzlesIndexedByDifficulty.get(DifficultyLevel.Hard).clear();
	}
	
	private void readPuzzle(BinaryInputStream readStream) throws IOException {
		Puzzle puzzle = new Puzzle();
		puzzle.load(readStream);

		//Add it to the repo
		this.puzzlesIndexedByDifficulty.get(puzzle.getDifficulty()).add(puzzle);
	}

	private void shufflePuzzles() {
		Collections.shuffle(this.puzzlesIndexedByDifficulty.get(DifficultyLevel.Easy));
		Collections.shuffle(this.puzzlesIndexedByDifficulty.get(DifficultyLevel.Medium));
		Collections.shuffle(this.puzzlesIndexedByDifficulty.get(DifficultyLevel.Hard));
	}

	private static List<Puzzle> generatePuzzles(int numPuzzles, DifficultyLevel difficulty) {
		List<Puzzle> puzzles = new ArrayList<Puzzle>(numPuzzles);

		for (int i = 0; i < numPuzzles; i++) {
			System.out.println("Generating puzzle: " + difficulty + " " + (i + 1) + " of " + numPuzzles);
			puzzles.add(PuzzleGenerator.makePuzzle(difficulty));
		}

		return puzzles;
	}
}
