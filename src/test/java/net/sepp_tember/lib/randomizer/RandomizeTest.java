package net.sepp_tember.lib.randomizer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RandomizeTest {

	@Test
	public void testWeightedUnlimitedRandomization_ReturnsWeightedUnlimitedRandomizeType() {
		double[] dummyWeights = {};
		RandomizeType randomizeType = Randomize.weightedUnlimitedRandomization(dummyWeights);
		assertTrue(randomizeType instanceof WeightedUnlimitedRandomizeType);
	}
}