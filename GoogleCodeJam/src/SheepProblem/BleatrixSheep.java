/**
 * 
 */
package SheepProblem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pritam
 *
 */
public class BleatrixSheep {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int bleatrix=100000000;
		int numberCount=0;
		File file=new File("glOutput.txt");//			// file name for sorted numbers
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
			FileReader fileReader=new FileReader("/Users/Pritam/Downloads/A-Large.in.txt");
			BufferedReader bufferedReader=new BufferedReader(fileReader);
			numberCount=Integer.parseInt(bufferedReader.readLine());
			//arraySize=inputNumbers.split(";").length;
			for(int caseNumber=1;caseNumber<=numberCount;caseNumber++)
			{
				String outputLine="";
				int nextInt=Integer.parseInt(bufferedReader.readLine());
				System.out.println("Number is : "+nextInt);
				if(nextInt==0)
					outputLine=outputLine+"Case #"+caseNumber+": INSOMNIA\n";//System.out.println("Case #"+caseNumber+" INSOMNIA");
				else
				{
					int N=nextInt;
					//List<Integer> digits=new ArrayList();
					int[] digits={0,1,2,3,4,5,6,7,8,9};
					int[] visitedDigits={10,10,10,10,10,10,10,10,10,10};
					
					boolean isLastDigit=false;
					int singleDigit=0;
					int temp;
					int count=1;
					//
					while(!compareDigitsVisited(digits,visitedDigits))
					{
						temp=N;
						//System.out.println("Temp is :" + temp);
						while(!isLastDigit)
						{
							singleDigit=temp%10;
							visitedDigits[singleDigit]=singleDigit;
							if(temp<10)
							{
								visitedDigits[temp]=temp;
								isLastDigit=true;
							}
							else
								temp=temp/10;
						}
						isLastDigit=false;
						if(compareDigitsVisited(digits,visitedDigits))
						{
							System.out.println("N is :"+N+"\t count is :"+count);
							break;
						}
						count++;
						N=count*nextInt;
						//System.out.println("N is :"+N+"\t count is :"+count+"\t Array is :"+visitedDigits[0]+" "+visitedDigits[1]+" "+visitedDigits[2]+" "+visitedDigits[3]+" "+visitedDigits[4]+" "+visitedDigits[5]+" "+visitedDigits[6]+" "+visitedDigits[7]+" "+visitedDigits[8]+" "+visitedDigits[9]);
					}
					//System.out.println("Last Digit is : "+N);
					//System.out.println("Case #"+caseNumber+" "+N);
					outputLine=outputLine+"Case #"+caseNumber+": "+N+"\n";
				}
				bw.write(outputLine);
				
			}
			bufferedReader.close();
			fileReader.close();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to find or read file at - "+args[0]);
		}
		
	}

	private static boolean compareDigitsVisited(int[] digits, int[] visitedDigits) {
		// TODO Auto-generated method stub
		//boolean areAllVisited=false;
		for(int i=0;i<10;i++)
		{
			//System.out.println(i+" Index "+digits[i]+" Value "+visitedDigits[i]);
			if(digits[i]==visitedDigits[i])
			{
				//System.out.println(digits[i]+" Matched "+visitedDigits[i]);
			}
			else
			{
				//System.out.println("------MisMatch-------");
				return false;
			}
		}
		return true;
	}

}
