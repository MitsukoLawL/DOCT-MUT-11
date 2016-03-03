package org.mutation11.maven;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.mdkt.compiler.InMemoryJavaCompiler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import spoon.Launcher;
import spoon.processing.Processor;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtElement;

//import spoon.support.reflect.declaration.CtFieldImpl;
import spoon.reflect.declaration.CtField;
import spoon.reflect.visitor.Filter;
import spoon.reflect.visitor.filter.TypeFilter;

/** mutates and kills mutants of type T.
 *
 */
public class MutationTester<T> {

	/** the content of the Java source code file to be mutated */
	private String sourceCodeToBeMutated;
	/** responsible for killing the mutants */

	/** mutation operator */
	private Processor mutator;

	/** the produced mutants */
	private final List<CtClass> mutants = new ArrayList<CtClass>();

	/** for the otherCalss that should be compiled */


	// public for testing
	public final List<T> mutantInstances = new ArrayList<T>();

	public MutationTester(String src, Processor mutator) {
		this.sourceCodeToBeMutated = src;
		//this.testDriver = tester;
		this.mutator = mutator;
//		this.otherClass = otherClass;
	}


	/** returns a list of mutant classes */
	public void generateMutants() {
		Launcher l = new Launcher();

		/** get File on a folder **/
		String theFolder = sourceCodeToBeMutated;
		File folder = new File(theFolder);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
//                convertTheJava("toBeMutated/"+listOfFiles[i].getName(), Integer.parseInt(args[0]));
				l.addInputResource(theFolder + listOfFiles[i].getName());
				System.out.println(theFolder + listOfFiles[i].getName());
//                convertTheJava("toBeMutated/A.java");
			}
		}
//		l.addInputResource(sourceCodeToBeMutated);
		l.buildModel();
		for (int i = 0; i < listOfFiles.length; i++) {

			CtClass origClass = (CtClass) l.getFactory().Package().getRootPackage()
					.getElements(new TypeFilter(CtClass.class)).get(i);
			System.out.println(origClass.toString());

			// now we apply a transformation
			List<CtElement> elementsToBeMutated = origClass.getElements(new Filter<CtElement>() {

				@Override
				public boolean matches(CtElement arg0) {
					return mutator.isToBeProcessed(arg0);
				}
			});

			// selecteur
			// Recuperer qu'une partie de la liste elementsToBeMutated
//			elementsToBeMutated = selecteurs(elementsToBeMutated, 100);

			// elementsToBeMutated = liste des elements que l'on va transformer
			for (CtElement e : elementsToBeMutated) {
				// this loop is the trickiest part
				// because we want one mutation after the other

				// cloning the AST element
				CtElement op = l.getFactory().Core().clone(e);

				// mutate the element
				mutator.process(op);

				// temporarily replacing the original AST node with the mutated element
				replace(e, op);

				// creating a new class containing the mutating code
				CtClass klass = l.getFactory().Core().clone(op.getParent(CtClass.class));
				// setting the package
				klass.setParent(origClass.getParent());

				// adding the new mutant to the list
				mutants.add(klass);
				System.out.println(klass.toString());


				// restoring the original code - ici commente pour appliquer la mutation à TOUS le fichier
				// Si on decommente, il applique une mutation par fichier
				//replace(op, e);
			}
		}
	}

	/**
	 * Input all mutations to do
	 * Out a percent of mutations to do
	 */
	public List<CtElement> selecteurs(List<CtElement> liste, int percent /* args[2]*/) {
		Random random = new Random();
		int tailleListe = liste.size();

		List<CtElement> newList = new ArrayList<CtElement>();

		for (int i=0; (i< tailleListe*percent/100 && !liste.isEmpty()) /*percent*/; i++) {
			int index = random.nextInt(liste.size());
			CtElement element = liste.get(index);
			newList.add(element);
			liste.remove(element);
		}

		return newList;
	}

	public List<CtClass> getMutants() {
		return Collections.unmodifiableList(mutants);
	}

	private void replace(CtElement e, CtElement op) {
		if (e instanceof CtStatement  && op instanceof CtStatement) {
			((CtStatement)e).replace((CtStatement) op);
			return;
		}
		if (e instanceof CtExpression && op instanceof CtExpression) {
			((CtExpression)e).replace((CtExpression) op);
			return;
		}

		 if (e instanceof CtField && op instanceof CtField) {
		 	//System.out.println(((CtField)e).toString()+" "+((CtField) op).toString());

		 	((CtField)e).replace((CtField) op);
		 	return;
		 }

		System.out.println(e.toString()+" "+op.toString());
		throw new IllegalArgumentException(e.getClass()+" "+op.getClass());
	}

	/** tries to kill all generated mutants, throws an AssertionError if one mutant is not killed */
	public void killMutants()  throws Exception{

		try{

			List<Class> mutedJava = muteJava(mutants);
		} catch (Exception expected) {
			// System.out.println("mutant killed!");
		}
//		runTestsOnEachMutantInstance(mutantInstances);


	}

	/** applies the test driver of this mutation tester on each mutant instance */
//	public void runTestsOnEachMutantInstance(List<T> mutantInstances) throws Exception {
//		// now we run the mutants against the test class
//		for (T t : mutantInstances) {
//			try {
//				testDriver.test(t);
//				throw new MutantNotKilledException();
//			} catch (AssertionError expected) {
//				System.out.println("mutant killed!");
//			}
//		}
//	}

	/** instantiate the mutant classes using the default zero-arg constructor */
	public List<T> instantiateMutants(List<Class> mutedJava)
			throws Exception {
		// we run each mutant one by one and check whether they are killed

		// instantiating the mutant classes
		for (Class mutantClass : mutedJava) {
			mutantInstances.add((T) mutantClass.newInstance());
		}
		return mutantInstances;
	}

	/** compiles the mutants on the fly */
	public List<Class> muteJava(List<CtClass> mutants) throws Exception {
		List<Class> mutedJava = new ArrayList<Class>();
		for (CtClass mutantClass : mutants) {
//			File arquivo = new File("target/generated-sources/"+mutantClass.getSimpleName()+".java");

			// chemin du fichier dans l'arborescence
			String chemin = mutantClass.getPackage().getQualifiedName();
			chemin = chemin.replace(".","/");

//			File arquivo = new File("../mutatedCode/src/main/java/minimal/"+mutantClass.getSimpleName()+".java");
			File arquivo = new File("../mutatedCode/src/main/java/"+chemin+"/"+mutantClass.getSimpleName()+".java");
			try{
				FileWriter fw = new FileWriter(arquivo);
			    fw.write("package " + mutantClass.getPackage().getQualifiedName() + "; \n" + mutantClass.toString());
			    fw.flush();
			}catch(IOException ex){
			  ex.printStackTrace();
			}
//			Class<?> klass = InMemoryJavaCompiler.compile(
//					mutantClass.getQualifiedName(), "package "
//							+ mutantClass.getPackage().getQualifiedName() + ";"
//							+ mutantClass);
//			mutedJava.add(klass);
		}
		return mutedJava;
	}

}
