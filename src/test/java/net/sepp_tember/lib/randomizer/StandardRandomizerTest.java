package net.sepp_tember.lib.randomizer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class StandardRandomizerTest {

	private static final Object EXPECTED_ELEMENT = new Object();
	private static final int NUMBER_OF_ELEMENTS = 7;
	private static final int INDEX_OF_EXPECTED = 3;

	private StandardRandomizer setupRandomizer(List<Object> source, Random rng) {
		StandardRandomizer<Object> randomizer = new StandardRandomizer<>();
		randomizer.setSource(source);
		randomizer.setRng(rng);
		return randomizer;
	}

	private StandardRandomizer setupRandomizer(int randomNumber) {
		List<Object> source = setupSourceList(NUMBER_OF_ELEMENTS);
		source.set(INDEX_OF_EXPECTED, EXPECTED_ELEMENT);

		StandardRandomizer<Object> randomizer = new StandardRandomizer<>();
		randomizer.setSource(source);

		Random randomMock = setupRngMock(randomNumber);
		randomizer.setRng(randomMock);

		return randomizer;
	}

	private Random setupRngMock(int randomNumber) {
		Random randomMock = mock(Random.class);
		Mockito.stub(randomMock.nextInt(Mockito.anyInt())).toReturn(randomNumber);
		return randomMock;
	}

	private List<Object> setupSourceList(int numberOfElements) {
		List<Object> source = new ArrayList<>();
		for (int i = 0; i < numberOfElements; i++) {
			source.add(new Object());
		}
		return source;
	}

	@Test
	public void testNext_ReturnsSomeOtherElementWhenRNGReturnsNumberBelowIndex() {
		Randomizer randomizer = setupRandomizer(INDEX_OF_EXPECTED - 1);
		assertNotEquals(EXPECTED_ELEMENT, randomizer.next());
	}

	@Test
	public void testNext_ReturnsSomeExpectedElementWhenRNGReturnsNumberAtIndex() {
		Randomizer randomizer = setupRandomizer(INDEX_OF_EXPECTED);
		assertEquals(EXPECTED_ELEMENT, randomizer.next());
	}

	@Test
	public void testNext_ReturnsSomeOtherElementWhenRNGReturnsNumberAboveIndex() {
		Randomizer randomizer = setupRandomizer(INDEX_OF_EXPECTED + 1);
		assertNotEquals(EXPECTED_ELEMENT, randomizer.next());
	}

	@Test
	public void testNext_RngGetsCalledWithSizeOfList() {
		int listSize = 5;
		List<Object> source = setupSourceList(listSize);

		Random rng = setupRngMock(2);
		StandardRandomizer randomizer = setupRandomizer(source, rng);
		randomizer.next();

		verify(rng).nextInt(listSize);
	}

}
