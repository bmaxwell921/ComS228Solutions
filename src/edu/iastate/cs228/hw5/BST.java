package edu.iastate.cs228.hw5;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Binary search tree implementation
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
		this((E[]) new Object[0]);
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
		// TODO
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
		// TODO

		return false;
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
		// TODO

		return false;
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
		// TODO

		return 0;
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
		// TODO
	}

	// --------------------------
	// Operations related to Keys
	// --------------------------

	@Override
	public boolean contains(Object obj) {
		// from BSTSet.java

		return false;
	}

	@Override
	public boolean add(E key) {
		// from BSTSet.java
		// Reset tags redoPreorder, redoInorder, redoPostorder

		return false;
	}

	@Override
	public boolean remove(Object obj) {
		// from BSTSet.java
		//
		// Reset tags redoPreorder, redoInorder, redoPostorder

		return false;
	}

	/**
	 * Returns the node containing key, or null if the key is not found in the
	 * tree.
	 * 
	 * @param key
	 * @return the node containing key, or null if not found
	 */
	protected Node<E> findEntry(E key) {
		// from BSTset.java

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
		// from BSTSet.java

		return null;
	}

	/**
	 * Returns the predecessor of a node.
	 * 
	 * @param n
	 * @return the predecessor of the given node in this tree, or null if there
	 *         is no successor
	 */
	public Node<E> predecessor(Node<E> n) {
		// ToDO

		return null;
	}

	/**
	 * Performs left rotation on a node Reset tags redoPreorder, redoInorder,
	 * redoPostorder
	 * 
	 * @param n
	 */
	public void leftRotate(Node<E> n) {
		// TODO
	}

	/**
	 * Performs right rotation on a node Reset tags redoPreorder, redoInorder,
	 * redoPostorder
	 * 
	 * @param n
	 */
	public void rightRotate(Node<E> n) {
		// TODO
	}

	/**
	 * Removes the given node, preserving the binary search tree property of the
	 * tree.
	 * 
	 * @param n
	 *            node to be removed
	 */
	protected void unlinkNode(Node<E> n) {
		// from BSTSet.java
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
		// from BSTSet.java

		return null;
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
		// TODO

		return false;
	}

}
