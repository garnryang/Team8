package edu.psu.sweng500.team8.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import edu.psu.sweng500.team8.coreDataStructures.Cell;
import edu.psu.sweng500.team8.play.GameSession;

/**
 * Prototype until we have better number entering system
 * 
 * @author ScottL
 *
 */
public class CustomKeyListener implements KeyListener {

	private Cell cell;
	private GameSession gameSession;
	
	public CustomKeyListener(Cell cell, GameSession gameSession) {
		this.cell = cell;
		this.gameSession = gameSession;
	}

	public void keyTyped(java.awt.event.KeyEvent keyEvent) {
		
		
		
		if (KeyEvent.VK_BACK_SPACE == keyEvent.getKeyChar() || KeyEvent.VK_DELETE == keyEvent.getKeyChar()) {
			
			gameSession.enterNumber(this.cell, 0);
		} else {
			gameSession.enterNumber(this.cell, Integer.parseInt(String.valueOf(keyEvent.getKeyChar())));
		}
	}

	@Override
	public void keyPressed(KeyEvent keyEvent) {
		
	
//		if (KeyEvent.VK_BACK_SPACE == keyEvent.getKeyChar()) {
//			gameSession.enterNumber(this.cell.getCoordinates(), 0);
//		} else {
//			gameSession.enterNumber(this.cell.getCoordinates(), Integer.parseInt(String.valueOf(keyEvent.getKeyChar())));
//		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	
	}
}
