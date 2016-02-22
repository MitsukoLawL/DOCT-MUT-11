package minimal; 
public class B {
    protected int a;

    int calc() {
        a = 2;
        return (a) - 2;
    }

    public static void main(java.lang.String[] args) {
        minimal.B b = new minimal.B();
        java.lang.System.out.println(b.calc());
    }
}