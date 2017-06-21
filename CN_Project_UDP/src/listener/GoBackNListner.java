package listener;

import java.io.ObjectInputStream;

public class GoBackNListner implements Runnable {

	public Thread t;
	public ObjectInputStream ois;
	public int reply,x;


	public GoBackNListner(ObjectInputStream o,int i)
	{
		t=new Thread(this);
		ois=o;
		reply=-2;
		x=i;
	}

	@Override
	public void run() {
		try
		{
			int temp=0;
			while(reply!=-1)
			{
				reply=(Integer)ois.readObject();
				if(reply!=-1 && reply!=temp+1)
					reply=temp;
				if(reply!=-1)
				{
					temp=reply;
					System.out.println("Acknowledgement of frame no " + (reply%x) + " recieved.");
					System.out.println();
				}
			}
			reply=temp;
		}
		catch(Exception e)
		{
			System.out.println("Exception => " + e);
		}

	}

}
