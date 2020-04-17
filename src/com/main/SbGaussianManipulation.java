package com.main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class SbGaussianManipulation {

	private double average, standardDeviation; 
	private List<String> gaussianList= new ArrayList<>();
	private List<Double> generatedGaussians = new ArrayList<>();
	private Random r = new Random();
	private int numberOfGenerations;
	
	public SbGaussianManipulation(String nrOfGenerations, String avg, String stDev)
	{
		avg = avg.replace(",", ".");
		stDev = stDev.replace(",", ".");
		
		average = Double.valueOf(avg);
		standardDeviation = Double.valueOf(stDev);
		numberOfGenerations = Integer.valueOf(nrOfGenerations);
		
//		assert standardDeviation > 0: "NOT VALID";

	}
	
	public void generateGaussianNumbers()
	{
		for(int i = 0; i < numberOfGenerations; i++)
		{
			double lVal = r.nextGaussian() * average + standardDeviation;
//			String lStr = String.valueOf(lVal);
						
//			gaussianList.add(lStr);
			
			generatedGaussians.add(lVal);
		}
	}
	
	public List<Double> getGeneratedGaussians() {
		return generatedGaussians;
	}

	public List<String> getGaussians()
	{
		return gaussianList;
	}
	
	public void saveDataToFile()
	{
		FileOutputStream file = null;
		try {
			file = new FileOutputStream("outputFile.bin");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        DataOutputStream data = new DataOutputStream(file);
        
        for (double dbl: generatedGaussians)
        {
        	try {
				data.writeDouble(dbl);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
	public void readDataAndSaveToTextFile() throws IOException, ParseException
	{
		FileOutputStream fileout = null;
		try {
			fileout = new FileOutputStream("changedDouble.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        DataOutputStream dataout = new DataOutputStream(fileout);
		
		FileInputStream file = null;
		try {
			file = new FileInputStream("outputFile.bin");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        DataInputStream data = new DataInputStream(file);
	
    	List<Double> lList = new ArrayList<>();
    	
//    	NumberFormat nf_in = NumberFormat.getNumberInstance(Locale.getDefault());

    	DecimalFormat df = new DecimalFormat("#.00000");
        DecimalFormatSymbols sym = DecimalFormatSymbols.getInstance();
        sym.setDecimalSeparator(',');
        df.setDecimalFormatSymbols(sym);
        
        while(data.available() > 0)
        {
        	lList.add(data.readDouble());
        }
        
        for(Double ldbl: lList)
        {
        	
        	df.format(ldbl);
        	
//        	String lStr = nf_in.parse(String.valueOf(ldbl)).toString();
//        	Double lDbl = null;
//        	try {
//				lDbl = nf_in.parse(lStr).doubleValue();
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//        	System.out.println(lStr);
        	dataout.writeBytes(df.format(ldbl));
        	String newLine = System.getProperty("line.separator");        	 
        	dataout.writeBytes(newLine);
        }
        
	}
}
