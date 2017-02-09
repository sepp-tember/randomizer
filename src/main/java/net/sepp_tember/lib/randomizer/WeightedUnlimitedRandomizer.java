package net.sepp_tember.lib.randomizer;

import java.util.Random;

@Deprecated
public class WeightedUnlimitedRandomizer implements Randomizer {

	private Random rng = new Random();
	private double[] limits;

	public WeightedUnlimitedRandomizer(double[] weights) {
		limits = new double[weights.length - 1];
		double sum = 0;
		for (int i = 0; i < weights.length - 1; i++) {
			sum = sum + weights[i];
			limits[i] = sum;
		}
	}

	void setRng(Random rng) {
		this.rng = rng;
	}

	@Override
	public boolean hasNext() {
		return true;
	}

	@Override
	public int nextIndex() {
		double random = rng.nextDouble();
		for (int i = 0; i < limits.length; i++) {
			if (random < limits[i]) {
				return i;
			}
		}
		return limits.length;
	}
}
