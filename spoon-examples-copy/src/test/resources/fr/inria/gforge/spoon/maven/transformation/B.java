package org.mutation11.maven.transformation;

public class B extends A{
	int calc(){
		a=2;
		return a+2;
	}
	public static void main (String[] args){
		B b = new B();
		System.out.println(b.calc());
	}
}
