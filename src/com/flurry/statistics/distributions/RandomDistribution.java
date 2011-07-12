package com.flurry.statistics.distributions;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Represents a random distribution that fits no known model.
 * 
 * @author Sean Byrnes
 */
public class RandomDistribution extends AUnivariateDistribution {

	public RandomDistribution(Collection<BigDecimal> samples) {
		super(samples);
	}

}
