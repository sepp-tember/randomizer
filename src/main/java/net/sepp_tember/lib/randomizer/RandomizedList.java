package net.sepp_tember.lib.randomizer;

import java.util.*;

class RandomizedList<E> implements List<E> {

	private List<E> backingList;
	private RandomizeType randomizeType;

	RandomizedList(List<E> backingList, RandomizeType randomizeType) {
		this.backingList = backingList;
		this.randomizeType = randomizeType;
	}

	@Override
	public int size() {
		return backingList.size();
	}

	@Override
	public boolean isEmpty() {
		return backingList.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return backingList.contains(o);
	}

	@Override
	public Iterator<E> iterator() {
		return new RandomizedIterator<>(backingList, randomizeType.createRandomizer(backingList));
	}

	@Override
	public Object[] toArray() {
		return backingList.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return backingList.toArray(a);
	}

	@Override
	public boolean add(E e) {
		throw new UnsupportedOperationException("List is not modifiable.");
	}

	@Override
	public boolean remove(Object o) {
		throw new UnsupportedOperationException("List is not modifiable.");
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return backingList.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		throw new UnsupportedOperationException("List is not modifiable.");
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		throw new UnsupportedOperationException("List is not modifiable.");
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException("List is not modifiable.");
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException("List is not modifiable.");
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException("List is not modifiable.");
	}

	@Override
	public E get(int index) {
		return backingList.get(index);
	}

	@Override
	public E set(int index, E element) {
		throw new UnsupportedOperationException("List is not modifiable.");
	}

	@Override
	public void add(int index, E element) {
		throw new UnsupportedOperationException("List is not modifiable.");
	}

	@Override
	public E remove(int index) {
		throw new UnsupportedOperationException("List is not modifiable.");
	}

	@Override
	public int indexOf(Object o) {
		return backingList.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return backingList.lastIndexOf(o);
	}

	@Override
	public ListIterator<E> listIterator() {
		throw new UnsupportedOperationException("List iterator cannot be randomized.");
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		throw new UnsupportedOperationException("List iterator cannot be randomized.");
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		return Collections.unmodifiableList(backingList.subList(fromIndex, toIndex));
	}
}
