package edu.iastate.cs228.hw5.util;

import java.util.Arrays;

import edu.iastate.cs228.hw5.BST;
import edu.iastate.cs228.hw5.Node;

/**
 * Utility class used to build BSTs
 * 
 * @author Brandon
 *
 */
public class BSTBuilder {

	/**
	 * Builds a BST with the given information. Levels is a 2d list,
	 * levels.get(0) should be a list of all the nodes at level 0 (i.e. just the
	 * root), levels.get(1) should have exactly 2 elements in it. If one element
	 * of levels.get(i) is null, that means it is an empty spot. The tree will
	 * be filled in from left to right, for all non null elements.
	 * 
	 * Note: This method will make sure you're only building valid BSTs
	 * 
	 * Examples: input: {{B}, {A, null}} output: B / A
	 * 
	 * input: {{C}, {null, E}, {D, F}} output: C \ E / \ E F Example usage:
	 * 
	 * @param levels
	 * @return The BST represented by the given List, or an empty tree is levels
	 *         is null or empty
	 */
	public static <E extends Comparable<? super E>> BST<E> buildBST(E[][] levels) {
		// Empty tree
		if (levels == null || levels.length == 0) {
			return new BST<E>(null, 0);
		}

		Node<E> root = buildRoot(levels[0]);
		buildTree((Node<E>[]) new Object[] { root }, 1, levels);

		int size = countSize(levels);

		return new BST<>(root, size);
	}

	/*
	 * Creates the root node
	 */
	private static <E extends Comparable<? super E>> Node<E> buildRoot(
			E[] level0) {
		if (level0 == null || !hasProperLevelCount(0, level0))
			throw new IllegalArgumentException(
					"The input list has the wrong amount of elements for level 0.");
		return new Node<E>(level0[0]);
	}

	/*
	 * Checks whether the given list has the proper number of elements for that
	 * level.
	 */
	private static boolean hasProperLevelCount(int level, Object[] eles) {
		return eles != null && eles.length == getProperLevelCount(level);
	}

	/*
	 * Returns how many elements would be in the full tree at the given level.
	 * I.e. level 0 has 1 node, 1 has 2 nodes, 2 has 4 nodes, etc
	 */
	private static int getProperLevelCount(int level) {
		return (int) Math.pow(2, level);
	}

	/**
	 * Builds the tree recursively, with the given nodes
	 * 
	 * @param previousNodes
	 *            All the nodes (including nulls) from the previous level
	 * @param curLevel
	 *            Which index our current level is at
	 * @param levels
	 *            All the levels to build from
	 */
	private static <E extends Comparable<? super E>> void buildTree(
			Node<E>[] prevNodes, int curLevel, E[][] levels) {
		// Base case
		if (curLevel == levels.length) {
			return;
		}

		// Error case:
		if (!hasProperLevelCount(curLevel, levels[curLevel])) {
			// Throw exception
			invalidLevelCount(curLevel, levels[curLevel]);
		}

		// If it's no an error, move on
		Node<E>[] curNodes = buildCurrentLevel(prevNodes, curLevel, levels);

		// Recurse
		buildTree(curNodes, ++curLevel, levels);
	}

	/*
	 * Builds all the nodes for the current level. Returns an array of the nodes
	 * that were created.
	 */
	private static <E extends Comparable<? super E>> Node<E>[] buildCurrentLevel(
			Node<E>[] prevNodes, int curLevel, E[][] levels) {
		Node<E>[] curNodes = (Node<E>[]) new Object[getProperLevelCount(curLevel)];
		// Loop thru all the previous nodes
		for (Node<E> prevNode : prevNodes) {
			// And go thru all the data. We'll manually update i in the body
			for (int i = 0; i < levels[curLevel].length;) {
				// If the previous node was null, add two
				// null children for it, but don't link them in
				if (prevNode == null) {
					curNodes[i++] = null;
					curNodes[i++] = null;
					continue;
				}

				// Otherwise we need to link in the children
				E left = levels[curLevel][i];
				E right = levels[curLevel][i + 1];

				if (!validBST(left, right, prevNode.getData())) {
					// Throw exception
					invalidBST(prevNode.getData(), left, right);
				}

				// Add the new nodes
				Node<E> lNode = new Node<E>(left);
				Node<E> rNode = new Node<E>(right);
				linkInNodes(prevNode, lNode, rNode);
				curNodes[i++] = lNode;
				curNodes[i++] = rNode;
			}
		}
		return curNodes;
	}

	// Checks to make sure that left is < parent and right is > parent
	private static <E extends Comparable<? super E>> boolean validBST(E left,
			E right, E parent) {
		return left.compareTo(parent) < 0 && right.compareTo(parent) > 0;
	}

	// Sets up the links between parent and children
	private static <E extends Comparable<? super E>> void linkInNodes(
			Node<E> parent, Node<E> left, Node<E> right) {
		parent.setLeft(left);
		parent.setRight(right);
		left.setParent(parent);
		right.setParent(parent);
	}

	/*
	 * Counts all the non null elements in levels
	 */
	private static int countSize(Object[][] levels) {
		int size = 0;
		for (Object[] level : levels) {
			for (Object obj : level) {
				if (obj != null) {
					++size;
				}
			}
		}
		return size;
	}

	/*
	 * --------------------------------------------------------------------------
	 * Exceptions
	 * ----------------------------------------------------------------
	 */
	private static void invalidLevelCount(int level, Object[] data) {
		throw new IllegalArgumentException(String.format(
				"Invalid level count for level: %d. Values: %s", level,
				Arrays.toString(data)));
	}

	private static void invalidBST(Object parent, Object left, Object right) {
		throw new IllegalArgumentException(String.format(
				"Tried to build invalid BST! Parent: %s, Left: %s, Right: %s",
				parent, left, right));
	}
}
