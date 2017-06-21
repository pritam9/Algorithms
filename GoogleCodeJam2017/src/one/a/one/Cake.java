package one.a.one;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import javax.sql.rowset.RowSetWarning;

public class Cake {

	public static void main(String[] args) {
		File file=new File("pancakeLargeOutput.txt");//			// file name for sorted numbers
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
			//FileReader fileReader=new FileReader("/Users/Pritam/Downloads/A-large.in.txt");
			BufferedReader bufferedReader=new BufferedReader(fileReader);
			int inputCount=Integer.parseInt(bufferedReader.readLine());
			//arraySize=inputNumbers.split(";").length;
			for(int caseNumber=1;caseNumber<=inputCount;caseNumber++)
			{
				String outputLine="";
				String[] rc=bufferedReader.readLine().split(" ");
				int row = Integer.parseInt(rc[0]);
				int col = Integer.parseInt(rc[1]);
				char[][] cake = new char[row][col];
				int[][] grid = new int[row][col];
				int child = 1;
				for(int i=0; i<row;i++){
					String rowGrid = bufferedReader.readLine();
					String rowGridChar = rowGrid.replaceAll("?", "");
					if(rowGridChar.length()==0){
						
					}else if(rowGrid.length()==rowGridChar.length()){
						
					}
					else if(rowGrid.length()>rowGridChar.length()){
						
					}
					
					
					cake[i]=rowGrid.toCharArray();
					for(int j =0; j<col;j++){
						if(cake[i][j]=='?')
							grid[i][j]=-1;
						else
						{
							grid[i][j]=child;
							child++;
						}
					}
				}
				System.out.println("Pancakes are : "+cake[0][1]);
				System.out.println(('c'=='C'));
				char prev='a';
				for(int i=0;i<row;i++){
					for(int j=0;j<col;j++){
						if(grid[i][j]==1){
							prev=cake[i][j];
						}
						if(prev=='a'){
							
						}
					}
				}

				//flipCount(bw,caseNumber,nextPanCakes[0],Integer.parseInt(nextPanCakes[1]));


			}
			bufferedReader.close();
			fileReader.close();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to find or read file at - ");
		}
	}

}
