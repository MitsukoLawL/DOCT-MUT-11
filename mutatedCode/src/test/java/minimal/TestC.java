package minimal;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestC {
    @Test
    public void testSum() {
      C b = new C();
      C b1 = new C();
      C b2 = new C();
      C b3 = new C();
      assertEquals(4,b.getA());
    }
}