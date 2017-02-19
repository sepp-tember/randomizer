package net.sepp_tember.lib.randomizer;

import java.util.*;

import net.sepp_tember.lib.randomizer.WeightedRandomizedList.WeightedElement;

/**
 * A randomized list with a weighted randomized iterator. The iterator returned
 * by {@link #randomizedIterator()} is of type
 * {@link WeightedRandomizedEndlessIterator}, which delivers the elements in a
 * random order with weighted distribution. The elements of the list are each
 * wrapped in a {@link WeightedElement WeightedElement} object to define the
 * weight for the elements. The weight is needed to calculate the probability
 * for the iterator, see {@link WeightedRandomizedEndlessIterator
 * WeightedRandomizedEndlessIterator for a detailed description}.
 *
 * @see WeightedRandomizedEndlessIterator
 * @see RandomizedList
 *
 * @param <E> type of values in weighted elements of this list
 */
public class WeightedRandomizedList<E> implements RandomizedList<WeightedElement<E>> {

	/**
	 * Wrapper for elements to assign a weight to them.
	 *
	 * @param <E> type of wrapped value
	 */
	public static class WeightedElement<E> {

		private double weight;
		private E element;

		/**
		 * Constructor to assign weight to given element.
		 *
		 * @param weight associated weight
		 * @param element value to be weighted
		 */
		public WeightedElement(double weight, E element) {
			if (weight < 0) {
				throw new IllegalArgumentException();
			}
			this.weight = weight;
			this.element = element;
		}

		/**
		 * Returns the weight assignd to the element.
		 *
		 * @return weight of the element
		 */
		public double getWeight() {
			return weight;
		}

		/**
		 * Returns value of wrapped element.
		 *
		 * @return value of element
		 */
		public E getElement() {
			return element;
		}

		/**
		 * Returns a hash code value for this weighted element.
		 *
		 * @return the hash code
		 */
		@Override
		public int hashCode() {
			int result;
			long temp;
			temp = Double.doubleToLongBits(weight);
			result = (int) (temp ^ (temp >>> 32));
			result = 31 * result + (element != null ? element.hashCode() : 0);
			return result;
		}

		/**
		 * Determines if the specified object is equal to this weighted element.
		 * <p>
		 * The specified object is equal to this weighted element if the object
		 * is an instance of {@code WeightedElement}, both weights have exactly
		 * the same value and either both elements are null or equal to each
		 * other.
		 *
		 * @param o object to determine equality to this wieghted element
		 *
		 * @return true if the speciefied object is equal to this weighted
		 * element
		 */
		@Override
		public boolean equals(Object o) {
			if (o == null || !(o instanceof WeightedElement)) {
				return false;
			} else if (this == o) {
				return true;
			}
			WeightedElement<?> other = (WeightedElement<?>) o;
			boolean isEqual = this.weight == other.weight;
			if (this.element == null) {
				isEqual = isEqual && other.element == null;
			} else {
				isEqual = isEqual && this.element.equals(other.element);
			}
			return isEqual;
		}
	}

	private List<WeightedElement<E>> backingList = new ArrayList<>();

	/**
	 * Constructs a new empty weighted randomized list.
	 */
	public WeightedRandomizedList() {
	}

	/**
	 * Constructs a new weighted randomized list filled with elements of
	 * specified collection.
	 *
	 * @param collection source of elements for list
	 */
	public WeightedRandomizedList(Collection<WeightedElement<E>> collection) {
		if (collection != null) {
			backingList.addAll(collection);
		}
	}

