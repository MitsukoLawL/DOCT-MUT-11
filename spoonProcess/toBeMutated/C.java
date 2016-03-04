package minimal; 

public class C {
	private static int a = 0;
	private int b;
	public C(){
		a++;
		this.b=0; 
	}
	
	public int getA(){
		return a;
	}
}
	