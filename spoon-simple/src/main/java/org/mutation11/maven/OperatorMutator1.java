package org.mutation11.maven;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.ModifierKind;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtModifiable;

public class OperatorMutator1 extends AbstractProcessor<CtElement> {
	@Override
	public boolean isToBeProcessed(CtElement candidate) {
		return candidate instanceof CtModifiable;
	}

	@Override
	public void process(CtElement candidate) {
		if (!(candidate instanceof CtModifiable)) {
			return;
		}
		CtModifiable emo = (CtModifiable)candidate;
		emo.setVisibility(ModifierKind.PRIVATE);
	}
}
