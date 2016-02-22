package minimal;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.Assert;

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
}
