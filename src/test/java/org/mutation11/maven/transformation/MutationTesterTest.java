package org.mutation11.maven.transformation;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;
import org.eclipse.jdt.internal.compiler.batch.Main;
import spoon.Launcher;
import spoon.reflect.declaration.CtClass;
import org.mutation11.maven.OperatorMutator1;
import org.mutation11.maven.MutantNotKilledException;
import org.mutation11.maven.MutationTester;
import org.mutation11.maven.TestDriver;
import org.mutation11.maven.transformation.Racine;

import java.io.PrintWriter;

import static org.junit.Assert.assertTrue;

public class MutationTesterTest {

	@Test
	public void testMutationTester() throws Exception {
		// mutation testing requires three things
		// 1. the code to be mutated
		// 2. the test driver to kill the mutants 
		// 3. the mutation operator
		String codeToBeMutated = "src/test/resources/org/mutation11/maven/transformation/A.java";
		List<String> otherClass = new ArrayList<String>();
		otherClass.add("src/test/resources/org/mutation11/maven/transformation/B.java");
		
		TestDriver<Racine> testDriverForIFooObjects = new TestDriver<Racine>() {
			@Override
			public void test(Racine t) {
				
				// final String[] args = {
				// 	"-i", "target/generated-sources/",
				// 	"-o", "target/generated-sources/",
				// 	"-p", "org.mutation11.maven.transformation.testMutationTester"
				// };

				//final Launcher launcher = new Launcher();
				//launcher.setArgs(args);
				assertTrue(Main.compile(Main.tokenize("-1.6 target/generated-sources/"), new PrintWriter(System.out), new PrintWriter(System.err), null));	

			}
		};
		
		OperatorMutator1 mutationOperator = new OperatorMutator1();

		// we instantiate the mutation tester
		MutationTester<Racine> mutationTester = new MutationTester<Racine>(codeToBeMutated, testDriverForIFooObjects, mutationOperator,otherClass);
		
		// generating the mutants
		mutationTester.generateMutants();
		List<CtClass> mutants = mutationTester.getMutants();
		///assertEquals(2, mutants.size());
		
		// killing the mutants, no exception should be thrown
		try {
			mutationTester.killMutants();				
		} catch (MutantNotKilledException e) {
			System.out.println("mutant NOT KILLED");
			Assert.fail();
		}
		
		// another couple of assertions for testing that mutants are actually mutants
		// // testing the first mutant
		// // 1-1 = 0
		// assertEquals(0, mutationTester.mutantInstances.get(0).m());
		// assertEquals(6, mutationTester.mutantInstances.get(0).n());
		
		// // testing the second mutant
		// assertEquals(2, mutationTester.mutantInstances.get(1).m());
		// // 2-3 = -1
		// assertEquals(-1, mutationTester.mutantInstances.get(1).n());
	}
}
