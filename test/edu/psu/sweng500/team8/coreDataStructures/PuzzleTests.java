package edu.psu.sweng500.team8.coreDataStructures;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import edu.psu.sweng500.team8.coreDataStructures.Puzzle.DifficultyLevel;
import edu.psu.sweng500.team8.io.BinaryInputStream;
import edu.psu.sweng500.team8.io.BinaryOutputStream;

public class PuzzleTests {
	@Test
	public void canSaveAndLoadFromFile() {
		Puzzle puzzleToSave = TestPuzzles.getEasyPuzzle();
		Puzzle loadedPuzzle = new Puzzle();
		
		try {
			//Save it
			BinaryOutputStream writeStream = new BinaryOutputStream("test.txt");
			puzzleToSave.save(writeStream);
			writeStream.close();
			
			//Load it
			BinaryInputStream readStream = new BinaryInputStream("test.txt");
			loadedPuzzle.load(readStream);
			readStream.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			fail("File exception thrown");
		}
		
		//Check the contents
		assertEquals(DifficultyLevel.Easy, loadedPuzzle.getDifficulty());
		CellGrid savedGrid = puzzleToSave.getCopyOfCellGrid();
		CellGrid loadedGrid = loadedPuzzle.getCopyOfCellGrid();
		//Compare the cells
		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				Cell savedCell = savedGrid.getCell(row, column);
				Cell loadedCell = loadedGrid.getCell(row, column);

				//Check the value
				assertEquals(savedCell.getNumber(), loadedCell.getNumber());

				//Check the type
				assertEquals(savedCell.getType(), loadedCell.getType());
			}
		}
		
		//Check the solution values. Don't care so much about the type.
		assertTrue(puzzleToSave.getSolution().valuesAreEqual(loadedPuzzle.getSolution()));
		
		Path filePath = Paths.get("test.txt");
		try {
			Files.delete(filePath);
		} catch (IOException e) {
			e.printStackTrace();
			fail("Failed to delete file");
		}
	}
}
