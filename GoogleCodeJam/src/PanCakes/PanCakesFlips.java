/**
 * 
 */
package PanCakes;

import java.awt.BufferCapabilities.FlipContents;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Pritam
 *
 */
public class PanCakesFlips {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file=new File("pancakeSmallOutput.txt");//			// file name for sorted numbers
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
			FileReader fileReader=new FileReader("/Users/Pritam/Downloads/B-large.in.txt");
			BufferedReader bufferedReader=new BufferedReader(fileReader);
			int inputCount=Integer.parseInt(bufferedReader.readLine());
			//arraySize=inputNumbers.split(";").length;
			for(int caseNumber=1;caseNumber<=inputCount;caseNumber++)
			{
				String outputLine="";
				String nextPanCakes=bufferedReader.readLine();
				System.out.println("Pancakes are : "+nextPanCakes);
				int flip=flipCount(nextPanCakes);
				outputLine=outputLine+"Case #"+caseNumber+": "+flip+"\n";
				System.out.println("Flips Required are: "+flip);
				bw.write(outputLine);
				
			}
			bufferedReader.close();
			fileReader.close();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to find or read file at - ");
		}
		
		

	}
	private static int flipCount(String pancakes) {
		// TODO Auto-generated method stub
		char[] charArray=pancakes.toCharArray();
		int flip=0;
		char isHappyFace=charArray[0];
		for(int i=0;i<charArray.length;i++)
		{
			if(!(charArray[i]==isHappyFace))
			{
				isHappyFace=charArray[i];
				flip++;
				//System.out.println("isHappyPlace is :"+isHappyFace);
			}
			
		}
		if(!(isHappyFace=='+'))
		{
			flip++;
		}
		return flip;
	}

}