	/**
	 * Constructs a new weighted randomized list filled with specified elements
	 * combined with specified weights. The lists of weights and elements must
	 * not be null and have the same size. Each weight of the specified weights
	 * list is combined with the element with the same index of the specified
	 * elements list to a new {@link WeightedElement WeightedElement} and added
	 * to this list.
	 *
	 * @param weights source of weights for this list
	 * @param elements source of elements for this list
	 *
	 * @throws NullPointerException if either list of weights or elements is null
	 * @throws IllegalArgumentException if number of weights is not equal to
	 * number of elements
	 */
	public WeightedRandomizedList(List<Double> weights, List<E> elements) {
		Objects.requireNonNull(weights, "Weights must not be null.");
		Objects.requireNonNull(elements, "Elements must not be null.");
		if (weights.size() != elements.size()) {
			throw new IllegalArgumentException("Number of weights (" + weights.size() + ") and number of elements (" + elements.size() +
					") differ, but they have to be equal.");
		}

		for (int i = 0; i < elements.size(); i++) {
			backingList.add(new WeightedElement<E>(weights.get(i), elements.get(i)));
		}
	}

	/**
	 * Sets the list used by this list to store elements.
	 *
	 * @param backingList list used for storage of elements
	 */
	void setBackingList(List<WeightedElement<E>> backingList) {
		this.backingList = backingList;
	}

	/**
	 * Appends the specified element to the end of this list.
	 *
	 * @param element element to be appended to this list
	 *
	 * @return {@code true} (as specified by {@link Collection#add})
	 */
	@Override
	public boolean add(WeightedElement<E> element) {
		return backingList.add(element);
	}

	/**
	 * Inserts the specified element at the specified position in this
	 * list. Shifts the element currently at that position (if any) and
	 * any subsequent elements towards the end (adds one to their indices).
	 *
	 * @param index index at which the specified element is to be inserted
	 * @param element element to be inserted
	 *
	 * @throws IndexOutOfBoundsException if the index is out of range
	 * ({@code index < 0 || index > size()})
	 */
	@Override
	public void add(int index, WeightedElement<E> element) {
		backingList.add(index, element);
	}

	/**
	 * Appends all of the elements in the specified collection to the end of
	 * this list, in the order that they are returned by the
	 * specified collection's Iterator.
	 *
	 * @param c collection containing elements to be added to this list
	 *
	 * @return <tt>true</tt> if this list changed as a result of the call
	 *
	 * @throws NullPointerException if the specified collection is null
	 */
	@Override
	public boolean addAll(Collection<? extends WeightedElement<E>> c) {
		return backingList.addAll(c);
	}

	/**
	 * Inserts all of the elements in the specified collection into this
	 * list, starting at the specified position. Shifts the element
	 * currently at that position (if any) and any subsequent elements towards
	 * the end (increases their indices). The new elements will appear
	 * in the list in the order that they are returned by the
	 * specified collection's iterator.
	 *
	 * @param index index at which to insert the first element from the
	 * specified collection
	 * @param c collection containing elements to be added to this list
	 *
	 * @return {@code true} if this list changed as a result of the call
	 *
	 * @throws IndexOutOfBoundsException if the index is out of range
	 * ({@code index < 0 || index > size()})
	 * @throws NullPointerException if the specified collection is null
	 */
	@Override
	public boolean addAll(int index, Collection<? extends WeightedElement<E>> c) {
		return backingList.addAll(index, c);
	}

	/**
	 * Removes all of the elements from this list.
	 */
	@Override
	public void clear() {
		backingList.clear();
	}

	/**
	 * Returns {@code true} if this list contains the specified element.
	 *
	 * @param o element whose presence in this list is to be tested
	 *
	 * @return {@code true} if this list contains the specified element
	 */
	@Override
	public boolean contains(Object o) {
		return backingList.contains(o);
	}

	/**
	 * Returns {@code true} if this collection contains all of the elements
	 * in the specified collection.
	 *
	 * @param c collection to be checked for containment in this collection
	 *
	 * @return {@code true} if this collection contains all of the elements
	 * in the specified collection
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		return backingList.containsAll(c);
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
	public WeightedElement<E> get(int index) {
		return backingList.get(index);
	}

	/**
	 * Returns the index of the first occurrence of the specified element
	 * in this list, or -1 if this list does not contain the element.
	 */
	@Override
	public int indexOf(Object o) {
		return backingList.indexOf(o);
	}

	/**
	 * Returns {@code true} if this list contains no elements.
	 *
	 * @return {@code true} if this list contains no elements
	 */
	@Override
	public boolean isEmpty() {
		return backingList.isEmpty();
	}

