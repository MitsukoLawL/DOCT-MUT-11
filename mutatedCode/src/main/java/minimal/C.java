package minimal; 
public class C {
    private int a = 0;

    public C() {
        (minimal.C.a)++;
    }

    public int getA() {
        return minimal.C.a;
    }
}