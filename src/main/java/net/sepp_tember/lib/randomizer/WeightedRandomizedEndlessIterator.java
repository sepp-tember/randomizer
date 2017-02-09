package net.sepp_tember.lib.randomizer;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.sepp_tember.lib.randomizer.WeightedRandomizedList.WeightedElement;

public class WeightedRandomizedEndlessIterator<T> implements Iterator<WeightedElement<T>> {

	private List<WeightedElement<T>> list;
	private Random rng = new Random();
	private double totalWeights;
	private int lastIndex = -1;

	public WeightedRandomizedEndlessIterator(List<WeightedElement<T>> list) {
		this.list = list;
		totalWeights = list.stream().reduce(0.0, (sum, element) -> sum + element.getWeight(), (sum1, sum2) -> sum1 + sum2);
	}

	WeightedRandomizedEndlessIterator(List<WeightedElement<T>> list, Random rng) {
		this(list);
		this.rng = rng;
	}

	public WeightedElement<T> next() {
		double randomValue = rng.nextDouble() * totalWeights;
		double sum = 0;
		for (int index = 0; index < list.size(); index++) {
			WeightedElement<T> element = list.get(index);
			sum = sum + element.getWeight();
			if (randomValue < sum) {
				lastIndex = index;
				return element;
			}
		}
		return list.get(list.size() - 1);
	}

	public boolean hasNext() {
		return !list.isEmpty();
	}

	public void remove() {
		if (lastIndex < 0) {
			throw new IllegalStateException();
		}

		WeightedElement<T> removed = list.remove(lastIndex);
		totalWeights = totalWeights - removed.getWeight();
		lastIndex = -1;
	}
}
