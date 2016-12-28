package net.sepp_tember.lib.randomizer;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class WeightedUnlimitedRandomizeTypeTest {

	private static double[] weights = {0.3, 0.5, 0.2};

	@Test
	public void testCreateRandomizer_ThrowsIllegalStateExceptionIfNumberOfElementsAndWeightsDiffer() {
		List<Object> elements = setupList(weights.length + 1);
		WeightedUnlimitedRandomizeType randomizeType = new WeightedUnlimitedRandomizeType(weights);
		assertThrows(IllegalStateException.class, () -> randomizeType.createRandomizer(elements));
	}

	@Test
	public void testCreateRandomizer_ReturnsWeightedUnlimitedRandomizer() {
		List<Object> dummyList = setupList(weights.length);
		WeightedUnlimitedRandomizeType randomizeType = new WeightedUnlimitedRandomizeType(weights);
		Randomizer randomizer = randomizeType.createRandomizer(dummyList);
		assertTrue(randomizer instanceof WeightedUnlimitedRandomizer);
	}

	private List<Object> setupList(int numberOfElements) {
		List<Object> list = new ArrayList<>();
		for (int i = 0; i < numberOfElements; i++) {
			list.add(new Object());
		}
		return list;
	}
}