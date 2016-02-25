package minimal;

public class DoSomeAction {
	protected int a;
	int calc(){
		a=3;
		return a+4;
	}
	int calc2(){
		a = 0;
		a++;
		while (a<10) a++;
		return a;
	}
	int calc3(){
		a = 0;
		++a;
		for (a=0; a<10; a++);
		return a;
	}
}
