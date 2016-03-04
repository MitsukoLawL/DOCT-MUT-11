<<<<<<< HEAD
package minimal;

public class B extends A {
	int calc(){
		super.a=4;
		int b = 3+5;
		return a+4;
	}

	int boucle() {
		int i, ai = 1;
		for (i = 1; i < 5; i++);


		while (ai < 105){ ai++;}


		do{
			ai--;
		}while (ai>0);


		return i*ai;
	}

}
=======
package minimal; 
public class B extends minimal.A {
    int calc() {
        a = 4;
        int b = 3 + 5;
        return (a) * 4;
    }

    int boucle() {
        int i;
        int ai = 1;
        for (i = 1 ; i < 5 ; i++)
            ;
        while (ai < 105) {
            ai++;
        }
        do {
            ai--;
        } while (ai > 0 );
        return i * ai;
    }
}
>>>>>>> cf0ffa856ca8f2c5ee970a91e4d11732ddfb1535
