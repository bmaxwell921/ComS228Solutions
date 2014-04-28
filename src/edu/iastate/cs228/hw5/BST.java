package edu.iastate.cs228.hw5;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Binary search tree implementation
 * 
 * @author Brandon
 * 
 * @param <E>
 */
public class BST<E extends Comparable<? super E>> extends AbstractSet<E> {
	private Node<E> root;
	private int size;

	private ArrayList<E> preorderArr; // stores the key values from a preorder
										// traversal
	private ArrayList<E> inorderArr; // stores the key values from an inorder
										// traversal
	private ArrayList<E> postorderArr; // stores the key values from a postorder
										// traversal

	/*
	 * These tags will be set to false respectively at the ends of calls to
	 * traversePreorder(), traverseInorder(), and traversePostorder(). They must
	 * be set back to true whenever the binary search tree is modified by add(),
	 * remove(), leftRotate(), and rightRotate().
	 */
	private boolean redoPreorder = true;
	private boolean redoInorder = true;
	private boolean redoPostorder = true;

	// ------------
	// Constructors
	// ------------

	/**
	 * Default constructor builds an empty tree.
	 */
	public BST() {
		this((E[]) new Comparable[0]);
	}

	/**
	 * Constructor from an existing tree (manually set up for testing)
	 * 
	 * @param root
	 * @param size
	 */
	public BST(Node<E> root, int size) {
		this.root = root;
		this.size = size;
		this.preorderArr = new ArrayList<E>();
		this.inorderArr = new ArrayList<E>();
		this.postorderArr = new ArrayList<E>();
	}

	/**
	 * Constructor over an element array. Elements must be inserted sequentially
	 * in order of increasing index from the array.
	 * 
	 * @param eleArray
	 */
	public BST(E[] eleArray) {
		this.root = null;
		this.size = 0;

		this.preorderArr = new ArrayList<>();
		this.inorderArr = new ArrayList<>();
		this.postorderArr = new ArrayList<>();

		addAll(Arrays.asList(eleArray));
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		for (E data : c) {
			add(data);
		}
		return true;

	}

	/**
	 * Copy constructor. It takes a binary tree with a given root as input, and
	 * calls isBST() to check if it is indeed a binary search tree. If not,
	 * throws a TreeStructureException with the message
	 * "Copying a non-BST tree". If so, makes a deep copy of the input tree such
	 * that the resulting BST assumes the same structure and has the same key
	 * stored at every corresponding node.
	 * 
	 * @param rt
	 *            root of an existing binary tree
	 */
	public BST(Node<E> root) throws TreeStructureException {
		if (!isBST(root)) {
			throw new TreeStructureException("Copying a non-BST tree");
		}
		deepCopy(root);
	}

	private void deepCopy(Node<E> root) {
		// Quit fast for empty trees
		if (root == null) {
			return;
		}
		// We're gonna go level by level
		Queue<Node<E>> them = new LinkedList<>();
		Queue<Node<E>> us = new LinkedList<>();

		them.offer(root);
		this.root = new Node<E>(root.getData());
		us.offer(this.root);

		// Make sure to set up the size properly
		this.size = 1;

		while (!them.isEmpty()) {
			// Get each node to work with
			Node<E> theirNode = them.poll();
			Node<E> ourNode = us.poll();

			// Set our data and then get the children to link in
			ourNode.setData(theirNode.getData());
			Node<E> ourLeft = theirNode.getLeft() == null ? null : new Node<>(theirNode.getLeft().getData());
			Node<E> ourRight = theirNode.getRight() == null ? null : new Node<>(theirNode.getRight().getData());

			// Only link in and process the non-null elements
			if (ourLeft != null) {
				ourLeft.setParent(ourNode);
				ourNode.setLeft(ourLeft);
				us.offer(ourLeft);
				them.offer(theirNode.getLeft());
				++this.size;
				;
			}
			if (ourRight != null) {
				ourRight.setParent(ourNode);
				ourNode.setRight(ourRight);
				us.offer(ourRight);
				them.offer(theirNode.getRight());
				++this.size;
			}
		}
	}

	// -------
	// Getters
	// -------

