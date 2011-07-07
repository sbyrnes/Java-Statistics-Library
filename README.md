Java Statistics Library (WORK IN PROGRESS)
=================
A general Java library for common statistical functions. All operations are floating-point 
safe utilizing the BigDecimal framework. 

IMPORTANT:
Note the project is currently a work in progress. Most if all functions are not complete.

Install
-------
	
	ant build.xml

Then include the generated javaStatistics.jar in your classpath. 

Example
-------

	// retrieve the standard deviation for a normal distribution
	NormalDistribution myDistribution = new NormalDistribution(mySet);
	BigDecimal stdDev = myDistribution.getStandardDeviation(); 

FAQ:
-------
Does the library provide a complete set of statistical functions?

No, at least not yet. For now it will include the functions I find more interesting to work on
or more useful. If you want to help contribute more please do.

I don't use BigDecimal in my program, can I still use the library?

You really should be, especially if you deal with currency. If you don't then there are constructors
available to create a Distribution from an element set. However, the result you get will be a BigDecimal
so you have a change to practice using BigDecimal. 

Why is this not already part of Java? 

Who knows. I have needed at least one of these functions in every project I've ever done.

COPYRIGHT
---------
Copyright (c) 2011 Sean Byrnes <sean@flurry.com@gmail.com>
Released under the MIT license