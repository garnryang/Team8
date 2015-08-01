package edu.psu.sweng500.team8.gui;

import org.junit.Assert;
import org.junit.Test;

import edu.psu.sweng500.team8.coreDataStructures.Cell;
import edu.psu.sweng500.team8.coreDataStructures.Puzzle;
import edu.psu.sweng500.team8.coreDataStructures.Puzzle.DifficultyLevel;
import edu.psu.sweng500.team8.play.GameSession;
import edu.psu.sweng500.team8.puzzleGenerator.PuzzleRepository;

public class NumberButtonGUITest {

	@Test
	public void testUpdateForFocusedCell_normalMode_overrideExistingNumber() {
	
		DifficultyLevel difficulty = DifficultyLevel.Easy;
		PuzzleRepository puzzleRepo = new PuzzleRepository();
		Puzzle puzzle = puzzleRepo.getPuzzle(difficulty);
		GameSession gameSession = new GameSession(puzzle, null);

		gameSession.setPencilMarkMode(false);
		
		NumberButtonGUI numberButtonGUI = new NumberButtonGUI();
		numberButtonGUI.init(null, gameSession);
		
		Cell cell = new Cell(1, 1);
		cell.setNumber(6);
		numberButtonGUI.updateForFocusedCell(cell);

		Assert.assertTrue(numberButtonGUI.getButtons()[5].isSelected());
		
		Assert.assertFalse(numberButtonGUI.getButtons()[0].isSelected());
		Assert.assertFalse(numberButtonGUI.getButtons()[1].isSelected());
		Assert.assertFalse(numberButtonGUI.getButtons()[2].isSelected());
		Assert.assertFalse(numberButtonGUI.getButtons()[3].isSelected());
		Assert.assertFalse(numberButtonGUI.getButtons()[4].isSelected());
		Assert.assertFalse(numberButtonGUI.getButtons()[6].isSelected());
		Assert.assertFalse(numberButtonGUI.getButtons()[7].isSelected());
		Assert.assertFalse(numberButtonGUI.getButtons()[8].isSelected());
		
		//==
		
		cell = new Cell(0, 0);
		cell.getPencilMarks().add(7);
		numberButtonGUI.updateForFocusedCell(cell);
		
		Assert.assertFalse(numberButtonGUI.getButtons()[0].isSelected());
		Assert.assertFalse(numberButtonGUI.getButtons()[1].isSelected());
		Assert.assertFalse(numberButtonGUI.getButtons()[2].isSelected());
		Assert.assertFalse(numberButtonGUI.getButtons()[3].isSelected());
		Assert.assertFalse(numberButtonGUI.getButtons()[4].isSelected());
		Assert.assertFalse(numberButtonGUI.getButtons()[5].isSelected());
		Assert.assertFalse(numberButtonGUI.getButtons()[6].isSelected());
		Assert.assertFalse(numberButtonGUI.getButtons()[7].isSelected());
		Assert.assertFalse(numberButtonGUI.getButtons()[8].isSelected());
		
		//==
		
		cell = new Cell(2, 2);
		numberButtonGUI.updateForFocusedCell(cell);
		
		Assert.assertFalse(numberButtonGUI.getButtons()[0].isSelected());
		Assert.assertFalse(numberButtonGUI.getButtons()[1].isSelected());
		Assert.assertFalse(numberButtonGUI.getButtons()[2].isSelected());
		Assert.assertFalse(numberButtonGUI.getButtons()[3].isSelected());
		Assert.assertFalse(numberButtonGUI.getButtons()[4].isSelected());
		Assert.assertFalse(numberButtonGUI.getButtons()[5].isSelected());
		Assert.assertFalse(numberButtonGUI.getButtons()[6].isSelected());
		Assert.assertFalse(numberButtonGUI.getButtons()[7].isSelected());
		Assert.assertFalse(numberButtonGUI.getButtons()[8].isSelected());

	}
	
	@Test
	public void testUpdateForFocusedCell_pencilMarkMode() {
		
		DifficultyLevel difficulty = DifficultyLevel.Easy;
		PuzzleRepository puzzleRepo = new PuzzleRepository();
		Puzzle puzzle = puzzleRepo.getPuzzle(difficulty);
		GameSession gameSession = new GameSession(puzzle, null);

		gameSession.setPencilMarkMode(true);
		
		NumberButtonGUI numberButtonGUI = new NumberButtonGUI();
		numberButtonGUI.init(null, gameSession);
		
		Cell cell = new Cell(0, 0);
		cell.getPencilMarks().add(7);
		numberButtonGUI.updateForFocusedCell(cell);
		
		Assert.assertTrue(numberButtonGUI.getButtons()[6].isSelected());
		
		Assert.assertFalse(numberButtonGUI.getButtons()[0].isSelected());
		Assert.assertFalse(numberButtonGUI.getButtons()[1].isSelected());
		Assert.assertFalse(numberButtonGUI.getButtons()[2].isSelected());
		Assert.assertFalse(numberButtonGUI.getButtons()[3].isSelected());
		Assert.assertFalse(numberButtonGUI.getButtons()[4].isSelected());
		Assert.assertFalse(numberButtonGUI.getButtons()[5].isSelected());
		Assert.assertFalse(numberButtonGUI.getButtons()[7].isSelected());
		Assert.assertFalse(numberButtonGUI.getButtons()[8].isSelected());
		
		
		cell = new Cell(1, 1);
		cell.setNumber(6);
		numberButtonGUI.updateForFocusedCell(cell);
		
		Assert.assertFalse(numberButtonGUI.getButtons()[0].isSelected());
		Assert.assertFalse(numberButtonGUI.getButtons()[1].isSelected());
		Assert.assertFalse(numberButtonGUI.getButtons()[2].isSelected());
		Assert.assertFalse(numberButtonGUI.getButtons()[3].isSelected());
		Assert.assertFalse(numberButtonGUI.getButtons()[4].isSelected());
		Assert.assertFalse(numberButtonGUI.getButtons()[5].isSelected());
		Assert.assertFalse(numberButtonGUI.getButtons()[6].isSelected());
		Assert.assertFalse(numberButtonGUI.getButtons()[7].isSelected());
		Assert.assertFalse(numberButtonGUI.getButtons()[8].isSelected());
	}
}
