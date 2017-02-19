package net.sepp_tember.lib.randomizer;

import java.util.*;

/**
 * Deprecated randomized list. This class was an attempt to create a
 * generally usable, immutable list that wraps another list and but provides a
 * randomzied iterator.
 * <p>
 * As this list has no public constructor it is intended to be constructed
 * only via {@link Randomizer} util class.
 *
 * @param <E> type of list elements
 */
@Deprecated
class DeprecatedRandomizedList<E> implements List<E> {

	private List<E> backingList;
	private RandomizeType randomizeType;

	/**
	 * Constructs new wrapper for specified list and uses specified randomize
	 * type for randomized iterator.
	 *
	 * @param backingList list to be wrapped
	 * @param randomizeType type of randomization for iterator
	 */
	DeprecatedRandomizedList(List<E> backingList, RandomizeType randomizeType) {
		this.backingList = backingList;
		this.randomizeType = randomizeType;
	}

	/**
	 * Returns the size of the list.
	 *
	 * @return size of the list
	 */
	@Override
	public int size() {
		return backingList.size();
	}

	/**
	 * Returns {@code true} if the list contains no elements.
	 *
	 * @return whether list is empty
	 */
	@Override
	public boolean isEmpty() {
		return backingList.isEmpty();
	}

	/**
	 * Returns {@code true} if some element in this list equals to specified
	 * object.
	 *
	 * @param o Object to be checked against
	 *
	 * @return {@code true} when list contains object
	 */
	@Override
	public boolean contains(Object o) {
		return backingList.contains(o);
	}

	/**
	 * Returns randomized iterator with randomization type given to constructor.
	 *
	 * @return randomized iterator
	 */
	@Override
	public Iterator<E> iterator() {
		return new RandomizedIterator<>(backingList, randomizeType.createRandomizer(backingList));
	}

	/**
	 * Returns an array containing elements of this list.
	 *
	 * @return array of elements
	 */
	@Override
	public Object[] toArray() {
		return backingList.toArray();
	}

	/**
	 * Returns an array containing elements of this list.
	 *
	 * @return array of elements
	 */
	@Override
	public <T> T[] toArray(T[] a) {
		return backingList.toArray(a);
	}

	/**
	 * Throws {@link UnsupportedOperationException} as this list is immutable.
	 *
	 * @param e element to be added
	 *
	 * @return {@code true} if this list changes with this operation
	 *
	 * @throws UnsupportedOperationException as this list is immutable, nothing
	 * can be added
	 */
	@Override
	public boolean add(E e) {
		throw new UnsupportedOperationException("List is not modifiable.");
	}

	/**
	 * Throws {@link UnsupportedOperationException} as this list is immutable.
	 *
	 * @param o element to be removed from this list, if present
	 *
	 * @return {@code true} if this list contained the specified element
	 *
	 * @throws UnsupportedOperationException as this list is immutable, nothing
	 * can be removed
	 */
	@Override
	public boolean remove(Object o) {
		throw new UnsupportedOperationException("List is not modifiable.");
	}

	/**
	 * Returns {@code true} if this list contains all of the elements of the
	 * specified collection.
	 *
	 * @param c collection to be checked for containment in this list
	 *
	 * @return {@code true} if this list contains all of the elements of the
	 * specified collection
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		return backingList.containsAll(c);
	}

	/**
	 * Throws {@link UnsupportedOperationException} as this list is immutable.
	 *
	 * @param c collection containing elements to be added to this list
	 *
	 * @return {@code true} if this list changed as a result of the call
	 *
	 * @throws UnsupportedOperationException as this list is immutable, nothing
	 * can be added
	 */
	@Override
	public boolean addAll(Collection<? extends E> c) {
		throw new UnsupportedOperationException("List is not modifiable.");
	}

