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


public class ISortNarration {

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
			BufferedWriter bw=null;
			File file=new File("ISortNarration.txt");//			// file name for sorted numbers
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
				 bw=new BufferedWriter(fileWriter);
			//long timetaken=System.currentTimeMillis();
			sortedNumbers=insertionsort(inputNumbers,bw);
			//timetaken=System.currentTimeMillis()-timetaken;//Function call to insertion sort.
			System.out.println("Sorted Numbers are -"+sortedNumbers);
			//System.out.println("Time Taken is -" + timetaken);
			
			
			
					//bw.write(sortedNumbers);
					bw.close();
				
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
	private static String insertionsort(String inputNumbers, BufferedWriter bw) {
		// TODO Auto-generated method stub
		String sorterdNumbers="";					// String is to store sorted numbers separated with ';'
		ArrayList<Integer> sortedArray = new ArrayList<Integer>();    // Data structure used to store sorted numbers
		int numberCount=0;							// variable to hold size of sorted numbers in arrayList
		int temp=0;	
		for(String numbersToSort:inputNumbers.split(";"))
		{
			temp=Integer.parseInt(numbersToSort);
			sortedArray.add(temp);
		}
		printArrayList(sortedArray,bw,"\nInput Array is: \n");
		//String str="\nGiven Array is "+inputNumbers.replace(';', '|');
		// variable to store number to be inserted in arrayList
		for(int i=1;i<sortedArray.size();i++)
		{
			temp=sortedArray.get(i);
			String str="\n**************************************\nNow we will compare number at index "+i+" with the numbers on its left, till we get a number less than current number.\n";
					int j=i-1;				// j is a counter which starts from arrayList size-1
					while(j>=0 && (Integer)(sortedArray.get(j))>temp)   //while new number is smaller than the number in sorted arrayList, counter decreases 
					{
						str+=sortedArray.get(j)+" is greater than "+temp+"\nWe will continue to check on its left side\n";
						j--;
					}
					str+="Found Location for "+temp+" to insert in an array; that is index "+(j+1)+"\n";
					sortedArray.remove(i);
					sortedArray.add(j+1,temp);          // inserts new value in sorted arrayList at j+1
					str+="After insertion of "+temp+" at location "+(j+1)+"; updated array is:\n";
					printArrayList(sortedArray, bw, str);
		}
		printArrayList(sortedArray, bw, "\n---------------------------------------------\nFinally Sorted array is al follows:\n");
	
		
		//This loop  is to create a string of sorted numbers separated by ';'
		for(int j=0;j<sortedArray.size();j++)
		{
			sorterdNumbers=sorterdNumbers+(Integer)sortedArray.get(j)+";";
		}
		
		return sorterdNumbers;
	}

	private static void printArrayList(ArrayList<Integer> sortedArray, BufferedWriter bw, String string) {
		// TODO Auto-generated method stub
		int temp=(sortedArray.size())*8;
		String line="\t";
		for(int i=0;i<temp;i++)
		  line+="-";
		string+=line+"\n\t|";
		for(int i=0;i<sortedArray.size();i++)
			string+=sortedArray.get(i)+"\t|";
		
		string+="\n"+line+"\n";
		try {
			bw.write(string);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
