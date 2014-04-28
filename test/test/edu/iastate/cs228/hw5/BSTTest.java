package test.edu.iastate.cs228.hw5;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import edu.iastate.cs228.hw5.BST;
import edu.iastate.cs228.hw5.Node;
import edu.iastate.cs228.hw5.TreeStructureException;
import edu.iastate.cs228.hw5.util.BSTBuilder;
import edu.iastate.cs228.hw5.util.BSTValidator;

/**
 * Class used to test my implementation of the BST class
 * 
 * @author Brandon
 * 
 */
public class BSTTest {

	// TODO add timeouts
	private static final int timeout = 2000;

	// The test tree
	private BST<String> tree;

	@Test
	public void testDefaultCons() {
		tree = new BST<>();
		testTree(new String[][] {});
	}

	@Test
	public void testSpecificCons_Empty() {
		tree = new BST<>(new String[] {});
		testTree(new String[][] {});
	}

	@Test
	public void testSpecificCons_Small() {
		tree = new BST<>(new String[] { "B", "A", "C" });
		testTree(new String[][] { { "B" }, { "A", "C" } });
	}

	@Test
	public void testSpecificCons_Medium() {
		tree = new BST<>(new String[] { "D", "B", "F", "A", "C", "E", "G" });
		testTree(new String[][] { { "D" }, { "B", "F" }, { "A", "C", "E", "G" } });
	}

	@Test
	public void testSpecificCons_Large() {
		tree = new BST<>(new String[] { "H", "D", "L", "B", "F", "J", "N", "A", "C", "E", "G", "I", "K", "M", "O" });
		testTree(new String[][] { { "H" }, { "D", "L" }, { "B", "F", "J", "N" }, { "A", "C", "E", "G", "I", "K", "M", "O" } });
	}

	@Test
	public void testCopyCons_Empty() throws TreeStructureException {
		Node<String> oRoot = null;
		tree = new BST<>(oRoot);
		testTree(new String[][] {});
	}

	@Test
	public void testCopyCons_Small() throws TreeStructureException {
		Node<String> oRoot = buildSmallTree().getRoot();
		tree = new BST<>(oRoot);
		testTree(new String[][] { { "B" }, { "A", "C" } });
	}

	@Test
	public void testCopyCons_Medium() throws TreeStructureException {
		Node<String> oRoot = buildMediumTree().getRoot();
		tree = new BST<>(oRoot);
		testTree(new String[][] { { "D" }, { "B", "F" }, { "A", "C", "E", "G" } });
	}

	@Test
	public void testCopyCons_Large() throws TreeStructureException {
		Node<String> oRoot = buildLargeTree().getRoot();
		tree = new BST<>(oRoot);
		testTree(new String[][] { { "H" }, { "D", "L" }, { "B", "F", "J", "N" }, { "A", "C", "E", "G", "I", "K", "M", "O" } });
	}

	@Test
	public void testCopyConsHoles_Medium() throws TreeStructureException {
		String[][] input = new String[][] { { "D" }, { null, "F" }, { null, "G" } };
		Node<String> oRoot = BSTBuilder.buildBST(input).getRoot();
		tree = new BST<>(oRoot);
		testTree(input);
	}

	@Test
	public void testCopyConsHoles_Large() throws TreeStructureException {
		String[][] input = new String[][] { { "H" }, { null, "L" }, { "J", null }, { "I", null } };
		Node<String> oRoot = BSTBuilder.buildBST(input).getRoot();
		tree = new BST<>(oRoot);
		testTree(input);
	}

	@Test(expected = TreeStructureException.class)
	public void testCopyConsNotBST_Small() throws TreeStructureException {
		Node<String> oRoot = new Node<>("A");
		Node<String> left = new Node<>("B");
		Node<String> right = new Node<>("C");

		oRoot.setLeft(left);
		oRoot.setRight(right);
		left.setParent(oRoot);
		right.setParent(oRoot);

		tree = new BST<>(oRoot);
	}

	@Test
	public void testHeight_Empty() {
		// DON'T PUT THIS IN THE REAL TESTS!
		tree = buildEmpty();
		Assert.assertEquals(-1, tree.height());
	}

	@Test
	public void testHeight_Small() {
		tree = buildSmallTree();
		Assert.assertEquals(1, tree.height());
	}

	@Test
	public void testHeight_Medium() {
		tree = buildMediumTree();
		Assert.assertEquals(2, tree.height());
	}

	@Test
	public void testHeight_Large() {
		tree = buildLargeTree();
		Assert.assertEquals(3, tree.height());
	}

