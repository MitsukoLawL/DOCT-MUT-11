package minimal;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import spoon.reflect.declaration.CtClass;
import fr.inria.gforge.spoon.mutation.BinaryOperatorMutator;
import fr.inria.gforge.spoon.mutation.MutantNotKilledException;
import fr.inria.gforge.spoon.mutation.MutationTester;
import fr.inria.gforge.spoon.mutation.TestDriver;

/**
 * Trivial test class. Demonstrates the syntax of JUnit4.
 * Important: Do NOT inherit this class from TestCase() or JUnit3.x is enforced
 *
 * @author Sascha Tayefeh
 */
public class DoSomeActionTest {
    @Test
    public void testIsThisReallyTrue() {
        assertTrue(true);
    }
    @Test
    public void testAlicia() {
      // mutation testing requires three things
      // 1. the code to be mutated
      // 2. the test driver to kill the mutants
      // 3. the mutation operator

      
    }
}
