package jamcoins;

import java.math.BigInteger;

public class JamCoins {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str="1001000010010001";
		
		BigInteger i=new BigInteger("101111011110111101111011110111");
		System.out.println(" Value is : "+i);
		//System.out.println(i.isProbablePrime(1));
		BigInteger[] values=new BigInteger[9];
		values=getValues(str);
		for(int base=0;base<9;base++)
		{
			System.out.println("At base"+ base +2+" Value is :"+ values[base]);
		}
		if(!arePrime(values))
		{
			getDivisors(values);
		}
		
	}

	private static void getDivisors(BigInteger[] values) {
		// TODO Auto-generated method stub
		BigInteger start=new BigInteger("2");
		BigInteger remainder=new BigInteger("0");
		for(int base=0;base<9;base++)
		{
			BigInteger temp=values[base];
			//System.out.println("temp "+temp+" MOd is "+temp.mod(start));
			if(temp.mod(start).equals(remainder))
			{
				System.out.println("for base"+base+" value of divisor is "+start);
			}
			else
			{
				BigInteger loop=new BigInteger("3");
				while((loop.multiply(loop)).equals(temp) ||(loop.multiply(loop)).compareTo(temp)<=0)
				{
					//System.out.println("--Value is"+loop);
					if(temp.mod(loop).equals(remainder))
						System.out.println("for base"+base+" value of divisor is "+loop);
					loop=loop.add(start);
				}
				System.out.println("--Value is"+(loop.multiply(loop)).compareTo(temp));
				/*for(long i=3;i*i<=temp.longValue();i+=2)
				{
					if(temp.longValue()%i==0)
					{
						//System.out.println("for base"+base+" value of divisor is "+i);
					}
				}*/
			}
		}
	}

	private static boolean arePrime(BigInteger[] values) {
		// TODO Auto-generated method stub
		BigInteger start=new BigInteger("2");
		BigInteger remainder=new BigInteger("0");
		for(int base=0;base<9;base++)
		{
			BigInteger temp=values[base];
			if(temp.mod(start).equals(remainder))
			{
				//System.out.println("for base"+base+2+" value of divisor is "+start);
				return false;
			}
			else
			{
				BigInteger loop=new BigInteger("3");
				while((loop.multiply(loop)).equals(temp) ||(loop.multiply(loop)).compareTo(temp)<=0)
				{
					if(temp.mod(loop).equals(remainder))
					{	
						//System.out.println("for base"+base+" value of divisor is "+loop);
						return false;
					}
					loop=loop.add(start);
				}
				//BigIntger
				/*for(long i=3;i*i<=temp.longValue();i+=2)
				{
					if(temp.longValue()%i==0)
					{
						//System.out.println("for base"+base+2+" value of divisor is "+i);
						return false;
					}
				}*/
			}
		}
		return true;
	}

	private static BigInteger[] getValues(String str) {
		// TODO Auto-generated method stub
		char[] charArray=str.toCharArray();
		BigInteger[] values={new BigInteger("0"),new BigInteger("0"),new BigInteger("0"),new BigInteger("0"),new BigInteger("0"),new BigInteger("0"),new BigInteger("0"),new BigInteger("0"),new BigInteger("0")};
		BigInteger[] previousValues={new BigInteger("1"),new BigInteger("1"),new BigInteger("1"),new BigInteger("1"),new BigInteger("1"),new BigInteger("1"),new BigInteger("1"),new BigInteger("1"),new BigInteger("1")};
			for(int i=charArray.length-1;i>=0;i--)
			{
				if(charArray[i]=='1')
				{
					for(int base=0;base<9;base++)
					{
						values[base]=values[base].add(previousValues[base]);
					}
				}
				BigInteger temp=new BigInteger("2");
				for(int base=0;base<9;base++)
				{
					previousValues[base]=previousValues[base].multiply(temp);
					temp=temp.add(new BigInteger("1"));
				}
			}
		
		return values;
	}

}
