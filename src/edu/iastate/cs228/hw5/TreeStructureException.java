package edu.iastate.cs228.hw5;

/**
 *  
 * @author 228 instructors
 *
 */

/**
 * Expression thrown in PostfixExample for an invalid expression.
 */
public class TreeStructureException extends Exception {
	/**
	 * Initial version
	 */
	private static final long serialVersionUID = 1L;

	public TreeStructureException() {
		super();
	}

	public TreeStructureException(String msg) {
		super(msg);
	}
}