	/**
	 * Returns an iterator over the elements in this list in ordered sequence.
	 *
	 * @return an iterator over the elements in this list in ordered sequence
	 */
	@Override
	public Iterator<WeightedElement<E>> iterator() {
		return backingList.iterator();
	}

	/**
	 * Returns the index of the last occurrence of the specified element
	 * in this list, or -1 if this list does not contain the element.
	 */
	@Override
	public int lastIndexOf(Object o) {
		return backingList.lastIndexOf(o);
	}

	/**
	 * Returns a list iterator over the elements in this list (in ordered
	 * sequence).
	 *
	 * @see #listIterator(int)
	 */
	@Override
	public ListIterator<WeightedElement<E>> listIterator() {
		return backingList.listIterator();
	}

	/**
	 * Returns a list iterator over the elements in this list (in ordered
	 * sequence), starting at the specified position in the list.
	 * The specified index indicates the first element that would be
	 * returned by an initial call to {@link ListIterator#next next}.
	 * An initial call to {@link ListIterator#previous previous} would
	 * return the element with the specified index minus one.
	 *
	 * @throws IndexOutOfBoundsException if the index is out of range
	 * ({@code index < 0 || index > size()})
	 */
	@Override
	public ListIterator<WeightedElement<E>> listIterator(int index) {
		return backingList.listIterator(index);
	}

	/**
	 * Removes the element at the specified position in this list.
	 * Shifts any subsequent elements towards the beginning (subtracts one
	 * from their indices).
	 *
	 * @param index the index of the element to be removed
	 *
	 * @return the element that was removed from the list
	 *
	 * @throws IndexOutOfBoundsException if the index is out of range
	 * ({@code index < 0 || index >= size()})
	 */
	@Override
	public WeightedElement<E> remove(int index) {
		return backingList.remove(index);
	}

	/**
	 * Removes the first occurrence of the specified element from this list,
	 * if it is present. If the list does not contain the element, it is
	 * unchanged. Returns {@code true} if this list contained the specified
	 * element (or equivalently, if this list changed as a result of the call).
	 *
	 * @param o element to be removed from this list, if present
	 *
	 * @return {@code true} if this list contained the specified element
	 */
	@Override
	public boolean remove(Object o) {
		return backingList.remove(o);
	}

	/**
	 * Removes from this list all of its elements that are contained in the
	 * specified collection.
	 *
	 * @param c collection containing elements to be removed from this list
	 *
	 * @return {@code true} if this list changed as a result of the call
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		return backingList.removeAll(c);
	}

	/**
	 * Retains only the elements in this list that are contained in the
	 * specified collection. In other words, removes from this list all
	 * of its elements that are not contained in the specified collection.
	 *
	 * @param c collection containing elements to be retained in this list
	 *
	 * @return {@code true} if this list changed as a result of the call
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		return backingList.retainAll(c);
	}

	/**
	 * Replaces the element at the specified position in this list with
	 * the specified element.
	 *
	 * @param index index of the element to replace
	 * @param element element to be stored at the specified position
	 *
	 * @return the element previously at the specified position
	 *
	 * @throws IndexOutOfBoundsException if the index is out of range
	 * ({@code index < 0 || index >= size()})
	 */
	@Override
	public WeightedElement<E> set(int index, WeightedElement<E> element) {
		return backingList.set(index, element);
	}

	/**
	 * Returns the number of elements in this list.
	 *
	 * @return the number of elements in this list
	 */
	@Override
	public int size() {
		return backingList.size();
	}

