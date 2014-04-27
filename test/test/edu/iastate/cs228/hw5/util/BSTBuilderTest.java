package test.edu.iastate.cs228.hw5.util;

import org.junit.Assert;
import org.junit.Test;

import edu.iastate.cs228.hw5.BST;
import edu.iastate.cs228.hw5.util.BSTBuilder;
import edu.iastate.cs228.hw5.util.BSTValidator;

/**
 * Tests to validate the BSTBuilder class
 * 
 * @author Brandon
 * 
 */
public class BSTBuilderTest {

	private static final int timeout = 200000;

	/*
	 * Naming: Small tree - Depth 1 Medium tree - Depth 2 Large tree - Depth 3
	 * 
	 * Implicit - Don't provide the last level of all nulls Explicit - Do
	 * provide last level of all nulls
	 */

	@Test(timeout = timeout)
	public void testNull() {
		buildAndTest(null);
	}

	@Test(timeout = timeout)
	public void testEmpty() {
		buildAndTest(new String[][] {});
	}

	@Test(timeout = timeout)
	public void testRoot_Implicit() {
		buildAndTest(new String[][] { { "A" } });
	}

	@Test(timeout = timeout)
	public void testRoot_Explicit() {
		buildAndTest(new String[][] { { "A" }, { null, null } });
	}

	@Test(timeout = timeout, expected = IllegalArgumentException.class)
	public void testRootNotEnoughData() {
		buildAndTest(new String[][] { { "A" }, { null } });
	}

	@Test(timeout = timeout)
	public void testFullTreeSmall_Implicit() {
		buildAndTest(new String[][] { { "B" }, { "A", "C" } });
	}

	@Test(timeout = timeout)
	public void testFullTreeSmall_Explicit() {
		buildAndTest(new String[][] { { "B" }, { "A", "C" }, { null, null, null, null } });
	}

	@Test(timeout = timeout)
	public void testFullTreeMedium_Implicit() {
		buildAndTest(new String[][] { { "D" }, { "B", "F" }, { "A", "C", "E", "G" } });
	}

	@Test(timeout = timeout)
	public void testFullTreeMedium_Explicit() {
		buildAndTest(new String[][] { { "D" }, { "B", "F" }, { "A", "C", "E", "G" }, { null, null, null, null, null, null, null, null } });
	}

	@Test(timeout = timeout)
	public void testFullTreeLarge_Implicit() {
		buildAndTest(new String[][] { { "H" }, { "D", "L" }, { "B", "F", "J", "N" }, { "A", "C", "E", "G", "I", "K", "M", "O" } });
	}

	@Test(timeout = timeout)
	public void testFullTreeLarge_Explicit() {
		buildAndTest(new String[][] { { "H" }, { "D", "L" }, { "B", "F", "J", "N" }, { "A", "C", "E", "G", "I", "K", "M", "O" },
				{ null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null } });
	}

	@Test(timeout = timeout)
	public void testSingleHoleTreeSmall_Implicit() {
		buildAndTest(new String[][] { { "B" }, { null, "C" } });
	}

	@Test(timeout = timeout)
	public void testSingleHoleTreeSmall_Explicit() {
		buildAndTest(new String[][] { { "B" }, { null, "C" }, { null, null } });
	}

	@Test(timeout = timeout)
	public void testSingleHoleTreeMedium_Implicit() {
		buildAndTest(new String[][] { { "D" }, { "B", null }, { "A", "C" } });
	}

	@Test(timeout = timeout)
	public void testSingleHoleTreeMedium_Explicit() {
		buildAndTest(new String[][] { { "D" }, { "B", null }, { "A", "C" }, { null, null, null, null } });
	}

	@Test(timeout = timeout)
	public void testSingleHoleTreeLarge_Implicit() {
		buildAndTest(new String[][] { { "H" }, { "D", "L" }, { "B", null, "J", "N" }, { "A", "C", "I", "K", "M", "O" } });
	}

	@Test(timeout = timeout)
	public void testSingleHoleTreeLarge_Explicit() {
		buildAndTest(new String[][] { { "H" }, { "D", "L" }, { "B", null, "J", "N" }, { "A", "C", "I", "K", "M", "O" },
				{ null, null, null, null, null, null, null, null, null, null, null, null } });
	}
	
	// No tests for multi-hole small trees since they're just roots
	
	@Test(timeout = timeout)
	public void testMultiHoleTreeMedium_Implicit() {
		buildAndTest(new String[][] {{"D"}, {null, "F"}, {null, "G"}});
	}
	
	@Test(timeout = timeout)
	public void testMultiHoleTreeMedium_Explicit() {
		buildAndTest(new String[][] {{"D"}, {null, "F"}, {null, "G"}, {null, null}});
	}
	
	@Test(timeout = timeout)
	public void testMultiHoleTreeLarge_Implicit() {
		buildAndTest(new String[][] {{"H"}, {null, "L"}, {"J", null}, {"I", null}});
	}
	
	@Test(timeout = timeout)
	public void testMultiHoleTreeLarge_Explicit() {
		buildAndTest(new String[][] {{"H"}, {null, "L"}, {"J", null}, {"I", null}, {null, null}});
	}

	private static <E extends Comparable<? super E>> void buildAndTest(E[][] data) {
		BST<E> tree = BSTBuilder.buildBST(data);
		Assert.assertFalse("BST should be created without cycles", BSTValidator.hasCycle(tree));
		Assert.assertTrue("BST should be created with the proper references", BSTValidator.hasProperRefs(tree));
		Assert.assertTrue("BST should be created with the proper size", BSTValidator.hasProperSize(tree, data));
		Assert.assertTrue("BST should be created with all the data", BSTValidator.hasAllData(tree, data));
	}

}
