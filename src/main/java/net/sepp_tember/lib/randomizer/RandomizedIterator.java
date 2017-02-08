package net.sepp_tember.lib.randomizer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Deprecated
class RandomizedIterator<T> implements Iterator<T> {

	private Randomizer randomizer;
	private List<T> elements;

	RandomizedIterator(Collection<T> sourceElements, Randomizer randomizer) {
		this.elements = new ArrayList<>(sourceElements);
		this.randomizer = randomizer;
	}

	@Override
	public boolean hasNext() {
		return randomizer.hasNext();
	}

	@Override
	public T next() {
		return elements.get(randomizer.nextIndex());
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("Randomized iterator is not modifiable.");
	}
}
