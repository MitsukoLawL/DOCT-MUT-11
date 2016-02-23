package org.mutation11.maven;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.declaration.CtElement;

/** a trivial mutation operator that transforms all binary operators to minus ("-") */
public class Op12 extends AbstractProcessor<CtElement> {
	@Override
	public boolean isToBeProcessed(CtElement candidate) {
		return candidate instanceof CtBinaryOperator;
	}

	@Override
	public void process(CtElement candidate) {
		if (!(candidate instanceof CtBinaryOperator)) {
			return;
		}
		CtBinaryOperator op = (CtBinaryOperator)candidate;

		int rand = (int) (Math.random()*3);
		System.out.println(rand);
		if (rand == 0) {
			op.setKind(BinaryOperatorKind.PLUS);
		} else if (rand == 1) {
			op.setKind(BinaryOperatorKind.MINUS);
		} else if (rand == 2) {
			op.setKind(BinaryOperatorKind.MUL);
		} else if (rand == 3) {
			op.setKind(BinaryOperatorKind.DIV);
		}
	}
}
