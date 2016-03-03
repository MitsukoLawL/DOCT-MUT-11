package org.mutation11.maven;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.ModifierKind;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtModifiable;
import spoon.reflect.declaration.CtVariable;

public class Op9 extends AbstractProcessor<CtElement> {
	@Override
	public boolean isToBeProcessed(CtElement candidate) {
		if (candidate instanceof CtModifiable){
			CtModifiable emo = (CtModifiable)candidate;
			//System.out.println(emo.getVisibility());
			return emo.hasModifier(ModifierKind.STATIC); 
		} else return false;		
	}

	@Override
	public void process(CtElement candidate) {
		if (!(candidate instanceof CtModifiable)) {
			return;
		}
		CtModifiable emo = (CtModifiable)candidate;
		if (!emo.hasModifier(ModifierKind.STATIC)) {

			return;
		}
	
		emo.removeModifier(ModifierKind.STATIC);
	}
}
