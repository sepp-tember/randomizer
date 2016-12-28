package net.sepp_tember.lib.randomizer;

import java.util.*;

public final class Randomize {

	private Randomize() {}

	public static <T> List<T> list(List<T> backingList, RandomizeType randomizeType) {
		return new RandomizedList<>(backingList, randomizeType);
	}

	public static RandomizeType weightedUnlimitedRandomization(double[] weights) {
		return new WeightedUnlimitedRandomizeType(weights);
	}
}
