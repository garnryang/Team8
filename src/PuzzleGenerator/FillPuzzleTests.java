package PuzzleGenerator;

import static org.junit.Assert.*;

import org.junit.Test;

import CoreDataStructures.CellGrid;
import CoreDataStructures.Puzzle;
import CoreDataStructures.Puzzle.DifficultyLevel;

public class FillPuzzleTests {

	@Test //UC3 Step 1
	public void playerSelectsAnEmptySquare() {
		Game game = new Game(DifficultyLevel.Easy);
		BoardGUI boardGui = new BoardGUI();
		
		Puzzle puzzle = game.getPuzzle();
		CellGrid grid = puzzle.getGrid();
		boardGui.setGame(game);
		
		int row = 0;
		int column = 0;
		
		for(int i = 0; i < 81; i++){
			 int r = ((int)Math.ceil(i/9.0)-1);
			 int c = (i%9)-1;
			 int test = grid.getCell(r, c==-1?8:c).getNumber();
			 
			 if(test == 0){
				 row = r;
				 column = c;
				 return;				 
			 }			 			 
		 }
				
		assertTrue(grid.getCell(row,column).getNumber() == 0);
		boardGui.setFocus(row,column);
		assertTrue(boardGui.hasFocus(row,column));
	}
	
	@Test //UC3 Steps 2&3
	public void playerEntersNumber(){
		Game game = new Game(DifficultyLevel.Easy);
		BoardGUI boardGui = new BoardGUI();
		
		Puzzle puzzle = game.getPuzzle();
		CellGrid grid = puzzle.getGrid();
		boardGui.setGame(game);
		
		int row = 0;
		int column = 0;
		
		for(int i = 0; i < 81; i++){
			 int r = ((int)Math.ceil(i/9.0)-1);
			 int c = (i%9)-1;
			 int test = grid.getCell(r, c==-1?8:c).getNumber();
			 
			 if(test == 0){
				 row = r;
				 column = c;
				 return;				 
			 }			 			 
		 }
		
		boardGui.setNumber(row,column,9);
		assertTrue(boardGui.getvalue(row, column) == 9);
		
	}
	@Test //UC3 Step 4
	public void systemClearsAnyPenciledinValuesInTheSquare(){
		Game game = new Game(DifficultyLevel.Easy);
		BoardGUI boardGui = new BoardGUI();
		
		Puzzle puzzle = game.getPuzzle();
		CellGrid grid = puzzle.getGrid();
		boardGui.setGame(game);
		
		int row = 0;
		int column = 0;
		
		for(int i = 0; i < 81; i++){
			 int r = ((int)Math.ceil(i/9.0)-1);
			 int c = (i%9)-1;
			 int test = grid.getCell(r, c==-1?8:c).getNumber();
			 
			 if(test == 0){
				 row = r;
				 column = c;
				 return;				 
			 }			 			 
		 }
		
		boardGui.setNumber(row,column,9);
		assertNull(boardGui.getvalue(row, column).getPencilMark());
	}
	
	@Test //UC3 Step 5
	public void systemClearsAnyPenciledinValuesFromRowColumnBoxes(){
		Game game = new Game(DifficultyLevel.Easy);
		BoardGUI boardGui = new BoardGUI();
		
		Puzzle puzzle = game.getPuzzle();
		CellGrid grid = puzzle.getGrid();
		boardGui.setGame(game);
		
		int row = 0;
		int column = 0;
		
		for(int i = 0; i < 81; i++){
			 int r = ((int)Math.ceil(i/9.0)-1);
			 int c = (i%9)-1;
			 int test = grid.getCell(r, c==-1?8:c).getNumber();
			 
			 if(test == 0){
				 row = r;
				 column = c;
				 return;				 
			 }			 			 
		 }
		
		boardGui.setNumber(row,column,9);
		boolean test = true;
		
		//check row pencilmark
		for(int i = 0; i < 9; i++){
			if(grid.getCell(i, column).getPencilMark().isNine()){
				test = true;
			}else{
				test = false;
			}
			assertFalse(test);
		}
		
		//check column Pencilmark
		for(int i = 0; i < 9; i++){
			if(grid.getCell(row, i).getPencilMark().isNine()){
				test = true;
			}else{
				test = false;
			}
			assertFalse(test);
		}
		
		//check box pencilmark
		int rowInit = (int) Math.ceil(row+1);
		int columnInit = (int) Math.ceil(column+1);
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){			
				if(grid.getCell(row+(rowInit*i), column+(columnInit*j)).getPencilMark().isNine()){
					test = true;
				}else{
					test = false;
				}
				assertFalse(test);
			}
		}
	}
}
