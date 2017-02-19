package net.sepp_tember.lib.randomizer;

import java.util.*;

/**
 * A randomized iterator delivers the elements in a random order. This
 * randomized iterator is not constructed directly but only returned by a
 * randomized list provided by {@link Randomize#list(List, RandomizeType)}.
 *
 * @param <T> type of elements to be iterated over
 */
@Deprecated
class RandomizedIterator<T> implements Iterator<T> {

	private Randomizer randomizer;
	private List<T> elements;

	/**
	 * Constructs new randomized iterator that iterates over the elements of
	 * specified collection and uses the given randomizer to randomize the
	 * elements.
	 *
	 * @param sourceElements elements to iterate over
	 * @param randomizer randomizer to randomize order of elements
	 */
	RandomizedIterator(Collection<T> sourceElements, Randomizer randomizer) {
		this.elements = new ArrayList<>(sourceElements);
		this.randomizer = randomizer;
	}

	/**
	 * Returns {@code true} if the iteration has more elements.
	 *
	 * @return {@code true} if the iteration has more elements
	 */
	@Override
	public boolean hasNext() {
		return randomizer.hasNext();
	}

	/**
	 * Returns the next element in the iteration.
	 *
	 * @return the next element in the iteration
	 *
	 * @throws NoSuchElementException if the iteration has no more elements
	 */
	@Override
	public T next() {
		return elements.get(randomizer.nextIndex());
	}

	/**
	 * Throws {@link UnsupportedOperationException} as the underlying
	 * collection should not be modified. A modification could interfere with
	 * the calculation of the probability for the elements to be drawn by
	 * the next iteration.
	 *
	 * @throws UnsupportedOperationException if this method gets called
	 */
	@Override
	public void remove() {
		throw new UnsupportedOperationException("Randomized iterator is not modifiable.");
	}
}
