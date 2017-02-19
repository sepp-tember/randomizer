package net.sepp_tember.lib.randomizer;

import java.util.*;

import net.sepp_tember.lib.randomizer.WeightedRandomizedList.WeightedElement;

public class WeightedRandomizedList<T> implements RandomizedList<WeightedElement<T>> {

	public static class WeightedElement<T> {

		private double weight;
		private T element;

		public WeightedElement(double weight, T element) {
			if (weight < 0) {
				throw new IllegalArgumentException();
			}
			this.weight = weight;
			this.element = element;
		}

		public double getWeight() {
			return weight;
		}

		public T getElement() {
			return element;
		}

		@Override
		public int hashCode() {
			int result;
			long temp;
			temp = Double.doubleToLongBits(weight);
			result = (int) (temp ^ (temp >>> 32));
			result = 31 * result + (element != null ? element.hashCode() : 0);
			return result;
		}

		@Override
		public boolean equals(Object o) {
			if (o == null || !(o instanceof WeightedElement)) {
				return false;
			} else if (this == o) {
				return true;
			}
			WeightedElement<?> other = (WeightedElement<?>) o;
			boolean isEqual = this.weight == other.weight;
			if (this.element == null) {
				isEqual = isEqual && other.element == null;
			} else {
				isEqual = isEqual && this.element.equals(other.element);
			}
			return isEqual;
		}
	}

	private List<WeightedElement<T>> backingList = new ArrayList<>();

	public WeightedRandomizedList() {
	}

	public WeightedRandomizedList(Collection<WeightedElement<T>> collection) {
		if (collection != null) {
			backingList.addAll(collection);
		}
	}

	public WeightedRandomizedList(List<Double> weights, List<T> elements) {
		Objects.requireNonNull(weights, "Weights must not be null.");
		Objects.requireNonNull(elements, "Elements must not be null.");
		if (weights.size() != elements.size()) {
			throw new IllegalArgumentException("Number of weights (" + weights.size() + ") and number of elements (" + elements.size() +
					") differ, but they have to be equal.");
		}

		for (int i = 0; i < elements.size(); i++) {
			backingList.add(new WeightedElement<T>(weights.get(i), elements.get(i)));
		}
	}

	void setBackingList(List<WeightedElement<T>> backingList) {
		this.backingList = backingList;
	}

	@Override
	public boolean add(WeightedElement<T> t) {
		return backingList.add(t);
	}

	@Override
	public void add(int index, WeightedElement<T> element) {
		backingList.add(index, element);
	}

	@Override
	public boolean addAll(Collection<? extends WeightedElement<T>> c) {
		return backingList.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends WeightedElement<T>> c) {
		return backingList.addAll(index, c);
	}

	@Override
	public void clear() {
		backingList.clear();
	}

	@Override
	public boolean contains(Object o) {
		return backingList.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return backingList.containsAll(c);
	}

	@Override
	public WeightedElement<T> get(int index) {
		return backingList.get(index);
	}

	@Override
	public int indexOf(Object o) {
		return backingList.indexOf(o);
	}

	@Override
	public boolean isEmpty() {
		return backingList.isEmpty();
	}

	@Override
	public Iterator<WeightedElement<T>> iterator() {
		return backingList.iterator();
	}

	@Override
	public int lastIndexOf(Object o) {
		return backingList.lastIndexOf(o);
	}

	@Override
	public ListIterator<WeightedElement<T>> listIterator() {
		return backingList.listIterator();
	}

	@Override
	public ListIterator<WeightedElement<T>> listIterator(int index) {
		return backingList.listIterator(index);
	}

	@Override
	public WeightedElement<T> remove(int index) {
		return backingList.remove(index);
	}

	@Override
	public boolean remove(Object o) {
		return backingList.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return backingList.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return backingList.retainAll(c);
	}

	@Override
	public WeightedElement<T> set(int index, WeightedElement<T> element) {
		return backingList.set(index, element);
	}

	@Override
	public int size() {
		return backingList.size();
	}

	@Override
	public List<WeightedElement<T>> subList(int fromIndex, int toIndex) {
		return backingList.subList(fromIndex, toIndex);
	}

	@Override
	public Object[] toArray() {
		return backingList.toArray();
	}

	@Override
	public <T1> T1[] toArray(T1[] a) {
		return backingList.toArray(a);
	}

	@Override
	public Iterator<WeightedElement<T>> randomizedIterator() {
		return new WeightedRandomizedEndlessIterator<T>(backingList);
	}

	@Override
	public Iterable<WeightedElement<T>> randomized() {
		return this::randomizedIterator;
	}
}
