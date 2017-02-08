package net.sepp_tember.lib.randomizer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import net.sepp_tember.lib.randomizer.WeightedRandomizedList.WeightedElement;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

public class WeightedRandomizedListTest {

	private Supplier<WeightedRandomizedList<Object>> supplier = WeightedRandomizedList::new;
	private BiConsumer<WeightedRandomizedList<Object>, List<WeightedElement<Object>>> injector = WeightedRandomizedList::setBackingList;

	private DelegationTester<WeightedRandomizedList<Object>, List<WeightedElement<Object>>> delegationTester =
			new DelegationTester<>(supplier, injector, cast(List.class));

	private WeightedElement<Object> element = new WeightedElement<>(0, new Object());

	@TestFactory
	public Collection<DynamicTest> createDelegateToBackingListTests() {
		return Arrays.asList(
				dynamicTest("add element", () -> delegationTester.testReturningCall(List::add, true, element)),
				dynamicTest("add element indexed", () -> delegationTester.testVoidCall(List::add, 3, element)),
				dynamicTest("add collection", () -> delegationTester.testReturningCall(List::addAll, true,
						new ArrayList<WeightedElement<Object>>())),
				dynamicTest("add collection indexed", () -> delegationTester.testReturningCall(List::addAll, true, 3,
						new ArrayList<WeightedElement<Object>>())),
				dynamicTest("clear", () -> delegationTester.testVoidCall(List::clear)),
				dynamicTest("contains", () -> delegationTester.testReturningCall(List::contains, true, new Object())),
				dynamicTest("contains all", () -> delegationTester.testReturningCall(List::containsAll, true, new ArrayList<>())),
				dynamicTest("get", () -> delegationTester.testReturningCall(List::get, element, 3)),
				dynamicTest("index of", () -> delegationTester.testReturningCall(List::indexOf, 3, new Object())),
				dynamicTest("is empty", () -> delegationTester.testReturningCall(List::isEmpty, true)),
				dynamicTest("iterator", () -> delegationTester.testReturningCall(List::iterator, new FakeListIterator())),
				dynamicTest("last index of", () -> delegationTester.testReturningCall(List::lastIndexOf, 3, new Object())),
				dynamicTest("list iterator", () -> delegationTester.testReturningCall(List::listIterator, new FakeListIterator())),
				dynamicTest("list iterator index", () -> delegationTester.testReturningCall(List::listIterator, new FakeListIterator(), 3)),
				dynamicTest("remove indexed", () -> delegationTester.testReturningCall(List::remove, element, 3)),
				dynamicTest("remove object", () -> delegationTester.testReturningCall(List::remove, true, new Object())),
				dynamicTest("remove all", () -> delegationTester.testReturningCall(List::removeAll, true, new ArrayList<>())),
				dynamicTest("retain all", () -> delegationTester.testReturningCall(List::retainAll, true, new ArrayList<>())),
				dynamicTest("set", () -> delegationTester.testReturningCall(List::set, element, 3, element)),
				dynamicTest("size", () -> delegationTester.testReturningCall(List::size, 5)),
				dynamicTest("sublist", () -> delegationTester.testReturningCall(List::subList, new ArrayList(), 3, 5)),
				dynamicTest("to object array", () -> delegationTester.testReturningCall(List::toArray, new Object[] {})),
				dynamicTest("to typed array", () -> delegationTester.testReturningCall(List::toArray, new Object[] {}, new Object[] {}))
		);
	}

	@Test
	public void testRandomizedIteratorReturnsWeightedRandomizedUnlimitedIterator() {
		WeightedRandomizedList<WeightedElement<Object>> list = new WeightedRandomizedList<>();
		assertEquals(WeightedRandomizedUnlimitedIterator.class, list.randomizedIterator().getClass());
	}

	@Test
	public void testCopyConstructorSetsAllElements() {
		List<WeightedElement<String>> expectedElements = Arrays.asList(
				new WeightedElement<>(0.1, "first"),
				new WeightedElement<>(0.2, "second"),
				new WeightedElement<>(0.3, "third")
		);

		WeightedRandomizedList<String> actualElements = new WeightedRandomizedList<>(expectedElements);

		assertEquals(expectedElements, actualElements);
	}

	@Test
	public void testConstructorCombinesElementAndWeight() {
		List<WeightedElement<String>> expectedElements = Arrays.asList(
				new WeightedElement<>(0.1, "first"),
				new WeightedElement<>(0.2, "second"),
				new WeightedElement<>(0.3, "third")
		);
		List<Double> weights = extractWeights(expectedElements);
		List<String> elements = extractElements(expectedElements);

		WeightedRandomizedList<String> actualElements = new WeightedRandomizedList<>(weights, elements);

		assertEquals(expectedElements, actualElements);
	}

	private List<String> extractElements(List<WeightedElement<String>> weightedElements) {
		return weightedElements.stream().map(WeightedElement::getElement).collect(Collectors.toList());
	}

	private List<Double> extractWeights(List<? extends WeightedElement> weightedElements) {
		return weightedElements.stream().map(WeightedElement::getWeight).collect(Collectors.toList());
	}

	@Test
	public void testConstructorThrowsExceptionWhenSizeOfElementsAndWeightsDiffer() {
		List<Double> weights = Arrays.asList(0.1, 0.2);
		List<String> elements = Arrays.asList("first", "second", "third");

		assertThrows(IllegalArgumentException.class, () -> new WeightedRandomizedList<>(weights, elements));
	}

	@Test
	public void testConstructorThrowsExceptionWhenElementsListIsNull() {
		List<Double> weights = new ArrayList<>();

		assertThrows(NullPointerException.class, () -> new WeightedRandomizedList<>(weights, null));
	}

	@Test
	public void testConstructorThrowsExceptionWhenWeightsListIsNull() {
		List<Double> elements = new ArrayList<>();

		assertThrows(NullPointerException.class, () -> new WeightedRandomizedList<>(null, elements));
	}

	@SuppressWarnings("unchecked")
	private <T1 extends T2, T2> Class<T1> cast(Class<T2> type) {
		return (Class<T1>) type;
	}
}