	/**
	 * This function is here for grading purpose not as a good programming
	 * practice.
	 * 
	 * @return root of the BST
	 */
	public Node<E> getRoot() {
		return root;
	}

	public int size() {
		return size;
	}

	/**
	 * 
	 * @return tree height
	 */
	public int height() {
		return heightRec(root);
	}

	private int heightRec(Node<E> root) {
		// -1 since the height of an empty tree is -1?
		if (root == null) {
			return -1;
		}
		return 1 + Math.max(heightRec(root.getLeft()), heightRec(root.getRight()));
	}

	/**
	 * This method must be implemented by operating over the tree without using
	 * either of the array lists preorderArr, inorderArr, and postorderArr.
	 * 
	 * @return the minimum element in the tree or null in the case of an empty
	 *         tree
	 */
	public E min() {
		if (size == 0) {
			return null;
		}
		Node<E> cur = root;
		while (cur.getLeft() != null) {
			cur = cur.getLeft();
		}

		return cur.getData();
	}

	/**
	 * This method must be implemented by operating over the tree without using
	 * either of the array lists preorderArr, inorderArr, and postorderArr.
	 * 
	 * @return the maximum element in the tree or null in the case of an empty
	 *         tree
	 */
	public E max() {
		if (size == 0) {
			return null;
		}
		Node<E> cur = root;
		while (cur.getRight() != null) {
			cur = cur.getRight();
		}
		return cur.getData();
	}

	/**
	 * Calls traversePreorder() and copy the content of preorderArr to arr.
	 * 
	 * @param arr
	 *            array list to store the sequence
	 */
	public void getPreorderSequence(ArrayList<E> arr) {
		traversePreorder();
		arr.addAll(preorderArr);
	}

	/**
	 * Calls traverseInorder() and copy the content of inorderArr to arr.
	 * 
	 * @param arr
	 *            array list to store the sequence
	 */
	public void getInorderSequence(ArrayList<E> arr) {
		traverseInorder();
		arr.addAll(inorderArr);
	}

	/**
	 * Calls traversePostorder() and copy the content of postorderArr to arr.
	 * 
	 * @param arr
	 *            array list to store the sequence
	 */
	public void getPostorderSequence(ArrayList<E> arr) {
		traversePostorder();
		arr.addAll(postorderArr);
	}

	private void postOrderRec(Node<E> root, ArrayList<E> arr) {
		if (root == null) {
			return;
		}
		postOrderRec(root.getLeft(), arr);
		postOrderRec(root.getRight(), arr);
		arr.add(root.getData());
	}

	// -----------
	// Comparators
	// -----------

	/**
	 * Returns true if the tree and a second tree o have exactly the same
	 * structure, and equal elements stored at every pair of corresponding
	 * nodes.
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null || o.getClass() != this.getClass()) {
			return false;
		}
		if (o == this) {
			return true;
		}

		BST<E> oBST = (BST<E>) o;
		if (!setEquals(oBST)) {
			return false;
		}

		return checkStructure(this.getRoot(), oBST.getRoot());
	}

	// Recursively checks that the given trees have the same structures
	private boolean checkStructure(Node<E> thisRoot, Node<E> otherRoot) {
		// Base case success
		if (thisRoot == otherRoot) {
			return true;
		}

		// Base case failure
		if (thisRoot == null && otherRoot != null) {
			return false;
		}
		if (thisRoot != null && otherRoot == null) {
			return false;
		}

		return thisRoot.getData().equals(otherRoot.getData()) && checkStructure(thisRoot.getLeft(), otherRoot.getLeft())
				&& checkStructure(thisRoot.getRight(), otherRoot.getRight());
	}

	/**
	 * Returns true if two binary search trees store the same set of elements,
	 * and false otherwise. The tree rooted at tree is also a binary search
	 * tree.
	 * 
	 * @param rt
	 * @return
	 */
	public boolean setEquals(BST<E> tree) {
		tree.traverseInorder();
		this.traverseInorder();

		return this.inorderArr.equals(tree.inorderArr);
	}

	// ----------
	// Traversals
	// ----------

