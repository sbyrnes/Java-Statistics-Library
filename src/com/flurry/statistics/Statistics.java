package com.flurry.statistics;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedList;

import com.flurry.statistics.distributions.NormalDistribution;
import com.flurry.statistics.distributions.RandomDistribution;



/**
 * Static class providing standard tools for comparing and analyzing distributions.
 * @author Sean Byrnes
 */
public class Statistics {
	public static RandomDistribution buildRandomDistribution(Collection<? extends Number> sample) throws Exception
	{
		return new RandomDistribution(buildBigDecimalSet(sample));
	}
	
	public static NormalDistribution buildNormalDistribution(Collection<? extends Number> sample) throws Exception
	{
		return new NormalDistribution(buildBigDecimalSet(sample));
	}
	
	private static Collection<BigDecimal> buildBigDecimalSet(Collection<? extends Number> sample) throws Exception
	{
		Collection<BigDecimal> result = new LinkedList<BigDecimal>();
		for(Number value : sample)
		{
			result.add(new BigDecimal(value.doubleValue()));
		}
		return result;
	}
}
