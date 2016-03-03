package org.mutation11.maven;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.declaration.CtElement;

import java.util.ArrayList;

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

		ArrayList<BinaryOperatorKind> toMutated = new ArrayList<BinaryOperatorKind>();
		// Only operator we want to mute :
		toMutated.add(BinaryOperatorKind.PLUS); toMutated.add(BinaryOperatorKind.MINUS);
		toMutated.add(BinaryOperatorKind.MUL); toMutated.add(BinaryOperatorKind.DIV);

		if (!toMutated.contains(op.getKind())) {
			return;
		}

		System.out.println("coucou");
		int rand = (int) (Math.random()*3);
		if (rand == 0) {
//			op.setKind(BinaryOperatorKind.PLUS);
			op.setKind(BinaryOperatorKind.MINUS);
		} else if (rand == 1) {
			op.setKind(BinaryOperatorKind.MINUS);
		} else if (rand == 2) {
			op.setKind(BinaryOperatorKind.MUL);
		} else if (rand == 3) {
			op.setKind(BinaryOperatorKind.DIV);
		}
	}
}
