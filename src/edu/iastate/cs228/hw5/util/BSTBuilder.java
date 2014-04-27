package edu.iastate.cs228.hw5.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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

	private static final int LEVEL_0_COUNT = 1;

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

		validateLevels(levels);
		List<E> data = flatten(levels);
		validateNoDuplicates(data);
		
		return buildTree(data);
	}

	/*
	 * Checks to make sure, each level has the proper number of children for the
	 * number of parents
	 */
	private static void validateLevels(Object[][] levels) {

		// Check the root manually
		if (levels[0].length != LEVEL_0_COUNT) {
			invalidChildCount(0, levels[0]);
		}
		validateLevelsRec(1, 1, levels);
	}

	/*
	 * Recursively checks that the number of children is proper for the number
	 * of parents
	 */
	private static void validateLevelsRec(int curLevel, int parentCount, Object[][] levels) {
		// Success!
		if (curLevel >= levels.length) {
			return;
		}

		// Invalid case
		if (levels[curLevel] == null) {
			invalidChildCount(curLevel, levels[curLevel]);
		}

		// Check that the number of children is correct
		if (getProperChildCount(parentCount) != levels[curLevel].length) {
			invalidChildCount(curLevel, levels[curLevel]);
		}

		validateLevelsRec(curLevel + 1, countNonNulls(levels[curLevel]), levels);
	}
	
	private static void validateNoDuplicates(List<?> data) {
		if (data == null) {
			return;
		}
		Set<Object> found = new HashSet<>();
		for (Object da : data) {
			// Nulls will be duplicated
			if (da == null) {
				continue;
			}
			if (!found.contains(da)) {
				found.add(da);
				continue;
			}
			invalidDuplicate(da);
		}
	}

	// 2 children for every parent
	private static int getProperChildCount(int parentCount) {
		return parentCount * 2;
	}

	private static int countNonNulls(Object[] children) {
		int count = 0;
		for (Object o : children) {
			if (o != null) {
				++count;
			}
		}
		return count;
	}

	/*
	 * Converts the 2D array into a 1D list
	 */
	private static <E extends Comparable<? super E>> List<E> flatten(E[][] arr) {
		List<E> ret = new LinkedList<>();
		if (arr == null) {
			return ret;
		}

		for (E[] innerArr : arr) {
			if (innerArr == null) {
				continue;
			}
			ret.addAll(Arrays.asList(innerArr));
		}

		return ret;
	}

	/*
	 * Converts the level order list to a BST. Since a Level order traversal is
	 * created using a Queue (and not recursion), it makes sense to use a Queue
	 * to build the tree as well.
	 */
	private static <E extends Comparable<? super E>> BST<E> buildTree(List<E> eles) {
		// Create our starting point
		Node<E> root = new Node<>(eles.get(0));
		Queue<Node<E>> q = new LinkedList<>();
		q.offer(root);

		// Where we're getting the data
		int dataIndex = 1;
		while (!q.isEmpty()) {
			// The node we're building now
			Node<E> curNode = q.poll();

			Node<E> left = (dataIndex >= eles.size() || eles.get(dataIndex) == null) ? null : new Node<>(eles.get(dataIndex));
			Node<E> right = (dataIndex >= eles.size() || eles.get(dataIndex + 1) == null) ? null : new Node<>(eles.get(dataIndex + 1));

			validateLeft(curNode, left);
			validateRight(curNode, right);

			if (left != null) {
				curNode.setLeft(left);
				left.setParent(curNode);
				q.offer(left);
			}

			if (right != null) {
				curNode.setRight(right);
				right.setParent(curNode);
				q.offer(right);
			}

			dataIndex += 2;
		}

		return new BST<>(root, countNonNulls(eles.toArray()));
	}

	private static <E extends Comparable<? super E>> void validateLeft(Node<E> parent, Node<E> left) {
		if (left == null) {
			return;
		}

		if (left.getData().compareTo(parent.getData()) >= 0) {
			invalidLeft(parent.getData(), left.getData());
		}
	}

	private static <E extends Comparable<? super E>> void validateRight(Node<E> parent, Node<E> right) {
		if (right == null) {
			return;
		}

		if (right.getData().compareTo(parent.getData()) <= 0) {
			invalidRight(parent.getData(), right.getData());
		}
	}

	/*
	 * --------------------------------------------------------------------------
	 * Exceptions
	 * ----------------------------------------------------------------
	 */
	private static void invalidChildCount(int level, Object[] children) {
		throw new IllegalArgumentException(String.format("Invalid number of children for level: %d. Children were: %s", level,
				Arrays.toString(children)));
	}
	
	private static void invalidDuplicate(Object data) {
		throw new IllegalArgumentException(String.format("Found duplicated data: ", data));
	}

	private static void invalidLeft(Object parent, Object left) {
		throw new IllegalArgumentException(String.format("Tried to build an invalid BST! Parent: %s, Left: %s"));
	}

	private static void invalidRight(Object parent, Object right) {
		throw new IllegalArgumentException(String.format("Tried to build an invalid BST! Parent: %s, Right: %s"));
	}
}
