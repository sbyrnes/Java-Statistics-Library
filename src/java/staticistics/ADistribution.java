package java.staticistics;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

/** 
 * Represents an abstract distribution of samples. 
 * 
 * Internal computations are all done using BigDecimals.
 * @author Sean Byrnes
 */
public class ADistribution {
	public static final BigDecimal TWO = new BigDecimal(2);
	public static final BigDecimal ZERO = new BigDecimal(0);
	
	Set<BigDecimal> samples;
	BigDecimal count;
	
	public ADistribution(Set<BigDecimal> samples)
	{
		this.samples = samples;
		this.count = new BigDecimal(samples.size());
	}
	
	/**
	 * Retrieve the median of the distribution defined as the value 
	 * where 50% of the values in the sample are greater (or equal) and 
	 * 50% of the values in the sample are less (or equal).
	 * 
	 * @return BigDecimal Median
	 */
	public BigDecimal getMedian()
	{
		BigDecimal median = ZERO;
		
		ArrayList<BigDecimal> sortedSamples = new ArrayList<BigDecimal>(samples);
		Collections.sort(sortedSamples);
		
		// TODO: Find a better even/odd test
		if(count.remainder(TWO).equals(ZERO)) // even
		{
			// The median of an even set is the mean of the values at n/2 and n/2+1
			int medianIndex1 = count.divide(TWO,  						// divide by two
											count.scale(), 				// use the same scale
											BigDecimal.ROUND_CEILING)  	// round to the ceiling
											.intValue();
			int medianIndex2 = count.divide(TWO,  						// divide by two
										    count.scale(), 				// use the same scale
										    BigDecimal.ROUND_FLOOR)  	// round to the floor
										    .intValue();
			
			BigDecimal medianValue1 = sortedSamples.get(medianIndex1);  // n/2 value
			BigDecimal medianValue2 = sortedSamples.get(medianIndex2);  // n/2+1 value
			
			median = medianValue1.add(medianValue2).divide(TWO); 		// take the mean
		} else {
			// The median of an odd set is the value at ceiling(n/2)
			int medianIndex = count.divide(TWO,  	// divide by two
						 count.scale(), 			// use the same scale
						 BigDecimal.ROUND_CEILING)  // round to the ceiling
				 .intValue();
						 
			median = sortedSamples.get(medianIndex);
		}
		
		return median;
	}

	/**
	 * Retrieve the mean of the distribution, defined as the sum
	 * of all values divided by the count of values.
	 * 
	 * @return BigDecimal Median
	 */
	public BigDecimal getMean()
	{
		BigDecimal count = new BigDecimal(samples.size());
		BigDecimal sum = getSum();
		BigDecimal mean = sum.divide(count);
		
		return mean;
	}

	/**
	 * Retrieve the sum of all values in the sample.
	 * 
	 * @return BigDecimal Sum
	 */
	protected BigDecimal getSum()
	{
		BigDecimal sum = new BigDecimal(0);
		for(BigDecimal value : samples)
		{
			sum = sum.add(value);
		}
		
		return sum;
	}
}
