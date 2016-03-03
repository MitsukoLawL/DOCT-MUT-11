package org.mutation11.maven;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.declaration.CtElement;

public class Op13 extends AbstractProcessor<CtElement> {
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

		if (op.getKind().equals(BinaryOperatorKind.OR)) {
			op.setKind(BinaryOperatorKind.AND);
		} else if (op.getKind().equals(BinaryOperatorKind.AND)) {
			op.setKind(BinaryOperatorKind.OR);
		}

	}
}
