import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 int[] a = {4,4,4,3,2,1,1};
		 remove_n_diplicates(a,3);
		 

	}

	private static void remove_n_diplicates(int[] a, int n) {
		// TODO Auto-generated method stub
		Arrays.sort(a);
		int count=1;
		int prev =a[0];
		for(int i=1;i<a.length;i++){
			if(prev==a[i]){
				count++;
			}else{
				if(count==n){
					//remove array items
				}
				count=1;
				prev=a[i];
			}
		}
		
	}

}
