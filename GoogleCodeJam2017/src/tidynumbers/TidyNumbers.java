package tidynumbers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class TidyNumbers {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file=new File("tidyLargeOutput.txt");//			// file name for sorted numbers
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
			//FileReader fileReader=new FileReader("/Users/Pritam/Documents/input1.txt");
			FileReader fileReader=new FileReader("/Users/Pritam/Downloads/B-large.in.txt");
			BufferedReader bufferedReader=new BufferedReader(fileReader);
			int inputCount=Integer.parseInt(bufferedReader.readLine());
			//arraySize=inputNumbers.split(";").length;
			for(int caseNumber=1;caseNumber<=inputCount;caseNumber++)
			{
				
				String tidy=bufferedReader.readLine();
				System.out.println("Pancakes are : "+tidy);


				getTidyNumber(bw,caseNumber,tidy);


			}
			bufferedReader.close();
			fileReader.close();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to find or read file at - ");
		}



	}

	private static void getTidyNumber(BufferedWriter bw, int caseNumber, String tidy) {
		int[] tidyArray = new int[tidy.length()];
		boolean isTidy = false;
		int prev=0;
		
		tidyArray[prev]=Integer.parseInt(tidy.charAt(prev)+"");
		for(int i = 1; i<tidy.length(); i++){
			tidyArray[i]=Integer.parseInt(tidy.charAt(i)+"");
			if(tidyArray[prev]>tidyArray[i]){
				isTidy=true;
				break;
			}
			prev++;
		}
		for(int i = prev+1;i<tidy.length();i++){
			tidyArray[i]=9;
		}
		if(isTidy){
		tidyArray[prev]--;
		for(int i=prev; i>0;i--){
			if(tidyArray[i]<tidyArray[i-1]){
				tidyArray[i]=9;
				tidyArray[i-1]--;
			}
		}
		}
		String str="";
		for(int i=0;i<tidyArray.length;i++){
			str+=tidyArray[i];
		}
		
		String outputLine="Case #"+caseNumber+": "+Long.parseLong(str)+"\n";
		try {
			bw.write(outputLine);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Long.parseLong(str));
	}
	
}
