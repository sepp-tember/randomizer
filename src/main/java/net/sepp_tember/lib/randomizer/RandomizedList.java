package net.sepp_tember.lib.randomizer;

import java.util.Iterator;
import java.util.List;

/**
 * A randomized list is like a normal {@link List} but with the additional
 * ability to provide a randomized iterator. That is an iterator which delivers
 * the lists elements in some random order.
 *
 * @param <E> the type of elements in this list
 */
public interface RandomizedList<E> extends List<E> {

	/**
	 * Returns an iterator that delivers the elements of the list in some random order.
	 * @return Iterator with random order
	 */
	public Iterator<E> randomizedIterator();

	/**
	 * Returns an iterable that provides a randomized iterator like {@link #randomizedIterator()} does.
	 * @return Iterable with randomized iterator
	 */
	public Iterable<E> randomized();
}
