package client;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.zip.CRC32;

import listener.GoBackNListener;
import listener.MyTimerTask;
import listener.SRListener;

public class UdpClient {
	private static int PACKETSIZE=1000;
	private static DatagramSocket socket;
	private static InetAddress host;
	private static int window_size;
	private static GoBackNListener listener;
	private static SRListener srListener;
	private static String protocol;
	public static int port;
	private static int packet_num;
	public static long timeout;
	private static MyTimerTask timerTask;
	public static Timer timer;
	private static int step;
	private static String input_file;
	private static InputFields inputFields;
	private static int m;
	public static Set<Integer> sentPackets=new HashSet<Integer>();
	public static int windowStartIndex=1;
	public static void main(String[] args) {
		packet_num=20;
		port=1025;
		m=3;
		window_size=7;
		protocol = "GBN";
		timeout=20000;
		socket = null ;
		if(args.length==3){
			input_file=args[0];
			try{
				port=Integer.parseInt(args[1]);
				packet_num=Integer.parseInt(args[2]);
				readFileAttributes(input_file);
			}catch(Exception e){
				System.out.println("Invalid inputs provided!!! Please correct and send back.");
				return;
			}

			try
			{
				host = InetAddress.getByName("localhost") ;
				socket = new DatagramSocket() ;
				timerTask = new MyTimerTask();
				timer = new Timer();
				if(protocol.equals("GBN")){
					listener=new GoBackNListener(socket,window_size,packet_num,1,timer);
					listener.getThread().start();
					System.out.println("\n------------------------------------------\n*** SIMULATING STEP ONE ***\n------------------------------------------");
					step=1;
					simulateStepOne();
					waitForThread();
					listener=new GoBackNListener(socket,window_size,packet_num,2,timer);
					listener.getThread().start();
					System.out.println("\n------------------------------------------\n*** SIMULATING STEP TWO ***\n------------------------------------------");
					step=2;
					simulateStepTwo();
					waitForThread();
					Thread.sleep(4000);
					listener=new GoBackNListener(socket,window_size,packet_num,3,timer);
					listener.getThread().start();
					System.out.println("\n------------------------------------------\n*** SIMULATING STEP THREE ***\n------------------------------------------");
					step=3;
					simulateStepThree();
					waitForThread();
				}else if(protocol.equals("SR")){
					srListener=new SRListener(socket,window_size,packet_num,1,timer);
					srListener.getThread().start();
					System.out.println("\n------------------------------------------\n*** SIMULATING STEP ONE ***\n------------------------------------------");
					step=4;
					simulateStepOneSR();
					waitForThread();
					srListener=new SRListener(socket,window_size,packet_num,1,timer);
					srListener.getThread().start();
					System.out.println("\n------------------------------------------\n*** SIMULATING STEP TWO ***\n------------------------------------------");
					step=5;
					simulateStepTwoSR();
					waitForThread();
					srListener=new SRListener(socket,window_size,packet_num,1,timer);
					srListener.getThread().start();
					System.out.println("\n------------------------------------------\n*** SIMULATING STEP THREE ***\n------------------------------------------");
					step=6;
					simulateStepThreeSR();
					waitForThread();
				}else{
					System.out.println("---- INVALID PROTOCOL ----");
				}
				System.out.println("~~~~~~~~~ END OF SIMULATION ~~~~~~~~~");
				System.exit(0);;
			}
			catch( Exception e )
			{
				System.out.println( e ) ;
				e.printStackTrace();
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			finally
			{
				if( socket != null )
					socket.close() ;
			}
		}else{
			System.out.println("--- PROVIDE FILE PATH, PORT, NUMBER OF PACKETS FOR EXECUTION ---");
		}
	}


	private static void simulateStepThreeSR() {
		
		sentPackets.clear();
		sendPackets(1,window_size,-1);
		try{
			timer.cancel();
			timer.schedule(new MyTimerTask(), timeout);	
		}catch(IllegalStateException e){
			timer=new Timer();
			timer.schedule(new MyTimerTask(), timeout);	
		}
	}


	private static void simulateStepTwoSR() {
		Random generator = new Random(); 
		int skip_index = generator.nextInt(window_size) + 1;
		System.out.println("~~~~~~~ For simulation, I am not sending packet number - "+skip_index+" ~~~~~~~ ");
		sentPackets.clear();
		sendPackets(1,window_size,skip_index);
		try{
			timer.cancel();
			timer.schedule(new MyTimerTask(), timeout);	
		}catch(IllegalStateException e){
			timer=new Timer();
			timer.schedule(new MyTimerTask(), timeout);	
		}
	}


	private static void simulateStepOneSR() {
		Random generator = new Random(); 
		int skip_index = generator.nextInt(window_size) + 1;
		System.out.println("~~~~~~~ For simulation, I am changing checksum packet number - "+skip_index+" ~~~~~~~ ");
		sentPackets.clear();
		sendPackets(1,window_size,skip_index);
		try{
			timer.cancel();
			timer.schedule(new MyTimerTask(), timeout);	
		}catch(IllegalStateException e){
			timer=new Timer();
			timer.schedule(new MyTimerTask(), timeout);	
		}
	}


	private static void readFileAttributes(String input_file2) throws IOException {
		InputFields inputFields = new InputFields();
		FileReader fileReader=new FileReader(input_file2);
		BufferedReader bufferedReader=new BufferedReader(fileReader);
		String line=bufferedReader.readLine();
		protocol=line.trim();
		line=bufferedReader.readLine();
		String[] lineData=line.split(" ");
		m=Integer.parseInt(lineData[0].trim());
		window_size=Integer.parseInt(lineData[1].trim());
		line=bufferedReader.readLine();
		timeout=Long.parseLong(line.trim());
		line=bufferedReader.readLine();
		PACKETSIZE=Integer.parseInt(line.trim());

	}


	private static void waitForThread() {
		if(protocol.equals("GBN")){
		while(listener.getThread().isAlive()){
		}
	}else{
		while(srListener.getThread().isAlive()){
		}
	}
	}

	private static void sendPackets(int start_index, int end_index, int skip_index) {
		for(int i=start_index;i<=end_index;i++){
			System.out.println("Sending packet number - "+i);
			sentPackets.add(i);
			if(i!=skip_index){
				try {
					byte [] data = (protocol+"-"+step+"-"+i).getBytes() ;
					DatagramPacket packet = new DatagramPacket( data, data.length , host,port) ;
					socket.send( packet ) ;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else if(skip_index!=-1 && step==1){
				try {
					byte [] data = (protocol+"-"+step+"-"+i).getBytes() ;
					CRC32 checksum = new CRC32();
					checksum.update(data);
					//checksum.update(dataBytes);
					byte[] checksumBytes = ByteBuffer.allocate(8).putLong(checksum.getValue()).array();
					DatagramPacket packet = new DatagramPacket( checksumBytes, checksumBytes.length , host,port) ;
					socket.send( packet ) ;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		windowStartIndex=end_index+1;
	}

	private static void simulateStepOne(){
		Random generator = new Random(); 
		int skip_index = generator.nextInt(window_size) + 1;
		System.out.println("~~~~~~~ For simulation, I am changing checksum for packet number - "+skip_index+" ~~~~~~~ ");

		sendPackets(1,window_size,skip_index);
		try{
			timer.cancel();
			timer.schedule(new MyTimerTask(), timeout);	
		}catch(IllegalStateException e){
			timer=new Timer();
			timer.schedule(new MyTimerTask(), timeout);	
		}
		/*try {
			listener.getThread().join(35000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(listener.getLast_ack_num()<packet_num){
			System.out.println("Timeout!! Not all responses recieved during this window.");
			int end_index = (listener.getLast_ack_num()+window_size > packet_num) ? packet_num : listener.getLast_ack_num()+window_size;
			sendPackets(listener.getLast_ack_num()+1, end_index,-1,1);
			try {
				listener.getThread().join(35000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}*/

	}

	private static void simulateStepTwo(){
		Random generator = new Random(); 
		int skip_index = generator.nextInt(window_size) + 1;
		System.out.println("~~~~~~~ For simulation, I am not sending packet number - "+skip_index+" ~~~~~~~ ");

		sendPackets(1,window_size,skip_index);

		try{
			timer.cancel();
			timer.schedule(new MyTimerTask(), timeout);	
		}catch(IllegalStateException e){
			timer=new Timer();
			timer.schedule(new MyTimerTask(), timeout);	
		}
		/*try {
			listener.getThread().join(35000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(listener.getLast_ack_num()<packet_num){
				System.out.println("Timeout!! Not all responses recieved during this window.");
				int end_index = (listener.getLast_ack_num()+window_size > packet_num) ? packet_num : listener.getLast_ack_num()+window_size;
				sendPackets(listener.getLast_ack_num()+1, end_index,-1,2);
				try {
					listener.getThread().join(35000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}*/
	}

	private static void simulateStepThree(){
		/*Random generator = new Random(); 
		int skip_index = generator.nextInt(window_size) + 1;
		System.out.println("~~~~~~~ For simulation, I am not sending packet number - "+skip_index+" ~~~~~~~ ");
		 */
		sendPackets(1,window_size,-1);
		//MyTimerTask timerTask = new MyTimerTask(timeout);
		try{
			timer.cancel();
			timer.schedule(new MyTimerTask(), timeout);	
		}catch(IllegalStateException e){
			timer=new Timer();
			timer.schedule(new MyTimerTask(), timeout);	
		}
		/*try {
			listener.getThread().join(35000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/

	}

	public static void sendRemainingPackets(){
		if(protocol.equals("GBN")){
		if(listener.getLast_ack_num()!=packet_num){
			System.out.println("Timeout!! Not all responses recieved during this window.");
			int end_index = (listener.getLast_ack_num()+window_size > packet_num) ? packet_num : listener.getLast_ack_num()+window_size;
			sendPackets(listener.getLast_ack_num()+1, end_index,-1);
			try{
				timer.cancel();
				timer.schedule(new MyTimerTask(), timeout);	
			}catch(IllegalStateException e){
				timer=new Timer();
				timer.schedule(new MyTimerTask(), timeout);	
			}

		}
	}else{
		if(sentPackets.size()>0){
			System.out.println("Timeout!! Not all responses recieved during this window.");
			for(int i : sentPackets){
				try {
					byte [] data = (protocol+"-"+step+"-"+i).getBytes() ;
					DatagramPacket packet = new DatagramPacket( data, data.length , host,port) ;
					socket.send( packet ) ;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try{
				timer.cancel();
				timer.schedule(new MyTimerTask(), timeout);	
			}catch(IllegalStateException e){
				timer=new Timer();
				timer.schedule(new MyTimerTask(), timeout);	
			}
		}
	}
	}


	public static void sendNextWindow() {
		int end_index = ((windowStartIndex+window_size-1) > packet_num) ? packet_num : (windowStartIndex+window_size-1);
		sendPackets(windowStartIndex, end_index, -1);
		try{
			timer.cancel();
			timer.schedule(new MyTimerTask(), timeout);	
		}catch(IllegalStateException e){
			timer=new Timer();
			timer.schedule(new MyTimerTask(), timeout);	
		}
	}

}
