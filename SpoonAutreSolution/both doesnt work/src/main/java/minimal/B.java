package minimal;

public class B {
	protected int a;
	int calc(){
		a=2;
		return a+2;
	}
	public static void main (String[] args){
		B b = new B();
		System.out.println(b.calc());
	}
}
