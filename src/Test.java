/*
 * Mudassir Mayet
 * #45462134
 * CS 141 - Project #2 - Part: I
 * 11:59pm, February 4, 2014
 */

public class Test {
	
	public static void main (String args[]) {
		
		A a = new A();
		GCSimulator.createObject("a", a);
		
		B b = new B();
		GCSimulator.createObject("b", b);
		
		B c = b;
		GCSimulator.assign("c", "b", b);
		
		c.f = a;
		GCSimulator.writeObject("c", "a", "f", c, a);
		
		P p = a.m;
		GCSimulator.readObject("p", "a", p);
		
		GCSimulator.gc();
	}

}
