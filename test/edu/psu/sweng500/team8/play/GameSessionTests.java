package edu.psu.sweng500.team8.play;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

import edu.psu.sweng500.team8.coreDataStructures.Board;
import edu.psu.sweng500.team8.coreDataStructures.Cell;
import edu.psu.sweng500.team8.coreDataStructures.Puzzle;

public class GameSessionTests {

	@Test
	public void testUndo_1_Action() {
		
		
		Puzzle puzzle = new Puzzle();
		GameSession gameSession = new GameSession(puzzle, null);
		
		Cell cell = gameSession.getGameBoard().getCell(0, 0);
		gameSession.enterNumber(cell, 8);
		Board boardAfterEnteringNumber = gameSession.refresh();
		Cell cellAfterEnteringNumber = boardAfterEnteringNumber.getCell(0, 0);
		Assert.assertEquals(8, cellAfterEnteringNumber.getNumber());	
		
		gameSession.doUndo();
		Board boardAfterUndo = gameSession.refresh();
		Cell cellAfterUndo = boardAfterUndo.getCell(0, 0);
		Assert.assertEquals(0, cellAfterUndo.getNumber());
	
	}
	
	@Test
	public void testUndo_0_Action() {
		
		Puzzle puzzle = new Puzzle();
		GameSession gameSession = new GameSession(puzzle, null);
		
		gameSession.doUndo();
		Board boardAfterUndo = gameSession.refresh();
		Cell cellAfterUndo = boardAfterUndo.getCell(0, 0);
		Assert.assertEquals(0, cellAfterUndo.getNumber());
	}
	
	@Test
	public void testUndo_2_Action() {
		
		Puzzle puzzle = new Puzzle();
		GameSession gameSession = new GameSession(puzzle, null);
		
		Cell cell_1 = gameSession.getGameBoard().getCell(0, 0);
		gameSession.enterNumber(cell_1, 8);
		Board boardAfterEnteringNumber = gameSession.refresh();
		Cell cellAfterEnteringNumber = boardAfterEnteringNumber.getCell(0, 0);
		Assert.assertEquals(8, cellAfterEnteringNumber.getNumber());
		
		Cell cell_2 = gameSession.getGameBoard().getCell(1, 1);
		gameSession.enterNumber(cell_2, 5);
		boardAfterEnteringNumber = gameSession.refresh();
		cellAfterEnteringNumber = boardAfterEnteringNumber.getCell(1, 1);
		Assert.assertEquals(5, cellAfterEnteringNumber.getNumber());
		
		gameSession.doUndo();
		Board boardAfterUndo = gameSession.refresh();
		Cell cellAfterUndo = boardAfterUndo.getCell(0, 0);
		Assert.assertEquals(8, cellAfterUndo.getNumber());
		cellAfterUndo = boardAfterUndo.getCell(1, 1);
		Assert.assertEquals(0, cellAfterUndo.getNumber());
		
		gameSession.doUndo();
		boardAfterUndo = gameSession.refresh();
		cellAfterUndo = boardAfterUndo.getCell(0, 0);
		Assert.assertEquals(0, cellAfterUndo.getNumber());
		cellAfterUndo = boardAfterUndo.getCell(1, 1);
		Assert.assertEquals(0, cellAfterUndo.getNumber());
	}
	
	@Test
	public void testRedo_1_Action() {
		
		Puzzle puzzle = new Puzzle();
		GameSession gameSession = new GameSession(puzzle, null);
		
		Cell cell = gameSession.getGameBoard().getCell(0, 0);
		gameSession.enterNumber(cell, 8);
		Board boardAfterEnteringNumber = gameSession.refresh();
		Cell cellAfterEnteringNumber = boardAfterEnteringNumber.getCell(0, 0);
		Assert.assertEquals(8, cellAfterEnteringNumber.getNumber());	
		
		gameSession.doUndo();
		Board boardAfterUndo = gameSession.refresh();
		Cell cellAfterUndo = boardAfterUndo.getCell(0, 0);
		Assert.assertEquals(0, cellAfterUndo.getNumber());
		
		gameSession.doRedo();
		Board boardAfterRedo = gameSession.refresh();
		Cell cellAfterRedo = boardAfterRedo.getCell(0, 0);
		Assert.assertEquals(8, cellAfterRedo.getNumber());
		
	}
	
