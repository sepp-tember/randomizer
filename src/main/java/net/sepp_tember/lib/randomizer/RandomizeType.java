package net.sepp_tember.lib.randomizer;

import java.util.Collection;

public interface RandomizeType {
	Randomizer createRandomizer(Collection<?> source);
}
