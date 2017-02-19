package net.sepp_tember.lib.randomizer;

import java.util.Random;

/**
 * A randomizer that produces an endless sequence of indexes with a weighted
 * distribution.
 * <p>
 * <a name="weight-description" id="weight-description" />
 * The sum of the weights given at construction should be equal to 1. If the
 * sum exceeds 1, all weights above this limit will be ignored. The first
 * weight which exceeds the sum of 1 is truncated to a weight which would lead
 * to the exact sum of 1. For example three weights like
 * {@code [0.7, 0.5, 0.2]} are effectively like {@code [0.7, 0.3, 0.0]},
 * as the weight of 0.5 exceeds the sum of 1 it is truncated to 0.3 and the
 * weight of 0.2 is totally ignored.
 */
@Deprecated
public class WeightedUnlimitedRandomizer implements Randomizer {

	private Random rng = new Random();
	private double[] limits;

	/**
	 * Constructs a randomizer for weighted distribution with the specified
	 * weights.
	 *
	 * @param weights the weights for the distribution
	 *
	 * @see <a href="weight-description">description of weights</a>
	 */
	public WeightedUnlimitedRandomizer(double[] weights) {
		limits = new double[weights.length - 1];
		double sum = 0;
		for (int i = 0; i < weights.length - 1; i++) {
			sum = sum + weights[i];
			limits[i] = sum;
		}
	}

	/**
	 * Sets the random number generator used to create random distribution.
	 *
	 * @param rng random number generator used for random distribution
	 */
	void setRng(Random rng) {
		this.rng = rng;
	}

	/**
	 * Always returns {@code true} as the sequence of indexes is endless.
	 *
	 * @return Always {@code true} as the sequence is endless
	 */
	@Override
	public boolean hasNext() {
		return true;
	}

	/**
	 * Returns next index in the sequence.
	 *
	 * @return next index in the sequence
	 */
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
