
public class TestAverage {
	double grades[]={4.00,4.00,3.33,2.67,3.67};
	double avg=getAverage(grades,0,4);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestAverage test1=new TestAverage();
		double grades[]={4.00,4.00,3.33,2.67,3.67};
		double avg=getAverage(grades,0,4);
		System.out.println("Average is - "+avg);
		
		    java.util.Date utilDate = new java.util.Date();
		    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		    System.out.println("utilDate:" + utilDate);
		    System.out.println("sqlDate:" + sqlDate);
		    test test2=new test(test1);
		    test2.printSome();
		
	}

	private static double getAverage(double[] grades, int i, int j) {
		// TODO Auto-generated method stub
		double avg=0;
		if(i>j)
			{
				System.out.println("Test");
				return 0;
			}
		else
		{
			System.out.println("Else");
			avg=(grades[i]+((j-i)*getAverage(grades, i+1, j)))/(j-i+1);
		}
		return avg;
	}

}


