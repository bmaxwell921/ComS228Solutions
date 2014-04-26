package edu.iastate.cs228.hw5.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	 * Builds a BST object with the given information. See the included README
	 * for detailed use.
	 * 
	 * @param levels
	 * @return The BST represented by the given List, or an empty tree if levels
	 *         is null or empty
	 * @throws IllegalArgumentException
	 *             If not enough information is provided
	 * @throws IllegalArgumentException
	 *             If you attempt to build a BST that violates BST ordering
	 * @throws IllegalArgumentException
	 *             If you have duplicate values in the given array
	 */
	public static <E extends Comparable<? super E>> BST<E> buildBST(E[][] levels) {
		// Empty tree
		if (levels == null || levels.length == 0) {
			return new BST<E>(null, 0);
		}

		if (hasDuplicates(levels)) {
			duplicateValues();
		}

		final Node<E> root = buildRoot(levels[0]);
		List<Node<E>> l = new ArrayList<>();
		l.add(root);

		buildTree(l, 1, levels);

		int size = countSize(levels);

		return new BST<>(root, size);
	}

	// Checks for dupliate values in the tree
	private static boolean hasDuplicates(Object[][] levels) {
		final Set<Object> found = new HashSet<>();
		for (Object[] level : levels) {
			if (level == null) {
				continue;
			}
			for (Object o : level) {
				if (o == null)
					continue;
				if (found.contains(o)) {
					return true;
				}
				found.add(o);
			}
		}
		return false;
	}

	/*
	 * Creates the root node
	 */
	private static <E extends Comparable<? super E>> Node<E> buildRoot(E[] level0) {
		if (level0 == null || !hasProperLevelCount(0, level0))
			throw new IllegalArgumentException("The input list has the wrong amount of elements for level 0.");
		return new Node<E>(level0[0]);
	}

	private static boolean hasProperChildCount(int numParents, Object[] children) {
		return children != null && children.length == (numParents * 2);
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

	/*
	 * Each parent should have all their children defined, even if they're null
	 */
	private static int getProperChildCount(int parentCount) {
		return parentCount * 2;
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
	private static <E extends Comparable<? super E>> void buildTree(List<Node<E>> prevNodes, int curLevel, E[][] levels) {
		// Base case
		if (curLevel == levels.length) {
			return;
		}

		// Error case
		if (!hasProperChildCount(prevNodes.size(), levels[curLevel])) {
			invalidChildCount(prevNodes, levels[curLevel]);
		}

		// If it's no an error, move on
		List<Node<E>> curNodes = buildCurrentLevel(prevNodes, curLevel, levels);

		// Recurse
		buildTree(curNodes, ++curLevel, levels);
	}

	/*
	 * Builds all the nodes for the current level. Returns an array of the nodes
	 * that were created.
	 */
	private static <E extends Comparable<? super E>> List<Node<E>> buildCurrentLevel(List<Node<E>> prevNodes, int curLevel, E[][] levels) {
		List<Node<E>> newNodes = new ArrayList<>();
		// Loop thru all the previous nodes
		int childNum = 0;
		for (Node<E> prevNode : prevNodes) {
			// Grab the data, post increment to update childNum too 
			E left = levels[curLevel][childNum++];
			E right = levels[curLevel][childNum++];

			// Check that it's not an invalid BST
			if (!(left == null || left != null && validLeft(left, prevNode.getData()))) {
				invalidBST(prevNode.getData(), left, right);
			}
			if (!(right == null || right != null && validRight(right, prevNode.getData()))) {
				invalidBST(prevNode.getData(), left, right);
			}

			// Add the new nodes
			Node<E> lNode = left != null ? new Node<E>(left) : null;
			Node<E> rNode = right != null ? new Node<E>(right) : null;
			linkInNodes(prevNode, lNode, rNode);

			// We only add non-null stuff so if one of prevNode's children are null, we just skip them later down the tree
			if (lNode != null) {
				newNodes.add(lNode);
			}
			if (rNode != null) {
				newNodes.add(rNode);
			}
		}
		return newNodes;
	}

	// Checks to make sure that left is < parent and right is > parent
	private static <E extends Comparable<? super E>> boolean validLeft(E left, E parent) {
		return left.compareTo(parent) < 0;
	}

	// Checks to make sure that left is < parent and right is > parent
	private static <E extends Comparable<? super E>> boolean validRight(E right, E parent) {
		return right.compareTo(parent) > 0;
	}

	// Sets up the links between parent and children
	private static <E extends Comparable<? super E>> void linkInNodes(Node<E> parent, Node<E> left, Node<E> right) {
		parent.setLeft(left);
		parent.setRight(right);
		if (left != null) {
			left.setParent(parent);
		}
		if (right != null) {
			right.setParent(parent);
		}
	}

	/*
	 * Counts all the non null elements in levels
	 */
	private static int countSize(Object[][] levels) {
		int size = 0;
		for (Object[] level : levels) {
			if (level == null) {
				continue;
			}
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
	private static void invalidChildCount(List<?> parents, Object[] children) {
		throw new IllegalArgumentException(String.format(
				"Invalid number of children for the number of parents. Was: %d, Expected: %d. Parents: %s, Children: %s", children.length,
				getProperChildCount(parents.size()), parents, Arrays.toString(children)));
	}

	private static void invalidBST(Object parent, Object left, Object right) {
		throw new IllegalArgumentException(
				String.format("Tried to build invalid BST! Parent: %s, Left: %s, Right: %s", parent, left, right));
	}

	private static void duplicateValues() {
		throw new IllegalArgumentException("Tried to make a BST with duplicate values");
	}
}
