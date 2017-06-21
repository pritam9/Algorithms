package server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Random;

public class UdpServer {
	private static final int PACKETSIZE=100;
	private static int last_packet_num=0;
	private static int simulation_step=0;
	private static int ack_num_to_miss;
	private static int port;
	public static void main(String[] args) {

		if( args.length == 1 )
		{

			try
			{
				// Convert the argument to ensure that is it valid
				port = Integer.parseInt( args[0] ) ;
				//int port =1025;
				// Construct the socket
				DatagramSocket socket = new DatagramSocket( port ) ;

				System.out.println( "The server is ready..." ) ;


				for( ;; )
				{
					// Create a packet
					DatagramPacket packet = new DatagramPacket( new byte[PACKETSIZE], PACKETSIZE ) ;

					// Receive a packet (blocking)
					socket.receive( packet ) ;
					//System.out.println( packet.getAddress() + " " + packet.getPort() + ": " + new String(packet.getData()) ) ;
					String data = new String(packet.getData());
					String[] received = data.split("-");
					try{
						int received_step= Integer.parseInt(received[1].trim());
						checkIfStepChanges(received_step);
						int ack_num = Integer.parseInt(received[2].trim());
						if(simulation_step>=3){
						if(simulation_step==3 || simulation_step==6){
							if(ack_num!=ack_num_to_miss){
								packet.setData((""+ack_num).getBytes());
								socket.send( packet );
								System.out.println(packet.getAddress() + " " + packet.getPort() + ": Received Segment - "+ ack_num);
							}else{
								System.out.println(packet.getAddress() + " " + packet.getPort() + ": Received Segment - "+ ack_num);
								System.out.println(packet.getAddress() + " " + packet.getPort() + ": Acknowledgement Sent but lost before reaching client - Segment id -"+ ack_num);
								ack_num_to_miss=-1;
							}
						}else{
							packet.setData((""+ack_num).getBytes());
							socket.send( packet );
							System.out.println(packet.getAddress() + " " + packet.getPort() + ": Received Segment - "+ ack_num);
							last_packet_num=ack_num;
						}
						}
						else if(ack_num-1==last_packet_num){
							last_packet_num=ack_num;
							packet.setData((""+ack_num).getBytes());
							socket.send( packet );
							System.out.println(packet.getAddress() + " " + packet.getPort() + ": Received Segment - "+ last_packet_num);
						}else{
							System.out.println(packet.getAddress() + " " + packet.getPort() + ": Packets are not in order. Received Segment - "+ack_num);
						}
					}catch(ArrayIndexOutOfBoundsException e){
						System.err.println(packet.getAddress() + " " + packet.getPort() + ": Invalid Checksum!! Invalidating packet");
					}


				}  
			}
			catch( Exception e )
			{
				System.out.println(e ) ;
				e.printStackTrace();
			}
		}else{
			System.out.println("--- PROVIDE PORT NUMBER ABOVE 1025 FOR EXECUTION ---");
		}
	}
	private static void checkIfStepChanges(int received_step) {
		if(received_step!=simulation_step){
			simulation_step=received_step;
			if(received_step==3 || received_step==6){
				Random generator = new Random(); 
				ack_num_to_miss = generator.nextInt(last_packet_num) + 1;
				System.out.println("~~~~~~~ For simulation, Server is not sending acknowlegement for packet number - "+ack_num_to_miss+" ~~~~~~~ ");
			}
			last_packet_num=0;
		}
	}

}
