package org.mutation11.maven;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.ModifierKind;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtModifiable;
import spoon.reflect.declaration.CtVariable;

public class Op1 extends AbstractProcessor<CtElement> {
	@Override
	public boolean isToBeProcessed(CtElement candidate) {
		//return candidate instanceof CtModifiable;
		if (candidate instanceof CtModifiable){
			CtModifiable emo = (CtModifiable)candidate;
			return emo instanceof CtVariable && emo.getVisibility()==ModifierKind.PROTECTED;
		} else return false;
	}

	@Override
	public void process(CtElement candidate) {
		if (!(candidate instanceof CtModifiable)) {
			return;
		}
		CtModifiable emo = (CtModifiable)candidate;
		if (!(emo instanceof CtVariable) || emo.getVisibility()!=ModifierKind.PROTECTED) {
			return;
		}
		emo.setVisibility(ModifierKind.PRIVATE);
	}
}
