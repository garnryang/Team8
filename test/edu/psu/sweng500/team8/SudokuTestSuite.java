package edu.psu.sweng500.team8;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import edu.psu.sweng500.team8.coreDataStructures.BoardTests;
import edu.psu.sweng500.team8.coreDataStructures.CellConstraintsTests;
import edu.psu.sweng500.team8.coreDataStructures.CellCoordinatesTests;
import edu.psu.sweng500.team8.coreDataStructures.CellGridTests;
import edu.psu.sweng500.team8.coreDataStructures.CellTests;
import edu.psu.sweng500.team8.coreDataStructures.PuzzleTests;
import edu.psu.sweng500.team8.gui.NumberButtonGUITest;
import edu.psu.sweng500.team8.play.GameSessionTests;
import edu.psu.sweng500.team8.play.SudokuActionTest;
import edu.psu.sweng500.team8.puzzleGenerator.FillPuzzleTests;
import edu.psu.sweng500.team8.puzzleGenerator.PuzzleGeneratorTests;
import edu.psu.sweng500.team8.puzzleGenerator.PuzzleRepositoryTests;
import edu.psu.sweng500.team8.puzzleGenerator.SolutionGeneratorTests;
import edu.psu.sweng500.team8.solver.ConstraintSolverTests;
import edu.psu.sweng500.team8.solver.GuessAndCheckSolverTests;
import edu.psu.sweng500.team8.solver.HintGeneratorTests;



@RunWith(Suite.class)
@Suite.SuiteClasses({
   BoardTests.class,
   CellConstraintsTests.class,
   CellCoordinatesTests.class,
   CellGridTests.class,
   CellTests.class,
   PuzzleTests.class,
   GameSessionTests.class,
   FillPuzzleTests.class,
   PuzzleGeneratorTests.class,
   PuzzleRepositoryTests.class,
   SolutionGeneratorTests.class,   
   ConstraintSolverTests.class,
   GuessAndCheckSolverTests.class,
   HintGeneratorTests.class,
   SudokuActionTest.class,
   NumberButtonGUITest.class
})
public class SudokuTestSuite {

}
