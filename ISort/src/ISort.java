/*
 *	Program to implement insertion sort and to write it to a file
 *	By Pritam Borate 
 *	Student Id- 800936897
 *	pborate@uncc.edu
 *	Date- 19 Jan 2916
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


public class ISort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String inputNumbers=null,sortedNumbers=null;
		int arraySize=0;								//This variable holds the value of array size
		
		if(args.length>0)					// This checks if program received filePath on command line
		{
			try {
				FileReader fileReader=new FileReader(args[0]);
				BufferedReader bufferedReader=new BufferedReader(fileReader);
				inputNumbers=bufferedReader.readLine();
				arraySize=inputNumbers.split(";").length;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Unable to find or read file at - "+args[0]);
			}
			long timetaken=System.currentTimeMillis();
			sortedNumbers=insertionsort(inputNumbers);
			timetaken=System.currentTimeMillis()-timetaken;//Function call to insertion sort.
			System.out.println("Sorted Numbers are -"+sortedNumbers);
			System.out.println("Time Taken is -" + timetaken);
			
			
			File file=new File("sorted.txt");//			// file name for sorted numbers
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
					bw.close();
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

	/*
	 * This function implements insertion sort
	 * It returns sorted numbers in the form of String, all numbers are separated by ';'
	 */
	private static String insertionsort(String inputNumbers) {
		// TODO Auto-generated method stub
		String sorterdNumbers="";					// String is to store sorted numbers separated with ';'
		ArrayList<Integer> sortedArray = new ArrayList<Integer>();    // Data structure used to store sorted numbers
		int numberCount=0;							// variable to hold size of sorted numbers in arrayList
		int temp=0;									// variable to store number to be inserted in arrayList
		for(String numbersToSort:inputNumbers.split(";"))
		{
			numberCount=sortedArray.size();
			
			try{
				temp=Integer.parseInt(numbersToSort);	//converts objects to integer, required for comparison
				if(numberCount>0)						 // checks if sortedArrayList has numbers
				{
					int j=numberCount-1;				// j is a counter which starts from arrayList size-1
					while(j>=0 && (Integer)(sortedArray.get(j))>temp)   //while new number is smaller than the number in sorted arrayList, counter decreases 
					{
						j--;
					}
					sortedArray.add(j+1,temp);          // inserts new value in sorted arrayList at j+1
					
				}
				else
				{
					sortedArray.add(temp);				// First number is added directly to the arrayList	
				}
				
			}
			catch(NumberFormatException e)				//If number is text file is having spaces or characters, those wont be considered for sorting
			{
				System.out.println(numbersToSort+" is a non Numeric number to sort. Not considered for sorting. Enter numbers without any spaces.");
			}
			
		}
	
		
		//This loop  is to create a string of sorted numbers separated by ';'
		for(int j=0;j<sortedArray.size();j++)
		{
			sorterdNumbers=sorterdNumbers+(Integer)sortedArray.get(j)+";";
		}
		
		return sorterdNumbers;
	}

}