	@Test
	public void testRedo_0_Action() {
		
		Puzzle puzzle = new Puzzle();
		GameSession gameSession = new GameSession(puzzle, null);
		
		Cell cell = gameSession.getGameBoard().getCell(0, 0);
		gameSession.enterNumber(cell, 8);
		Board boardAfterEnteringNumber = gameSession.refresh();
		Cell cellAfterEnteringNumber = boardAfterEnteringNumber.getCell(0, 0);
		Assert.assertEquals(8, cellAfterEnteringNumber.getNumber());	
		
		gameSession.doRedo();
		Board boardAfterRedo = gameSession.refresh();
		Cell cellAfterRedo = boardAfterRedo.getCell(0, 0);
		Assert.assertEquals(8, cellAfterRedo.getNumber());
		
	}
	
	@Test
	public void testRedo_2_Action() {
		
		Puzzle puzzle = new Puzzle();
		GameSession gameSession = new GameSession(puzzle, null);
		
		Cell cell = gameSession.getGameBoard().getCell(0, 0);
		gameSession.enterNumber(cell, 8);
		Board boardAfterEnteringNumber = gameSession.refresh();
		Cell cellAfterEnteringNumber = boardAfterEnteringNumber.getCell(0, 0);
		Assert.assertEquals(8, cellAfterEnteringNumber.getNumber());
		
		cell = gameSession.getGameBoard().getCell(1, 1);
		gameSession.enterNumber(cell, 5);
		boardAfterEnteringNumber = gameSession.refresh();
		cellAfterEnteringNumber = boardAfterEnteringNumber.getCell(1, 1);
		Assert.assertEquals(5, cellAfterEnteringNumber.getNumber());
		
		gameSession.doUndo();
		Board boardAfterUndo = gameSession.refresh();
		Cell cellAfterUndo = boardAfterUndo.getCell(0, 0);
		Assert.assertEquals(8, cellAfterUndo.getNumber());
		cellAfterUndo = boardAfterUndo.getCell(1, 1);
		Assert.assertEquals(0, cellAfterUndo.getNumber());
		
		gameSession.doUndo();
		boardAfterUndo = gameSession.refresh();
		cellAfterUndo = boardAfterUndo.getCell(0, 0);
		Assert.assertEquals(0, cellAfterUndo.getNumber());
		cellAfterUndo = boardAfterUndo.getCell(1, 1);
		Assert.assertEquals(0, cellAfterUndo.getNumber());
		
		gameSession.doRedo();
		Board boardAfterRedo = gameSession.refresh();
		Cell cellAfterRedo = boardAfterRedo.getCell(0, 0);
		Assert.assertEquals(8, cellAfterRedo.getNumber());
		cellAfterRedo = boardAfterRedo.getCell(1, 1);
		Assert.assertEquals(0, cellAfterRedo.getNumber());
		
		gameSession.doRedo();
		boardAfterRedo = gameSession.refresh();
		cellAfterRedo = boardAfterRedo.getCell(0, 0);
		Assert.assertEquals(8, cellAfterRedo.getNumber());
		cellAfterRedo = boardAfterRedo.getCell(1, 1);
		Assert.assertEquals(5, cellAfterRedo.getNumber());
		
	}
	
	@Test
	public void testHelp_About() {
		Puzzle puzzle = new Puzzle();
		GameSession gameSession = new GameSession(puzzle, null);
		Assert.assertNotNull(gameSession.getHelp(HelpType.ABOUT));
	}
	
	@Test
	public void testHelp_Hint() {
		Puzzle puzzle = new Puzzle();
		GameSession gameSession = new GameSession(puzzle, null);
		Assert.assertNotNull(gameSession.getHelp(HelpType.HINT));
	}
	
	@Test
	public void testHelp_Rules() {
		Puzzle puzzle = new Puzzle();
		GameSession gameSession = new GameSession(puzzle, null);
		Assert.assertNotNull(gameSession.getHelp(HelpType.RULES));
		
	}
	
