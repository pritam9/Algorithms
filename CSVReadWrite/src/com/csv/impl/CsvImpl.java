package com.csv.impl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class CsvImpl {
	static CSVWriter writer;
	
	public static void main(String[] args){
		try {
			String csv = "analysis.csv";
			writer = new CSVWriter(new FileWriter(csv));
			String [] country = {"user_id","auditRisc","auditScore","pcaCategory","peakBAC","typicalBAC"};
			writer.writeNext(country);
			/*CSVReader reader = new CSVReader(new FileReader("/MS_CS/RA Work/RA Analysis/feedback_analysis.csv"));
			String[] nextLine=reader.readNext();
		      while ((nextLine = reader.readNext()) != null) {
		    	 System.out.println(nextLine[2]);
		    	 Gson g = new Gson();
		    	 String str = g.toJson(nextLine[2]);*/
		    	 //JsonElement jelement = new JsonParser().parse(new FileReader("/MS_CS/RA Work/RA Analysis/feedback_analysis_json"));
			/* JsonArray  joArray = (JsonArray)new JsonParser().parse(new FileReader("/MS_CS/RA Work/RA Analysis/feedback_analysis_json"));
		    	    System.out.println(joArray.get(0).getAsJsonObject().get("user_id"));

		    	    jobject = jobject.getAsJsonObject("data");
		    	    JsonArray jarray = jobject.getAsJsonArray("translations");
		    	    jobject = jarray.get(0).getAsJsonObject();
		    	    String result = jobject.get("translatedText").toString();
		    	    return result;
		    	    for(int i=0;i<joArray.size();i++){
		    	    	JsonObject jo = joArray.get(i).getAsJsonObject();
		    	    	String user_id = jo.get("user_id").toString();
		    	    	
		    	    	//JsonPrimitive feedbackJson = (JsonPrimitive) new JsonParser().parse(jo.get("feedback").toString());
		    	    	//JsonObject feedbackJson = jo.get("feedback").getAsJsonObject();
		    	    	String auditRisc = feedbackJson.getAsJsonObject().get("auditRisc").toString();
				    	  System.out.println("For user "+ user_id+ " audit risc is - "+auditRisc);
		    	    }*/
		    	  
		       //}
		      //reader.close();
			
			JSONArray jo = (JSONArray) new JSONParser().parse(new FileReader("/MS_CS/RA Work/RA Analysis/feedback_analysis_json"));
			for(int i=0;i<jo.size();i++){
				JSONObject joObject = (JSONObject) jo.get(i);
				String user_id =joObject.get("user_id").toString();
				JSONObject feedbackObject = (JSONObject) new JSONParser().parse(joObject.get("feedback").toString());
				String auditRisc = feedbackObject.get("auditRisc").toString();
				String auditScore = feedbackObject.get("auditScore").toString();
				String pcaCategory = feedbackObject.get("pcaCategory").toString();
				String peakBAC = feedbackObject.get("peakBAC").toString();
				String typicalBAC = feedbackObject.get("typicalBAC").toString();
		    	System.out.println("For user "+ user_id+ " audit risc is - "+auditRisc+" - "+auditScore +" - "+pcaCategory+" - "+peakBAC+" - "+typicalBAC);
		    	writeToCsv(user_id,auditRisc,auditScore,pcaCategory,peakBAC,typicalBAC);
			}
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void writeToCsv(String user_id, String auditRisc, String auditScore, String pcaCategory,
			String peakBAC, String typicalBAC) {
		// TODO Auto-generated method stub			
			String [] country = {user_id,auditRisc,auditScore,pcaCategory,peakBAC,typicalBAC};
			writer.writeNext(country);


		
	}

}