	/**
	 * Performs a preorder traversal of the tree, stores the result in the array
	 * list preOrderArr, and also write the key values to a string in which they
	 * are separated by blanks (exactly one blank between two adjacent key
	 * values).
	 * 
	 * No need to perform the traversal if redoPreorder == false.
	 */
	public String traversePreorder() {
		if (redoPreorder) {
			preorderArr.clear();
			preorderRec(root);
		}
		return listString(preorderArr);
	}

	private void preorderRec(Node<E> root) {
		if (root == null) {
			return;
		}
		preorderArr.add(root.getData());
		preorderRec(root.getLeft());
		preorderRec(root.getRight());
	}

	/**
	 * Performs an inorder traversal of the tree, and stores the result in the
	 * array list inOrderArr. No need to perform the traversal if redoInorder ==
	 * false.
	 */
	public String traverseInorder() {
		if (redoInorder) {
			inorderArr.clear();
			inorderRec(root);
		}
		return listString(inorderArr);
	}

	private void inorderRec(Node<E> root) {
		if (root == null) {
			return;
		}
		inorderRec(root.getLeft());
		inorderArr.add(root.getData());
		inorderRec(root.getRight());
	}

	/**
	 * Performs a postorder traversal of the tree, and stores the result in the
	 * array list preOrderArr. No need to perform the traversal if redoPostorder
	 * == false.
	 */
	public String traversePostorder() {
		if (redoPostorder) {
			postorderArr.clear();
			postorderRec(root);
		}
		return listString(postorderArr);
	}

	private void postorderRec(Node<E> root) {
		if (root == null) {
			return;
		}
		postorderRec(root.getLeft());
		postorderRec(root.getRight());
		postorderArr.add(root.getData());
	}

	// Converts the given list to SSV - Space Separated Values
	private String listString(List<E> list) {
		if (list == null) {
			return "";
		}
		StringBuilder ersber = new StringBuilder();
		for (E derter : list) {
			ersber.append(derter).append(" ");
		}
		return ersber.toString().trim();
	}

	// -------------
	// Query Methods
	// -------------

	/**
	 * Returns the number of keys with values >= minValue and <= maxValue, and
	 * stores these key values in the array eleArray[] in the increasing order.
	 * Note that minValue and maxValue may not be any of the key values stored
	 * in the tree.
	 * 
	 * Exception is thrown if minValue > maxValue.
	 * 
	 * @param minValue
	 *            lower bound for query values
	 * @param maxValue
	 *            upper bound for query values
	 * @param eleArray
	 *            stores elements >= minValue and <= maxValue
	 * @return number of elements in the interval [minValue, maxValue]
	 */
	public int rangeQuery(E minValue, E maxValue, E[] eleArray) throws IllegalArgumentException {
		if (minValue.compareTo(maxValue) > 0) {
			throw new IllegalArgumentException("ahhhhhhh");
		}
		traverseInorder();
		int mindex = inorderArr.indexOf(minValue);
		// Cap it for not found indices
		mindex = (mindex == -1) ? 0 : mindex;
		int maxdex = inorderArr.indexOf(maxValue);
		maxdex = (maxdex == -1) ? inorderArr.size() - 1 : maxdex;

		int place = 0;
		for (int i = mindex; i <= maxdex; ++i) {
			eleArray[place++] = inorderArr.get(i);
		}

		return place;
	}

	/**
	 * Get the keys that are between the imin-th and the imax-th positions from
	 * an inorder traversal. The first visited node is at position 0. Store
	 * these keys in eleArray[] in their original order.
	 * 
	 * Exception is thrown if 1) imax < imin, 2) imin < 0, or 3) imax >= size.
	 * 
	 * @param imin
	 *            minimum index of the keys to be searched for according to
	 *            inorder
	 * @param imax
	 *            maximum index of the keys to be searched for according to
	 *            inorder
	 * @param eleArray
	 *            stores the found keys
	 * @return
	 */
	public void orderQuery(int imin, int imax, E[] eleArray) throws IllegalArgumentException {
		if (imax < imin || imin < 0 || imax >= size) {
			throw new IllegalArgumentException("ahhhhhhh");
		}
		traverseInorder();

		int place = 0;
		for (int i = imin; i <= imax; ++i) {
			eleArray[place++] = inorderArr.get(i);
		}
	}

