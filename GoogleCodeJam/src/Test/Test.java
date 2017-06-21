package Test;

import java.math.BigInteger;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BigInteger temp=new BigInteger("1090535681");
		BigInteger start=new BigInteger("2");
		BigInteger remainder=new BigInteger("0");
		//System.out.println("temp "+temp+" MOd is "+temp.mod(start));
		if(temp.mod(start).equals(remainder))
		{
			System.out.println("for base value of divisor is "+start);
		}
		else
		{
			BigInteger loop=new BigInteger("3");
			BigInteger loop1=new BigInteger("3");
			BigInteger loop2=new BigInteger("3");
			while((loop.multiply(loop2)).equals(temp) ||(loop.multiply(loop)).compareTo(temp)<=0)
			{
				System.out.println("--Value is"+loop+" Mod is "+temp.mod(loop));
				if(temp.mod(loop).equals(remainder))
				{	System.out.println("for base value of divisor is "+loop);
					break;
				}
				loop=loop.add(start);
			}
		}

	}
}
