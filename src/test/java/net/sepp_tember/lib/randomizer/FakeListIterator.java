package net.sepp_tember.lib.randomizer;

import java.util.ListIterator;

public class FakeListIterator<T> implements ListIterator<T> {
	@Override
	public boolean hasNext() {
		return false;
	}

	@Override
	public T next() {
		return null;
	}

	@Override
	public boolean hasPrevious() {
		return false;
	}

	@Override
	public T previous() {
		return null;
	}

	@Override
	public int nextIndex() {
		return 0;
	}

	@Override
	public int previousIndex() {
		return 0;
	}

	@Override
	public void remove() {

	}

	@Override
	public void set(T t) {

	}

	@Override
	public void add(T t) {

	}
}
