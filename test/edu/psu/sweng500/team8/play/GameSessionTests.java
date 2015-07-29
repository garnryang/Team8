package edu.psu.sweng500.team8.play;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
		GameSession gameSession = new GameSession(puzzle);
		
		Cell cell = gameSession.getGameBoard().getCell(0, 0);
		gameSession.enterNumber(cell, 8);
		Board boardAfterEnteringNumber = gameSession.getGameBoard(); 
		Cell cellAfterEnteringNumber = boardAfterEnteringNumber.getCell(0, 0);
		Assert.assertEquals(8, cellAfterEnteringNumber.getNumber());	
		
		gameSession.doUndo();
		Board boardAfterUndo = gameSession.getGameBoard(); //FIXME: Query is unnecessary. The board object does not change.
		Cell cellAfterUndo = boardAfterUndo.getCell(0, 0);
		Assert.assertEquals(0, cellAfterUndo.getNumber());
	
	}
	
	@Test
	public void testUndo_0_Action() {
		
		Puzzle puzzle = new Puzzle();
		GameSession gameSession = new GameSession(puzzle);
		
		gameSession.doUndo();
		Board boardAfterUndo = gameSession.getGameBoard();
		Cell cellAfterUndo = boardAfterUndo.getCell(0, 0);
		Assert.assertEquals(0, cellAfterUndo.getNumber());
	}
	
	@Test
	public void testUndo_2_Action() {
		
		Puzzle puzzle = new Puzzle();
		GameSession gameSession = new GameSession(puzzle);
		
		Cell cell_1 = gameSession.getGameBoard().getCell(0, 0);
		gameSession.enterNumber(cell_1, 8);
		Board boardAfterEnteringNumber = gameSession.getGameBoard();
		Cell cellAfterEnteringNumber = boardAfterEnteringNumber.getCell(0, 0);
		Assert.assertEquals(8, cellAfterEnteringNumber.getNumber());
		
		Cell cell_2 = gameSession.getGameBoard().getCell(1, 1);
		gameSession.enterNumber(cell_2, 5);
		boardAfterEnteringNumber = gameSession.getGameBoard(); //FIXME: All these queries are unnecessary. The board object does not change.
		cellAfterEnteringNumber = boardAfterEnteringNumber.getCell(1, 1);
		Assert.assertEquals(5, cellAfterEnteringNumber.getNumber());
		
		gameSession.doUndo();
		Board boardAfterUndo = gameSession.getGameBoard();
		Cell cellAfterUndo = boardAfterUndo.getCell(0, 0);
		Assert.assertEquals(8, cellAfterUndo.getNumber());
		cellAfterUndo = boardAfterUndo.getCell(1, 1);
		Assert.assertEquals(0, cellAfterUndo.getNumber());
		
		gameSession.doUndo();
		boardAfterUndo = gameSession.getGameBoard();
		cellAfterUndo = boardAfterUndo.getCell(0, 0);
		Assert.assertEquals(0, cellAfterUndo.getNumber());
		cellAfterUndo = boardAfterUndo.getCell(1, 1);
		Assert.assertEquals(0, cellAfterUndo.getNumber());
	}
	
	@Test
	public void testRedo_1_Action() {
		
		Puzzle puzzle = new Puzzle();
		GameSession gameSession = new GameSession(puzzle);
		
		Cell cell = gameSession.getGameBoard().getCell(0, 0);
		gameSession.enterNumber(cell, 8);
		Board boardAfterEnteringNumber = gameSession.getGameBoard(); //FIXME: All these queries are unnecessary. The board object does not change.
		Cell cellAfterEnteringNumber = boardAfterEnteringNumber.getCell(0, 0);
		Assert.assertEquals(8, cellAfterEnteringNumber.getNumber());	
		
		gameSession.doUndo();
		Board boardAfterUndo = gameSession.getGameBoard();
		Cell cellAfterUndo = boardAfterUndo.getCell(0, 0);
		Assert.assertEquals(0, cellAfterUndo.getNumber());
		
		gameSession.doRedo();
		Board boardAfterRedo = gameSession.getGameBoard();
		Cell cellAfterRedo = boardAfterRedo.getCell(0, 0);
		Assert.assertEquals(8, cellAfterRedo.getNumber());
		
	}
	
	@Test
	public void testRedo_0_Action() {
		
		Puzzle puzzle = new Puzzle();
		GameSession gameSession = new GameSession(puzzle);
		
		Cell cell = gameSession.getGameBoard().getCell(0, 0);
		gameSession.enterNumber(cell, 8);
		Board boardAfterEnteringNumber = gameSession.getGameBoard();
		Cell cellAfterEnteringNumber = boardAfterEnteringNumber.getCell(0, 0);
		Assert.assertEquals(8, cellAfterEnteringNumber.getNumber());	
		
		gameSession.doRedo();
		Board boardAfterRedo = gameSession.getGameBoard();
		Cell cellAfterRedo = boardAfterRedo.getCell(0, 0);
		Assert.assertEquals(8, cellAfterRedo.getNumber());
		
	}
	
	@Test
	public void testRedo_2_Action() {
		
		Puzzle puzzle = new Puzzle();
		GameSession gameSession = new GameSession(puzzle);
		
		Cell cell = gameSession.getGameBoard().getCell(0, 0);
		gameSession.enterNumber(cell, 8);
		Board boardAfterEnteringNumber = gameSession.getGameBoard();
		Cell cellAfterEnteringNumber = boardAfterEnteringNumber.getCell(0, 0);
		Assert.assertEquals(8, cellAfterEnteringNumber.getNumber());
		
		cell = gameSession.getGameBoard().getCell(1, 1);
		gameSession.enterNumber(cell, 5);
		boardAfterEnteringNumber = gameSession.getGameBoard(); //FIXME: All these queries are unnecessary. The board object does not change.
		cellAfterEnteringNumber = boardAfterEnteringNumber.getCell(1, 1);
		Assert.assertEquals(5, cellAfterEnteringNumber.getNumber());
		
		gameSession.doUndo();
		Board boardAfterUndo = gameSession.getGameBoard();
		Cell cellAfterUndo = boardAfterUndo.getCell(0, 0);
		Assert.assertEquals(8, cellAfterUndo.getNumber());
		cellAfterUndo = boardAfterUndo.getCell(1, 1);
		Assert.assertEquals(0, cellAfterUndo.getNumber());
		
		gameSession.doUndo();
		boardAfterUndo = gameSession.getGameBoard();
		cellAfterUndo = boardAfterUndo.getCell(0, 0);
		Assert.assertEquals(0, cellAfterUndo.getNumber());
		cellAfterUndo = boardAfterUndo.getCell(1, 1);
		Assert.assertEquals(0, cellAfterUndo.getNumber());
		
		gameSession.doRedo();
		Board boardAfterRedo = gameSession.getGameBoard();
		Cell cellAfterRedo = boardAfterRedo.getCell(0, 0);
		Assert.assertEquals(8, cellAfterRedo.getNumber());
		cellAfterRedo = boardAfterRedo.getCell(1, 1);
		Assert.assertEquals(0, cellAfterRedo.getNumber());
		
		gameSession.doRedo();
		boardAfterRedo = gameSession.getGameBoard();
		cellAfterRedo = boardAfterRedo.getCell(0, 0);
		Assert.assertEquals(8, cellAfterRedo.getNumber());
		cellAfterRedo = boardAfterRedo.getCell(1, 1);
		Assert.assertEquals(5, cellAfterRedo.getNumber());
		
	}
	
	@Test
	public void testHelp_About() {
		Puzzle puzzle = new Puzzle();
		GameSession gameSession = new GameSession(puzzle);
		Assert.assertNotNull(gameSession.getHelp(HelpType.ABOUT));
	}
	
	@Test
	public void testHelp_Hint() {
		Puzzle puzzle = new Puzzle();
		GameSession gameSession = new GameSession(puzzle);
		Assert.assertNotNull(gameSession.getHelp(HelpType.HINT));
	}
	
	@Test
	public void testHelp_Rules() {
		Puzzle puzzle = new Puzzle();
		GameSession gameSession = new GameSession(puzzle);
		Assert.assertNotNull(gameSession.getHelp(HelpType.RULES));
		
	}
	
	@Test
	public void testDoSave() {
		String saveFile = "save01";
		Puzzle puzzle = new Puzzle();
		GameSession gameSession = new GameSession(puzzle);
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
	 
	@Test
	public void testDoLoad() {
		String saveFile = "save01";
		Puzzle puzzle = new Puzzle();
		GameSession gameSession = new GameSession(puzzle);
		
		/* enter 8 at 0,0 */
		Cell cell = gameSession.getGameBoard().getCell(0, 0);
		gameSession.enterNumber(cell, 8);
		
		/* save */
		gameSession.doSave(saveFile);
		
		/* enter 5 at 1, 1 */
		cell = gameSession.getGameBoard().getCell(1, 1);
		gameSession.enterNumber(cell, 5);
		
		/* load */
		gameSession.doLoad(saveFile);
		Board boardAfterLoad = gameSession.getGameBoard();
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
		Assert.fail();
	}
	
	/**
	 * FIXME - implement and update the document
	 */
	@Test
	public void testDoRedo_With_PencilMark() {
		Assert.fail();
	}
	
	@Test
	public void hasUndoActionsReturnsTrueIfUndoStackIsNotEmpty() {
		
		Puzzle puzzle = new Puzzle();
		GameSession gameSession = new GameSession(puzzle);
		
		//Add an action and verify the undo stack is not empty
		Cell cell = gameSession.getGameBoard().getCell(0, 0);
		gameSession.enterNumber(cell, 5);
		
		assertTrue(gameSession.hasUndoActions());
	}
	
	@Test
	public void hasUndoActionsReturnsFalseIfUndoStackIsEmpty() {
		
		Puzzle puzzle = new Puzzle();
		GameSession gameSession = new GameSession(puzzle);
		
		//Verify the undo stack is empty
		assertFalse(gameSession.hasUndoActions());
	}
	
	@Test
	public void hasRedoActionsReturnsTrueIfRedoStackIsNotEmpty() {
		
		Puzzle puzzle = new Puzzle();
		GameSession gameSession = new GameSession(puzzle);
		
		//Add an action, undo it, and verify the redo stack is not empty
		Cell cell = gameSession.getGameBoard().getCell(0, 0);
		gameSession.enterNumber(cell, 5);
		
		gameSession.doUndo();
		
		assertTrue(gameSession.hasRedoActions());
	}
	
	@Test
	public void hasRedoActionsReturnsFalseIfRedoStackIsEmpty() {
		
		Puzzle puzzle = new Puzzle();
		GameSession gameSession = new GameSession(puzzle);
		
		//Verify the redo stack is empty
		assertFalse(gameSession.hasRedoActions());
		
		//Add an action, verify it is still empty
		Cell cell = gameSession.getGameBoard().getCell(0, 0);
		gameSession.enterNumber(cell, 5);
		
		assertFalse(gameSession.hasRedoActions());
	}
}
