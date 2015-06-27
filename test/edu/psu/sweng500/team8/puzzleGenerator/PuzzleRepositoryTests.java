package edu.psu.sweng500.team8.puzzleGenerator;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import edu.psu.sweng500.team8.coreDataStructures.Puzzle;
import edu.psu.sweng500.team8.coreDataStructures.Puzzle.DifficultyLevel;

public class PuzzleRepositoryTests {
	@Test
	public void writePuzzlesToFileCreatesNewFile() {
		try {
			PuzzleRepository.writePuzzlesToFile(1, "writePuzzlesToFileCreatesNewFile.test");
		} catch (IOException e) {
			e.printStackTrace();
			fail("Failed to create puzzles");
		}
		
		File file = new File("writePuzzlesToFileCreatesNewFile.test");
		assertTrue(file.exists());
		
		//Delete the file
		try {
			Files.delete(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			fail("Failed to delete file");
		}
	}
	
	@Test
	public void initializeLoadsPuzzlesFromFile() {
		PuzzleRepository repo = new PuzzleRepository();
		try {
			PuzzleRepository.writePuzzlesToFile(1, "initializeLoadsPuzzlesFromFile.test");
			
			repo.initialize("initializeLoadsPuzzlesFromFile.test");
		} catch (IOException e) {
			e.printStackTrace();
			fail("Failed to create puzzles");
		}
		
		assertNotNull(repo.getPuzzle(DifficultyLevel.Easy));
		assertNotNull(repo.getPuzzle(DifficultyLevel.Medium));
		assertNotNull(repo.getPuzzle(DifficultyLevel.Hard));
		
		//Delete the file
		Path filePath = Paths.get("initializeLoadsPuzzlesFromFile.test");
		try {
			Files.delete(filePath);
		} catch (IOException e) {
			e.printStackTrace();
			fail("Failed to delete file");
		}
	}
	
	@Test
	public void getPuzzleReturnsPuzzleOfSpecifiedDifficulty() {
		PuzzleRepository repo = new PuzzleRepository();
		
		try {
			repo.initialize(); //Assumes default pregenerated file exists
		} catch (IOException e) {
			e.printStackTrace();
			fail("Failed to load puzzles");
		} 
		
		Puzzle puzzle = repo.getPuzzle(DifficultyLevel.Easy);
		assertNotNull(puzzle);
		assertEquals(DifficultyLevel.Easy, puzzle.getDifficulty());
		
		puzzle = repo.getPuzzle(DifficultyLevel.Medium);
		assertNotNull(puzzle);
		assertEquals(DifficultyLevel.Medium, puzzle.getDifficulty());
		
		puzzle = repo.getPuzzle(DifficultyLevel.Hard);
		assertNotNull(puzzle);
		assertEquals(DifficultyLevel.Hard, puzzle.getDifficulty());
	}
	
	@Test
	public void getPuzzleGeneratesNewPuzzlesIfNoPuzzlesAreLeft() {
		PuzzleRepository repo = new PuzzleRepository();
		
		//Repo is empty
		
		//Try getting a puzzle. This should generate new puzzles.
		assertNotNull(repo.getPuzzle(DifficultyLevel.Easy));
	}
	
	@Test
	public void getPuzzleReturnsDifferentPuzzleOnEachCallTime() {
		PuzzleRepository repo = new PuzzleRepository();
		
		try {
			repo.initialize(); //Assumes default pregenerated file exists
		} catch (IOException e) {
			e.printStackTrace();
			fail("Failed to load puzzles");
		} 
		
		Puzzle puzzle = repo.getPuzzle(DifficultyLevel.Easy);
		assertNotEquals(puzzle, repo.getPuzzle(DifficultyLevel.Easy));
	}
	
	@Test
	public void getPuzzleReturnsDifferentPuzzleOnEachLoad() {
		PuzzleRepository repo = new PuzzleRepository();
		
		try {
			repo.initialize(); //Assumes default pregenerated file exists
		} catch (IOException e) {
			e.printStackTrace();
			fail("Failed to load puzzles");
		} 
		
		//Get a puzzle
		Puzzle firstPuzzle = repo.getPuzzle(DifficultyLevel.Easy);
		
		//Clear the repo
		repo.clear();
		
		//Load it again
		try {
			repo.initialize(); //Assumes default pregenerated file exists
		} catch (IOException e) {
			e.printStackTrace();
			fail("Failed to load puzzles");
		} 
		
		//Get another puzzle and verify it's not the same as last time (should be shuffled)
		Puzzle secondPuzzle = repo.getPuzzle(DifficultyLevel.Easy);
		assertFalse(firstPuzzle.getCopyOfCellGrid().valuesAreEqual(secondPuzzle.getCopyOfCellGrid()));
	}
}
