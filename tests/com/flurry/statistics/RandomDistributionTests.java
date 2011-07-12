package com.flurry.statistics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.flurry.statistics.Statistics;

public class RandomDistributionTests {
	private static final List<Integer> SINGLE_VALUE = Arrays.asList(1);
	private static final List<Integer> ORDINAL_SEQUENCE = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
	private static final List<Integer> FIBONACCI_SERIES = Arrays.asList(0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55);
	
	@Test
	public void testGetMedian() throws Exception {
		// median of set 1 is the element
		assertEquals(new BigDecimal(1), 
					 Statistics.buildRandomDistribution(SINGLE_VALUE).getMedian());
		// median of even number of elements is the mean of the middle entries
		assertEquals(new BigDecimal(5.5), 
					 Statistics.buildRandomDistribution(ORDINAL_SEQUENCE).getMedian());
		// median of set of odd elements is the middle element
		assertEquals(new BigDecimal(5), 
				     Statistics.buildRandomDistribution(FIBONACCI_SERIES).getMedian());
	}

	@Test
	public void testGetMean() throws Exception {
		// mean of set 1 is the element
		assertEquals(new BigDecimal(1), 
					 Statistics.buildRandomDistribution(SINGLE_VALUE).getMean());
		// mean of sequential numbers is the same as the median
		assertEquals(new BigDecimal(5.5), 
					 Statistics.buildRandomDistribution(ORDINAL_SEQUENCE).getMean());
		// calculated mean
		assertEquals(new BigDecimal(13), 
				     Statistics.buildRandomDistribution(FIBONACCI_SERIES).getMean());
	}

	@Test
	public void testGetMode() throws Exception {
		// mode of set 1 is the element
		assertEquals(new BigDecimal(1), 
					 Statistics.buildRandomDistribution(SINGLE_VALUE).getMode());
		// mode of sequential numbers is the first element
		assertEquals(new BigDecimal(1), 
					 Statistics.buildRandomDistribution(ORDINAL_SEQUENCE).getMode());
		// mode of fibonacci sequence is always 1
		assertEquals(new BigDecimal(1), 
				     Statistics.buildRandomDistribution(FIBONACCI_SERIES).getMode());
	}

	@Test
	public void testGetMedianAbsoluteDeviation() throws Exception {
		fail("Not yet implemented");
	}
}
