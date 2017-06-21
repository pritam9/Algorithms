package com.twitter.aggregator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = df.parse("2016-12-01");
			System.out.println((date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String range = s.nextLine();
		String[] range_array = range.split(",");
		String start=range_array[0]+"-01";
		String end = range_array[1]+"-01";
		List<Engagement> engagements = new ArrayList<Engagement>();
		try {
		Date range_start = df.parse(start);
		Date range_end = df.parse(end);
		System.out.println(start+" to "+end);
		System.out.println(range_start+" to "+range_end);
		//s.nextLine();
		for(int i = 0; i<10;i++){
			String line1 = s.nextLine();
			String[] line1_array = line1.split(", ");
			System.out.println("------"+line1);
			Date enaggement_date = df.parse(line1_array[0].trim());
			
			if(enaggement_date.compareTo(range_start)>=0 && enaggement_date.compareTo(range_end)<=0){
				Engagement engagement = new Engagement();
				engagement.setDate(enaggement_date);
				engagement.setEngagement_name(line1_array[1]);
				engagement.setEngagement_count(Integer.parseInt(line1_array[2]));
				engagements.add(engagement);
				System.out.println(engagements.size());
			}
		}
		
		Collections.sort(engagements);
		Map<String, String> list_map = new HashMap<>();
		for(Engagement engagement:engagements){
			DateFormat df1 = new SimpleDateFormat("yyyy-MM");
			String key = df1.format(engagement.getDate());
			if(list_map.containsKey(key)){
				String value = list_map.get(key);
				value+=","+engagement.getEngagement_name()+","+engagement.getEngagement_count();
				list_map.put(key, value);
			}else{
				String value = engagement.getEngagement_name()+","+engagement.getEngagement_count();
				list_map.put(key, value);
			}
		}
		
		Iterator it = list_map.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        System.out.println(pair.getKey() + " = " + pair.getValue());
	        it.remove(); // avoids a ConcurrentModificationException
	    }
		
		
		//String line1 = s.next();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//String[] end =range.split(",");
		//System.out.println(s.next());
		
		s.nextLine();
		System.out.println(s.next());
		System.out.println(s.next());
		System.out.println(s.next());
		
		
		
	}

}