	@Test
	public void testMin_Empty() {
		tree = buildEmpty();
		Assert.assertNull(tree.min());
	}

	@Test
	public void testMin_Small() {
		tree = buildSmallTree();
		Assert.assertEquals("A", tree.min());
	}

	@Test
	public void testMin_Medium() {
		tree = buildMediumTree();
		Assert.assertEquals("A", tree.min());
	}

	@Test
	public void testMin_Large() {
		tree = buildLargeTree();
		Assert.assertEquals("A", tree.min());
	}

	@Test
	public void testMax_Empty() {
		tree = buildEmpty();
		Assert.assertNull(tree.max());
	}

	@Test
	public void testMax_Small() {
		tree = buildSmallTree();
		Assert.assertEquals("C", tree.max());
	}

	@Test
	public void testMax_Medium() {
		tree = buildMediumTree();
		Assert.assertEquals("G", tree.max());
	}

	@Test
	public void testMax_Large() {
		tree = buildLargeTree();
		Assert.assertEquals("O", tree.max());
	}

	@Test
	public void testPreorderSeq_Empty() {
		ArrayList<String> seq = new ArrayList<>();
		tree = buildEmpty();
		tree.getPreorderSequence(seq);
		Assert.assertTrue(seq.isEmpty());
	}

	@Test
	public void testPreorderSeq_Small() {
		ArrayList<String> seq = new ArrayList<>();
		ArrayList<String> correct = new ArrayList<>(Arrays.asList(new String[] { "B", "A", "C" }));
		tree = buildSmallTree();
		tree.getPreorderSequence(seq);
		Assert.assertEquals(correct, seq);
	}

	@Test
	public void testPreorderSeq_Medium() {
		ArrayList<String> seq = new ArrayList<>();
		ArrayList<String> correct = new ArrayList<>(Arrays.asList(new String[] { "D", "B", "A", "C", "F", "E", "G" }));
		tree = buildMediumTree();
		tree.getPreorderSequence(seq);
		Assert.assertEquals(correct, seq);
	}

	@Test
	public void testPreorderSeq_Large() {
		ArrayList<String> seq = new ArrayList<>();
		ArrayList<String> correct = new ArrayList<>(Arrays.asList(new String[] { "H", "D", "B", "A", "C", "F", "E", "G", "L", "J", "I",
				"K", "N", "M", "O" }));
		tree = buildLargeTree();
		tree.getPreorderSequence(seq);
		Assert.assertEquals(correct, seq);
	}

	@Test
	public void testInorderSeq_Empty() {
		ArrayList<String> seq = new ArrayList<>();
		tree = buildEmpty();
		tree.getInorderSequence(seq);
		Assert.assertTrue(seq.isEmpty());
	}

	@Test
	public void testInorderSeq_Small() {
		ArrayList<String> seq = new ArrayList<>();
		ArrayList<String> correct = new ArrayList<>(Arrays.asList(new String[] { "A", "B", "C" }));
		tree = buildSmallTree();
		tree.getInorderSequence(seq);
		Assert.assertEquals(correct, seq);
	}

	@Test
	public void testInorderSeq_Medium() {
		ArrayList<String> seq = new ArrayList<>();
		ArrayList<String> correct = new ArrayList<>(Arrays.asList(new String[] { "A", "B", "C", "D", "E", "F", "G" }));
		tree = buildMediumTree();
		tree.getInorderSequence(seq);
		Assert.assertEquals(correct, seq);
	}

