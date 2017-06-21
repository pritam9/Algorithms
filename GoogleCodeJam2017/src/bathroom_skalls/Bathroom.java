package bathroom_skalls;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bathroom {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file=new File("stallSmallOutput.txt");//			// file name for sorted numbers
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
			FileReader fileReader=new FileReader("/Users/Pritam/Documents/input1.txt");
			//FileReader fileReader=new FileReader("/Users/Pritam/Downloads/C-small-1-attempt2.in.txt");
			BufferedReader bufferedReader=new BufferedReader(fileReader);
			int inputCount=Integer.parseInt(bufferedReader.readLine());
			//arraySize=inputNumbers.split(";").length;
			for(int caseNumber=1;caseNumber<=inputCount;caseNumber++)
			{
				
				String[] inputs=bufferedReader.readLine().split(" ");
				System.out.println(caseNumber+ " : Pancakes are : "+inputs[0]+ " and People are - "+inputs[1]);

				getMinMax(bw,caseNumber,Long.parseLong(inputs[0]),Long.parseLong(inputs[1]));


			}
			bufferedReader.close();
			fileReader.close();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to find or read file at - ");
		}



	}

	private static void getMinMax(BufferedWriter bw, int caseNumber, long stalls, long people) {
		List<Long> myList = new ArrayList<Long>();
		myList.add(stalls);
		long max=0,min=0;
		if((people)*2>(stalls+3)){
			max=0;
			min=0;
		}else{
			while(people>0){
				Collections.sort(myList, Collections.reverseOrder());
				System.out.println(myList);
			if(myList.get(0)%2==0){
				long temp = myList.get(0);
				max=temp/2;
				min=temp/2-1;
				myList.add(temp/2);
				myList.add(temp/2-1);
				myList.remove(0);
			}else{
				long temp = myList.get(0);
				max=temp/2;
				min=temp/2;
				myList.add(temp/2);
				myList.add(temp/2);
				myList.remove(0);
			}
			people--;
			}
		}


		String outputLine="Case #"+caseNumber+": "+max+" "+min+"\n";
		try {
			bw.write(outputLine);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(max+" - "+min);
		
	}
}
