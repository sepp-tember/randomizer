package net.sepp_tember.lib.randomizer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

class RandomizedIteratorTest {

	@Test
	public void testHasNext_DelegatesCallToRandomizer() {
		boolean expected = true;
		List<Object> dummyList = new ArrayList<>();
		Randomizer randomizer = mock(Randomizer.class);
		when(randomizer.hasNext()).thenReturn(expected);
		RandomizedIterator<Object> iterator = new RandomizedIterator<>(dummyList, randomizer);
		boolean actual = iterator.hasNext();
		assertEquals(expected, actual);
		verify(randomizer).hasNext();
	}

	@Test
	public void testNext_ReturnsElementOfIndexProvidedByRandomizer() {
		int index = 3;
		List<Object> elements = IntStream.range(0, 5).mapToObj(Integer::valueOf).collect(Collectors.toList());
		Object expectedElement = elements.get(index);
		Randomizer randomizer = mock(Randomizer.class);
		when(randomizer.nextIndex()).thenReturn(index);
		RandomizedIterator<Object> iterator = new RandomizedIterator<>(elements, randomizer);
		Object actualElement = iterator.next();
		assertEquals(expectedElement, actualElement);
	}

	@Test
	public void testRemove_ThrowsUnsupportedOperationException() {
		List<Object> dummyList = new ArrayList<>();
		Randomizer dummyRandomizer = mock(Randomizer.class);
		RandomizedIterator<Object> iterator = new RandomizedIterator<>(dummyList, dummyRandomizer);
		assertThrows(UnsupportedOperationException.class, iterator::remove);
	}
}