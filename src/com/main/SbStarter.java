package com.main;


public class SbStarter {
	
	
	public static void main(String[] args) throws Exception 
	{
		if(args.length == 0)
		{
			throw new Exception("NO ARGUMENTS PASSED. APP FAILED.");
		}

		assert Double.valueOf(args[2]) > 0.0: "NOT VALID";

		SbGaussianManipulation gaussian = new SbGaussianManipulation(args[0], args[1], args[2]);
		gaussian.generateGaussianNumbers();
		
		gaussian.saveDataToFile();
		
		for (Double dbl : gaussian.getGeneratedGaussians() ) {
			System.out.println(dbl);
		}
		System.out.println("-------------------------");
		gaussian.readDataAndSaveToTextFile();
		
	}
}
