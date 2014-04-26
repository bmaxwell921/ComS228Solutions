package test.edu.iastate.cs228.hw5.util;

import org.junit.Assert;
import org.junit.Test;

import edu.iastate.cs228.hw5.BST;
import edu.iastate.cs228.hw5.util.BSTBuilder;
import edu.iastate.cs228.hw5.util.BSTValidator;

/**
 * Tests to validate the BSTBuilder class
 * @author Brandon
 *
 */
public class BSTBuilderTest {
	
	private static final int timeout = 200000;
	
	
	@Test (timeout = timeout)
	public void testNull() {
		buildAndTest(null);
	}
	
	@Test (timeout = timeout)
	public void testEmpty() {
		buildAndTest(new String[][] {});
	}
	
	@Test (timeout = timeout)
	public void testJustRoot() {
		// Not required to provide data for the last level
		buildAndTest(new String[][] {{"A"}});
	}
	
	@Test (timeout = timeout)
	public void testRootWithNull() {
		buildAndTest(new String[][] {{"A"}, {null, null}});
	}
	
	@Test (timeout = timeout, expected = IllegalArgumentException.class) 
	public void testNotEnoughInfoSmall() {
		buildAndTest(new String[][] {{"A"}, {null}});
	}
	
	@Test (timeout = timeout)
	public void testEmptySpotMoreChildren() {
		// Can provide all data for last level
		buildAndTest(new String[][] {{"A"}, {null, "C"}, {"B", null}, {null, null}});
	}
	
	@Test (timeout = timeout)
	public void testEmptySpotNotEnoughChildren() {
		// Not required to provide data for last level
		buildAndTest(new String[][] {{"A"}, {null, "C"}, {"B", null}});
	}
	
	@Test (timeout = timeout)
	public void test() {
		buildAndTest(new String[][] {{"B"}, {null, "D"}, {null, null}});
	}
	
	private static <E extends Comparable<? super E>> void buildAndTest(E[][] data) {
		BST<E> tree = BSTBuilder.buildBST(data);
		Assert.assertFalse("BST should be created without cycles", BSTValidator.hasCycle(tree));
		Assert.assertTrue("BST should be created with the proper references", BSTValidator.hasProperRefs(tree));
		Assert.assertTrue("BST should be created with the proper size", BSTValidator.hasProperSize(tree, data));
		Assert.assertTrue("BST should be created with all the data", BSTValidator.hasAllData(tree, data));
	}

}
