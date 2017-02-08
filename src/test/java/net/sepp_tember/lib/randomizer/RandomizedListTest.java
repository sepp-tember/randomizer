package net.sepp_tember.lib.randomizer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

class RandomizedListTest {

	private class DirectRandomizer implements Randomizer {
		private int size;
		private int index = 0;

		DirectRandomizer(int size) {
			this.size = size;
		}

		@Override
		public boolean hasNext() {
			return index < size;
		}

		@Override
		public int nextIndex() {
			return index++;
		}
	}

	private static final RandomizeType dummyType = new RandomizeType() {
		@Override
		public Randomizer createRandomizer(Collection<?> source) {
			return null;
		}
	};

	@Test
	void testSize_DelegatesToBackingList() {
		int expectedSize = 5;
		@SuppressWarnings("unchecked")
		List<Object> backingList = mock(List.class);
		when(backingList.size()).thenReturn(expectedSize);
		List<Object> list = new DeprecatedRandomizedList<>(backingList, dummyType);

		int actualSize = list.size();

		assertEquals(expectedSize, actualSize);
		verify(backingList).size();
	}

	@Test
	void testIsEmpty_DelegatesToBackingList() {
		boolean expectedEmptiness = true;
		List<Object> backingList = mock(List.class);
		when(backingList.isEmpty()).thenReturn(expectedEmptiness);
		List<Object> list = new DeprecatedRandomizedList<>(backingList, dummyType);

		boolean actualEmptiness = list.isEmpty();

		assertEquals(expectedEmptiness, actualEmptiness);
		verify(backingList).isEmpty();
	}

	@Test
	void testContains_DelegatesToBackingList() {
		boolean expectedContainess = true;
		Object searchObject = new Object();
		List<Object> backingList = mock(List.class);
		when(backingList.contains(searchObject)).thenReturn(expectedContainess);
		List<Object> list = new DeprecatedRandomizedList<>(backingList, dummyType);

		boolean actualContainess = list.contains(searchObject);

		assertEquals(expectedContainess, actualContainess);
		verify(backingList).contains(searchObject);
	}

	@Test
	public void testIterator_ReturnsRandomizedIterator() {
		List<Object> dummyList = new ArrayList<>();
		List<Object> list = new DeprecatedRandomizedList<>(dummyList, dummyType);
		Iterator<Object> iterator = list.iterator();
		assertTrue(iterator instanceof RandomizedIterator);
	}

	@Test
	public void testIterator_CreatesNewRandomizerViaRandomizeType() {
		RandomizeType randomizeType = mock(RandomizeType.class);
		List<Object> dummyList = new ArrayList<>();
		List<Object> list = new DeprecatedRandomizedList<>(dummyList, randomizeType);
		list.iterator();
		verify(randomizeType).createRandomizer(dummyList);
	}

	@Test
	public void testIterator_InjectsBackingListIntoRandomizedIterator() {
		List<Object> backingList = IntStream.range(0, 3).mapToObj(Integer::valueOf).collect(Collectors.toList());
		DeprecatedRandomizedList<Object> list = new DeprecatedRandomizedList<>(backingList, source -> new DirectRandomizer(source.size()));
		Iterator<Object> iterator = list.iterator();
		List<Object> actualList = new ArrayList<>();
		while (iterator.hasNext()) {
			actualList.add(iterator.next());
		}
		assertEquals(backingList, actualList);
	}

	@Test
	void testToArray_DelegatesToBackingList() {
		Object[] expectedArray = {};
		List<Object> backingList = mock(List.class);
		when(backingList.toArray()).thenReturn(expectedArray);
		List<Object> list = new DeprecatedRandomizedList<>(backingList, dummyType);

		Object[] actualArray = list.toArray();

		assertEquals(expectedArray, actualArray);
		verify(backingList).toArray();
	}