	@Test
	public void testDoSave() {
		String saveFile = "save01";
		Puzzle puzzle = new Puzzle();
		GameSession gameSession = new GameSession(puzzle, null);
		gameSession.doSave(saveFile);
		
		/* The simplest implementation would be persisting a game as a 81 character String on a text file. 
		 * We can have a designated text file for saving games. If we use JSON, we can simulate a map
		 * where key is the save name and the value would be the stored game. 
		 * This way, if we need more information stored such as Player, time spent, and etc.
		 * we can expand more easily.
		 * We can go with XML, but JSON is much lighter */
		
//		Path file = FileSystems.getDefault().getPath("");
//		Charset charset = Charset.forName("US-ASCII");
//		List<String> readSaveFile =  Files.readAllLines(file, charset);
		
		JSONParser parser = new JSONParser();
		
		try {
			Object obj = parser.parse(new FileReader("./sudoku.dt"));
			JSONObject jsonObject = (JSONObject) obj;
			String data = (String) jsonObject.get("save01");
			/* TODO - translation is needed from data to board */
		} catch (IOException | ParseException e) {			// 
			e.printStackTrace();
			Assert.fail("exception should not be thrown");
		}
		 
		Assert.fail("Not Implemented to verify the functionality");
	}
	
	//FIXME: Update this test. There are unused values.
	@Test
	public void testDoLoad() {
		String saveFile = "save01";
		Puzzle puzzle = new Puzzle();
		GameSession gameSession = new GameSession(puzzle, null);
		
		/* enter 8 at 0,0 */
		Cell cell = gameSession.getGameBoard().getCell(0, 0);
		gameSession.enterNumber(cell, 8);
		Board boardAfterEnteringNumber = gameSession.refresh();
		
		/* save */
		gameSession.doSave(saveFile);
		
		/* enter 5 at 1, 1 */
		cell = gameSession.getGameBoard().getCell(1, 1);
		gameSession.enterNumber(cell, 5);
		boardAfterEnteringNumber = gameSession.refresh();
		
		/* load */
		gameSession.doLoad(saveFile);
		Board boardAfterLoad = gameSession.refresh();
		Cell savedCell = boardAfterLoad.getCell(0, 0);
		Assert.assertEquals(8, savedCell.getNumber());
		
		Cell unsavedCell = boardAfterLoad.getCell(1, 1);
		Assert.assertEquals(0, unsavedCell.getNumber());
	}
	
	/**
	 * FIXME - implement and update the document
	 */
	@Test
	public void testDoUndo_With_PencilMark() {

		final int TARGET_PENCIL_MARK = 8;
		Puzzle puzzle = new Puzzle();
		GameSession gameSession = new GameSession(puzzle, null);
		
		Cell cell = gameSession.getGameBoard().getCell(0, 0);
		gameSession.enterPencilMark(cell, TARGET_PENCIL_MARK, true);
		
		Board boardAfterEnteringPencilMark = gameSession.refresh();
		Cell cellAfterEnteringPencilMark = boardAfterEnteringPencilMark.getCell(0, 0);
		Assert.assertTrue(cellAfterEnteringPencilMark.getPencilMarks().contains(TARGET_PENCIL_MARK));	
		
		gameSession.doUndo();
		Board boardAfterUndo = gameSession.refresh();
		Cell cellAfterUndo = boardAfterUndo.getCell(0, 0);
		Assert.assertFalse(cellAfterUndo.getPencilMarks().contains(TARGET_PENCIL_MARK));
		
		gameSession.doRedo();
		Board boardAfterRedo = gameSession.refresh();
		Cell cellAfterRedo = boardAfterRedo.getCell(0, 0);
		Assert.assertTrue(cellAfterRedo.getPencilMarks().contains(TARGET_PENCIL_MARK));
	}
	
