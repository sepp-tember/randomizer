package net.sepp_tember.lib.randomizer;

import java.util.List;

/**
 * Utility class to create randomized lists out of standard lists. The sequence
 * of elements provided by iterators of randomized lists is in a random order.
 * The kind of randomization of the elements is specified by
 * {@link RandomizeType} and could be for example in an equal distribution, or
 * also a weighted distribution.
 */
@Deprecated
public final class Randomize {

	/**
	 * Private constructor as this is a static utility class.
	 */
	private Randomize() {
	}

	/**
	 * Returns the specified list as a randomized list with an distribution
	 * specified by the given randomize type. The iterator of the returned list
	 * provides the elements of the list in a random order. The list itself
	 * immutable, that means all methods that would modify the state of the
	 * list, for example {@link List#add}, throw a
	 * {@link UnsupportedOperationException}.
	 *
	 * @param backingList the list to be randomized
	 * @param randomizeType the kind of randomization
	 * @param <T> type of the lists elements
	 *
	 * @return a randomized list
	 *
	 * @see DeprecatedRandomizedList
	 */
	@Deprecated
	public static <T> List<T> list(List<T> backingList, RandomizeType randomizeType) {
		return new DeprecatedRandomizedList<T>(backingList, randomizeType);
	}

	/**
	 * Returns a randomization type for a weighted distribution of elements.
	 * The specified array of weights has to have the same number of elements
	 * as the list that is randomized by this randomization type.
	 * <p>
	 * <strong>Note:</strong>
	 * <p>
	 * If the
	 * number of elements differ at the moment as the iterator of the
	 * randomized list is created, an {@link IllegalStateException} will be
	 * thrown.
	 * <p>
	 * The sum of the weights should be equal to 1. If the sum exceeds 1, all
	 * weights above this limit will be ignored and the elements associated
	 * with this weights are never returned by the iterator. The weight of the
	 * first element which exceeds the sum of 1 is truncated to a weight which
	 * would lead to the exact sum of 1. For example three weights like
	 * {@code [0.7, 0.5, 0.2]} are effectively like {@code [0.7, 0.3, 0.0]},
	 * as the weight of 0.5 exceeds the sum of 1 it is truncated to 0.3 and the
	 * weight of 0.2 is totally ignored.
	 *
	 * @param weights weight of elements for distribution
	 *
	 * @return randomization type to be used with
	 * {@link #list(List, RandomizeType)} to randomize a normal list.
	 *
	 * @see #list(List, RandomizeType)
	 */
	@Deprecated
	public static RandomizeType weightedUnlimitedRandomization(double[] weights) {
		return new WeightedUnlimitedRandomizeType(weights);
	}
}
