package net.sepp_tember.lib.randomizer;

import java.util.Collection;

@Deprecated
public class WeightedUnlimitedRandomizeType implements RandomizeType {

	private double[] weights;

	public WeightedUnlimitedRandomizeType(double[] weights) {
		this.weights = weights;
	}

	@Override
	public Randomizer createRandomizer(Collection<?> source) {
		if (source.size() != weights.length) {
			throw new IllegalStateException("Number of elements (" + source.size() + ") differs from number of weights ("
					+ weights.length + ")");
		}
		return new WeightedUnlimitedRandomizer(weights);
	}
}
