package interviews;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class HighestVote {
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		int count = scan.nextInt();
		Map<String,Integer> votes=new TreeMap<String,Integer>();
		scan.nextLine();
		for(int i=0; i<count;i++){
			String name = scan.nextLine().trim();
			if(votes.containsKey(name)){
				votes.put(name, votes.get(name) + 1);	
			}else{
				votes.put(name, 1);
			}
			System.out.println(i);
		}
		
		List<Entry<String, Integer>> orderdList = new ArrayList<Entry<String,Integer>>(votes.entrySet());
		Collections.sort(orderdList,new Comparator<Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				// TODO Auto-generated method stub
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		
		System.out.println(orderdList.get(0).getKey()+" - "+orderdList.get(0).getValue());
	}
}
