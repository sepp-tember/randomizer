package net.sepp_tember.lib.randomizer;

import java.util.List;
import java.util.Random;

public interface Randomizer<T> {

	T next();

	void setSource(List<T> source);

	void setRng(Random rng);
}
