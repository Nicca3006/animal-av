package generators.maths.fixpointinteration.mathterm.operators;

public class Power extends Operator {

	@Override
	public Character getOperatorToken() {
		return '^';
	}

	@Override
	public Double evaluateOperator(Double x) {
		return Math.pow(left.evaluate(x), right.evaluate(x));
	}

	@Override
	public int getPriority() {
		return 3;
	}

}
