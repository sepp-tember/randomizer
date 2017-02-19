package net.sepp_tember.lib.randomizer;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.sepp_tember.lib.randomizer.WeightedRandomizedList.WeightedElement;

/**
 * This iterator does iterate over elements given to the constructor in a
 * random order with a weighted distribution. To achieve a weighted
 * distribution the iterator has to compute the probability for each element
 * to be returned by a call to next. So each element has to be associated with
 * a weight, which is achieved by wrapping the elements in
 * {@link WeightedElement}s.
 * <p>
 * Instead of using this iterator directly, {@link WeightedRandomizedList}
 * could be used, which returns an instance of this iterator when
 * {@link WeightedRandomizedList#randomizedIterator() randomizedIterator} is
 * called.
 * <p>
 * The probability for an element to be returned by each {@code next()}
 * iterator access is calculated by the weight of the element itself and the
 * total amount of all weights in the list. Let {@code w} be the weight of one
 * element and {@code s} the sum of all weights in the list, then
 * {@code P(x)=w/s} is the probability for the element to be returned by the
 * next call to {@link Iterator#next() next()} of the randomized iterator of this
 * list.
 * <p>
 * An example to demonstrate how this randomized iterator works:
 * <pre>
 *     List&lt;WeightedElement&lt;String&gt;&gt; list = new ArrayList&lt;&gt;();
 *     list.add(new WeightedElement&lt;&gt;(0.3, "first");
 *     list.add(new WeightedElement&lt;&gt;(0.7, "second");
 *     list.add(new WeightedElement&lt;&gt;(0.5, "third");
 *
 *     Iterator&lt;WeightedElement&lt;String&gt;&gt; iterator = new WeightedRandomizedEndlessIterator&lt;&gt;(list);
 *     int numberOfDrawings = 1000;
 *     for (int i = 0; i &lt; numberOfDrawings; i++) {
 *         WeightedElement&lt;String&gt; element = iterator.next();
 *         String value = element.getElement();
 *         // do something
 *     }
 * </pre>
 * First a list is created and some weighted elements are added. Then a
 * randomized iterator is created with the list as source of elements. After
 * that, an element is randomly drawn from the list by the iterator. The
 * probability for each element to be drawn from the list is shown in the table
 * below.
 * <table class="data">
 * <tr>
 * <th>Element</th>
 * <th>Weight</th>
 * <th>Probability</th>
 * </tr>
 * <tr>
 * <td>"first"</td>
 * <td>0.3</td>
 * <td>0.2</td>
 * </tr>
 * <tr>
 * <td>"second"</td>
 * <td>0.7</td>
 * <td>0.466</td>
 * </tr>
 * <tr>
 * <td>"third"</td>
 * <td>0.5</td>
 * <td>0.333</td>
 * </tr>
 * </table>
 *
 * @param <E>
 */
public class WeightedRandomizedEndlessIterator<E> implements Iterator<WeightedElement<E>> {

	private List<WeightedElement<E>> list;
	private Random rng = new Random();
	private double totalWeights;
	private int lastIndex = -1;

	/**
	 * Constructs randomized iterator with specified list as source of elements.
	 *
	 * @param list elements to be iterated over
	 */
	public WeightedRandomizedEndlessIterator(List<WeightedElement<E>> list) {
		this.list = list;
		totalWeights = list.stream().reduce(0.0, (sum, element) -> sum + element.getWeight(), (sum1, sum2) -> sum1 + sum2);
	}

	/**
	 * Constructs randomized iterator with specified list as source of elements
	 * and the specified random number generator used for randomization.
	 *
	 * @param list elements to be iterated over
	 * @param rng random number generator to randomize iteration
	 */
	WeightedRandomizedEndlessIterator(List<WeightedElement<E>> list, Random rng) {
		this(list);
		this.rng = rng;
	}

	/**
	 * Returns the next randomly drawn element.
	 *
	 * @return next random element
	 */
	public WeightedElement<E> next() {
		double randomValue = rng.nextDouble() * totalWeights;
		double sum = 0;
		for (int index = 0; index < list.size(); index++) {
			WeightedElement<E> element = list.get(index);
			sum = sum + element.getWeight();
			if (randomValue < sum) {
				lastIndex = index;
				return element;
			}
		}
		return list.get(list.size() - 1);
	}

	/**
	 * Returns always {@code true} as the iteration has always more elements.
	 * As this iterator delivers the elements with a weighted probability, it
	 * only makes sense that the sequence of elements is endless. Or else it
	 * would not be possible to create a weighted distribution of elements.
	 *
	 * @return always {@code true}, as the iteration is endless
	 */
	public boolean hasNext() {
		return !list.isEmpty();
	}

	/**
	 * Removes the last element returned by this iterator from the underlying
	 * list. This method can be called only once per call to {@link #next}.
	 *
	 * @throws IllegalStateException if the {@code next} method has not yet
	 * been called, or the {@code remove} method has already been called after
	 * the last call to the {@code next} method
	 */
	public void remove() {
		if (lastIndex < 0) {
			throw new IllegalStateException();
		}

		WeightedElement<E> removed = list.remove(lastIndex);
		totalWeights = totalWeights - removed.getWeight();
		lastIndex = -1;
	}
}
