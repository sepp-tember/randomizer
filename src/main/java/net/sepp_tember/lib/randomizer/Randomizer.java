package net.sepp_tember.lib.randomizer;

/**
 * Randomizer calculates a sequence of random indexes.
 */
@Deprecated
public interface Randomizer {

	/**
	 * Returns {@code true} if the sequence contains more elements.
	 *
	 * @return {@code true} if the sequence contains more elements
	 */
	boolean hasNext();

	/**
	 * Returns the next random index of the sequence.
	 *
	 * @return next random index of the sequence
	 */
	int nextIndex();
}
