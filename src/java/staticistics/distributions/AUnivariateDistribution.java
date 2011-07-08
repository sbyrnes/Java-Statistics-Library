package java.staticistics.distributions;

import java.math.BigDecimal;
import java.staticistics.Statistics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/** 
 * Represents an abstract distribution of samples. 
 * 
 * Internal computations are all done using BigDecimals.
 * @author Sean Byrnes
 */
public class AUnivariateDistribution {
	public static final BigDecimal TWO = new BigDecimal(2);
	public static final BigDecimal ZERO = new BigDecimal(0);
	
	Set<BigDecimal> samples;
	BigDecimal count;
	
	public AUnivariateDistribution(Set<BigDecimal> samples)
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
	 * Retrieve the mode of the distribution, defined as the value that appears
	 * most frequently.
	 * 
	 * @return BigDecimal Median
	 */
	public BigDecimal getMode()
	{
		Map<BigDecimal, Integer> valueCountMap = new HashMap<BigDecimal, Integer>();
		BigDecimal mode = null;
		int modeCount   = 0;
		
		for(BigDecimal value : samples)
		{
			if(valueCountMap.containsKey(value))  // have we seen this value before
			{
				int thisItemCount = valueCountMap.get(value);
				thisItemCount += 1;
				valueCountMap.put(value, thisItemCount);
				
				if(thisItemCount > modeCount)
				{
					// the frequency of this value is higher than the current mode
					mode = value;
					modeCount = thisItemCount;
				}
			} else {
				valueCountMap.put(value, 1); 	// initialize count to one
				if(mode == null)			 
				{
					// no mode defined yet so make this item the mode
					mode = value;
					modeCount = 1;
				}
			}
		}
		
		return mode;
	}
	
	/**
	 * Retrieve the median absolute deviation of the sample. This is a robust statistic of the variance. 
	 * 
	 * It is calculated as the median of the absolute values of the difference between values and the median.
	 * 
	 * @return BigDecimal Median absolute deviation
	 */
	public BigDecimal getMedianAbsoluteDeviation()
	{
		BigDecimal medianAbsoluteDeviation = null;
		Set<BigDecimal> absoluteResiduals = new HashSet<BigDecimal>();
		
		BigDecimal median = getMedian();
		
		for(BigDecimal value : samples)
		{
			absoluteResiduals.add(value.subtract(median).abs()); // the absolute residual from the median
		}
		
		// use our own utility to find the median of the set of residuals
		RandomDistribution residualDistribution = Statistics.buildRandomDistribution(absoluteResiduals);
		medianAbsoluteDeviation = residualDistribution.getMedian();
		
		return medianAbsoluteDeviation;
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