	@Test
	void testToArrayArray_DelegatesToBackingList() {
		Object[] expectedArray = {};
		Object[] initialArray = {};
		List<Object> backingList = mock(List.class);
		when(backingList.toArray(initialArray)).thenReturn(expectedArray);
		List<Object> list = new DeprecatedRandomizedList<>(backingList, dummyType);

		Object[] actualArray = list.toArray(initialArray);

		assertEquals(expectedArray, actualArray);
		verify(backingList).toArray(initialArray);
	}

	@Test
	void testAddObject_ThrowsUnsupportedOperationException() {
		Object dummyObject = new Object();
		List<Object> backingList = mock(List.class);
		List<Object> list = new DeprecatedRandomizedList<>(backingList, dummyType);

		assertThrows(UnsupportedOperationException.class, () -> list.add(dummyObject));
		verifyZeroInteractions(backingList);
	}

	@Test
	void testRemoveObject_ThrowsUnsupportedOperationException() {
		Object dummyObject = new Object();
		List<Object> backingList = mock(List.class);
		List<Object> list = new DeprecatedRandomizedList<>(backingList, dummyType);

		assertThrows(UnsupportedOperationException.class, () -> list.remove(dummyObject));
		verifyZeroInteractions(backingList);
	}

	@Test
	void testContainsAll_DelegatesToBackingList() {
		boolean expectedContainess = true;
		Collection<Object> searchCollection = new ArrayList<>();
		List<Object> backingList = mock(List.class);
		when(backingList.containsAll(searchCollection)).thenReturn(expectedContainess);
		List<Object> list = new DeprecatedRandomizedList<>(backingList, dummyType);

		boolean actualContainess = list.containsAll(searchCollection);

		assertEquals(expectedContainess, actualContainess);
		verify(backingList).containsAll(searchCollection);
	}

	@Test
	void testAddAllCollection_ThrowsUnsupportedOperationException() {
		Collection<Object> dummyCollection = new ArrayList<>();
		List<Object> backingList = mock(List.class);
		List<Object> list = new DeprecatedRandomizedList<>(backingList, dummyType);

		assertThrows(UnsupportedOperationException.class, () -> list.addAll(dummyCollection));
		verifyZeroInteractions(backingList);
	}

	@Test
	void testAddAllIndexedCollection_ThrowsUnsupportedOperationException() {
		Collection<Object> dummyCollection = new ArrayList<>();
		int dummyIndex = 3;
		List<Object> backingList = mock(List.class);
		List<Object> list = new DeprecatedRandomizedList<>(backingList, dummyType);

		assertThrows(UnsupportedOperationException.class, () -> list.addAll(dummyIndex, dummyCollection));
		verifyZeroInteractions(backingList);
	}

	@Test
	void testRemoveAll_ThrowsUnsupportedOperationException() {
		Collection<Object> dummyCollection = new ArrayList<>();
		List<Object> backingList = mock(List.class);
		List<Object> list = new DeprecatedRandomizedList<>(backingList, dummyType);

		assertThrows(UnsupportedOperationException.class, () -> list.removeAll(dummyCollection));
		verifyZeroInteractions(backingList);
	}

	@Test
	void testRetainAll_ThrowsUnsupportedOperationException() {
		Collection<Object> dummyCollection = new ArrayList<>();
		List<Object> backingList = mock(List.class);
		List<Object> list = new DeprecatedRandomizedList<>(backingList, dummyType);

		assertThrows(UnsupportedOperationException.class, () -> list.retainAll(dummyCollection));
		verifyZeroInteractions(backingList);
	}

	@Test
	void testClear_ThrowsUnsupportedOperationException() {
		List<Object> backingList = mock(List.class);
		List<Object> list = new DeprecatedRandomizedList<>(backingList, dummyType);

		assertThrows(UnsupportedOperationException.class, () -> list.clear());
		verifyZeroInteractions(backingList);
	}

