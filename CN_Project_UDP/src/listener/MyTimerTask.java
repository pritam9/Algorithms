package listener;

import java.util.TimerTask;

import client.UdpClient;

public class MyTimerTask extends TimerTask {
	private long timeout;
	
	

	/**
	 * @return the timeout
	 */
	public long getTimeout() {
		return timeout;
	}



	/**
	 * @param timeout the timeout to set
	 */
	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}



	public MyTimerTask() {
		//super();
	}



	@Override
	public void run() {
		UdpClient.sendRemainingPackets();
	}

}
