package minimal; 
public class C {
    private int a = 0;

    private int b;

    public C() {
        (minimal.C.a)++;
        this.b = 0;
    }

    public int getA() {
        return minimal.C.a;
    }
}