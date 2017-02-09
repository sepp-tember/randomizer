package net.sepp_tember.lib.randomizer;

import java.util.Iterator;
import java.util.List;

public interface RandomizedList<T> extends List<T> {

	public Iterator<T> randomizedIterator();

	public Iterable<T> randomized();
}
