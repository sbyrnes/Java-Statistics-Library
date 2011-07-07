package java.staticistics;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Represents a normal distribution, also known as a bell curve.
 * 
 * @author Sean Byrnes
 */
public class NormalDistribution extends ADistribution {

	public NormalDistribution(Set<BigDecimal> samples) {
		super(samples);
	}

	/**
	 * Retrieve the variance of the distribution, defined as the sum of the squares of the
	 * difference between all values in the sample and the mean, divided by the count.
	 * 
	 * @return BigDecimal Variance
	 */
	public BigDecimal getVariance()
	{
		BigDecimal mean = getMean();

		BigDecimal sumOfSquaredDifferences = new BigDecimal(0);
		for(BigDecimal value : samples)
		{
			sumOfSquaredDifferences.add(value.subtract(mean) // difference from the mean
											 .pow(2));       // squared
		}
		
		// divide by the count of samples
		BigDecimal variance = sumOfSquaredDifferences.divide(count);
		
		return variance;
	}

	/**
	 * Retrieve the standardDeviation of the distribution, defined 
	 * as the square root of the variance.
	 * 
	 * @return BigDecimal Standard Deviation
	 */
	public BigDecimal getStandardDeviation()
	{
		return getVariance().pow(-2); // Square root of the variance
	}
}
