package org.mutation11.maven;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.declaration.CtElement;

import java.util.ArrayList;

public class Op13 extends AbstractProcessor<CtElement> {
	@Override
	public boolean isToBeProcessed(CtElement candidate) {
		if (candidate instanceof CtBinaryOperator) {
			ArrayList<BinaryOperatorKind> toMutated = new ArrayList<BinaryOperatorKind>();
			// Only operator we want to mute :
			toMutated.add(BinaryOperatorKind.AND); toMutated.add(BinaryOperatorKind.OR);

			CtBinaryOperator op = (CtBinaryOperator)candidate;

			// Return true if the operator is in on the list
			return toMutated.contains(op.getKind());
		} return false;
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
