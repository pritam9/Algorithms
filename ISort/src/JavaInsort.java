/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Nitiranjan
 */
// student id: 800937633

import java.io.*;
import java.util.*;

public class JavaInsort {
    public static void writeFile(ArrayList<Float> data, String filePath)//this method takes the data and stores in the given file location,which is passed as an argument
	{
		int i;
		try
		{
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filePath)));
			for( i=0;i<data.size()-1;i++){
	bw.write(data.get(i) +";");
	
			}
			bw.write(data.get(i).toString());
                        bw.close();
		}
		catch(Exception e)
		{
			System.out.println("Error is"+e.getMessage());
			e.printStackTrace();
		}
	}
	public static ArrayList<Float> readFile(String filePath)//this method reads the data from the file stores in an array and returns that array
	{
		ArrayList<Float> items=null;
		BufferedReader br=null;
		
	String currentLine;
		String delim=";";
		try 
		{
			items=new ArrayList<>();
			br=new BufferedReader(new FileReader(new File(filePath)));
			while((currentLine=br.readLine())!=null){
				
				StringTokenizer st=new StringTokenizer(currentLine, delim);
				while(st.hasMoreTokens()){
                                    items.add(Float.parseFloat(st.nextToken()));
					//System.out.println("");
				}
				
			}
			System.out.println("file read");
						
			
		} 
		catch (Exception e)
		{
			System.out.println("error in readfrom file method"+e.getMessage());
			e.printStackTrace();
		}
		finally{
			try{
			br.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		return items;


	}

	public static ArrayList<Float> insertionSort(ArrayList<Float> a) { //it takes the array ,sorts the digits in ascending order and return an array

		for (int j = 1; j < a.size(); j++)
		{
			float key = a.get(j);
			int i = j-1;
			while ( (i > -1) && (a.get(i) > key ) )
			{
				a.set(i+1,a.get(i));
				i--;
			}
			a.set(i+1, key);
		}
		return a;
	}


	public static void main(String args[]){
	 String fileLocation=null;
		if(0<args.length){
			fileLocation=args[0];
		}
		
		ArrayList<Float> inputFileData= readFile(fileLocation);
	    //System.out.println("data in the file");
	    ArrayList<Float> sortedData= insertionSort(inputFileData);
           // System.out.print(sortedData);
            writeFile(sortedData, "output.txt");
		
	}
}

