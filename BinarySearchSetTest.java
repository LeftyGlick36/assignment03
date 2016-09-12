package assignment03;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BinarySearchSetTest {
	BinarySearchSet<Integer> testSet = new BinarySearchSet<>();
	BinarySearchSet<Integer> reverseTestSet = new BinarySearchSet<>();
	BinarySearchSet<Integer> nullTestSet = new BinarySearchSet<>();
	ArrayList<Integer> testArrayList = new ArrayList<Integer>();

	@Before
	public void setUp() throws Exception {
		testSet.add(1);
		testSet.add(2);
		testSet.add(3);
		testSet.add(4);
		testSet.add(5);
		testSet.add(6);
		testSet.add(7);
		testSet.add(8);
		testSet.add(9);
		testSet.add(10);

		reverseTestSet.add(10);
		reverseTestSet.add(9);
		reverseTestSet.add(8);
		reverseTestSet.add(7);
		reverseTestSet.add(6);
		reverseTestSet.add(5);
		reverseTestSet.add(4);
		reverseTestSet.add(3);
		reverseTestSet.add(2);
		reverseTestSet.add(1);

		testArrayList.add(1);
		testArrayList.add(2);
		testArrayList.add(3);
		testArrayList.add(4);
		testArrayList.add(5);
		testArrayList.add(6);
		testArrayList.add(7);
		testArrayList.add(8);
		testArrayList.add(9);
		testArrayList.add(10);

	}

	@Test
	public void testComparator() {

		assertEquals(null, nullTestSet.comparator());
	}

	@Test
	public void testFirst() {
		int firstTestSet = this.testSet.first();
		int secondTestSet = this.reverseTestSet.first();
		Assert.assertEquals(1, firstTestSet);
		Assert.assertEquals(1, secondTestSet);
	}

	@Test
	public void testLast() {
		int firstTestSet = this.testSet.last();
		int secondTestSet = this.reverseTestSet.last();
		Assert.assertEquals(10, firstTestSet);
		Assert.assertEquals(10, secondTestSet);
	}

	@Test
	public void testAdd() {
		this.testSet.add(11);
		Assert.assertEquals(11, testSet.size());
	}

	@Test
	public void testAddAll() {
		testSet.clear();
		testSet.addAll(testArrayList);
		// Assert.assertEquals(11, testSet.size());
		//System.out.println(testSet.toArray());
	}

	@Test
	public void testClear() {
		this.testSet.clear();
		Assert.assertEquals(0, testSet.size());
	}

	@Test
	public void testContains() {
		Assert.assertEquals(true, testSet.contains(1));
	}

	@Test
	public void testContainsAll() {
		ArrayList<Integer> testArray = new ArrayList<Integer>();
		testArray.add(1);
		testArray.add(2);
		testArray.add(3);
		testArray.add(4);
		testArray.add(5);

		Assert.assertEquals(true, testSet.containsAll(testArray));
	}

	@Test
	public void testIsEmpty() {
		Assert.assertEquals(false, testSet.isEmpty());
		testSet.clear();
		Assert.assertEquals(true, testSet.isEmpty());
	}

	@Test
	public void testIterator() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemove() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testSize() {
		Assert.assertEquals(10, testSet.size());
		testSet.add(12);
		Assert.assertEquals(11, testSet.size());

	}

	@Test
	public void testToArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testHasNext() {
		fail("Not yet implemented");
	}

	@Test
	public void testNext() {
		fail("Not yet implemented");
	}

}