	/**
	 * Throws {@link UnsupportedOperationException} as this list is immutable.
	 *
	 * @param index index at which to insert the first element from the
	 * specified collection
	 * @param c collection containing elements to be added to this list
	 *
	 * @return {@code true} if this list changed as a result of the call
	 *
	 * @throws UnsupportedOperationException as this list is immutable, nothing
	 * can be added
	 */
	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		throw new UnsupportedOperationException("List is not modifiable.");
	}

	/**
	 * Throws {@link UnsupportedOperationException} as this list is immutable.
	 *
	 * @param c collection containing elements to be removed from this list
	 *
	 * @return {@code true} if this list changed as a result of the call
	 *
	 * @throws UnsupportedOperationException as this list is immutable, nothing
	 * can be removed
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException("List is not modifiable.");
	}

	/**
	 * Throws {@link UnsupportedOperationException} as this list is immutable.
	 *
	 * @param c collection containing elements to be retained in this list
	 *
	 * @return {@code true} if this list changed as a result of the call
	 *
	 * @throws UnsupportedOperationException as this list is immutable, nothing
	 * can be removed
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException("List is not modifiable.");
	}

	/**
	 * Throws {@link UnsupportedOperationException} as this list is immutable.
	 *
	 * @throws UnsupportedOperationException as this list is immutable, nothing
	 * can be removed
	 */
	@Override
	public void clear() {
		throw new UnsupportedOperationException("List is not modifiable.");
	}

	/**
	 * Returns the element at the specified position in this list.
	 *
	 * @param index index of the element to return
	 *
	 * @return the element at the specified position in this list
	 *
	 * @throws IndexOutOfBoundsException if the index is out of range
	 * ({@code index < 0 || index >= size()})
	 */
	@Override
	public E get(int index) {
		return backingList.get(index);
	}

	/**
	 * Throws {@link UnsupportedOperationException} as this list is immutable.
	 *
	 * @param index index of the element to replace
	 * @param element element to be stored at the specified position
	 *
	 * @return the element previously at the specified position
	 *
	 * @throws UnsupportedOperationException as this list is immutable, nothing
	 * can be added
	 */
	@Override
	public E set(int index, E element) {
		throw new UnsupportedOperationException("List is not modifiable.");
	}

	/**
	 * Throws {@link UnsupportedOperationException} as this list is immutable.
	 *
	 * @param index index at which the specified element is to be inserted
	 * @param element element to be added
	 *
	 * @throws UnsupportedOperationException as this list is immutable, nothing
	 * can be added
	 */
	@Override
	public void add(int index, E element) {
		throw new UnsupportedOperationException("List is not modifiable.");
	}

	/**
	 * Throws {@link UnsupportedOperationException} as this list is immutable.
	 *
	 * @param index the index of the element to be removed
	 *
	 * @return the element previously at the specified position
	 *
	 * @throws UnsupportedOperationException as this list is immutable, nothing
	 * can be removed
	 */
	@Override
	public E remove(int index) {
		throw new UnsupportedOperationException("List is not modifiable.");
	}

	/**
	 * Returns the index of the first occurrence of the specified element
	 * in this list, or -1 if this list does not contain the element.
	 * More formally, returns the lowest index {@code i} such that
	 * {@code (o==null ? get(i)==null : o.equals(get(i)))},
	 * or -1 if there is no such index.
	 *
	 * @param o element to search for
	 *
	 * @return the index of the first occurrence of the specified element in
	 * this list, or -1 if this list does not contain the element
	 */
	@Override
	public int indexOf(Object o) {
		return backingList.indexOf(o);
	}

	/**
	 * Returns the index of the last occurrence of the specified element
	 * in this list, or -1 if this list does not contain the element.
	 * More formally, returns the highest index {@code i} such that
	 * {@code (o==null ? get(i)==null : o.equals(get(i)))},
	 * or -1 if there is no such index.
	 *
	 * @param o element to search for
	 *
	 * @return the index of the last occurrence of the specified element in
	 * this list, or -1 if this list does not contain the element
	 */
	@Override
	public int lastIndexOf(Object o) {
		return backingList.lastIndexOf(o);
	}

	/**
	 * Throws {@link UnsupportedOperationException} as this list is immutable
	 * and the list could be changed via the list iterator.
	 *
	 * @throws UnsupportedOperationException as this list is immutable
	 */
	@Override
	public ListIterator<E> listIterator() {
		throw new UnsupportedOperationException("List iterator cannot be randomized.");
	}

	/**
	 * Throws {@link UnsupportedOperationException} as this list is immutable
	 * and the list could be changed via the list iterator.
	 *
	 * @param index index of the first element to be returned from the
	 * list iterator
	 *
	 * @throws UnsupportedOperationException as this list is immutable
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		throw new UnsupportedOperationException("List iterator cannot be randomized.");
	}

	/**
	 * Returns an unmodifiable sublist of this list.
	 *
	 * @param fromIndex low endpoint (inclusive) of the subList
	 * @param toIndex high endpoint (exclusive) of the subList
	 *
	 * @return an unmodifiable view of the specified range within this list
	 *
	 * @throws IndexOutOfBoundsException for an illegal endpoint index value
	 * ({@code fromIndex < 0 || toIndex > size || fromIndex > toIndex})
	 */
	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		return Collections.unmodifiableList(backingList.subList(fromIndex, toIndex));
	}
}
