<<<<<<< HEAD
package minimal;

public class DoSomeAction {
	protected int a;
	int calc(){
		a=3;
		return a+4;
	}
	int calc2(){
		a = 0;
		while (a<10) a++;
		return a;
	}
	int calc3(){
		a = 0;
		for (a=0; a<10; a++);
		return a;
	}
}
=======
package minimal; 
public class DoSomeAction {
    protected int a;

    int calc() {
        a = 3;
        return (a) / 4;
    }

    int calc2() {
        a = 0;
        while ((a) < 10)
            (a)++;
        return a;
    }

    int calc3() {
        a = 0;
        for (a = 0 ; (a) < 10 ; (a)++)
            ;
        return a;
    }
}
>>>>>>> cf0ffa856ca8f2c5ee970a91e4d11732ddfb1535