	@Test
	public void testInorderSeq_Large() {
		ArrayList<String> seq = new ArrayList<>();
		ArrayList<String> correct = new ArrayList<>(Arrays.asList(new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
				"L", "M", "N", "O" }));
		tree = buildLargeTree();
		tree.getInorderSequence(seq);
		Assert.assertEquals(correct, seq);
	}

	@Test
	public void testPostorderSeq_Empty() {
		ArrayList<String> seq = new ArrayList<>();
		tree = buildEmpty();
		tree.getPostorderSequence(seq);
		Assert.assertTrue(seq.isEmpty());
	}

	@Test
	public void testPostorderSeq_Small() {
		ArrayList<String> seq = new ArrayList<>();
		ArrayList<String> correct = new ArrayList<>(Arrays.asList(new String[] { "A", "C", "B" }));
		tree = buildSmallTree();
		tree.getPostorderSequence(seq);
		Assert.assertEquals(correct, seq);
	}

	@Test
	public void testPostorderSeq_Medium() {
		ArrayList<String> seq = new ArrayList<>();
		ArrayList<String> correct = new ArrayList<>(Arrays.asList(new String[] { "A", "C", "B", "E", "G", "F", "D" }));
		tree = buildMediumTree();
		tree.getPostorderSequence(seq);
		Assert.assertEquals(correct, seq);
	}

	@Test
	public void testPostorderSeq_Large() {
		ArrayList<String> seq = new ArrayList<>();
		ArrayList<String> correct = new ArrayList<>(Arrays.asList(new String[] { "A", "C", "B", "E", "G", "F", "D", "I", "K", "J", "M",
				"O", "N", "L", "H" }));
		tree = buildLargeTree();
		tree.getPostorderSequence(seq);
		Assert.assertEquals(correct, seq);
	}

	@Test
	public void testTraversePre_Empty() {
		String correct = "";
		tree = buildEmpty();
		Assert.assertEquals(correct, tree.traversePreorder().trim());
	}

	@Test
	public void testTraversePre_Small() {
		String correct = "B A C";
		tree = buildSmallTree();
		Assert.assertEquals(correct, tree.traversePreorder().trim());
	}

	@Test
	public void testTraversePre_Medium() {
		String correct = "D B A C F E G";
		tree = buildMediumTree();
		Assert.assertEquals(correct, tree.traversePreorder().trim());
	}

	@Test
	public void testTraversePre_Large() {
		String correct = "H D B A C F E G L J I K N M O";
		tree = buildLargeTree();
		Assert.assertEquals(correct, tree.traversePreorder().trim());
	}

	@Test
	public void testTraverseIn_Empty() {
		String correct = "";
		tree = buildEmpty();
		Assert.assertEquals(correct, tree.traverseInorder().trim());
	}

	@Test
	public void testTraverseIn_Small() {
		String correct = "A B C";
		tree = buildSmallTree();
		Assert.assertEquals(correct, tree.traverseInorder().trim());
	}

	@Test
	public void testTraverseIn_Medium() {
		String correct = "A B C D E F G";
		tree = buildMediumTree();
		Assert.assertEquals(correct, tree.traverseInorder().trim());
	}

	@Test
	public void testTraverseIn_Large() {
		String correct = "A B C D E F G H I J K L M N O";
		tree = buildLargeTree();
		Assert.assertEquals(correct, tree.traverseInorder().trim());
	}

	@Test
	public void testTraversePost_Empty() {
		String correct = "";
		tree = buildEmpty();
		Assert.assertEquals(correct, tree.traversePostorder().trim());
	}

	@Test
	public void testTraversePost_Small() {
		String correct = "A C B";
		tree = buildSmallTree();
		Assert.assertEquals(correct, tree.traversePostorder().trim());
	}

	@Test
	public void testTraversePost_Medium() {
		String correct = "A C B E G F D";
		tree = buildMediumTree();
		Assert.assertEquals(correct, tree.traversePostorder().trim());
	}

	@Test
	public void testTraversePost_Large() {
		String correct = "A C B E G F D I K J M O N L H";
		tree = buildLargeTree();
		Assert.assertEquals(correct, tree.traversePostorder().trim());
	}

	@Test
	public void testToString_Medium() {
		// Copied from BSTSet...why bother testing it much
		String regex = "+D+B-A-C+F-E-G";
		tree = buildMediumTree();
		Assert.assertTrue(tree.toString().replaceAll("\\s*", "").equals(regex));
	}

	@Test
	public void testSetEqualsTrue_Medium() {
		BST<String> one = BSTBuilder.buildBST(new String[][] { { "C" }, { "B", "D" }, { "A", null, null, "E" } });
		BST<String> two = BSTBuilder.buildBST(new String[][] { { "A" }, { null, "B" }, { null, "C" }, { null, "D" }, { null, "E" } });
		Assert.assertTrue(one.setEquals(two));
	}

	@Test
	public void testSetEqualsFalse_Medium() {
		BST<String> one = BSTBuilder.buildBST(new String[][] { { "C" }, { "B", "D" }, { "A", null, null, "E" } });
		BST<String> two = BSTBuilder.buildBST(new String[][] { { "D" }, { "C", "E" }, { "B", null, null, "F" } });
		Assert.assertFalse(one.setEquals(two));
	}
	
	@Test
	public void testRangeQueryMinNotFound_Medium() {
		tree = buildMediumTree();
		String[] correct = {"A", "B", "C", "D", "E", "F"};
		String[] found = new String[correct.length];
		int num = tree.rangeQuery("@", "F", found);
		
		Assert.assertArrayEquals(correct, found);
		Assert.assertEquals(correct.length, num);
	}
	
	@Test
	public void testRangeQueryMaxNotFound_Medium() {
		tree = buildMediumTree();
		String[] correct = {"C", "D", "E", "F", "G"};
		String[] found = new String[correct.length];
		int num = tree.rangeQuery("C", "[", found);
		
		Assert.assertArrayEquals(correct, found);
		Assert.assertEquals(correct.length, num);
	}
	
	@Test
	public void testRangeQueryFullTree_Medium() {
		tree = buildMediumTree();
		String[] correct = {"A", "B", "C", "D", "E", "F", "G"};
		String[] found = new String[correct.length];
		int num = tree.rangeQuery("A", "G", found);
		
		Assert.assertArrayEquals(correct, found);
		Assert.assertEquals(correct.length, num);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testRangeQueryInvalid_Medium() {
		tree = buildMediumTree();
		String[] correct = {"A", "B", "C", "D", "E", "F", "G"};
		String[] found = new String[correct.length];
		tree.rangeQuery("G", "A", found);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testOrderQueryMinTooSmall_Medium() {
		tree = buildMediumTree();
		tree.orderQuery(-5, 2, new String[] {});
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testOrderQueryMaxTooBig_Medium() {
		tree = buildMediumTree();
		tree.orderQuery(2, 5000000, new String[] {});
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testOrderQueryMinBiggerMax_Medium() {
		tree = buildMediumTree();
		tree.orderQuery(500, 2, new String[] {});
	}
	
	@Test
	public void testOrderQueryNormal_Medium() {
		tree = buildMediumTree();
		String[] correct = {"C", "D", "E"};
		String[] found = new String[correct.length];
		tree.orderQuery(2, 4, found);
		
		Assert.assertArrayEquals(correct, found);
	}
	
	@Test
	public void testEqualsTrue_Medium() {
		BST<String> one = buildMediumTree();
		BST<String> two = buildMediumTree();
		
		Assert.assertTrue(one.equals(two));
	}
	
	@Test
	public void testEqualsFalse_Medium() {
		BST<String> one = buildMediumTree();
		BST<String> two = buildLargeTree();
		
		Assert.assertFalse(one.equals(two));
	}
	
	@Test
	public void testPredecessorMin_Medium() {
		tree = buildMediumTree();
		// Navigate to the min
		Node<String> test = tree.getRoot();
		while (test.getLeft() != null) {
			test = test.getLeft();
		}
		
		Assert.assertNull(tree.predecessor(test));
	}
	
	@Test
	public void testPredecessorDownTree_Medium() {
		tree = buildMediumTree();
		// Navigate to the min
		Node<String> test = tree.getRoot();
		Assert.assertEquals("C", tree.predecessor(test).getData());
	}
	
	@Test
	public void testPredecessorUpTree_Medium() {
		tree = buildMediumTree();
		// "E" node
		Node<String> test = tree.getRoot().getRight().getLeft();
		Assert.assertEquals("D", tree.predecessor(test).getData());
	}

	private static BST<String> buildEmpty() {
		return BSTBuilder.buildBST(new String[][] {});
	}

	private static BST<String> buildSmallTree() {
		return BSTBuilder.buildBST(new String[][] { { "B" }, { "A", "C" } });
	}

	private static BST<String> buildMediumTree() {
		return BSTBuilder.buildBST(new String[][] { { "D" }, { "B", "F" }, { "A", "C", "E", "G" } });
	}

	private static BST<String> buildLargeTree() {
		return BSTBuilder.buildBST(new String[][] { { "H" }, { "D", "L" }, { "B", "F", "J", "N" },
				{ "A", "C", "E", "G", "I", "K", "M", "O" } });
	}

	private <E extends Comparable<? super E>> void testTree(E[][] data) {
		/*
		 * I'm not going to provide the BSTValidator class since it would give
		 * away homework answers, but you should be able to figure out what each
		 * method does by its name.
		 */
		Assert.assertFalse("BST should be created without cycles", BSTValidator.hasCycle(tree));
		Assert.assertTrue("BST should be created with the proper references", BSTValidator.hasProperRefs(tree));
		Assert.assertTrue("BST should be created with the proper size", BSTValidator.hasProperSize(tree, data));
		Assert.assertTrue("BST should be created with all the data in the right place", BSTValidator.hasAllData(tree, data));
	}
}
