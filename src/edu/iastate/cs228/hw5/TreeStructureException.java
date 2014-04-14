package edu.iastate.cs228.hw5;

/**
 *  
 * @author Steve Kautz
 *
 */

/**
 * Expression thrown in PostfixExample for an invalid expression.
 */
public class TreeStructureException extends Exception
{
  public TreeStructureException()
  {
    super();
  }
  
  public TreeStructureException(String msg)
  {
    super(msg);
  }
}