	/**
	 * Returns a view of the portion of this list between the specified
	 * {@code fromIndex}, inclusive, and {@code toIndex}, exclusive. (If
	 * {@code fromIndex} and {@code toIndex} are equal, the returned list is
	 * empty.) The returned list is backed by this list, so non-structural
	 * changes in the returned list are reflected in this list, and vice-versa.
	 * The returned list supports all of the optional list operations supported
	 * by this list.
	 * <p>
	 * This method eliminates the need for explicit range operations (of
	 * the sort that commonly exist for arrays). Any operation that expects
	 * a list can be used as a range operation by passing a subList view
	 * instead of a whole list. For example, the following idiom
	 * removes a range of elements from a list:
	 * <pre>{@code
	 *      list.subList(from, to).clear();
	 * }</pre>
	 * Similar idioms may be constructed for {@code indexOf} and
	 * {@code lastIndexOf}, and all of the algorithms in the
	 * {@code Collections} class can be applied to a subList.
	 * <p>
	 * The semantics of the list returned by this method become undefined if
	 * the backing list (i.e., this list) is <em>structurally modified</em> in
	 * any way other than via the returned list. (Structural modifications are
	 * those that change the size of this list, or otherwise perturb it in such
	 * a fashion that iterations in progress may yield incorrect results.)
	 *
	 * @param fromIndex low endpoint (inclusive) of the subList
	 * @param toIndex high endpoint (exclusive) of the subList
	 *
	 * @return a view of the specified range within this list
	 *
	 * @throws IndexOutOfBoundsException for an illegal endpoint index value
	 * (<code>fromIndex &lt; 0 || toIndex &gt; size || fromIndex &gt; toIndex</code>)
	 */
	@Override
	public List<WeightedElement<E>> subList(int fromIndex, int toIndex) {
		return backingList.subList(fromIndex, toIndex);
	}

	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence (from first to last element).
	 * <p>
	 * This list does not hold any references to the returned array. The caller
	 * is thus free to modify the returned array.
	 *
	 * @return an array containing all of the elements in this list in proper
	 * sequence
	 */
	@Override
	public Object[] toArray() {
		return backingList.toArray();
	}

	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence (from first to last element); the runtime type of the returned
	 * array is that of the specified array. If the list fits in the
	 * specified array, it is returned therein. Otherwise, a new array is
	 * allocated with the runtime type of the specified array and the size of
	 * this list.
	 * <p>
	 * If the list fits in the specified array with room to spare
	 * (i.e., the array has more elements than the list), the element in
	 * the array immediately following the end of the collection is set to
	 * {@code null}.
	 *
	 * @param a the array into which the elements of the list are to
	 * be stored, if it is big enough; otherwise, a new array of the
	 * same runtime type is allocated for this purpose.
	 *
	 * @return an array containing the elements of the list
	 *
	 * @throws ArrayStoreException if the runtime type of the specified array
	 * is not a supertype of the runtime type of every element in this list
	 * @throws NullPointerException if the specified array is null
	 */
	@Override
	public <T1> T1[] toArray(T1[] a) {
		return backingList.toArray(a);
	}

	/**
	 * Returns an iterator over the elements in this list in a random, endless
	 * sequence. That means {@link Iterator#hasNext()} <strong>always</strong>
	 * returns true and {@link Iterator#next()} <strong>always</strong> returns
	 * some random element from this list. The probability for an element to be
	 * returned by the iterator depends on its weight. See
	 * {@linkplain WeightedRandomizedList the class description for information}.
	 *
	 * @return an iterator over the elements in this list in weighted random
	 * sequence
	 */
	@Override
	public Iterator<WeightedElement<E>> randomizedIterator() {
		return new WeightedRandomizedEndlessIterator<E>(backingList);
	}

	/**
	 * Returns an iterable that provides an iterator like
	 * {@link #randomizedIterator()} does. This is convinient to use the
	 * randomized iterator of this list in a for-loop like
	 * <pre>{@literal
	 *     for (WeightedElement<?> element : list.randomized()) {
	 *         // do something
	 *     }
	 * }</pre>
	 * But keep in mind that the sequence of elements provided by the iterator
	 * is endless, so using this iterable in a for-loop without some additional
	 * ending mechanism would lead to an infinite loop.
	 *
	 * @return iterable that provides an iterator like {@link #randomizedIterator()}
	 */
	@Override
	public Iterable<WeightedElement<E>> randomized() {
		return this::randomizedIterator;
	}
}
