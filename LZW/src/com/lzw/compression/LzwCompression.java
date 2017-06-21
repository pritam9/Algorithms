package com.lzw.compression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LzwCompression {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str="abbbab";
		String keyString ="";
		Map<String, Integer> table = new HashMap<String,Integer>();
		for(int i=0;i<256;i++){
			//System.out.println((char)i+" - "+i );
			table.put((char)i+"", i);
		}
		for(char c : str.toCharArray()){
			String key = keyString+c;
			
			//System.out.println(keyString);
			if(table.containsKey(key)){
				keyString+=c;
			}else{
				String bin = String.format("%16s", Integer.toBinaryString(table.get(keyString))).replace(' ', '0');
				System.out.println("Value is - "+table.get(keyString)+" - "+bin);
				table.put(key,table.size());
				keyString=c+"";
			}
		}
		System.out.println("Value is - "+table.get(keyString));
		//System.out.println(table);

	}

}
