package test.edu.iastate.cs228.hw5;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import edu.iastate.cs228.hw5.TreeSort;

/**
 * Tests for the TreeSort class
 * 
 * @author Brandon
 * 
 */
public class TreeSortTest {

	@Test
	public void testSmall() {
		String[] test = { "D", "E", "F", "B", "A" };
		String[] correct = test.clone();

		Arrays.sort(correct);
		TreeSort.sort(test);

		Assert.assertArrayEquals(correct, test);
	}

	@Test
	public void testMedium() {
		String[] test = { "G", "H", "A", "E", "F", "I", "K", "L", "M", "N", "O" };
		String[] correct = test.clone();

		Arrays.sort(correct);
		TreeSort.sort(test);

		Assert.assertArrayEquals(correct, test);
	}

	@Test
	public void testLarge() {
		String[] test = { "A", "L", "U", "H", "D", "Q", "R", "T", "S", "B", "V", "K", "J", "F", "O", "G", "W", "P", "C", "Y", "X", "E",
				"I", "M", "N", "Z" };
		String[] correct = test.clone();
		
		Arrays.sort(correct);
		TreeSort.sort(test);
		
		Assert.assertArrayEquals(correct, test);
	}
}
