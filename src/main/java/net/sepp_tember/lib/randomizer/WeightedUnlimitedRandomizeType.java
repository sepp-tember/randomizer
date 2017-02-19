package net.sepp_tember.lib.randomizer;

import java.util.Collection;

/**
 * Randomization type to create a randomizer that produces an endless
 * sequence of indexes with a weighted distribution.
 *
 * @see Randomize#weightedUnlimitedRandomization(double[])
 */
@Deprecated
public class WeightedUnlimitedRandomizeType implements RandomizeType {

	private double[] weights;

	/**
	 * Creates a randomization type to create a randomizer that produces an
	 * endless sequence of indexes with a weighted distribution defined by the
	 * specified weights. The number of weights has to match the number of
	 * elements later given to {@link #createRandomizer(Collection)}.
	 *
	 * @param weights weights to calculate distribution
	 *
	 * @see Randomize#weightedUnlimitedRandomization(double[])
	 */
	public WeightedUnlimitedRandomizeType(double[] weights) {
		this.weights = weights;
	}

	/**
	 * Creates a new randomizer for the specified collection. The size of the
	 * collection has to match the number of weights given to
	 * {@link #WeightedUnlimitedRandomizeType(double[])}.
	 *
	 * @param source the collection the randomizer should be created for.
	 *
	 * @return randomizer for weighted distribution
	 *
	 * @see Randomize#weightedUnlimitedRandomization(double[])
	 */
	@Override
	public Randomizer createRandomizer(Collection<?> source) {
		if (source.size() != weights.length) {
			throw new IllegalStateException("Number of elements (" + source.size() + ") differs from number of weights ("
					+ weights.length + ")");
		}
		return new WeightedUnlimitedRandomizer(weights);
	}
}
