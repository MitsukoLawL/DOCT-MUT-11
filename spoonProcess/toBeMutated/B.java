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
