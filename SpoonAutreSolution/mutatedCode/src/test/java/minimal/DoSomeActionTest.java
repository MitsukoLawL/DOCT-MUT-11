package minimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.Assert;

public class DoSomeActionTest {
    @Test
    public void testIsThisReallyTrue() {
        assertTrue(true);
    }

    @Test
    public void testSum() {
      DoSomeAction d = new DoSomeAction();
      assertEquals(7,d.calc());
    }
}