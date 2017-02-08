package net.sepp_tember.lib.randomizer;

import java.util.List;

@Deprecated
public final class Randomize {

	private Randomize() {
	}

	@Deprecated
	public static <T> List<T> list(List<T> backingList, RandomizeType randomizeType) {
		return new DeprecatedRandomizedList<T>(backingList, randomizeType);
	}

	@Deprecated
	public static RandomizeType weightedUnlimitedRandomization(double[] weights) {
		return new WeightedUnlimitedRandomizeType(weights);
	}
}
