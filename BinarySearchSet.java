package assignment03;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinarySearchSet<E> implements SortedSet<E>, Iterator<E> {

	private E[] backupArray;
	private int size;
	Comparator<? super E> modelComparator;

	/**
	 * Constructor to initiate the class
	 */
	@SuppressWarnings("unchecked")
	public BinarySearchSet() {
		// We use an object because we know everything is an object or an array
		// as objects
		backupArray = (E[]) new Object[100];
		size = 0;
	}

	BinarySearchSet(Comparator<? super E> comparator) {
		this(); // Will construct the default constructor above
		modelComparator = comparator;
	}

	/**
	 * @return the comparator used to order the elements of this set, or null if
	 *         it uses the natural ordering of its elements (Comparable)
	 */
	@Override
	public Comparator<? super E> comparator() {
		if (modelComparator != null) {
			return this.modelComparator;
		} else {
			return null;
		}
	}

	/**
	 * @return: the first (lowest, smallest) element currently in this set
	 * @throws: NoSuchElementException
	 *              - if the set is empty
	 */
	@Override
	public E first() throws NoSuchElementException {
		if (this.size == 0) {
			throw new NoSuchElementException();
		}
		return this.backupArray[0];
	}

	/**
	 * @return: the last (highest, largest) element currently in this set
	 * @throws: NoSuchElementException
	 *              - if the set is empty
	 */
	@Override
	public E last() throws NoSuchElementException {
		if (this.size == 0) {
			throw new NoSuchElementException();
		}
		return this.backupArray[this.size - 1];
	}

	/**
	 * Adds the specified element to this set if it is not already present and
	 * not set to null.
	 * 
	 * @param: element
	 * 
	 * @return: true if this set did not already contain the specified element
	 */
	@Override
	public boolean add(E element) {
		// TODO Auto-generated method stub
		if (this.contains(element)) {
			return false;
		}
		// If the array is full we need to double its size, copy all values to a
		// temp array and place it back in the original backup Array
		if (this.backupArray.length - 1 == size) {
			@SuppressWarnings("unchecked")
			E[] tempArray = (E[]) new Object[backupArray.length * 2];
			for (int i = 0; i < backupArray.length; i++) {
				tempArray[i] = backupArray[i];
			}
			this.backupArray = tempArray;
		}

		// inserts elements into a binarySearchSet
		int insertionPoint = binarySearch(element);

		for (int i = size; i > insertionPoint; i--) {
			this.backupArray[i] = this.backupArray[i - 1];
		}
		this.backupArray[insertionPoint] = element;
		this.size++;
		return true;

	}

	/**
	 * Performs a Binary Search to return the element
	 * 
	 * @param element
	 * @return
	 */
	public int binarySearch(E element) {
		int low = 0;
		int mid = 0;
		int high = this.size;

		if (this.size == 0) {
			return 0;
		}

		if (myComparableMethod(element, this.backupArray[size - 1]) > 0) {
			return this.size;
		}

		if (myComparableMethod(element, this.backupArray[0]) < 0) {
			return 0;
		}

		while (low < high) {
			mid = (low + high) / 2;
			if (myComparableMethod(element, this.backupArray[mid]) > 0) {
				low = mid + 1;
			} else {
				high = mid;
			}
			if (low == this.size || myComparableMethod(element, this.backupArray[low]) == 0) {
				return -(low + 1);
			}
		}
		return low;
	}

	/**
	 * Adds all of the elements in the specified collection to this set if they
	 * are not already present and not set to null.
	 * 
	 * @param: elements
	 * 
	 * @return: true if this set changed as a result of the call
	 */
	@Override
	public boolean addAll(Collection<? extends E> elements) {
		boolean changesMadeToSet = false;

		if (!this.contains(elements)) {
			return false;
		}
		// Add element if not already in set
		for (E currentElement : elements) {
			if (add(currentElement)) {
				changesMadeToSet = true;
				continue;
			}
		}
		return changesMadeToSet;
	}

	/**
	 * Removes all of the elements from this set. The set will be empty after
	 * this call returns.
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub

		this.backupArray = (E[]) new Object[100];
		this.size = 0;

	}

	/**
	 * @param: element
	 * 
	 * @return true if this set contains the specified element
	 */
	@Override
	public boolean contains(Object element) {
		if (this.size == 0) {
			return false;
		}

		if (binarySearch((E) element) < 0) {
			return true;
		}
		return false;
	}

	/**
	 * @param: elements
	 * 
	 * @return true if this set contains all of the elements of the specified
	 *         collection
	 */
	@Override
	public boolean containsAll(Collection<?> elements) {
		// TODO Auto-generated method stub
		for (Object currentElement : elements) {
			if (!this.contains(currentElement)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @return true if this set contains no elements
	 */
	@Override
	public boolean isEmpty() {
		if (this.size == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return an iterator over the elements in this set, where the elements are
	 *         returned in sorted (ascending) order
	 */
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Removes the specified element from this set if it is present.
	 * 
	 * @param: element
	 * 
	 * @return true if this set contained the specified element
	 */
	@Override
	public boolean remove(Object element) {
		if (this.contains(element)) {
			return false;
		}

		int elementToRemove = (-1 * binarySearch((E) element) - 1);

		if (myComparableMethod((E) element, this.last()) == 0) {
			this.backupArray[elementToRemove] = null;
		} else {
			for (int i = elementToRemove; i < this.size; i++) {
				this.backupArray[i] = this.backupArray[i + 1];
			}
		}
		this.size--;
		return true;
	}

	/**
	 * Removes from this set all of its elements that are contained in the
	 * specified collection.
	 * 
	 * @param: elements
	 * 
	 * @return true if this set changed as a result of the call
	 */
	@Override
	public boolean removeAll(Collection<?> elements) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @return the number of elements in this set
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * @return an array containing all of the elements in this set, in sorted
	 *         (ascending) order.
	 */
	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	public int myComparableMethod(E left, E right) {
		if (modelComparator != null) {
			return modelComparator.compare(left, right);
		}
		return ((Comparable<E>) left).compareTo(right);
	}

	/**
	 * Returns true if the iteration has more elements. (In other words, returns
	 * true if next would return an element rather than throwing an exception.)
	 * 
	 * @return true if the iteration has more elements
	 */
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @return the next element in the iteration.
	 */
	@Override
	public E next() {
		// TODO Auto-generated method stub
		return null;
	}

}
