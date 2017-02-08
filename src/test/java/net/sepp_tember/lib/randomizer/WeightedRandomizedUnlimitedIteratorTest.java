package net.sepp_tember.lib.randomizer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import net.sepp_tember.lib.randomizer.WeightedRandomizedList.WeightedElement;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

public class WeightedRandomizedUnlimitedIteratorTest {

	@Test
	public void testNextReturnsElementOfSubsequentIndexWhenRngReturnsSumOfNormalizedWeightsOfSuccessiveElements() {
		Random random = mock(Random.class);
		when(random.nextDouble()).thenReturn(0.5);
		WeightedElement<String> expected = new WeightedElement<>(3, "third");
		List<WeightedElement<String>> list = Arrays.asList(
				new WeightedElement<>(1, "first"),
				new WeightedElement<>(2, "second"),
				expected
		);
		WeightedRandomizedUnlimitedIterator<String> iterator = new WeightedRandomizedUnlimitedIterator<>(list, random);

		WeightedElement<String> next = iterator.next();

		assertSame(expected, next);
	}

	@Test
	public void testNextReturnsElementOfSameIndexWhenRngReturnsValueBelowSumOfNormalizedWeightsOfSuccessiveElements() {
		Random random = mock(Random.class);
		when(random.nextDouble()).thenReturn(0.499999999);
		WeightedElement<String> expected = new WeightedElement<>(2, "second");
		List<WeightedElement<String>> list = Arrays.asList(
				new WeightedElement<>(1, "first"),
				expected,
				new WeightedElement<>(3, "third")
		);
		WeightedRandomizedUnlimitedIterator<String> iterator = new WeightedRandomizedUnlimitedIterator<>(list, random);

		WeightedElement<String> next = iterator.next();

		assertSame(expected, next);
	}

	@Test
	public void testNextReturnsLastElementWhenRngReturnsValueSlightlyBelowOne() {
		Random random = mock(Random.class);
		when(random.nextDouble()).thenReturn(0.99999999);
		WeightedElement<String> expected = new WeightedElement<>(3, "third");
		List<WeightedElement<String>> list = Arrays.asList(
				new WeightedElement<>(1, "first"),
				new WeightedElement<>(2, "second"),
				expected
		);
		WeightedRandomizedUnlimitedIterator<String> iterator = new WeightedRandomizedUnlimitedIterator<>(list, random);

		WeightedElement<String> next = iterator.next();

		assertSame(expected, next);
	}

	@Test
	public void testNextReturnsCorrectElementAfterRemoveHasBeenCalled() {
		Random random = mock(Random.class);
		when(random.nextDouble()).thenReturn(0.2);
		WeightedElement<String> expected = new WeightedElement<>(1, "first");
		List<WeightedElement<String>> list = new ArrayList<>();
		list.add(expected);
		list.add(new WeightedElement<>(2, "second"));
		list.add(new WeightedElement<>(3, "third"));
		WeightedRandomizedUnlimitedIterator<String> iterator = new WeightedRandomizedUnlimitedIterator<>(list, random);
		iterator.next();
		iterator.remove();

		WeightedElement<String> next = iterator.next();

		assertSame(expected, next);
	}

	@Test
	public void testHasNextReturnsAlwaysTrueIfListHasElements() {
		WeightedRandomizedUnlimitedIterator<String> iterator = new WeightedRandomizedUnlimitedIterator<>(
				Collections.singletonList(new WeightedElement<>(1, "dummy")));

		assertTrue(iterator.hasNext());
	}

	@Test
	public void testHasNextReturnsAlwaysFalseIfListIsEmpty() {
		WeightedRandomizedUnlimitedIterator<String> iterator = new WeightedRandomizedUnlimitedIterator<>(Collections.emptyList());

		assertFalse(iterator.hasNext());
	}

	@Test
	public void testRemoveElementOfLastCallOfNextFromUnderlyingList() {
		List<WeightedElement<String>> list = new ArrayList<>();
		list.add(new WeightedElement<>(1, "first"));
		list.add(new WeightedElement<>(2, "second"));
		list.add(new WeightedElement<>(3, "third"));
		WeightedRandomizedUnlimitedIterator<String> iterator = new WeightedRandomizedUnlimitedIterator<>(list);
		WeightedElement<String> elementToBeRemoved = iterator.next();
		Assumptions.assumeTrue(list.contains(elementToBeRemoved));

		iterator.remove();

		assertFalse(list.contains(elementToBeRemoved));
	}

	@Test
	public void testRemoveThrowsIllegalStateExceptionIfNextHasNotBeenCalled() {
		WeightedRandomizedUnlimitedIterator<String> iterator = new WeightedRandomizedUnlimitedIterator<>(
				Collections.singletonList(new WeightedElement<>(1, "dummy")));

		assertThrows(IllegalStateException.class, iterator::remove);
	}

	@Test
	public void testRemoveThrowsIllegalStateExceptionIfRemoveHasAlreadyBeenCalled() {
		List<WeightedElement<String>> list = new ArrayList<>();
		list.add(new WeightedElement<>(1, "dummy"));
		WeightedRandomizedUnlimitedIterator<String> iterator = new WeightedRandomizedUnlimitedIterator<>(list);
		iterator.next();
		iterator.remove();

		assertThrows(IllegalStateException.class, iterator::remove);
	}
}
