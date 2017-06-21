/*
 *	Program to implement Quick sort and to write sorted  numbers to a file
 *	By Pritam Borate 
 *	Student Id- 800936897
 *	pborate@uncc.edu
 *	Date- 16 Feb 2916
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.SortedMap;


public class QSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String inputNumbers=null,sortedNumbers="Quick_Sorted_Nembers are as follows: \n";
		ArrayList<Integer> inputArray = new ArrayList<Integer>();    // Data structure used to store numbers for sorting
		
		if(args.length>0)					// This checks if program received filePath on command line
		{
			try {
				FileReader fileReader=new FileReader(args[0]);
				BufferedReader bufferedReader=new BufferedReader(fileReader);
				inputNumbers=bufferedReader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Unable to find or read file at - "+args[0]);
			}
			for(String numbersToSort:inputNumbers.split(";"))
			{
				try
				{
					inputArray.add(Integer.parseInt(numbersToSort));
				}
				catch(NumberFormatException e)				//If number is text file is having spaces or characters, those wont be considered for sorting
				{
					System.out.println(numbersToSort+" is a non Numeric number to sort. Not considered for sorting. Enter numbers without any spaces.");
				}
			}
			
			
			long timeTaken=System.currentTimeMillis();
			quick_sort(inputArray,0,inputArray.size()-1);		//Function call to quick sort.
			timeTaken=System.currentTimeMillis()-timeTaken;		// Gives runtime for quick sort	
			
			for(int j=0;j<inputArray.size();j++)
			{
				sortedNumbers=sortedNumbers+(Integer)inputArray.get(j)+";";
			}
			
			
			File file=new File("answer.txt");//			// file name for sorted numbers
			if(!file.exists())								//output file is generated at current folder with above name
			{
				try {
					file.createNewFile();
					System.out.println("File created successfully!!");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					System.out.println("Unable to create new file");
				}
			}
			try {				
				FileWriter fileWriter= new FileWriter(file.getAbsoluteFile());		//code is to write sorted numbers to file
				BufferedWriter bw=new BufferedWriter(fileWriter);
				try {
					bw.write(sortedNumbers);
					bw.write("\nPerformance Analysis:\n");
					bw.write("Size \t Time Taken in miliseconds\n");
					bw.write(inputArray.size()+"\t"+timeTaken);
					bw.close();
					System.out.println("Output written to File");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					System.out.println("Failure to write data to File");
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println("Unable to get File");
			}
			
		}
		else
		{
			System.out.println("Please provide input file path to program.");
		}
	}

	
	//This function is to implement quick sort algorithm
	private static void quick_sort(ArrayList<Integer> inputArray, int i, int j) {
		if(i<j)
		{
			int q=partition(inputArray,i,j);
			quick_sort(inputArray,i,q-1);
			quick_sort(inputArray, q+1, j);
		}
		
	}
	/*
	 * This function creates partition
	 * numbers on left of wall are less than pivot
	 * numbers on right of wall are greater than pivot value
	 * */
	private static int partition(ArrayList<Integer> inputArray, int i, int j) {
		//variable initialization
		int pivot=inputArray.get(j);
		int wall=i-1;
		int temp=0;
		for(int k=i; k<j;k++)
		{
			if(inputArray.get(k)<=pivot)
			{
				wall++;
				temp=inputArray.get(wall);
				inputArray.set(wall, inputArray.get(k));
				inputArray.set(k,temp);
			}
		}
		inputArray.set(j,inputArray.get(wall+1));		
		inputArray.set(wall+1, pivot);
		return wall+1;
	}

}
