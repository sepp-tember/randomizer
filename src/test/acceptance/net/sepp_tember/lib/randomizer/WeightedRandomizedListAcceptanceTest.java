package net.sepp_tember.lib.randomizer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import net.sepp_tember.lib.randomizer.WeightedRandomizedList.WeightedElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WeightedRandomizedListAcceptanceTest {

	private Random rng = new Random();

	@Test
	public void testDistribution() {
		WeightedRandomizedList<Number> randomizedList = new WeightedRandomizedList<>();
		Map<Number, Integer> distribution = new HashMap<>();
		int numberOfElements = 3 + rng.nextInt(3);
		int numberOfDrawings = 100000;
		for (int i = 0; i < numberOfElements; i++) {
			double number = rng.nextDouble();
			randomizedList.add(new WeightedElement<>(number, number));
			distribution.put(number, 0);
		}
		double totalWeight = randomizedList.stream().mapToDouble(WeightedElement::getWeight).sum();

		Iterator<WeightedElement<Number>> iterator = randomizedList.randomizedIterator();
		for (int i = 0; i < numberOfDrawings; i++) {
			distribution.compute(iterator.next().getElement(), (key, value) -> value + 1);
		}

		Assertions.assertAll(randomizedList.stream().map(element ->
				() -> Assertions.assertEquals(
						element.getWeight() / totalWeight,
						distribution.get(element.getElement()) / (double) numberOfDrawings,
						0.01
				)));
	}
}
