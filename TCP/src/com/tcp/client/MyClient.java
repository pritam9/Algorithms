package com.tcp.client;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.util.Scanner;

public class MyClient {

	public static void main(String[] args) {
		try{
		String host = args[0];
		int port = Integer.parseInt(args[1]);
		String method = args[2].trim().toUpperCase();
		String filename = args[3];
		
		//System.out.println("Argument1 - "+args[0]+"\nArgument2 -"+args[1]+"\nArgument3 - "+args[2]+"\nArgument4 - "+args[3]);
		switch(method){
		case "GET": sendGetRequest(host,port,method,filename);
			break;
		case "PUT":	sendPutRequest(host,port,method,filename);
			break;
		}
		}catch(Exception e){
			System.out.println("Please pass all 'Host Port Method FileName'");
		}
		

	}

	private static void sendPutRequest(String host, int port, String method, String filename) {
		// TODO Auto-generated method stub
		try {
			// Create connection with host with given port number
			Socket myClient = new Socket(host, port);
			
			//Create a scanner to get values from socket input stream
			
			/*Scanner scan = new Scanner(System.in);
			//System.out.println("Enter a number");
			//int num = scan.nextInt();*/
			//Use print writer to send request to host
			PrintWriter pw = new PrintWriter(myClient.getOutputStream());
			pw.println(method+" /HTTP/1.1");
			pw.println("Host: "+host);
			pw.println("Content-Type: plain/text");
			pw.flush();
			File file = new File(filename);
			if(file.exists()){
				Files.copy(file.toPath(), myClient.getOutputStream());
				//myClient.getOutputStream().close();
				Scanner clientScanner = new Scanner(myClient.getInputStream());
				System.out.println("CLIENT OS Closed.");
				if(clientScanner.hasNext()){
					System.out.println(clientScanner.nextLine());
				}
				myClient.close();
			}else{
				System.out.println("File Not Exists!!");
			}
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void sendGetRequest(String host, int port, String method, String filename) {
		// TODO Auto-generated method stub
		try {
			// Create connection with host with given port number
			Socket myClient = new Socket(host, port);
			
			//Create a scanner to get values from socket input stream
			Scanner clientScanner = new Scanner(myClient.getInputStream());
			/*Scanner scan = new Scanner(System.in);
			//System.out.println("Enter a number");
			//int num = scan.nextInt();*/
			//Use print writer to send request to host
			PrintWriter pw = new PrintWriter(myClient.getOutputStream());
			pw.println(method+" /"+filename+" HTTP/1.1");
			pw.println("Host: "+host);
			pw.println("");
			pw.flush();
			// Wait for response from host
			while(clientScanner.hasNext()){
				System.out.println(clientScanner.nextLine());
			}
			myClient.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
