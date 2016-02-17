package org.mutation11.maven.transformation;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import spoon.reflect.declaration.CtClass;
import org.mutation11.maven.OperatorMutator1;
import org.mutation11.maven.MutantNotKilledException;
import org.mutation11.maven.MutationTester;
import org.mutation11.maven.TestDriver;
import org.mutation11.maven.transformation.Racine;

public class MutationTesterTest {

	@Test
	public void testMutationTester() throws Exception {
		// mutation testing requires three things
		// 1. the code to be mutated
		// 2. the test driver to kill the mutants 
		// 3. the mutation operator
		String codeToBeMutated = "src/test/resources/org/mutation11/maven/transformation/A.java";
		
		TestDriver<Racine> testDriverForIFooObjects = new TestDriver<Racine>() {
			@Override
			public void test(Racine t) {

			}
		};
		
		OperatorMutator1 mutationOperator = new OperatorMutator1();

		// we instantiate the mutation tester
		MutationTester<Racine> mutationTester = new MutationTester<Racine>(codeToBeMutated, testDriverForIFooObjects, mutationOperator);
		
		// generating the mutants
		mutationTester.generateMutants();
		List<CtClass> mutants = mutationTester.getMutants();
		///assertEquals(2, mutants.size());
		
		// killing the mutants, no exception should be thrown
		try {
			mutationTester.killMutants();				
		} catch (MutantNotKilledException e) {
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