	// --------------------------
	// Operations related to Keys
	// --------------------------

	@Override
	public boolean contains(Object obj) {
		// Copied from BSTSet.java
		E key = (E) obj;
		return findEntry(key) != null;
	}

	@Override
	public boolean add(E key) {
		// Copied from BSTSet.java
		if (root == null) {
			root = new Node<>(key);
			++size;
			redoPostorder = true;
			redoPreorder = true;
			redoInorder = true;
			return true;
		}

		Node<E> current = root;
		while (true) {
			int comp = current.getData().compareTo(key);
			if (comp == 0) {
				// key is already in the tree
				return false;
			} else if (comp > 0) {
				if (current.getLeft() != null) {
					current = current.getLeft();
				} else {
					current.setLeft(new Node<>(key));
					current.getLeft().setParent(current);
					++size;
					redoPostorder = true;
					redoPreorder = true;
					redoInorder = true;
					return true;
				}
			} else {
				if (current.getRight() != null) {
					current = current.getRight();
				} else {
					current.setRight(new Node<>(key));
					current.getRight().setParent(current);
					++size;
					redoPostorder = true;
					redoPreorder = true;
					redoInorder = true;
					return true;
				}
			}
		}
	}

	@Override
	public boolean remove(Object obj) {
		// Copied from BSTSet.java
		E key = (E) obj;
		Node<E> n = findEntry(key);
		if (n == null) {
			return false;
		}
		unlinkNode(n);
		return true;
	}

	/**
	 * Returns the node containing key, or null if the key is not found in the
	 * tree.
	 * 
	 * @param key
	 * @return the node containing key, or null if not found
	 */
	protected Node<E> findEntry(E key) {
		// Copied from BSTSet.java
		Node<E> current = root;
		while (current != null) {
			int comp = current.getData().compareTo(key);
			if (comp == 0) {
				return current;
			} else if (comp > 0) {
				current = current.getLeft();
			} else {
				current = current.getRight();
			}
		}
		return null;
	}

	// -------------------
	// Operations on Nodes
	// -------------------

	/**
	 * Returns the successor of the given node.
	 * 
	 * @param n
	 * @return the successor of the given node in this tree, or null if there is
	 *         no successor
	 */
	protected Node<E> successor(Node<E> n) {
		// Copied from BSTSet.java
		if (n == null) {
			return null;
		} else if (n.getRight() != null) {
			// leftmost entry in right subtree
			Node<E> current = n.getRight();
			while (current.getLeft() != null) {
				current = current.getLeft();
			}
			return current;
		} else {
			// we need to go up the tree to the closest ancestor that is
			// a left child; its parent must be the successor
			Node<E> current = n.getParent();
			Node<E> child = n;
			while (current != null && current.getRight() == child) {
				child = current;
				current = current.getParent();
			}
			// either current is null, or child is left child of current
			return current;
		}
	}

	/**
	 * Returns the predecessor of a node.
	 * 
	 * @param n
	 * @return the predecessor of the given node in this tree, or null if there
	 *         is no predecessor
	 */
	public Node<E> predecessor(Node<E> n) {
		// Either the right most in the left subtree
		if (n.getLeft() != null) {
			Node<E> cur = n.getLeft();
			while (cur.getRight() != null) {
				cur = cur.getRight();
			}
			return cur;
		}

		// Or we have to go up the tree
		Node<E> parent = n.getParent();
		Node<E> cur = n;

		while (parent != null && parent.getLeft() == cur) {
			cur = parent;
			parent = parent.getParent();
		}

		// Either found parent, or we hit the top
		return parent;
	}

	/**
	 * Performs left rotation on a node Reset tags redoPreorder, redoInorder,
	 * redoPostorder
	 * 
	 * @param n
	 */
	public void leftRotate(Node<E> n) {
		if (n == null) {
			return;
		}
		
		// Just gotta swap some references
		Node<E> parent = n.getParent();
		Node<E> child = n.getRight();
		Node<E> childChild = child.getLeft();

		if (parent != null) {
			parent.setRight(child);
		}
		
		child.setParent(parent);
		child.setLeft(n);
		n.setParent(child);
		n.setRight(childChild);

		if (childChild != null) {
			childChild.setParent(n);
		}
		
		if (n == this.root) {
			this.root = child;
		}

		redoInorder = true;
		redoPreorder = true;
		redoPostorder = true;
	}

