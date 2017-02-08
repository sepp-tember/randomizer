package net.sepp_tember.lib.randomizer;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import net.sepp_tember.lib.randomizer.WeightedRandomizedList.WeightedElement;
import org.junit.jupiter.api.Test;

public class WeightedElementTest {

	@Test
	public void testGetWeightProvidedByConstructor() {
		String dummyElement = "dummy";
		double expectedWeight = 0.1234;
		WeightedElement<String> weightedElement = new WeightedElement<>(expectedWeight, dummyElement);

		double actualWeight = weightedElement.getWeight();

		assertEquals(expectedWeight, actualWeight);
	}

	@Test
	public void testGetElementProvidedByConstructor() {
		double dummyWeight = 0;
		String expectedElement = "expected";
		WeightedElement<String> weightedElement = new WeightedElement<>(dummyWeight, expectedElement);

		String actualElement = weightedElement.getElement();

		assertEquals(expectedElement, actualElement);
	}

	@Test
	public void testConstructorThrowsIllegalArgumentExceptionIfWeightIsNegative() {
		String dummyElement = "dummy";

		assertThrows(IllegalArgumentException.class, () -> new WeightedElement<>(-0.1, dummyElement));
	}

	@Test
	public void testEqualsIsFalseWhenComparedToNull() {
		WeightedElement<String> weightedElement = new WeightedElement<>(0, "dummy");

		assertFalse(weightedElement.equals(null));
	}

	@Test
	public void testEqualsIsTrueIfWeightAndElementIsEqual() {
		WeightedElement<String> someElement = new WeightedElement<>(0.1, "value");
		WeightedElement<String> anotherElement = new WeightedElement<>(0.1, "value");

		assertTrue(someElement.equals(anotherElement));
	}

	@Test
	public void testEqualsIsFalseIfWeightDiffers() {
		WeightedElement<String> someElement = new WeightedElement<>(0.1, "value");
		WeightedElement<String> anotherElement = new WeightedElement<>(0.2, "value");

		assertFalse(someElement.equals(anotherElement));
	}

	@Test
	public void testEqualsIsFalseIfElementIsNotEqual() {
		WeightedElement<String> someElement = new WeightedElement<>(0.1, "value");
		WeightedElement<String> anotherElement = new WeightedElement<>(0.1, "not equal");

		assertFalse(someElement.equals(anotherElement));
	}

	@Test
	public void testEqualsIsFalseWhenThisElementIsNullAndOtherIsNonNull() {
		WeightedElement<String> someElement = new WeightedElement<>(0.1, null);
		WeightedElement<String> anotherElement = new WeightedElement<>(0.1, "not null");

		assertFalse(someElement.equals(anotherElement));
	}

	@Test
	public void testEqualsIsFalseWhenThisElementIsNonNullAndOtherIsNull() {
		WeightedElement<String> someElement = new WeightedElement<>(0.1, "not null");
		WeightedElement<String> anotherElement = new WeightedElement<>(0.1, null);

		assertFalse(someElement.equals(anotherElement));
	}

	@Test
	public void testEqualsIsTrueWhenThisElementIsNullAndOtherIsNull() {
		WeightedElement<String> someElement = new WeightedElement<>(0.1, null);
		WeightedElement<String> anotherElement = new WeightedElement<>(0.1, null);

		assertTrue(someElement.equals(anotherElement));
	}

	@Test
	public void testEqualsIsTrueWhenComparedToSameObject() {
		WeightedElement<String> someElement = new WeightedElement<>(0.1, "value");

		assertTrue(someElement.equals(someElement));
	}

	@Test
	public void testEqualsIsFalseWhenComparedToObjectOfAnotherType() {
		WeightedElement<String> someElement = new WeightedElement<>(0.1, "value");

		assertFalse(someElement.equals("some object"));
	}

	@Test
	public void testEqualsIsTrueWhenComparedToOtherObjectWhichIsEqualToThisObject() {
		WeightedElement<String> someElement = new WeightedElement<>(0.1, "value");
		WeightedElement<String> anotherElement = new WeightedElement<>(0.1, "value");
		assumeTrue(anotherElement.equals(someElement));

		assertTrue(someElement.equals(anotherElement));
	}

	@Test
	public void testEqualsIsTransitive() {
		WeightedElement<String> elementX = new WeightedElement<>(0.1, "value");
		WeightedElement<String> elementY = new WeightedElement<>(0.1, "value");
		WeightedElement<String> elementZ = new WeightedElement<>(0.1, "value");
		assumeTrue(elementX.equals(elementY));
		assumeTrue(elementY.equals(elementZ));

		assertTrue(elementX.equals(elementZ));
	}

	@Test
	public void testHashCodeReturnsSameHashOnSeveralInvocations() {
		WeightedElement<String> element = new WeightedElement<>(0.1, "value");

		int firstHash = element.hashCode();
		int secondHash = element.hashCode();

		assertEquals(firstHash, secondHash);
	}

	@Test
	public void testHashCodeReturnsSameHashForEqualObjects() {
		WeightedElement<String> someElement = new WeightedElement<>(0.1, "value");
		WeightedElement<String> anotherElement = new WeightedElement<>(0.1, "value");
		assumeTrue(someElement.equals(anotherElement));

		assertEquals(someElement.hashCode(), anotherElement.hashCode());
	}
}
