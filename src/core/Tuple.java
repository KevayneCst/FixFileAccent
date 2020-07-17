package core;

public class Tuple<A, B> {

	private A valueA;
	private B valueB;

	public Tuple(A a, B b) {
		this.valueA = a;
		this.valueB = b;
	}

	public A getA() {
		return valueA;
	}

	public B getB() {
		return valueB;
	}

	@Override
	public String toString() {
		return "Tuple [A:" + valueA + "][B:" + valueB + "]";
	}
}
