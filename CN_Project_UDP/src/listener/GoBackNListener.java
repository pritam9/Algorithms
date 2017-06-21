package listener;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Timer;

import client.UdpClient;

public class GoBackNListener implements Runnable {
	private static final int PACKETSIZE=100;
	private Thread thread;
	private static InetAddress host;
	private DatagramSocket socket;
	private int window_size;
	private int number_of_packets;
	private int lastPacketSent;
	private int last_ack_num;
	private int step;
	private Timer timer;
	
	/**
	 * @return the timer
	 */
	public Timer getTimer() {
		return timer;
	}



	/**
	 * @param timer the timer to set
	 */
	public void setTimer(Timer timer) {
		this.timer = timer;
	}



	/**
	 * @return the step
	 */
	public int getStep() {
		return step;
	}



	/**
	 * @param step the step to set
	 */
	public void setStep(int step) {
		this.step = step;
	}



	/**
	 * @return the lastPacketSent
	 */
	public int getLastPacketSent() {
		return lastPacketSent;
	}



	/**
	 * @param lastPacketSent the lastPacketSent to set
	 */
	public void setLastPacketSent(int lastPacketSent) {
		this.lastPacketSent = lastPacketSent;
	}



	/**
	 * @return the window_size
	 */
	public int getWindow_size() {
		return window_size;
	}



	/**
	 * @param window_size the window_size to set
	 */
	public void setWindow_size(int window_size) {
		this.window_size = window_size;
	}



	/**
	 * @return the number_of_packets
	 */
	public int getNumber_of_packets() {
		return number_of_packets;
	}



	/**
	 * @param number_of_packets the number_of_packets to set
	 */
	public void setNumber_of_packets(int number_of_packets) {
		this.number_of_packets = number_of_packets;
	}



	/**
	 * @return the thread
	 */
	public Thread getThread() {
		return thread;
	}



	/**
	 * @param thread the thread to set
	 */
	public void setThread(Thread thread) {
		this.thread = thread;
	}



	/**
	 * @return the socket
	 */
	public DatagramSocket getSocket() {
		return socket;
	}



	/**
	 * @param socket the socket to set
	 */
	public void setSocket(DatagramSocket socket) {
		this.socket = socket;
	}


	/**
	 * @return the last_ack_num
	 */
	public int getLast_ack_num() {
		return last_ack_num;
	}



	/**
	 * @param last_ack_num the last_ack_num to set
	 */
	public void setLast_ack_num(int last_ack_num) {
		this.last_ack_num = last_ack_num;
	}



	public GoBackNListener(DatagramSocket socket, int window_size,int numberOfPackets,int step, Timer timer) {
		super();
		thread=new Thread(this);
		this.socket = socket;
		this.window_size = window_size;
		this.number_of_packets=numberOfPackets;
		this.lastPacketSent=window_size;
		this.last_ack_num=0;
		this.step=step;
		this.timer=timer;
		try {
			host= InetAddress.getByName("localhost");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}



	@Override
	public void run() {
		byte [] data = ("hi").getBytes() ;
		DatagramPacket packet = new DatagramPacket( data, data.length , host,UdpClient.port) ;
		while(last_ack_num<number_of_packets){
			packet.setData( new byte[PACKETSIZE] ) ;
			try {
				socket.receive( packet ) ;
				String ack = new String(packet.getData());
				int received_ack =Integer.parseInt(ack.trim());
				if(last_ack_num+1==received_ack){
					last_ack_num=received_ack;
					System.out.println("\nAcknowledgement received for packet - "+last_ack_num);
					if(lastPacketSent!=number_of_packets){
						//Thread.sleep(100);
						lastPacketSent++;
						System.out.println("Sending packet number - "+lastPacketSent);
						data = ("GBN-"+step+"-"+lastPacketSent).getBytes() ;
						packet = new DatagramPacket( data, data.length , host ,UdpClient.port) ;
						socket.send( packet ) ;
						try{
							UdpClient.timer.cancel();
							UdpClient.timer.schedule(new MyTimerTask(), UdpClient.timeout);	
							}catch(IllegalStateException e){
								UdpClient.timer=new Timer();
								UdpClient.timer.schedule(new MyTimerTask(), UdpClient.timeout);	
							}
					}
				}else{
					System.out.println("Received Acknowledgement But missing one packet. Hence Ignoring acknowledgement for Packet - "+received_ack);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
