package net.sepp_tember.lib.randomizer;

import java.util.Collection;

/**
 * A randomize type is intended to create a randomizer of a specific type. The
 * type of a randomizer could be for example equal distribution, or also a
 * weighted distribution.
 */
@Deprecated
public interface RandomizeType {

	/**
	 * Should create a randomizer of specific type for the given collection.
	 *
	 * @param source the collection the randomizer should be created for.
	 *
	 * @return a randomizer for the specified collection
	 */
	Randomizer createRandomizer(Collection<?> source);
}
