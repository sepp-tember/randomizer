package net.sepp_tember.lib.randomizer;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class StandardRandomizer<T> implements Randomizer<T> {

	private List<T> source;
	private Random rng;

	@Override
	public T next() {
		return source.get(rng.nextInt(source.size()));
	}

	@Override
	public void setSource(List<T> source) {
		this.source = source;
	}

	@Override
	public void setRng(Random rng) {
		this.rng = rng;
	}
}
