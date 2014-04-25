package edu.iastate.cs228.hw5.util;

import edu.iastate.cs228.hw5.BST;

/**
 * Class used to validate information about BSTs
 * @author Brandon
 *
 */
public class BSTValidator {

	/**
	 * Checks whether the given BST has a cycle
	 * @param tree
	 * @return
	 */
	public static <E extends Comparable<? super E>> boolean hasCycle(BST<E> tree) {
		return true;
	}
	
	public static <E extends Comparable<? super E>> boolean hasProperRefs(BST<E> tree) {
		return false;
	}
}
