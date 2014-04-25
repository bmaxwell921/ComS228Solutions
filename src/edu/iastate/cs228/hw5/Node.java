package edu.iastate.cs228.hw5;

/**
 * 
 * @author 
 *
 */

/**
 * 
 * This class represents a tree node. The class is public but in the BST class
 * the root node will be protected.
 * 
 */

// also okay to use the following.
// public class Node<E extends Comparable<? super E>>
public class Node<E> {
	private E data;
	private Node<E> parent;
	private Node<E> left;
	private Node<E> right;

	public Node(E dat) {
		this(dat, null, null);
	}

	public Node(E dat, Node<E> left, Node<E> right) {
		this.data = dat;
		this.left = left;
		this.right = right;
		this.parent = null;
	}

	/**
	 * Write the value of the instance variable named data.
	 */
	public String toString() {
		return "Node: " + data;
	}

	public E getData() {
		return data;
	}

	public void setData(E dat) {
		this.data = dat;
	}

	public Node<E> getParent() {
		return parent;
	}

	public void setParent(Node<E> parent) {
		this.parent = parent;
	}

	public Node<E> getLeft() {
		return left;
	}

	public void setLeft(Node<E> left) {
		this.left = left;
	}

	public Node<E> getRight() {
		return right;
	}

	public void setRight(Node<E> right) {
		this.right = right;
	}

}
