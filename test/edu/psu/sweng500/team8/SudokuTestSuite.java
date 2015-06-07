package edu.psu.sweng500.team8;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import edu.psu.sweng500.team8.coreDataStructures.BoardTests;
import edu.psu.sweng500.team8.coreDataStructures.CellConstraintsTest;
import edu.psu.sweng500.team8.coreDataStructures.CellCoordinatesTests;
import edu.psu.sweng500.team8.coreDataStructures.CellGridTest;
import edu.psu.sweng500.team8.coreDataStructures.CellTest;
import edu.psu.sweng500.team8.coreDataStructures.PencilMarkManagerTests;
import edu.psu.sweng500.team8.play.GameSessionTests;
import edu.psu.sweng500.team8.puzzleGenerator.SolutionGeneratorTest;
import edu.psu.sweng500.team8.solver.ConstraintSolverTests;
import edu.psu.sweng500.team8.solver.GuessAndCheckSolverTests;
import edu.psu.sweng500.team8.solver.HintGeneratorTests;



@RunWith(Suite.class)
@Suite.SuiteClasses({
   BoardTests.class,
   CellCoordinatesTests.class,
   PencilMarkManagerTests.class,
   SolutionGeneratorTest.class,
   CellGridTest.class,
   CellTest.class,
   CellConstraintsTest.class,
   SolutionGeneratorTest.class,
   ConstraintSolverTests.class,
   GuessAndCheckSolverTests.class,
   HintGeneratorTests.class,
   GameSessionTests.class
})
public class SudokuTestSuite {

}
