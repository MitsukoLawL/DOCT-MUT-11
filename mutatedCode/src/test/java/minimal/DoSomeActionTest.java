package minimal;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.Assert;

public class DoSomeActionTest {
    @Test(timeout=100)
    public void testIsThisReallyTrue() {
        assertTrue(true);
    }

    @Test
    public void testSum() {
      DoSomeAction d = new DoSomeAction();
      assertEquals(7,d.calc());
    }
    @Test(timeout=1000)
    public void testLopp() {
      DoSomeAction d = new DoSomeAction();
      assertEquals(10,d.calc2());
      assertEquals(10,d.calc3());
    }
}
