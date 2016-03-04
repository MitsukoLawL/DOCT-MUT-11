package minimal;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestB {
    @Test
    public void testSum() {
      B b = new B();
      assertEquals(8,b.calc());
    }

    @Test(timeout = 100)
    public void testLoop() {
        B b = new B();
        assertEquals(0,b.boucle());
    }
}
