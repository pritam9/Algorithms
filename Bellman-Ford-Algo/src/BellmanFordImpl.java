import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
 
public class BellmanFordImpl {
 
  public Integer[][] bellmanShortestPath(Integer[][] weight,
          int source, BufferedWriter bw) throws Exception
  {
 
    //auxiliary constants
    final int SIZE = weight.length;
    final int EVE = -1;//to indicate no predecessor
    final int INFINITY = Integer.MAX_VALUE;
 
    //declare and initialize pred to EVE and minDist to INFINITY
    Integer[] pred = new Integer[SIZE];
    Integer[] minDist = new Integer[SIZE];
    Arrays.fill(pred, EVE);
    Arrays.fill(minDist, INFINITY);
    //printArrays(pred,minDist,bw,"\nInitial Distance and predecor arrays are as follows:\n");
    //set minDist[source] = 0 because source is 0 distance from itself.
    minDist[source] = 0;
    printArrays(pred,minDist,bw,"\nInitial Distance and predecor arrays are as follows:\n");
    printLine(bw,"\n*************************************\nNow Start with relaxing edges:");
    //relax the edge set V-1 times to find all shortest paths
    for (int i = 1; i < minDist.length ; i++) {
    	printLine(bw,"\n*************************************\nFor iteration number "+i+"\n*************************************");
      for (int v = 0; v < SIZE; v++) {
        for (int x : adjacency(weight, v, bw)) {
          if (minDist[x] > minDist[v] + weight[v][x]) {
            int temp=minDist[x];
        	  
        	  minDist[x] = minDist[v] + weight[v][x];
            pred[x] = v;
            String prevValue="";
            if(temp==Integer.MAX_VALUE)
            	prevValue="INFINITY";
            else
            	prevValue=""+temp;
            printLine(bw,"\n----------------------------------\nExisting Distance from "+v+" to "+x+" was "+prevValue+" But now we have found shorter distance than that. New Distance is "+minDist[x]+" and predessor for this vertex is "+pred[x]);
          }
        }
        printLine(bw, "\n----------------------------------\nNow Realxing edges from vertex "+v);
        printArrays(pred,minDist,bw,"\nAfter relaxing edges from vertex "+(i-1)+", Distance and predecor arrays are as follows:\n");
      }
      
    }
    printLine(bw, "\nNow we are checking for cycles if there are any in given graph. Here we are checking if there are any negative cycles present.\n");
    //detect cycles if any, looking for negative cycle
    for (int v = 0; v < SIZE; v++) {
      for (int x : adjacency(weight, v, bw)) {
        if (minDist[x] > minDist[v] + weight[v][x]) {
        		
        		printLine(bw, "\nCycle found at vertex - "+x);
        		throw new Exception("Negative cycle found");
        
        	}
      }
    }
    
    printLine(bw,"\n*************************************\nFinally,\n");
    printArrays(pred,minDist,bw,"\nWe have visited all vertices and looped through |V|-1 times.\nOur Final array Distance and previous node arrays are as follows:\n");
 
    Integer[][] result = {pred, minDist};
    return result;
  }
  private void printLine(BufferedWriter bw, String string) {
	// TODO Auto-generated method stub
	  
	  try {
			bw.write(string);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
}
/**************************************************************
   * @param pred
   * @param minDist
   * @param bw
   */
  private void printArrays(Integer[] pred, Integer[] minDist, BufferedWriter bw, String message) {
	// TODO Auto-generated method stub
	int temp=(pred.length+1)*8;
	String line="\t";
	for(int i=0;i<temp;i++)
	  line+="-";
	message+=line+"\n\t|Node	|";
	for(int i=0;i<pred.length;i++)
		message+=i+"\t|";
	message+="\n"+line+"\n\t|Dist\t|";
	for(int i=0;i<minDist.length;i++)
		{
			if(minDist[i]==Integer.MAX_VALUE)
				message+="INF\t|";
			else	
				message+=minDist[i]+"\t|"; 
		}
	message+="\n"+line+"\n\t|Prev\t|";  
	for(int i=0;i<pred.length;i++)
		message+=pred[i]+"\t|";
	message+="\n"+line+"\n";
	try {
		bw.write(message);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

/******************************************************************
   *  finding neighbours of current Node.
 * @param bw 
   *****************************************************************/
  private List<Integer> adjacency(Integer[][] G, int v, BufferedWriter bw) {
    List<Integer> result = new ArrayList<Integer>();
    for (int x = 1; x < G.length; x++) {
      if (G[v][x] != null) {
        result.add(x);
      }
    }
    if(v!=0)
    {
	    String str="\n--------------------------------\nAdjacent nodes to "+v+" are :";
	    for(int i=0;i<result.size();i++)
	    {
	    	str+=result.get(i)+", ";
	    }
	    str+="\n";
	   /* try {
			bw.write(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    }
    return result;
  }
}
