package minimal; 

public class B extends minimal.A {
    int calc() {
        a = 4;
        return (a) - 4;
    }

    int boucle() {
        int i;
        for (i = 1 ; i < 5 ; i++)
            ;
        return i;
    }
}