	@Test
	void testGet_DelegatesToBackingList() {
		int expectedIndex = 5;
		Object expectedReturnValue = new Object();
		List<Object> backingList = mock(List.class);
		when(backingList.get(expectedIndex)).thenReturn(expectedReturnValue);
		List<Object> list = new DeprecatedRandomizedList<>(backingList, dummyType);

		Object actualReturnValue = list.get(expectedIndex);

		assertEquals(expectedReturnValue, actualReturnValue);
		verify(backingList).get(expectedIndex);
	}

	@Test
	void testSet_ThrowsUnsupportedOperationException() {
		Object dummyElement = new Object();
		int dummyIndex = 3;
		List<Object> backingList = mock(List.class);
		List<Object> list = new DeprecatedRandomizedList<>(backingList, dummyType);

		assertThrows(UnsupportedOperationException.class, () -> list.set(dummyIndex, dummyElement));
		verifyZeroInteractions(backingList);
	}

	@Test
	void testAddIndexedElement_ThrowsUnsupportedOperationException() {
		Object dummyElement = new Object();
		int dummyIndex = 3;
		List<Object> backingList = mock(List.class);
		List<Object> list = new DeprecatedRandomizedList<>(backingList, dummyType);

		assertThrows(UnsupportedOperationException.class, () -> list.add(dummyIndex, dummyElement));
		verifyZeroInteractions(backingList);
	}

	@Test
	void testRemoveIndexedElement_ThrowsUnsupportedOperationException() {
		int dummyIndex = 3;
		List<Object> backingList = mock(List.class);
		List<Object> list = new DeprecatedRandomizedList<>(backingList, dummyType);

		assertThrows(UnsupportedOperationException.class, () -> list.remove(dummyIndex));
		verifyZeroInteractions(backingList);
	}

	@Test
	void testIndexOf_DelegatesToBackingList() {
		Object searchObject = new Object();
		int expectedIndex = 5;
		List<Object> backingList = mock(List.class);
		when(backingList.indexOf(searchObject)).thenReturn(expectedIndex);
		List<Object> list = new DeprecatedRandomizedList<>(backingList, dummyType);

		int actualIndex = list.indexOf(searchObject);

		assertEquals(expectedIndex, actualIndex);
		verify(backingList).indexOf(searchObject);
	}

	@Test
	void testLastIndexOf_DelegatesToBackingList() {
		Object searchObject = new Object();
		int expectedIndex = 5;
		List<Object> backingList = mock(List.class);
		when(backingList.lastIndexOf(searchObject)).thenReturn(expectedIndex);
		List<Object> list = new DeprecatedRandomizedList<>(backingList, dummyType);

		int actualIndex = list.lastIndexOf(searchObject);

		assertEquals(expectedIndex, actualIndex);
		verify(backingList).lastIndexOf(searchObject);
	}

	@Test
	void testListIterator_ThrowsUnsupportedOperationException() {
		List<Object> backingList = mock(List.class);
		List<Object> list = new DeprecatedRandomizedList<>(backingList, dummyType);

		assertThrows(UnsupportedOperationException.class, () -> list.listIterator());
		verifyZeroInteractions(backingList);
	}

	@Test
	void testListIteratorIndexed_ThrowsUnsupportedOperationException() {
		int dummyIndex = 3;
		List<Object> backingList = mock(List.class);
		List<Object> list = new DeprecatedRandomizedList<>(backingList, dummyType);

		assertThrows(UnsupportedOperationException.class, () -> list.listIterator(dummyIndex));
		verifyZeroInteractions(backingList);
	}

	@Test
	void testSubList_DelegatesToBackingList() {
		int fromIndex = 1;
		int toIndex = 2;
		List<Object> dummyList = new ArrayList<>();
		List<Object> backingList = mock(List.class);
		when(backingList.subList(fromIndex, toIndex)).thenReturn(dummyList);
		List<Object> list = new DeprecatedRandomizedList<>(backingList, dummyType);

		list.subList(fromIndex, toIndex);

		verify(backingList).subList(fromIndex, toIndex);
	}
}