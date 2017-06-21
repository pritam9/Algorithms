import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class InClassOne {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length>0)					// This checks if program received filePath on command line
		{
			try {
				FileReader fileReader=new FileReader("topmovies.csv");
				BufferedReader bufferedReader=new BufferedReader(fileReader);
				ArrayList<Movie> movies=parseMovie(bufferedReader);
				/*for(int i=0;i<movies.size();i++)
					System.out.println(movies.get(i));*/
				Comparator<Movie> cmp = new Comparator<Movie>() {
			        public int compare(Movie m1, Movie m2) {
			            return ((Double)m2.getTotal()).compareTo((Double)m1.getTotal());
			        }
			    };
			    System.out.println("-------Movies Sorted By total($) in descending order-------");
			    movies.sort(cmp);
			    //movies.t
			    for(int i=0;i<movies.size();i++)
					System.out.println(movies.get(i));
				
			    
			    //comparator for String name sorting
			    
			    Comparator<Movie> cmpName = new Comparator<Movie>() {
			        public int compare(Movie m1, Movie m2) {
			            return m1.getName().compareTo(m2.getName());
			        }
			    };
			    movies.sort(cmpName);
			    System.out.println("-------");
			   /* for(int i=0;i<movies.size();i++)
					System.out.println(movies.get(i));*/
			    HashMap<String,HashSet<String>> hmap= new HashMap<String,HashSet<String>>();
			    String key;
			    for(int i=0;i<movies.size();i++)
			    {
			    	key=movies.get(i).getName().substring(0, 1);
			    	if(hmap.containsKey(key))
			    	{
			    		HashSet<String> nameSet=hmap.get(key);
			    		nameSet.add(movies.get(i).getName());
			    		hmap.put(key, nameSet);
			    	}
			    	else
			    	{
			    		HashSet<String> nameSet=new HashSet<String>();
			    		nameSet.add(movies.get(i).getName());
			    		hmap.put(key,nameSet);
			    	}
			    }
			    System.out.println("\n-------Movies Grouped By First Letter-------");
			    //System.out.println(hmap);
			    
			    
			    Iterator entries = hmap.entrySet().iterator();
			    while (entries.hasNext()) {
			    	//System.out.println("Yeda!!!!");
			        HashMap.Entry entry = (HashMap.Entry) entries.next();
			        String key1 = (String)entry.getKey();
			        HashSet value = (HashSet)entry.getValue();
			        System.out.println( key1 + ": " + value);
			    }
			    
			   /* for (HashMap.Entry<String,HashSet<String>> entry : hmap.entrySet()) {
			        String entry.getKey();
			        Object value = entry.getValue();
			        // ...
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

	private static ArrayList<Movie> parseMovie(BufferedReader bufferedReader) {
		// TODO Auto-generated method stub
		//BufferedReader inputStream = null;
        // We need a try catch block so we can handle any potential IO errors
		ArrayList<Movie> movies=new ArrayList<Movie>();
        try {
               try {
                      //inputStream = new BufferedReader(new FileReader(filePath));
                      String lineContent = null;
                      // Loop will iterate over each line within the file.
                      // It will stop when no new lines are found.
                      bufferedReader.readLine();
                      while ((lineContent = bufferedReader.readLine()) != null) {
                    	  {
                    		  //System.out.println("Found the line: " + lineContent);
                    		  movies.add(parseLine(lineContent));
                    	  }
} }
               // Make sure we close the buffered reader.
               finally {
                      if (bufferedReader != null)
                    	  bufferedReader.close();
               }
        } catch (IOException e) {
               e.printStackTrace();
  }// end of method
		
		return movies;
	}

	private static Movie parseLine(String lineContent) {
		// TODO Auto-generated method stub
		String[] resultingTokens = lineContent.split(",");
		Movie movie=new Movie();
		movie.setYear(Integer.parseInt(resultingTokens[0]));
		movie.setName(resultingTokens[1]);
		movie.setTotal(Double.parseDouble(resultingTokens[2]));
	       /*for (int i = 0; i < resultingTokens.length; i++){
	    	   System.out.println(resultingTokens [i].trim());
	    	   movie.setYear(resultingTokens[i]);
	    	   
	￼}*/
		return movie;
	}
	
	

}