	/**
	 * Performs right rotation on a node Reset tags redoPreorder, redoInorder,
	 * redoPostorder
	 * 
	 * @param n
	 */
	public void rightRotate(Node<E> n) {
		if (n == null) {
			return;
		}
		
		Node<E> parent = n.getParent();
		Node<E> child = n.getLeft();
		Node<E> childChild = child.getRight();
		
		if (parent != null) {
			parent.setLeft(child);
		}
		
		child.setParent(parent);
		child.setRight(n);
		n.setParent(child);
		n.setLeft(childChild);
		
		if (childChild != null) {
			childChild.setParent(n);
		}

		if (n == this.root) {
			this.root = child;
		}
		
		redoInorder = true;
		redoPreorder = true;
		redoPostorder = true;
	}

	/**
	 * Removes the given node, preserving the binary search tree property of the
	 * tree.
	 * 
	 * @param n
	 *            node to be removed
	 */
	protected void unlinkNode(Node<E> n) {
		// Copied from BSTSet.java

		// first deal with the two-child case; copy
		// data from successor up to n, and then delete successor
		// node instead of given node n
		if (n.getLeft() != null && n.getRight() != null) {
			Node<E> s = successor(n);
			n.setData(s.getData());
			n = s; // causes s to be deleted in code below
		}

		// n has at most one child
		Node<E> replacement = null;
		if (n.getLeft() != null) {
			replacement = n.getLeft();
		} else if (n.getRight() != null) {
			replacement = n.getRight();
		}

		// link replacement into tree in place of node n
		// (replacement may be null)
		if (n.getParent() == null) {
			root = replacement;
		} else {
			if (n == n.getParent().getLeft()) {
				n.getParent().setLeft(replacement);
			} else {
				n.getParent().setRight(replacement);
			}
		}

		if (replacement != null) {
			replacement.setParent(n.getParent());
		}

		--size;
	}

	// -------------
	// String output
	// -------------

	/**
	 * Returns a representation of this tree as a multi-line string. The tree is
	 * drawn with the root at the left and children are shown top-to-bottom.
	 * Leaves are marked with a "-" and non-leaves are marked with a "+".
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		toStringRec(root, sb, 0);
		return sb.toString();
	}

	/**
	 * Preorder traversal of the tree that builds a string representation in the
	 * given StringBuilder.
	 * 
	 * @param n
	 *            root of subtree to be traversed
	 * @param sb
	 *            StringBuilder in which to create a string representation
	 * @param depth
	 *            depth of the given node in the tree
	 */
	private void toStringRec(Node<E> n, StringBuilder sb, int depth) {
		for (int i = 0; i < depth; ++i) {
			sb.append("  ");
		}

		if (n == null) {
			sb.append("-\n");
			return;
		}

		if (n.getLeft() != null || n.getRight() != null) {
			sb.append("+ ");
		} else {
			sb.append("- ");
		}
		sb.append(n.getData().toString());
		sb.append("\n");
		if (n.getLeft() != null || n.getRight() != null) {
			toStringRec(n.getLeft(), sb, depth + 1);
			toStringRec(n.getRight(), sb, depth + 1);
		}
	}

	/**
	 * Iterator implementation is from BSTSet.java and thus not required here.
	 */
	@Override
	public Iterator<E> iterator() {
		return null;
	}

	/**
	 * Checks if the tree with a given root is a binary search tree.
	 * 
	 * @param rt
	 */
	private boolean isBST(Node<E> root) {
		if (root == null) {
			return true;
		}
		if (root.getLeft() != null && root.getLeft().getData().compareTo(root.getData()) >= 0) {
			return false;
		}
		if (root.getRight() != null && root.getRight().getData().compareTo(root.getData()) <= 0) {
			return false;
		}

		return isBST(root.getLeft()) && isBST(root.getRight());
	}

}
