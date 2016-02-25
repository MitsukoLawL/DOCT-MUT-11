package org.mutation11.maven;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.UnaryOperatorKind;
import spoon.reflect.code.CtUnaryOperator;
import spoon.reflect.declaration.CtElement;

public class Op16 extends AbstractProcessor<CtElement> {
	@Override
	public boolean isToBeProcessed(CtElement candidate) {
		return candidate instanceof CtUnaryOperator;
	}

	@Override
	public void process(CtElement candidate) {
		if (!(candidate instanceof CtUnaryOperator)) {
			return;
		}
		CtUnaryOperator op = (CtUnaryOperator)candidate;
		if (op.getKind()==UnaryOperatorKind.POSTINC){
			op.setKind(UnaryOperatorKind.POSTDEC);
		} else if (op.getKind()==UnaryOperatorKind.PREINC){
			op.setKind(UnaryOperatorKind.PREDEC);
		}
		
	}
}
