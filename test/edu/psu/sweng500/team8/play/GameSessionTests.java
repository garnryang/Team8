package edu.psu.sweng500.team8.play;

import org.junit.Assert;
import org.junit.Test;

import edu.psu.sweng500.team8.coreDataStructures.Board;
import edu.psu.sweng500.team8.coreDataStructures.Cell;
import edu.psu.sweng500.team8.coreDataStructures.CellCoordinates;

public class GameSessionTests {

	@Test
	public void testUndo_1_Action() {
		
		Board board = new Board();
		GameSession gameSession = new GameSession(board);
		
		CellCoordinates cellCoordinates = new CellCoordinates(0, 0);
		gameSession.enterNumber(cellCoordinates, 8);
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
		
		Board board = new Board();
		GameSession gameSession = new GameSession(board);
		
		gameSession.doUndo();
		Board boardAfterUndo = gameSession.refresh();
		Cell cellAfterUndo = boardAfterUndo.getCell(0, 0);
		Assert.assertEquals(0, cellAfterUndo.getNumber());
	}
	
	@Test
	public void testUndo_2_Action() {
		
		Board board = new Board();
		GameSession gameSession = new GameSession(board);
		
		CellCoordinates cellCoordinates_1 = new CellCoordinates(0, 0);
		gameSession.enterNumber(cellCoordinates_1, 8);
		Board boardAfterEnteringNumber = gameSession.refresh();
		Cell cellAfterEnteringNumber = boardAfterEnteringNumber.getCell(0, 0);
		Assert.assertEquals(8, cellAfterEnteringNumber.getNumber());
		
		CellCoordinates cellCoordinates_2 = new CellCoordinates(1, 1);
		gameSession.enterNumber(cellCoordinates_2, 5);
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
		
		Board board = new Board();
		GameSession gameSession = new GameSession(board);
		
		CellCoordinates cellCoordinates = new CellCoordinates(0, 0);
		gameSession.enterNumber(cellCoordinates, 8);
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
		
		Board board = new Board();
		GameSession gameSession = new GameSession(board);
		
		CellCoordinates cellCoordinates = new CellCoordinates(0, 0);
		gameSession.enterNumber(cellCoordinates, 8);
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
		
		Board board = new Board();
		GameSession gameSession = new GameSession(board);
		
		CellCoordinates cellCoordinates = new CellCoordinates(0, 0);
		gameSession.enterNumber(cellCoordinates, 8);
		Board boardAfterEnteringNumber = gameSession.refresh();
		Cell cellAfterEnteringNumber = boardAfterEnteringNumber.getCell(0, 0);
		Assert.assertEquals(8, cellAfterEnteringNumber.getNumber());
		
		cellCoordinates = new CellCoordinates(1, 1);
		gameSession.enterNumber(cellCoordinates, 5);
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
		Board board = new Board();
		GameSession gameSession = new GameSession(board);
		Assert.assertNotNull(gameSession.getHelp(HelpType.ABOUT));
	}
	
	@Test
	public void testHelp_Hint() {
		Board board = new Board();
		GameSession gameSession = new GameSession(board);
		Assert.assertNotNull(gameSession.getHelp(HelpType.HINT));
	}
	
	@Test
	public void testHelp_Rules() {
		Board board = new Board();
		GameSession gameSession = new GameSession(board);
		Assert.assertNotNull(gameSession.getHelp(HelpType.RULES));
		
	}
}
