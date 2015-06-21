package edu.psu.sweng500.team8;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import edu.psu.sweng500.team8.coreDataStructures.BoardTests;
import edu.psu.sweng500.team8.coreDataStructures.CellConstraintsTest;
import edu.psu.sweng500.team8.coreDataStructures.CellCoordinatesTests;
import edu.psu.sweng500.team8.coreDataStructures.CellGridTests;
import edu.psu.sweng500.team8.coreDataStructures.CellTests;
import edu.psu.sweng500.team8.coreDataStructures.PencilMarkManagerTests;
import edu.psu.sweng500.team8.play.GameSessionTests;
import edu.psu.sweng500.team8.puzzleGenerator.FillPuzzleTests;
import edu.psu.sweng500.team8.puzzleGenerator.PuzzleGeneratorTests;
import edu.psu.sweng500.team8.puzzleGenerator.SolutionGeneratorTests;
import edu.psu.sweng500.team8.puzzleGenerator.Test5;
import edu.psu.sweng500.team8.solver.ConstraintSolverTests;
import edu.psu.sweng500.team8.solver.GuessAndCheckSolverTests;
import edu.psu.sweng500.team8.solver.HintGeneratorTests;



@RunWith(Suite.class)
@Suite.SuiteClasses({
   BoardTests.class,
   CellConstraintsTest.class,
   CellCoordinatesTests.class,
   CellGridTests.class,
   CellTests.class,
   PencilMarkManagerTests.class,
   GameSessionTests.class,
   FillPuzzleTests.class,
   PuzzleGeneratorTests.class,
   SolutionGeneratorTests.class,
   Test5.class,
   ConstraintSolverTests.class,
   GuessAndCheckSolverTests.class,
   HintGeneratorTests.class
})
public class SudokuTestSuite {

}