	/**
	 * FIXME - implement and update the document
	 */
	@Test
	public void testDoRedo_With_PencilMark() {
		
		final int TARGET_PENCIL_MARK = 7;
		Puzzle puzzle = new Puzzle();
		GameSession gameSession = new GameSession(puzzle, null);
		
		Cell cell = gameSession.getGameBoard().getCell(0, 0);
		gameSession.enterPencilMark(cell, TARGET_PENCIL_MARK,  true);
		Board boardAfterEnteringPencilMark = gameSession.refresh();
		Cell cellAfterEnteringNumber = boardAfterEnteringPencilMark.getCell(0, 0);
		Assert.assertTrue(cellAfterEnteringNumber.getPencilMarks().contains(TARGET_PENCIL_MARK));	
		
		gameSession.doUndo();
		Board boardAfterUndo = gameSession.refresh();
		Cell cellAfterUndo = boardAfterUndo.getCell(0, 0);
		Assert.assertFalse(cellAfterUndo.getPencilMarks().contains(TARGET_PENCIL_MARK));
		
		gameSession.doRedo();
		Board boardAfterRedo = gameSession.refresh();
		Cell cellAfterRedo = boardAfterRedo.getCell(0, 0);
		Assert.assertTrue(cellAfterRedo.getPencilMarks().contains(TARGET_PENCIL_MARK));
	}

	/**
	 * Raising coverage: unit testing scenario of clearing a cell that has a number already
	 */
	@Test
	public void testEnterNumber_clear_existing() {
		
		final int TARGET_NUMBER = 3;
		Puzzle puzzle = new Puzzle();
		GameSession gameSession = new GameSession(puzzle, null);
		
		Cell cell = gameSession.getGameBoard().getCell(0, 0);
		gameSession.enterNumber(cell, TARGET_NUMBER);
		Board boardAfterEnteringNumber = gameSession.refresh();
		Cell cellAfterEnteringNumber = boardAfterEnteringNumber.getCell(0, 0);
		Assert.assertEquals(TARGET_NUMBER, cellAfterEnteringNumber.getNumber());
		
		gameSession.enterNumber(cell, 0);
		Assert.assertEquals(0, cellAfterEnteringNumber.getNumber());
	}
	
	/**
	 * Raising coverage: unit testing scenario of clearing a cell that does not have a number
	 */
	@Test
	public void testEnterNumber_clear_non_existing() {
		
		Puzzle puzzle = new Puzzle();
		GameSession gameSession = new GameSession(puzzle, null);
		
		Cell cell = gameSession.getGameBoard().getCell(0, 0);
		Board boardBeforeEnteringNumber = gameSession.refresh();
		Cell cellAfterEnteringNumber = boardBeforeEnteringNumber.getCell(0, 0);
		Assert.assertEquals(0, cellAfterEnteringNumber.getNumber());
		
		gameSession.enterNumber(cell, 0);
		Assert.assertEquals(0, cellAfterEnteringNumber.getNumber());
	}
	
	/**
	 * Raising coverage: unit testing scenario of clearing pencil mark
	 */
	@Test
	public void testUpdatePencilMark() {
		
		final int TARGET_PENCIL_MARK = 8;
		Puzzle puzzle = new Puzzle();
		GameSession gameSession = new GameSession(puzzle, null);
		
		Cell cell = gameSession.getGameBoard().getCell(0, 0);
		gameSession.enterPencilMark(cell, TARGET_PENCIL_MARK, true);
		
		Board boardAfterEnteringPencilMark = gameSession.refresh();
		Cell cellAfterEnteringPencilMark = boardAfterEnteringPencilMark.getCell(0, 0);
		Assert.assertTrue(cellAfterEnteringPencilMark.getPencilMarks().contains(TARGET_PENCIL_MARK));	
		
		gameSession.updatePencilMark(cellAfterEnteringPencilMark, 0, false);
		Board boardAfterPencilMarkClear = gameSession.refresh();
		Cell cellAfterPencilMarkClear = boardAfterPencilMarkClear.getCell(0, 0);
		Assert.assertFalse(cellAfterPencilMarkClear.getPencilMarks().contains(TARGET_PENCIL_MARK));
	}
	
	@Test
	public void testPencilMarkMode() {
		Puzzle puzzle = new Puzzle();
		GameSession gameSession = new GameSession(puzzle, null);
		
		Assert.assertFalse(gameSession.isPencilMarkMode());
		
		gameSession.setPencilMarkMode(true);
		Assert.assertTrue(gameSession.isPencilMarkMode());
		
		gameSession.setPencilMarkMode(false);
		Assert.assertFalse(gameSession.isPencilMarkMode());
	}
}
