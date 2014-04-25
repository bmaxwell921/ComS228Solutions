package edu.iastate.cs228.hw5;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author
 *
 */

public class TreeSort {
	/**
	 * Sorts an input array by first creating a binary search tree to store its
	 * elements, and then perform an inorder traversal of the tree to visit the
	 * elements in order and put them back in the array.
	 * 
	 * @param eleArray
	 */
	public static <E extends Comparable<? super E>> void sort(E[] eleArray) {
		// Create BST
		BST<E> tree = new BST<>(eleArray);
		
		// Get inorder
		ArrayList<E> inorder = new ArrayList<>();
		tree.getInorderSequence(inorder);
		
		// Put everything in eleArray
		int index = 0;
		for (E ele : inorder) {
			eleArray[index++] = ele;
		}
	}
}
