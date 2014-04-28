package edu.iastate.cs228.hw5.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import edu.iastate.cs228.hw5.BST;
import edu.iastate.cs228.hw5.Node;

/**
 * Class used to validate information about BSTs
 * 
 * @author Brandon
 * 
 */
public class BSTValidator {

	/**
	 * Checks whether the given BST has a cycle. Just checks if we find
	 * duplicate data since that's not allowed anyway.
	 * 
	 * @param tree
	 * @return
	 */
	public static <E extends Comparable<? super E>> boolean hasCycle(BST<E> tree) {
		// Whether we found duplicates or not
		final BoolBox b = new BoolBox(false);
		inorderRec(null, tree.getRoot(), new Visitor() {
			// Holds 'hashcode' of visited nodes
			private Set<Object> visited = new HashSet<>();

			@Override
			public void visit(Node<?> parent, Node<?> cur) {
				// Visit the node by setting it as visited
				if (visited.contains(cur.getData())) {
					b.bool = true;
					return;
				}
				visited.add(cur.getData());
			}
		});
		return b.bool;
	}

	/**
	 * Checks to make sure the references in the tree were properly set up
	 * 
	 * @param tree
	 * @return
	 */
	public static <E extends Comparable<? super E>> boolean hasProperRefs(BST<E> tree) {
		final BoolBox b = new BoolBox(true);
		inorderRec(null, tree.getRoot(), new Visitor() {
			@Override
			public void visit(Node<?> parent, Node<?> cur) {
				if (cur.getParent() != parent) {
					b.bool = false;
				}
			}
		});
		return b.bool;
	}

	private static <E extends Comparable<? super E>> void inorderRec(Node<E> parent, Node<E> root, Visitor v) {
		if (root == null) {
			return;
		}
		inorderRec(root, root.getLeft(), v);
		v.visit(parent, root);
		inorderRec(root, root.getRight(), v);
	}

	/**
	 * Checks to make sure the BST has all the given data in the proper place
	 * 
	 * @param levelBasedData
	 * @return
	 */
	public static boolean hasAllData(BST<?> tree, Object[][] levelBasedData) {
		if (levelBasedData == null || levelBasedData.length == 0) {
			return tree.getRoot() == null;
		}
		
		// Since it's a level order traversal of the data, we should just be able to look at it one by one...
		List<Object> flatData = flatten(levelBasedData);
		int curData = 0;
		
		Queue<Node<?>> q = new LinkedList<>();
		q.offer(tree.getRoot());

		while (!q.isEmpty()) {
			Node<?> cur = q.poll();
			// Last level can be left off, if all null
			Object check = (curData >= flatData.size()) ? null : flatData.get(curData++);
			// If it's not equal, then that's a problem
			if (!(cur == check || cur != null && cur.getData().equals(check))) {
				return false;
			}
			
			// otherwise, add cur's children. if there are any
			if (cur != null) {
				q.offer(cur.getLeft());
				q.offer(cur.getRight());
			}
		}
		
		return true;
	}
	
	// Converts the given 2D array into a 1D list
	private static List<Object> flatten(Object[][] data) {
		List<Object> flat = new ArrayList<>();
		if (data == null || data.length == 0) {
			return flat;
		}
		
		for (Object[] dataLine : data) {
			if (dataLine == null) {
				continue;
			}
			for (Object o : dataLine) {
				flat.add(o);
			}
		}
		return flat;
	}

	// Good ol math. Based on which level order
	// number the node is, we can figure out which level it's in
	private static int getLevelNum(int nodeCount) {
		return (int) (Math.log(nodeCount + 1) / Math.log(2));
	}

	// Interfaced defining tree traversal behavior
	private interface Visitor {
		// Visit current node
		public void visit(Node<?> parent, Node<?> cur);
	}

	// We need a box to use the Visitor appropriately
	private static class BoolBox {
		private boolean bool;

		public BoolBox(boolean bool) {
			this.bool = bool;
		}
	}

	/**
	 * Checks that the BST has the proper size
	 * 
	 * @param tree
	 * @param data
	 * @return
	 */
	public static boolean hasProperSize(BST<?> tree, Object[][] data) {
		if (data == null) {
			return tree.size() == 0;
		}
		int size = 0;
		for (Object[] levelData : data) {
			if (levelData == null) {
				continue;
			}
			for (Object d : levelData) {
				if (d != null) {
					++size;
				}
			}
		}

		return size == tree.size();
	}

}
