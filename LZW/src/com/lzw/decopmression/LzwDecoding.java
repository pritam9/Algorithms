package com.lzw.decopmression;

import java.util.HashMap;
import java.util.Map;

public class LzwDecoding {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] codes={97,98,257,256};
		//String keyString ="";
		Map<Integer, String> table = new HashMap<Integer,String>();
		for(int i=0;i<256;i++){
			//System.out.println((char)i+" - "+i );
			table.put( i,(char)i+"");
		}
		String keyString = table.get(codes[0]);
		System.out.println("Decode is - "+keyString);
		for(int i=1;i<codes.length;i++){
			String newString = "";
			
			//System.out.println(keyString);
			if(table.containsKey(codes[i])){
				newString=table.get(codes[i]);
			}else{
				newString=keyString+keyString.toCharArray()[0];
			}
			System.out.println("Decode is - "+newString);
			table.put(table.size(), keyString+newString.toCharArray()[0]);
			keyString=newString;
			//System.out.println("Key String is - "+keyString);
		}
		//System.out.println("Decode is - "+keyString);

	}

}
