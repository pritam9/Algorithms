package com.tcp.server;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Scanner;

public class MyServer implements Runnable {
	private Socket socket;
	private static int fileCount=0;
	public MyServer(Socket sock)
	{
		this.socket=sock;
	}

	public static void main(String[] args) {
		ServerSocket serverSocket=null;
		//Socket socket=null;
		try {
			 serverSocket = new ServerSocket(5431);
			 while (true) {
		         Socket sock = serverSocket.accept();
		         System.out.println("Connected");
		         new Thread(new MyServer(sock)).start();
		      }
			//socket = serverSocket.accept();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				//socket.close();
				serverSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Scanner serverScanner;
		try {
			serverScanner = new Scanner(socket.getInputStream());
			StringBuilder sb = new StringBuilder();
			if(serverScanner.hasNext()){
				//System.out.println(serverScanner.nextLine());
				String[] request = serverScanner.nextLine().split(" ");
				String[] host = serverScanner.nextLine().split(" ");
				//System.out.println(sb.toString());
				switch(request[0]){
				case "GET": respondToGet(request[1],request[2]);
					break;
				case "PUT":	
						serverScanner.nextLine();
						respondToPut(request[1],serverScanner);
						
					break;
				default: respondWithError(request[0]+" is not a valid method. Please Use GET/PUT.");
					break;
				}
				
			
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private void respondWithError(String string) {
		// TODO Auto-generated method stub
		try {
			PrintWriter pw=new PrintWriter(socket.getOutputStream());
			pw.println("HTTP/1.1 500 "+string);
			pw.println("");
			pw.flush();
			socket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void respondToPut(String http, Scanner scanner) {
		// TODO Auto-generated method stub
		PrintWriter pw;
		
		File file = new File("CopiedFromClient"+fileCount+".txt");
		fileCount++;
		if(!file.exists()){
			try {
				pw = new PrintWriter(socket.getOutputStream());
				//file.createNewFile();
				
				//socket.getInputStream().close();
				//System.out.println("Socket IS Closed");
				pw.println(http+" 200 OK File Created.");
				pw.println("");
				pw.flush();
				Files.copy(socket.getInputStream(), file.toPath());
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				respondWithError("Error Copying File");
			}
		}else{
			try {
				Files.copy(socket.getInputStream(), file.toPath());
				socket.getInputStream().close();
				System.out.println("Socket IS Closed");
				pw = new PrintWriter(socket.getOutputStream());
				pw.println(http+" 200 OK File Created.");
				pw.println("");
				pw.flush();
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				respondWithError("Error Copying File");
			}
			
			
			
		}
		
		
	}

	private void respondToGet(String filename, String http) {
		// TODO Auto-generated method stub
		try {
			PrintWriter pw=new PrintWriter(socket.getOutputStream());
			File file = new File(filename.substring(1));
			if(!file.exists()){
				pw.println(http+" 400 File Not Found "+filename);
				pw.println("");
				pw.flush();
			}else{
			pw.println(http+" 200 OK "+filename);
			//pw.println("");
			pw.flush();
			Files.copy(file.toPath(), socket.getOutputStream());
			socket.getOutputStream().close();
			socket.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
