package net.sepp_tember.lib.randomizer;

import java.util.Collection;

@Deprecated
public interface RandomizeType {
	Randomizer createRandomizer(Collection<?> source);
}
