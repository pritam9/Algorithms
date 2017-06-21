import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author Pritam
 *
 */
public class BellmanFordClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer adjacency_matrix[][];
		Integer final_distances[][] = null;
        int nVertices;
        int source = 0;
        FileWriter fileWriter;
        BufferedWriter bw = null;
        if(args.length>0)					// This checks if program received filePath on command line
		{
			try {
				File file=new File(args[0]);
				Scanner scanner= new Scanner(file);
				String line = scanner.nextLine();
				nVertices=line.split(" ").length;
				System.out.println("No of Vertices are "+nVertices);
				scanner.close();
				scanner=null;
				adjacency_matrix = new Integer[nVertices + 1][nVertices + 1];
				scanner=new Scanner(new File(args[0]));
				
				// output file for narrative algorithm 
				File outFile=new File("BellmanFord.txt");
				if(!outFile.exists())								//output file is generated at current folder with above name
				{
					try {
						outFile.createNewFile();
						System.out.println("File created successfully!!");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						System.out.println("Unable to create new file");
					}
				}
				try {
					fileWriter= new FileWriter(outFile.getAbsoluteFile());		//code is to write sorted numbers to file
					bw=new BufferedWriter(fileWriter);
				}catch (IOException e1) {
					// TODO Auto-generated catch block
					System.out.println("Unable to get File");
				}
				// call to adjacency matrix method
				adjacency_matrix=DijkstrasAlgoMatrix(scanner, nVertices, bw);
				source=Integer.parseInt(scanner.nextLine());
				System.out.println("Source is "+source);
				
				//DijkstraUsingPriorityQueue dijkstrasPriorityQueue = new DijkstraUsingPriorityQueue(nVertices);
	           // dijkstrasPriorityQueue.dijkstras_algorithm(adjacency_matrix, source,bw);
	 
	            System.out.println("The Shortest Path to all nodes are ");
	            //String distance="\n------------------------------------\nNow we have visited all nodes and we have shortest distances from source to other nodes.\nThe Shortest Path to all nodes are\n------------------------------------\n";
	            try {
					final_distances=new BellmanFordImpl().bellmanShortestPath(adjacency_matrix, source, bw);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            /*for (int i = 0; i <= final_distances.length - 1; i++)
	            {
	            	for(int j = 0; j <= final_distances[0].length - 1; j++)
	            	{
		                System.out.println(source + " to " + j + " is " + final_distances[i][j]);
		                distance=distance+source + " to " + j + " is " + final_distances[i][j]+"\n";
	            	}
	            }*/
	            /*File outFile=new File("DijkstraOutput.txt");//			// file name for sorted numbers
				if(!outFile.exists())								//output file is generated at current folder with above name
				{
					try {
						outFile.createNewFile();
						System.out.println("File created successfully!!");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						System.out.println("Unable to create new file");
					}
				}
				try {				
					FileWriter fileWriter= new FileWriter(outFile.getAbsoluteFile());		//code is to write sorted numbers to file
					BufferedWriter bw=new BufferedWriter(fileWriter);*/
	            bw.close();	
	            /*try {
						bw.write(distance);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						System.out.println("Failure to write data to File");
					}*/
				/*} catch (IOException e1) {
					// TODO Auto-generated catch block
					System.out.println("Unable to get File");
				}*/
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Unable to find or read file at - "+args[0]);
			}
			
			
		}
		else
		{
			System.out.println("Please provide input file path to program.");
		}
      
	}
	
	
	private static Integer[][] DijkstrasAlgoMatrix(Scanner scanner, int nVertices, BufferedWriter bw) {
		// TODO Auto-generated method stub
		Integer matrix[][]=new Integer[nVertices][nVertices];
		int i=0,j=0;
		String matrixS="Weight Matrix is as follows:\n";
		while (scanner.hasNextLine()&&i<nVertices)    
	    {
			
			String row=scanner.nextLine();
			matrixS+=row;
			System.out.println(row);
			//System.out.println("While"+i);
			for(String vertices:row.split(" "))
			{
				
				//System.out.println("--For"+i);
				int nodeValue=Integer.parseInt(vertices);
				matrix[i][j]=nodeValue;
				if (i == j)
                {
                    matrix[i][j] = 0;
                    
                    //continue;
                }
				if (matrix[i][j] == 0)
                {
                    matrix[i][j] =  null;
                    System.out.println("if 0 then - "+matrix[i][j]);
                }else
                	System.out.println("Non- zero is - "+matrix[i][j]);
				j++;
				
			}
			matrixS+="\n";
			i++;
			j=0;
	    }
		matrixS+="----------------------------------------\n";
		try {
			bw.write(matrixS);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return matrix;
	}

}
