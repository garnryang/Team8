import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import CoreDataStructures.CellGridTest;
import CoreDataStructures.CellTest;
import CoreDataStructures.HelperTest;
import PuzzleGenerator.SolutionGeneratorTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
   SolutionGeneratorTest.class,
   CellGridTest.class,
   CellTest.class,
   HelperTest.class   
})
public class SudokuTestSuite {

}
