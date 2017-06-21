import java.util.Random;

public class RandomNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String inputNumbers="";
		Random rand=new Random();
		for(long i=1L;i<100000L;i++)
		{
			inputNumbers=inputNumbers+rand.nextInt()+";";
		}
		System.out.println(inputNumbers);
	}

}
