package net.sepp_tember.lib.randomizer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

public class WeightedUnlimitedRandomizerTest {

	private static final double[] weights = { 0.5, 0.3, 0.1, 0.1 };
	private static final double[] limits = { 0.5, 0.8, 0.9 };

	@Test
	public void testNextIndex_ReturnsIndexBelowWhenRngDeliversNumberBelowLowerLimit() {
		int indexBelow = 1;
		Random rng = mock(Random.class);
		when(rng.nextDouble()).thenReturn(limits[indexBelow] - 0.0001);
		WeightedUnlimitedRandomizer randomizer = new WeightedUnlimitedRandomizer(weights);
		randomizer.setRng(rng);
		int actualIndex = randomizer.nextIndex();
		assertEquals(indexBelow, actualIndex);
	}

	@Test
	public void testNextIndex_ReturnsExpectedIndexWhenRngDeliversNumberAtLowerLimit() {
		int expectedIndex = 2;
		Random rng = mock(Random.class);
		when(rng.nextDouble()).thenReturn(limits[expectedIndex - 1]);
		WeightedUnlimitedRandomizer randomizer = new WeightedUnlimitedRandomizer(weights);
		randomizer.setRng(rng);
		int actualIndex = randomizer.nextIndex();
		assertEquals(expectedIndex, actualIndex);
	}

	@Test
	public void testNextIndex_ReturnsExpectedIndexWhenRngDeliversNumberBelowUpperLimit() {
		int expectedIndex = 2;
		Random rng = mock(Random.class);
		when(rng.nextDouble()).thenReturn(limits[expectedIndex] - 0.0001);
		WeightedUnlimitedRandomizer randomizer = new WeightedUnlimitedRandomizer(weights);
		randomizer.setRng(rng);
		int actualIndex = randomizer.nextIndex();
		assertEquals(expectedIndex, actualIndex);
	}

	@Test
	public void testNextIndex_ReturnsIndexAboveWhenRngDeliversNumberAtUpperLimit() {
		int indexAbove = 3;
		Random rng = mock(Random.class);
		when(rng.nextDouble()).thenReturn(limits[indexAbove - 1]);
		WeightedUnlimitedRandomizer randomizer = new WeightedUnlimitedRandomizer(weights);
		randomizer.setRng(rng);
		int actualIndex = randomizer.nextIndex();
		assertEquals(indexAbove, actualIndex);
	}

	@Test
	public void testHasNext_ReturnsAlwaysTrue() {
		WeightedUnlimitedRandomizer randomizer = new WeightedUnlimitedRandomizer(weights);
		assertTrue(randomizer.hasNext());
	}
}
