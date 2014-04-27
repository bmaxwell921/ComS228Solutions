package test.edu.iastate.cs228.hw5;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import edu.iastate.cs228.hw5.BST;
import edu.iastate.cs228.hw5.util.BSTBuilder;

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
	public void testHeight_Empty() {
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
}
