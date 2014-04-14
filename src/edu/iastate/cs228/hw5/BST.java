package edu.iastate.cs228.hw5;

/**
 *  
 * @author
 *
 */

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.ArrayList;
import java.lang.IllegalArgumentException;

/**
 * Binary search tree implementation
 */
public class BST<E extends Comparable<? super E>> extends AbstractSet<E> {
	protected Node root;
	protected int size;

	private ArrayList<E> preorderArr; // stores the key values from a preorder
										// traversal
	private ArrayList<E> inorderArr; // stores the key values from an inorder
										// traversal
	private ArrayList<E> postorderArr; // stores the key values from a postorder
										// traversal

	/*
	 * These tags are set to false respectively at the ends of calls to
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
	 * Default constructor
	 */
	public BST() {
		// TODO
	}

	/**
	 * Constructor over an element array.
	 * 
	 * @param eleArray
	 */
	public BST(E[] eleArray) {
		// TODO
	}

	/**
	 * Copy constructor. It takes a binary tree as input, and checks if it is
	 * binary search tree. If not, throws a TreeStructureException with the
	 * message "Copying a non-BST tree". If so, copies the input tree.
	 * 
	 * @param rt
	 *            root of an existing binary search tree
	 */
	public BST(BST<E> tree) throws TreeStructureException {
		// TODO
	}

	// -------
	// Getters
	// -------

	/**
	 * This function is here for grading purpose not as good programming
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
		// TODO
		return -1;
	}

	/**
	 * 
	 * @return the minimum element in the tree
	 */
	public E min() {
		// TODO
		return null;
	}

	/**
	 * 
	 * @return the maximum element in the tree
	 */
	public E max() {
		// TODO
		return null;
	}

	/**
	 * 
	 * @param arr
	 *            array list to store the sequence
	 */
	public void getPreorderSequence(ArrayList<E> arr) {
		// TODO
	}

	/**
	 * 
	 * @param arr
	 *            array list to store the sequence
	 */
	public void getInorderSequence(ArrayList<E> arr) {
		// TODO
	}

	/**
	 * 
	 * @param arr
	 *            array list to store the sequence
	 */
	public void getPostorderSequence(ArrayList<E> arr) {
		// TODO
	}

	// -----------
	// Comparisons
	// -----------

	/**
	 * Returns true if the tree and a second tree o have exactly the same
	 * structure, and the same value stored at every pair of corresponding
	 * nodes.
	 */
	@Override
	public boolean equals(Object o) {
		// TODO
		return false;
	}

	/**
	 * Returns true if two binary search trees store the same set of elements,
	 * and false otherwise. The tree rooted at rt is also a binary search tree.
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
		// TODO
		return null;
	}

	/**
	 * Performs an inorder traversal of the tree, and stores the result in the
	 * array list inOrderArr. No need to perform the traversal if redoInorder ==
	 * false.
	 */
	public String traverseInorder() {
		// TODO
		return null;
	}

	/**
	 * Performs a postorder traversal of the tree, and stores the result in the
	 * array list preOrderArr. No need to perform the traversal if redoInorder
	 * == false.
	 */
	public String traversePostorder() {
		// TODO
		return null;
	}

	// -------------
	// Query Methods
	// -------------

	/**
	 * Returns the number of keys with values >= minValue and <= maxValue, and
	 * stores these key values in the array eleArray[] in the increasing order.
	 * 
	 * @param minValue
	 *            lower bound for query values
	 * @param maxValue
	 *            upper bound for query values
	 * @param eleArray
	 *            stores elements >= minValue and <= maxValue
	 * @return number of elements in the interval [minValue, maxValue]
	 */
	public int rangeQuery(E minValue, E maxValue, E[] eleArray)
			throws IllegalArgumentException {
		// TODO
		return -1;
	}

	/**
	 * Get the keys that are between the imin-th and the imax-th positions from
	 * an inorder traversal. The first visited node is at position 0. Store
	 * these keys in eleArray[] in the same order.
	 * 
	 * Exception is thrown if 1) imin, imax < 0, 2) imin, imax >= size, or 3)
	 * imax < imin.
	 * 
	 * @param imin
	 *            minimum index of the keys to be searched for according to the
	 *            inorder traversal
	 * @param imax
	 *            maximum index of the keys to be searched for
	 * @param eleArray
	 *            stores the found keys
	 * @return
	 */
	public void orderQuery(int imin, int imax, E[] eleArray)
			throws IllegalArgumentException {
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
		// reset tags redoPreorder, redoInorder, redoPostorder
		return false;
	}

	@Override
	public boolean remove(Object obj) {
		// from BSTSet.java
		// reset tags redoPreorder, redoInorder, redoPostorder
		return false;
	}

	/**
	 * Returns the node containing key, or null if the key is not found in the
	 * tree.
	 * 
	 * @param key
	 * @return the node containing key, or null if not found
	 */
	protected Node findEntry(E key) {
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
	protected Node successor(Node n) {
		// from BSTSet.java
		return null;
	}

	/**
	 * Returns the predecessor of the given node.
	 * 
	 * @param n
	 * @return the predecessor of the given node in this tree, or null if there
	 *         is no successor
	 */
	public Node predecessor(Node n) {
		// ToDO
		return null;
	}

	/**
	 * Performs left rotation on a node
	 * 
	 * @param n
	 */
	public void leftRotate(Node n) {
		// TODO
	}

	/**
	 * Performs right rotation on a node
	 * 
	 * @param n
	 */
	public void rightRotate(Node n) {
		// TODO
	}

	/**
	 * Removes the given node, preserving the binary search tree property of the
	 * tree.
	 * 
	 * @param n
	 *            node to be removed
	 */
	protected void unlinkNode(Node n) {
		// from BSTSet.java
	}

	@Override
	public Iterator<E> iterator() {
		// from BSTSet.java
		return null;
	}

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
	 * Checks if the tree with a given root is a binary search tree.
	 * 
	 * @param rt
	 */
	private boolean isBST(Node<E> root) {
		// TODO
		return false;
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
	private void toStringRec(Node n, StringBuilder sb, int depth) {
		// from BSTSet.java
	}

	/**
	 * Iterator implementation for this binary search tree. The elements are
	 * returned in ascending order according to their natural ordering.
	 */
	private class BSTIterator implements Iterator<E> {

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public E next() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
		// from BSTSet.java
	}
}
